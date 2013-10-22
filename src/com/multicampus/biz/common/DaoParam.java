package com.multicampus.biz.common;

public class DaoParam { 
	
	private String sqlType;
	private String key;
	private Object value;
	
	public DaoParam(String sqlType, String key, Object value) {
		this.sqlType = sqlType;
		this.key = key;
		this.value = value;
	}

	public String getSqlType() {
		return sqlType;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}
	
}