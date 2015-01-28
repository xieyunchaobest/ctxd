package com.cattsoft.tm.component.domain;

import java.util.List;

import org.apache.struts.action.ActionForward;

import com.cattsoft.pub.ConstantsHelp;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.tm.component.dao.IFuncNodeSDAO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.component.dao.IDTableDescSDAO;
import com.cattsoft.tm.component.dao.IInstanceSettingMDAO;
import com.cattsoft.tm.component.dao.IQueryConditionSDAO;
import com.cattsoft.tm.component.dao.IQueryInstanceColumnSDAO;
import com.cattsoft.tm.component.dao.IQueryInstanceSDAO;
import com.cattsoft.tm.vo.DTableDescSVO;
import com.cattsoft.tm.vo.FuncNodeSVO;
import com.cattsoft.tm.vo.FuncNodeTreeSVO;
import com.cattsoft.tm.vo.QueryConditionSVO;
import com.cattsoft.tm.vo.QueryInstanceColumnSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;

public class InstanceSettingDOM {
	
	public List getTableColumns(String tableName) throws AppException,SysException{
		IInstanceSettingMDAO columnDAO= (IInstanceSettingMDAO) DAOFactory.getDAO(IInstanceSettingMDAO.class);
		return columnDAO.getTableColumns(tableName);
	}
	
	public List getTables() throws AppException,SysException{
		IDTableDescSDAO tableDAO= (IDTableDescSDAO) DAOFactory.getDAO(IDTableDescSDAO.class);
		return tableDAO.findByVO(new DTableDescSVO());
	}

	public void addInstance(QueryInstanceSVO instance,List queryInstanceColumnList,List queryConditionList) throws AppException,SysException{
		String instanceId=MaxId.getSequenceNextVal(ConstantsHelp.SEQ_QUERY_INSTANCE);
		instance.setQueryInstanceId(instanceId);
		
		IQueryInstanceSDAO instanceDAO= (IQueryInstanceSDAO) DAOFactory.getDAO(IQueryInstanceSDAO.class);
		IQueryInstanceColumnSDAO columnDAO= (IQueryInstanceColumnSDAO) DAOFactory.getDAO(IQueryInstanceColumnSDAO.class);
		IQueryConditionSDAO conditionDAO= (IQueryConditionSDAO) DAOFactory.getDAO(IQueryConditionSDAO.class);
		IFuncNodeSDAO nodeDAO= (IFuncNodeSDAO) DAOFactory.getDAO(IFuncNodeSDAO.class);
		
		instanceDAO.add(instance);
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
		
		//插入一个菜单
		String nodeId=MaxId.getSequenceNextVal(ConstantsHelp.SEQ_FUNC_NODE);
		FuncNodeSVO node=new FuncNodeSVO();
		node.setFuncNodeId(nodeId);
		node.setNodeTreeId(instance.getTreeId());
		node.setFuncNodeCode(nodeId);
		node.setFuncNodeName(instance.getInstanceName());
		node.setSubSystemName("BM");
		node.setSecurityLevel("1");
		node.setFuncNodeType("M");
		node.setHtml(ConstantsHelp.INSTANCEURL+instance.getQueryInstanceId());
		node.setVersion("1.0");
		node.setSts(ConstantsHelp.ACTIVE);
		node.setStsDate(DateUtil.getDBDate());
		nodeDAO.add(node);
		
	}
	
	public List getInstanceTypeList() throws AppException,SysException {
		com.cattsoft.tm.component.dao.IFuncNodeTreeSDAO treeSDAO = (com.cattsoft.tm.component.dao.IFuncNodeTreeSDAO) DAOFactory.getDAO(com.cattsoft.tm.component.dao.IFuncNodeTreeSDAO.class);
		FuncNodeTreeSVO atree=new FuncNodeTreeSVO();
		atree.setSts(ConstantsHelp.ACTIVE);
		return treeSDAO.findByVO(atree);
	}
	
	
	public void deleteInstance(String instanceId) throws AppException,SysException {
		IQueryInstanceSDAO instanceDAO= (IQueryInstanceSDAO) DAOFactory.getDAO(IQueryInstanceSDAO.class);
		IQueryInstanceColumnSDAO columnDAO= (IQueryInstanceColumnSDAO) DAOFactory.getDAO(IQueryInstanceColumnSDAO.class);
		IQueryConditionSDAO conditionDAO= (IQueryConditionSDAO) DAOFactory.getDAO(IQueryConditionSDAO.class);
		
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
	}
	
	public QueryInstanceSVO getQueryInstance(String instanceId) throws AppException,SysException {
		IQueryInstanceSDAO instanceDAO= (IQueryInstanceSDAO) DAOFactory.getDAO(IQueryInstanceSDAO.class);
		QueryInstanceSVO svo=new QueryInstanceSVO();
		svo.setQueryInstanceId(instanceId);
		return (QueryInstanceSVO)instanceDAO.findByPK(svo);
	}
	
}
