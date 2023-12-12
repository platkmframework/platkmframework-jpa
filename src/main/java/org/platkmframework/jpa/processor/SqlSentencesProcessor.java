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
package org.platkmframework.jpa.processor;
  
import java.util.List;

import org.platkmframework.common.domain.filter.criteria.DeleteCriteria;
import org.platkmframework.common.domain.filter.criteria.SearchCriteria;
import org.platkmframework.common.domain.filter.criteria.WhereCriteria;
import org.platkmframework.common.domain.filter.criteria.base.ConditionFilterBase;
import org.platkmframework.database.query.common.vo.CustomResultInfo;
import org.platkmframework.database.query.manager.model.QuerySelect;
import org.platkmframework.jpa.base.PlatkmEntityManager;
import org.platkmframework.jpa.exception.DatabaseValidationException; 


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public interface SqlSentencesProcessor {
	
	public ProcessResult process(PlatkmEntityManager platkmEntityManager, ConditionFilterBase filterCriteria, List<Object> param) throws DatabaseValidationException;

	public ProcessResult process(PlatkmEntityManager platkmEntityManager, QuerySelect querySelect, WhereCriteria whereCriteria, List<Object> param, String... replacements) throws DatabaseValidationException;

	public ProcessResult process(PlatkmEntityManager platkmEntityManager, String sql, SearchCriteria searchCriteria) throws DatabaseValidationException;

	public ProcessResult removeProcess(PlatkmEntityManager platkmEntityManager, DeleteCriteria deleteCriteria,List<Object> param)throws DatabaseValidationException;

	public ProcessResult process(PlatkmEntityManager  entyEntityManager, QuerySelect querySelect, WhereCriteria filter,
			List<Object> parameters, CustomResultInfo<?> customResultInfo, String[] replacements)throws DatabaseValidationException; 
	
/**	public ProcessResult processSelectOptions(PlatkmEntityManager entyEntityManager, QuerySelect querySelect,
			String tableName, String keyColumn, String textColumns, WhereCriteria filter) throws DatabaseValidationException;
*/
}
