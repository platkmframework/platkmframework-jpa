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
package org.platkmframework.database.query.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.platkmframework.database.query.manager.model.QuerySelect;
import org.platkmframework.database.query.manager.model.QuerySyntax;
import org.platkmframework.util.manager.ManagerException;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class QueryManager {

    /**
     * Atributo SELECT_TOP_CUSTOM_TABLE
     */
    public static final String SELECT_TOP_CUSTOM_TABLE = "SELECT_TOP_CUSTOM_TABLE";

    /**
     * Atributo C_SELECT_TABLE_RECORD_BY_ID
     */
    public static final String C_SELECT_TABLE_RECORD_BY_ID = "SELECT_TABLE_RECORD_BY_ID";

    /**
     * Atributo C_FIND_BY_COUNT_CUSTOM_TABLE
     */
    public static final String C_FIND_BY_COUNT_CUSTOM_TABLE = "FIND_BY_COUNT_CUSTOM_TABLE";

    /**
     * Atributo C_SELECT_OPTION
     */
    public static final String C_SELECT_OPTION = "SELECT_OPTION";

    /**
     * Atributo C_FIND_BY_CUSTOM_TABLE
     */
    public static final String C_FIND_BY_CUSTOM_TABLE = "FIND_BY_CUSTOM_TABLE";

    /**
     * Atributo C_FIND_ONE_BY_CUSTOM_TABLE
     */
    public static final String C_FIND_ONE_BY_CUSTOM_TABLE = "FIND_ONE_BY_CUSTOM_TABLE";

    /**
     * Atributo C_FIND_BY_CUSTOM_TABLE_ORDER
     */
    public static final String C_FIND_BY_CUSTOM_TABLE_ORDER = "FIND_BY_CUSTOM_TABLE_ORDER";

    /**
     * Atributo C_SEARCH_PROCESS
     */
    public static final String C_SEARCH_PROCESS = "SEARCH_PROCESS";

    /**
     * Atributo C_SEARCH_PROCESS_COUNT
     */
    public static final String C_SEARCH_PROCESS_COUNT = "SEARCH_PROCESS_COUNT";

    /**
     * Atributo C_REMOVE_MODELDATA_RECORD
     */
    public static final String C_REMOVE_MODELDATA_RECORD = "REMOVE_MODELDATA_RECORD";

    /**
     * Atributo C_REMOVE_MODELDATA_RECORDS
     */
    public static final String C_REMOVE_MODELDATA_RECORDS = "REMOVE_MODELDATA_RECORDS";

    /**
     * Atributo C_REMOVE_TABLE_BY_FILTERS
     */
    public static final String C_REMOVE_TABLE_BY_FILTERS = "REMOVE_TABLE_BY_FILTER";

    /**
     * Atributo C_QUERY_TYPE_SELECT
     */
    public static final String C_QUERY_TYPE_SELECT = "SELECT";

    /**
     * Atributo C_QUERY_TYPE_UPDATE
     */
    public static final String C_QUERY_TYPE_UPDATE = "UPDATE";

    /**
     * Atributo C_QUERY_TYPE_DELETE
     */
    public static final String C_QUERY_TYPE_DELETE = "DELETE";

    /**
     * Atributo C_RCODE_FIELD_NAME
     */
    public static final String C_RCODE_FIELD_NAME = "rcode";

    /**
     * Atributo databaseName
     */
    protected String databaseName;

    /**
     * Atributo mapQueryEntry
     */
    private Map<String, QueryEntry> mapQueryEntry;

    /**
     * Constructor QueryManager
     * @param databaseName databaseName
     */
    public QueryManager(String databaseName) {
        this.databaseName = databaseName;
        mapQueryEntry = new HashMap<>();
    }

    /**
     * readModel
     * @param key key
     * @param absolutePath absolutePath
     * @throws ManagerException ManagerException
     */
    public void readModel(String key, String absolutePath) throws ManagerException {
        QueryEntry queryEntry = new QueryEntry(databaseName);
        queryEntry.init(absolutePath);
        mapQueryEntry.put(key, queryEntry);
    }

    /**
     * readModel
     * @param key key
     * @param inputStream inputStream
     * @throws ManagerException ManagerException
     */
    public void readModel(String key, InputStream inputStream) throws ManagerException {
        QueryEntry queryEntry = new QueryEntry(databaseName);
        queryEntry.init(inputStream);
        mapQueryEntry.put(key, queryEntry);
    }

    /**
     * getQuery
     * @param queryName queryName
     * @return QuerySelect
     */
    public QuerySelect getQuery(String queryName) {
        return getSelectQuery(queryName);
    }

    /**
     * getDeleteQuery
     * @param queryName queryName
     * @return QuerySelect
     */
    public QuerySelect getDeleteQuery(String queryName) {
        QuerySelect querySelect;
        for (Map.Entry<String, QueryEntry> entry : mapQueryEntry.entrySet()) {
            querySelect = entry.getValue().getQueryDelete(queryName);
            if (querySelect != null)
                return querySelect;
        }
        return null;
    }

    /**
     * getUpdateQuery
     * @param queryName queryName
     * @return QuerySelect
     */
    public QuerySelect getUpdateQuery(String queryName) {
        QuerySelect querySelect;
        for (Map.Entry<String, QueryEntry> entry : mapQueryEntry.entrySet()) {
            querySelect = entry.getValue().getQueryUpdate(queryName);
            if (querySelect != null)
                return querySelect;
        }
        return null;
    }

    /**
     * getSelectQuery
     * @param queryName queryName
     * @return QuerySelect
     */
    public QuerySelect getSelectQuery(String queryName) {
        QuerySelect querySelect;
        for (Map.Entry<String, QueryEntry> entry : mapQueryEntry.entrySet()) {
            querySelect = entry.getValue().getQuerySelect(queryName);
            if (querySelect != null)
                return querySelect;
        }
        return null;
    }

    /**
     * getExceuteQuery
     * @param queryName queryName
     * @return QuerySelect
     */
    public QuerySelect getExceuteQuery(String queryName) {
        QuerySelect querySelect;
        for (Map.Entry<String, QueryEntry> entry : mapQueryEntry.entrySet()) {
            querySelect = entry.getValue().getQueryExecute(queryName);
            if (querySelect != null)
                return querySelect;
        }
        return null;
    }

    /**
     * getQuerySyntax
     * @param name name
     * @return QuerySyntax
     */
    public QuerySyntax getQuerySyntax(String name) {
        QuerySyntax querySyntax;
        for (Map.Entry<String, QueryEntry> entry : mapQueryEntry.entrySet()) {
            querySyntax = entry.getValue().getQuerySyntax(name);
            if (querySyntax != null)
                return querySyntax;
        }
        return null;
    }

    /**
     * getAllQuerySyntax
     * @return List
     */
    public List<QuerySyntax> getAllQuerySyntax() {
        List<QuerySyntax> querySyntaxs = new ArrayList<>();
        List<QuerySyntax> auxQuerySyntaxs;
        for (Map.Entry<String, QueryEntry> entry : mapQueryEntry.entrySet()) {
            auxQuerySyntaxs = entry.getValue().getAllQuerySyntax();
            if (auxQuerySyntaxs != null)
                querySyntaxs.addAll(auxQuerySyntaxs);
        }
        return querySyntaxs;
    }

    /**
     * getDataBaseName
     * @return String
     */
    public String getDataBaseName() {
        return this.databaseName;
    }

    /**
     * getQuerySyntaxValue
     * @param key key
     * @return String
     */
    public String getQuerySyntaxValue(String key) {
        List<QuerySyntax> list = getAllQuerySyntax();
        if (list == null)
            return "";
        for (QuerySyntax querySyntax : list) {
            if (querySyntax.getName().equals(key))
                return querySyntax.getValue();
        }
        return "";
    }

    /**
     * getReportQueries
     * @return List
     */
    public List<QuerySelect> getReportQueries() {
        List<QuerySelect> result = new ArrayList<>();
        for (Map.Entry<String, QueryEntry> entry : mapQueryEntry.entrySet()) {
            result.addAll(entry.getValue().getReportQueries());
        }
        return result;
    }
}
