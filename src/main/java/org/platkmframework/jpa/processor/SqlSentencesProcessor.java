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
package org.platkmframework.jpa.processor;

import java.util.List;
import org.platkmframework.database.query.common.vo.CustomResultInfo;
import org.platkmframework.database.query.manager.model.QuerySelect;
import org.platkmframework.jpa.base.PlatkmORMEntityManager;
import org.platkmframework.jpa.exception.DatabaseValidationException;
import org.platkmframework.persistence.filter.criteria.DeleteCriteria;
import org.platkmframework.persistence.filter.criteria.SearchCriteria;
import org.platkmframework.persistence.filter.criteria.WhereCriteria;
import org.platkmframework.persistence.filter.criteria.base.ConditionFilterBase;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public interface SqlSentencesProcessor {

    /**
     * process
     * @param platkmEntityManager platkmEntityManager
     * @param filterCriteria filterCriteria
     * @param param param
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    public ProcessResult process(PlatkmORMEntityManager platkmEntityManager, ConditionFilterBase filterCriteria, List<Object> param) throws DatabaseValidationException;

    /**
     * process
     * @param PlatkmORMEntityManager PlatkmORMEntityManager
     * @param querySelect querySelect
     * @param whereCriteria whereCriteria
     * @param param param
     * @param replacements replacements
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    public ProcessResult process(PlatkmORMEntityManager PlatkmORMEntityManager, QuerySelect querySelect, WhereCriteria whereCriteria, List<Object> param, String... replacements) throws DatabaseValidationException;

    /**
     * process
     * @param PlatkmORMEntityManager PlatkmORMEntityManager
     * @param sql sql
     * @param searchCriteria searchCriteria
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    public ProcessResult process(PlatkmORMEntityManager PlatkmORMEntityManager, String sql, SearchCriteria searchCriteria) throws DatabaseValidationException;

    /**
     * removeProcess
     * @param PlatkmORMEntityManager PlatkmORMEntityManager
     * @param deleteCriteria deleteCriteria
     * @param param param
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    public ProcessResult removeProcess(PlatkmORMEntityManager PlatkmORMEntityManager, DeleteCriteria deleteCriteria, List<Object> param) throws DatabaseValidationException;

    /**
     * process
     * @param entyEntityManager entyEntityManager
     * @param querySelect querySelect
     * @param filter filter
     * @param parameters parameters
     * @param customResultInfo customResultInfo
     * @param replacements replacements
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    public ProcessResult process(PlatkmORMEntityManager entyEntityManager, QuerySelect querySelect, WhereCriteria filter, List<Object> parameters, CustomResultInfo<?> customResultInfo, String[] replacements) throws DatabaseValidationException;
    /**
     * 	public ProcessResult processSelectOptions(PlatkmORMEntityManager entyEntityManager, QuerySelect querySelect,
     * 			String tableName, String keyColumn, String textColumns, WhereCriteria filter) throws DatabaseValidationException;
     */
}
