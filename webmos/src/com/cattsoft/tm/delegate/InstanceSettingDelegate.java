package com.cattsoft.tm.delegate;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.component.domain.InstanceSettingDOM;
import com.cattsoft.tm.vo.QueryInstanceSVO;


/**
 * 
 * Title: 服务开通系统<br>
 * Description: 外勘处理Delegate<br>
 * Date: 2007-6-25 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author wangyun
 */
public class InstanceSettingDelegate {
	private static Log log = LogFactory.getLog(InstanceSettingDelegate.class);

	private static InstanceSettingDelegate delegate = null;

	private InstanceSettingDelegate() {

	}

	public static InstanceSettingDelegate getDelegate() {
		if (delegate == null) {
			delegate = new InstanceSettingDelegate();
		}
		return delegate;
	}
	
	
	
	
	public List getTableColumns(String tableName) throws AppException,SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			InstanceSettingDOM dom=new InstanceSettingDOM();
			returnValue =dom.getTableColumns(tableName);
			ConnectionFactory.commit();
		}catch (SysException e1) { 
			ConnectionFactory.rollback();
			throw e1;
		}catch (AppException e2) { 
			ConnectionFactory.rollback();
			throw e2;
		}catch (Exception e) { 
			ConnectionFactory.rollback();
			throw new SysException("","出现未知异常！",e);
		}finally {
				ConnectionFactory.closeConnection();
		}
		return returnValue;
	}
	
	public List getTables() throws AppException,SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			InstanceSettingDOM dom=new InstanceSettingDOM();
			returnValue =dom.getTables();
			ConnectionFactory.commit();
		}catch (SysException e1) { 
			ConnectionFactory.rollback();
			throw e1;
		}catch (AppException e2) { 
			ConnectionFactory.rollback();
			throw e2;
		}catch (Exception e) { 
			ConnectionFactory.rollback();
			throw new SysException("","出现未知异常！",e);
		}finally {
				ConnectionFactory.closeConnection();
		}
		return returnValue;
	}
	
	public void addInstance(QueryInstanceSVO addInstance,List queryInstanceColumnList,List queryConditionList) throws AppException,SysException{
		Connection conn = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			InstanceSettingDOM dom=new InstanceSettingDOM();
			dom.addInstance(addInstance,queryInstanceColumnList,queryConditionList);
			ConnectionFactory.commit();
		}catch (SysException e1) { 
			ConnectionFactory.rollback();
			throw e1;
		}catch (AppException e2) { 
			ConnectionFactory.rollback();
			throw e2;
		}catch (Exception e) { 
			ConnectionFactory.rollback();
			throw new SysException("","出现未知异常！",e);
		}finally {
				ConnectionFactory.closeConnection();
		}
	}
	
	public List getInstanceTypeList() throws AppException,SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			InstanceSettingDOM dom=new InstanceSettingDOM();
			returnValue =dom.getInstanceTypeList();
			ConnectionFactory.commit();
		}catch (SysException e1) { 
			ConnectionFactory.rollback();
			throw e1;
		}catch (AppException e2) { 
			ConnectionFactory.rollback();
			throw e2;
		}catch (Exception e) { 
			ConnectionFactory.rollback();
			throw new SysException("","出现未知异常！",e);
		}finally {
				ConnectionFactory.closeConnection();
		}
		return returnValue;
	}
	
	public void deleteInstance(String instanceId) throws AppException,SysException{
		Connection conn = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			InstanceSettingDOM dom=new InstanceSettingDOM();
			dom.deleteInstance(instanceId);
			ConnectionFactory.commit();
		}catch (SysException e1) { 
			ConnectionFactory.rollback();
			throw e1;
		}catch (AppException e2) { 
			ConnectionFactory.rollback();
			throw e2;
		}catch (Exception e) { 
			ConnectionFactory.rollback();
			throw new SysException("","出现未知异常！",e);
		}finally {
				ConnectionFactory.closeConnection();
		}
	
	}
}
