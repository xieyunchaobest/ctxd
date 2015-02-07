package com.cattsoft.tm.delegate;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Document;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.component.domain.CtxdDOM;
import com.cattsoft.tm.vo.DTableDescSVO;
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
	
	
	public List getQueryConditionList(String instanceId,List conditionListFromPage)throws AppException,SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.getQueryConditionList(instanceId,conditionListFromPage);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
	
	/**
	 * 获取需要查询的列
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public  List getQueryColumnList(String tableName) throws AppException, SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.getQueryColumnList(tableName);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
	
	public List getFuncNodeListByUser(SysUserSVO user) throws AppException,SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.getFuncNodeListByUser(user);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
	
	public String login(SysUserSVO user, HttpServletRequest request) throws AppException,SysException{
		Connection conn = null;
		String returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.login(user,request);
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
	
	/**
	 * 获取当前数据库用户的表
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getDBTables()throws AppException, SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.getDBTables();
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
	
	public DTableDescSVO getConfigTableInfo(String tableId)throws AppException, SysException{
		Connection conn = null;
		DTableDescSVO returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.getConfigTableInfo(tableId);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
	
	/**
	 * 获取列的说明信息，如果没有，则取数据字典的说明
	 * @param svo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getColumnDescList(String tableName)throws AppException, SysException{
		Connection conn = null;
		List returnValue = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			returnValue =dom.getColumnDescList(tableName);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
	
	
	/**
	 *
	 * @param svo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public void saveTableConfig(DTableDescSVO table,List columns,List queryConditionList)throws AppException, SysException{
		Connection conn = null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			dom.saveTableConfig(table,columns,queryConditionList);
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
	
	public List getConfigTabList() throws AppException, SysException {
		Connection conn = null;
		List res=null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			res=dom.getConfigTabList();
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
		return res;
	}
	
	public DTableDescSVO getTableVO(String tableName) throws AppException, SysException {
		Connection conn = null;
		DTableDescSVO res=null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			res=dom.getTableVO(tableName);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
		return res;
	
	}
	
	public List getColumnCommentsByTable(String tableName)throws AppException, SysException{
		Connection conn = null;
		List res=null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			res=dom.getColumnCommentsByTable(tableName);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
		return res;
	}
	
	public PagView getQueryInstanceList(QueryInstanceSVO i,PagInfo pagInfo) throws AppException, SysException{
		Connection conn = null;
		PagView res=null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			res=dom.getQueryInstanceList(i,pagInfo);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
		return res;
	}
	
	public Document getFuncMenuDoc(SysUserSVO user) throws AppException,SysException{
		Connection conn = null;
		Document  res=null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			res=dom.getFuncMenuDoc(user);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
		return res;
		
	}
	
	public List getFuncMenuList(SysUserSVO user) throws AppException, SysException{
		Connection conn = null;
		List  res=null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			res=dom.getFuncMenuList(user);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
		return res;
	}
	
	
	public HSSFWorkbook exportExcel(String instanceId,List conditionListFromPage) throws AppException,SysException{
		Connection conn = null;
		HSSFWorkbook  res=null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			res=dom.exportExcel(instanceId,conditionListFromPage);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
		return res;
	
	}
	
	
	public List getDataDicList(String tableName,String columnName) throws AppException,SysException{
		Connection conn = null;
		List  res=null;
		try {
			conn = ConnectionFactory.createConnection();
			conn.setAutoCommit(false);
			CtxdDOM dom=new CtxdDOM();
			res=dom.getDataDicList(tableName,columnName);
			ConnectionFactory.commit();
		} catch (SysException e1) { 
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
		return res;
	}
	
}
