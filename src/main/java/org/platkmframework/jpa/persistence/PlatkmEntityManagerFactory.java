/**
 * ****************************************************************************
 *  Copyright(c) 2023 the original author Eduardo Iglesias Taylor.
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

import java.util.Map;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.platkmframework.jpa.base.PlatkmORMEntityManager;
import org.platkmframework.jpa.dll.SchemaGenerator;
import org.platkmframework.jpa.exception.PlatkmJpaException;
import org.platkmframework.util.DataTypeUtil;
import jakarta.persistence.Cache;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnitUtil;
import jakarta.persistence.Query;
import jakarta.persistence.SynchronizationType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class PlatkmEntityManagerFactory implements EntityManagerFactory {

    /**
     * Atributo logger
     */
    private static Logger logger = LoggerFactory.getLogger(PlatkmEntityManagerFactory.class);

    /**
     * Atributo emPool
     */
    private GenericObjectPool<PlatkmORMEntityManager> emPool;

    /**
     * Atributo opened
     */
    private boolean opened;

    /**
     * Atributo persistenceUnit
     */
    private PersistenceUnit persistenceUnit;

    /**
     * Atributo schemaGenerator
     */
    private SchemaGenerator schemaGenerator;

    /**
     * Constructor PlatkmEntityManagerFactory
     * @param persistenceUnit persistenceUnit
     * @param poolFactory poolFactory
     * @param schemaGenerator schemaGenerator
     */
    public PlatkmEntityManagerFactory(PersistenceUnit persistenceUnit, BasePooledObjectFactory<PlatkmORMEntityManager> poolFactory, SchemaGenerator schemaGenerator) {
        super();
        this.persistenceUnit = persistenceUnit;
        this.emPool = new GenericObjectPool<>(poolFactory);
        /*
		 * this.emPool.setMaxIdle(-1);  org.platkmframework.database.querymanagers.path
		 * this.emPool.setMaxTotal(-1);
		 */
        this.emPool.setMaxIdle(DataTypeUtil.getIntegerValue(persistenceUnit.getProperties().get("org.platkmframework.platkmframework.entitymanager.maxidle"), GenericObjectPoolConfig.DEFAULT_MAX_IDLE));
        this.emPool.setMaxTotal(DataTypeUtil.getIntegerValue(persistenceUnit.getProperties().get("org.platkmframework.platkmframework.entitymanager.maxtotal"), GenericObjectPoolConfig.DEFAULT_MAX_TOTAL));
        this.emPool.setBlockWhenExhausted(false);
        opened = true;
        this.schemaGenerator = schemaGenerator;
    }

    /**
     * createEntityManager
     * @return PlatkmORMEntityManager
     */
    @Override
    public synchronized PlatkmORMEntityManager createEntityManager() {
        try {
            if (!opened)
                throw new IllegalStateException("entity manager factory has been closed");
            PlatkmORMEntityManager em = this.emPool.borrowObject();
            logger.info("borrowObjec -> " + em.toString());
            return em;
            //platkmEntityManager.getTransaction().begin();
            //threadLocal.set(platkmEntityManager);
        } catch (Exception e) {
            throw new PlatkmJpaException(e);
        }
    }

    /**
     * createEntityManager
     * @param map map
     * @return PlatkmORMEntityManager
     */
    @Override
    public PlatkmORMEntityManager createEntityManager(Map map) {
        return createEntityManager();
    }

    /**
     * createEntityManager
     * @param synchronizationType synchronizationType
     * @return PlatkmORMEntityManager
     */
    @Override
    public PlatkmORMEntityManager createEntityManager(SynchronizationType synchronizationType) {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * createEntityManager
     * @param synchronizationType synchronizationType
     * @param map map
     * @return EntityManager
     */
    @Override
    public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * getCriteriaBuilder
     * @return CriteriaBuilder
     */
    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * getMetamodel
     * @return Metamodel
     */
    @Override
    public Metamodel getMetamodel() {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * isOpen
     * @return boolean
     */
    @Override
    public boolean isOpen() {
        return opened;
    }

    /**
     * close
     */
    @Override
    public void close() {
        opened = true;
    }

    /**
     * getProperties
     * @return Map
     */
    @Override
    public Map<String, Object> getProperties() {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * getCache
     * @return Cache
     */
    @Override
    public Cache getCache() {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * getPersistenceUnitUtil
     * @return PersistenceUnitUtil
     */
    @Override
    public PersistenceUnitUtil getPersistenceUnitUtil() {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * addNamedQuery
     * @param name name
     * @param query query
     */
    @Override
    public void addNamedQuery(String name, Query query) {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * unwrap
     * @param cls cls
     * @return T
     */
    @Override
    public <T> T unwrap(Class<T> cls) {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * addNamedEntityGraph
     * @param graphName graphName
     * @param entityGraph entityGraph
     */
    @Override
    public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {
        throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
    }

    /**
     * returnObject
     * @param platkmEntityManager platkmEntityManager
     */
    public void returnObject(PlatkmORMEntityManager platkmEntityManager) {
        try {
            logger.info("returnObject -> " + platkmEntityManager.toString());
            emPool.returnObject(platkmEntityManager);
        } catch (Exception e) {
            throw new PlatkmJpaException(e);
        }
    }

    /**
     * getPersistenceUnit
     * @return PersistenceUnit
     */
    public PersistenceUnit getPersistenceUnit() {
        return persistenceUnit;
    }

    /**
     * getSchemaGenerator
     * @return SchemaGenerator
     */
    public SchemaGenerator getSchemaGenerator() {
        return schemaGenerator;
    }
}
