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

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.ProviderUtil;
 
/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public abstract class PlatkmPersistenceProvider implements PersistenceProvider{
	
	private PlakmProviderUtil plakmProviderUtil;
	
	protected static Map<String, PlatkmEntityManagerFactory > mapFactory = new HashMap<>(); 
	protected boolean loaded;
	
	public PlatkmPersistenceProvider() {
		super(); 
		plakmProviderUtil = new PlakmProviderUtil();  
	}

	@Override
	public EntityManagerFactory createEntityManagerFactory(String persistenceUnitName, Map map) { 
		return getEntityManagerFactory(persistenceUnitName, map);
	}

	@Override
	public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map) {
		return getEntityManagerFactory(info.getPersistenceUnitName(), map);
	}

	@Override
	public void generateSchema(PersistenceUnitInfo info, Map map) {
		generateSchema(info.getPersistenceUnitName(), map);
	}

	@Override
	public boolean generateSchema(String persistenceUnitName, Map map) { 
		return getEntityManagerFactory(persistenceUnitName, map).getSchemaGenerator().generateSchema( map); 
	}

	public PlatkmEntityManagerFactory getEntityManagerFactory(String persistenceUnitName, Map map) 
	{  
		
		if(!loaded){
			createPlakmEntityManagerFactory(persistenceUnitName,map);
			loaded = true;
		}
		return mapFactory.get(persistenceUnitName);  
	}
	
	protected abstract void createPlakmEntityManagerFactory(String persistenceUnitName, Map map);
	
	@Override
	public ProviderUtil getProviderUtil() {
		return plakmProviderUtil;
	}
 
}
