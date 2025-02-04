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
package org.platkmframework.jpa.converter;

import org.apache.commons.lang3.StringUtils;
import org.platkmframework.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/** 
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 * @param <E> E
 */
@Converter(autoApply = true)
public abstract class JpaConverterJson<E extends Object> implements AttributeConverter<Object, String> {

    /**
     * Atributo logger
     */
    private static Logger logger = LoggerFactory.getLogger(JpaConverterJson.class);

    /**
     * Atributo class1
     */
    private Class<E> class1;

    /**
     * Constructor JpaConverterJson
     * @param class1 class1
     */
    public JpaConverterJson(Class<E> class1) {
        this.class1 = class1;
    }

    /**
     * convertToDatabaseColumn
     * @param meta meta
     * @return String
     */
    @Override
    public String convertToDatabaseColumn(Object meta) {
        try {
            return JsonUtil.objectToJson(meta);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * convertToEntityAttribute
     * @param dbData dbData
     * @return E
     */
    @Override
    public E convertToEntityAttribute(String dbData) {
        try {
            if (StringUtils.isBlank(dbData))
                return null;
            return JsonUtil.jsonToObject(dbData, this.class1);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }
}
