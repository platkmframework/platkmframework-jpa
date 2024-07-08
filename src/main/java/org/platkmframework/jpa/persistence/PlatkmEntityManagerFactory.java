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

public class PlatkmEntityManagerFactory  implements EntityManagerFactory{
	
	private static Logger logger = LoggerFactory.getLogger(PlatkmEntityManagerFactory.class);

	private GenericObjectPool<PlatkmORMEntityManager> emPool;
	private boolean opened; 
	private PersistenceUnit  persistenceUnit;
	private SchemaGenerator schemaGenerator;
	
	public PlatkmEntityManagerFactory(PersistenceUnit  persistenceUnit, 
									BasePooledObjectFactory<PlatkmORMEntityManager> poolFactory,
									SchemaGenerator schemaGenerator) {
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

	@Override
	public synchronized PlatkmORMEntityManager createEntityManager() {  
		try {
			if(!opened) throw new IllegalStateException("entity manager factory has been closed");
			  
			PlatkmORMEntityManager em = this.emPool.borrowObject();
			logger.info("borrowObjec -> " + em.toString());
			return em;
			//platkmEntityManager.getTransaction().begin();
			//threadLocal.set(platkmEntityManager);
			  
		} catch (Exception e) {
			throw new PlatkmJpaException(e);
		}  
	}

	@Override
	public PlatkmORMEntityManager createEntityManager(Map map) { 
		return  createEntityManager();
	}

	@Override
	public PlatkmORMEntityManager createEntityManager(SynchronizationType synchronizationType) {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
	}

	@Override
	public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
	}

	@Override
	public Metamodel getMetamodel() {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
	}

	@Override
	public boolean isOpen() { 
		return opened;
	}

	@Override
	public void close() {
		opened = true; 
	}

	@Override
	public Map<String, Object> getProperties() {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
	}

	@Override
	public Cache getCache() {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
	}

	@Override
	public PersistenceUnitUtil getPersistenceUnitUtil() {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
	}

	@Override
	public void addNamedQuery(String name, Query query) {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
		
	}

	@Override
	public <T> T unwrap(Class<T> cls) {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)");
	}

	@Override
	public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {
		throw new NotImplementedException("generateSchema(PersistenceUnitInfo info, Map map)"); 
	}

	public void returnObject(PlatkmORMEntityManager platkmEntityManager) {
		try {
			logger.info("returnObject -> " + platkmEntityManager.toString());
			emPool.returnObject(platkmEntityManager);
		} catch (Exception e) {
			throw new PlatkmJpaException(e);
		}
	}

	public PersistenceUnit getPersistenceUnit() {
		return persistenceUnit;
	}

	public SchemaGenerator getSchemaGenerator() {
		return schemaGenerator;
	}
	
}
