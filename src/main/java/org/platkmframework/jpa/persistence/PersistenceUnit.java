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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.platkmframework.database.query.manager.QueryManager;
import org.platkmframework.jpa.mapping.DatabaseMapper;
import org.platkmframework.jpa.processor.SqlSentencesProcessor;
import org.platkmframework.util.DataTypeUtil;


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class PersistenceUnit{
	
	private String name;  
	private String transactionType; 
	private String provider; 
	private Map<String, Object> properties;
	private List<String> classes;
	private DatabaseMapper  databaseMapper;
	private QueryManager queryManager; 
	private SqlSentencesProcessor sqlSentencesProcessor;
	
	public PersistenceUnit() {
		super();
	}
	 
	
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	 
 
	public Map<String, Object> getProperties() {
		if (properties == null) properties = new HashMap<>();
		return properties;
	}
	
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
  
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public List<String> getClasses() {
		if (classes == null) classes = new ArrayList<>();
		return classes;
	}
	public void setClasses(List<String> classes) {
		this.classes = classes;
	}
	public DatabaseMapper  getDatabaseMapper() {
		return databaseMapper;
	}
	public void setDatabaseMapper(DatabaseMapper databaseMapper) {
		this.databaseMapper = databaseMapper;
	}

	public QueryManager getQueryManager() {
		return queryManager;
	}

	public void setQueryManager(QueryManager queryManager) {
		this.queryManager = queryManager;
	}
 
	public String getStringPropertyValue(String key) {
		return DataTypeUtil.getStringValue(properties.get(key), "");
	}


	public SqlSentencesProcessor getSqlSentencesProcessor() {
		return sqlSentencesProcessor;
	}


	public void setSqlSentencesProcessor(SqlSentencesProcessor sqlSentencesProcessor) {
		this.sqlSentencesProcessor = sqlSentencesProcessor;
	}

}
