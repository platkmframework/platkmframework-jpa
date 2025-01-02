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
import jakarta.persistence.TemporalType;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 *   @param <T> T
 */
public final class ParameterInfo<T> {

    /**
     * Atributo value
     */
    private T value;

    /**
     * Atributo parameter
     */
    private Parameter<T> parameter;

    /**
     * Atributo temporalType
     */
    private TemporalType temporalType;

    /**
     * Constructor ParameterInfo
     */
    public ParameterInfo() {
        super();
    }

    /**
     * Constructor ParameterInfo
     * @param value value
     * @param parameter parameter
     */
    public ParameterInfo(T value, Parameter<T> parameter) {
        super();
        this.value = value;
        this.parameter = parameter;
    }

    /**
     * Constructor ParameterInfo
     * @param value value
     * @param parameter parameter
     * @param temporalType temporalType
     */
    public ParameterInfo(T value, Parameter<T> parameter, TemporalType temporalType) {
        super();
        this.value = value;
        this.parameter = parameter;
        this.temporalType = temporalType;
    }

    /**
     * getValue
     * @return T
     */
    public T getValue() {
        return value;
    }

    /**
     * setValue
     * @param value value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * getParameter
     * @return Parameter
     */
    public Parameter<T> getParameter() {
        return parameter;
    }

    /**
     * setParameter
     * @param parameter parameter
     */
    public void setParameter(Parameter<T> parameter) {
        this.parameter = parameter;
    }

    /**
     * getTemporalType
     * @return TemporalType
     */
    public TemporalType getTemporalType() {
        return temporalType;
    }

    /**
     * setTemporalType
     * @param temporalType temporalType
     */
    public void setTemporalType(TemporalType temporalType) {
        this.temporalType = temporalType;
    }
}
