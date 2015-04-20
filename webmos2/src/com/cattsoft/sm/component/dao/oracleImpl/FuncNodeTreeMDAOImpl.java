package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.sm.component.dao.IFuncNodeTreeMDAO;
import com.cattsoft.sm.vo.FuncNodeSVO;
import com.cattsoft.sm.vo.FuncNodeTreeSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-27 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class FuncNodeTreeMDAOImpl implements IFuncNodeTreeMDAO {

	// private static Log log=LogFactory.getLog(FuncNodeTreeMDAOImpl.class);

	public List findFuncNodeTreeVOs(List fnvs) throws SysException, AppException {

		FuncNodeSVO fnv = null;
		StringBuffer hql = new StringBuffer();
		int nt = 0; // nodeTreeId count;

		for (int i = 0; i < fnvs.size(); i++) { // get nodeTreeId String,like 101,103
			fnv = (FuncNodeSVO) fnvs.get(i);
			if (fnv.getNodeTreeId() != null) {
				nt++;
				if (i == fnvs.size() - 1)
					hql.append(fnv.getNodeTreeId());
				else
					hql.append(fnv.getNodeTreeId() + ",");
			}
		}
		if (nt == 0)
			return (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST); // no nodeTreeId exist.
		StringBuffer hqlStr = new StringBuffer();
		hqlStr
				.append("select fnt.NODE_TREE_ID,fnt.NODE_TREE_NAME,fnt.PARENT_NODE_TREE_ID,fnt.NODE_TREE_CODE,fnt.DESCRIPTION,fnt.HTML,fnt.FILE_NAME from Func_Node_Tree fnt where fnt.node_Tree_Id>0 and (fnt.node_Tree_Id in( ");

		for (int i = 0; i < fnvs.size(); i++) {
			fnv = (FuncNodeSVO) fnvs.get(i);
			if (fnv.getNodeTreeId() != null) {
				if (i == 0)
					hqlStr.append("?");
				else
					hqlStr.append(",?");
			}
		}
		hqlStr.append(") or fnt.node_Tree_Id in(");
		hqlStr
				.append("select fnts.parent_node_tree_Id from Func_Node_Tree fnts where  fnts.node_Tree_Id in( ");
		for (int i = 0; i < fnvs.size(); i++) {
			fnv = (FuncNodeSVO) fnvs.get(i);
			if (fnv.getNodeTreeId() != null) {
				if (i == 0)
					hqlStr.append("?");
				else
					hqlStr.append(",?");
			}
		}

		hqlStr.append("))) order by fnt.node_Tree_Code");
		List result = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(hqlStr.toString());
			int i = 0;
			for (; i < fnvs.size();) {
				fnv = (FuncNodeSVO) fnvs.get(i);
				ps.setString(++i, fnv.getNodeTreeId());
			}
			for (int j = 0; j < fnvs.size(); j++) {
				fnv = (FuncNodeSVO) fnvs.get(j);
				ps.setString(++i, fnv.getNodeTreeId());
			}
			rs = ps.executeQuery();
			result = (List) ResultSetUtil.convertToList(rs, FuncNodeTreeSVO.class);
		} catch (SQLException e) {
			throw new SysException("", "findFuncNodeTreeVOs error..", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

}
