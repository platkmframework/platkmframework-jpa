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
package org.platkmframework.jpa.mapping;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public interface JpaPropertyConstant {

    /**
     * ORG_PLATKMFRAMEWORK_AUDIT_JDBC_TABLENAME org.platkmframework.audit.jdbc.tablename
     */
    public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_TABLENAME = "org.platkmframework.audit.jdbc.tablename";

    /**
     * Attribute ORG_PLATKMFRAMEWORK_AUDIT_JDBC_URL
     */
    public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_URL = "org.platkmframework.audit.jdbc.url";

    /**
     * Attribute ORG_PLATKMFRAMEWORK_AUDIT_JDBC_DRIVER
     */
    public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_DRIVER = "org.platkmframework.audit.jdbc.driver";

    /**
     * Attribute ORG_PLATKMFRAMEWORK_AUDIT_JDBC_USER
     */
    public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_USER = "org.platkmframework.audit.jdbc.user";

    /**
     * Attribute ORG_PLATKMFRAMEWORK_AUDIT_JDBC_PASSWORD
     */
    public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_PASSWORD = "org.platkmframework.audit.jdbc.password";

    /**
     * QUERY MANAGER *
     */
    public static final String ORG_PLATKMFRAMEWORK_DATABASE_QUERYMANAGERS_PATH = "org.platkmframework.database.querymanagers.path";
    
    
    /**
     * Attribute ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX org.platkmframework.persistence
     */
    public static final String ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX = "org.platkmframework.persistence";
    

    /**
     * Attribute ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL_NAME show.sql
     */
    public static final String ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL_NAME = "show.sql";
    
    /**
     * Attribute ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL
     */
    public static final String ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL_NAME;
    
    
    /**
     * Attribute ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_MULTI_PREFIX org.platkmframework.persistence.multi
     */
    public static final String ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_MULTI_PREFIX = "org.platkmframework.persistence.multi";
    
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_URL_NAME url
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_URL_NAME = "url"; 
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_URL org.platkmframework.persistence.url
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_URL = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." +ORG_PLATKMFRAMEWORK_PERSISTENCE_URL_NAME;
    
    
    /**
     * public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD_NAME  = "password"
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD_NAME = "password"; 
    
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD org.platkmframework.persistence.password
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD_NAME;

    /**
     * public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_PASSWORD_NAME  = "user"
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_USER_NAME = "user"; 
    
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_USER org.platkmframework.persistence.user
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_USER = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + ORG_PLATKMFRAMEWORK_PERSISTENCE_USER_NAME;
    
    /**
     * public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER_NAME  = "driver"
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER_NAME = "driver"; 
    
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER org.platkmframework.persistence.driver
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER_NAME;
    
    
    /**
     * public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_DRIVER_NAME  = "auto.commit"
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_AUTO_COMMIT_NAME = "auto.commit"; 
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_AUTO_COMMIT org.platkmframework.persistence.auto.commit
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_AUTO_COMMIT = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + ORG_PLATKMFRAMEWORK_PERSISTENCE_AUTO_COMMIT_NAME;  
       
    
    /**
     * public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_DEFAULT_AUTO_COMMIT_NAME  = "default.auto.commit"
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_DEFAULT_AUTO_COMMIT_NAME = "default.auto.commit"; 
    
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_DEFAULT_AUTO_COMMIT org.platkmframework.persistence.default.auto.commit
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_DEFAULT_AUTO_COMMIT = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + ORG_PLATKMFRAMEWORK_PERSISTENCE_DEFAULT_AUTO_COMMIT_NAME;
    
    /**
     * public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE_NAME  = "minidle"
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE_NAME = "minidle";
    
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE org.platkmframework.persistence.minidle
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE_NAME;
    
    /**
     * public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXIDLE_NAME  = "maxidle"
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXIDLE_NAME = "maxidle";
    
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_MINIDLE org.platkmframework.persistence.maxidle
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXIDLE = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXIDLE_NAME;
    
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXOPEN org.platkmframework.persistence.maxopen
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MAXOPEN = "org.platkmframework.persistence.maxopen"; 
    
    
    /**
     * public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MAX_TOTAL_NAME  = "max.total"
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MAX_TOTAL_NAME = "max.total";
    
    /**
     * ORG_PLATKMFRAMEWORK_PERSISTENCE_MAX_TOTAL org.platkmframework.persistence.max.total
     */
    public static final String ORG_PLATKMFRAMEWORK_PERSISTENCE_MAX_TOTAL = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + ORG_PLATKMFRAMEWORK_PERSISTENCE_MAX_TOTAL_NAME;
       
    /**
     * C_DEFAULT_PERSISTENCE_NAME org.platkmframework.default.persistence.name
     */
	public static final String C_DEFAULT_PERSISTENCE_NAME = "org.platkmframework.default.persistence.name";

    /**
     * public static final String C_PERSISTENCE_TRANSACTION_TYPE_NAME  = "transaction.type"
     */
    public static final String C_PERSISTENCE_TRANSACTION_TYPE_NAME = "transaction.type";
    
	/**
	 * C_PERSISTENCE_TRANSACTION_TYPE org.platkmframework.default.persistence.transaction.type
	 */
	public static final String C_PERSISTENCE_TRANSACTION_TYPE = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + C_PERSISTENCE_TRANSACTION_TYPE_NAME;

	/**
	 * C_PERSISTENCE_DEFAULT_TRANSACTION_TYPE RESOURCE_LOCAL
	 */
	public static final String C_PERSISTENCE_DEFAULT_TRANSACTION_TYPE = "RESOURCE_LOCAL";

    /**
     * public static final String C_PERSISTENCE_PROVIDER_NAME  = "transaction.type"
     */
    public static final String C_PERSISTENCE_PROVIDER_NAME = "provider";
	/**
	 * C_PERSISTENCE_PROVIDER org.platkmframework.persistence.transaction.type
	 */
	public static final String C_PERSISTENCE_PROVIDER = ORG_PLATKMFRAMEWORK_JPA_PERSISTENCE_PREFIX + "." + C_PERSISTENCE_PROVIDER_NAME;
	
	/**
	 * C_PERSISTENCE_DEFAULT_PROVIDER org.platkmframework.jpa.orm.provider.PlatkmORMPersistenceProvider
	 */
	public static final String C_PERSISTENCE_DEFAULT_PROVIDER = "org.platkmframework.jpa.orm.provider.PlatkmORMPersistenceProvider";

	

	

	

    
}
