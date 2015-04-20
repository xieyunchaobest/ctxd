package com.cattsoft.pub.jndi;

/**
 * Title: 服务开通系统<br>
 * Description: <br>
 * Date: 2007-8-14 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class ContextDef {
	private String name;
	private String initContextFactory;
	private String providerUrl;
	private String securityPrincipal;
	private String securityCredentials;
	private String initialContext;
	
	public static final String INITIAL_CONTEXT_TRUE="TRUE";
	public static final String INITIAL_CONTEXT_FALSE="FALSE";
	
	public String getInitContextFactory() {
		return initContextFactory;
	}
	public void setInitContextFactory(String initContextFactory) {
		this.initContextFactory = initContextFactory;
	}
	public String getInitialContext() {
		return initialContext;
	}
	public void setInitialContext(String initialContext) {
		this.initialContext = initialContext;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProviderUrl() {
		return providerUrl;
	}
	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}
	public String getSecurityCredentials() {
		return securityCredentials;
	}
	public void setSecurityCredentials(String securityCredentials) {
		this.securityCredentials = securityCredentials;
	}
	public String getSecurityPrincipal() {
		return securityPrincipal;
	}
	public void setSecurityPrincipal(String securityPrincipal) {
		this.securityPrincipal = securityPrincipal;
	}
	
	

}
