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
package org.platkmframework.database.query.entitytrigger;

import java.util.List;
import org.platkmframework.database.query.common.ColumnInfoValue;
import org.platkmframework.database.query.common.exception.DaoException;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class TableTriggerAdapter {

    /**
     * Constructor TableTriggerAdapter
     */
    public TableTriggerAdapter() {
    }

    /**
     * beforeRead
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public void beforeRead(String tableName, List<ColumnInfoValue> columns) throws DaoException {
    }

    /**
     * afterRead
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public void afterRead(String tableName, List<ColumnInfoValue> columns) throws DaoException {
    }

    /**
     * beforeInsert
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public void beforeInsert(String tableName, List<ColumnInfoValue> columns) throws DaoException {
    }

    /**
     * afterInsert
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public void afterInsert(String tableName, List<ColumnInfoValue> columns) throws DaoException {
    }

    /**
     * beforeUpdate
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public void beforeUpdate(String tableName, List<ColumnInfoValue> columns) throws DaoException {
    }

    /**
     * afterUpdate
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public void afterUpdate(String tableName, List<ColumnInfoValue> columns) throws DaoException {
    }

    /**
     * beforeDelete
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public void beforeDelete(String tableName, List<ColumnInfoValue> columns) throws DaoException {
    }

    /**
     * afterDelete
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public void afterDelete(String tableName, List<ColumnInfoValue> columns) throws DaoException {
    }

    /**
     * getFieldValue
     * @param columns columns
     * @param fieldName fieldName
     * @return ColumnInfoValue
     */
    protected ColumnInfoValue getFieldValue(List<ColumnInfoValue> columns, String fieldName) {
        for (ColumnInfoValue columnInfoValue : columns) {
            if (columnInfoValue.getName().equalsIgnoreCase(fieldName)) {
                return columnInfoValue;
            }
        }
        return null;
    }

    /**
     * getFieldValueByCode
     * @param columns columns
     * @param code code
     * @return ColumnInfoValue
     */
    protected ColumnInfoValue getFieldValueByCode(List<ColumnInfoValue> columns, String code) {
        for (ColumnInfoValue columnInfoValue : columns) {
            if (columnInfoValue.getCode().equalsIgnoreCase(code)) {
                return columnInfoValue;
            }
        }
        return null;
    }

    /**
     * setFieldValue
     * @param columns columns
     * @param fieldName fieldName
     * @param value value
     */
    protected void setFieldValue(List<ColumnInfoValue> columns, String fieldName, Object value) {
        ColumnInfoValue columnInfoValue = getFieldValue(columns, fieldName);
        if (columnInfoValue != null)
            columnInfoValue.setValue(value);
    }
}
