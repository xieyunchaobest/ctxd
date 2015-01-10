package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IWorkAreaExchSDAO;
import com.cattsoft.sm.vo.WorkAreaExchSVO;

/**
 * 方法WorkAreaExchSDAOImpl
 * <p>
 * Company: 大唐软件
 * </p>
 * 
 * @author ：白小亮。
 * @version 1.1 2007-9-23
 */
public class WorkAreaExchSDAOImpl implements IWorkAreaExchSDAO {
//	private static Logger log = Logger.getLogger(WorkAreaExchSDAOImpl.class);
//
//	private static final String UNABLE_STS = "P";

	/**
	 * 增加。
	 * 
	 * @return String
	 */
	public void add(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		WorkAreaExchSVO workAreaExch = (WorkAreaExchSVO) vo;
		if (StringUtil.isBlank(workAreaExch.getWorkAreaExchId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO WORK_AREA_EXCH(CREATE_DATE,EXCH_ID,STS,STS_DATE,WORK_AREA_EXCH_ID,WORK_AREA_ID)");
		sql.append(" VALUES (:createDate,:exchId,:sts,:stsDate,:workAreaExchId,:workAreaId)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			if (workAreaExch.getCreateDate() == null) {
				sql.setNullDate("createDate");
			} else {
				sql.setTimestamp("createDate", workAreaExch.getCreateDate());
			}

			if (StringUtil.isBlank(workAreaExch.getExchId())) {
				sql.setNullLong("exchId");
			} else {
				sql.setLong("exchId", workAreaExch.getExchId());
			}

			if (StringUtil.isBlank(workAreaExch.getSts())) {
				sql.setNullString("sts");
			} else {
				sql.setString("sts", workAreaExch.getSts());
			}

			if (workAreaExch.getStsDate() == null) {
				sql.setNullDate("stsDate");
			} else {
				sql.setTimestamp("stsDate", workAreaExch.getStsDate());
			}

			if (StringUtil.isBlank(workAreaExch.getWorkAreaExchId())) {
				sql.setNullLong("workAreaExchId");
			} else {
				sql.setLong("workAreaExchId", workAreaExch.getWorkAreaExchId());
			}

			if (StringUtil.isBlank(workAreaExch.getWorkAreaId())) {
				sql.setNullLong("workAreaId");
			} else {
				sql.setLong("workAreaId", workAreaExch.getWorkAreaId());
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
		WorkAreaExchSVO workAreaExch = (WorkAreaExchSVO) vo;
		if (StringUtil.isBlank(workAreaExch.getWorkAreaExchId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Sql sql = new Sql(
				"SELECT CREATE_DATE,EXCH_ID,STS,STS_DATE,WORK_AREA_EXCH_ID,WORK_AREA_ID FROM WORK_AREA_EXCH WHERE 1=1  ");
		sql.append(" and WORK_AREA_EXCH_ID=:workAreaExchId");
		sql.setLong("workAreaExchId", workAreaExch.getWorkAreaExchId());

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		workAreaExch = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				workAreaExch = new WorkAreaExchSVO();
				workAreaExch.setCreateDate(rs.getDate("CREATE_DATE"));
				workAreaExch.setExchId(rs.getString("EXCH_ID"));
				workAreaExch.setSts(rs.getString("STS"));
				workAreaExch.setStsDate(rs.getDate("STS_DATE"));
				workAreaExch.setWorkAreaExchId(rs.getString("WORK_AREA_EXCH_ID"));
				workAreaExch.setWorkAreaId(rs.getString("WORK_AREA_ID"));
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return workAreaExch;
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
		WorkAreaExchSVO workAreaExch = (WorkAreaExchSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT CREATE_DATE,EXCH_ID,STS,STS_DATE,WORK_AREA_EXCH_ID,WORK_AREA_ID FROM WORK_AREA_EXCH WHERE 1=1 ");
		try {
			if (workAreaExch.getFlagCreateDate() == 1) {
				if (workAreaExch.getCreateDate() == null) {
					sql.append(" and CREATE_DATE is null ");
				} else {
					sql.append(" and CREATE_DATE=:createDate");
					sql.setTimestamp("createDate", workAreaExch.getCreateDate());
				}
			}

			if (workAreaExch.getFlagExchId() == 1) {
				if (StringUtil.isBlank(workAreaExch.getExchId())) {
					sql.append(" and EXCH_ID is null ");
				} else {
					sql.append(" and EXCH_ID=:exchId");
					sql.setLong("exchId", workAreaExch.getExchId());
				}
			}

			if (workAreaExch.getFlagSts() == 1) {
				if (StringUtil.isBlank(workAreaExch.getSts())) {
					sql.append(" and STS is null ");
				} else {
					sql.append(" and STS=:sts");
					sql.setString("sts", workAreaExch.getSts());
				}
			}

			if (workAreaExch.getFlagStsDate() == 1) {
				if (workAreaExch.getStsDate() == null) {
					sql.append(" and STS_DATE is null ");
				} else {
					sql.append(" and STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", workAreaExch.getStsDate());
				}
			}

			if (workAreaExch.getFlagWorkAreaExchId() == 1) {
				if (StringUtil.isBlank(workAreaExch.getWorkAreaExchId())) {
					sql.append(" and WORK_AREA_EXCH_ID is null ");
				} else {
					sql.append(" and WORK_AREA_EXCH_ID=:workAreaExchId");
					sql.setLong("workAreaExchId", workAreaExch.getWorkAreaExchId());
				}
			}

			if (workAreaExch.getFlagWorkAreaId() == 1) {
				if (StringUtil.isBlank(workAreaExch.getWorkAreaId())) {
					sql.append(" and WORK_AREA_ID is null ");
				} else {
					sql.append(" and WORK_AREA_ID=:workAreaId");
					sql.setLong("workAreaId", workAreaExch.getWorkAreaId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				workAreaExch = new WorkAreaExchSVO();
				workAreaExch.setCreateDate(rs.getDate("CREATE_DATE"));
				workAreaExch.setExchId(rs.getString("EXCH_ID"));
				workAreaExch.setSts(rs.getString("STS"));
				workAreaExch.setStsDate(rs.getDate("STS_DATE"));
				workAreaExch.setWorkAreaExchId(rs.getString("WORK_AREA_EXCH_ID"));
				workAreaExch.setWorkAreaId(rs.getString("WORK_AREA_ID"));
				res.add(workAreaExch);

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
		WorkAreaExchSVO workAreaExch = (WorkAreaExchSVO) vo;
		if (StringUtil.isBlank(workAreaExch.getWorkAreaExchId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("UPDATE WORK_AREA_EXCH SET ");
		try {
			if (workAreaExch.getFlagCreateDate() == 1) {
				sql.append("CREATE_DATE=:createDate,");
				sql.setTimestamp("createDate", workAreaExch.getCreateDate());
			}

			if (workAreaExch.getFlagExchId() == 1) {
				sql.append("EXCH_ID=:exchId,");
				sql.setLong("exchId", workAreaExch.getExchId());
			}

			if (workAreaExch.getFlagSts() == 1) {
				sql.append("STS=:sts,");
				sql.setString("sts", workAreaExch.getSts());
			}

			if (workAreaExch.getFlagStsDate() == 1) {
				sql.append("STS_DATE=:stsDate,");
				sql.setTimestamp("stsDate", workAreaExch.getStsDate());
			}

			if (workAreaExch.getFlagWorkAreaId() == 1) {
				sql.append("WORK_AREA_ID=:workAreaId,");
				sql.setLong("workAreaId", workAreaExch.getWorkAreaId());
			}

			sql.removeSuffix(1);
			sql.append(" WHERE 1=1 ");
			sql.append(" and WORK_AREA_EXCH_ID=:workAreaExchId");
			sql.setLong("workAreaExchId", workAreaExch.getWorkAreaExchId());

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
				"INSERT INTO WORK_AREA_EXCH(CREATE_DATE,EXCH_ID,STS,STS_DATE,WORK_AREA_EXCH_ID,WORK_AREA_ID)");
		sql.append(" VALUES (:createDate,:exchId,:sts,:stsDate,:workAreaExchId,:workAreaId)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			for (int i = 0; i < list.size(); i++) {
				WorkAreaExchSVO workAreaExch = (WorkAreaExchSVO) list.get(i);
				if (workAreaExch == null) {
					throw new AppException("100001", "缺少DAO操作对象！");
				}
				if (StringUtil.isBlank(workAreaExch.getWorkAreaExchId())) {
					throw new AppException("100002", "缺少对象的主键！");
				}
				if (workAreaExch.getCreateDate() == null) {
					sql.setNullDate("createDate");
				} else {
					sql.setTimestamp("createDate", workAreaExch.getCreateDate());
				}

				if (StringUtil.isBlank(workAreaExch.getExchId())) {
					sql.setNullLong("exchId");
				} else {
					sql.setLong("exchId", workAreaExch.getExchId());
				}

				if (StringUtil.isBlank(workAreaExch.getSts())) {
					sql.setNullString("sts");
				} else {
					sql.setString("sts", workAreaExch.getSts());
				}

				if (workAreaExch.getStsDate() == null) {
					sql.setNullDate("stsDate");
				} else {
					sql.setTimestamp("stsDate", workAreaExch.getStsDate());
				}

				if (StringUtil.isBlank(workAreaExch.getWorkAreaExchId())) {
					sql.setNullLong("workAreaExchId");
				} else {
					sql.setLong("workAreaExchId", workAreaExch.getWorkAreaExchId());
				}

				if (StringUtil.isBlank(workAreaExch.getWorkAreaId())) {
					sql.setNullLong("workAreaId");
				} else {
					sql.setLong("workAreaId", workAreaExch.getWorkAreaId());
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
		WorkAreaExchSVO workAreaExch = (WorkAreaExchSVO) vo;
		if (StringUtil.isBlank(workAreaExch.getWorkAreaExchId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("DELETE FROM WORK_AREA_EXCH WHERE 1=1  ");
		sql.append(" and WORK_AREA_EXCH_ID=:workAreaExchId");
		sql.setLong("workAreaExchId", workAreaExch.getWorkAreaExchId());

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

	 
}
