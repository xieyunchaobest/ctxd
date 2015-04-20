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
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ICertTypeSDAO;
import com.cattsoft.sm.vo.CertTypeSVO;

/**
 * 方法CertTypeSDAOImpl
 * <p>
 * Company: 大唐软件
 * </p>
 * 
 * @author ：白小亮。
 * @version 1.1 2007-9-23
 */
public class CertTypeSDAOImpl implements ICertTypeSDAO {
	//private static Logger log = Logger.getLogger(CertTypeSDAOImpl.class);

	//private static final String UNABLE_STS = "P";

	/**
	 * 增加。
	 * 
	 * @return String
	 */
	public void add(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		CertTypeSVO certType = (CertTypeSVO) vo;
		if (StringUtil.isBlank(certType.getCertTypeId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO CERT_TYPE(CERT_CODE_LEN,CERT_TYPE_ID,CREATE_DATE,EXP_DATE,NAME,STS,STS_DATE)");
		sql.append(" VALUES (:certCodeLen,:certTypeId,:createDate,:expDate,:name,:sts,:stsDate)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			if (StringUtil.isBlank(certType.getCertCodeLen())) {
				sql.setNullLong("certCodeLen");
			} else {
				sql.setLong("certCodeLen", certType.getCertCodeLen());
			}

			if (StringUtil.isBlank(certType.getCertTypeId())) {
				sql.setNullString("certTypeId");
			} else {
				sql.setString("certTypeId", certType.getCertTypeId());
			}

			if (certType.getCreateDate() == null) {
				sql.setNullDate("createDate");
			} else {
				sql.setTimestamp("createDate", certType.getCreateDate());
			}

			if (certType.getExpDate() == null) {
				sql.setNullDate("expDate");
			} else {
				sql.setTimestamp("expDate", certType.getExpDate());
			}

			if (StringUtil.isBlank(certType.getName())) {
				sql.setNullString("name");
			} else {
				sql.setString("name", certType.getName());
			}

			if (StringUtil.isBlank(certType.getSts())) {
				sql.setNullString("sts");
			} else {
				sql.setString("sts", certType.getSts());
			}

			if (certType.getStsDate() == null) {
				sql.setNullDate("stsDate");
			} else {
				sql.setTimestamp("stsDate", certType.getStsDate());
			}

			sql.fillParams(ps);
			sql.log(this.getClass());
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	/**
	 * 主键查询的SQL。
	 * 
	 * @return String ： 主键查询的SQL。
	 */
	public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		CertTypeSVO certType = (CertTypeSVO) vo;
		if (StringUtil.isBlank(certType.getCertTypeId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Sql sql = new Sql(
				"SELECT CERT_CODE_LEN,CERT_TYPE_ID,CREATE_DATE,EXP_DATE,NAME,STS,STS_DATE FROM CERT_TYPE WHERE 1=1  ");
		sql.append(" and CERT_TYPE_ID=:certTypeId");
		sql.setString("certTypeId", certType.getCertTypeId());

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		certType = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				certType = new CertTypeSVO();
				certType.setCertCodeLen(rs.getString("CERT_CODE_LEN"));
				certType.setCertTypeId(rs.getString("CERT_TYPE_ID"));
				certType.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				certType.setExpDate(rs.getTimestamp("EXP_DATE"));
				certType.setName(rs.getString("NAME"));
				certType.setSts(rs.getString("STS"));
				certType.setStsDate(rs.getTimestamp("STS_DATE"));
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return certType;
	}

	/**
	 * 批量查询的SQL。
	 * 
	 * @return String ： 批量查询的SQL。
	 */
	public List findByVO(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		CertTypeSVO certType = (CertTypeSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT CERT_CODE_LEN,CERT_TYPE_ID,CREATE_DATE,EXP_DATE,NAME,STS,STS_DATE FROM CERT_TYPE WHERE 1=1 ");
		try {
			if (certType.getFlagCertCodeLen() == 1) {
				if (StringUtil.isBlank(certType.getCertCodeLen())) {
					sql.append(" and CERT_CODE_LEN is null ");
				} else {
					sql.append(" and CERT_CODE_LEN=:certCodeLen");
					sql.setLong("certCodeLen", certType.getCertCodeLen());
				}
			}

			if (certType.getFlagCertTypeId() == 1) {
				if (StringUtil.isBlank(certType.getCertTypeId())) {
					sql.append(" and CERT_TYPE_ID is null ");
				} else {
					sql.append(" and CERT_TYPE_ID=:certTypeId");
					sql.setString("certTypeId", certType.getCertTypeId());
				}
			}

			if (certType.getFlagCreateDate() == 1) {
				if (certType.getCreateDate() == null) {
					sql.append(" and CREATE_DATE is null ");
				} else {
					sql.append(" and CREATE_DATE=:createDate");
					sql.setTimestamp("createDate", certType.getCreateDate());
				}
			}

			if (certType.getFlagExpDate() == 1) {
				if (certType.getExpDate() == null) {
					sql.append(" and EXP_DATE is null ");
				} else {
					sql.append(" and EXP_DATE=:expDate");
					sql.setTimestamp("expDate", certType.getExpDate());
				}
			}

			if (certType.getFlagName() == 1) {
				if (StringUtil.isBlank(certType.getName())) {
					sql.append(" and NAME is null ");
				} else {
					sql.append(" and NAME=:name");
					sql.setString("name", certType.getName());
				}
			}

			if (certType.getFlagSts() == 1) {
				if (StringUtil.isBlank(certType.getSts())) {
					sql.append(" and STS is null ");
				} else {
					sql.append(" and STS=:sts");
					sql.setString("sts", certType.getSts());
				}
			}

			if (certType.getFlagStsDate() == 1) {
				if (certType.getStsDate() == null) {
					sql.append(" and STS_DATE is null ");
				} else {
					sql.append(" and STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", certType.getStsDate());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				certType = new CertTypeSVO();
				certType.setCertCodeLen(rs.getString("CERT_CODE_LEN"));
				certType.setCertTypeId(rs.getString("CERT_TYPE_ID"));
				certType.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				certType.setExpDate(rs.getTimestamp("EXP_DATE"));
				certType.setName(rs.getString("NAME"));
				certType.setSts(rs.getString("STS"));
				certType.setStsDate(rs.getTimestamp("STS_DATE"));
				res.add(certType);

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

	/**
	 * 更新的SQL。
	 * 
	 * @return String ： 更新的SQL。
	 */
	public void update(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		CertTypeSVO certType = (CertTypeSVO) vo;
		if (StringUtil.isBlank(certType.getCertTypeId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("UPDATE CERT_TYPE SET ");
		try {
			if (certType.getFlagCertCodeLen() == 1) {
				sql.append("CERT_CODE_LEN=:certCodeLen,");
				sql.setLong("certCodeLen", certType.getCertCodeLen());
			}

			if (certType.getFlagCreateDate() == 1) {
				sql.append("CREATE_DATE=:createDate,");
				sql.setTimestamp("createDate", certType.getCreateDate());
			}

			if (certType.getFlagExpDate() == 1) {
				sql.append("EXP_DATE=:expDate,");
				sql.setTimestamp("expDate", certType.getExpDate());
			}

			if (certType.getFlagName() == 1) {
				sql.append("NAME=:name,");
				sql.setString("name", certType.getName());
			}

			if (certType.getFlagSts() == 1) {
				sql.append("STS=:sts,");
				sql.setString("sts", certType.getSts());
			}

			if (certType.getFlagStsDate() == 1) {
				sql.append("STS_DATE=:stsDate,");
				sql.setTimestamp("stsDate", certType.getStsDate());
			}

			sql.removeSuffix(1);
			sql.append(" WHERE 1=1 ");
			sql.append(" and CERT_TYPE_ID=:certTypeId");
			sql.setString("certTypeId", certType.getCertTypeId());

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}

	}

	/**
	 * 批量增加记录。
	 * 
	 * @return String ： 批量增加的SQL。
	 */
	public void addBat(List list) throws AppException, SysException {
		if (list == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO CERT_TYPE(CERT_CODE_LEN,CERT_TYPE_ID,CREATE_DATE,EXP_DATE,NAME,STS,STS_DATE)");
		sql.append(" VALUES (:certCodeLen,:certTypeId,:createDate,:expDate,:name,:sts,:stsDate)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			for (int i = 0; i < list.size(); i++) {
				CertTypeSVO certType = (CertTypeSVO) list.get(i);
				if (certType == null) {
					throw new AppException("100001", "缺少DAO操作对象！");
				}
				if (StringUtil.isBlank(certType.getCertTypeId())) {
					throw new AppException("100002", "缺少对象的主键！");
				}
				if (StringUtil.isBlank(certType.getCertCodeLen())) {
					sql.setNullLong("certCodeLen");
				} else {
					sql.setLong("certCodeLen", certType.getCertCodeLen());
				}

				if (StringUtil.isBlank(certType.getCertTypeId())) {
					sql.setNullString("certTypeId");
				} else {
					sql.setString("certTypeId", certType.getCertTypeId());
				}

				if (certType.getCreateDate() == null) {
					sql.setNullDate("createDate");
				} else {
					sql.setTimestamp("createDate", certType.getCreateDate());
				}

				if (certType.getExpDate() == null) {
					sql.setNullDate("expDate");
				} else {
					sql.setTimestamp("expDate", certType.getExpDate());
				}

				if (StringUtil.isBlank(certType.getName())) {
					sql.setNullString("name");
				} else {
					sql.setString("name", certType.getName());
				}

				if (StringUtil.isBlank(certType.getSts())) {
					sql.setNullString("sts");
				} else {
					sql.setString("sts", certType.getSts());
				}

				if (certType.getStsDate() == null) {
					sql.setNullDate("stsDate");
				} else {
					sql.setTimestamp("stsDate", certType.getStsDate());
				}

				sql.fillParams(ps);
				sql.log(this.getClass());
				sql.clearParameters();
				ps.addBatch();
			}

			ps.executeBatch();
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	/**
	 * 根据传入参数删除一条或者一批记录。
	 * 
	 * @return String ： 删除的SQL。
	 */
	public void delete(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		CertTypeSVO certType = (CertTypeSVO) vo;
		if (StringUtil.isBlank(certType.getCertTypeId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("DELETE FROM CERT_TYPE WHERE 1=1  ");
		sql.append(" and CERT_TYPE_ID=:certTypeId");
		sql.setString("certTypeId", certType.getCertTypeId());

		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(ps);
		}
	}

	/**
	 * 注销一条或者一批
	 * 
	 * @return String ： 注销一条或者一批的SQL。
	 */
	public void unable(GenericVO vo) throws AppException, SysException {
		//CertTypeSVO certType = (CertTypeSVO) vo;
	}
}
