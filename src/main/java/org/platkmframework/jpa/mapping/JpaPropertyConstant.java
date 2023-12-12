/*******************************************************************************
 * Copyright(c) 2023 the original author Eduardo Iglesias Taylor.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	 https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * 	Eduardo Iglesias Taylor - initial API and implementation
 *******************************************************************************/
package org.platkmframework.jpa.mapping;

public interface JpaPropertyConstant {
	
	
	/** AUDIT**/
	public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_TABLENAME = "org.platkmframework.audit.jdbc.tablename";
	public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_URL 		= "org.platkmframework.audit.jdbc.url";
	public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_DRIVER 	= "org.platkmframework.audit.jdbc.driver";
	public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_USER 		= "org.platkmframework.audit.jdbc.user";
	public static final String ORG_PLATKMFRAMEWORK_AUDIT_JDBC_PASSWORD 	= "org.platkmframework.audit.jdbc.password";
 
	/** QUERY MANAGER **/
	public static final String ORG_PLATKMFRAMEWORK_DATABASE_QUERYMANAGERS_PATH	= "org.platkmframework.database.querymanagers.path";
	
	public static final String ORG_PLATKMFRAMEWORK_JPA_SHOW_SQL    	            = "org.platkmframework.jpa.show.sql";

}
