package com.cattsoft.tm.component.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.vo.DTableDescSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;

public interface ICtxdMDAO extends ISDAO{
	
	PagView queryResult(String tableId,List conditionListFromPage,PagInfo pg,Map sortMap,String userId) throws AppException,SysException;
	
	/**
	 * 获取查询条件列表
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
//	public List getQueryCondition(String tableId) throws AppException, SysException;
//	
//	/**
//	 * 获取需要查询的列
//	 * @param tableId
//	 * @return
//	 * @throws AppException
//	 * @throws SysException
//	 */
//	public  List getQueryColumnList(String tableId) throws AppException, SysException;
	
	/**
	 * 获取查询条件列表,包含查询条件值
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getQueryCondition(String tableId,List conditionListFromPage) throws AppException, SysException;
	
	public List getFuncNodeListByUser(SysUserSVO vo) throws AppException,
	SysException ;
	
	public List getFuncNodeListByUserNew(SysUserSVO vo) throws AppException,
	SysException ;
	
	/**
	 * 获取当前数据库用户的表
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getDBTables()throws AppException, SysException;
	
	public DTableDescSVO getConfigTableInfo(String tableId)throws AppException, SysException;
	
	/**
	 * 获取列的说明信息，如果没有，则取数据字典的说明
	 * @param svo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getColumnDescList(String tableName)throws AppException, SysException;
	
	public List getColumnCommentsByTable(String tableName)throws AppException, SysException ;

	public PagView getQueryInstanceList(QueryInstanceSVO i,PagInfo pagInfo)throws AppException, SysException;
	
	public List getColumnList(String instacneId) throws AppException, SysException;
	
	/**
	 * 获取查询条件列表
	 * 
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getQueryConditionList(String instanceId) throws AppException,SysException;
	
	public List exportResult(String instanceId, List conditionListFromPage,Map sortMap,String userId) throws AppException, SysException ;
	
}
