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
import java.util.Map;

import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.platkmframework.content.project.ProjectContent;
import org.platkmframework.jpa.base.PlatkmEntityManager;
import org.platkmframework.jpa.exception.PlatkmJpaException;
import org.platkmframework.jpa.mapping.DatabaseMapper;
import org.platkmframework.jpa.persistence.reader.PlatkmPersistenceFileParse; 


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class PersistenceManager<E extends DatabaseMapper> {
	
	private static final Logger logger = LogManager.getLogger(PersistenceManager.class);
	
	private static PersistenceManager<?> persistenceManager;
	
	ThreadLocal<Map<String, PlatkmEntityManager>> threadLocal;   
	 
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
		
		PlatkmPersistenceFileParse.persistenceInfoList.forEach(
			(info)->{
					createPlakmEnity(info.getName()); 
			});
	}
	
	public synchronized void begin(String persistenceUnitName) {
		close();  
		createPlakmEnity(persistenceUnitName);  
	}
	
	private void createPlakmEnity(String persistenceUnitName) {
		PlatkmEntityManagerFactory plakmEntityManagerFactory = (PlatkmEntityManagerFactory) Persistence.createEntityManagerFactory(persistenceUnitName);
		
		//Persistence.generateSchema(persistenceUnit.getName(), null); @TODO
			
		PlatkmEntityManager platkmEntityManager = plakmEntityManagerFactory.createEntityManager();
		platkmEntityManager.getTransaction().begin();
		put(persistenceUnitName, platkmEntityManager);
		
		//logger.info("connecton opened " + ProjectContent.instance().getProjectName() + " - " + persistenceUnit.getName());
		
	}

	public synchronized void commit() { 
		if(threadLocal.get() != null)
		threadLocal.get().forEach((k, v) -> v.getTransaction().commit());  
	}

	public synchronized void rollback() {
		if(threadLocal.get() != null)
			threadLocal.get().forEach((k, v) -> v.getTransaction().rollback());
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
					threadLocal.remove();
					v.close();
					try {
						((PlatkmEntityManagerFactory) Persistence.createEntityManagerFactory(k)).returnObject(v);
					} catch (Exception e) {
						new PlatkmJpaException(e);
					}finally {
						threadLocal.remove();
					}
				} 
			}); 
	}

	private void put(String name, PlatkmEntityManager platkmEntityManager) {
		if(threadLocal.get() == null) {
			threadLocal.set(new HashMap<>());
		}
		threadLocal.get().put(name, platkmEntityManager); 
		logger.info("connecton put to threadLocal " + ProjectContent.instance().getProjectName() + " - " + name);
	} 
	
	public synchronized PlatkmEntityManager get(String name) {
		return threadLocal.get().get(name);
	}

}
