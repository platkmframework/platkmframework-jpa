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
package org.platkmframework.database.query;

import java.util.List;

import org.platkmframework.common.domain.filter.FilterResult;
import org.platkmframework.common.domain.filter.criteria.WhereCriteria;
import org.platkmframework.database.query.common.exception.DaoException;
import org.platkmframework.database.query.common.vo.CustomResultInfo;

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public interface QueryManagerDao {
	 
	public <F> FilterResult<F> search(String queryName, WhereCriteria filter, List<Object> parameters, Class<F> returnClass, String... replacements) throws DaoException;
	
	public FilterResult<List<Object>> search(String queryName, WhereCriteria filter, List<Object> parameters) throws DaoException;
	
	public <F> F selectOne(String queryName, WhereCriteria filter, List<Object> parameters, Class<F> returnClas) throws DaoException;
	
	public Object selectOne(String queryName, WhereCriteria filter, List<Object> parameters) throws DaoException;
	
	public <F> List<F> select(String queryName, WhereCriteria filter, List<Object> parameters, Class<F> returnClass, String... replacements) throws DaoException; 
	
	public List<Object> select(String queryName, WhereCriteria filter, List<Object> parameters, String... replacements) throws DaoException; 
	
	public void delete(String queryName, WhereCriteria filter, List<Object> parameters ) throws DaoException;
	
	public void update(String queryName, WhereCriteria filter, List<Object> parameters, String... replacements) throws DaoException; 
	
	public <F> List<F>  select(String queryName, WhereCriteria filter, List<Object>  parameters,
			CustomResultInfo<F> customResultInfo, String... replacements) throws DaoException;
	  
}
