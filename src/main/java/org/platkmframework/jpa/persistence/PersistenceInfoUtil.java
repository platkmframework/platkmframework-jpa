/**
 * ****************************************************************************
 *  Copyright(c) 2024 the original author Eduardo Iglesias Taylor.
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
import java.util.List;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class PersistenceInfoUtil {

    /**
     * Atributo persistenceInfoList
     */
    public List<PersistenceInfo> persistenceInfoList;

    /**
     * Atributo loaded
     */
    private boolean loaded = false;

    /**
     * Atributo notFound
     */
    private boolean notFound = false;

    /**
     * Atributo persistenceInfoUtil
     */
    private static PersistenceInfoUtil persistenceInfoUtil;

    /**
     * Constructor PersistenceInfoUtil
     */
    private PersistenceInfoUtil() {
        persistenceInfoList = new ArrayList<>();
    }

    /**
     * instance
     * @return PersistenceInfoUtil
     */
    public static PersistenceInfoUtil instance() {
        if (persistenceInfoUtil == null)
            persistenceInfoUtil = new PersistenceInfoUtil();
        return persistenceInfoUtil;
    }

    /**
     * getPersistenceInfoList
     * @return List
     */
    public List<PersistenceInfo> getPersistenceInfoList() {
        return persistenceInfoList;
    }

    /**
     * setPersistenceInfoList
     * @param persistenceInfoList persistenceInfoList
     */
    public void setPersistenceInfoList(List<PersistenceInfo> persistenceInfoList) {
        this.persistenceInfoList = persistenceInfoList;
    }

    /**
     * setLoaded
     * @param loaded loaded
     */
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    /**
     * isLoaded
     * @return boolean
     */
    protected boolean isLoaded() {
        return loaded;
    }

    /**
     * isNotFound
     * @return boolean
     */
    public boolean isNotFound() {
        return notFound;
    }

    /**
     * setNotFound
     * @param notFound notFound
     */
    public void setNotFound(boolean notFound) {
        this.notFound = notFound;
    }
}
