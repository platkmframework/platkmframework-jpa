/*******************************************************************************
 * Copyright(c) 2023 the original author Eduardo Iglesias Taylor.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	 https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * 	Eduardo Iglesias Taylor - initial API and implementation
 *******************************************************************************/
package org.platkmframework.jpa.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Persistence;

import org.platkmframework.content.project.ProjectContent;
import org.platkmframework.jpa.base.PlatkmORMEntityManager;
import org.platkmframework.jpa.exception.PlatkmJpaException;
import org.platkmframework.jpa.mapping.DatabaseMapper;


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class PersistenceManager<E extends DatabaseMapper> {
	
	private static Logger logger = LoggerFactory.getLogger(PersistenceManager.class);
	
	private static PersistenceManager<?> persistenceManager;
	protected static Map<String, PlatkmEntityManagerFactory > mapFactory = new HashMap<>(); 
	
	ThreadLocal<Map<String, PlatkmORMEntityManager>> threadLocal; 
	 
	private PersistenceManager() {
		super();
		threadLocal = new ThreadLocal<>(); 
	}

	public static PersistenceManager<?> instance()
	{
		if(persistenceManager == null)
			persistenceManager = new PersistenceManager<>();
		
		return persistenceManager;
	}

	public synchronized void begin() {
		close(); 
		

		PersistenceInfoUtil.instance().getPersistenceInfoList().forEach(
			(info)->{
					createPlakmEnity(info.getName()); 
			});
	}
	
	public synchronized void begin(String persistenceUnitName) {
		close();  
		createPlakmEnity(persistenceUnitName);  
	}
	
	private void createPlakmEnity(String persistenceUnitName) {
		
		
		//Persistence.generateSchema(persistenceUnit.getName(), null); @TODO
			
		PlatkmORMEntityManager platkmEntityManager = mapFactory.get(persistenceUnitName).createEntityManager();
		platkmEntityManager.getTransaction().begin();
		put(persistenceUnitName, platkmEntityManager);
		
		//logger.info("connecton opened " + ProjectContent.instance().getProjectName() + " - " + persistenceUnit.getName());
		
	}

	public synchronized void commit() { 
		if(threadLocal.get() != null)
			threadLocal.get().forEach((k, v) -> v.getTransaction().commit());  
	}
	
	public synchronized void commit(String persistenceUnit) { 
		if(threadLocal.get() != null)
			threadLocal.get().get(persistenceUnit).getTransaction().commit();   
	}

	public synchronized void rollback() {
		if(threadLocal.get() != null)
			threadLocal.get().forEach((k, v) -> v.getTransaction().rollback());
	}
	
	public synchronized void rollback(String persistenceUnit) {
		if(threadLocal.get() != null)
			threadLocal.get().get(persistenceUnit).getTransaction().rollback();
	}
	
	
	public synchronized void  close() {
		if(threadLocal.get() != null)
			threadLocal.get().forEach((k, v) -> {
				
				try {
					
					if(v.getTransaction().isActive()){
						v.getTransaction().commit(); 
					}  
					
				} catch (Exception e) {
					v.getTransaction().rollback();
					new PlatkmJpaException(e);
				} finally { 
					v.close();
					try {
						  mapFactory.get(k).returnObject(v);
					} catch (Exception e) {
						new PlatkmJpaException(e);
					}finally {
						threadLocal.remove();
					}
				} 
			}); 
	}
	
	
	public synchronized void  close(String persistenceUnit) {
		if(threadLocal.get() != null && threadLocal.get().get(persistenceUnit) != null) {
			try {
				
				if(threadLocal.get().get(persistenceUnit).getTransaction().isActive()){
					threadLocal.get().get(persistenceUnit).getTransaction().commit(); 
				}  
				
			} catch (Exception e) {
				threadLocal.get().get(persistenceUnit).getTransaction().rollback();
				new PlatkmJpaException(e);
			} finally { 
				threadLocal.get().get(persistenceUnit).close();
				try {
					  mapFactory.get(persistenceUnit).returnObject(threadLocal.get().get(persistenceUnit));
				} catch (Exception e) {
					new PlatkmJpaException(e);
				}finally {
					threadLocal.remove();
				}
			}
		}
	}

	private void put(String name, PlatkmORMEntityManager platkmEntityManager) {
		if(threadLocal.get() == null) {
			threadLocal.set(new HashMap<>());
		}
		threadLocal.get().put(name, platkmEntityManager); 
		logger.info("connection put to threadLocal " + ProjectContent.instance().getProjectName() + " - " + name);
	} 
	
	public synchronized PlatkmORMEntityManager get(String name) {
		return threadLocal.get().get(name);
	}
	
	public void init() {
		
		PlatkmPersistenceFileParse platkmPersistenceFileParse = new PlatkmPersistenceFileParse();
		List<PersistenceInfo> list = platkmPersistenceFileParse.parse();
		if(list != null){
			PersistenceInfoUtil.instance().setPersistenceInfoList(list);
			PersistenceInfoUtil.instance().setLoaded(true);
			
			for (PersistenceInfo persistenceInfo : list) {
				PlatkmEntityManagerFactory plakmEntityManagerFactory = (PlatkmEntityManagerFactory) Persistence.createEntityManagerFactory(persistenceInfo.getName());
				mapFactory.put(persistenceInfo.getName(), plakmEntityManagerFactory);
				
				logger.info("Persistence :{}", persistenceInfo.getName());
			}
			
		}else {
			logger.warn("No se encontró información de pesistencia");
			PersistenceInfoUtil.instance().setNotFound(true);
		}		
	}
	
	public PlatkmORMEntityManager getPlatkmORMEntityManager(String persistenceUnitName) {
		return  mapFactory.get(persistenceUnitName).createEntityManager();
	}

}
