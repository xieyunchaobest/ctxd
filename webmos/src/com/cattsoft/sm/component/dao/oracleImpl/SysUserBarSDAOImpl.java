package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ISysUserBarSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.SysUserBarMVO;
import com.cattsoft.sm.vo.SysUserBarSVO;

public class SysUserBarSDAOImpl implements ISysUserBarSDAO {

    public void add(GenericVO vo) throws AppException, SysException {
    	
        SysUserBarSVO sysUserBar = (SysUserBarSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" SYS_USER_BAR(SYS_USER_BAR_ID,SYS_USER_ID,FUNC_NODE_ID,STS,STS_DATE) values(?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
           
     
            ps.setString(1, sysUserBar.getSysUserBarId());

            ps.setString(2, sysUserBar.getSysUserId());
            ps.setString(3, sysUserBar.getFuncNodeId());
            ps.setString(4, sysUserBar.getSts());
            ps.setDate(5, sysUserBar.getStsDate());
            ps.execute();
        } catch (SQLException e) {
            throw new SysException("", "add error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void update(GenericVO vo) throws AppException, SysException {
        SysUserBarSVO sysUserBar = (SysUserBarSVO) vo;
        StringBuffer sql = new StringBuffer("update SYS_USER_BAR set");
        if (sysUserBar.getSysUserId() != null) {
            sql.append(" SYS_USER_ID=?,");
        }
        if (sysUserBar.getFuncNodeId() != null) {
            sql.append(" FUNC_NODE_ID=?,");
        }
        if (sysUserBar.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (sysUserBar.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and SYS_USER_BAR_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (sysUserBar.getSysUserId() != null) {
                ps.setString(index++, sysUserBar.getSysUserId());
            }
            if (sysUserBar.getFuncNodeId() != null) {
                ps.setString(index++, sysUserBar.getFuncNodeId());
            }
            if (sysUserBar.getSts() != null) {
                ps.setString(index++, sysUserBar.getSts());
            }
            if (sysUserBar.getStsDate() != null) {
                ps.setDate(index++, sysUserBar.getStsDate());
            }
            ps.setString(index++, sysUserBar.getSysUserBarId());
            ps.execute();
        } catch (SQLException e) {
            throw new SysException("", "update error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void delete(GenericVO vo) throws AppException, SysException {
        SysUserBarSVO sysUserBar = (SysUserBarSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from SYS_USER_BAR where 1=1");
        sql.append(" and SYS_USER_BAR_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, sysUserBar.getSysUserBarId());
            ps.execute();
        } catch (SQLException e) {
            throw new SysException("", "delete error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        SysUserBarSVO result = null;
        SysUserBarSVO sysUserBar = (SysUserBarSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql.append(" a.SYS_USER_BAR_ID,a.SYS_USER_ID,a.FUNC_NODE_ID,a.STS,a.STS_DATE");
        sql.append(" from SYS_USER_BAR a where 1=1");
        sql.append(" and SYS_USER_BAR_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, sysUserBar.getSysUserBarId());
            rs = ps.executeQuery();
            result = (SysUserBarSVO) ResultSetUtil.convertToVo(rs, SysUserBarSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findByPK error..", e);
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
        return result;
    }

    public List findByVO(GenericVO vo) throws AppException, SysException {
        List results = null;
        SysUserBarSVO sysUserBar = (SysUserBarSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.SYS_USER_BAR_ID,a.SYS_USER_ID,a.FUNC_NODE_ID,a.STS,a.STS_DATE");
        sql.append(" from SYS_USER_BAR a where 1=1");
        if (sysUserBar.getSysUserBarId() != null) {
            sql.append(" and SYS_USER_BAR_ID=?");
        }
        if (sysUserBar.getSysUserId() != null) {
            sql.append(" and SYS_USER_ID=?");
        }
        if (sysUserBar.getFuncNodeId() != null) {
            sql.append(" and FUNC_NODE_ID=?");
        }
        if (sysUserBar.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (sysUserBar.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (sysUserBar.getSysUserBarId() != null) {
                ps.setString(index++, sysUserBar.getSysUserBarId());
            }
            if (sysUserBar.getSysUserId() != null) {
                ps.setString(index++, sysUserBar.getSysUserId());
            }
            if (sysUserBar.getFuncNodeId() != null) {
                ps.setString(index++, sysUserBar.getFuncNodeId());
            }
            if (sysUserBar.getSts() != null) {
                ps.setString(index++, sysUserBar.getSts());
            }
            if (sysUserBar.getStsDate() != null) {
                ps.setDate(index++, sysUserBar.getStsDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, SysUserBarSVO.class);
        } catch (SQLException e) {
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
        return results;
    }

    public List findSysUserBarMvo(GenericVO vo) throws AppException, SysException {
        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        SysUserBarMVO sysUserBar = (SysUserBarMVO) vo;
        SysUserBarMVO mvo = null;
        Sql sql = new Sql();
        sql
                .append("select a.sys_user_bar_id,a.sys_user_id,a.func_node_id,a.sts," +
                		"(select sts.sts_words from status sts where sts.table_name = 'SYS_USER_BAR' and sts.column_name = 'STS' and sts.sts_id = a.sts) STS_DESC ,a.sts_date,b.func_node_name,b.sub_system_name,b.html from SYS_USER_BAR a,func_node b where 1=1 ");
        sql.append("and a.func_node_id=b.func_node_id");
        if (sysUserBar.getSysUserBarId() != null) {
            sql.append(" and a.SYS_USER_BAR_ID=:sysUserBarId");
            sql.setString("sysUserBarId", sysUserBar.getSysUserBarId());
        }
        
        if(sysUserBar.getSysUserId() !=null){
            sql.append(" and a.SYS_USER_ID=:sysUserId");
            sql.setString("sysUserId", sysUserBar.getSysUserId());
        }
        
        if(sysUserBar.getSts() != null){
            sql.append(" and a.STS=:sts");
            sql.setString("sts", sysUserBar.getSts());
        }
        
        if(sysUserBar.getSubSystemName() != null){
            sql.append(" and b.sub_system_name=:subSystemName");
            sql.setString("subSystemName", sysUserBar.getSubSystemName());
        }
        if(sysUserBar.getFuncNodeName() != null){
            sql.append(" and b.func_node_name like :funcNodeName");
            sql.setString("funcNodeName", "%"+sysUserBar.getFuncNodeName()+"%");
        }
        sql.append(" order by a.sys_user_bar_id");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getSql());
            sql.fillParams(ps);
            sql.log(this.getClass());
            rs = ps.executeQuery();
          //  results = (List) ResultSetUtil.convertToList(rs, SysUserBarMVO.class);
            while(rs.next()){
        	mvo = new SysUserBarMVO();
        	mvo.setFuncNodeId(rs.getString("FUNC_NODE_ID"));
        	mvo.setFuncNodeName(rs.getString("FUNC_NODE_NAME"));
        	mvo.setSysUserBarId(rs.getString("SYS_USER_BAR_ID"));
        	mvo.setSubSystemName(rs.getString("SUB_SYSTEM_NAME"));
        	mvo.setSts(rs.getString("STS"));
        	mvo.setStsDate(rs.getDate("STS_DATE"));
        	mvo.setStsDesc(rs.getString("STS_DESC"));
        	mvo.setHtml(rs.getString("HTML"));
        	results.add(mvo);
            }
        } catch (SQLException e) {
            throw new SysException("", "findSysUserBarMvo error..", e);
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
        return results;
    }
    public PagView findSysUserBarMVOsByPage(SysUserBarMVO vo, HashSet set, PagInfo pagInfo) throws Exception {
        List results = null;
        SysUserBarMVO sysUserBar = (SysUserBarMVO) vo;
        StringBuffer sql = new StringBuffer();
        sql
                .append("select a.sys_user_bar_id,a.sys_user_id,a.func_node_id,a.sts,a.sts_date,b.func_node_name,b.sub_system_name,b.html from SYS_USER_BAR a,func_node b where 1=1");
        sql.append("and a.func_node_id=b.func_node_id");
        
        if (vo == null && set != null) {
            sql.append("and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "sys_user_bar_id", "sysUserBarId"));
            sql.append(" ) ");
        }
        if (vo != null && set == null) {
        if (sysUserBar.getSysUserBarId() != null) {
            sql.append(" and a.SYS_USER_BAR_ID="+sysUserBar.getSysUserBarId());
        }
        if (sysUserBar.getSysUserId() != null) {
            sql.append(" and a.SYS_USER_ID="+sysUserBar.getSysUserId());
        }
        if (sysUserBar.getFuncNodeId() != null) {
            sql.append(" and a.FUNC_NODE_ID="+sysUserBar.getFuncNodeId());
        }
        if (sysUserBar.getSts() != null) {
            sql.append(" and a.STS='"+sysUserBar.getSts()+"'");
        }
        if (sysUserBar.getStsDate() != null) {
            sql.append(" and a.STS_DATE="+sysUserBar.getStsDate());
        }
        if(sysUserBar.getFuncNodeName()!=null){
            sql.append(" and b.func_node_name='"+sysUserBar.getFuncNodeName()+"'");
        }
        if(sysUserBar.getSubSystemName()!=null){
            sql.append(" and b.sub_system_name='"+sysUserBar.getSubSystemName()+"'");
        }
        }
        sql.append(" order by a.sys_user_bar_id");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(SysUserBarSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, SysUserBarMVO.class);
            pagView.setViewList(results);
        } catch (SQLException e) {
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
