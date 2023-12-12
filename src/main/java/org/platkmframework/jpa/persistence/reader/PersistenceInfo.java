package org.platkmframework.jpa.persistence.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.platkmframework.util.DataTypeUtil;

public class PersistenceInfo {
	
	private String name;  
	private String transactionType; 
	private String provider; 
	private Map<String, Object> properties;
	private List<String> classes;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public Map<String, Object> getProperties() {
		if (properties == null) properties = new HashMap<>();
		return properties;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	public List<String> getClasses() {
		return classes;
	}
	public void setClasses(List<String> classes) {
		this.classes = classes;
	}
	
	public String getStringPropertyValue(String key) {
		return DataTypeUtil.getStringValue(properties.get(key), "");
	}
}
