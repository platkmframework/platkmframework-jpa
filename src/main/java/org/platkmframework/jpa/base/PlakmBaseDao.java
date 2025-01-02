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
package org.platkmframework.jpa.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.platkmframework.annotation.db.SystemColumn;
import org.platkmframework.annotation.db.SystemColumnAction;
import org.platkmframework.annotation.db.SystemColumnValue;
import org.platkmframework.content.ObjectContainer;
import org.platkmframework.database.query.common.ColumnInfoValue;
import org.platkmframework.database.query.common.exception.DaoException;
import org.platkmframework.doi.data.BeanMethodInfo;
import org.platkmframework.jpa.exception.DatabaseConnectionException;
import org.platkmframework.jpa.exception.DatabaseValidationException;
import org.platkmframework.jpa.persistence.PersistenceUnit;
import org.platkmframework.jpa.util.DaoUtil;
import org.platkmframework.persistence.filter.criteria.FilterCriteria;
import org.platkmframework.persistence.filter.info.FilterData;
import org.platkmframework.persistence.filter.info.FilterDataType;
import org.platkmframework.util.error.InvocationException;
import org.platkmframework.util.reflection.ReflectionUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public abstract class PlakmBaseDao {

    /**
     * Atributo persistenceUnit
     */
    protected PersistenceUnit persistenceUnit;

    /**
     * Constructor PlakmBaseDao
     * @param persistenceUnit persistenceUnit
     */
    public PlakmBaseDao(PersistenceUnit persistenceUnit) {
        super();
        this.persistenceUnit = persistenceUnit;
    }

    /**
     * getColumInfoValues
     * @param customEntityBase customEntityBase
     * @param systemColumnAction systemColumnAction
     * @return List
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws InvocationException InvocationException
     */
    protected List<ColumnInfoValue> getColumInfoValues(Object customEntityBase, SystemColumnAction systemColumnAction) throws ClassNotFoundException, InvocationException {
        List<ColumnInfoValue> columns = new ArrayList<>();
        List<Field> fields = ReflectionUtil.getAllFieldHeritage(customEntityBase.getClass());
        Column column;
        ColumnInfoValue columnInfoValue;
        Method method;
        BeanMethodInfo beanMethodInfo;
        boolean isPk;
        boolean isIncrement;
        Object objValue;
        Class<?> returnType;
        String key;
        Field field;
        for (int i = 0; i < fields.size(); i++) {
            key = "";
            field = fields.get(i);
            if (field.isAnnotationPresent(Column.class)) {
                column = field.getAnnotation(Column.class);
                isPk = field.isAnnotationPresent(Id.class);
                isIncrement = field.isAnnotationPresent(GeneratedValue.class);
                if (field.isAnnotationPresent(SystemColumn.class) && (field.getAnnotation(SystemColumn.class).action().name().equalsIgnoreCase(systemColumnAction.name()) || field.getAnnotation(SystemColumn.class).action().name().equalsIgnoreCase(SystemColumnAction.BOTH.name()))) {
                    key = field.getAnnotation(SystemColumn.class).key();
                    beanMethodInfo = getColumnSystemMethodByKey(key);
                    objValue = getColumnSystemValue(beanMethodInfo.getMethod(), beanMethodInfo.getObj());
                    returnType = beanMethodInfo.getMethod().getReturnType();
                    ReflectionUtil.setAttributeValue(customEntityBase, field, objValue, false);
                } else {
                    method = ReflectionUtil.getAtributeGETmethod(customEntityBase, field);
                    objValue = ReflectionUtil.invokeMethod(customEntityBase, method, null);
                    returnType = method.getReturnType();
                }
                columnInfoValue = new ColumnInfoValue(objValue, returnType, isPk, isIncrement);
                columnInfoValue.set(column, isPk, isIncrement);
                columnInfoValue.setClassType(field.getType());
                columnInfoValue.setClassFieldName(field.getName());
                columnInfoValue.setConverter((field.isAnnotationPresent(Convert.class) ? field.getAnnotation(Convert.class).converter() : null));
                columns.add(columnInfoValue);
            }
        }
        return columns;
    }

    /**
     * getColumnSystemMethodByKey
     * @param key key
     * @return BeanMethodInfo
     * @throws InvocationException InvocationException
     */
    protected BeanMethodInfo getColumnSystemMethodByKey(String key) throws InvocationException {
        if (StringUtils.isBlank(key))
            return null;
        List<BeanMethodInfo> list = ObjectContainer.instance().getBeansMethodByAnnotation(SystemColumnValue.class);
        for (BeanMethodInfo beanMethodInfo : list) {
            if (beanMethodInfo.getMethod().getAnnotation(SystemColumnValue.class).name().equalsIgnoreCase(key)) {
                return beanMethodInfo;
            }
        }
        return null;
    }

    /**
     * getColumnSystemValue
     * @param method method
     * @param obj obj
     * @return Object
     * @throws InvocationException InvocationException
     */
    protected Object getColumnSystemValue(Method method, Object obj) throws InvocationException {
        try {
            return method.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InvocationException(e);
        }
    }

    /**
     * getColumInfoValue
     * @param customEntityBase customEntityBase
     * @param columnName columnName
     * @param systemColumnAction systemColumnAction
     * @return ColumnInfoValue
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws InvocationException InvocationException
     */
    protected ColumnInfoValue getColumInfoValue(Object customEntityBase, String columnName, SystemColumnAction systemColumnAction) throws ClassNotFoundException, InvocationException {
        ColumnInfoValue columnInfoValue;
        ;
        List<Field> fields = ReflectionUtil.getAllFieldHeritage(customEntityBase.getClass());
        Column column;
        Method method;
        BeanMethodInfo beanMethodInfo;
        boolean isPk;
        boolean isIncrement;
        Object objValue;
        Class<?> returnType;
        String key;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                column = field.getAnnotation(Column.class);
                if (column.name().equals(columnName)) {
                    method = ReflectionUtil.getAtributeGETmethod(customEntityBase, field);
                    isPk = field.isAnnotationPresent(Id.class);
                    isIncrement = field.isAnnotationPresent(GeneratedValue.class);
                    if (field.isAnnotationPresent(SystemColumn.class) && (field.getAnnotation(SystemColumn.class).action().name().equalsIgnoreCase(systemColumnAction.name()) || field.getAnnotation(SystemColumn.class).action().name().equalsIgnoreCase(SystemColumnAction.BOTH.name()))) {
                        key = field.getAnnotation(SystemColumn.class).key();
                        beanMethodInfo = getColumnSystemMethodByKey(key);
                        objValue = getColumnSystemValue(beanMethodInfo.getMethod(), beanMethodInfo.getObj());
                        returnType = beanMethodInfo.getMethod().getReturnType();
                        ReflectionUtil.setAttributeValue(customEntityBase, field, objValue, false);
                    } else {
                        method = ReflectionUtil.getAtributeGETmethod(customEntityBase, field);
                        objValue = ReflectionUtil.invokeMethod(customEntityBase, method, null);
                        returnType = method.getReturnType();
                    }
                    columnInfoValue = new ColumnInfoValue(objValue, returnType, isPk, isIncrement);
                    columnInfoValue.set(column, isPk, isIncrement);
                    columnInfoValue.setConverter((field.isAnnotationPresent(Convert.class) ? field.getAnnotation(Convert.class).converter() : null));
                    return columnInfoValue;
                }
            }
        }
        return null;
    }

    /**
     * getIdAnnotationValue
     * @param customEntityBase customEntityBase
     * @return Object
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws InvocationException InvocationException
     */
    protected Object getIdAnnotationValue(Object customEntityBase) throws ClassNotFoundException, InvocationException {
        List<Field> fields = ReflectionUtil.getAllFieldHeritage(customEntityBase.getClass());
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                return ReflectionUtil.getAttributeValue(customEntityBase, field);
            }
        }
        return null;
    }

    /**
     * _getUpdateIntoValues
     * @param columns columns
     * @return String
     */
    protected String _getUpdateIntoValues(List<ColumnInfoValue> columns) {
        String updateIntoValues = "";
        if (columns != null) {
            String aux = "";
            for (int i = 0; i < columns.size(); i++) {
                if (!columns.get(i).isIncrement() && !columns.get(i).isPk()) {
                    updateIntoValues += aux + columns.get(i).getName() + "=?";
                    aux = ",";
                }
            }
        }
        return updateIntoValues;
    }

    /**
     * _getInsertIntoFieldNames
     * @param columns columns
     * @return String
     */
    protected String _getInsertIntoFieldNames(List<ColumnInfoValue> columns) {
        String tableFieldName = "";
        if (columns != null) {
            String aux = "";
            for (int i = 0; i < columns.size(); i++) {
                if (!columns.get(i).isIncrement()) {
                    tableFieldName += aux + columns.get(i).getName();
                    aux = ",";
                }
            }
        }
        return tableFieldName;
    }

    /**
     * _getInsertIntoValues
     * @param columns columns
     * @return Object
     */
    protected Object _getInsertIntoValues(List<ColumnInfoValue> columns) {
        String insertIntoValues = "";
        if (columns != null) {
            String aux = "";
            for (int i = 0; i < columns.size(); i++) if (!columns.get(i).isIncrement()) {
                insertIntoValues += aux + "?";
                aux = ",";
            }
        }
        return insertIntoValues;
    }

    /**
     * _validate
     * @param columnInfoValue columnInfoValue
     * @param value value
     * @throws DatabaseValidationException DatabaseValidationException
     */
    protected void _validate(ColumnInfoValue columnInfoValue, Object value) throws DatabaseValidationException {
        if (columnInfoValue == null)
            return;
        boolean empty = value == null || StringUtils.isBlank(value.toString().trim());
        if (!columnInfoValue.isNullable() && empty) {
            throw new DatabaseValidationException("el valor del campo: " + columnInfoValue.getName() + ", es requerido");
        }
        if (empty)
            return;
        if (columnInfoValue.getClassType().equals(String.class) && !VisualTypesEnum.textarea.name().equals(columnInfoValue.getTagtype()) && !VisualTypesEnum.bpmn.name().equals(columnInfoValue.getTagtype()) && !VisualTypesEnum.WYSIWYG.name().equals(columnInfoValue.getTagtype())) {
            String strValue = value.toString().trim();
            if (columnInfoValue.getLength() == 0)
                throw new DatabaseValidationException("String data type without size --> " + columnInfoValue.getName());
            if (strValue.length() > columnInfoValue.getLength())
                throw new DatabaseValidationException("String data size greatest that size configurated --> " + columnInfoValue.getName());
            return;
        }
        if (columnInfoValue.getMinlength() > 0) {
            if (columnInfoValue.getClassType().equals(String.class)) {
                if (value.toString().trim().length() < columnInfoValue.getMinlength())
                    throw new DatabaseValidationException("el tama�o de la cadena debe ser mayor que " + columnInfoValue.getMinlength() + ", para el campo " + columnInfoValue.getLabel());
            } else if (columnInfoValue.getClassType().equals(Number.class)) {
                Double dvalue = Double.valueOf(value.toString());
                if (dvalue < columnInfoValue.getMinlength())
                    throw new DatabaseValidationException("el tama�o de la cadena debe ser mayor o igual a " + columnInfoValue.getMinlength() + ", para el campo " + columnInfoValue.getLabel());
            }
        }
        if (columnInfoValue.getLength() > 0) {
            if (columnInfoValue.getClassType().equals(String.class)) {
                if (value.toString().trim().length() > columnInfoValue.getLength())
                    throw new DatabaseValidationException("el tama�o de la cadena debe ser menor o igual a " + columnInfoValue.getLength() + ", para el campo " + columnInfoValue.getLabel());
            } else if (columnInfoValue.getClassType().equals(Number.class)) {
                BigDecimal dvalue = BigDecimal.valueOf(Double.valueOf(value.toString()));
                if (dvalue.precision() > columnInfoValue.getLength() || dvalue.scale() > columnInfoValue.getScale())
                    throw new DatabaseValidationException("el valor n�merico excede la precision y/o escala definida " + columnInfoValue.getLength() + " " + columnInfoValue.getScale() + ", para el campo " + columnInfoValue.getLabel());
            }
        }
    }

    /**
     * _removeDefaultDependent
     * @param tableName tableName
     * @throws DatabaseConnectionException DatabaseConnectionException
     * @throws SQLException SQLException
     * @throws DaoException DaoException
     */
    protected void _removeDefaultDependent(String tableName) throws DatabaseConnectionException, SQLException, DaoException {
        //mdTableRecoverService.removeDefaultDependent(tableName);
    }

    /**
     * getTableName
     * @param class1 class1
     * @return String
     */
    protected String getTableName(Class<?> class1) {
        return DaoUtil.getTableName(class1);
    }

    /**
     * updateTableName
     * @param <E> E
     * @param filter filter
     * @param entityClass entityClass
     */
    protected <E> void updateTableName(FilterCriteria filter, Class<E> entityClass) {
        Optional<FilterData> fromInfo = filter.getSql().stream().filter(obj -> ((FilterData) obj).isType(FilterDataType.FROMINFO)).findAny();
        if (fromInfo.isPresent()) {
            fromInfo.get().setFrom(getTableName(entityClass));
        }
    }
}
