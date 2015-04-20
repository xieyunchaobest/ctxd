package com.cattsoft.webpub.servlet;

import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.cattsoft.pub.LogConfig;
import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.config.SysConfig;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.jndi.JndiFactory;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.util.SysDate;


public class InitConfig extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(InitConfig.class);

	private ServletConfig config;

	private static final String DAO_CONFIG = "DAOConfig";

	private static final String CONNECTION_CONFIG = "ConnectionConfig";

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init() throws ServletException {
		// 初始化dao配置
		config = this.getServletConfig();
		LogConfig.init();
		log.debug("complete log init .");
		String connConfigStr = config
				.getInitParameter(CONNECTION_CONFIG.trim());
		String DAOConfigStr = System
			.getProperty(SysConstants.SYSTEM_PRPTY_DAO_CONFIG_URL);
		if(StringUtil.isBlank(DAOConfigStr)){
			DAOConfigStr = config.getInitParameter(DAO_CONFIG).trim();
		}
		String cacheMode=null;
		try {
			//log.debug("开始装载JMS遗留的实时流程实例消息内存记录...");
			//JMSDispatcher.getInstance().initProcInstMsgSendRecord(SysConstants.REAL_TIME);
			
//			log.debug("开始装载Connection配置...");
			log.debug("开始装载DAO配置...");
			DAOFactory.initDAOFactory(DAOConfigStr);
			
			log.debug("开始初始化sps缓存数据...");
			// DataCache.initial();
			//add by xieyunchao 20130411，该应用可能在内网和外网都部署一份，在外网不能访问数据库，缓存数据需要读sys_config.properties文件获取
			Properties p=  SysConfig.getConfig();
			cacheMode=p.getProperty("DATABASE_CACHE");
			if(SysConstants.FALSE.equals(cacheMode)){
				com.cattsoft.pub.util.DataCache.initPropertiesHashMap();
			}else{
				ConnectionFactory.initConnectionFactory(connConfigStr);
				ConnectionFactory.createConnection();
				com.cattsoft.pub.util.DataCache.initial();
			}
			
			SysDate.getCurrentDate();
			// 初始化工作流缓存
			//log.debug("开始初始化wfs缓存数据...");
			//WMCachePool.initCachePool();
			//log.debug("完成初始化缓存数据");

			//HashMap hm = new HashMap(); // 初始化登陆的配置
		//	hm.put(SysInit.ORG_MNG_MODE, "A");
			//hm.put(SysInit.WEB_CONTEXT, "/web");
			
			//SysInit.instance(hm);

			//如果部署在weblogic上，则加载context-config文件
			if(SysConstants.TRUE.equals(p.getProperty("CONTEXT_CONFIG"))){
				String contextURL = System
						.getProperty(SysConstants.SYSTEM_PRPTY_CONTEXT_CONFIG_URL);
				JndiFactory.getInstance(contextURL);
			}

			/*// 发布版本
			String version = SysConfigData.getSysConfigCurValue(
					SysConstants.SYS_CONFIG_RELEASED_VERSION, null, null, null,
					null, null);
			if (version != null) {
				this.getServletContext().setAttribute("RELEASE_VERSION",
						version);
			}
			String smallVersion =SysConfigData.getSysConfigCurValue(
				SysConstants.SYS_CONFIG_RELEASED_SMALL_VERSION, null, null, null,
				null, null);
			if (smallVersion != null) {
				this.getServletContext().setAttribute("RELEASE_SMALL_VERSION",
						smallVersion);
			}	
				
			// 发布时间
			String releaseDate = SysConfig.getConfig().getProperty(
					"system.buildTime");
			if (releaseDate != null) {
				this.getServletContext().setAttribute("RELEASE_TIME",
						releaseDate);
			}
			// 客户名称
			String customName = SysConfigData.getSysConfigCurValue(
					SysConstants.SYS_CONFIG_CUSTOM_NAME_HB_CUCC, null, null,
					null, null, null);
			if (customName != null) {
				this.getServletContext()
						.setAttribute("CUSTOM_NAME", customName);
			}*/
		} catch (SysException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		} catch (AppException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		} catch (DataCacheException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				if(SysConstants.TRUE.equals(cacheMode)){
					ConnectionFactory.closeConnection();
				}
				
			} catch (SysException e) {
				log.error(e.getMessage());
				throw new RuntimeException(e);
			} catch (AppException e) {
				log.error(e.getMessage());
				throw new RuntimeException(e);
			}
		}

	}
}
