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

import java.util.List;
import java.util.Map;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import org.platkmframework.database.query.QueryDao;
import org.platkmframework.database.query.QueryManagerDao;
import org.platkmframework.database.query.common.ColumnInfoValue;
import org.platkmframework.database.query.manager.QueryManager;
import org.platkmframework.databasereader.model.Column;
import org.platkmframework.databasereader.model.Table;
import org.platkmframework.jpa.base.PlatkmORMEntityManager;
import org.platkmframework.jpa.base.PlatkmQuery;
import org.platkmframework.jpa.exception.DatabaseValidationException;
import org.platkmframework.jpa.mapping.DatabaseMapper;
import org.platkmframework.persistence.filter.criteria.DeleteCriteria;
import org.platkmframework.persistence.filter.criteria.FilterCriteria;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class PlatkmEntityManagerProxy implements PlatkmORMEntityManager {

    /**
     * Atributo persistenceUnitName
     */
    private String persistenceUnitName;

    /**
     * Constructor PlatkmEntityManagerProxy
     * @param persistenceUnitName persistenceUnitName
     */
    public PlatkmEntityManagerProxy(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    /**
     * persist
     * @param entity entity
     */
    @Override
    public void persist(Object entity) {
        PersistenceManager.instance().get(persistenceUnitName).persist(entity);
    }

    /**
     * merge
     * @param entity entity
     * @return T
     */
    @Override
    public <T> T merge(T entity) {
        return PersistenceManager.instance().get(persistenceUnitName).merge(entity);
    }

    /**
     * remove
     * @param entity entity
     */
    @Override
    public void remove(Object entity) {
        PersistenceManager.instance().get(persistenceUnitName).remove(entity);
    }

    /**
     * find
     * @param entityClass entityClass
     * @param primaryKey primaryKey
     * @return T
     */
    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return PersistenceManager.instance().get(persistenceUnitName).find(entityClass, primaryKey);
    }

    /**
     * find
     * @param entityClass entityClass
     * @param primaryKey primaryKey
     * @param properties properties
     * @return T
     */
    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
        return PersistenceManager.instance().get(persistenceUnitName).find(entityClass, primaryKey);
    }

    /**
     * find
     * @param entityClass entityClass
     * @param primaryKey primaryKey
     * @param lockMode lockMode
     * @return T
     */
    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
        return PersistenceManager.instance().get(persistenceUnitName).find(entityClass, primaryKey);
    }

    /**
     * find
     * @param entityClass entityClass
     * @param primaryKey primaryKey
     * @param lockMode lockMode
     * @param properties properties
     * @return T
     */
    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
        return PersistenceManager.instance().get(persistenceUnitName).find(entityClass, primaryKey);
    }

    /**
     * getReference
     * @param entityClass entityClass
     * @param primaryKey primaryKey
     * @return T
     */
    @Override
    public <T> T getReference(Class<T> entityClass, Object primaryKey) {
        return PersistenceManager.instance().get(persistenceUnitName).getReference(entityClass, primaryKey);
    }

    /**
     * flush
     */
    @Override
    public void flush() {
        PersistenceManager.instance().get(persistenceUnitName).flush();
    }

    /**
     * setFlushMode
     * @param flushMode flushMode
     */
    @Override
    public void setFlushMode(FlushModeType flushMode) {
        PersistenceManager.instance().get(persistenceUnitName).setFlushMode(flushMode);
    }

    /**
     * getFlushMode
     * @return FlushModeType
     */
    @Override
    public FlushModeType getFlushMode() {
        return PersistenceManager.instance().get(persistenceUnitName).getFlushMode();
    }

    /**
     * lock
     * @param entity entity
     * @param lockMode lockMode
     */
    @Override
    public void lock(Object entity, LockModeType lockMode) {
        PersistenceManager.instance().get(persistenceUnitName).lock(entity, lockMode);
    }

    /**
     * lock
     * @param entity entity
     * @param lockMode lockMode
     * @param properties properties
     */
    @Override
    public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        PersistenceManager.instance().get(persistenceUnitName).lock(entity, lockMode);
    }

    /**
     * refresh
     * @param entity entity
     */
    @Override
    public void refresh(Object entity) {
        PersistenceManager.instance().get(persistenceUnitName).refresh(entity);
    }

    /**
     * refresh
     * @param entity entity
     * @param properties properties
     */
    @Override
    public void refresh(Object entity, Map<String, Object> properties) {
        PersistenceManager.instance().get(persistenceUnitName).refresh(entity, properties);
    }

    /**
     * refresh
     * @param entity entity
     * @param lockMode lockMode
     */
    @Override
    public void refresh(Object entity, LockModeType lockMode) {
        PersistenceManager.instance().get(persistenceUnitName).refresh(entity, lockMode);
    }

    /**
     * refresh
     * @param entity entity
     * @param lockMode lockMode
     * @param properties properties
     */
    @Override
    public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        PersistenceManager.instance().get(persistenceUnitName).refresh(entity, lockMode, properties);
    }

    /**
     * clear
     */
    @Override
    public void clear() {
        PersistenceManager.instance().get(persistenceUnitName).clear();
    }

    /**
     * detach
     * @param entity entity
     */
    @Override
    public void detach(Object entity) {
        PersistenceManager.instance().get(persistenceUnitName).detach(entity);
    }

    /**
     * contains
     * @param entity entity
     * @return boolean
     */
    @Override
    public boolean contains(Object entity) {
        return PersistenceManager.instance().get(persistenceUnitName).contains(entity);
    }

    /**
     * getLockMode
     * @param entity entity
     * @return LockModeType
     */
    @Override
    public LockModeType getLockMode(Object entity) {
        return PersistenceManager.instance().get(persistenceUnitName).getLockMode(entity);
    }

    /**
     * setProperty
     * @param propertyName propertyName
     * @param value value
     */
    @Override
    public void setProperty(String propertyName, Object value) {
        PersistenceManager.instance().get(persistenceUnitName).setProperty(propertyName, value);
    }

    /**
     * getProperties
     * @return Map
     */
    @Override
    public Map<String, Object> getProperties() {
        return PersistenceManager.instance().get(persistenceUnitName).getProperties();
    }

    /**
     * createQuery
     * @param sql sql
     * @return Query
     */
    @Override
    public Query createQuery(String sql) {
        return PersistenceManager.instance().get(persistenceUnitName).createQuery(sql);
    }

    /**
     * createQuery
     * @param criteriaQuery criteriaQuery
     * @return TypedQuery
     */
    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return PersistenceManager.instance().get(persistenceUnitName).createQuery(criteriaQuery);
    }

    /**
     * createQuery
     * @param updateQuery updateQuery
     * @return Query
     */
    @Override
    public Query createQuery(CriteriaUpdate updateQuery) {
        return PersistenceManager.instance().get(persistenceUnitName).createQuery(updateQuery);
    }

    /**
     * createQuery
     * @param deleteQuery deleteQuery
     * @return Query
     */
    @Override
    public Query createQuery(CriteriaDelete deleteQuery) {
        return PersistenceManager.instance().get(persistenceUnitName).createQuery(deleteQuery);
    }

    /**
     * createQuery
     * @param filterCriteria filterCriteria
     * @param params params
     * @param returnClass returnClass
     * @return PlatkmQuery
     */
    @Override
    public PlatkmQuery createQuery(FilterCriteria filterCriteria, List<Object> params, Class<?> returnClass) {
        return PersistenceManager.instance().get(persistenceUnitName).createQuery(filterCriteria, params, returnClass);
    }

    /**
     * createQuery
     * @param deleteCriteria deleteCriteria
     * @param params params
     * @return Query
     */
    @Override
    public Query createQuery(DeleteCriteria deleteCriteria, List<Object> params) {
        return PersistenceManager.instance().get(persistenceUnitName).createQuery(deleteCriteria, params);
    }

    /**
     * createQuery
     * @param sql sql
     * @param resultClass resultClass
     * @return TypedQuery
     */
    @Override
    public <T> TypedQuery<T> createQuery(String sql, Class<T> resultClass) {
        return PersistenceManager.instance().get(persistenceUnitName).createQuery(sql, resultClass);
    }

    /**
     * createNamedQuery
     * @param name name
     * @return Query
     */
    @Override
    public Query createNamedQuery(String name) {
        return PersistenceManager.instance().get(persistenceUnitName).createNamedQuery(name);
    }

    /**
     * createNamedQuery
     * @param name name
     * @param resultClass resultClass
     * @return TypedQuery
     */
    @Override
    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
        return PersistenceManager.instance().get(persistenceUnitName).createNamedQuery(name, resultClass);
    }

    /**
     * createNativeQuery
     * @param sql sql
     * @return Query
     */
    @Override
    public Query createNativeQuery(String sql) {
        return PersistenceManager.instance().get(persistenceUnitName).createNativeQuery(sql);
    }

    /**
     * createNativeQuery
     * @param sql sql
     * @param resultClass resultClass
     * @return Query
     */
    @Override
    public Query createNativeQuery(String sql, Class resultClass) {
        return PersistenceManager.instance().get(persistenceUnitName).createNativeQuery(sql, resultClass);
    }

    /**
     * createNativeQuery
     * @param sql sql
     * @param resultSetMapping resultSetMapping
     * @return Query
     */
    @Override
    public Query createNativeQuery(String sql, String resultSetMapping) {
        return PersistenceManager.instance().get(persistenceUnitName).createNativeQuery(sql);
    }

    /**
     * createNamedStoredProcedureQuery
     * @param name name
     * @return StoredProcedureQuery
     */
    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
        return PersistenceManager.instance().get(persistenceUnitName).createNamedStoredProcedureQuery(name);
    }

    /**
     * createStoredProcedureQuery
     * @param procedureName procedureName
     * @return StoredProcedureQuery
     */
    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
        return PersistenceManager.instance().get(persistenceUnitName).createStoredProcedureQuery(procedureName);
    }

    /**
     * createStoredProcedureQuery
     * @param procedureName procedureName
     * @param resultClasses resultClasses
     * @return StoredProcedureQuery
     */
    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
        return PersistenceManager.instance().get(persistenceUnitName).createStoredProcedureQuery(procedureName, resultClasses);
    }

    /**
     * createStoredProcedureQuery
     * @param procedureName procedureName
     * @param resultSetMappings resultSetMappings
     * @return StoredProcedureQuery
     */
    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
        return PersistenceManager.instance().get(persistenceUnitName).createStoredProcedureQuery(procedureName, resultSetMappings);
    }

    /**
     * joinTransaction
     */
    @Override
    public void joinTransaction() {
        PersistenceManager.instance().get(persistenceUnitName).joinTransaction();
    }

    /**
     * isJoinedToTransaction
     * @return boolean
     */
    @Override
    public boolean isJoinedToTransaction() {
        return PersistenceManager.instance().get(persistenceUnitName).isJoinedToTransaction();
    }

    /**
     * unwrap
     * @param cls cls
     * @return T
     */
    @Override
    public <T> T unwrap(Class<T> cls) {
        return PersistenceManager.instance().get(persistenceUnitName).unwrap(cls);
    }

    /**
     * getDelegate
     * @return Object
     */
    @Override
    public Object getDelegate() {
        return PersistenceManager.instance().get(persistenceUnitName).getDelegate();
    }

    /**
     * close
     */
    @Override
    public void close() {
        PersistenceManager.instance().get(persistenceUnitName).close();
    }

    /**
     * isOpen
     * @return boolean
     */
    @Override
    public boolean isOpen() {
        return PersistenceManager.instance().get(persistenceUnitName).isOpen();
    }

    /**
     * getTransaction
     * @return EntityTransaction
     */
    @Override
    public EntityTransaction getTransaction() {
        return PersistenceManager.instance().get(persistenceUnitName).getTransaction();
    }

    /**
     * getEntityManagerFactory
     * @return EntityManagerFactory
     */
    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return PersistenceManager.instance().get(persistenceUnitName).getEntityManagerFactory();
    }

    /**
     * getCriteriaBuilder
     * @return CriteriaBuilder
     */
    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return PersistenceManager.instance().get(persistenceUnitName).getCriteriaBuilder();
    }

    /**
     * getMetamodel
     * @return Metamodel
     */
    @Override
    public Metamodel getMetamodel() {
        return PersistenceManager.instance().get(persistenceUnitName).getMetamodel();
    }

    /**
     * createEntityGraph
     * @param rootType rootType
     * @return EntityGraph
     */
    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
        return PersistenceManager.instance().get(persistenceUnitName).createEntityGraph(rootType);
    }

    /**
     * createEntityGraph
     * @param graphName graphName
     * @return EntityGraph
     */
    @Override
    public EntityGraph<?> createEntityGraph(String graphName) {
        return PersistenceManager.instance().get(persistenceUnitName).createEntityGraph(graphName);
    }

    /**
     * getEntityGraph
     * @param graphName graphName
     * @return EntityGraph
     */
    @Override
    public EntityGraph<?> getEntityGraph(String graphName) {
        return PersistenceManager.instance().get(persistenceUnitName).getEntityGraph(graphName);
    }

    /**
     * getEntityGraphs
     * @param entityClass entityClass
     * @return List
     */
    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
        return PersistenceManager.instance().get(persistenceUnitName).getEntityGraphs(entityClass);
    }

    /**
     * getQueryDao
     * @return QueryDao
     */
    public QueryDao getQueryDao() {
        return PersistenceManager.instance().get(persistenceUnitName).getQueryDao();
    }

    /**
     * restart
     */
    public void restart() {
        PersistenceManager.instance().get(persistenceUnitName).setFlushMode(null);
    }

    /**
     * getQueryManagerDao
     * @return QueryManagerDao
     */
    @Override
    public QueryManagerDao getQueryManagerDao() {
        return PersistenceManager.instance().get(persistenceUnitName).getQueryManagerDao();
    }

    /**
     * getQueryManager
     * @return QueryManager
     */
    @Override
    public QueryManager getQueryManager() {
        return PersistenceManager.instance().get(persistenceUnitName).getQueryManager();
    }

    /**
     * getDatabaseMapper
     * @return DatabaseMapper
     */
    @Override
    public DatabaseMapper getDatabaseMapper() {
        return (DatabaseMapper) PersistenceManager.instance().get(persistenceUnitName).getDatabaseMapper();
    }

    /**
     * insert
     * @param tableName tableName
     * @param columns columns
     * @return ColumnInfoValue
     * @throws DatabaseValidationException DatabaseValidationException
     */
    @Override
    public ColumnInfoValue insert(String tableName, List<ColumnInfoValue> columns) throws DatabaseValidationException {
        return PersistenceManager.instance().get(persistenceUnitName).insert(tableName, columns);
    }

    /**
     * update
     * @param tablename tablename
     * @param columns columns
     * @throws DatabaseValidationException DatabaseValidationException
     */
    @Override
    public void update(String tablename, List<ColumnInfoValue> columns) throws DatabaseValidationException {
        PersistenceManager.instance().get(persistenceUnitName).update(tablename, columns);
    }

    /**
     * getMetadata
     * @param catalog catalog
     * @param schema schema
     * @return List
     */
    @Override
    public List<Table> getMetadata(String catalog, String schema) {
        return PersistenceManager.instance().get(persistenceUnitName).getMetadata(catalog, schema);
    }

    /**
     * getTablePksContraints
     * @param catalog catalog
     * @param schema schema
     * @param tableName tableName
     * @return List
     */
    @Override
    public List<String> getTablePksContraints(String catalog, String schema, String tableName) {
        return PersistenceManager.instance().get(persistenceUnitName).getTablePksContraints(catalog, schema, tableName);
    }

    /**
     * getTableColumnMetaData
     * @param catalog catalog
     * @param schema schema
     * @param tablename tablename
     * @return List
     */
    @Override
    public List<Column> getTableColumnMetaData(String catalog, String schema, String tablename) {
        return PersistenceManager.instance().get(persistenceUnitName).getTableColumnMetaData(catalog, schema, tablename);
    }
}
