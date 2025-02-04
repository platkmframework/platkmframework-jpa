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

import org.platkmframework.context.ObjectContainer;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class TriggerHandler {

	
	/**
	 * TriggerHandler
	 */
	  private TriggerHandler() {
	    throw new IllegalStateException("TriggerHandler class");
	  }
	  
    /**
     * 
     * getTrigger
     * @param <E> E
     * @param entityClass entityClass
     * @return TriggerAdapter
     */
    public static <E> TriggerAdapter<E> getTrigger(Class<E> entityClass) {
        List<Object> list = ObjectContainer.instance().getListObjectByAnnontation(EntityTrigger.class);
        if (list != null) {
            TriggerAdapter<E> triggerAdapter = null;
            Class<?> triggerClass = null;
            for (Object object : list) {
                if (object instanceof TriggerAdapter) {
                    triggerAdapter = TriggerAdapter.class.cast(object);
                    triggerClass = triggerAdapter.getClass().getAnnotation(EntityTrigger.class).entity();
                    if (triggerClass.equals(entityClass)) {
                        return triggerAdapter;
                    }
                }
            }
        }
        return null;
    }

    /**
     * getTrigger
     * @param tableName tableName
     * @return TableTriggerAdapter
     */
    public static TableTriggerAdapter getTrigger(String tableName) {
        List<Object> list = ObjectContainer.instance().getListObjectByAnnontation(TableTrigger.class);
        if (list != null) {
            TableTriggerAdapter tableTriggerAdapter = null;
            String annTableName;
            for (Object object : list) {
                if (object instanceof TableTriggerAdapter) {
                    tableTriggerAdapter = (TableTriggerAdapter) object;
                    annTableName = tableTriggerAdapter.getClass().getAnnotation(TableTrigger.class).table();
                    if (annTableName.equals(tableName)) {
                        return tableTriggerAdapter;
                    }
                }
            }
        }
        return null;
    }
}
