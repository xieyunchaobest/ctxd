package com.cattsoft.tm.component.domain;


import java.util.List;
import java.util.Map;

import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.tm.component.dao.ICtxdMDAO;

public class CtxdDOM {
	public List queryResult(String tableId,List conditionListFromPage) throws AppException,SysException{
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List res=ctxddao.queryResult(tableId,conditionListFromPage);
		return res;
	}
	
	
	
	public List getQueryConditionList(String tableId,List conditionListFromPage)throws AppException,SysException{
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List conditionList=ctxddao.getQueryCondition(tableId,conditionListFromPage);
		
		return conditionList;
	}
	
	
	/**
	 * 获取需要查询的列
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public  List getQueryColumnList(String tableId) throws AppException, SysException{
		ICtxdMDAO ctxddao = (ICtxdMDAO) DAOFactory.getDAO(ICtxdMDAO.class);
		List conditionList=ctxddao.getQueryColumnList(tableId);
		return conditionList;
	}
}
