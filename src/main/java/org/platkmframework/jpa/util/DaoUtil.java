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
package org.platkmframework.jpa.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.platkmframework.database.query.common.ColumnInfoValue;
import org.platkmframework.util.Util;
import org.platkmframework.util.error.InvocationException;
import org.platkmframework.util.reflection.ReflectionUtil;
 


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class DaoUtil {

	
	public static String _valoresIN(String valor) 
	{
		String stringIn = "";
		String coma     = "";
		String[] valoresIn = valor.toString().split(",");
		if(valoresIn != null)
			for (int i = 0; i < valoresIn.length; i++) 
			{
				stringIn+= coma + "?";
				coma=",";
			}
		return stringIn;
	}
	
	public static String _valoresIN(List<?>  valor) 
	{
		String stringIn = "";
		String coma     = ""; 
		if(valor != null)
			for (int i = 0; i < valor.size(); i++) 
			{
				stringIn+= coma + "?";
				coma=",";
			}
		return stringIn;
	}
	
	public static String getTableName(Class<?> class1) 
	{ 	
		if(class1.isAnnotationPresent(Table.class))
			return class1.getAnnotation(Table.class).name();
		return class1.getSimpleName().toLowerCase();
	}

	public static String getColumns(Class<?> entityClass) {
		
		List<Field> fields = ReflectionUtil.getAllFieldHeritage(entityClass);
		String columns = "";
		Column column;
		String coma = "";
		for (Field field : fields){
			if(field.isAnnotationPresent(Column.class)){
				column = field.getAnnotation(Column.class);
				columns+= coma + column.name();
				coma = ","; 	
			}
		}
		return columns;
	}
	
	public static List<String> getColumnsName(List<ColumnInfoValue> coumnsInfoValue){
		return coumnsInfoValue.stream()
            .map(ColumnInfoValue::getName)
            .collect(Collectors.toList());
	 
	}
	
	public static String getColumnsNameString(List<ColumnInfoValue> coumnsInfoValue){
		List<String> namesList = coumnsInfoValue.stream()
            .map(ColumnInfoValue::getName)
            .collect(Collectors.toList());
	
		return Util.listToStringComa(namesList);
	}

	public static boolean isEntityAnnotationPresent(Object entity) {
		return entity.getClass().isAnnotationPresent(Entity.class);
	}

	public static boolean isEntityIdFieldWithValue(Object entity) {
		List<Field> fields = ReflectionUtil.getAllFieldHeritage(entity.getClass());
		for (Field field : fields){
			if(field.isAnnotationPresent(Id.class)){
				try {
					Object value = ReflectionUtil.getAttributeValue(entity, field);
					return value != null && StringUtils.isNotBlank(value.toString());
				} catch (InvocationException e) { 
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	public static String getIdColumnName(Class<?> entityClass) {
		List<Field> fields = ReflectionUtil.getAllFieldHeritage(entityClass);
		for (Field field : fields){
			if(field.isAnnotationPresent(Id.class)) return field.getAnnotation(Column.class).name(); 
		}
		return null;
	}

	public static  Object getValueFromIdColumn(Object entity) {
		try {
			List<Field> fields = ReflectionUtil.getAllFieldHeritage(entity.getClass());
			for (Field field : fields){
				if(field.isAnnotationPresent(Id.class)) {
					return ReflectionUtil.getAttributeValue(entity, field);
				}
			} 
		} catch (InvocationException e) { 
			e.printStackTrace();
		}
		return null;
	}
}
