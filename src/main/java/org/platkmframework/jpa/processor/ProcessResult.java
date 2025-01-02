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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.platkmframework.persistence.filter.info.FilterData;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class ProcessResult {

    /**
     * Atributo parameters
     */
    private List<Object> parameters;

    /**
     * Atributo page
     */
    private int page;

    /**
     * Atributo pageCount
     */
    private int pageCount;

    /**
     * Atributo fastSearchInfo
     */
    private FilterData fastSearchInfo;

    /**
     * Atributo havingInfo
     */
    private FilterData havingInfo;

    /**
     * Atributo addtionalDataInfo
     */
    private String addtionalDataInfo = "";

    /**
     * Atributo offSetInfo
     */
    private FilterData offSetInfo;

    /**
     * Atributo orderBy
     */
    private List<FilterData> orderBy;

    /**
     * Atributo groupBy
     */
    private String groupBy = "";

    /**
     * Atributo withWhere
     */
    private Boolean withWhere;

    /**
     * Atributo sql
     */
    private String sql = "";

    /**
     * Atributo sb
     */
    StringBuilder sb = new StringBuilder();

    /**
     * Constructor ProcessResult
     */
    public ProcessResult() {
        super();
    }

    /**
     * Constructor ProcessResult
     * @param parameters parameters
     * @param page page
     * @param pageCount pageCount
     */
    public ProcessResult(List<Object> parameters, int page, int pageCount) {
        this.parameters = parameters;
        this.page = page;
        this.pageCount = pageCount;
    }

    /**
     * addSQL
     * @param value value
     */
    public void addSQL(String value) {
        sb.append(value);
    }

    /**
     * addGroupBy
     * @param ob ob
     */
    public void addGroupBy(FilterData ob) {
        groupBy = ob.getGroupColumns();
    }

    /**
     * addAdditionalInfo
     * @param ob ob
     */
    public void addAdditionalInfo(FilterData ob) {
        addtionalDataInfo = ob.getGroupColumns();
    }

    /**
     * addOrderBy
     * @param ob ob
     */
    public void addOrderBy(FilterData ob) {
        getOrderBy().add(ob);
    }

    /**
     * getParameters
     * @return List
     */
    public List<Object> getParameters() {
        return parameters;
    }

    /**
     * setParameters
     * @param parameters parameters
     */
    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * getPage
     * @return int
     */
    public int getPage() {
        return page;
    }

    /**
     * setPage
     * @param page page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * getPageCount
     * @return int
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * setPageCount
     * @param pageCount pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * getFastSearchInfo
     * @return FilterData
     */
    public FilterData getFastSearchInfo() {
        return fastSearchInfo;
    }

    /**
     * setFastSearchInfo
     * @param fastSearchInfo fastSearchInfo
     */
    public void setFastSearchInfo(FilterData fastSearchInfo) {
        this.fastSearchInfo = fastSearchInfo;
    }

    /**
     * getAddtionalDataInfo
     * @return String
     */
    public String getAddtionalDataInfo() {
        return addtionalDataInfo;
    }

    /**
     * setAddtionalDataInfo
     * @param addtionalDataInfo addtionalDataInfo
     */
    public void setAddtionalDataInfo(String addtionalDataInfo) {
        this.addtionalDataInfo = addtionalDataInfo;
    }

    /**
     * getOffSetInfo
     * @return FilterData
     */
    public FilterData getOffSetInfo() {
        return offSetInfo;
    }

    /**
     * setOffSetInfo
     * @param offSetInfo offSetInfo
     */
    public void setOffSetInfo(FilterData offSetInfo) {
        this.offSetInfo = offSetInfo;
    }

    /**
     * getOrderBy
     * @return List
     */
    public List<FilterData> getOrderBy() {
        if (orderBy == null)
            orderBy = new ArrayList<>();
        return orderBy;
    }

    /**
     * setOrderBy
     * @param orderBy orderBy
     */
    public void setOrderBy(List<FilterData> orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * getGroupBy
     * @return String
     */
    public String getGroupBy() {
        return groupBy;
    }

    /**
     * setGroupBy
     * @param groupBy groupBy
     */
    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    /**
     * getWithWhere
     * @return Boolean
     */
    public Boolean getWithWhere() {
        return withWhere;
    }

    /**
     * setWithWhere
     * @param withWhere withWhere
     */
    public void setWithWhere(Boolean withWhere) {
        this.withWhere = withWhere;
    }

    /**
     * setSql
     * @param sql sql
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * getSql
     * @return String
     */
    public String getSql() {
        return sql;
    }

    /**
     * getSbSQL
     * @return String
     */
    public String getSbSQL() {
        return sb.toString();
    }

    /**
     * addHavingInfo
     * @param havingInfo havingInfo
     */
    public void addHavingInfo(FilterData havingInfo) {
        this.havingInfo = havingInfo;
    }

    /**
     * getHavingInfo
     * @return FilterData
     */
    public FilterData getHavingInfo() {
        return havingInfo;
    }

    /**
     * getHaving
     * @return String
     */
    public String getHaving() {
        return havingInfo == null || StringUtils.isBlank(havingInfo.getHavingInfo()) ? "" : " HAVING " + havingInfo.getHavingInfo();
    }
}
