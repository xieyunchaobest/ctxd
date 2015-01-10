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
import com.cattsoft.sm.component.dao.IOtherEnumValueSDAO;
import com.cattsoft.sm.vo.EnumValueMVO;
import com.cattsoft.sm.vo.OtherEnumValueSVO;

public class OtherEnumValueSDAOImpl implements IOtherEnumValueSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		OtherEnumValueSVO otherEnumValue = (OtherEnumValueSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" OTHER_ENUM_VALUE(ENUM_VALUE_ID,ENUM_VALUE_NAME,UNIT_ID,CHARACTERISTIC_ID,ENUM_VALUE_CODE,SORT_BY,VALID_FLAG) values(?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, otherEnumValue.getEnumValueId());
			ps.setString(2, otherEnumValue.getEnumValueName());
			ps.setString(3, otherEnumValue.getUnitId());
			ps.setString(4, otherEnumValue.getCharacteristicId());
			ps.setString(5, otherEnumValue.getEnumValueCode());
			ps.setString(6, otherEnumValue.getSortBy());
			ps.setString(7, otherEnumValue.getValidFlag());
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
		OtherEnumValueSVO otherEnumValue = (OtherEnumValueSVO) vo;
		StringBuffer sql = new StringBuffer("update OTHER_ENUM_VALUE set");
		if (otherEnumValue.getEnumValueName() != null) {
			sql.append(" ENUM_VALUE_NAME=?,");
		}
		if (otherEnumValue.getUnitId() != null) {
			sql.append(" UNIT_ID=?,");
		}
		if (otherEnumValue.getCharacteristicId() != null) {
			sql.append(" CHARACTERISTIC_ID=?,");
		}
		if (otherEnumValue.getEnumValueCode() != null) {
			sql.append(" ENUM_VALUE_CODE=?,");
		}
		if (otherEnumValue.getSortBy() != null) {
			sql.append(" SORT_BY=?,");
		}
		if (otherEnumValue.getValidFlag() != null) {
			sql.append(" VALID_FLAG=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and ENUM_VALUE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (otherEnumValue.getEnumValueName() != null) {
				ps.setString(index++, otherEnumValue.getEnumValueName());
			}
			if (otherEnumValue.getUnitId() != null) {
				ps.setString(index++, otherEnumValue.getUnitId());
			}
			if (otherEnumValue.getCharacteristicId() != null) {
				ps.setString(index++, otherEnumValue.getCharacteristicId());
			}
			if (otherEnumValue.getEnumValueCode() != null) {
				ps.setString(index++, otherEnumValue.getEnumValueCode());
			}
			if (otherEnumValue.getSortBy() != null) {
				ps.setString(index++, otherEnumValue.getSortBy());
			}
			if (otherEnumValue.getValidFlag() != null) {
				ps.setString(index++, otherEnumValue.getValidFlag());
			}
			ps.setString(index++, otherEnumValue.getEnumValueId());
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
		OtherEnumValueSVO otherEnumValue = (OtherEnumValueSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from OTHER_ENUM_VALUE where 1=1");
		sql.append(" and ENUM_VALUE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, otherEnumValue.getEnumValueId());
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
		OtherEnumValueSVO result = null;
		OtherEnumValueSVO otherEnumValue = (OtherEnumValueSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.ENUM_VALUE_ID,a.ENUM_VALUE_NAME,a.UNIT_ID,a.CHARACTERISTIC_ID,a.ENUM_VALUE_CODE,a.SORT_BY,a.VALID_FLAG");
		sql.append(" from OTHER_ENUM_VALUE a where 1=1");
		sql.append(" and ENUM_VALUE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, otherEnumValue.getEnumValueId());
			rs = ps.executeQuery();
			result = (OtherEnumValueSVO) ResultSetUtil.convertToVo(rs, OtherEnumValueSVO.class);
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
		OtherEnumValueSVO otherEnumValue = (OtherEnumValueSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.ENUM_VALUE_ID,a.ENUM_VALUE_NAME,a.UNIT_ID,a.CHARACTERISTIC_ID,a.ENUM_VALUE_CODE,a.SORT_BY,a.VALID_FLAG");
		sql.append(" from OTHER_ENUM_VALUE a where 1=1");
		if (otherEnumValue.getEnumValueId() != null) {
			sql.append(" and ENUM_VALUE_ID=?");
		}
		if (otherEnumValue.getEnumValueName() != null) {
			sql.append(" and ENUM_VALUE_NAME=?");
		}
		if (otherEnumValue.getUnitId() != null) {
			sql.append(" and UNIT_ID=?");
		}
		if (otherEnumValue.getCharacteristicId() != null) {
			sql.append(" and CHARACTERISTIC_ID=?");
		}
		if (otherEnumValue.getEnumValueCode() != null) {
			sql.append(" and ENUM_VALUE_CODE=?");
		}
		if (otherEnumValue.getSortBy() != null) {
			sql.append(" and SORT_BY=?");
		}
		if (otherEnumValue.getValidFlag() != null) {
			sql.append(" and VALID_FLAG=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (otherEnumValue.getEnumValueId() != null) {
				ps.setString(index++, otherEnumValue.getEnumValueId());
			}
			if (otherEnumValue.getEnumValueName() != null) {
				ps.setString(index++, otherEnumValue.getEnumValueName());
			}
			if (otherEnumValue.getUnitId() != null) {
				ps.setString(index++, otherEnumValue.getUnitId());
			}
			if (otherEnumValue.getCharacteristicId() != null) {
				ps.setString(index++, otherEnumValue.getCharacteristicId());
			}
			if (otherEnumValue.getEnumValueCode() != null) {
				ps.setString(index++, otherEnumValue.getEnumValueCode());
			}
			if (otherEnumValue.getSortBy() != null) {
				ps.setString(index++, otherEnumValue.getSortBy());
			}
			if (otherEnumValue.getValidFlag() != null) {
				ps.setString(index++, otherEnumValue.getValidFlag());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, OtherEnumValueSVO.class);
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
     * 获取属性的所有在用属性值信息----属性属性值配置
     * 
     * @param EnumValueMVO
     *            vo
     * @param PagInfo
     *            pagInfo
     * @throws AppException
     * @throws SysException
     * @return PagView 分页查询的结果
     * @author longtao
     */
    public PagView findEnumValueMVO(GenericVO vo, PagInfo pagInfo,Set set) throws AppException,
            SysException {
        List results = null;
        OtherEnumValueSVO enumValue = (OtherEnumValueSVO) vo;
        Sql sql = new Sql();
        sql
                .append("select a.ENUM_VALUE_ID,a.ENUM_VALUE_NAME,a.ENUM_VALUE_CODE,a.SORT_BY,b.UNIT_NAME as UNIT_NAME,decode(a.VALID_FLAG,'Y','是','N','否') as VALID_FLAG");
        sql.append(" from other_ENUM_VALUE a,UNIT b where a.UNIT_ID = b.UNIT_ID(+)");
        if (enumValue.getEnumValueName() != null) {
            sql.append(" and a.ENUM_VALUE_NAME LIKE :enumValueName");
            sql.setString("enumValueName", enumValue.getEnumValueName() + "%");
        }
        if (enumValue.getCharacteristicId() != null) {
            sql.append(" and CHARACTERISTIC_ID=:characteristicId");
            sql.setString("characteristicId", enumValue.getCharacteristicId());
        }
        if (enumValue.getValidFlag() != null) {
            sql.append(" and VALID_FLAG=:validFlag");
            sql.setString("validFlag", enumValue.getValidFlag());
        }
        Connection connection = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, EnumValueMVO.class);
            pagView.setViewList(results);
        } catch (Exception e) {
            throw new SysException("", "findEnumValueMVO error..", e);
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
