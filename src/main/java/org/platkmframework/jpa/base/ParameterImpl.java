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

import jakarta.persistence.Parameter;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 *   @param <T> T
 */
public final class ParameterImpl<T> implements Parameter<T> {

    /**
     * Atributo name
     */
    private String name;

    /**
     * Atributo position
     */
    private Integer position;

    /**
     * Atributo parameterType
     */
    private Class<T> parameterType;

    /**
     * Constructor ParameterImpl
     */
    public ParameterImpl() {
        super();
    }

    /**
     * Constructor ParameterImpl
     * @param name name
     * @param position position
     * @param parameterType parameterType
     */
    public ParameterImpl(String name, Integer position, Class<T> parameterType) {
        super();
        this.name = name;
        this.position = position;
        this.parameterType = parameterType;
    }

    /**
     * getName
     * @return String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * getPosition
     * @return Integer
     */
    @Override
    public Integer getPosition() {
        return position;
    }

    /**
     * getParameterType
     * @return Class
     */
    @Override
    public Class<T> getParameterType() {
        return parameterType;
    }

    /**
     * setName
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setPosition
     * @param position position
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * setParameterType
     * @param parameterType parameterType
     */
    public void setParameterType(Class<T> parameterType) {
        this.parameterType = parameterType;
    }
}
