package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.sm.component.dao.IApnUserSDAO;
import com.cattsoft.sm.vo.ApnUserSVO;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.pub.util.StringUtil;

/**
 * 方法ApnUserSDAOImpl
 * <p>Company: 大唐软件</p>
 * @author ：白小亮。
 * @version 1.1  2007-9-23
 */
public class ApnUserSDAOImpl implements IApnUserSDAO {
	private static Logger log = Logger.getLogger(ApnUserSDAOImpl.class);
	private static final String UNABLE_STS = "P";

	/**
	 * 增加。
	 * @return String
	 */
	public void add(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		ApnUserSVO apnUser = (ApnUserSVO) vo;
		if (StringUtil.isBlank(apnUser.getId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO APN_USER(CREATED_DATE,EMAIL,ID,NAME,PASSWORD,UPDATED_DATE,USERNAME)");
		sql
				.append(" VALUES (:createdDate,:email,:id,:name,:password,:updatedDate,:username)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			if (apnUser.getCreatedDate() == null) {
				sql.setNullDate("createdDate");
			} else {
				sql.setTimestamp("createdDate", apnUser.getCreatedDate());
			}

			if (StringUtil.isBlank(apnUser.getEmail())) {
				sql.setNullString("email");
			} else {
				sql.setString("email", apnUser.getEmail());
			}

			if (StringUtil.isBlank(apnUser.getId())) {
				sql.setNullLong("id");
			} else {
				sql.setLong("id", apnUser.getId());
			}

			if (StringUtil.isBlank(apnUser.getName())) {
				sql.setNullString("name");
			} else {
				sql.setString("name", apnUser.getName());
			}

			if (StringUtil.isBlank(apnUser.getPassword())) {
				sql.setNullString("password");
			} else {
				sql.setString("password", apnUser.getPassword());
			}

			if (apnUser.getUpdatedDate() == null) {
				sql.setNullDate("updatedDate");
			} else {
				sql.setTimestamp("updatedDate", apnUser.getUpdatedDate());
			}

			if (StringUtil.isBlank(apnUser.getUsername())) {
				sql.setNullString("username");
			} else {
				sql.setString("username", apnUser.getUsername());
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
	 * @return String ： 主键查询的SQL。
	 */
	public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		ApnUserSVO apnUser = (ApnUserSVO) vo;
		if (StringUtil.isBlank(apnUser.getId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Sql sql = new Sql(
				"SELECT CREATED_DATE,EMAIL,ID,NAME,PASSWORD,UPDATED_DATE,USERNAME FROM APN_USER WHERE 1=1  ");
		sql.append(" and ID=:id");
		sql.setLong("id", apnUser.getId());

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		apnUser = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				apnUser = new ApnUserSVO();
				apnUser.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
				apnUser.setEmail(rs.getString("EMAIL"));
				apnUser.setId(rs.getString("ID"));
				apnUser.setName(rs.getString("NAME"));
				apnUser.setPassword(rs.getString("PASSWORD"));
				apnUser.setUpdatedDate(rs.getTimestamp("UPDATED_DATE"));
				apnUser.setUsername(rs.getString("USERNAME"));
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return apnUser;
	}

	/**
	 * 批量查询的SQL。
	 * @return String ： 批量查询的SQL。
	 */
	public List findByVO(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		ApnUserSVO apnUser = (ApnUserSVO) vo;
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT CREATED_DATE,EMAIL,ID,NAME,PASSWORD,UPDATED_DATE,USERNAME FROM APN_USER WHERE 1=1 ");
		try {
			if (apnUser.getFlagCreatedDate() == 1) {
				if (apnUser.getCreatedDate() == null) {
					sql.append(" and CREATED_DATE is null ");
				} else {
					sql.append(" and CREATED_DATE=:createdDate");
					sql.setTimestamp("createdDate", apnUser.getCreatedDate());
				}
			}

			if (apnUser.getFlagEmail() == 1) {
				if (StringUtil.isBlank(apnUser.getEmail())) {
					sql.append(" and EMAIL is null ");
				} else {
					sql.append(" and EMAIL=:email");
					sql.setString("email", apnUser.getEmail());
				}
			}

			if (apnUser.getFlagId() == 1) {
				if (StringUtil.isBlank(apnUser.getId())) {
					sql.append(" and ID is null ");
				} else {
					sql.append(" and ID=:id");
					sql.setLong("id", apnUser.getId());
				}
			}

			if (apnUser.getFlagName() == 1) {
				if (StringUtil.isBlank(apnUser.getName())) {
					sql.append(" and NAME is null ");
				} else {
					sql.append(" and NAME=:name");
					sql.setString("name", apnUser.getName());
				}
			}

			if (apnUser.getFlagPassword() == 1) {
				if (StringUtil.isBlank(apnUser.getPassword())) {
					sql.append(" and PASSWORD is null ");
				} else {
					sql.append(" and PASSWORD=:password");
					sql.setString("password", apnUser.getPassword());
				}
			}

			if (apnUser.getFlagUpdatedDate() == 1) {
				if (apnUser.getUpdatedDate() == null) {
					sql.append(" and UPDATED_DATE is null ");
				} else {
					sql.append(" and UPDATED_DATE=:updatedDate");
					sql.setTimestamp("updatedDate", apnUser.getUpdatedDate());
				}
			}

			if (apnUser.getFlagUsername() == 1) {
				if (StringUtil.isBlank(apnUser.getUsername())) {
					sql.append(" and USERNAME is null ");
				} else {
					sql.append(" and USERNAME=:username");
					sql.setString("username", apnUser.getUsername());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				apnUser = new ApnUserSVO();
				apnUser.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
				apnUser.setEmail(rs.getString("EMAIL"));
				apnUser.setId(rs.getString("ID"));
				apnUser.setName(rs.getString("NAME"));
				apnUser.setPassword(rs.getString("PASSWORD"));
				apnUser.setUpdatedDate(rs.getTimestamp("UPDATED_DATE"));
				apnUser.setUsername(rs.getString("USERNAME"));
				res.add(apnUser);

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
	 * @return String ： 更新的SQL。
	 */
	public void update(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		ApnUserSVO apnUser = (ApnUserSVO) vo;
		if (StringUtil.isBlank(apnUser.getId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("UPDATE APN_USER SET ");
		try {
			if (apnUser.getFlagCreatedDate() == 1) {
				sql.append("CREATED_DATE=:createdDate,");
				sql.setTimestamp("createdDate", apnUser.getCreatedDate());
			}

			if (apnUser.getFlagEmail() == 1) {
				sql.append("EMAIL=:email,");
				sql.setString("email", apnUser.getEmail());
			}

			if (apnUser.getFlagName() == 1) {
				sql.append("NAME=:name,");
				sql.setString("name", apnUser.getName());
			}

			if (apnUser.getFlagPassword() == 1) {
				sql.append("PASSWORD=:password,");
				sql.setString("password", apnUser.getPassword());
			}

			if (apnUser.getFlagUpdatedDate() == 1) {
				sql.append("UPDATED_DATE=:updatedDate,");
				sql.setTimestamp("updatedDate", apnUser.getUpdatedDate());
			}

			if (apnUser.getFlagUsername() == 1) {
				sql.append("USERNAME=:username,");
				sql.setString("username", apnUser.getUsername());
			}

			sql.removeSuffix(1);
			sql.append(" WHERE 1=1 ");
			sql.append(" and ID=:id");
			sql.setLong("id", apnUser.getId());

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
	 * @return String ： 批量增加的SQL。
	 */
	public void addBat(List list) throws AppException, SysException {
		if (list == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO APN_USER(CREATED_DATE,EMAIL,ID,NAME,PASSWORD,UPDATED_DATE,USERNAME)");
		sql
				.append(" VALUES (:createdDate,:email,:id,:name,:password,:updatedDate,:username)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			for (int i = 0; i < list.size(); i++) {
				ApnUserSVO apnUser = (ApnUserSVO) list.get(i);
				if (apnUser == null) {
					throw new AppException("100001", "缺少DAO操作对象！");
				}
				if (StringUtil.isBlank(apnUser.getId())) {
					throw new AppException("100002", "缺少对象的主键！");
				}
				if (apnUser.getCreatedDate() == null) {
					sql.setNullDate("createdDate");
				} else {
					sql.setTimestamp("createdDate", apnUser.getCreatedDate());
				}

				if (StringUtil.isBlank(apnUser.getEmail())) {
					sql.setNullString("email");
				} else {
					sql.setString("email", apnUser.getEmail());
				}

				if (StringUtil.isBlank(apnUser.getId())) {
					sql.setNullLong("id");
				} else {
					sql.setLong("id", apnUser.getId());
				}

				if (StringUtil.isBlank(apnUser.getName())) {
					sql.setNullString("name");
				} else {
					sql.setString("name", apnUser.getName());
				}

				if (StringUtil.isBlank(apnUser.getPassword())) {
					sql.setNullString("password");
				} else {
					sql.setString("password", apnUser.getPassword());
				}

				if (apnUser.getUpdatedDate() == null) {
					sql.setNullDate("updatedDate");
				} else {
					sql.setTimestamp("updatedDate", apnUser.getUpdatedDate());
				}

				if (StringUtil.isBlank(apnUser.getUsername())) {
					sql.setNullString("username");
				} else {
					sql.setString("username", apnUser.getUsername());
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
	 * @return String ： 删除的SQL。
	 */
	public void delete(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		ApnUserSVO apnUser = (ApnUserSVO) vo;
		if (StringUtil.isBlank(apnUser.getId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("DELETE FROM APN_USER WHERE 1=1  ");
		sql.append(" and ID=:id");
		sql.setLong("id", apnUser.getId());

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
	 * @return String ： 注销一条或者一批的SQL。
	 */
	public void unable(GenericVO vo) throws AppException, SysException {
		ApnUserSVO apnUser = (ApnUserSVO) vo;
	}
}
