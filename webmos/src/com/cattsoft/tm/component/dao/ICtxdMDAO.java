package com.cattsoft.tm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.SysUserSVO;

public interface ICtxdMDAO extends ISDAO{
	
	List queryResult(String tableId,List conditionListFromPage) throws AppException,SysException;
	
	/**
	 * 获取查询条件列表
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getQueryCondition(String tableId) throws AppException, SysException;
	
	/**
	 * 获取需要查询的列
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public  List getQueryColumnList(String tableId) throws AppException, SysException;
	
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

}
