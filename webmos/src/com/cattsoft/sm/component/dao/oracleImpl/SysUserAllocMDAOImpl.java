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
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.sm.component.dao.ISysUserAllocMDAO;
import com.cattsoft.sm.vo.FuncNodeSVO;
import com.cattsoft.sm.vo.SysUserAllocSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */
public class SysUserAllocMDAOImpl implements ISysUserAllocMDAO {

	// private static Logger log = Logger.getLogger(SysUserAllocMDAOImpl.class);

	/**
     * @param sysRoleID
     *            Long
     * @author: cason_lau
     * @throws SysException
     * @return List
     * @throws AppException
     */
    public List findBySysRoleId(SysUserAllocSVO suavo) throws SysException, AppException {

        Sql sql = new Sql();

        sql.append(" select distinct a.sys_user_id    ");
        sql.append("from sys_user_alloc a ");
        sql.append("where a.sts='A'  ");
        sql.append("and a.sys_role_id = ?  ");
        List ret = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(suavo.getLocalNetId()));
            sql.setString(1, suavo.getSysRoleId());
            sql.fillParams(ps);
            sql.logPartition(suavo.getLocalNetId(), this.getClass());
            rs = ps.executeQuery();
            String sysUserId = null;
            while (rs.next()) {
                sysUserId = rs.getString(1);
                ret.add(sysUserId);
            }
        } catch (SQLException e) {
            throw new SysException("", "findBySysRoleId error..", e);
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
     * @author: cason_lau
     * @throws SysException
     * @return List
     * @throws AppException
     */
    public List findFuncAllocBySysUserNotInSet(String sysUserId, String localNetId)
            throws SysException, AppException {

        List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        Sql sql = new Sql();
        sql
                .append("select fn.func_node_id,fn.node_tree_id,fn.func_node_code,fn.func_node_name,fn.sub_system_name,fn.security_level,fn.func_node_type,fn.html,fn.file_name,fn.version,fn.description,fn.short_cut_image,fn.sts,fn.sts_date    ");
        sql.append("from sys_user_alloc sua,func_node fn   ");
        sql.append("where sua.func_node_id=fn.func_node_id  ");
        sql.append("and fn.sts='A'   ");
        sql.append("and sua.sts='A'  ");
        sql.append("and sua.sys_role_id is null   ");
        sql.append("and sua.sys_user_id= ?  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(localNetId));
            if (sysUserId != null) {
                sql.setString(1, sysUserId);
            }
            sql.fillParams(ps);
            sql.logPartition(localNetId, this.getClass());
            rs = ps.executeQuery();
            vos = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
        } catch (Exception e) {
            e.printStackTrace();
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
        return vos;

    }

    /**
     * @param SysUserAllocSVO
     * 
     * @author: cason_lau
     * @throws SysException
     * @return List
     * @throws AppException
     */
    public List findSysUserByFuncNodeAndSysUser(SysUserAllocSVO svo) throws SysException,
            AppException {

        List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        Sql sql = new Sql();
        sql
                .append("select sua.alloc_id,sua.sys_user_id,sua.func_node_id,sua.sys_role_id,sua.grant_sys_user_id,sua.entrust_flag,sua.alloc_auth,sua.range_id,sua.create_date,sua.sts,sua.sts_date    ");
        sql.append("from sys_user_alloc sua   ");
        sql.append("where sua.sys_user_id= ? ");
        sql.append("and sua.func_node_id= ? ");
        sql.append("and sua.sys_role_id is null  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(svo.getLocalNetId()));
            int index = 1;
            if (svo.getSysUserId() != null) {
                sql.setString(index++, svo.getSysUserId());
            }
            if (svo.getFuncNodeId() != null) {
                sql.setString(index++, svo.getFuncNodeId());
            }
            sql.fillParams(ps);
            sql.logPartition(svo.getLocalNetId(), this.getClass());
            rs = ps.executeQuery();
            vos = (List) ResultSetUtil.convertToList(rs, SysUserAllocSVO.class);
        } catch (Exception e) {
            e.printStackTrace();
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
        return vos;

    }

    public void addBySysRole(SysUserAllocSVO suavo) throws SysException, AppException {

        Sql sql = new Sql();

        sql.append(" insert into sys_user_alloc ");
        sql.append(" ( alloc_id,sys_user_id,func_node_id,sys_role_id,grant_sys_user_id, ");
        sql.append(" entrust_flag,alloc_auth,local_net_id,range_id,sts_date,create_date,sts ) ");
        sql.append(" select alloc_id_seq.NEXTVAL,a.sys_user_id,?,a.sys_role_id,?,?,?,b.local_net_id,NULL,?,?,? ");
        sql.append(" from sys_user b,sys_user_role a ");
        sql.append(" where a.sys_user_id=b.sys_user_id ");
        sql.append(" and b.sts='" + Constant.STS_IN_USE + "'");
        sql.append(" and a.sts='" + Constant.STS_IN_USE + "'");
        sql.append(" and a.sys_role_id=?   ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(null));
            sql.setString(1, suavo.getFuncNodeId());
            sql.setString(2, suavo.getGrantSysUserId());
            sql.setString(3, suavo.getEntrustFlag());
            sql.setString(4, suavo.getAllocAuth());
            sql.setTimestamp(5, suavo.getStsDate());
            sql.setTimestamp(6, suavo.getCreateDate());
            sql.setString(7, suavo.getSts());
            sql.setString(8, suavo.getSysRoleId());
            sql.fillParams(ps);
            sql.logPartition(null, this.getClass());
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new SysException("", "findBySysRoleId error..", e);
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
    }

    public void delBySysRole(SysUserAllocSVO suavo) throws SysException, AppException {

        Sql sql = new Sql();

        sql.append(" delete from sys_user_alloc a");
        sql
                .append(" where exists (select 1 from sys_user_role b where a.sys_role_id=b.sys_role_id and a.sys_user_id=b.sys_user_id ) ");
        sql.append(" and a.sys_role_id=? ");
        sql.append(" and a.func_node_id=?  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(suavo.getLocalNetId()));
            sql.setString(1, suavo.getSysRoleId());
            sql.setString(2, suavo.getFuncNodeId());

            sql.fillParams(ps);
            sql.logPartition(suavo.getLocalNetId(), this.getClass());
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new SysException("", "findBySysRoleId error..", e);
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
    }

    public void addByFuncNode(SysUserAllocSVO suavo) throws SysException, AppException {

        Sql sql = new Sql();

        sql.append(" insert into sys_user_alloc ");
        sql.append(" (alloc_id,sys_user_id,func_node_id,,grant_sys_user_id,entrust_flag, ");
        sql.append(" local_net_id,,alloc_auth,sts_date,create_date ) ");
        sql.append(" select alloc_id_seq.NEXTVAL,?,?,null,?,?,?,a.local_net_id,NULL,?,?,? ");
        sql.append(" from sys_user a ");
        sql.append(" where a.sys_user_id=? ");
        sql.append(" and a.sts='" + Constant.STS_IN_USE + "'");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(null));
            sql.setString(1, suavo.getSysUserId());
            sql.setString(2, suavo.getFuncNodeId());
            sql.setString(3, suavo.getGrantSysUserId());
            sql.setString(4, suavo.getEntrustFlag());
            sql.setString(5, suavo.getAllocAuth());
            sql.setTimestamp(6, suavo.getStsDate());
            sql.setTimestamp(7, suavo.getCreateDate());
            sql.setString(8, suavo.getSysUserId());
            sql.fillParams(ps);
            sql.logPartition(null, this.getClass());
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new SysException("", "findBySysRoleId error..", e);
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
    }

    public void delBySysRoleAndSysUser(SysUserAllocSVO suavo) throws SysException, AppException {

        Sql sql = new Sql();

        sql.append(" delete from sys_user_alloc a");
        sql.append(" where 1=1 ");
        sql.append(" and a.sys_role_id=? ");
        sql.append(" and a.sys_user_id=? ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(suavo.getLocalNetId()));
            sql.setString(1, suavo.getSysRoleId());
            sql.setString(2, suavo.getSysUserId());

            sql.fillParams(ps);
            sql.logPartition(suavo.getLocalNetId(), this.getClass());
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new SysException("", "findBySysRoleId error..", e);
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
    }
    public void addBySysRoleAndSysUser(SysUserAllocSVO suavo) throws SysException, AppException {

        Sql sql = new Sql();

        sql.append(" insert into sys_user_alloc ");
        sql.append(" ( alloc_id,sys_user_id,func_node_id,sys_role_id,grant_sys_user_id, ");
        sql.append(" entrust_flag,alloc_auth,local_net_id,range_id,sts_date,create_date,sts ) ");
        sql.append(" select alloc_id_seq.NEXTVAL,?,a.func_node_id,?,?,?,?,?,NULL,?,?,? ");
        sql.append(" from sys_role_alloc a ");
        sql.append(" where 1=1 ");
        sql.append(" and a.sts='" + Constant.STS_IN_USE + "'");
        sql.append(" and a.sys_role_id=?   ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(null));
            sql.setString(1, suavo.getSysUserId());
            sql.setString(2, suavo.getSysRoleId());
            sql.setString(3, suavo.getGrantSysUserId());
            sql.setString(4, suavo.getEntrustFlag());
            sql.setString(5, suavo.getAllocAuth());
            sql.setString(6, suavo.getLocalNetId());
            sql.setTimestamp(7, suavo.getStsDate());
            sql.setTimestamp(8, suavo.getCreateDate());
            sql.setString(9, suavo.getSts());
            sql.setString(10, suavo.getSysRoleId());
            sql.fillParams(ps);
            sql.logPartition(null, this.getClass());
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new SysException("", "findBySysRoleId error..", e);
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
    }

}
