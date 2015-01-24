/**
 * 
 */
package com.cattsoft.tm.struts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.cattsoft.pub.ConstantsHelp;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.tm.delegate.InstanceSettingDelegate;
import com.cattsoft.tm.vo.QueryConditionSVO;
import com.cattsoft.tm.vo.QueryInstanceColumnSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;

/**
 * @author Xieyunchao
 * CreateTime 2015-09-10 ÉÏÎç10:55:59
 */
public class InstanceSettingAction extends DispatchAction{
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public ActionForward addInstanceInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException {
		String tableName=request.getParameter("tableName");
		List tableList=InstanceSettingDelegate.getDelegate().getTables();
		request.setAttribute("tableList", tableList);
		return mapping.findForward("addInstanceInit");

	}
	
	public ActionForward columnsInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException{
		String tableName=request.getParameter("tableName");
		List columnList=new ArrayList();
		if(!StringUtil.isBlank(tableName)) {
			columnList=InstanceSettingDelegate.getDelegate().getTableColumns(tableName);
		}
		request.setAttribute("columnList", columnList);
		return mapping.findForward("instanceColumnList");
	}
	
	public ActionForward addInstance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException {
		String tableName=request.getParameter("tableName");
		String instanceName=request.getParameter("instanceName");
		String instanceType=request.getParameter("instanceType");
		String columnCount=request.getParameter("columnCount");
		String isQueryCondition=request.getParameter("chkIsCondition");
		String queryConditionType=request.getParameter("sltConditionType");
		
		Date d=new Date();
		
		QueryInstanceSVO instance=new QueryInstanceSVO();
		instance.setInstanceName(instanceName);
		instance.setInstanceType(instanceType);
		instance.setTableName(tableName);
		instance.setCreateTime(d);
		instance.setSts(ConstantsHelp.ACTIVE);
		
		String isSum=ConstantsHelp.NO;
		String isGroup=ConstantsHelp.NO;
		List columnList=new ArrayList();
		List conditionList=new ArrayList();
		
		
		for(int i=0;i<Integer.parseInt(columnCount);i++) {
			String isShow=request.getParameter("chkIsShow"+i);
			if(ConstantsHelp.YES.equals(isShow)) {
				QueryInstanceColumnSVO c=new QueryInstanceColumnSVO();
				String columnName=request.getParameter("columnName"+i);
				String seq=request.getParameter("seq"+i);
				c.setColumnName(columnName);
				c.setCreateTime(d);
				c.setIsGroup(isGroup);
				c.setIsSum(isSum);
				c.setSeq(seq);
				c.setSts(ConstantsHelp.ACTIVE);
				columnList.add(c);
				
				if(ConstantsHelp.YES.equals(isQueryCondition)) {
					QueryConditionSVO condition=new QueryConditionSVO();
					condition.setColumnName(columnName);
					condition.setConditionType(queryConditionType);
					condition.setSts(ConstantsHelp.ACTIVE);
					condition.setCreateTime(d);
					conditionList.add(condition);
				}
			}
		}
		
		return mapping.findForward("addInstanceInit");
	}



}
