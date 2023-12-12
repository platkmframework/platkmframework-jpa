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
package org.platkmframework.jpa.base;

import javax.persistence.Parameter;
import javax.persistence.TemporalType;


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public final class ParameterInfo<T>{
 
	private T value;
	private Parameter<T> parameter;
	private TemporalType temporalType;
	  
	public ParameterInfo() {
		super(); 
	}

	public ParameterInfo(T value, Parameter<T> parameter) {
		super();
		this.value = value;
		this.parameter = parameter;
	}
	
	public ParameterInfo(T value, Parameter<T> parameter, TemporalType temporalType) {
		super();
		this.value = value;
		this.parameter = parameter;
		this.temporalType = temporalType;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Parameter<T> getParameter() {
		return parameter;
	}

	public  void setParameter(Parameter<T> parameter) {
		 this.parameter = parameter;
	}

	public TemporalType getTemporalType() {
		return temporalType;
	}

	public void setTemporalType(TemporalType temporalType) {
		this.temporalType = temporalType;
	}
 
	

}
