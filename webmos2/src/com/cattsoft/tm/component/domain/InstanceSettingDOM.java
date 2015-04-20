package com.cattsoft.tm.component.domain;

import java.util.List;

import com.cattsoft.pub.ConstantsHelp;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.tm.component.dao.IDTableDescSDAO;
import com.cattsoft.tm.component.dao.IFuncMenuSDAO;
import com.cattsoft.tm.component.dao.IFuncNodeSDAO;
import com.cattsoft.tm.component.dao.IInstanceSettingMDAO;
import com.cattsoft.tm.component.dao.IQueryConditionSDAO;
import com.cattsoft.tm.component.dao.IQueryInstanceColumnSDAO;
import com.cattsoft.tm.component.dao.IQueryInstanceSDAO;
import com.cattsoft.tm.component.dao.IQuerySortSDAO;
import com.cattsoft.tm.component.dao.ISysDataPrivSDAO;
import com.cattsoft.tm.vo.DTableDescSVO;
import com.cattsoft.tm.vo.FuncMenuSVO;
import com.cattsoft.tm.vo.QueryConditionSVO;
import com.cattsoft.tm.vo.QueryInstanceColumnSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;
import com.cattsoft.tm.vo.QuerySortSVO;
import com.cattsoft.tm.vo.SysDataPrivSVO;

public class InstanceSettingDOM {
	
	public List getTableColumns(String tableName) throws AppException,SysException{
		IInstanceSettingMDAO columnDAO= (IInstanceSettingMDAO) DAOFactory.getDAO(IInstanceSettingMDAO.class);
		return columnDAO.getTableColumns(tableName);
	}
	
	public List getTables() throws AppException,SysException{
		IDTableDescSDAO tableDAO= (IDTableDescSDAO) DAOFactory.getDAO(IDTableDescSDAO.class);
		return tableDAO.findByVO(new DTableDescSVO());
	}

	public void addInstance(QueryInstanceSVO instance,List queryInstanceColumnList,List queryConditionList,List sortList) throws AppException,SysException{
		IQueryInstanceSDAO instanceDAO= (IQueryInstanceSDAO) DAOFactory.getDAO(IQueryInstanceSDAO.class);
		IQueryInstanceColumnSDAO columnDAO= (IQueryInstanceColumnSDAO) DAOFactory.getDAO(IQueryInstanceColumnSDAO.class);
		IQueryConditionSDAO conditionDAO= (IQueryConditionSDAO) DAOFactory.getDAO(IQueryConditionSDAO.class);
		IFuncNodeSDAO nodeDAO= (IFuncNodeSDAO) DAOFactory.getDAO(IFuncNodeSDAO.class);
		IQuerySortSDAO sortDAO= (IQuerySortSDAO) DAOFactory.getDAO(IQuerySortSDAO.class);
		IFuncMenuSDAO menuDAO= (IFuncMenuSDAO) DAOFactory.getDAO(IFuncMenuSDAO.class);
		
		String instanceId="";
		if(!StringUtil.isBlank(instance.getQueryInstanceId())) {//不为空则更新
			instanceId=instance.getQueryInstanceId();
			instanceDAO.update(instance);	
			
			QueryInstanceColumnSVO column=new QueryInstanceColumnSVO();
			column.setInstanceId(instanceId);
			List columnList=columnDAO.findByVO(column);
			if(columnList!=null) {
				for(int i=0;i<columnList.size();i++) {
					QueryInstanceColumnSVO c=(QueryInstanceColumnSVO)columnList.get(i);
					columnDAO.delete(c);
				}
			}
			
			QueryConditionSVO condition =new QueryConditionSVO();
			condition.setInstanceId(instanceId);
			List conditionList=conditionDAO.findByVO(condition);
			if(conditionList!=null) {
				for(int i=0;i<conditionList.size();i++) {
					QueryConditionSVO c=(QueryConditionSVO)conditionList.get(i);
					conditionDAO.delete(c);
				}
			}
			
			QuerySortSVO sort=new QuerySortSVO();
			sort.setQueryInstanceId(instanceId);
			List sortLst=sortDAO.findByVO(sort);
			if(sortLst!=null) {
				for(int i=0;i<sortLst.size();i++) {
					QuerySortSVO c=(QuerySortSVO)sortLst.get(i);
					sortDAO.delete(c);
				}
			}
			//nodeDAO.deleteByInstanceId(Long.parseLong(instanceId));
		}else {
			instanceId=MaxId.getSequenceNextVal(ConstantsHelp.SEQ_QUERY_INSTANCE);
			instance.setQueryInstanceId(instanceId);
			instanceDAO.add(instance);
			
			FuncMenuSVO fm=new FuncMenuSVO();
			fm.setFuncMenuId(instance.getTreeId());
			FuncMenuSVO funm=(FuncMenuSVO)menuDAO.findByPK(fm);
			
			FuncMenuSVO funcMenu=new FuncMenuSVO();
			funcMenu.setFuncMenuId(MaxId.getSequenceNextVal(ConstantsHelp.FUNC_MENU_SEQ));
			funcMenu.setDepth( (Integer.parseInt(funm.getDepth())+1)+"" );
			funcMenu.setFuncMenuName(instance.getInstanceName());
			funcMenu.setHtml(ConstantsHelp.INSTANCEURL+instance.getQueryInstanceId());
			funcMenu.setInstanceId(instanceId);
			funcMenu.setParentId(instance.getTreeId());
			funcMenu.setSts(ConstantsHelp.ACTIVE);
			funcMenu.setStsDate(DateUtil.getDBDate());
			menuDAO.add(funcMenu);
			
			
//			//插入一个菜单
//			String nodeId=MaxId.getSequenceNextVal(ConstantsHelp.SEQ_FUNC_NODE);
//			FuncNodeSVO node=new FuncNodeSVO();
//			node.setFuncNodeId(nodeId);
//			node.setNodeTreeId(instance.getTreeId());
//			node.setFuncNodeCode(nodeId);
//			node.setFuncNodeName(instance.getInstanceName());
//			node.setSubSystemName("BM");
//			node.setSecurityLevel("1");
//			node.setFuncNodeType("M");
//			node.setHtml(ConstantsHelp.INSTANCEURL+instance.getQueryInstanceId());
//			node.setVersion("1.0");
//			node.setSts(ConstantsHelp.ACTIVE);
//			node.setStsDate(DateUtil.getDBDate());
//			node.setInstanceId(instanceId);
//			nodeDAO.add(node);
			
		}
		
		if(queryInstanceColumnList!=null) {
			for(int i=0;i<queryInstanceColumnList.size();i++) {
				QueryInstanceColumnSVO c=(QueryInstanceColumnSVO)queryInstanceColumnList.get(i);
				c.setQueryInstanceColumnId(MaxId.getSequenceNextVal(ConstantsHelp.SEQ_UERY_INSTANCE_COLUMN));
				c.setInstanceId(instanceId);
				columnDAO.add(c);
			}
		}
		
		if(queryConditionList!=null) {
			for(int i=0;i<queryConditionList.size();i++) {
				QueryConditionSVO con=(QueryConditionSVO)queryConditionList.get(i);
				con.setQueryConditionId(MaxId.getSequenceNextVal(ConstantsHelp.SEQ_QUERY_CONDITION_SEQ));
				con.setInstanceId(instanceId);
				conditionDAO.add(con);
			}
		}
		
		if(sortList!=null) {
			for(int i=0;i<sortList.size();i++) {
				QuerySortSVO sort=(QuerySortSVO)sortList.get(i);
				sort.setQueryInstanceId(instanceId);
				sort.setQuerySortId(MaxId.getSequenceNextVal(ConstantsHelp.SEQ_QUERY_SORT_SEQ));
				sortDAO.add(sort);
			}
		}
		
		
		
	}
	
	public List getInstanceTypeList() throws AppException,SysException {
		//com.cattsoft.tm.component.dao.IFuncNodeTreeSDAO treeSDAO = (com.cattsoft.tm.component.dao.IFuncNodeTreeSDAO) DAOFactory.getDAO(com.cattsoft.tm.component.dao.IFuncNodeTreeSDAO.class);
		//FuncNodeTreeSVO atree=new FuncNodeTreeSVO();
		//atree.setSts(ConstantsHelp.ACTIVE);
		//return treeSDAO.findByVO(atree);
		IFuncMenuSDAO menuDAO= (IFuncMenuSDAO) DAOFactory.getDAO(IFuncMenuSDAO.class);
		FuncMenuSVO menu=new FuncMenuSVO();
		menu.setSts(ConstantsHelp.ACTIVE);
		return menuDAO.findByVO(menu);
		
	}
	
	
	public void deleteInstance(String instanceId) throws AppException,SysException {
		IQueryInstanceSDAO instanceDAO= (IQueryInstanceSDAO) DAOFactory.getDAO(IQueryInstanceSDAO.class);
		IQueryInstanceColumnSDAO columnDAO= (IQueryInstanceColumnSDAO) DAOFactory.getDAO(IQueryInstanceColumnSDAO.class);
		IQueryConditionSDAO conditionDAO= (IQueryConditionSDAO) DAOFactory.getDAO(IQueryConditionSDAO.class);
		//IFuncNodeSDAO nodeDAO= (IFuncNodeSDAO) DAOFactory.getDAO(IFuncNodeSDAO.class);
		IFuncMenuSDAO menuDAO= (IFuncMenuSDAO) DAOFactory.getDAO(IFuncMenuSDAO.class);
		IQuerySortSDAO sortDAO= (IQuerySortSDAO) DAOFactory.getDAO(IQuerySortSDAO.class);
		
		QueryInstanceSVO instance=new QueryInstanceSVO();
		instance.setQueryInstanceId(instanceId);
		instanceDAO.delete(instance);
		
		QueryInstanceColumnSVO column=new QueryInstanceColumnSVO();
		column.setInstanceId(instanceId);
		List columnList=columnDAO.findByVO(column);
		if(columnList!=null) {
			for(int i=0;i<columnList.size();i++) {
				QueryInstanceColumnSVO c=(QueryInstanceColumnSVO)columnList.get(i);
				columnDAO.delete(c);
			}
		}
		
		QueryConditionSVO condition =new QueryConditionSVO();
		condition.setInstanceId(instanceId);
		List conditionList=conditionDAO.findByVO(condition);
		if(conditionList!=null) {
			for(int i=0;i<conditionList.size();i++) {
				QueryConditionSVO c=(QueryConditionSVO)conditionList.get(i);
				conditionDAO.delete(c);
			}
		}
		menuDAO.deleteByInstanceId(instanceId);
		
		QuerySortSVO sort=new QuerySortSVO();
		sort.setQueryInstanceId(instanceId);
		List sortList=sortDAO.findByVO(sort);
		if(sortList!=null) {
			for(int i=0;i<sortList.size();i++) {
				QuerySortSVO s=(QuerySortSVO)sortList.get(i);
				sortDAO.delete(s);
			}
			
		}
		
		menuDAO.deleteByInstanceId(instanceId);
	}
	
	public QueryInstanceSVO getQueryInstance(String instanceId) throws AppException,SysException {
		IQueryInstanceSDAO instanceDAO= (IQueryInstanceSDAO) DAOFactory.getDAO(IQueryInstanceSDAO.class);
		QueryInstanceSVO svo=new QueryInstanceSVO();
		svo.setQueryInstanceId(instanceId);
		return (QueryInstanceSVO)instanceDAO.findByPK(svo);
	}
	
	/**
	 * 获取某一实例中列的信息
	 * @param instacneId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getQueryConfigColumnList(String instanceId) throws AppException,
			SysException {
		IInstanceSettingMDAO instanceDAO= (IInstanceSettingMDAO) DAOFactory.getDAO(IInstanceSettingMDAO.class);
		return instanceDAO.getQueryConfigColumnList(instanceId);
	}
	
	public QueryInstanceSVO getTableByInstanceId(String instanceId)
			throws AppException, SysException{
		IInstanceSettingMDAO instanceDAO= (IInstanceSettingMDAO) DAOFactory.getDAO(IInstanceSettingMDAO.class);
		return instanceDAO.getTableByInstanceId(instanceId);
	}
	
	public List getSysPrivList() throws AppException,
	SysException {
		ISysDataPrivSDAO dao=(ISysDataPrivSDAO)DAOFactory.getDAO(ISysDataPrivSDAO.class);
		return dao.findByVO(new SysDataPrivSVO());
	}
}
