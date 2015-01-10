package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IAreaCharacSDAO;
import com.cattsoft.sm.vo.AreaCharacMVO;
import com.cattsoft.sm.vo.AreaCharacSVO;

public class AreaCharacSDAOImpl implements IAreaCharacSDAO {
	
    private Logger log=Logger.getLogger(AreaCharacSDAOImpl.class);
	public void add(GenericVO vo) throws AppException, SysException {
		AreaCharacSVO areaCharac = (AreaCharacSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" AREA_CHARAC(AREA_CHARAC_ID,CHARACTERISTIC_ID,REGION_TYPE,REGION_ID,VALUE,STS,STS_DATE) values(?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, areaCharac.getAreaCharacId());
			ps.setString(2, areaCharac.getCharacteristicId());
			ps.setString(3, areaCharac.getRegionType());
			ps.setString(4, areaCharac.getRegionId());
			ps.setString(5, areaCharac.getValue());
			ps.setString(6, areaCharac.getSts());
			ps.setTimestamp(7, areaCharac.getStsDate());
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
		AreaCharacSVO areaCharac = (AreaCharacSVO) vo;
		StringBuffer sql = new StringBuffer("update AREA_CHARAC set");
		if (areaCharac.getCharacteristicId() != null) {
			sql.append(" CHARACTERISTIC_ID=?,");
		}
		if (areaCharac.getRegionType() != null) {
			sql.append(" REGION_TYPE=?,");
		}
		if (areaCharac.getRegionId() != null) {
			sql.append(" REGION_ID=?,");
		}
		if (areaCharac.getValue() != null) {
			sql.append(" VALUE=?,");
		}
		if (areaCharac.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (areaCharac.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and AREA_CHARAC_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (areaCharac.getCharacteristicId() != null) {
				ps.setString(index++, areaCharac.getCharacteristicId());
			}
			if (areaCharac.getRegionType() != null) {
				ps.setString(index++, areaCharac.getRegionType());
			}
			if (areaCharac.getRegionId() != null) {
				ps.setString(index++, areaCharac.getRegionId());
			}
			if (areaCharac.getValue() != null) {
				ps.setString(index++, areaCharac.getValue());
			}
			if (areaCharac.getSts() != null) {
				ps.setString(index++, areaCharac.getSts());
			}
			if (areaCharac.getStsDate() != null) {
				ps.setTimestamp(index++, areaCharac.getStsDate());
			}
			ps.setString(index++, areaCharac.getAreaCharacId());
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
		AreaCharacSVO areaCharac = (AreaCharacSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from AREA_CHARAC where 1=1");
		sql.append(" and AREA_CHARAC_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, areaCharac.getAreaCharacId());
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
		AreaCharacSVO result = null;
		AreaCharacSVO areaCharac = (AreaCharacSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.AREA_CHARAC_ID,a.CHARACTERISTIC_ID,a.REGION_TYPE,a.REGION_ID,a.VALUE,a.STS,a.STS_DATE");
		sql.append(" from AREA_CHARAC a where 1=1");
		sql.append(" and AREA_CHARAC_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, areaCharac.getAreaCharacId());
			rs = ps.executeQuery();
			result = (AreaCharacSVO) ResultSetUtil.convertToVo(rs, AreaCharacSVO.class);
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
		AreaCharacSVO areaCharac = (AreaCharacSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.AREA_CHARAC_ID,a.CHARACTERISTIC_ID,a.REGION_TYPE,a.REGION_ID,a.VALUE,a.STS,a.STS_DATE");
		sql.append(" from AREA_CHARAC a where 1=1");
		if (areaCharac.getAreaCharacId() != null) {
			sql.append(" and AREA_CHARAC_ID=?");
		}
		if (areaCharac.getCharacteristicId() != null) {
			sql.append(" and CHARACTERISTIC_ID=?");
		}
		if (areaCharac.getRegionType() != null) {
			sql.append(" and REGION_TYPE=?");
		}
		if (areaCharac.getRegionId() != null) {
			sql.append(" and REGION_ID=?");
		}
		if (areaCharac.getValue() != null) {
			sql.append(" and VALUE=?");
		}
		if (areaCharac.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (areaCharac.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (areaCharac.getAreaCharacId() != null) {
				ps.setString(index++, areaCharac.getAreaCharacId());
			}
			if (areaCharac.getCharacteristicId() != null) {
				ps.setString(index++, areaCharac.getCharacteristicId());
			}
			if (areaCharac.getRegionType() != null) {
				ps.setString(index++, areaCharac.getRegionType());
			}
			if (areaCharac.getRegionId() != null) {
				ps.setString(index++, areaCharac.getRegionId());
			}
			if (areaCharac.getValue() != null) {
				ps.setString(index++, areaCharac.getValue());
			}
			if (areaCharac.getSts() != null) {
				ps.setString(index++, areaCharac.getSts());
			}
			if (areaCharac.getStsDate() != null) {
				ps.setTimestamp(index++, areaCharac.getStsDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, AreaCharacSVO.class);
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
    public List findByMVO(GenericVO vo) throws AppException, SysException {
        List results = null;
        AreaCharacSVO areaCharac = (AreaCharacSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.AREA_CHARAC_ID,a.CHARACTERISTIC_ID,a.REGION_TYPE,a.REGION_ID,a.VALUE,a.STS,a.STS_DATE,b.name,c.enum_value_id ");
        sql.append(" from AREA_CHARAC a,OTHER_CHARACTERISTIC b,other_enum_value c where 1=1 and a.characteristic_id=b.characteristic_id ")
               .append(" and a.characteristic_id=c.characteristic_id and a.value=c.enum_value_code ");
        if (areaCharac.getAreaCharacId() != null) {
            sql.append(" and a.AREA_CHARAC_ID=?");
        }
        if (areaCharac.getCharacteristicId() != null) {
            sql.append(" and a.CHARACTERISTIC_ID=?");
        }
        if (areaCharac.getRegionType() != null) {
            sql.append(" and a.REGION_TYPE=?");
        }
        if (areaCharac.getRegionId() != null) {
            sql.append(" and a.REGION_ID=?");
        }
        if (areaCharac.getValue() != null) {
            sql.append(" and a.VALUE=?");
        }
        if (areaCharac.getSts() != null) {
            sql.append(" and a.STS=?");
        }
        if (areaCharac.getStsDate() != null) {
            sql.append(" and a.STS_DATE=?");
        }
        log.debug(sql.toString());
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (areaCharac.getAreaCharacId() != null) {
                ps.setString(index++, areaCharac.getAreaCharacId());
            }
            if (areaCharac.getCharacteristicId() != null) {
                ps.setString(index++, areaCharac.getCharacteristicId());
            }
            if (areaCharac.getRegionType() != null) {
                ps.setString(index++, areaCharac.getRegionType());
            }
            if (areaCharac.getRegionId() != null) {
                ps.setString(index++, areaCharac.getRegionId());
            }
            if (areaCharac.getValue() != null) {
                ps.setString(index++, areaCharac.getValue());
            }
            if (areaCharac.getSts() != null) {
                ps.setString(index++, areaCharac.getSts());
            }
            if (areaCharac.getStsDate() != null) {
                ps.setTimestamp(index++, areaCharac.getStsDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, AreaCharacMVO.class);
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
