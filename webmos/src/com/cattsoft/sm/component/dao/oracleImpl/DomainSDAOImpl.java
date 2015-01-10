package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IDomainSDAO;
import com.cattsoft.sm.vo.*;


public class DomainSDAOImpl implements IDomainSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		DomainSVO domain = (DomainSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" DOMAIN(DOMAIN_ID,DOMAIN_NAME,DOMAIN_MEANING,MIN_VALUE,MAX_VALUE,DEF_VALUE,LAST_MODI_DATE,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, domain.getDomainId());
			ps.setString(2, domain.getDomainName());
			ps.setString(3, domain.getDomainMeaning());
			ps.setString(4, domain.getMinValue());
			ps.setString(5, domain.getMaxValue());
			ps.setString(6, domain.getDefValue());
			ps.setDate(7, domain.getLastModiDate());
			ps.setString(8, domain.getSts());
			ps.setDate(9, domain.getStsDate());
			ps.setDate(10, domain.getCreateDate());
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
		DomainSVO domain = (DomainSVO) vo;
		StringBuffer sql = new StringBuffer("update DOMAIN set");
		if (domain.getDomainName() != null) {
			sql.append(" DOMAIN_NAME=?,");
		}
		if (domain.getDomainMeaning() != null) {
			sql.append(" DOMAIN_MEANING=?,");
		}
		if (domain.getMinValue() != null) {
			sql.append(" MIN_VALUE=?,");
		}
		if (domain.getMaxValue() != null) {
			sql.append(" MAX_VALUE=?,");
		}
		if (domain.getDefValue() != null) {
			sql.append(" DEF_VALUE=?,");
		}
		if (domain.getLastModiDate() != null) {
			sql.append(" LAST_MODI_DATE=?,");
		}
		if (domain.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (domain.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (domain.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and DOMAIN_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (domain.getDomainName() != null) {
				ps.setString(index++, domain.getDomainName());
			}
			if (domain.getDomainMeaning() != null) {
				ps.setString(index++, domain.getDomainMeaning());
			}
			if (domain.getMinValue() != null) {
				ps.setString(index++, domain.getMinValue());
			}
			if (domain.getMaxValue() != null) {
				ps.setString(index++, domain.getMaxValue());
			}
			if (domain.getDefValue() != null) {
				ps.setString(index++, domain.getDefValue());
			}
			if (domain.getLastModiDate() != null) {
				ps.setDate(index++, domain.getLastModiDate());
			}
			if (domain.getSts() != null) {
				ps.setString(index++, domain.getSts());
			}
			if (domain.getStsDate() != null) {
				ps.setDate(index++, domain.getStsDate());
			}
			if (domain.getCreateDate() != null) {
				ps.setDate(index++, domain.getCreateDate());
			}
			ps.setString(index++, domain.getDomainId());
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
		DomainSVO domain = (DomainSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from DOMAIN where 1=1");
		sql.append(" and DOMAIN_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, domain.getDomainId());
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
		DomainSVO result = null;
		DomainSVO domain = (DomainSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.DOMAIN_ID,a.DOMAIN_NAME,a.DOMAIN_MEANING,a.MIN_VALUE,a.MAX_VALUE,a.DEF_VALUE,a.LAST_MODI_DATE,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from DOMAIN a where 1=1");
		sql.append(" and DOMAIN_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, domain.getDomainId());
			rs = ps.executeQuery();
			result = (DomainSVO) ResultSetUtil.convertToVo(rs, DomainSVO.class);
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
		DomainSVO domain = (DomainSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.DOMAIN_ID,a.DOMAIN_NAME,a.DOMAIN_MEANING,a.MIN_VALUE,a.MAX_VALUE,a.DEF_VALUE,a.LAST_MODI_DATE,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from DOMAIN a where 1=1");
		if (domain.getDomainId() != null) {
			sql.append(" and DOMAIN_ID=?");
		}
		if (domain.getDomainName() != null) {
			sql.append(" and DOMAIN_NAME=?");
		}
		if (domain.getDomainMeaning() != null) {
			sql.append(" and DOMAIN_MEANING=?");
		}
		if (domain.getMinValue() != null) {
			sql.append(" and MIN_VALUE=?");
		}
		if (domain.getMaxValue() != null) {
			sql.append(" and MAX_VALUE=?");
		}
		if (domain.getDefValue() != null) {
			sql.append(" and DEF_VALUE=?");
		}
		if (domain.getLastModiDate() != null) {
			sql.append(" and LAST_MODI_DATE=?");
		}
		if (domain.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (domain.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (domain.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (domain.getDomainId() != null) {
				ps.setString(index++, domain.getDomainId());
			}
			if (domain.getDomainName() != null) {
				ps.setString(index++, domain.getDomainName());
			}
			if (domain.getDomainMeaning() != null) {
				ps.setString(index++, domain.getDomainMeaning());
			}
			if (domain.getMinValue() != null) {
				ps.setString(index++, domain.getMinValue());
			}
			if (domain.getMaxValue() != null) {
				ps.setString(index++, domain.getMaxValue());
			}
			if (domain.getDefValue() != null) {
				ps.setString(index++, domain.getDefValue());
			}
			if (domain.getLastModiDate() != null) {
				ps.setDate(index++, domain.getLastModiDate());
			}
			if (domain.getSts() != null) {
				ps.setString(index++, domain.getSts());
			}
			if (domain.getStsDate() != null) {
				ps.setDate(index++, domain.getStsDate());
			}
			if (domain.getCreateDate() != null) {
				ps.setDate(index++, domain.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, DomainSVO.class);
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
