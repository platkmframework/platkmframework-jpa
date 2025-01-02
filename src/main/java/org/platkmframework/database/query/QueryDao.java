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
import org.platkmframework.persistence.filter.FilterResult;
import org.platkmframework.persistence.filter.criteria.DeleteCriteria;
import org.platkmframework.persistence.filter.criteria.SearchCriteria;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public interface QueryDao {

    /** 
     * 
     * select
     * @param <F> F
     * @param searchCriteria searchCriteria
     * @param params params
     * @param returnClass returnClass
     * @return List
     * @throws DaoException DaoException
     */
    public <F> List<F> select(SearchCriteria searchCriteria, List<Object> params, Class<F> returnClass) throws DaoException;

    /**
     * select
     * @param searchCriteria searchCriteria
     * @param params params
     * @return List
     * @throws DaoException DaoException
     */
    public List select(SearchCriteria searchCriteria, List<Object> params) throws DaoException;

    /** 
     * 
    * selectOne
    * @param <F> F
     * @param searchCriteria searchCriteria
     * @param params params
     * @param returnClass returnClass
     * @return F
     * @throws DaoException DaoException
     */
    public <F> F selectOne(SearchCriteria searchCriteria, List<Object> params, Class<F> returnClass) throws DaoException;

    /**
     * selectOne
     * @param searchCriteria searchCriteria
     * @param params params
     * @return Object
     * @throws DaoException DaoException
     */
    public Object selectOne(SearchCriteria searchCriteria, List<Object> params) throws DaoException;

    /**
     * remove
     * @param deleteCriteria deleteCriteria
     * @return int
     * @throws DaoException DaoException
     */
    public int remove(DeleteCriteria deleteCriteria) throws DaoException;

    /**
     * 
     * search
     * @param <F> F
     * @param searchCriteria searchCriteria
     * @param params params
     * @param returnClass returnClass
     * @return FilterResult
     * @throws DaoException DaoException
     */
 
    public <F> FilterResult<F> search(SearchCriteria searchCriteria, List<Object> params, Class<F> returnClass) throws DaoException;
}
