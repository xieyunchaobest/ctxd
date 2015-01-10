package com.cattsoft.pub.jndi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.LogUtil;
import com.cattsoft.pub.util.StringUtil;

/**
 * 
 * Title: 服务开通系统<br>
 * Description: 根据配置查找对应jndicontext<br>
 * Date: 2007-8-15 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class JndiFactory {

	private static JndiFactory jndiFactory;

	public static String CONTEXTS_FILE = "context-config.xml";

	private ThreadLocal threadCtxs = new ThreadLocal();

	private ThreadLocal threadCtxNames = new ThreadLocal();

	private static Logger log = Logger.getLogger(JndiFactory.class);
	
	public static final String SYSTEM_PRPTY_CONTEXT_CONFIG_URL_DEFAULT = "default";

	// 配置的多个context参数定义类
	private ContextConfigDef pro = null;

	// 默认context名称
	// private Context defaultCtx = null;

	private String defaultName = "";

	private Map jndiTreeMap = new HashMap();
	
	

	/**
	 * 获取Jndi工厂实例
	 * 
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	synchronized public static JndiFactory getInstance() throws SysException,
			AppException {
		if (null == jndiFactory) {
			if (StringUtil.isBlank(CONTEXTS_FILE)) {
				throw new AppException("","缺少context-config.xml文件地址参数配置，请检查jvm参数设置！");
			//} else if(SYSTEM_PRPTY_CONTEXT_CONFIG_URL_DEFAULT.equals(CONTEXTS_FILE)){
			//	log.debug("开始装载默认context-config.xml文件...");
			//	jndiFactory = new JndiFactory();
			//	log.debug("JndiFacotry 创建成功。");
			}else{
				log.debug("开始装载context-config.xml文件：" + CONTEXTS_FILE);
				jndiFactory = new JndiFactory(CONTEXTS_FILE);
				log.debug("JndiFacotry 创建成功。");
			}
			
		}
		return jndiFactory;
	}

	/**
	 * 获取Jndi工厂实例
	 * 
	 * @return
	 * @throws SysException
	 * @throws AppException
	 *             author ljx
	 */
	synchronized public static JndiFactory getInstance(String contextPath)
			throws SysException, AppException {
		CONTEXTS_FILE = contextPath;
		return getInstance();
	}

	/**
	 * 根据jndi名称查找默认context的远程home
	 * 
	 * @param jndiName
	 * @return
	 * @throws NamingException
	 * @throws SysException
	 * @throws AppException
	 */
	public Object getHome(String jndiName) throws NamingException,
			SysException, AppException {
		return getHome(defaultName, jndiName);
	}

	/**
	 * 根据context名称和jndi名称查找远程home
	 * 
	 * @param jndiName
	 * @return
	 * @throws NamingException
	 * @throws AppException
	 * @throws SysException
	 */
	public Object getHome(String ctxName, String jndiName)
			throws NamingException, SysException, AppException {
		java.lang.Object home = null;

		home = getObject(ctxName, jndiName);
		home = null;// 生产集群环境下不从缓存当中取，避免始终到同一个服务器下的JNDI对象，导致无法Failover
		if (null == home) {
			home = this.getHomeInteface(ctxName, jndiName);
		}

		Object realhome = null;
		try {
			realhome = javax.rmi.PortableRemoteObject
					.narrow(home, Object.class);
		} catch (ClassCastException ex) {
			throw new SysException("", "EJB远程接口narrow错误！jndi:" + jndiName, ex);
		}
		return realhome;
	}

	/**
	 * 根据jndi名称查找默认context的本地home
	 * 
	 * @param jndiName
	 * @return
	 * @throws NamingException
	 * @throws SysException
	 * @throws AppException
	 */
	public Object getLocalHome(String jndiName) throws NamingException,
			SysException, AppException {
		return getLocalHome(defaultName, jndiName);
	}

	/**
	 * 根据context名称和jndi名称查找本地home
	 * 
	 * @param jndiName
	 * @return
	 * @throws NamingException
	 * @throws AppException
	 * @throws SysException
	 */
	public Object getLocalHome(String ctxName, String jndiName)
			throws NamingException, SysException, AppException {
		java.lang.Object home = null;

		// if(log.isDebugEnabled())log.info("find "+jndiName + " Home in
		// hashtable");
		home = getObject(ctxName, jndiName);
		// if(log.isDebugEnabled())log.info("--"+jndiName + "-- Home :"+ home);
		home = null;// 生产集群环境下不从缓存当中取，避免始终到同一个服务器下的JNDI对象，导致无法Failover
		if (null == home) {
			// if(log.isDebugEnabled())log.info("Not in hashtable,looking up
			// begin");
			home = getHomeInteface(ctxName, jndiName);
			//log.info("looking up -*>" + jndiName + "<*- sucess:" + home);
		}

		log.info("looking up -*>" + jndiName + "<*- sucess :" + home);
		Object realhome = home;

		return realhome;
	}

	/**
	 * 具体得到home的函数，在getObject得不到home的时候调用
	 * 
	 * @param jndiName
	 * @return
	 * @throws NamingException
	 * @throws SysException
	 * @throws AppException
	 */
	private Object getHomeInteface(String ctxName, String jndiName)
			throws SysException, AppException {
		Object o = null;
		Context ctx = this.getContext(ctxName);
		if (ctx == null)
			throw new AppException("", "系统找不到对应的Context环境！" + ctxName);
		try {
			o = ctx.lookup(jndiName);
			if (o != null) {
				putObject(ctxName, jndiName, o);
			}
			log.debug("look up sucess: found " + jndiName + ":" + o);
		} catch (NamingException e) {
			throw new SysException("", "系统找不到对应的JNDI资源！jndi:" + jndiName, e);
		}
		return o;
	}

	/**
	 * 在缓存中查找是否已经有指定名称的home
	 * 
	 * @param jndiName
	 * @return
	 */
	synchronized private Object getObject(String ctxName, String jndiName) {
		Object jndiObj = jndiTreeMap.get(ctxName);
		if (jndiObj == null)
			return null;
		Map jndiMap = (Map) jndiObj;
		return jndiMap.get(jndiName);

	}

	/**
	 * 将已经定位好的home放入缓存中
	 * 
	 * @param jndiName
	 * @param ref
	 */
	synchronized private void putObject(String ctxName, String jndiName,
			Object ref) {
		Map jndiMap = null;

		Object jndiObj = jndiTreeMap.get(ctxName);
		if (jndiObj == null) {
			jndiMap = new HashMap();
		} else {
			jndiMap = (Map) jndiObj;
		}

		jndiMap.put(jndiName, ref);

	}

	/**
	 * 读配置文件，初始化contexts
	 * 
	 * @throws SysException
	 * @throws AppException
	 */
	private JndiFactory() throws SysException, AppException {

		ContextConfigReader ctxReader = new ContextConfigReader();
		InputStream in;

		log.debug("开始装载配置文件:" + CONTEXTS_FILE);
		in = JndiFactory.class.getClassLoader().getResourceAsStream(
				CONTEXTS_FILE);
		ctxReader.read(in);
		pro = ctxReader.getConfigDef();
		defaultName = pro.getDefaultName();

	}

	/**
	 * 读配置文件，初始化contexts
	 * 
	 * @throws SysException
	 * @throws AppException
	 *             author ljx
	 */
	private JndiFactory(String contextPath) throws SysException, AppException {

		ContextConfigReader ctxReader = new ContextConfigReader();
		InputStream in;

		File file = new File(contextPath);
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new SysException("1000005","装载外围系统环境配置文件失败！",e);
		}
		ctxReader.read(in);
		pro = ctxReader.getConfigDef();
		defaultName = pro.getDefaultName();

	}

	/**
	 * 根据制定名称，获取对应配置的Context变量，<br>
	 * 1、当前线程无context，创建一个新的，返回；<br>
	 * 2、当前线程已有同名context，直接返回；<br>
	 * 3、当前线程已有context,但名称不同，关闭当前的后，创建一个新的；<br>
	 * 
	 * @param ctxName
	 * @return
	 * @throws SysException
	 */
	public Context getContext(String ctxName) throws SysException {
		ContextDef ctxDef = (ContextDef) pro.getContexts().get(ctxName);
		Object obj = threadCtxNames.get();
		if (obj != null) {
			String name = (String) obj;
			Object ctxObj = threadCtxs.get();
			// 存在一个已有context，直接返回
			if (name.equals(ctxName)) {
				return (Context) ctxObj;
			} else {
				// 当前context非所要的且非默认的，关闭当前context，创建新context
				Context oriCtx = (Context) ctxObj;
				try {
					// 关闭weblogic当前栈顶的非默认context
					oriCtx.close();
					log.debug("成功关闭context:" + name);
					threadCtxNames.set(null);
					threadCtxs.set(null);
				} catch (NamingException e) {
					throw new SysException("1000001", "关闭系统已有context时发生异常！"
							+ name, e);

				}

			}
		}
		Context ctx = null;
		try {
			ctx = createContext(ctxDef);
			threadCtxNames.set(ctxName);
			threadCtxs.set(ctx);
		} catch (NamingException e) {
			LogUtil.logExceptionStackTrace(log, e);
			log.error("初始化context失败！Context_Name:" + ctxDef.getName());
		}

		return ctx;
	}

	private Context createContext(ContextDef ctxDef) throws NamingException {
		Context ctx = null;
		String intialContext = ctxDef.getInitialContext();

		if (!StringUtil.isBlank(intialContext)
				&& intialContext.trim().toUpperCase().equals(
						ContextDef.INITIAL_CONTEXT_TRUE)) {
			ctx = new InitialContext();
		} else {
			Hashtable ht = new Hashtable();
			ht.put(Context.INITIAL_CONTEXT_FACTORY, ctxDef
					.getInitContextFactory().trim());
			ht.put(Context.PROVIDER_URL, ctxDef.getProviderUrl());
			if (!StringUtil.isBlank(ctxDef.getSecurityPrincipal())) {
				ht.put(Context.SECURITY_PRINCIPAL, ctxDef
						.getSecurityPrincipal());
				ht.put(Context.SECURITY_CREDENTIALS, ctxDef
						.getSecurityCredentials());
			}
//			ht.put(weblogic.jndi.WLContext.ENABLE_SERVER_AFFINITY, "true");
			ctx = new InitialContext(ht);
		}
		log.error("成功初始化Context:" + ctxDef.getName() + ":" + ctx.toString());
		threadCtxNames.set(ctxDef.getName());
		threadCtxs.set(ctx);

		return ctx;
	}
	
	
	/**
	 * 针对单server地址访问，不提供集群failover能力
	 * 
	 * @param url
	 * @param principal
	 * @param credentials
	 * @return
	 * @throws NamingException
	 */
	public static Context createContext(String url,String principal,String credentials) throws NamingException {
		Context ctx = null;
		
			Hashtable ht = new Hashtable();
			ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			ht.put(Context.PROVIDER_URL, url);
			if (!StringUtil.isBlank(principal)) {
				ht.put(Context.SECURITY_PRINCIPAL, principal);
				ht.put(Context.SECURITY_CREDENTIALS, credentials);
			}
			//ht.put(weblogic.jndi.WLContext.ENABLE_SERVER_AFFINITY, "true");单server地址访问
			ctx = new InitialContext(ht);
		
		log.error("成功初始化Context:" + url + ":" + ctx.toString());
		
		return ctx;
	}

	/**
	 * 清除已有ejb的context和ejb，
	 * 
	 * 
	 */
	public void clear() {
		jndiTreeMap.clear();
	}

	public String getDefaultName() {
		return defaultName;
	}

	public static void main(String[] args) throws SysException, AppException {
		//JndiFactory.getInstance();
		log.debug("开始进入JndiFactory.java的main()...");
		// String filePath="D:\env\sps\context\context-config.xml";
		String filePath = "D:/env/sps/context/context-config.xml";
		// String filePath1="D:\\env\\sps\\context\\context-config.xml";
		//String contextURL = System.getProperty(SysConstants.SYSTEM_PRPTY_CONTEXT_CONFIG_URL);
		//JndiFactory.getInstance(contextURL);
		//new JndiFactory(filePath);

	}

}
