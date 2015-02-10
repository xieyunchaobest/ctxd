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
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.vo.SysUserMVO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.delegate.CtxdDelegate;
import com.cattsoft.tm.delegate.InstanceSettingDelegate;
import com.cattsoft.tm.vo.QueryConditionSVO;
import com.cattsoft.tm.vo.QueryInstanceColumnSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;
import com.cattsoft.tm.vo.QuerySortSVO;
import com.sun.tools.corba.se.idl.toJavaPortable.Helper;

/**
 * @author Xieyunchao
 * CreateTime 2015-09-10 上午10:55:59
 */
public class InstanceSettingAction extends DispatchAction{
	
	
	public ActionForward getQueryInstanceList(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException {
		QueryInstanceSVO i=new QueryInstanceSVO();
//		String instanceType=request.getParameter("instanceType");
		String instanceName=request.getParameter("instanceName");
//		String tableDesc=request.getParameter("tableDesc");
//		i.setInstanceType(instanceType);
		i.setInstanceName(instanceName);
//		i.setTableDesc(tableDesc);
		
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
		String typeFlag=request.getParameter("typeFlag");
		List tableList=InstanceSettingDelegate.getDelegate().getTables();
		List instanceTypeList=InstanceSettingDelegate.getDelegate().getInstanceTypeList();
		
		request.setAttribute("tableList", tableList);
		request.setAttribute("instanceTypeList", instanceTypeList);
		request.setAttribute("typeFlag", typeFlag);
		return mapping.findForward("addInstanceInit");

	}
	
	public ActionForward columnsInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException{
		String tableName=request.getParameter("tableName");
		String  typeFlag=request.getParameter("typeFlag");
		
		List columnList=new ArrayList();
		if(!StringUtil.isBlank(tableName)) {
			columnList=InstanceSettingDelegate.getDelegate().getTableColumns(tableName);
		}
		List bgColorList=CtxdDelegate.getDelegate().getDataDicList("QUERY_INSTANCE_COLUMN", "BG_COLOR");
		request.setAttribute("bgColorList",bgColorList);
		request.setAttribute("columnList", columnList);
		if(ConstantsHelp.INSTANCE_TYPE_COMMON.equals(typeFlag)) {
			return mapping.findForward("instanceColumnList");
		}else {
			return mapping.findForward("instanceColumnListGroup");
		}
		
	}
	
	public ActionForward addInstance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException {
		String tableName=request.getParameter("tableName");
		String instanceName=request.getParameter("instanceName");
		String columnCount=request.getParameter("columnCount");
		String treeId=request.getParameter("treeId");
		String  typeFlag=request.getParameter("typeFlag"); 
		String instanceId=request.getParameter("instanceId");
		
		Date d=new Date();
		
		QueryInstanceSVO instance=new QueryInstanceSVO();
		instance.setQueryInstanceId(instanceId);
		instance.setInstanceName(instanceName);
		instance.setInstanceType(typeFlag);
		instance.setTableName(tableName);
		instance.setCreateTime(d);
		instance.setSts(ConstantsHelp.ACTIVE);
		instance.setTreeId(treeId);
		
		String isSum=ConstantsHelp.NO;
		String isGroup=ConstantsHelp.NO;
		List columnList=new ArrayList();
		List conditionList=new ArrayList();
		List sortList=new ArrayList();
		
		if(ConstantsHelp.INSTANCE_TYPE_COMMON.equals(typeFlag)) {//通用查询
			for(int i=0;i<Integer.parseInt(columnCount);i++) {
				String isShow=request.getParameter("chkIsShow"+i);
				if(ConstantsHelp.YES.equals(isShow)) {
					QueryInstanceColumnSVO c=new QueryInstanceColumnSVO();
					String columnName=request.getParameter("columnName"+i);
					String seq=request.getParameter("seq"+i);
					String bgColor=request.getParameter("sltbgColor"+i);
					String width=request.getParameter("width"+i);
					String isSort=request.getParameter("isSort"+i);
					
					c.setColumnName(columnName);
					c.setCreateTime(d);
					c.setIsGroup(isGroup);
					c.setIsSum(isSum);
					c.setSeq(seq);
					c.setSts(ConstantsHelp.ACTIVE);
					c.setWidth(width);
					c.setBgColor(bgColor);
					c.setIsSort(isSort);
					
					columnList.add(c);
					
					String isQueryCondition=request.getParameter("chkIsCondition"+i);
					String queryConditionType=request.getParameter("sltConditionType"+i);
					if(ConstantsHelp.YES.equals(isQueryCondition)) {
						QueryConditionSVO condition=new QueryConditionSVO();
						condition.setColumnName(columnName);
						condition.setConditionType(queryConditionType);
						condition.setSts(ConstantsHelp.ACTIVE);
						condition.setCreateTime(d);
						conditionList.add(condition);
					}
					//排序
					if(ConstantsHelp.YES.equals(isSort)) {
						QuerySortSVO sort=new QuerySortSVO();
						sort.setQueryColumnName(columnName);
						sort.setSts(ConstantsHelp.ACTIVE);
						sort.setStsDate(d);
						
						sortList.add(sort);
					}
				}
				
				
			}
		}else {
			for(int i=0;i<Integer.parseInt(columnCount);i++) {
				//针对分组的情况
				String isSumG=request.getParameter("chkIsSum"+i);
				String isGroupG=request.getParameter("chkIsGroup"+i);
				String columnName=request.getParameter("columnName"+i);
				String seq=request.getParameter("seq"+i);
				String bgColor=request.getParameter("sltbgColor"+i);
				String width=request.getParameter("width"+i);
				String isSort=request.getParameter("isSort"+i);
				QueryInstanceColumnSVO c=null;
				if(ConstantsHelp.YES.equals(isGroupG)) {
					c=new QueryInstanceColumnSVO();
					c.setColumnName(columnName);
					c.setIsGroup(isGroupG);
					c.setIsSum(ConstantsHelp.NO);
					c.setCreateTime(d);
					c.setSeq(seq);
					c.setSts(ConstantsHelp.ACTIVE);
					//c.setIsSort(isSort);
					columnList.add(c);
				}
				
				
				if(ConstantsHelp.YES.equals(isSumG)) {
					c=new QueryInstanceColumnSVO();
					c.setColumnName(columnName);
					c.setIsGroup(ConstantsHelp.NO);
					c.setIsSum(isSumG);
					c.setCreateTime(d);
					c.setSeq(seq);
					c.setSts(ConstantsHelp.ACTIVE);
					columnList.add(c);
				}
				if(c!=null) {
					c.setWidth(width);
					c.setBgColor(bgColor);
				}
				
				
				String isQueryCondition=request.getParameter("chkIsCondition"+i);
				String queryConditionType=request.getParameter("sltConditionType"+i);
				if(ConstantsHelp.YES.equals(isQueryCondition)) {
					QueryConditionSVO condition=new QueryConditionSVO();
					condition.setColumnName(columnName);
					condition.setConditionType(queryConditionType);
					condition.setSts(ConstantsHelp.ACTIVE);
					condition.setCreateTime(d);
					conditionList.add(condition);
				}
				
				if(ConstantsHelp.YES.equals(isSort)) {
					QuerySortSVO st=new QuerySortSVO();
					st.setQueryColumnName(columnName);
					st.setSts(ConstantsHelp.ACTIVE);
					st.setStsDate(d);
					sortList.add(st);
				}
				
			}
		}
		
		InstanceSettingDelegate.getDelegate().addInstance(instance, columnList, conditionList,sortList);
		return getQueryInstanceList(mapping,form,request,response);
	}

	public ActionForward delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException {
		String instanceId=request.getParameter("instanceId");
		InstanceSettingDelegate.getDelegate().deleteInstance(instanceId);
		return getQueryInstanceList(mapping,form,request,response);
	}
	
	
	public ActionForward editInstanceMainInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException {
		String instanceId=request.getParameter("instanceId");
		String  typeFlag=request.getParameter("typeFlag"); 
		QueryInstanceSVO instance=InstanceSettingDelegate.getDelegate().getQueryInstance(instanceId);
		List instanceTypeList=InstanceSettingDelegate.getDelegate().getInstanceTypeList();
		QueryInstanceSVO inst=InstanceSettingDelegate.getDelegate().getTableByInstanceId(instanceId);
		request.setAttribute("instance", instance);   
		request.setAttribute("instanceTypeList", instanceTypeList); 
		request.setAttribute("typeFlag", typeFlag);
		request.setAttribute("inst", inst);
		
		return mapping.findForward("editInstanceInit");
	}
	
	public ActionForward editInstanceColumnsInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException, SysException {
		String instanceId=request.getParameter("instanceId");
		String typeFlag=request.getParameter("typeFlag");
		QueryInstanceSVO instance=InstanceSettingDelegate.getDelegate().getQueryInstance(instanceId);
		List columnList=InstanceSettingDelegate.getDelegate().getQueryConfigColumnList(instanceId);
		List instanceTypeList=InstanceSettingDelegate.getDelegate().getInstanceTypeList();
		request.setAttribute("instance", instance); 
		request.setAttribute("columnList", columnList); 
		List bgColorList=CtxdDelegate.getDelegate().getDataDicList("QUERY_INSTANCE_COLUMN", "BG_COLOR");
		request.setAttribute("bgColorList", bgColorList);
		request.setAttribute("instanceTypeList", instanceTypeList); 
		if(ConstantsHelp.INSTANCE_TYPE_COMMON.equals(typeFlag)) {
			return mapping.findForward("editInstanceSub");
		}else {
			return mapping.findForward("editInstanceSubGroup");
		}
		
	}
	

}
