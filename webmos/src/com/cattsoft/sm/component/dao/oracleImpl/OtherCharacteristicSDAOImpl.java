package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IOtherCharacteristicSDAO;
import com.cattsoft.sm.vo.OtherCharacteristicMVO;
import com.cattsoft.sm.vo.OtherCharacteristicSVO;

public class OtherCharacteristicSDAOImpl implements IOtherCharacteristicSDAO {

    public void add(GenericVO vo) throws AppException, SysException {
        OtherCharacteristicSVO otherCharacteristic = (OtherCharacteristicSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" OTHER_CHARACTERISTIC(CHARACTERISTIC_ID,NAME,PARENT_CHARACTERISTIC_ID,DATA_TYPE,VALUE_TYPE,FROM_VALUE,TO_VALUE,DEFAULT_VALUE,ENCRYPTED,VALUE_LEN_LIMIT,VALUE_NUM_LIMIT,STANDARD_CODE,BACKFILL_FLAG,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, otherCharacteristic.getCharacteristicId());
            ps.setString(3, otherCharacteristic.getName());
            ps.setString(4, otherCharacteristic.getParentCharacteristicId());
            ps.setString(5, otherCharacteristic.getDataType());
            ps.setString(6, otherCharacteristic.getValueType());
            ps.setString(7, otherCharacteristic.getFromValue());
            ps.setString(8, otherCharacteristic.getToValue());
            ps.setString(9, otherCharacteristic.getDefaultValue());
            ps.setString(10, otherCharacteristic.getEncrypted());
            ps.setString(11, otherCharacteristic.getValueLenLimit());
            ps.setString(12, otherCharacteristic.getValueNumLimit());
            ps.setString(13, otherCharacteristic.getStandardCode());
            ps.setString(14, otherCharacteristic.getBackfillFlag());
            ps.setString(15, otherCharacteristic.getSts());
            ps.setTimestamp(16, otherCharacteristic.getStsDate());
            ps.setTimestamp(17, otherCharacteristic.getCreateDate());
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
        OtherCharacteristicSVO otherCharacteristic = (OtherCharacteristicSVO) vo;
        StringBuffer sql = new StringBuffer("update OTHER_CHARACTERISTIC set");
        if (otherCharacteristic.getName() != null) {
            sql.append(" NAME=?,");
        }
        if (otherCharacteristic.getParentCharacteristicId() != null) {
            sql.append(" PARENT_CHARACTERISTIC_ID=?,");
        }
        if (otherCharacteristic.getDataType() != null) {
            sql.append(" DATA_TYPE=?,");
        }
        if (otherCharacteristic.getValueType() != null) {
            sql.append(" VALUE_TYPE=?,");
        }
        if (otherCharacteristic.getFromValue() != null) {
            sql.append(" FROM_VALUE=?,");
        }
        if (otherCharacteristic.getToValue() != null) {
            sql.append(" TO_VALUE=?,");
        }
        if (otherCharacteristic.getDefaultValue() != null) {
            sql.append(" DEFAULT_VALUE=?,");
        }
        if (otherCharacteristic.getEncrypted() != null) {
            sql.append(" ENCRYPTED=?,");
        }
        if (otherCharacteristic.getValueLenLimit() != null) {
            sql.append(" VALUE_LEN_LIMIT=?,");
        }
        if (otherCharacteristic.getValueNumLimit() != null) {
            sql.append(" VALUE_NUM_LIMIT=?,");
        }
        if (otherCharacteristic.getStandardCode() != null) {
            sql.append(" STANDARD_CODE=?,");
        }
        if (otherCharacteristic.getBackfillFlag() != null) {
            sql.append(" BACKFILL_FLAG=?,");
        }
        if (otherCharacteristic.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (otherCharacteristic.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (otherCharacteristic.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and CHARACTERISTIC_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
       
            if (otherCharacteristic.getName() != null) {
                ps.setString(index++, otherCharacteristic.getName());
            }
            if (otherCharacteristic.getParentCharacteristicId() != null) {
                ps.setString(index++, otherCharacteristic.getParentCharacteristicId());
            }
            if (otherCharacteristic.getDataType() != null) {
                ps.setString(index++, otherCharacteristic.getDataType());
            }
            if (otherCharacteristic.getValueType() != null) {
                ps.setString(index++, otherCharacteristic.getValueType());
            }
            if (otherCharacteristic.getFromValue() != null) {
                ps.setString(index++, otherCharacteristic.getFromValue());
            }
            if (otherCharacteristic.getToValue() != null) {
                ps.setString(index++, otherCharacteristic.getToValue());
            }
            if (otherCharacteristic.getDefaultValue() != null) {
                ps.setString(index++, otherCharacteristic.getDefaultValue());
            }
            if (otherCharacteristic.getEncrypted() != null) {
                ps.setString(index++, otherCharacteristic.getEncrypted());
            }
            if (otherCharacteristic.getValueLenLimit() != null) {
                ps.setString(index++, otherCharacteristic.getValueLenLimit());
            }
            if (otherCharacteristic.getValueNumLimit() != null) {
                ps.setString(index++, otherCharacteristic.getValueNumLimit());
            }
            if (otherCharacteristic.getStandardCode() != null) {
                ps.setString(index++, otherCharacteristic.getStandardCode());
            }
            if (otherCharacteristic.getBackfillFlag() != null) {
                ps.setString(index++, otherCharacteristic.getBackfillFlag());
            }
            if (otherCharacteristic.getSts() != null) {
                ps.setString(index++, otherCharacteristic.getSts());
            }
            if (otherCharacteristic.getStsDate() != null) {
                ps.setTimestamp(index++, otherCharacteristic.getStsDate());
            }
            if (otherCharacteristic.getCreateDate() != null) {
                ps.setTimestamp(index++, otherCharacteristic.getCreateDate());
            }
            ps.setString(index++, otherCharacteristic.getCharacteristicId());
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
        OtherCharacteristicSVO otherCharacteristic = (OtherCharacteristicSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from OTHER_CHARACTERISTIC where 1=1");
        sql.append(" and CHARACTERISTIC_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, otherCharacteristic.getCharacteristicId());
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
        OtherCharacteristicSVO result = null;
        OtherCharacteristicSVO otherCharacteristic = (OtherCharacteristicSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.CHARACTERISTIC_ID,a.NAME,a.PARENT_CHARACTERISTIC_ID,a.DATA_TYPE,a.VALUE_TYPE,a.FROM_VALUE,a.TO_VALUE,a.DEFAULT_VALUE,a.ENCRYPTED,a.VALUE_LEN_LIMIT,a.VALUE_NUM_LIMIT,a.STANDARD_CODE,a.BACKFILL_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from OTHER_CHARACTERISTIC a where 1=1");
        sql.append(" and CHARACTERISTIC_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, otherCharacteristic.getCharacteristicId());
            rs = ps.executeQuery();
            result = (OtherCharacteristicSVO) ResultSetUtil.convertToVo(rs,
                    OtherCharacteristicSVO.class);
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
        OtherCharacteristicSVO otherCharacteristic = (OtherCharacteristicSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.CHARACTERISTIC_ID,a.NAME,a.PARENT_CHARACTERISTIC_ID,a.DATA_TYPE,a.VALUE_TYPE,a.FROM_VALUE,a.TO_VALUE,a.DEFAULT_VALUE,a.ENCRYPTED,a.VALUE_LEN_LIMIT,a.VALUE_NUM_LIMIT,a.STANDARD_CODE,a.BACKFILL_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from OTHER_CHARACTERISTIC a where 1=1");
        if (otherCharacteristic.getCharacteristicId() != null) {
            sql.append(" and CHARACTERISTIC_ID=?");
        }
       
        if (otherCharacteristic.getName() != null) {
            sql.append(" and NAME=?");
        }
        if (otherCharacteristic.getParentCharacteristicId() != null) {
            sql.append(" and PARENT_CHARACTERISTIC_ID=?");
        }
        if (otherCharacteristic.getDataType() != null) {
            sql.append(" and DATA_TYPE=?");
        }
        if (otherCharacteristic.getValueType() != null) {
            sql.append(" and VALUE_TYPE=?");
        }
        if (otherCharacteristic.getFromValue() != null) {
            sql.append(" and FROM_VALUE=?");
        }
        if (otherCharacteristic.getToValue() != null) {
            sql.append(" and TO_VALUE=?");
        }
        if (otherCharacteristic.getDefaultValue() != null) {
            sql.append(" and DEFAULT_VALUE=?");
        }
        if (otherCharacteristic.getEncrypted() != null) {
            sql.append(" and ENCRYPTED=?");
        }
        if (otherCharacteristic.getValueLenLimit() != null) {
            sql.append(" and VALUE_LEN_LIMIT=?");
        }
        if (otherCharacteristic.getValueNumLimit() != null) {
            sql.append(" and VALUE_NUM_LIMIT=?");
        }
        if (otherCharacteristic.getStandardCode() != null) {
            sql.append(" and STANDARD_CODE=?");
        }
        if (otherCharacteristic.getBackfillFlag() != null) {
            sql.append(" and BACKFILL_FLAG=?");
        }
        if (otherCharacteristic.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (otherCharacteristic.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (otherCharacteristic.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (otherCharacteristic.getCharacteristicId() != null) {
                ps.setString(index++, otherCharacteristic.getCharacteristicId());
            }
      
            if (otherCharacteristic.getName() != null) {
                ps.setString(index++, otherCharacteristic.getName());
            }
            if (otherCharacteristic.getParentCharacteristicId() != null) {
                ps.setString(index++, otherCharacteristic.getParentCharacteristicId());
            }
            if (otherCharacteristic.getDataType() != null) {
                ps.setString(index++, otherCharacteristic.getDataType());
            }
            if (otherCharacteristic.getValueType() != null) {
                ps.setString(index++, otherCharacteristic.getValueType());
            }
            if (otherCharacteristic.getFromValue() != null) {
                ps.setString(index++, otherCharacteristic.getFromValue());
            }
            if (otherCharacteristic.getToValue() != null) {
                ps.setString(index++, otherCharacteristic.getToValue());
            }
            if (otherCharacteristic.getDefaultValue() != null) {
                ps.setString(index++, otherCharacteristic.getDefaultValue());
            }
            if (otherCharacteristic.getEncrypted() != null) {
                ps.setString(index++, otherCharacteristic.getEncrypted());
            }
            if (otherCharacteristic.getValueLenLimit() != null) {
                ps.setString(index++, otherCharacteristic.getValueLenLimit());
            }
            if (otherCharacteristic.getValueNumLimit() != null) {
                ps.setString(index++, otherCharacteristic.getValueNumLimit());
            }
            if (otherCharacteristic.getStandardCode() != null) {
                ps.setString(index++, otherCharacteristic.getStandardCode());
            }
            if (otherCharacteristic.getBackfillFlag() != null) {
                ps.setString(index++, otherCharacteristic.getBackfillFlag());
            }
            if (otherCharacteristic.getSts() != null) {
                ps.setString(index++, otherCharacteristic.getSts());
            }
            if (otherCharacteristic.getStsDate() != null) {
                ps.setTimestamp(index++, otherCharacteristic.getStsDate());
            }
            if (otherCharacteristic.getCreateDate() != null) {
                ps.setTimestamp(index++, otherCharacteristic.getCreateDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, OtherCharacteristicSVO.class);
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

    /**
     * 获取所有在用属性信息----根据适应范围
     * 
     * @param CharacteristicMVO
     *            vo
     * @param PagInfo
     *            pagInfo
     * @throws AppException
     * @throws SysException
     * @return PagView 分页查询的结果
     * @author longtao
     */
    public PagView findCharacteristicMVOByRange(GenericVO vo, PagInfo pagInfo,Set set) throws AppException,
            SysException {
        List results = null;
        OtherCharacteristicMVO rvo = (OtherCharacteristicMVO) vo;
        Sql sql = new Sql();
        sql
                .append("select a.CHARACTERISTIC_ID,a.NAME,a.STANDARD_CODE,a.sts,a.VALUE_TYPE,a.CREATE_DATE,a.STS_DATE,b.STS_WORDS as VALUE_TYPE_NAME");
        sql.append(" from Other_CHARACTERISTIC a,STATUS b,Other_Character_Apply_Range c ");
        sql
                .append(" where b.TABLE_NAME = 'CHARACTERISTIC' and b.COLUMN_NAME = 'VALUE_TYPE' and b.STS_ID = a.VALUE_TYPE and a.characteristic_id=c.characteristic_id and a.sts='A' ");
        
        if (rvo.getApplyType()!=null) {
            sql.append(" and c.apply_type= :type");
            sql.setString("type", rvo.getApplyType());
        }
       if(rvo.getName()!=null){
           sql.append(" and a.name like :name");
           sql.setString(":name", "'%"+rvo.getName()+"%'");
       }
        sql.append(" order by CHARACTERISTIC_ID desc");
        Connection connection = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, OtherCharacteristicMVO.class);
            pagView.setViewList(results);
        } catch (Exception e) {
            throw new SysException("", "findCharacteristicMVO error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
        }
        return pagView;
    }
    /**
     * 获取所有在用属性信息----属性属性值配置
     * 
     * @param CharacteristicMVO
     *            vo
     * @param PagInfo
     *            pagInfo
     * @throws AppException
     * @throws SysException
     * @return PagView 分页查询的结果
     * @author longtao
     */
    public PagView findCharacteristicMVO(GenericVO vo, PagInfo pagInfo,Set set) throws AppException,
            SysException {
        List results = null;
        OtherCharacteristicMVO characteristic = (OtherCharacteristicMVO) vo;
        Sql sql = new Sql();
        sql
                .append("select a.CHARACTERISTIC_ID,a.NAME,a.STANDARD_CODE,a.VALUE_TYPE,a.CREATE_DATE,a.STS_DATE,b.STS_WORDS as VALUE_TYPE_NAME");
        sql.append(" from Other_CHARACTERISTIC a,STATUS b");
        sql
                .append(" where b.TABLE_NAME = 'CHARACTERISTIC' and b.COLUMN_NAME = 'VALUE_TYPE' and b.STS_ID = a.VALUE_TYPE");

        if (characteristic.getName() != null) {
            sql.append(" and NAME like :name");
            sql.setString("name", characteristic.getName() + "%");
        }
        if (characteristic.getValueType() != null) {
            sql.append(" and VALUE_TYPE =:valueType");
            sql.setString("valueType", characteristic.getValueType());
        }
        if (characteristic.getSts() != null) {
            sql.append(" and STS=:sts");
            sql.setString("sts", characteristic.getSts());
        }
        sql.append(" order by CHARACTERISTIC_ID desc");
        Connection connection = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, OtherCharacteristicMVO.class);
            pagView.setViewList(results);
        } catch (Exception e) {
            throw new SysException("", "findCharacteristicMVO error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
        }
        return pagView;
    }


}
