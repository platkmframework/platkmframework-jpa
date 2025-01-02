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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.platkmframework.database.query.common.vo.CustomResultInfo;
import org.platkmframework.database.query.manager.model.QuerySelect;
import org.platkmframework.database.query.manager.model.QuerySyntax;
import org.platkmframework.jpa.base.PlatkmORMEntityManager;
import org.platkmframework.jpa.exception.DatabaseValidationException;
import org.platkmframework.jpa.mapping.JpaPropertyConstant;
import org.platkmframework.persistence.filter.criteria.SearchCriteria;
import org.platkmframework.persistence.filter.criteria.WhereCriteria;
import org.platkmframework.persistence.filter.criteria.base.ConditionFilterBase;
import org.platkmframework.persistence.filter.enumerator.GroupOperator;
import org.platkmframework.persistence.filter.enumerator.SqlOperator;
import org.platkmframework.persistence.filter.enumerator.SqlOrder;
import org.platkmframework.persistence.filter.info.FilterData;
import org.platkmframework.persistence.filter.info.FilterDataType;
import org.platkmframework.util.Util;
import jakarta.persistence.Query;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public abstract class SqlSentencesProcessorBase implements SqlSentencesProcessor {

    /**
     * Atributo C_MSSQL
     */
    public static final String C_MSSQL = "mssql";

    /**
     * Atributo C_POSTGRESQL
     */
    public static final String C_POSTGRESQL = "postgresql";

    /**
     * Atributo logger
     */
    private static Logger logger = LoggerFactory.getLogger(SqlSentencesProcessorBase.class);

    
    /**
     * SqlSentencesProcessorBase
     */
    public SqlSentencesProcessorBase() {
		super();
	}

	/**
     * process
     * @param platkmEntityManager platkmEntityManager
     * @param filter filter
     * @param param param
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    @Override
    public ProcessResult process(PlatkmORMEntityManager platkmEntityManager, ConditionFilterBase filter, List<Object> param) throws DatabaseValidationException {
        List<Object> parameters = cloneList(param);
        ProcessResult processResult = processCriteria(platkmEntityManager, filter.getSql(), parameters);
        FilterData filterDataWhere = filter.getSql().stream().filter((obj -> ((FilterData) obj).isType(FilterDataType.WHEREINFO))).findAny().orElse(null);
        boolean withWhere = filterDataWhere != null;
        //fastsearch
        if (processResult.getFastSearchInfo() != null && StringUtils.isNotBlank(processResult.getFastSearchInfo().getFastsearch()) && processResult.getFastSearchInfo().getFastSearchList() != null && !processResult.getFastSearchInfo().getFastSearchList().isEmpty()) {
            processResult.addSQL(processFastSearch(platkmEntityManager.getQueryManager().getAllQuerySyntax(), withWhere, processResult.getFastSearchInfo().getFastsearch(), parameters, processResult.getFastSearchInfo().getFastSearchList()));
        }
        //group by
        //String groupBy = processResult.getGroupBy();
        String orderBy = "";
        //, order by
        if (processResult.getOrderBy().size() > 0) {
            String coma = " ORDER BY  ";
            for (FilterData filterData : processResult.getOrderBy()) {
                orderBy += coma + filterData.getOrderColumn() + " " + getOrderByType(filterData.getOrderType());
                coma = ",";
            }
        }
        if (StringUtils.isNotBlank(processResult.getGroupBy())) {
            processResult.addSQL(" GROUP BY " + processResult.getGroupBy());
        }
        processResult.addSQL(" " + processResult.getHaving());
        processResult.addSQL(" " + orderBy);
        String sql = process(processResult.getSbSQL(), null, parameters);
        List<String> listArgs = new ArrayList<>();
        sql = checkingArgs(sql, listArgs.stream().toArray(String[]::new));
        //offset
        if (processResult.getOffSetInfo() != null && processResult.getOrderBy() != null && processResult.getOrderBy().size() > 0) {
            sql = processOffset(processResult, sql, platkmEntityManager, parameters);
        }
        processResult.setSql(sql);
        Object oShowSQL = platkmEntityManager.getProperties().get(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL);
        if (oShowSQL != null && Boolean.valueOf(oShowSQL.toString())) {
            logger.info(processResult.getSql());
        }
        return processResult;
    }

    /**
     * process
     * @param platkmEntityManager platkmEntityManager
     * @param querySelect querySelect
     * @param whereCriteria whereCriteria
     * @param param param
     * @param replacements replacements
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    @Override
    public ProcessResult process(PlatkmORMEntityManager platkmEntityManager, QuerySelect querySelect, WhereCriteria whereCriteria, List<Object> param, String... replacements) throws DatabaseValidationException {
        return process(platkmEntityManager, null, querySelect, whereCriteria, param, replacements);
    }

    /**
     * process
     * @param platkmEntityManager platkmEntityManager
     * @param customSelect customSelect
     * @param querySelect querySelect
     * @param whereCriteria whereCriteria
     * @param param param
     * @param replacements replacements
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    protected ProcessResult process(PlatkmORMEntityManager platkmEntityManager, String customSelect, QuerySelect querySelect, WhereCriteria whereCriteria, List<Object> param, String... replacements) throws DatabaseValidationException {
        List<Object> parameters = cloneList(param);
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(customSelect)) {
            sb.append(customSelect + " ");
        } else {
            sb.append(querySelect.getSelect() + " ");
        }
        if (StringUtils.isNotBlank(querySelect.getFrom())) {
            sb.append(querySelect.getFrom());
        }
        ProcessResult processResult;
        //the sql supose to be only where sentences
        String strWhere = "";
        String strQueryWhere = querySelect.getWhere();
        boolean withWhere = false;
        if (whereCriteria == null) {
            processResult = new ProcessResult();
            processResult.setParameters(parameters);
            if (StringUtils.isNotBlank(strQueryWhere)) {
                sb.append(strQueryWhere);
                withWhere = true;
            }
        } else {
            whereCriteria.getSql().removeIf(p -> p.getFilterDataType().name().equalsIgnoreCase(FilterDataType.WHEREINFO.name()));
            processResult = processCriteria(platkmEntityManager, whereCriteria.getSql(), parameters);
            strWhere = processResult.getSbSQL();
            if (StringUtils.isNotBlank(strWhere)) {
                //overwrite the where sentences from  querySelect
                sb.append(" WHERE " + strWhere);
                withWhere = true;
            } else if (StringUtils.isNotBlank(strQueryWhere)) {
                sb.append(strQueryWhere);
                withWhere = true;
            }
            for (String fastsearch : querySelect.getFastsearch()) {
                processResult.getFastSearchInfo().getFastSearchList().add(fastsearch);
            }
            //fastsearch
            if (processResult.getFastSearchInfo() != null && StringUtils.isNotBlank(processResult.getFastSearchInfo().getFastsearch()) && processResult.getFastSearchInfo().getFastSearchList() != null && !processResult.getFastSearchInfo().getFastSearchList().isEmpty()) {
                //processResult.addSQL(processFastSearch(platkmEntityManager.getQueryManager().getAllQuerySyntax(), withWhere, processResult.getFastSearchInfo().getFastsearch(), parameters, processResult.getFastSearchInfo().getFastSearchList()));
                sb.append(" " + processFastSearch(platkmEntityManager.getQueryManager().getAllQuerySyntax(), withWhere, processResult.getFastSearchInfo().getFastsearch(), parameters, processResult.getFastSearchInfo().getFastSearchList()));
            }
        }
        String orderBy = "";
        //, order by
        if (processResult.getOrderBy().size() > 0) {
            String coma = "";
            for (FilterData filterData : processResult.getOrderBy()) {
                orderBy += coma + " ORDER BY " + filterData.getOrderColumn() + " " + getOrderByType(filterData.getOrderType());
                coma = ",";
            }
        } else {
            orderBy = querySelect.getAdditional() == null ? "" : querySelect.getAdditional();
        }
        //queryCriteria.getArguments(
        sb.append(" " + orderBy + " ");
        //setting arguments
        List<Object> listArgs = new ArrayList<>();
        listArgs.add("tableWhere=" + processResult.getSbSQL());
        listArgs.add("tableGroupBy=" + processResult.getGroupBy());
        listArgs.add("orderBy=" + orderBy);
        if (whereCriteria != null && whereCriteria.getArguments() != null) {
            listArgs.addAll(whereCriteria.getArguments());
        }
        String[] arg = listArgs.stream().toArray(String[]::new);
        String sql = process(sb.toString(), arg, parameters);
        processResult.addSQL(" " + orderBy);
        sql = checkingArgs(sql, arg);
        if (replacements != null) {
            String[] aValue;
            for (int i = 0; i < replacements.length; i++) {
                aValue = replacements[i].split("=");
                sql = sql.replaceAll("(?i)" + Pattern.quote("${" + aValue[0] + "}"), aValue[1]);
                //sql = sql.replace(aValue[0], aValue[1]);
            }
        }
        if (processResult.getOffSetInfo() != null && processResult.getOrderBy() != null && processResult.getOrderBy().size() > 0) {
            //offset
            sql = processOffset(processResult, sql, platkmEntityManager, parameters);
        }
        processResult.setSql(sql);
        Object oShowSQL = platkmEntityManager.getProperties().get(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL);
        if (oShowSQL != null && Boolean.valueOf(oShowSQL.toString())) {
            logger.info(processResult.getSql());
        }
        return processResult;
    }

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
    @Override
    public ProcessResult process(PlatkmORMEntityManager entyEntityManager, QuerySelect querySelect, WhereCriteria filter, List<Object> parameters, CustomResultInfo<?> customResultInfo, String[] replacements) throws DatabaseValidationException {
        return process(entyEntityManager, "SELECT " + String.join(",", customResultInfo.getColumns()), querySelect, filter, parameters, replacements);
    }

    /**
     * processOffsetRecordCount
     * @param sql sql
     * @param platkmEntityManager platkmEntityManager
     * @param parameters parameters
     * @return Integer
     */
    protected Integer processOffsetRecordCount(String sql, PlatkmORMEntityManager platkmEntityManager, List<Object> parameters) {
        String sqlCount = "SELECT count('x') as cantidad FROM (" + sql + " ) AS vwSelect";
        Query query = platkmEntityManager.createNativeQuery(sqlCount);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) query.setParameter(i + 1, parameters.get(i));
        }
        Integer recordCount = 0;
        Object obj = query.getSingleResult();
        if (obj != null)
            recordCount = Integer.valueOf(((Object[]) obj)[0].toString());
        return recordCount;
    }

    /**
     * processOffset
     * @param processResult processResult
     * @param sql sql
     * @param platkmEntityManager platkmEntityManager
     * @param parameters parameters
     * @return String
     */
    protected String processOffset(ProcessResult processResult, String sql, PlatkmORMEntityManager platkmEntityManager, List<Object> parameters) {
        int pageCount = 0;
        int page = 0;
        if (processResult.getOffSetInfo() != null && processResult.getOffSetInfo().getRecordPerPage() > 0) {
            String sqlCount = "SELECT count('x') as cantidad FROM (" + sql + ") AS vwSelect";
            Object oShowSQL = platkmEntityManager.getProperties().get(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL);
            if (oShowSQL != null && Boolean.valueOf(oShowSQL.toString())) {
                logger.info(sqlCount);
            }
            Query query = platkmEntityManager.createNativeQuery(sqlCount);
            if (parameters != null) {
                for (int i = 0; i < parameters.size(); i++) query.setParameter(i + 1, parameters.get(i));
            }
            Integer recordCount = 0;
            Object obj = query.getSingleResult();
            if (obj != null)
                recordCount = Integer.valueOf(((Object[]) obj)[0].toString());
            if (recordCount > 0) {
                if (StringUtils.isNotBlank(processResult.getAddtionalDataInfo())) {
                    sql += " " + processResult.getAddtionalDataInfo();
                }
                page = processResult.getOffSetInfo().getPage();
                if (page < 1) {
                    page = 1;
                }
                long offsetValue = processResult.getOffSetInfo().getRecordPerPage() * (processResult.getOffSetInfo().getPage() - 1);
                if (offsetValue < 0)
                    offsetValue = 0;
                pageCount = (recordCount + processResult.getOffSetInfo().getRecordPerPage() - 1) / processResult.getOffSetInfo().getRecordPerPage();
                sql += " OFFSET " + offsetValue + " ROWS FETCH NEXT " + processResult.getOffSetInfo().getRecordPerPage() + " ROWS ONLY ";
            }
        }
        processResult.setPage(page);
        processResult.setPageCount(pageCount);
        return sql;
    }

    /**
     * process
     * @param platkmEntityManager platkmEntityManager
     * @param sql sql
     * @param searchCriteria searchCriteria
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    @Override
    public ProcessResult process(PlatkmORMEntityManager platkmEntityManager, String sql, SearchCriteria searchCriteria) throws DatabaseValidationException {
        // TODO REPORT
        throw new UnsupportedOperationException();
    }

    /**
     * processCriteria
     * @param platkmEntityManager platkmEntityManager
     * @param filterList filterList
     * @param parameters parameters
     * @return ProcessResult
     * @throws DatabaseValidationException DatabaseValidationException
     */
    protected ProcessResult processCriteria(PlatkmORMEntityManager platkmEntityManager, List<FilterData> filterList, List<Object> parameters) throws DatabaseValidationException {
        ProcessResult processResult = new ProcessResult();
        processResult.setParameters(parameters);
        if (filterList != null && filterList.size() > 0) {
            String auxSql;
            for (FilterData ob : filterList) {
                if (ob.isType(FilterDataType.SELECTINFO)) {
                    auxSql = ob.getSelectColumns();
                    if (StringUtils.isBlank(auxSql)) {
                        auxSql = " SELECT * ";
                    } else {
                        auxSql = " SELECT " + auxSql;
                    }
                    processResult.addSQL(auxSql);
                } else if (ob.isType(FilterDataType.FROMINFO)) {
                    auxSql = " FROM " + ob.getFrom();
                    if (StringUtils.isNotBlank(ob.getAlias())) {
                        auxSql += " AS " + ob.getAlias();
                    }
                    processResult.addSQL(auxSql);
                } else if (ob.isType(FilterDataType.JOININFO)) {
                    auxSql = " " + _getQuerySyntaxValue(platkmEntityManager.getQueryManager().getAllQuerySyntax(), ob.getInnerJoin().name()) + " " + ob.getTable() + " AS " + ob.getTableAlias();
                    processResult.addSQL(auxSql + " on ");
                    //processResult.setWithWhere(true);
                } else if (ob.isType(FilterDataType.USINGPOSTGRESQLINFO)) {
                    auxSql = " " + ob.getUsing();
                    processResult.addSQL(auxSql + " ");
                } else if (ob.isType(FilterDataType.WHEREINFO)) {
                    processResult.addSQL(" where ");
                    processResult.setWithWhere(true);
                } else if (ob.isType(FilterDataType.EXPRESSIONINFO)) {
                    auxSql = " " + getExpression(ob, parameters);
                    processResult.addSQL(auxSql);
                } else if (ob.isType(FilterDataType.VALUEINFO)) {
                    auxSql = " " + getExpression(ob, parameters);
                    processResult.addSQL(auxSql);
                } else if (ob.isType(FilterDataType.OPERATORINFO)) {
                    auxSql = getOperator(ob.getSqlOperator());
                    processResult.addSQL(auxSql);
                } else if (ob.isType(FilterDataType.DELETEINFO)) {
                    processResult.addSQL(" DELETE ");
                } else if (ob.isType(FilterDataType.GROUPBYINFO)) {
                    processResult.addGroupBy(ob);
                } else if (ob.isType(FilterDataType.FASTSEARCHINFO)) {
                    processResult.setFastSearchInfo(ob);
                } else if (ob.isType(FilterDataType.OFFSETINFO)) {
                    processResult.setOffSetInfo(ob);
                } else if (ob.isType(FilterDataType.ADDTIONALDATAINFO)) {
                    processResult.addAdditionalInfo(ob);
                } else if (ob.isType(FilterDataType.ORDERBYINFO)) {
                    processResult.addOrderBy(ob);
                } else if (ob.isType(FilterDataType.HAVINGINFO)) {
                    processResult.addHavingInfo(ob);
                }
            }
        }
        return processResult;
    }

    /**
     * getOperator
     * @param operator operator
     * @return String
     * @throws DatabaseValidationException DatabaseValidationException
     */
    protected String getOperator(String operator) throws DatabaseValidationException {
        if (SqlOperator.and.name().equals(operator)) {
            return " AND ";
        } else if (SqlOperator.or.name().equals(operator)) {
            return " OR ";
        } else if (GroupOperator.close.name().equals(operator)) {
            return " ) ";
        } else if (GroupOperator.open.name().equals(operator)) {
            return " ( ";
        } else {
            logger.error("sql operator not added - " + operator);
            throw new DatabaseValidationException("error consult admnin");
        }
    }

    /**
     * _getQuerySyntaxValue
     * @param listQuerySyntax listQuerySyntax
     * @param key key
     * @return String
     */
    protected String _getQuerySyntaxValue(List<QuerySyntax> listQuerySyntax, String key) {
        if (listQuerySyntax == null)
            return "";
        for (QuerySyntax querySyntax : listQuerySyntax) {
            if (querySyntax.getName().equals(key))
                return querySyntax.getValue();
        }
        return "";
    }

    /**
     * process
     * @param sql sql
     * @param args args
     * @param param param
     * @return String
     * @throws DatabaseValidationException DatabaseValidationException
     */
    protected String process(String sql, String[] args, List<Object> param) throws DatabaseValidationException {
        //checking ${key}, the args are key=value
        String auxSQL = checkingArgs(sql, args);
        Map<String, String> indexInMap = new HashMap<>();
        //update sql sentences whit filter
        //sql = updateSqlSentencesForFilter(sql, param, filter);
        //again after filter
        //checkingArgs(sql, args);
        //searching for array or Collection
        if (param != null) {
            Object value;
            List<?> list;
            List<Object> auxParam = new ArrayList<>();
            //auxParam.addAll(param);
            for (int i = 0; i < param.size(); i++) {
                value = param.get(i);
                list = checkForList(value);
                if (list != null) {
                    if (list.isEmpty())
                        throw new DatabaseValidationException("the list parameter is empty in the search process");
                    //int j = i;
                    auxParam.addAll(list);
                    /*for (Object object : list) {
						param.add(j, object);
						j++;
					}
					param.add(j); */
                    auxSQL = updateSqlSentencesForListParameter(auxSQL, (i + 1 - indexInMap.size()), list.size(), indexInMap);
                } else {
                    auxParam.add(value);
                }
            }
            param.clear();
            param.addAll(auxParam);
            for (var entry : indexInMap.entrySet()) {
                auxSQL = auxSQL.replace(entry.getKey(), entry.getValue());
            }
        }
        return auxSQL;
    }

    /**
     * checkingArgs
     * @param sql sql
     * @param args args
     * @return String
     */
    protected String checkingArgs(String sql, String[] args) {
        if (args != null) {
            String[] keyValue;
            for (int i = 0; i < args.length; i++) {
                keyValue = args[i].split("=", 2);
                sql = sql.replaceAll("(?i)" + Pattern.quote("${" + keyValue[0] + "}"), keyValue[1]);
            }
        }
        return sql;
    }

    /**
     * updateSqlSentencesForListParameter
     * @param sql sql
     * @param paramIndex paramIndex
     * @param arrayParamCount arrayParamCount
     * @param indexInMap indexInMap
     * @return String
     * @throws DatabaseValidationException DatabaseValidationException
     */
    private String updateSqlSentencesForListParameter(String sql, int paramIndex, int arrayParamCount, Map<String, String> indexInMap) throws DatabaseValidationException {
        int index = theNthOcurrency(sql, paramIndex);
        indexInMap.put("#paramlist_" + paramIndex, ",?".repeat(arrayParamCount).substring(1));
        return sql.substring(0, index) + ("#paramlist_" + paramIndex) + sql.substring(index + 1);
    }

    /**
     * theNthOcurrency
     * @param sql sql
     * @param nth nth
     * @return int
     */
    private int theNthOcurrency(String sql, int nth) {
        if (nth <= 1) {
            return sql.indexOf("?");
        } else {
            return sql.indexOf("?", theNthOcurrency(sql, nth - 1) + "?".length());
        }
    }

    /**
     * checkForList
     * @param value value
     * @return List
     */
    private List<?> checkForList(Object value) {
        List<?> list = null;
        if (value instanceof Collection)
            list = new ArrayList<>((Collection<?>) value);
        else if (value instanceof Object[])
            list = Util.convertArrayToList((Object[]) value);
        else if (value instanceof String[])
            list = Util.convertArrayToList((String[]) value);
        else if (value instanceof Double[])
            list = Util.convertArrayToList((Double[]) value);
        else if (value instanceof Integer[])
            list = Util.convertArrayToList((Integer[]) value);
        else if (value instanceof Float[])
            list = Util.convertArrayToList((Float[]) value);
        else if (value instanceof Character[])
            list = Util.convertArrayToList((Character[]) value);
        return list;
    }

    /**
     * cloneList
     * @param parameters parameters
     * @return List
     */
    protected List<Object> cloneList(List<?> parameters) {
        if (parameters == null)
            return new ArrayList<>();
        List<Object> paramResult = new ArrayList<>();
        for (Object object : parameters) paramResult.add(object);
        return paramResult;
    }

    /**
     * getExpression
     * @param expression expression
     * @param param param
     * @return String
     * @throws DatabaseValidationException DatabaseValidationException
     */
    protected abstract String getExpression(FilterData expression, List<Object> param) throws DatabaseValidationException;

    /**
     * getExpression
     * @param colName colName
     * @param operator operator
     * @param value value
     * @param param param
     * @return String
     */
    protected abstract String getExpression(String colName, String operator, Object value, List<Object> param);

    /**
     * processFastSearch
     * @param listQuerySyntax listQuerySyntax
     * @param withWhere withWhere
     * @param fastsearchValue fastsearchValue
     * @param parameters parameters
     * @param fastsearchList fastsearchList
     * @return String
     */
    protected abstract String processFastSearch(List<QuerySyntax> listQuerySyntax, boolean withWhere, String fastsearchValue, List<Object> parameters, List<String> fastsearchList);

    /**
     * processWhere
     * @param param param
     * @param whereFilter whereFilter
     * @return String
     * @throws DatabaseValidationException DatabaseValidationException
     */
    public abstract String processWhere(List<Object> param, ConditionFilterBase whereFilter) throws DatabaseValidationException;

    /**
     * getOrderByType
     * @param orderType orderType
     * @return String
     * @throws DatabaseValidationException DatabaseValidationException
     */
    protected abstract String getOrderByType(SqlOrder orderType) throws DatabaseValidationException;
}
