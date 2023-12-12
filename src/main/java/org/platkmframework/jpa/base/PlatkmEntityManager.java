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
package org.platkmframework.jpa.base;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.platkmframework.common.domain.filter.criteria.DeleteCriteria;
import org.platkmframework.common.domain.filter.criteria.FilterCriteria;
import org.platkmframework.database.query.QueryDao;
import org.platkmframework.database.query.QueryManagerDao;
import org.platkmframework.database.query.manager.QueryManager;
import org.platkmframework.databasereader.model.Column;
import org.platkmframework.databasereader.model.Table;
import org.platkmframework.jpa.mapping.DatabaseMapper; 


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public interface PlatkmEntityManager extends EntityManager, PlatkmDb{
      
	public QueryDao getQueryDao();
	
	public QueryManagerDao getQueryManagerDao();
	
	PlatkmQuery createQuery(FilterCriteria filterCriteria, List<Object> params, Class<?> returnClass);
	
	Query createQuery(DeleteCriteria deleteCriteria, List<Object> params);
	
	public QueryManager getQueryManager();
	
	public DatabaseMapper getDatabaseMapper();
	
	List<Table> getMetadata();
	
	List<Column> getTableColumnMetaData(String tablename);
	
	List<String> getTablePksContraints(String tableName); 
	
}
