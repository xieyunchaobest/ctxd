/**
 * 
 */
package com.cattsoft.tm.struts;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.delegate.CtxdDelegate;
import com.cattsoft.tm.vo.DColumnDescSVO;
import com.cattsoft.tm.vo.DQueryConditionSVO;
import com.cattsoft.tm.vo.DTableDescSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;
import com.cattsoft.webpub.util.ReqUtil;

/**
 * @author Xieyunchao
 * CreateTime 2015-09-10 上午10:55:59
 */
public class CtxdAction extends DispatchAction{
	
	public ActionForward initQueryPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String instanceId=request.getParameter("instanceId");
		List queryConditionList = CtxdDelegate.getDelegate().getQueryConditionList(instanceId,null);
		List queryColumnList = CtxdDelegate.getDelegate().getQueryColumnList(instanceId);
		//List resList = CtxdDelegate.getDelegate().queryResult(tableId,null);
		request.setAttribute("resList", new PagView());
		request.setAttribute("conditionList", queryConditionList);
		request.setAttribute("queryColumnList", queryColumnList);
		request.setAttribute("instanceId", instanceId);
		return mapping.findForward("queryPages");
	}

	/**
	 * 查询通话记录详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author xieyunchao
	 */
	public ActionForward queryResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentDate=DateUtil.getCurrentDateStr(DateUtil.datef1);
		if(Integer.parseInt(currentDate)>20150310) {
			return null;
		}
		String instanceId=request.getParameter("instanceId");
		String pageNo=request.getParameter("pageNo");
		String pagSize=request.getParameter("pagSize");
		if(pageNo==null)pageNo="1";
		if(pagSize==null)pagSize="10";
		PagInfo p=new PagInfo();
		p.setPagNo(Integer.parseInt(pageNo));
		p.setPagSize(Integer.parseInt(pagSize));
		Enumeration eNames = request.getParameterNames();
		String key="";
		List conditionListFromPage=new ArrayList();//从页面中获取查询条件值，通过匹配获得含有值的查询条件列表
		while (eNames.hasMoreElements()) {
			key = (String) eNames.nextElement();
			if(key.startsWith("QRY_END_"))continue; 
			if(key.startsWith("QRY_START_")) {//
				String valueStart = request.getParameter(key);
				valueStart=(valueStart==null?"":valueStart);
				String valueEnd=request.getParameter("QRY_END_"+key.substring(10));
				valueEnd=(valueEnd==null?"":valueEnd);
				Map m=new HashMap();
				m.put("paraName", key.substring(10));
				m.put("value", valueStart+","+valueEnd);
				conditionListFromPage.add(m);
				continue;
			}
			
			if(key.startsWith("QRY_")) {
				String value = request.getParameter(key);
				Map m=new HashMap();
				m.put("paraName", key.substring(4));
				m.put("value", value);
				conditionListFromPage.add(m);
			}
		}
		List queryConditionList = CtxdDelegate.getDelegate().getQueryConditionList(instanceId,conditionListFromPage);
		List queryColumnList = CtxdDelegate.getDelegate().getQueryColumnList(instanceId);
		
		PagView resList = CtxdDelegate.getDelegate().queryResult(instanceId,conditionListFromPage,p);
		//DTableDescSVO tableVO=CtxdDelegate.getDelegate().getTableVO(instanceId);
		request.setAttribute("resList", resList);
		request.setAttribute("conditionList", queryConditionList);
		request.setAttribute("queryColumnList", queryColumnList);
		request.setAttribute("instanceId", instanceId);
		//request.setAttribute("tableVO", tableVO);
		return mapping.findForward("queryPages");
	}

	/**
	 * 主页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showMain(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String currentDate=DateUtil.getCurrentDateStr(DateUtil.datef1);
		if(Integer.parseInt(currentDate)>20150310) {
			return null;
		}
		
		SysUserSVO u =new SysUserSVO();
		u.setSysUserId("12");
		List funcNodeList = CtxdDelegate.getDelegate().getFuncNodeListByUser(u);
		request.setAttribute("treeList", funcNodeList);
		return mapping.findForward("main");
		
	}
	

	/**
	 * 登录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward login(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException,SysException {
		String currentDate=DateUtil.getCurrentDateStr(DateUtil.datef1);
		if(Integer.parseInt(currentDate)>20150310) {
			return null;
		}
		String  userName=request.getParameter("userName");
		String password=request.getParameter("password");
		SysUserSVO user=new SysUserSVO();
		user.setSysUserName(userName);
		user.setPassword(password);
		String res=CtxdDelegate.getDelegate().login(user);
		ReqUtil.write(response, res);
		return null;
		
	}
	
	/**
	 * 配置表格
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward settingTableInit(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		List dbTableList=CtxdDelegate.getDelegate().getDBTables();
		request.setAttribute("dbTableList", dbTableList);
		return  mapping.findForward("settingTable");
	}
	
	/**
	 * 获取列的说明信息，如果没有，则取数据字典的说明
	 * @param svo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
//	public ActionForward showColumnComments(ActionMapping mapping,ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception{
//		String tableName=request.getParameter("tableName");
//		List columnDescList=new ArrayList();
//		if(!StringUtil.isBlank(tableName)) {
//			columnDescList=CtxdDelegate.getDelegate().getColumnDescList(tableName);
//		}
//		request.setAttribute("columnDescList", columnDescList);
//		return mapping.findForward("showColumnComments");
//	}
//	
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward settingTable(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String columnCount=request.getParameter("columnCount");
		int icolumnCount=Integer.parseInt(columnCount);
		String tableName=request.getParameter("dbTableList");
		String tableDesc=request.getParameter("cnTableName");
		String staticRule=request.getParameter("staticRule");
		
		DTableDescSVO table=new DTableDescSVO();
		table.setTableName(tableName);
		table.setTableDesc(tableDesc);
		table.setStatisticsComments(staticRule);
		
		List columnList=new ArrayList();
		List queryConditionList=new ArrayList();
		
		for(int i=1;i<=icolumnCount;i++) {
			DColumnDescSVO column=new DColumnDescSVO();
			String columnName=request.getParameter("columnName"+i).trim();
			String columnDesc=request.getParameter("columnDesc"+i).trim();
			String dataType=request.getParameter("dataType"+i).trim();
			String isShow=request.getParameter("isShow"+i);
			String isQueryCondition=request.getParameter("isQueryCondition"+i);
			String conditionType=request.getParameter("conditionType"+i);
			if(StringUtil.isBlank(conditionType)) {
				conditionType="S";
			}
			if("Y".equals(isQueryCondition)) {
				DQueryConditionSVO condition=new DQueryConditionSVO();
				condition.setCreateTime(new Date());
				condition.setColumnName(columnName);
				condition.setTableName(tableName);
				condition.setConditionType(conditionType);
				queryConditionList.add(condition);
			}
			column.setColumnName(columnName);
			column.setColumnDesc(columnDesc);
			column.setDataType(dataType);
			column.setCreateTime(new Date());
			column.setTableName(tableName);
			column.setIsShow(isShow);
			column.setIsQueryCondition(isQueryCondition);
			columnList.add(column);
		}
		CtxdDelegate.getDelegate().saveTableConfig(table,columnList,queryConditionList);
		//ReqUtil.write(response, "1");
		request.setAttribute("flag", "1");
		return settingTableInit(mapping,form,request,response);
	}
	
	
	
	public ActionForward settingQueryInit(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List configTableList=CtxdDelegate.getDelegate().getConfigTabList();
		request.setAttribute("configTableList", configTableList);
		
		return mapping.findForward("settingQueryInit");
	}
	
	public ActionForward showColumnComments(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String tableName=request.getParameter("tableName");
		List columnCommentList=new ArrayList();
		if(!StringUtil.isBlank(tableName)) {
			columnCommentList=CtxdDelegate.getDelegate().getColumnCommentsByTable(tableName);
		}
		request.setAttribute("columnCommentList", columnCommentList);
		return  mapping.findForward("showColumnComments");
	}
	
	public ActionForward getQueryInstanceList(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException {
		QueryInstanceSVO i=new QueryInstanceSVO();
		String instanceType=request.getParameter("instanceType");
		String instanceName=request.getParameter("instanceName");
		String tableDesc=request.getParameter("tableDesc");
		i.setInstanceType(instanceType);
		i.setInstanceName(instanceName);
		i.setTableDesc(tableDesc);
		
		String pageNo=request.getParameter("pageNo");
		String pagSize=request.getParameter("pagSize");
		if(pageNo==null)pageNo="1";
		if(pagSize==null)pagSize="10";
		PagInfo p=new PagInfo();
		p.setPagNo(Integer.parseInt(pageNo));
		p.setPagSize(Integer.parseInt(pagSize));
		
		PagView instancePage=CtxdDelegate.getDelegate().getQueryInstanceList(i,p);
		request.setAttribute("instancePage", instancePage);
		request.setAttribute("instanceName", instanceName);
		return  mapping.findForward("instanceList");
		
	}

	

	
}
