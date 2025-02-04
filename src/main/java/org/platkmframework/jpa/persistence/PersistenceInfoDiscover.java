/**
 * ****************************************************************************
 *  Copyright(c) 2025 the original author Eduardo Iglesias Taylor.
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
import java.util.Map;
import java.util.Properties;

import org.platkmframework.context.project.ProjectContent;
import org.platkmframework.jpa.exception.PlatkmJpaException;
import org.platkmframework.jpa.mapping.JpaPropertyConstant;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class PersistenceInfoDiscover {
	
	
	/**
	 * search
	 * @return List
	 * @throws PlatkmJpaException PlatkmJpaException
	 */
	 public List<PersistenceInfo> search() throws PlatkmJpaException {
		 
		 Properties properties = ProjectContent.instance().getPropertiesWithPrefix(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX);
		 List<PersistenceInfo> list = new ArrayList<>();
		 list.add(getDeaultPersistenceInfo(properties));
		 
		 properties = ProjectContent.instance().getPropertiesWithPrefix(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_MULTI_PREFIX);
		 
		 String[] splitKey; 
		 List<String> mutiListNames = new ArrayList<>();
		  
		 for (Map.Entry<Object, Object> e : properties.entrySet()) {
			 splitKey = e.getKey().toString().split(".");
			 if(splitKey.length > 5 && (!mutiListNames.contains(splitKey[4]))) {
				 mutiListNames.add(splitKey[4]);
				 list.add(getMultiPersistenceInfo(properties, splitKey[4]));
			 }
        }
		 return list;
	 }

	 /**
	  * get Deault Persistence Info
	  * @param properties properties
	  * @return PersistenceInfo
	  */
	 private PersistenceInfo getDeaultPersistenceInfo(Properties properties) {
		
		 PersistenceInfo persistenceInfo = new PersistenceInfo();
		 persistenceInfo.setName(JpaPropertyConstant.C_DEFAULT_PERSISTENCE_NAME);
         persistenceInfo.setTransactionType(properties.getProperty(JpaPropertyConstant.C_PERSISTENCE_TRANSACTION_TYPE, JpaPropertyConstant.C_PERSISTENCE_DEFAULT_TRANSACTION_TYPE));
         persistenceInfo.setProvider(properties.getProperty(JpaPropertyConstant.C_PERSISTENCE_PROVIDER, JpaPropertyConstant.C_PERSISTENCE_DEFAULT_PROVIDER));
         
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_URL, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_URL,""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_USER, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_USER, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD,""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_AUTO_COMMIT, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_AUTO_COMMIT, Boolean.FALSE.toString()));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_DEFAULT_AUTO_COMMIT, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_DEFAULT_AUTO_COMMIT, Boolean.FALSE.toString()));
         
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXIDLE, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXIDLE, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MAX_TOTAL, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MAX_TOTAL, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL, properties.getProperty(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL, Boolean.TRUE.toString()));
           
         return persistenceInfo;
	}

	 /**
	  * get Multi Persistence Info
	  * @param properties properties
	  * @param multiPrefix multi Prefix
	  * @return PersistenceInfo
	  */
	 private PersistenceInfo getMultiPersistenceInfo(Properties properties, String multiPersistenceName) {
		
		 String prefixMutli = JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_MULTI_PREFIX + "." + multiPersistenceName + ".";
		 PersistenceInfo persistenceInfo = new PersistenceInfo();
		 persistenceInfo.setName(multiPersistenceName);
         persistenceInfo.setTransactionType(properties.getProperty(prefixMutli + "." + JpaPropertyConstant.C_PERSISTENCE_TRANSACTION_TYPE_NAME, JpaPropertyConstant.C_PERSISTENCE_DEFAULT_TRANSACTION_TYPE));
         persistenceInfo.setProvider(properties.getProperty(prefixMutli + "." + JpaPropertyConstant.C_PERSISTENCE_PROVIDER_NAME, JpaPropertyConstant.C_PERSISTENCE_DEFAULT_PROVIDER));
         
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_URL, properties.getProperty(prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_URL_NAME, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_USER, properties.getProperty(prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_USER_NAME, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD, properties.getProperty(prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD_NAME,""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER, properties.getProperty(prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER_NAME, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_AUTO_COMMIT, properties.getProperty(prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_AUTO_COMMIT_NAME, Boolean.FALSE.toString()));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_DEFAULT_AUTO_COMMIT, properties.getProperty(prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_DEFAULT_AUTO_COMMIT_NAME, Boolean.FALSE.toString()));
         
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE, properties.getProperty(prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE_NAME, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXIDLE, properties.getProperty(prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXIDLE_NAME, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MAX_TOTAL, properties.getProperty(prefixMutli + prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_PERSISTENCE_MAX_TOTAL_NAME, ""));
         persistenceInfo.getProperties().put(JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL, properties.getProperty(properties.getProperty(prefixMutli + "." + JpaPropertyConstant.ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL_NAME, ""), Boolean.TRUE.toString()));
           
         return persistenceInfo;
	}

}
