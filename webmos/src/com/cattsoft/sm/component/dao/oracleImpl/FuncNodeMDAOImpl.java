package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.sm.component.dao.IFuncNodeMDAO;
import com.cattsoft.sm.vo.FuncNodeSVO;
import com.cattsoft.sm.vo.MosFuncNodeSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class FuncNodeMDAOImpl implements IFuncNodeMDAO {
	
	public List findFuncNodeForLogin4MOS(String sysUserId, String localNetId)
			throws SysException, AppException {

		if (sysUserId == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql();
		sql
				.append("SELECT  DISTINCT E.FUNC_NODE_ID,"
						+ "      E.NODE_TREE_ID,"
						+ "      E.FUNC_NODE_CODE,"
						+ "      E.SUB_SYSTEM_NAME,"
						+ "      E.FUNC_NODE_NAME,"
						+ "      E.FUNC_NODE_TYPE,"
						+ "      E.HTML,"
						+ "      G.ALLOC_AUTH "
						+ " FROM FUNC_NODE E,SYS_USER_ALLOC G  WHERE E.FUNC_NODE_ID = G.FUNC_NODE_ID "
						+ "          AND (G.ALLOC_AUTH = 'T' OR G.ALLOC_AUTH = 'U') "
						+ "          AND G.SYS_USER_ID = :sysUserId "
						+ "          AND G.STS = 'A' "
						+ "          AND E.FUNC_NODE_TYPE IN ('M', 'H') "
						+ "          AND E.STS = 'A' and  E.FUNC_NODE_ID>=6000000 and E.FUNC_NODE_ID < 6000100  ");

		try {

			sql.setString("sysUserId", sysUserId);
			sql.append(" order by  E.FUNC_NODE_CODE");
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			MosFuncNodeSVO funcNodeSVO = null;
			while (rs.next()) {
				funcNodeSVO = new MosFuncNodeSVO();
				funcNodeSVO.setName(rs.getString("FUNC_NODE_NAME"));
				funcNodeSVO.setFunccLevel(rs.getString("ALLOC_AUTH"));
				funcNodeSVO.setShortName(rs.getString("FUNC_NODE_ID"));
				funcNodeSVO.setType(rs.getString("FUNC_NODE_TYPE"));
				funcNodeSVO.setMosFuncNodeId(rs.getString("FUNC_NODE_ID"));
				res.add(funcNodeSVO);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		if (0 == res.size())
			res = null;
		return res;

	}
	
	public List findFuncNodeForLogin(String sysUserId,String localNetId) throws SysException, AppException {

        Sql sql = new Sql();

        sql
                .append("SELECT e.func_node_id, e.node_tree_id, e.func_node_code,e.sub_system_name,e.func_node_name,e.func_node_type,e.html ");
        sql.append("FROM func_node e WHERE exists  ( ");
        sql
                .append("SELECT 1 FROM sys_user_alloc g WHERE e.func_node_id=g.func_node_id AND (g.alloc_auth = 'T' OR g.alloc_auth = 'U') ");
        sql.append(" AND g.sys_user_id = ? AND g.entrust_flag != 'P' ");
        sql.append(" AND g.sts = 'A') AND e.func_node_type IN ( 'M','H') AND e.sts = 'A' ");
        
        
//        sql.append(" UNION ");
//        sql.append(" SELECT e.func_node_id, e.node_tree_id, e.func_node_code,e.sub_system_name,e.func_node_name,e.func_node_type,e.html ");
//        sql.append(" FROM func_node e ");
//        sql.append(" WHERE EXISTS ( SELECT 1 FROM sys_role_alloc a, sys_role b, sys_user_role d WHERE e.func_node_id= a.func_node_id ");
//        sql.append(" AND a.sys_role_id = b.sys_role_id AND b.sys_role_id = d.sys_role_id AND a.func_node_id NOT IN ( ");
//        sql.append(" SELECT h.func_node_id FROM constraint_and_priviledge h WHERE h.sys_user_role_id = d.sys_user_role_id) ");
//        sql.append(" AND d.sys_user_id = ? ");
//        sql.append(" AND d.sts = 'A' AND b.sts='A' AND (a.allow_auth = 'T' OR a.allow_auth = 'U') AND a.sts = 'A') AND e.func_node_type IN ('M','H') AND e.sts = 'A' ");

        List ret = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(localNetId));
            //ps = connection.prepareStatement(sql.getSql());
            sql.setString(1, sysUserId);
//            sql.setString(2, sysUserId);
            sql.fillParams(ps);
            sql.logPartition(localNetId, FuncNodeMDAOImpl.class);
            //sql.log(this.getClass());
            rs = ps.executeQuery();
            ret = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findFuncNodeForLogin error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return ret;

    }
	
	public List findFuncNodeForOssLogin(String sysUserId,String localNetId) throws SysException, AppException {

        Sql sql = new Sql();

        sql
                .append("SELECT e.func_node_id, e.node_tree_id, e.func_node_code,e.sub_system_name,e.func_node_name,e.func_node_type,e.html ");
        sql.append("FROM func_node e WHERE exists  ( ");
        sql
                .append("SELECT 1 FROM sys_user_alloc g WHERE e.func_node_id=g.func_node_id AND (g.alloc_auth = 'T' OR g.alloc_auth = 'U') ");
        sql.append(" AND g.sys_user_id = ? AND g.entrust_flag = 'P' ");
        sql.append(" AND g.sts = 'A') AND e.func_node_type IN ( 'M','H') AND e.sts = 'A' ");
        
        
//        sql.append(" UNION ");
//        sql.append(" SELECT e.func_node_id, e.node_tree_id, e.func_node_code,e.sub_system_name,e.func_node_name,e.func_node_type,e.html ");
//        sql.append(" FROM func_node e ");
//        sql.append(" WHERE EXISTS ( SELECT 1 FROM sys_role_alloc a, sys_role b, sys_user_role d WHERE e.func_node_id= a.func_node_id ");
//        sql.append(" AND a.sys_role_id = b.sys_role_id AND b.sys_role_id = d.sys_role_id AND a.func_node_id NOT IN ( ");
//        sql.append(" SELECT h.func_node_id FROM constraint_and_priviledge h WHERE h.sys_user_role_id = d.sys_user_role_id) ");
//        sql.append(" AND d.sys_user_id = ? ");
//        sql.append(" AND d.sts = 'A' AND b.sts='A' AND (a.allow_auth = 'T' OR a.allow_auth = 'U') AND a.sts = 'A') AND e.func_node_type IN ('M','H') AND e.sts = 'A' ");

        List ret = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(localNetId));
            //ps = connection.prepareStatement(sql.getSql());
            sql.setString(1, sysUserId);
//            sql.setString(2, sysUserId);
            sql.fillParams(ps);
            sql.logPartition(localNetId, FuncNodeMDAOImpl.class);
            //sql.log(this.getClass());
            rs = ps.executeQuery();
            ret = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findFuncNodeForLogin error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return ret;

    }

    /**
     * @param sysUserId
     *            Long
     * @param funcNodeId
     *            Long
     * @param subSystemName
     *            String 功能点 菜单 功能点 新改进的权限 A：分配权限。
     * @author: anzhiqiang
     * @throws SysException
     * @return List
     * @throws AppException
     */
    public List findFuncNodeForMenu(String sysUserId, String subSystemName) throws SysException,
            AppException {

        Sql sql = new Sql();

        sql
                .append("select e.FUNC_NODE_ID,e.NODE_TREE_ID,e.FUNC_NODE_CODE,e.FUNC_NODE_NAME,e.SUB_SYSTEM_NAME,e.SECURITY_LEVEL,e.FUNC_NODE_TYPE,e.HTML,e.FILE_NAME,e.VERSION,e.DESCRIPTION,e.SHORT_CUT_IMAGE,e.STS,e.STS_DATE ");
        sql.append("from Func_Node e  ");
        sql.append("where (e.func_Node_Id in (select a.func_Node_Id  ");
        sql.append("from sys_role_alloc                a,  ");
        sql.append("Sys_Role                  b,  ");
        sql.append("where a.sys_Role_Id = b.sys_Role_Id  ");
        sql.append("and b.sys_Role_Id = d.sys_Role_Id  ");
        sql
                .append("and a.func_Node_Id not in (select h.func_Node_Id from Constraint_And_Priviledge h ");
        sql.append(" where  h.sys_user_Role_Id = d.sys_user_Role_Id )");
        if (sysUserId != null)
            sql.append(" and d.sys_User_Id = ? ");
        sql.append(" and d.sts='A'");
        sql.append(" and (a.allow_Auth = 'T' or a.allow_Auth = 'U') ");
        sql.append(" and a.sts = 'A') or ");
        sql.append(" (e.func_Node_Id in (select f.func_Node_Id ");
        sql.append(" from Func_Node f, sys_user_Alloc g ");
        sql.append(" where f.func_Node_Id = g.func_Node_Id ");
        sql.append(" and (g.alloc_Auth = 'T' or g.alloc_Auth = 'U') ");
        if (sysUserId != null)
            sql.append(" and g.sys_User_Id = ? ");
        sql.append(" and g.sts = 'A'))) ");
        sql.append(" and e.func_Node_Type = 'M' ");
        if (subSystemName != null)
            sql.append(" and e.sub_System_Name =?  ");
        sql.append(" and e.sts = 'A' ");

        List ret = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(null));
            int index = 1;
            if (sysUserId != null) {
                sql.setString(index++, sysUserId);
                sql.setString(index++, sysUserId);
            }
            if (subSystemName != null)
                sql.setString(index++, subSystemName);
            sql.fillParams(ps);
            sql.logPartition(null, FuncNodeMDAOImpl.class);
            rs = ps.executeQuery();
            ret = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findFuncNodeForMenu error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return ret;
    }

    /**
     * @param sysRoleID
     *            Long
     * @author: cason_lau
     * @throws SysException
     * @return List
     * @throws AppException
     */
    public List findFuncAllocBySysRoleNotInSet(String id) throws SysException, AppException {

        Sql sql = new Sql();

        sql
                .append(" select a.func_node_id,a.node_tree_id,a.func_node_code,a.func_node_name,a.sub_system_name,a.security_level,a.func_node_type,a.html,a.file_name,a.version,a.description,a.short_cut_image,a.sts,a.sts_date  ");
        sql.append("from func_node a,sys_role_alloc b  ");
        sql.append("where a.func_node_id=b.func_node_id  ");
        sql.append("and a.sts='A'  ");
        sql.append("and b.sts='A'  ");
        sql.append("and b.sys_role_id=?   ");

        List ret = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(null));
            sql.setString(1, id);
            sql.fillParams(ps);
            sql.logPartition(null, FuncNodeMDAOImpl.class);
            rs = ps.executeQuery();
            ret = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findFuncAllocBySysRoleNotInSet error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return ret;
    }

    /**
     * @param String
     *            sysUserId, String funcNodeId, String funcNodeType, String nodeTreeId, String type
     * @author: cason_lau
     * @throws SysException
     * @return List
     * @throws AppException
     */
    public List findFuncNodeVOsBySysUserAuthNew(String sysUserId, String funcNodeId,
            String funcNodeType, String nodeTreeId, String type) throws SysException, AppException {

        Sql sql = new Sql();

        sql
                .append("SELECT DISTINCT a.func_node_id, a.node_tree_id, a.func_node_code, a.func_node_name, a.sub_system_name, a.security_level, ");
        sql
                .append(" a.func_node_type, a.html, a.file_name, a.VERSION, a.description, a.short_cut_image, a.sts, a.sts_date ");
        sql
                .append(" FROM func_node a WHERE a.sts = 'A' AND a.func_node_id IN ( SELECT DISTINCT b.func_node_id ");
        sql.append(" FROM sys_user_alloc b WHERE b.sts = 'A' AND b.sys_user_id = ? ");
        sql.append(" AND (b.alloc_auth = 'T' OR b.alloc_auth = 'A')) ");
        sql
                .append(" AND a.func_node_id NOT IN (SELECT e.func_node_id FROM constraint_and_priviledge e ");
        sql.append(" WHERE e.sts = 'A' AND e.flag = 'C') ");
        if (funcNodeType != null) {
            sql.append(" and a.func_node_type=? ");
        }
        // 判断用户有无某功能点权限用到
        if (funcNodeId != null) {
            sql.append(" and a.func_node_id=? ");
        }
        // 增加nodeTreeId条件
        if (nodeTreeId != null) {
            sql.append(" and a.node_tree_id=? ");
        }
        sql.append(" UNION  ");
        sql
                .append(" SELECT DISTINCT a.func_node_id, a.node_tree_id, a.func_node_code, a.func_node_name, a.sub_system_name, a.security_level, ");
        sql
                .append(" a.func_node_type, a.html, a.file_name, a.VERSION, a.description, a.short_cut_image, a.sts, a.sts_date ");
        sql.append(" FROM func_node a WHERE a.sts = 'A' ");
        sql.append(" AND a.func_node_id IN ( SELECT DISTINCT c.func_node_id ");
        sql.append(" FROM sys_role_alloc c WHERE c.sts = 'A' AND c.sys_role_id IN ( ");
        sql.append(" SELECT d.sys_role_id FROM sys_user_role d WHERE d.sts = 'A' ");
        sql.append(" AND d.sys_user_id = ? ");
        sql.append(" AND (   d.allow_auth = 'T' OR d.allow_auth = 'A')) ");
        sql.append(" AND (c.allow_auth = 'T' OR c.allow_auth = 'A')) ");
        sql
                .append(" AND a.func_node_id NOT IN (SELECT e.func_node_id FROM constraint_and_priviledge e ");
        sql.append(" WHERE e.sts = 'A' AND e.flag = 'C') ");
        if (funcNodeType != null) {
            sql.append(" and a.func_node_type=? ");
        }
        // 判断用户有无某功能点权限用到
        if (funcNodeId != null) {
            sql.append(" and a.func_node_id=? ");
        }
        // 增加nodeTreeId条件
        if (nodeTreeId != null) {
            sql.append(" and a.node_tree_id=? ");
        }

        List ret = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(null));
            int index = 1;
            for (int i = 0; i < 2; i++) {
                sql.setString(index++, sysUserId);
                if (funcNodeType != null)
                    sql.setString(index++, funcNodeType);
                if (funcNodeId != null)
                    sql.setString(index++, funcNodeId);
                if (nodeTreeId != null)
                    sql.setString(index++, nodeTreeId);
            }
            sql.fillParams(ps);
            sql.logPartition(null, FuncNodeMDAOImpl.class);
            
            rs = ps.executeQuery();
            ret = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findFuncNodeVOsBySysUserAuthNew error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return ret;
    }

    public PagView findFuncNodeForLogin(String sysUserId, PagInfo pagInfo) throws SysException,
            AppException {
        List results = null;
        Sql sql = new Sql();

        sql
                .append("SELECT e.func_node_id, e.node_tree_id, e.func_node_code, e.func_node_name,e.sub_system_name, e.security_level, e.func_node_type, e.html,e.file_name, e.VERSION, e.description, e.short_cut_image, e.sts,e.sts_date ");
        sql.append("FROM func_node e WHERE exists  ( ");
        sql
                .append("SELECT 1 FROM sys_user_alloc g WHERE e.func_node_id=g.func_node_id AND (g.alloc_auth = 'T' OR g.alloc_auth = 'U') ");
        sql.append(" AND g.sys_user_id =:sysUserId");
        sql.setString("sysUserId", sysUserId);
        sql.append(" AND g.sts = 'A') AND e.func_node_type IN ( 'M','H') AND e.sts = 'A' ");

        sql.append(" UNION ALL ");
        sql
                .append(" SELECT e.func_node_id, e.node_tree_id, e.func_node_code, e.func_node_name,e.sub_system_name, e.security_level, e.func_node_type, e.html, ");
        sql
                .append(" e.file_name, e.VERSION, e.description, e.short_cut_image, e.sts,e.sts_date FROM func_node e ");
        sql
                .append(" WHERE EXISTS ( SELECT 1 FROM sys_role_alloc a, sys_role b, sys_user_role d WHERE e.func_node_id= a.func_node_id ");
        sql
                .append(" AND a.sys_role_id = b.sys_role_id AND b.sys_role_id = d.sys_role_id AND a.func_node_id NOT IN ( ");
        sql
                .append(" SELECT h.func_node_id FROM constraint_and_priviledge h WHERE h.sys_user_role_id = d.sys_user_role_id) ");
        sql.append(" AND d.sys_user_id =:sysUserId");
        sql.setString("sysUserId", sysUserId);
        sql
                .append(" AND d.sts = 'A' AND b.sts='A' AND (a.allow_auth = 'T' OR a.allow_auth = 'U') AND a.sts = 'A') AND e.func_node_type IN ('M','H') AND e.sts = 'A' ");
        sql.append(" order by func_node_id ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            sql.logPartition(null, FuncNodeMDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
            pagView.setViewList(results);
        } catch (Exception e) {
            throw new SysException("", "findByVO error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return pagView;
    }
}
