package com.cattsoft.pub.connection;

/**
 * Title: 服务开通系统<br>
 * Description: <br>
 * Date: 2007-8-14 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class ConnectionDef {
	
	private String name ;
	private String datasource;
	private String driverClass;
	private String userName;
	private String password;
	private String url;
	private String poolMaxActive;
	private String poolMaxWait;
	private String poolMaxIdle;
	
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPoolMaxActive() {
		return poolMaxActive;
	}
	public void setPoolMaxActive(String poolMaxActive) {
		this.poolMaxActive = poolMaxActive;
	}
	public String getPoolMaxIdle() {
		return poolMaxIdle;
	}
	public void setPoolMaxIdle(String poolMaxIdle) {
		this.poolMaxIdle = poolMaxIdle;
	}
	public String getPoolMaxWait() {
		return poolMaxWait;
	}
	public void setPoolMaxWait(String poolMaxWait) {
		this.poolMaxWait = poolMaxWait;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
