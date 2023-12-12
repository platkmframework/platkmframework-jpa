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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.platkmframework.common.domain.filter.info.FilterData; 


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class ProcessResult {
	 
	private List<Object> parameters; 
	private int page;
	private int pageCount;
	
	private FilterData fastSearchInfo;
	private FilterData havingInfo;
	private String addtionalDataInfo= "";
	private FilterData offSetInfo;
	private List<FilterData> orderBy; 
	private String groupBy= ""; 
	private Boolean withWhere;
	private String sql = "";
	
	StringBuilder sb = new StringBuilder(); 
	 
	public ProcessResult() {
		super();
	}

	public ProcessResult( List<Object> parameters, int page, int pageCount) {
		 
		 this.parameters = parameters;
		 this.page = page;
		 this.pageCount = pageCount;
	}
	 
	public void addSQL(String value) {
		sb.append(value);
	}
	public void addGroupBy(FilterData ob) {
		groupBy = ob.getGroupColumns(); 
	}

	public void addAdditionalInfo(FilterData ob) {
		addtionalDataInfo = ob.getGroupColumns(); 
		
	}

	public void addOrderBy(FilterData ob) {
		getOrderBy().add(ob);
	}
	public List<Object> getParameters() {
		return parameters;
	}
	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	} 
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public FilterData getFastSearchInfo() {
		return fastSearchInfo;
	}

	public void setFastSearchInfo(FilterData fastSearchInfo) {
		this.fastSearchInfo = fastSearchInfo;
	}

	public String getAddtionalDataInfo() {
		return addtionalDataInfo;
	}

	public void setAddtionalDataInfo(String addtionalDataInfo) {
		this.addtionalDataInfo = addtionalDataInfo;
	}

	public FilterData getOffSetInfo() {
		return offSetInfo;
	}

	public void setOffSetInfo(FilterData offSetInfo) {
		this.offSetInfo = offSetInfo;
	}
 
	public List<FilterData> getOrderBy() {
		if(orderBy == null) orderBy = new ArrayList<>();
		return orderBy;  
	}

	public void setOrderBy(List<FilterData> orderBy) {
		this.orderBy = orderBy;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public Boolean getWithWhere() {
		return withWhere;
	}

	public void setWithWhere(Boolean withWhere) {
		this.withWhere = withWhere;
	}

	public void setSql(String sql) {
		this.sql = sql; 
	}

	public String getSql() {
		return sql;
	}
	
	public String getSbSQL() {
		return sb.toString();
	}

	public void addHavingInfo(FilterData havingInfo) {
	 this.havingInfo = havingInfo;
		
	}

	public FilterData getHavingInfo() {
		return havingInfo;
	}

	public String getHaving() { 
		return havingInfo == null || StringUtils.isBlank(havingInfo.getHavingInfo())? "": " HAVING " + havingInfo.getHavingInfo();
	}
 

}
