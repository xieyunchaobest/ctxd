package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ISysAreaConfigSDAO;
import com.cattsoft.sm.vo.SysAreaConfigSVO;



public class SysAreaConfigSDAOImpl implements ISysAreaConfigSDAO {

    public void add(GenericVO vo) throws AppException, SysException {
        SysAreaConfigSVO sysAreaConfig = (SysAreaConfigSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" SYS_AREA_CONFIG(SYS_AREA_CONFIG_ID,CONFIG_ID,SP_AREA_ID,CUR_VALUE,VALUE_DESC) values(?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, sysAreaConfig.getSysAreaConfigId());
            ps.setString(2, sysAreaConfig.getConfigId());
            ps.setString(3, sysAreaConfig.getSpAreaId());
            ps.setString(4, sysAreaConfig.getCurValue());
            ps.setString(5, sysAreaConfig.getValueDesc());
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
        SysAreaConfigSVO sysAreaConfig = (SysAreaConfigSVO) vo;
        StringBuffer sql = new StringBuffer("update SYS_AREA_CONFIG set");
        if (sysAreaConfig.getConfigId() != null) {
            sql.append(" CONFIG_ID=?,");
        }
        if (sysAreaConfig.getSpAreaId() != null) {
            sql.append(" SP_AREA_ID=?,");
        }
        if (sysAreaConfig.getCurValue() != null) {
            sql.append(" CUR_VALUE=?,");
        }
        if (sysAreaConfig.getValueDesc() != null) {
            sql.append(" VALUE_DESC=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and SYS_AREA_CONFIG_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (sysAreaConfig.getConfigId() != null) {
                ps.setString(index++, sysAreaConfig.getConfigId());
            }
            if (sysAreaConfig.getSpAreaId() != null) {
                ps.setString(index++, sysAreaConfig.getSpAreaId());
            }
            if (sysAreaConfig.getCurValue() != null) {
                ps.setString(index++, sysAreaConfig.getCurValue());
            }
            if (sysAreaConfig.getValueDesc() != null) {
                ps.setString(index++, sysAreaConfig.getValueDesc());
            }
            ps.setString(index++, sysAreaConfig.getSysAreaConfigId());
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
        SysAreaConfigSVO sysAreaConfig = (SysAreaConfigSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from SYS_AREA_CONFIG where 1=1");
        sql.append(" and SYS_AREA_CONFIG_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, sysAreaConfig.getSysAreaConfigId());
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
        SysAreaConfigSVO result = null;
        SysAreaConfigSVO sysAreaConfig = (SysAreaConfigSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql.append(" a.SYS_AREA_CONFIG_ID,a.CONFIG_ID,a.SP_AREA_ID,a.CUR_VALUE,a.VALUE_DESC");
        sql.append(" from SYS_AREA_CONFIG a where 1=1");
        sql.append(" and SYS_AREA_CONFIG_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, sysAreaConfig.getSysAreaConfigId());
            rs = ps.executeQuery();
            result = (SysAreaConfigSVO) ResultSetUtil.convertToVo(rs, SysAreaConfigSVO.class);
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
        SysAreaConfigSVO sysAreaConfig = (SysAreaConfigSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.SYS_AREA_CONFIG_ID,a.CONFIG_ID,a.SP_AREA_ID,a.CUR_VALUE,a.VALUE_DESC");
        sql.append(" from SYS_AREA_CONFIG a where 1=1");
        if (sysAreaConfig.getSysAreaConfigId() != null) {
            sql.append(" and SYS_AREA_CONFIG_ID=?");
        }
        if (sysAreaConfig.getConfigId() != null) {
            sql.append(" and CONFIG_ID=?");
        }
        if (sysAreaConfig.getSpAreaId() != null) {
            sql.append(" and SP_AREA_ID=?");
        }
        if (sysAreaConfig.getCurValue() != null) {
            sql.append(" and CUR_VALUE=?");
        }
        if (sysAreaConfig.getValueDesc() != null) {
            sql.append(" and VALUE_DESC=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (sysAreaConfig.getSysAreaConfigId() != null) {
                ps.setString(index++, sysAreaConfig.getSysAreaConfigId());
            }
            if (sysAreaConfig.getConfigId() != null) {
                ps.setString(index++, sysAreaConfig.getConfigId());
            }
            if (sysAreaConfig.getSpAreaId() != null) {
                ps.setString(index++, sysAreaConfig.getSpAreaId());
            }
            if (sysAreaConfig.getCurValue() != null) {
                ps.setString(index++, sysAreaConfig.getCurValue());
            }
            if (sysAreaConfig.getValueDesc() != null) {
                ps.setString(index++, sysAreaConfig.getValueDesc());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, SysAreaConfigSVO.class);
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

}
