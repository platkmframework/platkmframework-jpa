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
package org.platkmframework.database.query.common;

import java.io.Serializable;

import org.platkmframework.annotation.db.SystemColumnAction;

import jakarta.persistence.Column;


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class ColumnInfoValue implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	private String name;
	private boolean unique = false;
	private boolean nullable = true;
	private boolean insertable = true;
	private boolean updatable = true;
	private String columnDefinition;
	private String table;
	int length =  255;
	int precision = 0;
	private int scale = 0;
	
	private String label;
	private String code;
	private Class<?> classType;
	private String classFieldName;
	private String description;
	private String tagtype;  
	private int minlength;
	Class<?>[] json;
	boolean pk;
	boolean increment;
	
	private Object value; 
	
	@SuppressWarnings("rawtypes")
	private Class  converter;
	
	private SystemColumnAction systemColumnAction;
	private String systemColumnKey;
	
	  
	public ColumnInfoValue(Object value, Class<?> classType, boolean pk, boolean increment) {
		this.value = value;
		this.classType = classType;
		this.pk = pk;
		this.increment = increment;
	}

	public ColumnInfoValue(String name, Class<?> classType, Object value, String tagtype, boolean pk, boolean increment) {
		this.name = name;
		this.classType = classType;
		this.value = value;
		this.tagtype = tagtype;
		this.pk = pk;
		this.increment = increment;
	}

	public void set(Column column, boolean pk, boolean increment) {
		this.name = column.name();
		this.unique = column.unique();
		this.nullable = column.nullable(); 
		this.insertable = column.insertable();
		this.updatable = column.updatable();
		this.columnDefinition = column.columnDefinition();
		this.table = column.table();
		this.length = column.length();
		this.precision = column.precision();
		this.scale = column.scale(); 
		this.pk = pk;
		this.increment = increment;
	}
 

	public static Class<?> getLanguageType(String languageType)  {
   	 
		try {
			return Class.forName(languageType);
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
			return null;
		}
		 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public boolean isInsertable() {
		return insertable;
	}

	public void setInsertable(boolean insertable) {
		this.insertable = insertable;
	}

	public boolean isUpdatable() {
		return updatable;
	}

	public void setUpdatable(boolean updatable) {
		this.updatable = updatable;
	}

	public String getColumnDefinition() {
		return columnDefinition;
	}

	public void setColumnDefinition(String columnDefinition) {
		this.columnDefinition = columnDefinition;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Class<?> getClassType() {
		return classType;
	}

	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTagtype() {
		return tagtype;
	}

	public void setTagtype(String tagtype) {
		this.tagtype = tagtype;
	}

	public int getMinlength() {
		return minlength;
	}

	public void setMinlength(int minlength) {
		this.minlength = minlength;
	}

	public Class<?>[] getJson() {
		return json;
	}

	public void setJson(Class<?>[] json) {
		this.json = json;
	} 

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public boolean isIncrement() {
		return increment;
	}

	public void setIncrement(boolean increment) {
		this.increment = increment;
	}

	public String getClassFieldName() {
		return classFieldName;
	}

	public void setClassFieldName(String classFieldName) {
		this.classFieldName = classFieldName;
	}

	@SuppressWarnings("rawtypes")
	public Class getConverter() {
		return converter;
	}

	public void setConverter(@SuppressWarnings("rawtypes") Class converter) {
		this.converter = converter;
	}

	public SystemColumnAction getSystemColumnAction() {
		return systemColumnAction;
	}

	public void setSystemColumnAction(SystemColumnAction systemColumnAction) {
		this.systemColumnAction = systemColumnAction;
	}

	public String getSystemColumnKey() {
		return systemColumnKey;
	}

	public void setSystemColumnKey(String systemColumnKey) {
		this.systemColumnKey = systemColumnKey;
	}
	
}
