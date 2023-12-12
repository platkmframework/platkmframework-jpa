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
package org.platkmframework.jpa.persistence;

import javax.persistence.spi.LoadState;
import javax.persistence.spi.ProviderUtil;


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class PlakmProviderUtil implements ProviderUtil {
	 

	@Override
	public LoadState isLoadedWithoutReference(Object entity, String attributeName) { 
		return LoadState.UNKNOWN;
	}

	@Override
	public LoadState isLoadedWithReference(Object entity, String attributeName) { 
		return LoadState.UNKNOWN;
	}

	@Override
	public LoadState isLoaded(Object entity) { 
		return LoadState.UNKNOWN;
	}

}
