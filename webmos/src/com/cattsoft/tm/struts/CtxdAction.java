/**
 * 
 */
package com.cattsoft.tm.struts;

import java.util.ArrayList;
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

import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.delegate.CtxdDelegate;
import com.cattsoft.webpub.util.ReqUtil;

/**
 * @author Xieyunchao
 * CreateTime 2015-09-10 上午10:55:59
 */
public class CtxdAction extends DispatchAction{
	
	public ActionForward initQueryPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tableId=request.getParameter("tableId");
		List queryConditionList = CtxdDelegate.getDelegate().getQueryConditionList(tableId,null);
		List queryColumnList = CtxdDelegate.getDelegate().getQueryColumnList(tableId);
		//List resList = CtxdDelegate.getDelegate().queryResult(tableId,null);
		request.setAttribute("resList", new PagView());
		request.setAttribute("conditionList", queryConditionList);
		request.setAttribute("queryColumnList", queryColumnList);
		request.setAttribute("tableId", tableId);
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
		String tableId=request.getParameter("tableId");
		String pageNo=request.getParameter("pageNo");
		String pagSize=request.getParameter("pagSize");
		if(pageNo==null)pageNo="1";
		if(pagSize==null)pagSize="20";
		PagInfo p=new PagInfo();
		p.setPagNo(Integer.parseInt(pageNo));
		p.setPagSize(Integer.parseInt(pagSize));
		Enumeration eNames = request.getParameterNames();
		String key="";
		List conditionListFromPage=new ArrayList();//从页面中获取查询条件值，通过匹配获得含有值的查询条件列表
		while (eNames.hasMoreElements()) {
			key = (String) eNames.nextElement();
			if(key.startsWith("QRY_")) {
				String value = request.getParameter(key);
				Map m=new HashMap();
				m.put("paraName", key.substring(4));
				m.put("value", value);
				conditionListFromPage.add(m);
			}
		}
		List queryConditionList = CtxdDelegate.getDelegate().getQueryConditionList(tableId,conditionListFromPage);
		List queryColumnList = CtxdDelegate.getDelegate().getQueryColumnList(tableId);
		
		PagView resList = CtxdDelegate.getDelegate().queryResult(tableId,conditionListFromPage,p);
		request.setAttribute("resList", resList);
		request.setAttribute("conditionList", queryConditionList);
		request.setAttribute("queryColumnList", queryColumnList);
		request.setAttribute("tableId", tableId);
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
			HttpServletResponse response) throws Exception{
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
	public ActionForward settingTable(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return  mapping.findForward("settingTable");
		
	}
	
	
	
}
