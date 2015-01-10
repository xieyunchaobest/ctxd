package com.cattsoft.pub.jndi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.cattsoft.pub.exception.SysException;

/**
 * Title: 服务开通系统<br>
 * Description: <br>
 * Date: 2007-8-14 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class ContextConfigReader {
	
	public static final String CONTEXT_CONFIG = "context-config";
	public static final String CONTEXT = 	"context-config/context";
	public static final String INIT_CONTEXT_FACTORY = 	"context-config/context/init-context-factory";
	public static final String PROVIDER_URL = 	"context-config/context/provider-url";
	public static final String SECURITY_PRINCIPAL = 		"context-config/context/security-principal";
	public static final String SECURITY_CREDENTIALS = 		"context-config/context/security-credentials";
	
	private ContextConfigDef configDef = new ContextConfigDef();
	
	private Digester digester;
	
	/**
	 * 从 输入流中按添加的规则读取xml配置信息
	 * @param in
	 * @throws BeansDefinitionException
	 */
	public void read(InputStream in) throws SysException{
		
		digester=new Digester();
		digester.push(this);
		digester.setValidating(false);
		this.addRules();
		try	{
			digester.parse(in);
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			throw new SysException("xml parse exception",ioe);
		}catch(SAXException saxe)
		{
			saxe.printStackTrace();
			throw new SysException("xml parse exception",saxe);
		}
		
	}
	
	/**
	 * 添加digester的解析规则
	 *
	 */
	public void addRules() 
	{
		digester.addObjectCreate(CONTEXT_CONFIG,ContextConfigDef.class);
		digester.addSetProperties(CONTEXT_CONFIG,"default","defaultName");
		
		
		/*
		 * <context name="local">
			<init-context-factory >weblogic.jndi.WLInitialContextFactory</init-context-factory>
			<provider-url>t3://127.0.0.1:7001</provider-url>
			<security-principal>weblogic</security-principal>
			<security-credentials>weblogic</security-credentials>
		  </context>
		  <contex name="initialContext" initialContext="true" >
		  </contex>
		 */
		digester.addObjectCreate(CONTEXT,ContextDef.class);
		digester.addSetProperties(CONTEXT,"name","name");
		digester.addSetProperties(CONTEXT,"initialContext","initialContext");
		digester.addCallMethod(INIT_CONTEXT_FACTORY,"setInitContextFactory",0);
		digester.addCallMethod(PROVIDER_URL,"setProviderUrl",0);
		digester.addCallMethod(SECURITY_PRINCIPAL,"setSecurityPrincipal",0);
		digester.addCallMethod(SECURITY_CREDENTIALS,"setSecurityCredentials",0);
		
		
		digester.addSetNext(CONTEXT,"addContext");
		digester.addSetNext(CONTEXT_CONFIG,"setConfigDef");
	}
	
	

	public ContextConfigDef getConfigDef() {
		return configDef;
	}

	public void setConfigDef(ContextConfigDef configDef) {
		this.configDef = configDef;
	}

	public static void main(String[] args)throws Exception{
		ContextConfigReader reader = new ContextConfigReader();
		InputStream in = new FileInputStream("D:/context-config.xml");
		reader.read(in);
		ContextConfigDef pro = reader.getConfigDef();
		//System.out.println(pro.getDefaultName());
		
		
		/*pro = (BeanPropertyDefinition)reader.getBeanDefinition("globalActionHandle").getProperties().get("actions");
		System.out.println(pro.getType()+":"+pro.getCollection().size());
		
		pro = (BeanPropertyDefinition)reader.getBeanDefinition("nodeActionDispatcher").getProperties().get("actions");
		System.out.println(pro.getType()+":"+pro.getCollection().size());*/
		
	
	}
	
	

}
