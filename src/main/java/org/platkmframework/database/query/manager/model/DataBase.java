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
//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen.
// Generado el: 2020.04.27 a las 09:56:19 PM CDT
//
package org.platkmframework.database.query.manager.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
/**
 * <p>Clase Java para anonymous complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}id"/>
 *         &lt;element ref="{}name"/>
 *         &lt;element ref="{}QuerySyntax" maxOccurs="unbounded"/>
 *         &lt;element ref="{}QueriesGroup" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "name", "querySyntax", "queriesGroup" })
@XmlRootElement(name = "DataBase")
public class DataBase {

    /**
     * Atributo id
     */
    @XmlElement(required = true)
    public String id;

    /**
     * Atributo name
     */
    @XmlElement(required = true)
    public String name;

    /**
     * Atributo querySyntax
     */
    @XmlElement(name = "QuerySyntax", required = true)
    public List<QuerySyntax> querySyntax;

    /**
     * Atributo queriesGroup
     */
    @XmlElement(name = "QueriesGroup", required = true)
    public List<QueriesGroup> queriesGroup;
    
    
	/**
	 * DataBase
	 */
    public DataBase() {
		super();
	}

	/**
     * Obtiene el valor de la propiedad id.
     *
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad name.
     *
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the querySyntax property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the querySyntax property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuerySyntax().add(newItem);
     * </pre>
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QuerySyntax }
     * @return List
     */
    public List<QuerySyntax> getQuerySyntax() {
        if (querySyntax == null) {
            querySyntax = new ArrayList<QuerySyntax>();
        }
        return this.querySyntax;
    }

    /**
     * Gets the value of the queriesGroup property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queriesGroup property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueriesGroup().add(newItem);
     * </pre>
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueriesGroup }
     * @return List
     */
    public List<QueriesGroup> getQueriesGroup() {
        if (queriesGroup == null) {
            queriesGroup = new ArrayList<QueriesGroup>();
        }
        return this.queriesGroup;
    }
}
