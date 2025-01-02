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
package org.platkmframework.jpa.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.platkmframework.database.query.manager.QueryManager;
import org.platkmframework.jpa.mapping.DatabaseMapper;
import org.platkmframework.jpa.processor.SqlSentencesProcessor;
import org.platkmframework.util.DataTypeUtil;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class PersistenceUnit {

    /**
     * Atributo name
     */
    private String name;

    /**
     * Atributo transactionType
     */
    private String transactionType;

    /**
     * Atributo provider
     */
    private String provider;

    /**
     * Atributo properties
     */
    private Map<String, Object> properties;

    /**
     * Atributo classes
     */
    private List<String> classes;

    /**
     * Atributo databaseMapper
     */
    private DatabaseMapper databaseMapper;

    /**
     * Atributo queryManager
     */
    private QueryManager queryManager;

    /**
     * Atributo sqlSentencesProcessor
     */
    private SqlSentencesProcessor sqlSentencesProcessor;

    /**
     * Constructor PersistenceUnit
     */
    public PersistenceUnit() {
        super();
    }

    /**
     * getProvider
     * @return String
     */
    public String getProvider() {
        return provider;
    }

    /**
     * setProvider
     * @param provider provider
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * getProperties
     * @return Map
     */
    public Map<String, Object> getProperties() {
        if (properties == null)
            properties = new HashMap<>();
        return properties;
    }

    /**
     * setProperties
     * @param properties properties
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * getName
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getTransactionType
     * @return String
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * setTransactionType
     * @param transactionType transactionType
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * getClasses
     * @return List
     */
    public List<String> getClasses() {
        if (classes == null)
            classes = new ArrayList<>();
        return classes;
    }

    /**
     * setClasses
     * @param classes classes
     */
    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    /**
     * getDatabaseMapper
     * @return DatabaseMapper
     */
    public DatabaseMapper getDatabaseMapper() {
        return databaseMapper;
    }

    /**
     * setDatabaseMapper
     * @param databaseMapper databaseMapper
     */
    public void setDatabaseMapper(DatabaseMapper databaseMapper) {
        this.databaseMapper = databaseMapper;
    }

    /**
     * getQueryManager
     * @return QueryManager
     */
    public QueryManager getQueryManager() {
        return queryManager;
    }

    /**
     * setQueryManager
     * @param queryManager queryManager
     */
    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    /**
     * getStringPropertyValue
     * @param key key
     * @return String
     */
    public String getStringPropertyValue(String key) {
        return DataTypeUtil.getStringValue(properties.get(key), "");
    }

    /**
     * getSqlSentencesProcessor
     * @return SqlSentencesProcessor
     */
    public SqlSentencesProcessor getSqlSentencesProcessor() {
        return sqlSentencesProcessor;
    }

    /**
     * setSqlSentencesProcessor
     * @param sqlSentencesProcessor sqlSentencesProcessor
     */
    public void setSqlSentencesProcessor(SqlSentencesProcessor sqlSentencesProcessor) {
        this.sqlSentencesProcessor = sqlSentencesProcessor;
    }
}
