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
package org.platkmframework.database.query.manager;
 
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.platkmframework.database.query.manager.model.DataBase;
import org.platkmframework.database.query.manager.model.ProjectQueriesContainer;
import org.platkmframework.database.query.manager.model.QueriesGroup;
import org.platkmframework.database.query.manager.model.QuerySelect;
import org.platkmframework.database.query.manager.model.QuerySyntax;
import org.platkmframework.util.manager.ManagerBase;
import org.platkmframework.util.manager.ManagerException;  


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class QueryEntry extends ManagerBase<ProjectQueriesContainer>{
	 
	
	private ProjectQueriesContainer projectQueriesContainer; 
	private String absolutePath;
	private String databaseName;
	
	public QueryEntry(String databaseName){
		super(ProjectQueriesContainer.class); 
		this.databaseName = databaseName;
	}
  

	public QuerySelect getQuerySelect(String queryName)
	{
		return getQuerySelectByNameAndType(queryName, QueryEntryType.select.name());
	}
	
	public QuerySelect getQueryExecute(String queryName)
	{
		return getQuerySelectByNameAndType(queryName, QueryEntryType.execute.name());
	}
	
	public QuerySelect getQueryUpdate(String queryName)
	{
		return getQuerySelectByNameAndType(queryName, QueryEntryType.update.name());
	}
	
	public QuerySelect getQueryDelete(String queryName)
	{
		return getQuerySelectByNameAndType(queryName, QueryEntryType.delete.name());
	}
	
	public QuerySyntax getQuerySyntax(String name){
		
		List<QuerySyntax> list = getAllQuerySyntax();
		if(list == null || list.isEmpty()) return null;
		
		for (QuerySyntax querySyntax : list) 
			if(querySyntax.getName().equalsIgnoreCase(name))
				return querySyntax; 
		
		return null; 
	}
	 
	public QuerySelect getQuerySelectByNameAndType(String queryName, String type)
	{
		List<QueriesGroup> queiresGroupList= getQueriesByDataBaseName();
		for (QueriesGroup queriesGroup : queiresGroupList)
			for (QuerySelect querySelect : queriesGroup.getQuerySelect()) 
			{
				if(querySelect.getName().trim().equalsIgnoreCase(queryName.trim()) && type.equals(querySelect.getType().trim()))
					return querySelect;
			}
		return null;
	}
 	
	
	public ProjectQueriesContainer getProjectQueriesContainer() {
		return projectQueriesContainer;
	}
	
	public void setProjectQueriesContainer(ProjectQueriesContainer projectQueriesContainer) {
		this.projectQueriesContainer = projectQueriesContainer;
	}
	
	public String getAbsolutePath() {
		return absolutePath;
	}
	
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
 
	public void init(String absolutePath) throws ManagerException {
		this.absolutePath = absolutePath;
		this.projectQueriesContainer = super.readModel(absolutePath);  
	}
	
	public void init(InputStream inputStream) throws ManagerException {
		//this.absolutePath = absolutePath;
		this.projectQueriesContainer = super.readModel(inputStream);  
	}	
	
	
	public void writeModel(ProjectQueriesContainer model) throws ManagerException 
	{
		super.writeModel(this.absolutePath, model);
		this.projectQueriesContainer = model;
	}
	 
	private List<QueriesGroup> getQueriesByDataBaseName() {
	 
		if(projectQueriesContainer != null)
			for (DataBase dataBase : projectQueriesContainer.getDataBase()) {
				if(StringUtils.isNotEmpty(databaseName) && databaseName.trim().equalsIgnoreCase(dataBase.getName().trim()))
					return dataBase.getQueriesGroup();
			}
		return new ArrayList<QueriesGroup>();
	}	 
	
	public List<QuerySyntax> getAllQuerySyntax() {
		if(projectQueriesContainer != null)
			for (DataBase dataBase : projectQueriesContainer.getDataBase()) {
				if(StringUtils.isNotEmpty(databaseName) && databaseName.trim().equalsIgnoreCase(dataBase.getName().trim()))
					return dataBase.getQuerySyntax();
			}
		return null;
	}
	 
	public ProjectQueriesContainer get()
	{
		return this.projectQueriesContainer;
	}
	 
	public List<QuerySelect> getReportQueries()
	{
		List<QueriesGroup> queiresGroupList= getQueriesByDataBaseName();
		List<QuerySelect> result = new ArrayList<>();
		for (QueriesGroup queriesGroup : queiresGroupList)
			for (QuerySelect querySelect : queriesGroup.getQuerySelect()) 
			{
				if(querySelect.isReport()) result.add(querySelect);
			}
		return result;
	}	
}
