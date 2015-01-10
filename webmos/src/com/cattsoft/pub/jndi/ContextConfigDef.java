package com.cattsoft.pub.jndi;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Title: 服务开通系统<br>
 * Description: <br>
 * Date: 2007-8-14 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class ContextConfigDef {

	
	private String defaultName;
	
	private Map contextDefs = new HashMap();

	

	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public Map getContexts() {
		return contextDefs;
	}

	public void setContexts(Map contexts) {
		this.contextDefs = contexts;
	}
	
	public void addContext(ContextDef contextDef){
		this.contextDefs.put(contextDef.getName(), contextDef);
	}
	
	
}
