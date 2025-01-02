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
package org.platkmframework.database.query.common.vo;

import jakarta.persistence.Column;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class OptionResult {

    /**
     * Atributo id
     */
    @Column(name = "id", length = 255)
    private String id;

    /**
     * Atributo text
     */
    @Column(name = "text", length = 255)
    private String text;

    /**
     * Constructor OptionResult
     */
    public OptionResult() {
        super();
    }

    /**
     * Constructor OptionResult
     * @param id id
     * @param text text
     */
    public OptionResult(String id, String text) {
        super();
        this.id = id;
        this.text = text;
    }

    /**
     * getId
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * setId
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getText
     * @return String
     */
    public String getText() {
        return text;
    }

    /**
     * setText
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }
}
