package org.platkmframework.jpa.persistence;

import java.util.ArrayList;
import java.util.List;

public class PersistenceInfoUtil {

	public List<PersistenceInfo> persistenceInfoList;
	
	private boolean loaded   = false;
	private boolean notFound = false;
	
	private static PersistenceInfoUtil persistenceInfoUtil;
	
	private PersistenceInfoUtil() {
		persistenceInfoList = new ArrayList<PersistenceInfo>();
	}
	
	public static PersistenceInfoUtil instance()
	{
		if(persistenceInfoUtil == null)
			persistenceInfoUtil = new PersistenceInfoUtil(); 
		return persistenceInfoUtil;
	}

	public List<PersistenceInfo> getPersistenceInfoList() {
		return persistenceInfoList;
	}

	public void setPersistenceInfoList(List<PersistenceInfo> persistenceInfoList) {
		this.persistenceInfoList = persistenceInfoList;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	protected boolean isLoaded() {
		return loaded;
	}

	public boolean isNotFound() {
		return notFound;
	}

	public void setNotFound(boolean notFound) {
		this.notFound = notFound;
	}

}
