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
public class TriggerExecutor {

    /**
     * Constructor TriggerExecutor
     */
    public TriggerExecutor() {
    }

    /**
     * beforeRead
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public static void beforeRead(String tableName, List<ColumnInfoValue> columns) throws DaoException {
        TableTriggerAdapter tableTriggerAdapter = TriggerHandler.getTrigger(tableName);
        if (tableTriggerAdapter != null)
            tableTriggerAdapter.beforeRead(tableName, columns);
    }

    /**
     * afterRead
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public static void afterRead(String tableName, List<ColumnInfoValue> columns) throws DaoException {
        TableTriggerAdapter tableTriggerAdapter = TriggerHandler.getTrigger(tableName);
        if (tableTriggerAdapter != null)
            tableTriggerAdapter.afterRead(tableName, columns);
    }

    /**
     * beforeInsert
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public static void beforeInsert(String tableName, List<ColumnInfoValue> columns) throws DaoException {
        TableTriggerAdapter tableTriggerAdapter = TriggerHandler.getTrigger(tableName);
        if (tableTriggerAdapter != null)
            tableTriggerAdapter.beforeInsert(tableName, columns);
    }

    /**
     * afterInsert
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public static void afterInsert(String tableName, List<ColumnInfoValue> columns) throws DaoException {
        TableTriggerAdapter tableTriggerAdapter = TriggerHandler.getTrigger(tableName);
        if (tableTriggerAdapter != null)
            tableTriggerAdapter.afterInsert(tableName, columns);
    }

    /**
     * beforeUpdate
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public static void beforeUpdate(String tableName, List<ColumnInfoValue> columns) throws DaoException {
        TableTriggerAdapter tableTriggerAdapter = TriggerHandler.getTrigger(tableName);
        if (tableTriggerAdapter != null)
            tableTriggerAdapter.beforeUpdate(tableName, columns);
    }

    /**
     * afterUpdate
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public static void afterUpdate(String tableName, List<ColumnInfoValue> columns) throws DaoException {
        TableTriggerAdapter tableTriggerAdapter = TriggerHandler.getTrigger(tableName);
        if (tableTriggerAdapter != null)
            tableTriggerAdapter.afterUpdate(tableName, columns);
    }

    /**
     * afterDelete
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public static void afterDelete(String tableName, List<ColumnInfoValue> columns) throws DaoException {
        TableTriggerAdapter tableTriggerAdapter = TriggerHandler.getTrigger(tableName);
        if (tableTriggerAdapter != null)
            tableTriggerAdapter.afterDelete(tableName, columns);
    }

    /**
     * beforeDelete
     * @param tableName tableName
     * @param columns columns
     * @throws DaoException DaoException
     */
    public static void beforeDelete(String tableName, List<ColumnInfoValue> columns) throws DaoException {
        TableTriggerAdapter tableTriggerAdapter = TriggerHandler.getTrigger(tableName);
        if (tableTriggerAdapter != null)
            tableTriggerAdapter.beforeDelete(tableName, columns);
    }
}
