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
import com.cattsoft.sm.component.dao.IDomainValueSDAO;
import com.cattsoft.sm.vo.DomainValueSVO;


public class DomainValueSDAOImpl implements IDomainValueSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		DomainValueSVO domainValue = (DomainValueSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" DOMAIN_VALUE(DOMAIN_VALUE_ID,DOMAIN_ID,DOMAIN_VALUE,VALUE_MEANING,ORDER_ID,APPL_SCOPE) values(?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, domainValue.getDomainValueId());
			ps.setString(2, domainValue.getDomainId());
			ps.setString(3, domainValue.getDomainValue());
			ps.setString(4, domainValue.getValueMeaning());
			ps.setString(5, domainValue.getOrderId());
			ps.setString(6, domainValue.getApplScope());
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
		DomainValueSVO domainValue = (DomainValueSVO) vo;
		StringBuffer sql = new StringBuffer("update DOMAIN_VALUE set");
		if (domainValue.getDomainId() != null) {
			sql.append(" DOMAIN_ID=?,");
		}
		if (domainValue.getDomainValue() != null) {
			sql.append(" DOMAIN_VALUE=?,");
		}
		if (domainValue.getValueMeaning() != null) {
			sql.append(" VALUE_MEANING=?,");
		}
		if (domainValue.getOrderId() != null) {
			sql.append(" ORDER_ID=?,");
		}
		if (domainValue.getApplScope() != null) {
			sql.append(" APPL_SCOPE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and DOMAIN_VALUE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (domainValue.getDomainId() != null) {
				ps.setString(index++, domainValue.getDomainId());
			}
			if (domainValue.getDomainValue() != null) {
				ps.setString(index++, domainValue.getDomainValue());
			}
			if (domainValue.getValueMeaning() != null) {
				ps.setString(index++, domainValue.getValueMeaning());
			}
			if (domainValue.getOrderId() != null) {
				ps.setString(index++, domainValue.getOrderId());
			}
			if (domainValue.getApplScope() != null) {
				ps.setString(index++, domainValue.getApplScope());
			}
			ps.setString(index++, domainValue.getDomainValueId());
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
		DomainValueSVO domainValue = (DomainValueSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from DOMAIN_VALUE where 1=1");
		sql.append(" and DOMAIN_VALUE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, domainValue.getDomainValueId());
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
		DomainValueSVO result = null;
		DomainValueSVO domainValue = (DomainValueSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.DOMAIN_VALUE_ID,a.DOMAIN_ID,a.DOMAIN_VALUE,a.VALUE_MEANING,a.ORDER_ID,a.APPL_SCOPE");
		sql.append(" from DOMAIN_VALUE a where 1=1");
		sql.append(" and DOMAIN_VALUE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, domainValue.getDomainValueId());
			rs = ps.executeQuery();
			result = (DomainValueSVO) ResultSetUtil.convertToVo(rs, DomainValueSVO.class);
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
		DomainValueSVO domainValue = (DomainValueSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.DOMAIN_VALUE_ID,a.DOMAIN_ID,a.DOMAIN_VALUE,a.VALUE_MEANING,a.ORDER_ID,a.APPL_SCOPE");
		sql.append(" from DOMAIN_VALUE a where 1=1");
		if (domainValue.getDomainValueId() != null) {
			sql.append(" and DOMAIN_VALUE_ID=?");
		}
		if (domainValue.getDomainId() != null) {
			sql.append(" and DOMAIN_ID=?");
		}
		if (domainValue.getDomainValue() != null) {
			sql.append(" and DOMAIN_VALUE=?");
		}
		if (domainValue.getValueMeaning() != null) {
			sql.append(" and VALUE_MEANING=?");
		}
		if (domainValue.getOrderId() != null) {
			sql.append(" and ORDER_ID=?");
		}
		if (domainValue.getApplScope() != null) {
			sql.append(" and APPL_SCOPE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (domainValue.getDomainValueId() != null) {
				ps.setString(index++, domainValue.getDomainValueId());
			}
			if (domainValue.getDomainId() != null) {
				ps.setString(index++, domainValue.getDomainId());
			}
			if (domainValue.getDomainValue() != null) {
				ps.setString(index++, domainValue.getDomainValue());
			}
			if (domainValue.getValueMeaning() != null) {
				ps.setString(index++, domainValue.getValueMeaning());
			}
			if (domainValue.getOrderId() != null) {
				ps.setString(index++, domainValue.getOrderId());
			}
			if (domainValue.getApplScope() != null) {
				ps.setString(index++, domainValue.getApplScope());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, DomainValueSVO.class);
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
