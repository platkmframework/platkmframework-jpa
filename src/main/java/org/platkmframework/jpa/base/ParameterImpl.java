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


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public final class ParameterImpl<T> implements Parameter<T>{

	private String name;
	private Integer position;
	private Class<T> parameterType; 
	 
	public ParameterImpl() {
		super(); 
	}

	public ParameterImpl(String name, Integer position, Class<T> parameterType) {
		super();
		this.name = name;
		this.position = position;
		this.parameterType = parameterType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getPosition() { 
		return position;
	}

	@Override
	public Class<T> getParameterType() { 
		return parameterType;
	}
  
	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public void setParameterType(Class<T> parameterType) {
		this.parameterType = parameterType;
	}
	
	

}
