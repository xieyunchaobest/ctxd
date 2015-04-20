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
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.vo.ActionLogSVO;


public class ActionLogSDAOImpl implements IActionLogSDAO {

	/**
	 * 增加。
	 * 
	 * @return String
	 */
	public void add(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		ActionLogSVO actionLog = (ActionLogSVO) vo;
		if (StringUtil.isBlank(actionLog.getActionId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO ACTION_LOG(ACTION_DOMAIN,ACTION_ID,ACTION_MODULE,ACTION_TEXT,ACTION_TIME,ACTION_TYPE,LOGIN_ID)");
		sql
				.append(" VALUES (:actionDomain,:actionId,:actionModule,:actionText,:actionTime,:actionType,:loginId)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			if (StringUtil.isBlank(actionLog.getActionDomain())) {
				sql.setNullString("actionDomain");
			} else {
				sql.setString("actionDomain", actionLog.getActionDomain());
			}

			if (StringUtil.isBlank(actionLog.getActionId())) {
				sql.setNullLong("actionId");
			} else {
				sql.setLong("actionId", actionLog.getActionId());
			}

			if (StringUtil.isBlank(actionLog.getActionModule())) {
				sql.setNullString("actionModule");
			} else {
				sql.setString("actionModule", actionLog.getActionModule());
			}

			if (StringUtil.isBlank(actionLog.getActionText())) {
				sql.setNullString("actionText");
			} else {
				sql.setString("actionText", actionLog.getActionText());
			}

			if (actionLog.getActionTime() == null) {
				sql.setNullDate("actionTime");
			} else {
				sql.setTimestamp("actionTime", actionLog.getActionTime());
			}

			if (StringUtil.isBlank(actionLog.getActionType())) {
				sql.setNullString("actionType");
			} else {
				sql.setString("actionType", actionLog.getActionType());
			}

			if (StringUtil.isBlank(actionLog.getLoginId())) {
				sql.setNullString("loginId");
			} else {
				sql.setString("loginId", actionLog.getLoginId());
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
		ActionLogSVO actionLog = (ActionLogSVO) vo;
		if (StringUtil.isBlank(actionLog.getActionId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Sql sql = new Sql(
				"SELECT ACTION_DOMAIN,ACTION_ID,ACTION_MODULE,ACTION_TEXT,ACTION_TIME,ACTION_TYPE,LOGIN_ID FROM ACTION_LOG WHERE 1=1  ");
		sql.append(" and ACTION_ID=:actionId");
		sql.setLong("actionId", actionLog.getActionId());

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		actionLog = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				actionLog = new ActionLogSVO();
				actionLog.setActionDomain(rs.getString("ACTION_DOMAIN"));
				actionLog.setActionId(rs.getString("ACTION_ID"));
				actionLog.setActionModule(rs.getString("ACTION_MODULE"));
				actionLog.setActionText(rs.getString("ACTION_TEXT"));
				actionLog.setActionTime(rs.getTimestamp("ACTION_TIME"));
				actionLog.setActionType(rs.getString("ACTION_TYPE"));
				actionLog.setLoginId(rs.getString("LOGIN_ID"));
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return actionLog;
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
		ActionLogSVO actionLog = (ActionLogSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT ACTION_DOMAIN,ACTION_ID,ACTION_MODULE,ACTION_TEXT,ACTION_TIME,ACTION_TYPE,LOGIN_ID FROM ACTION_LOG WHERE 1=1 ");
		try {
			if (actionLog.getFlagActionDomain() == 1) {
				if (StringUtil.isBlank(actionLog.getActionDomain())) {
					sql.append(" and ACTION_DOMAIN is null ");
				} else {
					sql.append(" and ACTION_DOMAIN=:actionDomain");
					sql.setString("actionDomain", actionLog.getActionDomain());
				}
			}

			if (actionLog.getFlagActionId() == 1) {
				if (StringUtil.isBlank(actionLog.getActionId())) {
					sql.append(" and ACTION_ID is null ");
				} else {
					sql.append(" and ACTION_ID=:actionId");
					sql.setLong("actionId", actionLog.getActionId());
				}
			}

			if (actionLog.getFlagActionModule() == 1) {
				if (StringUtil.isBlank(actionLog.getActionModule())) {
					sql.append(" and ACTION_MODULE is null ");
				} else {
					sql.append(" and ACTION_MODULE=:actionModule");
					sql.setString("actionModule", actionLog.getActionModule());
				}
			}

			if (actionLog.getFlagActionText() == 1) {
				if (StringUtil.isBlank(actionLog.getActionText())) {
					sql.append(" and ACTION_TEXT is null ");
				} else {
					sql.append(" and ACTION_TEXT=:actionText");
					sql.setString("actionText", actionLog.getActionText());
				}
			}

			if (actionLog.getFlagActionTime() == 1) {
				if (actionLog.getActionTime() == null) {
					sql.append(" and ACTION_TIME is null ");
				} else {
					sql.append(" and ACTION_TIME=:actionTime");
					sql.setTimestamp("actionTime", actionLog.getActionTime());
				}
			}

			if (actionLog.getFlagActionType() == 1) {
				if (StringUtil.isBlank(actionLog.getActionType())) {
					sql.append(" and ACTION_TYPE is null ");
				} else {
					sql.append(" and ACTION_TYPE=:actionType");
					sql.setString("actionType", actionLog.getActionType());
				}
			}

			if (actionLog.getFlagLoginId() == 1) {
				if (StringUtil.isBlank(actionLog.getLoginId())) {
					sql.append(" and LOGIN_ID is null ");
				} else {
					sql.append(" and LOGIN_ID=:loginId");
					sql.setString("loginId", actionLog.getLoginId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				actionLog = new ActionLogSVO();
				actionLog.setActionDomain(rs.getString("ACTION_DOMAIN"));
				actionLog.setActionId(rs.getString("ACTION_ID"));
				actionLog.setActionModule(rs.getString("ACTION_MODULE"));
				actionLog.setActionText(rs.getString("ACTION_TEXT"));
				actionLog.setActionTime(rs.getTimestamp("ACTION_TIME"));
				actionLog.setActionType(rs.getString("ACTION_TYPE"));
				actionLog.setLoginId(rs.getString("LOGIN_ID"));
				res.add(actionLog);

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
		ActionLogSVO actionLog = (ActionLogSVO) vo;
		if (StringUtil.isBlank(actionLog.getActionId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("UPDATE ACTION_LOG SET ");
		try {
			if (actionLog.getFlagActionDomain() == 1) {
				sql.append("ACTION_DOMAIN=:actionDomain,");
				sql.setString("actionDomain", actionLog.getActionDomain());
			}

			if (actionLog.getFlagActionModule() == 1) {
				sql.append("ACTION_MODULE=:actionModule,");
				sql.setString("actionModule", actionLog.getActionModule());
			}

			if (actionLog.getFlagActionText() == 1) {
				sql.append("ACTION_TEXT=:actionText,");
				sql.setString("actionText", actionLog.getActionText());
			}

			if (actionLog.getFlagActionTime() == 1) {
				sql.append("ACTION_TIME=:actionTime,");
				sql.setTimestamp("actionTime", actionLog.getActionTime());
			}

			if (actionLog.getFlagActionType() == 1) {
				sql.append("ACTION_TYPE=:actionType,");
				sql.setString("actionType", actionLog.getActionType());
			}

			if (actionLog.getFlagLoginId() == 1) {
				sql.append("LOGIN_ID=:loginId,");
				sql.setString("loginId", actionLog.getLoginId());
			}

			sql.removeSuffix(1);
			sql.append(" WHERE 1=1 ");
			sql.append(" and ACTION_ID=:actionId");
			sql.setLong("actionId", actionLog.getActionId());

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			ps.executeQuery();

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
				"INSERT INTO ACTION_LOG(ACTION_DOMAIN,ACTION_ID,ACTION_MODULE,ACTION_TEXT,ACTION_TIME,ACTION_TYPE,LOGIN_ID)");
		sql
				.append(" VALUES (:actionDomain,:actionId,:actionModule,:actionText,:actionTime,:actionType,:loginId)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			for (int i = 0; i < list.size(); i++) {
				ActionLogSVO actionLog = (ActionLogSVO) list.get(i);
				if (actionLog == null) {
					throw new AppException("100001", "缺少DAO操作对象！");
				}
				if (StringUtil.isBlank(actionLog.getActionId())) {
					throw new AppException("100002", "缺少对象的主键！");
				}
				if (StringUtil.isBlank(actionLog.getActionDomain())) {
					sql.setNullString("actionDomain");
				} else {
					sql.setString("actionDomain", actionLog.getActionDomain());
				}

				if (StringUtil.isBlank(actionLog.getActionId())) {
					sql.setNullLong("actionId");
				} else {
					sql.setLong("actionId", actionLog.getActionId());
				}

				if (StringUtil.isBlank(actionLog.getActionModule())) {
					sql.setNullString("actionModule");
				} else {
					sql.setString("actionModule", actionLog.getActionModule());
				}

				if (StringUtil.isBlank(actionLog.getActionText())) {
					sql.setNullString("actionText");
				} else {
					sql.setString("actionText", actionLog.getActionText());
				}

				if (actionLog.getActionTime() == null) {
					sql.setNullDate("actionTime");
				} else {
					sql.setTimestamp("actionTime", actionLog.getActionTime());
				}

				if (StringUtil.isBlank(actionLog.getActionType())) {
					sql.setNullString("actionType");
				} else {
					sql.setString("actionType", actionLog.getActionType());
				}

				if (StringUtil.isBlank(actionLog.getLoginId())) {
					sql.setNullString("loginId");
				} else {
					sql.setString("loginId", actionLog.getLoginId());
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
		ActionLogSVO actionLog = (ActionLogSVO) vo;
		if (StringUtil.isBlank(actionLog.getActionId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("DELETE FROM ACTION_LOG WHERE 1=1  ");
		sql.append(" and ACTION_ID=:actionId");
		sql.setLong("actionId", actionLog.getActionId());

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
