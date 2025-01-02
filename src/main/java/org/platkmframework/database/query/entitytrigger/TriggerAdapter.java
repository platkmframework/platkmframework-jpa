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

import org.platkmframework.database.query.common.exception.DaoException;


/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 *    @param <E> E
 */ 
public class TriggerAdapter<E> {

    /**
     * Constructor TriggerAdapter
     */
    public TriggerAdapter() {
    }

    /**
     * beforeInsert
     * @param entity entity
     * @throws DaoException DaoException
     */
    public void beforeInsert(E entity) throws DaoException {
    }

    /**
     * afterInsert
     * @param entity entity
     * @throws DaoException DaoException
     */
    public void afterInsert(E entity) throws DaoException {
    }

    /**
     * beforeUpdate
     * @param entity entity
     * @throws DaoException DaoException
     */
    public void beforeUpdate(E entity) throws DaoException {
    }

    /**
     * afterUpdate
     * @param entity entity
     * @throws DaoException DaoException
     */
    public void afterUpdate(E entity) throws DaoException {
    }

    /**
     * beforeDelete
     * @param entity entity
     * @throws DaoException DaoException
     */
    public void beforeDelete(E entity) throws DaoException {
    }

    /**
     * afterDelete
     * @param entity entity
     * @throws DaoException DaoException
     */
    public void afterDelete(E entity) throws DaoException {
    }
}
