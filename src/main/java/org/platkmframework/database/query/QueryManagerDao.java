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
package org.platkmframework.database.query;

import java.util.List;
import org.platkmframework.database.query.common.exception.DaoException;
import org.platkmframework.database.query.common.vo.CustomResultInfo;
import org.platkmframework.persistence.filter.FilterResult;
import org.platkmframework.persistence.filter.criteria.WhereCriteria;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public interface QueryManagerDao {

    /** 
     * 
     * search
     * @param <F> F
     * @param queryName queryName
     * @param filter filter
     * @param parameters parameters
     * @param returnClass returnClass
     * @param replacements replacements
     * @return FilterResult
     * @throws DaoException DaoException
     */
    public <F> FilterResult<F> search(String queryName, WhereCriteria filter, List<Object> parameters, Class<F> returnClass, String... replacements) throws DaoException;

    /**
     * search
     * @param queryName queryName
     * @param filter filter
     * @param parameters parameters
     * @return FilterResult
     * @throws DaoException DaoException
     */
    public FilterResult<List<Object>> search(String queryName, WhereCriteria filter, List<Object> parameters) throws DaoException;

    /** 
     * 
     * selectOne
     * @param <F> F
     * @param queryName queryName
     * @param filter filter
     * @param parameters parameters
     * @param returnClas returnClas
     * @return F
     * @throws DaoException DaoException
     */
    public <F> F selectOne(String queryName, WhereCriteria filter, List<Object> parameters, Class<F> returnClas) throws DaoException;

    /**
     * selectOne
     * @param queryName queryName
     * @param filter filter
     * @param parameters parameters
     * @return Object
     * @throws DaoException DaoException
     */
    public Object selectOne(String queryName, WhereCriteria filter, List<Object> parameters) throws DaoException;

    /** 
     * 
     * select
     * @param <F> F
     * @param queryName queryName
     * @param filter filter
     * @param parameters parameters
     * @param returnClass returnClass
     * @param replacements replacements
     * @return List
     * @throws DaoException DaoException
     */
    public <F> List<F> select(String queryName, WhereCriteria filter, List<Object> parameters, Class<F> returnClass, String... replacements) throws DaoException;

    /**
     * select
     * @param queryName queryName
     * @param filter filter
     * @param parameters parameters
     * @param replacements replacements
     * @return List
     * @throws DaoException DaoException
     */
    public List<Object> select(String queryName, WhereCriteria filter, List<Object> parameters, String... replacements) throws DaoException;

    /**
     * delete
     * @param queryName queryName
     * @param filter filter
     * @param parameters parameters
     * @throws DaoException DaoException
     */
    public void delete(String queryName, WhereCriteria filter, List<Object> parameters) throws DaoException;

    /**
     * update
     * @param queryName queryName
     * @param filter filter
     * @param parameters parameters
     * @param replacements replacements
     * @throws DaoException DaoException
     */
    public void update(String queryName, WhereCriteria filter, List<Object> parameters, String... replacements) throws DaoException;

    /** 
     * 
     * select
     * @param <F> F
     * @param queryName queryName
     * @param filter filter
     * @param parameters parameters
     * @param customResultInfo customResultInfo
     * @param replacements replacements
     * @return List
     * @throws DaoException DaoException
     */
    public <F> List<F> select(String queryName, WhereCriteria filter, List<Object> parameters, CustomResultInfo<F> customResultInfo, String... replacements) throws DaoException;
}
