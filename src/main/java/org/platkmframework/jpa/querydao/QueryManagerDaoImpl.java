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
package org.platkmframework.jpa.querydao;
       
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.platkmframework.common.domain.filter.FilterResult;
import org.platkmframework.common.domain.filter.criteria.WhereCriteria;
import org.platkmframework.database.query.QueryManagerDao;
import org.platkmframework.database.query.common.exception.DaoException;
import org.platkmframework.database.query.common.vo.CustomResultInfo;
import org.platkmframework.database.query.manager.QueryManager;
import org.platkmframework.database.query.manager.model.QuerySelect;
import org.platkmframework.jpa.base.PlatkmEntityManager;
import org.platkmframework.jpa.exception.DatabaseValidationException;
import org.platkmframework.jpa.mapping.DatabaseMapper;
import org.platkmframework.jpa.processor.ProcessResult;
import org.platkmframework.jpa.processor.SqlSentencesProcessor;

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class QueryManagerDaoImpl implements QueryManagerDao{
	
	private static final Logger logger = LogManager.getLogger(QueryManagerDaoImpl.class);
 
	protected SqlSentencesProcessor sqlSentencesProcessor;
	
	protected PlatkmEntityManager entyEntityManager;
	
	protected QueryManager queryManager;

	public QueryManagerDaoImpl(PlatkmEntityManager entyEntityManager, SqlSentencesProcessor sqlSentencesProcessor, QueryManager queryManager) {
		super();
		this.entyEntityManager = entyEntityManager;
		this.sqlSentencesProcessor = sqlSentencesProcessor;
		this.queryManager = queryManager;
		
	}
	
	public FilterResult<List<Object>> search(String queryName, WhereCriteria filter, List<Object> parameters) throws DaoException{
		
		try {
			QuerySelect querySelect = queryManager.getSelectQuery(queryName);
			if(querySelect == null) {
				logger.error(" query not found - >" + queryName);
				throw new DaoException(" please, consult admin" );
			}
			
			if(!QueryManager.C_QUERY_TYPE_SELECT.equalsIgnoreCase(querySelect.getType())) {
				logger.error(" it is not select query - >" + queryName);
				throw new DaoException("please, consult admin");
			}
	 
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, querySelect, filter, parameters);
			FilterResult<List<Object>> filterResult = new FilterResult<>();
			filterResult.setPage(processResult.getPage());
			filterResult.setPageCount(processResult.getPageCount());
			Query query = entyEntityManager.createNativeQuery(processResult.getSql());
			
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			}
			
			filterResult.setList(query.getResultList());
			
			return filterResult;
			
		} catch (Exception e) {
			logger.error(e); 
			throw new DaoException("No se pudo realizar el proceso, int�ntelo m�s tarde");
		} 
	}

	@Override
	public <F> FilterResult<F> search(String queryName, WhereCriteria filter, List<Object> parameters, Class<F> returnClass, String... replacements) throws DaoException {
		
		try {
			 
			QuerySelect querySelect = queryManager.getSelectQuery(queryName);
			if(querySelect == null) {
				logger.error(" query not found - >" + queryName);
				throw new DaoException(" please, consult admin" );
			}
			
			if(!QueryManager.C_QUERY_TYPE_SELECT.equalsIgnoreCase(querySelect.getType())) {
				logger.error(" it is not select query - >" + queryName);
				throw new DaoException("please, consult admin");
			}
			
			/**
				ProcessInfo processInfo = new ProcessInfo(false, ProcessType.SELECT);
				processInfo.setSql(querySelect.getSelect());
				processInfo.setWhere(querySelect.getWhere());
				processInfo.setApplyAdditional(true); 
				processInfo.setAdditional(querySelect.getAdditional());
				processInfo.setArgs(args); 
				processInfo.setParameters(parameters);  
			*/
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, querySelect, filter, parameters, replacements);
			FilterResult<F> filterResult = new FilterResult<>();
			filterResult.setPage(processResult.getPage());
			filterResult.setPageCount(processResult.getPageCount());
	 
			Query query = entyEntityManager.createNativeQuery(processResult.getSql(), returnClass); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			} 
			
			filterResult.setList(query.getResultList());
			 
			return filterResult;
			
			
  		} catch (DatabaseValidationException e) {
			logger.error(e); 
			throw new DaoException("error en el proceso, int�ntelo m�s tarde");
		} 
	}


		

	@Override
	public void delete(String queryName, WhereCriteria filter, List<Object> parameters)
			throws DaoException {
		
		try {
			QuerySelect querySelect = queryManager.getDeleteQuery(queryName);
			if(querySelect == null) {
				logger.error(" query not found - >" + queryName);
				throw new DaoException(" please, consult admin" );
			}
			
			if(!QueryManager.C_QUERY_TYPE_DELETE.equalsIgnoreCase(querySelect.getType())) {
				logger.error(" it is not delete query - >" + queryName);
				throw new DaoException("please, consult admin");
			}
			
			/**ProcessInfo processInfo = new ProcessInfo(false, ProcessType.DELETE);
			processInfo.setSql(querySelect.getSelect());
			processInfo.setWhere(querySelect.getWhere());
			processInfo.setApplyAdditional(true); 
			processInfo.setAdditional(querySelect.getAdditional());
			processInfo.setArgs(args); 
			processInfo.setParameters(parameters);  */
			
			
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, querySelect, filter, parameters);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql()); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			} 
			
			query.executeUpdate(); 
			
  		} catch (DatabaseValidationException e) {
			logger.error(e); 
			throw new DaoException("error en el proceso, int�ntelo m�s tarde");
		} 
	}

	@Override
	public void update(String queryName, WhereCriteria filter, List<Object> parameters, String... replacements )
			throws DaoException {
		
		try {
			QuerySelect querySelect = queryManager.getUpdateQuery(queryName);
			if(querySelect == null) {
				logger.error(" query not found - >" + queryName);
				throw new DaoException(" please, consult admin" );
			}
			
			if(!QueryManager.C_QUERY_TYPE_UPDATE.equalsIgnoreCase(querySelect.getType())) {
				logger.error(" it is not delete query - >" + queryName);
				throw new DaoException("please, consult admin");
			}
			
			/**ProcessInfo processInfo = new ProcessInfo(false, ProcessType.UPDATE);
			processInfo.setSql(querySelect.getSelect());
			processInfo.setWhere(querySelect.getWhere());
			processInfo.setApplyAdditional(true); 
			processInfo.setAdditional(querySelect.getAdditional());
			processInfo.setArgs(args); 
			processInfo.setParameters(parameters);  
			*/
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, querySelect, filter, parameters, replacements);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql()); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			} 
			
			query.executeUpdate();
			 
  		} catch (DatabaseValidationException e) {
			logger.error(e); 
			throw new DaoException("error en el proceso, int�ntelo m�s tarde");
		} 
		
	}
   

	@Override
	public <F> F selectOne(String queryName, WhereCriteria filter, List<Object> parameters, Class<F> returnClass ) throws DaoException {
		
		try {
		
			QuerySelect querySelect = queryManager.getSelectQuery(queryName);
			if(querySelect == null) {
				logger.error(" query not found - >" + queryName);
				throw new DaoException(" please, consult admin" );
			}
			
			if(!QueryManager.C_QUERY_TYPE_SELECT.equalsIgnoreCase(querySelect.getType())) {
				logger.error(" it is not select query - >" + queryName);
				throw new DaoException("please, consult admin");
			}
			/**
			ProcessInfo processInfo = new ProcessInfo(false, ProcessType.SELECT);
			processInfo.setSql(querySelect.getSelect());
			processInfo.setWhere(querySelect.getWhere());
			processInfo.setApplyAdditional(true); 
			processInfo.setAdditional(querySelect.getAdditional());
			processInfo.setArgs(args); 
			processInfo.setParameters(parameters);  
			*/
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, querySelect, filter, parameters);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql(), returnClass); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			}
			
			return (F) query.getSingleResult();
		
		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("error en el proceso, int�ntelo m�s tarde");
		} 
	}
	

	@Override
	public Object selectOne(String queryName, WhereCriteria filter, List<Object> parameters) throws DaoException {
		
		try {
		
			QuerySelect querySelect = queryManager.getSelectQuery(queryName);
			if(querySelect == null) {
				logger.error(" query not found - >" + queryName);
				throw new DaoException(" please, consult admin" );
			}
			
			if(!QueryManager.C_QUERY_TYPE_SELECT.equalsIgnoreCase(querySelect.getType())) {
				logger.error(" it is not select query - >" + queryName);
				throw new DaoException("please, consult admin");
			}

			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, querySelect, filter, parameters);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql()); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			}
			
			return query.getSingleResult();
		
		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("error en el proceso, int�ntelo m�s tarde");
		} 
	}

	@Override
	public <F> List<F> select(String queryName, WhereCriteria filter, List<Object> parameters, Class<F> returnClass, String... replacements) throws DaoException {
		 
		try {
			
			QuerySelect querySelect = queryManager.getSelectQuery(queryName);
			if(querySelect == null) {
				logger.error(" query not found - >" + queryName);
				throw new DaoException(" please, consult admin" );
			}
			
			if(!QueryManager.C_QUERY_TYPE_SELECT.equalsIgnoreCase(querySelect.getType())) {
				logger.error(" it is not select query - >" + queryName);
				throw new DaoException("please, consult admin");
			}
			/*
			ProcessInfo processInfo = new ProcessInfo(false, ProcessType.SELECT);
			processInfo.setSql(querySelect.getSelect());
			processInfo.setWhere(querySelect.getWhere());
			processInfo.setApplyAdditional(true); 
			processInfo.setAdditional(querySelect.getAdditional());
			processInfo.setArgs(args); 
			processInfo.setParameters(parameters);  */
			
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, querySelect, filter, parameters, replacements);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql(), returnClass); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			}
			
			return query.getResultList();
		
		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("error en el proceso, int�ntelo m�s tarde");
		} 
	} 
	
	@Override
	public <F> List<F> select(String queryName, WhereCriteria filter, List<Object>  parameters,
			CustomResultInfo<F> customResultInfo, String... replacements) throws DaoException {
		try {
			
			QuerySelect querySelect = queryManager.getSelectQuery(queryName);
			if(querySelect == null) {
				logger.error(" query not found - >" + queryName);
				throw new DaoException(" please, consult admin" );
			}
			
			if(!QueryManager.C_QUERY_TYPE_SELECT.equalsIgnoreCase(querySelect.getType())) {
				logger.error(" it is not select query - >" + queryName);
				throw new DaoException("please, consult admin");
			}
			
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, querySelect, filter, parameters, customResultInfo, replacements);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql(), customResultInfo.getResultClass()); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			}
			
			return query.getResultList();
		
		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("error en el proceso, int�ntelo m�s tarde");
		} 
	}
	
	@Override
	public List<Object> select(String queryName, WhereCriteria filter, List<Object> parameters, String... replacements) throws DaoException {
		 
		try {
			
			QuerySelect querySelect = queryManager.getSelectQuery(queryName);
			if(querySelect == null) {
				logger.error(" query not found - >" + queryName);
				throw new DaoException(" please, consult admin" );
			}
			
			if(!QueryManager.C_QUERY_TYPE_SELECT.equalsIgnoreCase(querySelect.getType())) {
				logger.error(" it is not select query - >" + queryName);
				throw new DaoException("please, consult admin");
			}
 
			
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, querySelect, filter, parameters, replacements);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql()); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			}
			
			return query.getResultList();
		
		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("error en el proceso, int�ntelo m�s tarde");
		} 
	}

	/**
	@Override
	public List<OptionValue> selectOptions(String tableName, String keyColumn, String textColumns,
			WhereCriteria filter) throws DaoException {
		
		try { 
			 
			QuerySelect querySelect = queryManager.getSelectQuery(QueryManager.C_SELECT_OPTION);
			if(querySelect == null) {
				logger.error(" query not found - >" + QueryManager.C_SELECT_OPTION);
				throw new DaoException(" please, consult admin" );
			}
			
			
			ProcessResult processResult = sqlSentencesProcessor.processSelectOptions(entyEntityManager, querySelect, tableName, keyColumn, textColumns, filter);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql(), OptionValue.class); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			}
			
			return query.getResultList();
	
		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("error en el proceso, int�ntelo m�s tarde");
		} 
	}
*/
 
}
