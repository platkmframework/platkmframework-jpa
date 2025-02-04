/**
 * ****************************************************************************
 *  Copyright(c) 2025 the original author Eduardo Iglesias Taylor.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  	 https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  Contributors:
 *  	Eduardo Iglesias Taylor - initial API and implementation
 * *****************************************************************************
 */
package org.platkmframework.jpa.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.platkmframework.context.project.ProjectContent;
import org.platkmframework.jpa.base.PlatkmORMEntityManager;
import org.platkmframework.jpa.mapping.DatabaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.spi.PersistenceProvider;

/**
  *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 * @param <E> E
 */ 
public class PersistenceManager<E extends DatabaseMapper> {

    /**
     * Atributo logger
     */
    private static final Logger logger = LoggerFactory.getLogger(PersistenceManager.class);

    /**
     * Atributo persistenceManager
     */
    private static PersistenceManager<?> persistenceManager;

    /**
     * Atributo mapFactory
     */
    protected static Map<String, PlatkmEntityManagerFactory> mapFactory = new HashMap<>();

    /**
     * Atributo threadLocal
     */
    private static final ThreadLocal<Map<String, PlatkmORMEntityManager>> threadLocal = new ThreadLocal<>();

    
    /**
     * Constructor PersistenceManager
     */
    private PersistenceManager() {
        super(); 

    }

    /**
     * instance
     * @return PersistenceManager
     */
    public static PersistenceManager<?> instance() {
        if (persistenceManager == null)
            persistenceManager = new PersistenceManager<>();
        return persistenceManager;
    }

    /**
     * begin
     */
    public synchronized void begin() {
        close();
        PersistenceInfoUtil.instance().getPersistenceInfoList().forEach((info) -> {
            createPlakmEnity(info.getName());
        });
    }

    /**
     * begin
     * @param persistenceUnitName persistenceUnitName
     */
    public synchronized void begin(String persistenceUnitName) {
        close();
        createPlakmEnity(persistenceUnitName);
    }

    /**
     * createPlakmEnity
     * @param persistenceUnitName persistenceUnitName
     */
    private void createPlakmEnity(String persistenceUnitName) {
        //Persistence.generateSchema(persistenceUnit.getName(), null); @TODO
        PlatkmORMEntityManager platkmEntityManager = mapFactory.get(persistenceUnitName).createEntityManager();
        platkmEntityManager.getTransaction().begin();
        put(persistenceUnitName, platkmEntityManager);
    }

    /**
     * commit
     */
    public  void commit() {
        if (threadLocal.get() != null)
            threadLocal.get().forEach((k, v) -> v.getTransaction().commit());
    }

    /**
     * commit
     * @param persistenceUnit persistenceUnit
     */
    public void commit(String persistenceUnit) {
        if (threadLocal.get() != null)
            threadLocal.get().get(persistenceUnit).getTransaction().commit();
    }

    /**
     * rollback
     */
    public void rollback() {
        if (threadLocal.get() != null)
            threadLocal.get().forEach((k, v) -> v.getTransaction().rollback());
    }

    /**
     * rollback
     * @param persistenceUnit persistenceUnit
     */
    public void rollback(String persistenceUnit) {
        if (threadLocal.get() != null)
            threadLocal.get().get(persistenceUnit).getTransaction().rollback();
    }

    /**
     * close
     */
    public void close() {
        if (threadLocal.get() != null)
            threadLocal.get().forEach((k, v) -> {
                try {
                    if (v.getTransaction().isActive()) {
                        v.close();
                    }
                } catch (Exception e) {
                    v.getTransaction().rollback();
                    v.close();
                    logger.error( "DATABASE commit ERROR {} ",  e.getMessage());
                } finally {
                    try {
                        mapFactory.get(k).returnObject(v);
                    } catch (Exception e) {
                        logger.error("DATABASE returnObject ERROR {}  ",  e.getMessage() );
                    } finally {
                        threadLocal.remove();
                    }
                }
            });
    }

    /**
     * close
     * @param persistenceUnit persistenceUnit
     */
    public  void close(String persistenceUnit) {
        if (threadLocal.get() != null && threadLocal.get().get(persistenceUnit) != null) {
            try {
                if (threadLocal.get().get(persistenceUnit).getTransaction().isActive()) {
                    threadLocal.get().get(persistenceUnit).getTransaction().commit();
                }
            } catch (Exception e) {
                threadLocal.get().get(persistenceUnit).getTransaction().rollback();
                 logger.error("Database ERROR, commit process {} ", e.getMessage());
            } finally {
                try {
                    threadLocal.get().get(persistenceUnit).close();
                    mapFactory.get(persistenceUnit).returnObject(threadLocal.get().get(persistenceUnit));
                } catch (Exception e) {
                    logger.error("Database ERROR, close connection or returning persistence {} ", e.getMessage());
                } finally {
                    threadLocal.remove();
                }
            }
        }
    }

    /**
     * put
     * @param name name
     * @param platkmEntityManager platkmEntityManager
     */
    private void put(String name, PlatkmORMEntityManager platkmEntityManager) {
        if (threadLocal.get() == null) {
            threadLocal.set(new HashMap<>());
        }
        threadLocal.get().put(name, platkmEntityManager);
        logger.info("connection put to threadLocal {} - {}", ProjectContent.instance().getProjectName(),  name);
    }

    /**
     * get
     * @param name name
     * @return PlatkmORMEntityManager
     */
    public PlatkmORMEntityManager get(String name) {
        return threadLocal.get().get(name);
    }

    /**
     * init
     */
    public void init(PersistenceProvider persistenceProvider) {
    	PersistenceInfoDiscover persistenceInfoDiscover = new PersistenceInfoDiscover();
        List<PersistenceInfo> list = persistenceInfoDiscover.search();
        if (list != null) {
            PersistenceInfoUtil.instance().setPersistenceInfoList(list);
            PersistenceInfoUtil.instance().setLoaded(true); 
            for (PersistenceInfo persistenceInfo : list) {
                PlatkmEntityManagerFactory plakmEntityManagerFactory = (PlatkmEntityManagerFactory) persistenceProvider.createEntityManagerFactory(persistenceInfo.getName(), null);
                mapFactory.put(persistenceInfo.getName(), plakmEntityManagerFactory);
                logger.info("Persistence :{}", persistenceInfo.getName());
            }
        } else {
            logger.warn("No se encontró información de pesistencia");
            PersistenceInfoUtil.instance().setNotFound(true);
        }
    }

    /**
     * getPlatkmORMEntityManager
     * @param persistenceUnitName persistenceUnitName
     * @return PlatkmORMEntityManager
     */
    public PlatkmORMEntityManager getPlatkmORMEntityManager(String persistenceUnitName) {
        return mapFactory.get(persistenceUnitName).createEntityManager();
    }
}
