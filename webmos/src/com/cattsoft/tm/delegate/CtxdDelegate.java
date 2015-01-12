package com.cattsoft.tm.delegate;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.List;

import javax.xml.rpc.ServiceException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cattsoft.im.service.webservice.client.IM4MOSServicesService;
import com.cattsoft.im.service.webservice.client.IM4MOSServicesServiceLocator;
import com.cattsoft.im.service.webservice.client.IM4MOSServices_PortType;
import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.util.SysConfigData;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.component.domain.CtxdDOM;


/**
 * 
 * Title: 服务开通系统<br>
 * Description: 外勘处理Delegate<br>
 * Date: 2007-6-25 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author wangyun
 */
public class CtxdDelegate {
	private static Log log = LogFactory.getLog(CtxdDelegate.class);

	private static CtxdDelegate delegate = null;

	private CtxdDelegate() {

	}

	public static CtxdDelegate getDelegate() {
		if (delegate == null) {
			delegate = new CtxdDelegate();
		}
		return delegate;
	}
	
	
	
	
	/**
	 * 获取查询结果集
	 */
	public PagView queryResult(String tableId,List conditionListFromPage,PagInfo pageInfo) throws AppException, SysException {
		Connection conn = null;
		PagView returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.queryResult(tableId,conditionListFromPage,pageInfo);
			ConnectionFactory.commit();
		} catch (Exception e) { 
			e.printStackTrace();
			log.error("[IOM系统接口svcCallIOMByMosNative异常]" + e);
			try {
				ConnectionFactory.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative事务回滚异常]" + e1);
			}
		} finally {
			try {
				ConnectionFactory.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative数据库连接关闭异常]" + e);
			}
		}
		return returnValue;
	}
	
	
	public List getQueryConditionList(String tableId,List conditionListFromPage)throws AppException,SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.getQueryConditionList(tableId,conditionListFromPage);
			ConnectionFactory.commit();
		} catch (Exception e) { 
			e.printStackTrace();
			log.error("[IOM系统接口svcCallIOMByMosNative异常]" + e);
			try {
				ConnectionFactory.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative事务回滚异常]" + e1);
			}
		} finally {
			try {
				ConnectionFactory.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative数据库连接关闭异常]" + e);
			}
		}
		return returnValue;
	}
	
	/**
	 * 获取需要查询的列
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public  List getQueryColumnList(String tableId) throws AppException, SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.getQueryColumnList(tableId);
			ConnectionFactory.commit();
		} catch (Exception e) { 
			e.printStackTrace();
			log.error("[IOM系统接口svcCallIOMByMosNative异常]" + e);
			try {
				ConnectionFactory.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative事务回滚异常]" + e1);
			}
		} finally {
			try {
				ConnectionFactory.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative数据库连接关闭异常]" + e);
			}
		}
		return returnValue;
	}
	
	public List getFuncNodeListByUser(SysUserSVO user) throws AppException,SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.getFuncNodeListByUser(user);
			ConnectionFactory.commit();
		} catch (Exception e) { 
			e.printStackTrace();
			log.error("[IOM系统接口svcCallIOMByMosNative异常]" + e);
			try {
				ConnectionFactory.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative事务回滚异常]" + e1);
			}
		} finally {
			try {
				ConnectionFactory.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative数据库连接关闭异常]" + e);
			}
		}
		return returnValue;
	}
	
	public String login(SysUserSVO user) throws AppException,SysException{
		Connection conn = null;
		String returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.login(user);
			ConnectionFactory.commit();
		} catch (Exception e) { 
			e.printStackTrace();
			log.error("[IOM系统接口svcCallIOMByMosNative异常]" + e);
			try {
				ConnectionFactory.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative事务回滚异常]" + e1);
			}
		} finally {
			try {
				ConnectionFactory.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("[IOM系统接口svcCallIOMByMosNative数据库连接关闭异常]" + e);
			}
		}
		return returnValue;
	}
	
}
