package org.platkmframework.database.query.common.vo;

import java.util.ArrayList;
import java.util.List;

public class CustomResultInfo<E> {
	
	private Class<E> resultClass;
	private List<String> columns;

	public CustomResultInfo(Class<E> resultClass) {
		super();
		this.resultClass = resultClass;
		this.columns = new ArrayList<String>();
	}

	public Class<E> getResultClass() {
		return resultClass;
	}

	public void addColumn(String column) {
		this.columns.add(column);
	}

	public List<String> getColumns() {
		return columns;
	}

	public void addColumn(String[] split) {
		for (int i = 0; i < split.length; i++) {
			addColumn(split[i]);
		}
	}

	
}