package org.platkmframework.jpa.persistence;

import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.platkmframework.jpa.base.PlatkmEntityManager;
import org.platkmframework.jpa.dll.SchemaGenerator;
import org.platkmframework.jpa.exception.PlatkmJpaException; 

public class PlatkmEntityManagerFactory  implements EntityManagerFactory{

	private ObjectPool<PlatkmEntityManager> emPool;
	private boolean opened; 
	private PersistenceUnit  persistenceUnit;
	private SchemaGenerator schemaGenerator;
	
	public PlatkmEntityManagerFactory(PersistenceUnit  persistenceUnit, 
									BasePooledObjectFactory<PlatkmEntityManager> poolFactory,
									SchemaGenerator schemaGenerator) {
		super();  
		this.persistenceUnit = persistenceUnit;
		this.emPool = new GenericObjectPool<>(poolFactory); 
		opened = true;
		this.schemaGenerator = schemaGenerator;
	}

	@Override
	public synchronized PlatkmEntityManager createEntityManager() {  
		try {
			if(!opened) throw new IllegalStateException("entity manager factory has been closed");
			  
			return this.emPool.borrowObject();
			//platkmEntityManager.getTransaction().begin();
			//threadLocal.set(platkmEntityManager);
			  
		} catch (Exception e) {
			throw new PlatkmJpaException(e);
		}  
	}

	@Override
	public PlatkmEntityManager createEntityManager(Map map) { 
		return  createEntityManager();
	}

	@Override
	public PlatkmEntityManager createEntityManager(SynchronizationType synchronizationType) {
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

	public void returnObject(PlatkmEntityManager platkmEntityManager) {
		try {
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
