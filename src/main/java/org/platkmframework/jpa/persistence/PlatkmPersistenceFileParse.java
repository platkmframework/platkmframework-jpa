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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.platkmframework.content.project.ProjectContent;
import org.platkmframework.jpa.exception.PlatkmJpaException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class PlatkmPersistenceFileParse{
	
	private static Logger logger = LoggerFactory.getLogger(PlatkmPersistenceFileParse.class);
	
	public List<PersistenceInfo> parse() throws PlatkmJpaException {
		return parseByInputStream(PlatkmPersistenceFileParse.class.getClassLoader().getResourceAsStream("META-INF/persistence.xml"));
	}
	
	public List<PersistenceInfo> parseByInputStream(InputStream inputStream) throws PlatkmJpaException {
		 
		if(inputStream == null) return Collections.emptyList();
		List<PersistenceInfo> persistenceInfoList = new ArrayList<>();
		try {
		
			String strPersistence = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
			
			for (Object key: ProjectContent.instance().getAppProperties().keySet()) {
				strPersistence = strPersistence.replace("${" + key + "}", ProjectContent.instance().getAppProperties().getProperty(key.toString()));
	        }
			InputStream inputStreamPersistence = new ByteArrayInputStream(strPersistence.getBytes());
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			Document doc       = db.parse(inputStreamPersistence);
			final Element top  = doc.getDocumentElement();
			 
			final NodeList children = top.getChildNodes();
			Element element;
			Element element1;
			Element element2;
			String tag;
			PersistenceInfo  persistenceInfo;
			
			for ( int i = 0; i < children.getLength() ; i++ ) {
				if ( children.item( i ).getNodeType() == Node.ELEMENT_NODE ) {
					element = (Element) children.item( i );
					tag = element.getTagName();
					if (tag.equals("persistence-unit")) {
						if(StringUtils.isBlank(element.getAttribute("name"))) throw new PlatkmJpaException("persisten unit without name");
							persistenceInfo = new PersistenceInfo();
							persistenceInfo.setName(element.getAttribute("name"));
							persistenceInfo.setTransactionType(element.getAttribute("transaction-type"));
							 
							for ( int j = 0;j < element.getChildNodes().getLength() ; j++ ) {
								if ( element.getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE ) {
									element1 = (Element) element.getChildNodes().item(j);
									 tag = element1.getTagName();
									 if (tag.equals( "provider" ) ) {
										 persistenceInfo.setProvider(element1.getTextContent());
									 }else if ( tag.equals( "properties" ) ) {
										 for ( int k = 0;k < element1.getChildNodes().getLength() ; k++ ) {
											 if ( element1.getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE ) {
												 element2 = (Element) element1.getChildNodes().item(k);
												 tag = element2.getTagName();
												 if ( tag.equals( "property" ) ) {
													 persistenceInfo.getProperties().put(element2.getAttribute("name"), element2.getAttribute("value"));
												 }
											 }
										 }
									 }else if ( tag.equals( "class" ) ) {
										 persistenceInfo.getClasses().add(element1.getTextContent());
									 }
								}
							}  
							
							persistenceInfoList.add(persistenceInfo);
						} 
					}
				}
				
				if(persistenceInfoList.size() == 0 ) {
					logger.info("No hay información de unidad de persistencia. No podran funcionar los paquetes de openAPI, dbAPI y System search");
				}else if(persistenceInfoList.size() > 1 ) {
					logger.info("Existe más de un paquete de persistencia. Debe agregar en las propiedades del sistema el el identificicador de persistencia para que pudean "
							+ " funcionar los paquetes de openAPI, dbAPI y System search. Ejemplo: ");
				}
			
			return persistenceInfoList;
			
		} catch (ParserConfigurationException | SAXException | IOException  e) {
			throw new PlatkmJpaException(e);
		}
	}

}
