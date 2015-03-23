/**
 * 
 */
package com.cattsoft.tm.struts;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cattsoft.pub.ConstantsHelp;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.delegate.CtxdDelegate;
import com.cattsoft.tm.delegate.InstanceSettingDelegate;
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
		if(Integer.parseInt(currentDate)>20150610) {
			return null;
		}
		String instanceId=request.getParameter("instanceId");
		String sortBy=request.getParameter("sortBy");//排序字段
		String sortRule=request.getParameter("sortRule");
		Map sortMap=new HashMap();
		sortMap.put("sortBy", sortBy);
		sortMap.put("sortRule",sortRule);
		
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
		SysUserSVO u=(SysUserSVO)request.getSession().getAttribute("user");
		
		PagView resList = CtxdDelegate.getDelegate().queryResult(instanceId,conditionListFromPage,p,sortMap,u.getSysUserId());
		QueryInstanceSVO instance=InstanceSettingDelegate.getDelegate().getQueryInstance(instanceId);
		DTableDescSVO tableVO=CtxdDelegate.getDelegate().getTableVO(instance.getTableName());
		request.setAttribute("resList", resList);
		request.setAttribute("conditionList", queryConditionList);
		request.setAttribute("queryColumnList", queryColumnList);
		request.setAttribute("instanceId", instanceId);
		//request.setAttribute("instance", instance);
		request.setAttribute("tableVO", tableVO);
		request.setAttribute("sortBy", sortBy);
		request.setAttribute("sortRule", sortRule);
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
			HttpServletResponse response) throws AppException,SysException {
		String currentDate=DateUtil.getCurrentDateStr(DateUtil.datef1);
		if(Integer.parseInt(currentDate)>20150610) {
			return null;
		}
		SysUserSVO u=(SysUserSVO)request.getSession().getAttribute("user");
		List funcMenuList = CtxdDelegate.getDelegate().getFuncMenuList(u);
		String funcJson=JSON.toJSONString(funcMenuList,SerializerFeature.DisableCircularReferenceDetect);
		request.setAttribute("treeJson", funcJson);
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
			HttpServletResponse response) throws SysException,AppException {
		String currentDate=DateUtil.getCurrentDateStr(DateUtil.datef1);
		if(Integer.parseInt(currentDate)>20150610) {
			return null;
		}
		String frompage=request.getParameter("frompage");
		SysUserSVO user=new SysUserSVO();
		if(ConstantsHelp.FROM_PAGE_OA.equals(frompage)) {//从oa系统进入
			String erpno=request.getParameter("erpno");
			if(StringUtil.isBlank(erpno)) {
				throw new AppException("1212", "用户不存在或没有访问权限！");
			}
			user.setErpno(erpno);
			String res=CtxdDelegate.getDelegate().login(user,request);
			if("S".equals(res)) { //如果成功放入权限信息
				return showMain(mapping,form,request,response);
			}else {
				throw new AppException("1212", "用户不存在或没有访问权限！");
			}
			
		}else {
			String  userName=request.getParameter("userName");
			String password=request.getParameter("password");
			user.setSysUserName(userName);
			user.setPassword(password);
			String res=CtxdDelegate.getDelegate().login(user,request);
			
			ReqUtil.write(response, res);
			return null;
		}
		
		
		
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
			HttpServletResponse response) throws SysException,AppException{
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
	
	
	
		public ActionForward getFuncNodeXML(ActionMapping mapping,ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws SysException,AppException {
		 SysUserSVO user=new SysUserSVO();
		 user.setSysUserId("12");
		 Document  doc=CtxdDelegate.getDelegate().getFuncMenuDoc(user);
		
			response.setContentType("text/plain;charset=UTF-8");
			response.setHeader("Cache-control", "no-cache");
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			try {
				PrintWriter out = response.getWriter();
				XMLWriter writer = new XMLWriter(out, format);
				writer.write(doc);
				out.flush();
				out.close();
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			log.debug("userTree：" + doc.asXML());
			return null;
	 }
	
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
			HttpServletResponse response) throws SysException,AppException {
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

	public ActionForward loginOut(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SysException,AppException {
		request.getSession().removeAttribute("user");
		return  mapping.findForward("loginout");
		//return null;
	}
	
	
	
 
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author xieyunchao
	 */
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentDate=DateUtil.getCurrentDateStr(DateUtil.datef1);
		if(Integer.parseInt(currentDate)>20150610) {
			return null;
		}
		String instanceId=request.getParameter("instanceId");
		String pageNo=request.getParameter("pageNo");
		String pagSize=request.getParameter("pagSize");
		String sortBy=request.getParameter("sortBy");//排序字段
		String sortRule=request.getParameter("sortRule");
		Map sortMap=new HashMap();
		sortMap.put("sortBy", sortBy);
		sortMap.put("sortRule",sortRule);
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
		SysUserSVO u=(SysUserSVO)request.getSession().getAttribute("user");
		HSSFWorkbook book = CtxdDelegate.getDelegate().exportExcel(instanceId,conditionListFromPage,sortMap,u.getSysUserId());
		QueryInstanceSVO instance=InstanceSettingDelegate.getDelegate().getQueryInstance(instanceId);
		String instanceName=instance.getInstanceName();
		instanceName = URLEncoder.encode(instanceName,"UTF8");
		response.setContentType("application/vnd.ms-excel;charset=GBK");  
        response.setHeader("Content-disposition", "attachment;filename="+instanceName+".xls");  
        OutputStream ouputStream = response.getOutputStream();  
        book.write(ouputStream);  
        ouputStream.flush();  
        ouputStream.close();  
        return null;
	}

	
}
