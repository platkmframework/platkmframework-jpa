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
package org.platkmframework.database.query.common;

import java.io.Serializable;
import org.platkmframework.annotation.db.SystemColumnAction;
import jakarta.persistence.Column;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class ColumnInfoValue implements Serializable {

    /**
     * Atributo serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo name
     */
    private String name;

    /**
     * Atributo unique
     */
    private boolean unique = false;

    /**
     * Atributo nullable
     */
    private boolean nullable = true;

    /**
     * Atributo insertable
     */
    private boolean insertable = true;

    /**
     * Atributo updatable
     */
    private boolean updatable = true;

    /**
     * Atributo columnDefinition
     */
    private String columnDefinition;

    /**
     * Atributo table
     */
    private String table;

    /**
     * Atributo length
     */
    int length = 255;

    /**
     * Atributo precision
     */
    int precision = 0;

    /**
     * Atributo scale
     */
    private int scale = 0;

    /**
     * Atributo label
     */
    private String label;

    /**
     * Atributo code
     */
    private String code;

    /**
     * Atributo classType
     */
    private Class<?> classType;

    /**
     * Atributo classFieldName
     */
    private String classFieldName;

    /**
     * Atributo description
     */
    private String description;

    /**
     * Atributo tagtype
     */
    private String tagtype;

    /**
     * Atributo minlength
     */
    private int minlength;

    /**
     * Atributo json
     */
    Class<?>[] json;

    /**
     * Atributo pk
     */
    boolean pk;

    /**
     * Atributo increment
     */
    boolean increment;

    /**
     * Atributo value
     */
    private Object value;

    /**
     * Atributo converter
     */
    @SuppressWarnings("rawtypes")
    private Class converter;

    /**
     * Atributo systemColumnAction
     */
    private SystemColumnAction systemColumnAction;

    /**
     * Atributo systemColumnKey
     */
    private String systemColumnKey;

    /**
     * Constructor ColumnInfoValue
     * @param value value
     * @param classType classType
     * @param pk pk
     * @param increment increment
     */
    public ColumnInfoValue(Object value, Class<?> classType, boolean pk, boolean increment) {
        this.value = value;
        this.classType = classType;
        this.pk = pk;
        this.increment = increment;
    }

    /**
     * Constructor ColumnInfoValue
     * @param name name
     * @param classType classType
     * @param value value
     * @param tagtype tagtype
     * @param pk pk
     * @param increment increment
     */
    public ColumnInfoValue(String name, Class<?> classType, Object value, String tagtype, boolean pk, boolean increment) {
        this.name = name;
        this.classType = classType;
        this.value = value;
        this.tagtype = tagtype;
        this.pk = pk;
        this.increment = increment;
    }

    /**
     * set
     * @param column column
     * @param pk pk
     * @param increment increment
     */
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

    /**
     * getLanguageType
     * @param languageType languageType
     * @return Class
     */
    public static Class<?> getLanguageType(String languageType) {
        try {
            return Class.forName(languageType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * getName
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * isUnique
     * @return boolean
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * setUnique
     * @param unique unique
     */
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    /**
     * isNullable
     * @return boolean
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * setNullable
     * @param nullable nullable
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * isInsertable
     * @return boolean
     */
    public boolean isInsertable() {
        return insertable;
    }

    /**
     * setInsertable
     * @param insertable insertable
     */
    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }

    /**
     * isUpdatable
     * @return boolean
     */
    public boolean isUpdatable() {
        return updatable;
    }

    /**
     * setUpdatable
     * @param updatable updatable
     */
    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    /**
     * getColumnDefinition
     * @return String
     */
    public String getColumnDefinition() {
        return columnDefinition;
    }

    /**
     * setColumnDefinition
     * @param columnDefinition columnDefinition
     */
    public void setColumnDefinition(String columnDefinition) {
        this.columnDefinition = columnDefinition;
    }

    /**
     * getTable
     * @return String
     */
    public String getTable() {
        return table;
    }

    /**
     * setTable
     * @param table table
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * getLength
     * @return int
     */
    public int getLength() {
        return length;
    }

    /**
     * setLength
     * @param length length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * getPrecision
     * @return int
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * setPrecision
     * @param precision precision
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    /**
     * getScale
     * @return int
     */
    public int getScale() {
        return scale;
    }

    /**
     * setScale
     * @param scale scale
     */
    public void setScale(int scale) {
        this.scale = scale;
    }

    /**
     * getLabel
     * @return String
     */
    public String getLabel() {
        return label;
    }

    /**
     * setLabel
     * @param label label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * getCode
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * setCode
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * getClassType
     * @return Class
     */
    public Class<?> getClassType() {
        return classType;
    }

    /**
     * setClassType
     * @param classType classType
     */
    public void setClassType(Class<?> classType) {
        this.classType = classType;
    }

    /**
     * getDescription
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * setDescription
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getTagtype
     * @return String
     */
    public String getTagtype() {
        return tagtype;
    }

    /**
     * setTagtype
     * @param tagtype tagtype
     */
    public void setTagtype(String tagtype) {
        this.tagtype = tagtype;
    }

    /**
     * getMinlength
     * @return int
     */
    public int getMinlength() {
        return minlength;
    }

    /**
     * setMinlength
     * @param minlength minlength
     */
    public void setMinlength(int minlength) {
        this.minlength = minlength;
    }

    /**
     * getJson
     * @return Class[]
     */
    public Class<?>[] getJson() {
        return json;
    }

    /**
     * setJson
     * @param json json
     */
    public void setJson(Class<?>[] json) {
        this.json = json;
    }

    /**
     * getValue
     * @return Object
     */
    public Object getValue() {
        return value;
    }

    /**
     * setValue
     * @param value value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * isPk
     * @return boolean
     */
    public boolean isPk() {
        return pk;
    }

    /**
     * setPk
     * @param pk pk
     */
    public void setPk(boolean pk) {
        this.pk = pk;
    }

    /**
     * isIncrement
     * @return boolean
     */
    public boolean isIncrement() {
        return increment;
    }

    /**
     * setIncrement
     * @param increment increment
     */
    public void setIncrement(boolean increment) {
        this.increment = increment;
    }

    /**
     * getClassFieldName
     * @return String
     */
    public String getClassFieldName() {
        return classFieldName;
    }

    /**
     * setClassFieldName
     * @param classFieldName classFieldName
     */
    public void setClassFieldName(String classFieldName) {
        this.classFieldName = classFieldName;
    }

    /**
     * getConverter
     * @return Class
     */
    @SuppressWarnings("rawtypes")
    public Class getConverter() {
        return converter;
    }

    /**
     * setConverter
     * @param converter converter
     */
    public void setConverter(@SuppressWarnings("rawtypes") Class converter) {
        this.converter = converter;
    }

    /**
     * getSystemColumnAction
     * @return SystemColumnAction
     */
    public SystemColumnAction getSystemColumnAction() {
        return systemColumnAction;
    }

    /**
     * setSystemColumnAction
     * @param systemColumnAction systemColumnAction
     */
    public void setSystemColumnAction(SystemColumnAction systemColumnAction) {
        this.systemColumnAction = systemColumnAction;
    }

    /**
     * getSystemColumnKey
     * @return String
     */
    public String getSystemColumnKey() {
        return systemColumnKey;
    }

    /**
     * setSystemColumnKey
     * @param systemColumnKey systemColumnKey
     */
    public void setSystemColumnKey(String systemColumnKey) {
        this.systemColumnKey = systemColumnKey;
    }
}
