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
import org.platkmframework.common.domain.filter.criteria.DeleteCriteria;
import org.platkmframework.common.domain.filter.criteria.SearchCriteria;
import org.platkmframework.database.query.QueryDao;
import org.platkmframework.database.query.common.exception.DaoException;
import org.platkmframework.jpa.base.PlatkmEntityManager;
import org.platkmframework.jpa.exception.DatabaseValidationException;
import org.platkmframework.jpa.processor.ProcessResult;
import org.platkmframework.jpa.processor.SqlSentencesProcessor; 

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public final class QueryDaoImpl implements QueryDao{
	 
	private static final Logger logger = LogManager.getLogger(QueryDaoImpl.class);

	protected SqlSentencesProcessor  sqlSentencesProcessor;
	
	protected PlatkmEntityManager  entyEntityManager;

	public QueryDaoImpl(PlatkmEntityManager entyEntityManager, SqlSentencesProcessor sqlSentencesProcessor) {
		super();
		this.entyEntityManager = entyEntityManager;
		this.sqlSentencesProcessor = sqlSentencesProcessor;
	}

	@Override
	public <F> List<F> select(SearchCriteria searchCriteria, List<Object> params, Class<F> returnClass)
			throws DaoException {
		
		try { 
			/**ProcessInfo processInfo = new ProcessInfo(true, ProcessType.SELECT); 
			processInfo.setApplyAdditional(true);
			processInfo.setArgs(args); 
			processInfo.setParameters(params);  */
			
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, searchCriteria, params);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql(), returnClass); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			} 
 
			return query.getResultList();
			
  		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("usuario y/o contrase�a incorrecta");
		} 
	}

	@Override
	public <F> F selectOne(SearchCriteria searchCriteria, List<Object> params, Class<F> returnClass )
			throws DaoException {
		
		try { 
			/**
			ProcessInfo processInfo = new ProcessInfo(true, ProcessType.SELECT); 
			processInfo.setApplyAdditional(true);  ;
			processInfo.setArgs(args); 
			processInfo.setParameters(params);  
			*/
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, searchCriteria, params);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql(), returnClass); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			} 
 
			return (F) query.getSingleResult();
			
  		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("usuario y/o contrase�a incorrecta");
		} 
	}
	
	
	@Override
	public Object selectOne(SearchCriteria searchCriteria, List<Object> params )
			throws DaoException {
		
		try { 
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, searchCriteria, params);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql()); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			} 
 
			return query.getSingleResult();
			
  		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("usuario y/o contrase�a incorrecta");
		} 
	}

	@Override
	public List select(SearchCriteria searchCriteria, List<Object> params) throws DaoException {
		
		try { 
			/**
			ProcessInfo processInfo = new ProcessInfo(true, ProcessType.SELECT); 
			processInfo.setApplyAdditional(true);  ;
			processInfo.setArgs(args); 
			processInfo.setParameters(params);  
			*/
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, searchCriteria, params);
			
			Query query = entyEntityManager.createNativeQuery(processResult.getSql()); 
			if(processResult.getParameters() != null){
		          for (int i = 0; i<processResult.getParameters().size(); i++) 
		        	  query.setParameter(i+1, processResult.getParameters().get(i)); 
			} 
 
			return query.getResultList();
			
  		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("usuario y/o contrase�a incorrecta");
		} 
	}

	@Override
	public int remove(DeleteCriteria deleteCriteria) throws DaoException {
		 
		try {
			ProcessResult processResult = sqlSentencesProcessor.removeProcess(entyEntityManager, deleteCriteria, null);
			Query query = entyEntityManager.createNativeQuery(processResult.getSql()); 
			if(processResult.getParameters() != null){
				for (int i = 0; i<processResult.getParameters().size(); i++) 
					query.setParameter(i+1, processResult.getParameters().get(i)); 
			} 
			return query.executeUpdate();
			
		} catch (DatabaseValidationException e) {
			logger.error(e,e); 
			throw new DaoException("No se pudo realizar la operaci�n, int�ntelo m�s tarde");
		}
	}

	@Override
	public <F> FilterResult<F> search(SearchCriteria searchCriteria, List<Object> params, Class<F> returnClass)
			throws DaoException {
	
		 
		try {
			ProcessResult processResult = sqlSentencesProcessor.process(entyEntityManager, searchCriteria, params);
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
			logger.error(e,e); 
			throw new DaoException("No se pudo realizar la operaci�n, int�ntelo m�s tarde");
		}
	}

}
