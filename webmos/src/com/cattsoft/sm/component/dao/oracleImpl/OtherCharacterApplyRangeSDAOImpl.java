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
import com.cattsoft.sm.component.dao.IOtherCharacterApplyRangeSDAO;
import com.cattsoft.sm.vo.OtherCharacterApplyRangeMVO;
import com.cattsoft.sm.vo.OtherCharacterApplyRangeSVO;

public class OtherCharacterApplyRangeSDAOImpl implements IOtherCharacterApplyRangeSDAO {

    public void add(GenericVO vo) throws AppException, SysException {
        OtherCharacterApplyRangeSVO otherCharacterApplyRange = (OtherCharacterApplyRangeSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" OTHER_CHARACTER_APPLY_RANGE(CHARACTER_APPLY_RANGE_ID,CHARACTERISTIC_ID,APPLY_TYPE) values(?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, otherCharacterApplyRange.getCharacterApplyRangeId());
            ps.setString(2, otherCharacterApplyRange.getCharacteristicId());
            ps.setString(3, otherCharacterApplyRange.getApplyType());
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
        OtherCharacterApplyRangeSVO otherCharacterApplyRange = (OtherCharacterApplyRangeSVO) vo;
        StringBuffer sql = new StringBuffer("update OTHER_CHARACTER_APPLY_RANGE set");
        if (otherCharacterApplyRange.getCharacteristicId() != null) {
            sql.append(" CHARACTERISTIC_ID=?,");
        }
        if (otherCharacterApplyRange.getApplyType() != null) {
            sql.append(" APPLY_TYPE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and CHARACTER_APPLY_RANGE_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (otherCharacterApplyRange.getCharacteristicId() != null) {
                ps.setString(index++, otherCharacterApplyRange.getCharacteristicId());
            }
            if (otherCharacterApplyRange.getApplyType() != null) {
                ps.setString(index++, otherCharacterApplyRange.getApplyType());
            }
            ps.setString(index++, otherCharacterApplyRange.getCharacterApplyRangeId());
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
        OtherCharacterApplyRangeSVO otherCharacterApplyRange = (OtherCharacterApplyRangeSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from OTHER_CHARACTER_APPLY_RANGE where 1=1");
        sql.append(" and CHARACTER_APPLY_RANGE_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, otherCharacterApplyRange.getCharacterApplyRangeId());
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
        OtherCharacterApplyRangeSVO result = null;
        OtherCharacterApplyRangeSVO otherCharacterApplyRange = (OtherCharacterApplyRangeSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql.append(" a.CHARACTER_APPLY_RANGE_ID,a.CHARACTERISTIC_ID,a.APPLY_TYPE");
        sql.append(" from OTHER_CHARACTER_APPLY_RANGE a where 1=1");
        sql.append(" and CHARACTER_APPLY_RANGE_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, otherCharacterApplyRange.getCharacterApplyRangeId());
            rs = ps.executeQuery();
            result = (OtherCharacterApplyRangeSVO) ResultSetUtil.convertToVo(rs,
                    OtherCharacterApplyRangeSVO.class);
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
        OtherCharacterApplyRangeSVO otherCharacterApplyRange = (OtherCharacterApplyRangeSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.CHARACTER_APPLY_RANGE_ID,a.CHARACTERISTIC_ID,a.APPLY_TYPE");
        sql.append(" from OTHER_CHARACTER_APPLY_RANGE a where 1=1");
        if (otherCharacterApplyRange.getCharacterApplyRangeId() != null) {
            sql.append(" and CHARACTER_APPLY_RANGE_ID=?");
        }
        if (otherCharacterApplyRange.getCharacteristicId() != null) {
            sql.append(" and CHARACTERISTIC_ID=?");
        }
        if (otherCharacterApplyRange.getApplyType() != null) {
            sql.append(" and APPLY_TYPE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (otherCharacterApplyRange.getCharacterApplyRangeId() != null) {
                ps.setString(index++, otherCharacterApplyRange.getCharacterApplyRangeId());
            }
            if (otherCharacterApplyRange.getCharacteristicId() != null) {
                ps.setString(index++, otherCharacterApplyRange.getCharacteristicId());
            }
            if (otherCharacterApplyRange.getApplyType() != null) {
                ps.setString(index++, otherCharacterApplyRange.getApplyType());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, OtherCharacterApplyRangeSVO.class);
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

    public List findMVO(GenericVO vo) throws AppException, SysException {
        List results = null;
        OtherCharacterApplyRangeSVO otherCharacterApplyRange = (OtherCharacterApplyRangeSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.CHARACTER_APPLY_RANGE_ID,a.CHARACTERISTIC_ID,a.APPLY_TYPE,b.STS_WORDS as TYPE_NAME");
        sql.append(" from OTHER_CHARACTER_APPLY_RANGE a,STATUS b where 1=1");
        sql
                .append("and b.TABLE_NAME = 'OTHER_CHARACTER_APPLY_RANGE' and b.COLUMN_NAME = 'APPLY_TYPE' and b.STS_ID = a.APPLY_TYPE ");
        if (otherCharacterApplyRange.getCharacterApplyRangeId() != null) {
            sql.append(" and CHARACTER_APPLY_RANGE_ID=?");
        }
        if (otherCharacterApplyRange.getCharacteristicId() != null) {
            sql.append(" and CHARACTERISTIC_ID=?");
        }
        if (otherCharacterApplyRange.getApplyType() != null) {
            sql.append(" and APPLY_TYPE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (otherCharacterApplyRange.getCharacterApplyRangeId() != null) {
                ps.setString(index++, otherCharacterApplyRange.getCharacterApplyRangeId());
            }
            if (otherCharacterApplyRange.getCharacteristicId() != null) {
                ps.setString(index++, otherCharacterApplyRange.getCharacteristicId());
            }
            if (otherCharacterApplyRange.getApplyType() != null) {
                ps.setString(index++, otherCharacterApplyRange.getApplyType());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, OtherCharacterApplyRangeMVO.class);
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
