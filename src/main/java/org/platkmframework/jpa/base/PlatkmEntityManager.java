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
package org.platkmframework.jpa.base;

import java.util.List;
import org.platkmframework.database.query.common.ColumnInfoValue;
import org.platkmframework.jpa.exception.DatabaseValidationException;
import org.platkmframework.jpa.exception.PlatkmJpaException;
import org.platkmframework.persistence.filter.criteria.DeleteCriteria;
import org.platkmframework.persistence.filter.criteria.FilterCriteria;
import jakarta.persistence.Query;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public interface PlatkmEntityManager {

    /**
     * createQuery
     * @param filterCriteria filterCriteria
     * @param params params
     * @param returnClass returnClass
     * @return PlatkmQuery
     */
    PlatkmQuery createQuery(FilterCriteria filterCriteria, List<Object> params, Class<?> returnClass);

    /**
     * createQuery
     * @param deleteCriteria deleteCriteria
     * @param params params
     * @return Query
     */
    Query createQuery(DeleteCriteria deleteCriteria, List<Object> params);

    /**
     * insert
     * @param entity entity
     * @param columns columns
     * @return ColumnInfoValue
     * @throws DatabaseValidationException DatabaseValidationException
     * @throws PlatkmJpaException PlatkmJpaException
     */
    public ColumnInfoValue insert(String entity, List<ColumnInfoValue> columns) throws DatabaseValidationException, PlatkmJpaException;

    /**
     * update
     * @param entity entity
     * @param columns columns
     * @throws DatabaseValidationException DatabaseValidationException
     * @throws PlatkmJpaException PlatkmJpaException
     */
    public void update(String entity, List<ColumnInfoValue> columns) throws DatabaseValidationException, PlatkmJpaException;
}
