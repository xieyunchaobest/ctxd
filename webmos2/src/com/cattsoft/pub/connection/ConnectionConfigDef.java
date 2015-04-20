package com.cattsoft.pub.connection;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: 服务开通系统<br>
 * Description: <br>
 * Date: 2007-8-14 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class ConnectionConfigDef {
	
	private String defaultName;
	
	private Map connections = new HashMap();

	public Map getConnections() {
		return connections;
	}

	public void setConnections(Map connections) {
		this.connections = connections;
	}
	
	public void addConnection(ConnectionDef connDef){
		this.connections.put(connDef.getName(), connDef);
	}

	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}
	
	
	

}
