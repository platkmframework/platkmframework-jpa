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
package org.platkmframework.database.query.common.limit;

import javax.naming.LimitExceededException; 

import org.platkmframework.annotation.Service;
import org.platkmframework.annotation.limit.ApplicationLimit;
import org.platkmframework.content.ObjectContainer;


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
@Service
public class LimitChecker {
	
	 
	public boolean check(Object obj) throws LimitExceededException {
		
		if(obj.getClass().isAnnotationPresent(ApplicationLimit.class)) {
			ApplicationLimit applicationLimit = obj.getClass().getAnnotation(ApplicationLimit.class);
			Limit limit = (Limit)  ObjectContainer.instance().geApptScopeObj(applicationLimit.limitClass());
			if(!limit.check(obj)) throw new ApplicationLimitException("No se pueden crear m�s registros en este proceso, por exceder el l�mite permitido");
		}
		
		return true;
	}

}
