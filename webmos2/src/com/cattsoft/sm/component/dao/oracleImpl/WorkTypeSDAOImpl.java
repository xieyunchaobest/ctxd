package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IWorkTypeSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.WorkTypeSVO;

public class WorkTypeSDAOImpl implements IWorkTypeSDAO {

	/**
	 * 增加。
	 * 
	 * @return String
	 */
	public void add(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		WorkTypeSVO workType = (WorkTypeSVO) vo;
		if (StringUtil.isBlank(workType.getWorkTypeId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO WORK_TYPE(ABB_WORD,ACTION_ID,CREATE_DATE,NAME,REMARKS,STS,STS_DATE,TYPE,WORK_TYPE_ID)");
		sql
				.append(" VALUES (:abbWord,:actionId,:createDate,:name,:remarks,:sts,:stsDate,:type,:workTypeId)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			if (StringUtil.isBlank(workType.getAbbWord())) {
				sql.setNullString("abbWord");
			} else {
				sql.setString("abbWord", workType.getAbbWord());
			}

			if (StringUtil.isBlank(workType.getActionId())) {
				sql.setNullLong("actionId");
			} else {
				sql.setLong("actionId", workType.getActionId());
			}

			if (workType.getCreateDate() == null) {
				sql.setNullDate("createDate");
			} else {
				sql.setTimestamp("createDate", workType.getCreateDate());
			}

			if (StringUtil.isBlank(workType.getName())) {
				sql.setNullString("name");
			} else {
				sql.setString("name", workType.getName());
			}

			if (StringUtil.isBlank(workType.getRemarks())) {
				sql.setNullString("remarks");
			} else {
				sql.setString("remarks", workType.getRemarks());
			}

			if (StringUtil.isBlank(workType.getSts())) {
				sql.setNullString("sts");
			} else {
				sql.setString("sts", workType.getSts());
			}

			if (workType.getStsDate() == null) {
				sql.setNullDate("stsDate");
			} else {
				sql.setTimestamp("stsDate", workType.getStsDate());
			}

			if (StringUtil.isBlank(workType.getType())) {
				sql.setNullString("type");
			} else {
				sql.setString("type", workType.getType());
			}

			if (StringUtil.isBlank(workType.getWorkTypeId())) {
				sql.setNullLong("workTypeId");
			} else {
				sql.setLong("workTypeId", workType.getWorkTypeId());
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
		WorkTypeSVO workType = (WorkTypeSVO) vo;
		if (StringUtil.isBlank(workType.getWorkTypeId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}

		Sql sql = new Sql(
				"SELECT ABB_WORD,ACTION_ID,CREATE_DATE,NAME,REMARKS,STS,STS_DATE,TYPE,WORK_TYPE_ID FROM WORK_TYPE WHERE 1=1  ");
		sql.append(" and WORK_TYPE_ID=:workTypeId");
		sql.setLong("workTypeId", workType.getWorkTypeId());

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		workType = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				workType = new WorkTypeSVO();
				workType.setAbbWord(rs.getString("ABB_WORD"));
				workType.setActionId(rs.getString("ACTION_ID"));
				workType.setCreateDate(rs.getDate("CREATE_DATE"));
				workType.setName(rs.getString("NAME"));
				workType.setRemarks(rs.getString("REMARKS"));
				workType.setSts(rs.getString("STS"));
				workType.setStsDate(rs.getDate("STS_DATE"));
				workType.setType(rs.getString("TYPE"));
				workType.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return workType;
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
		WorkTypeSVO workType = (WorkTypeSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT ABB_WORD,ACTION_ID,CREATE_DATE,NAME,REMARKS,STS,STS_DATE,TYPE,WORK_TYPE_ID FROM WORK_TYPE WHERE 1=1 ");
		try {
			if (workType.getFlagAbbWord() == 1) {
				if (StringUtil.isBlank(workType.getAbbWord())) {
					sql.append(" and ABB_WORD is null ");
				} else {
					sql.append(" and ABB_WORD=:abbWord");
					sql.setString("abbWord", workType.getAbbWord());
				}
			}

			if (workType.getFlagActionId() == 1) {
				if (StringUtil.isBlank(workType.getActionId())) {
					sql.append(" and ACTION_ID is null ");
				} else {
					sql.append(" and ACTION_ID=:actionId");
					sql.setLong("actionId", workType.getActionId());
				}
			}

			if (workType.getFlagCreateDate() == 1) {
				if (workType.getCreateDate() == null) {
					sql.append(" and CREATE_DATE is null ");
				} else {
					sql.append(" and CREATE_DATE=:createDate");
					sql.setTimestamp("createDate", workType.getCreateDate());
				}
			}

			if (workType.getFlagName() == 1) {
				if (StringUtil.isBlank(workType.getName())) {
					sql.append(" and NAME is null ");
				} else {
					sql.append(" and NAME=:name");
					sql.setString("name", workType.getName());
				}
			}

			if (workType.getFlagRemarks() == 1) {
				if (StringUtil.isBlank(workType.getRemarks())) {
					sql.append(" and REMARKS is null ");
				} else {
					sql.append(" and REMARKS=:remarks");
					sql.setString("remarks", workType.getRemarks());
				}
			}

			if (workType.getFlagSts() == 1) {
				if (StringUtil.isBlank(workType.getSts())) {
					sql.append(" and STS is null ");
				} else {
					sql.append(" and STS=:sts");
					sql.setString("sts", workType.getSts());
				}
			}

			if (workType.getFlagStsDate() == 1) {
				if (workType.getStsDate() == null) {
					sql.append(" and STS_DATE is null ");
				} else {
					sql.append(" and STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", workType.getStsDate());
				}
			}

			if (workType.getFlagType() == 1) {
				if (StringUtil.isBlank(workType.getType())) {
					sql.append(" and TYPE is null ");
				} else {
					sql.append(" and TYPE=:type");
					sql.setString("type", workType.getType());
				}
			}

			if (workType.getFlagWorkTypeId() == 1) {
				if (StringUtil.isBlank(workType.getWorkTypeId())) {
					sql.append(" and WORK_TYPE_ID is null ");
				} else {
					sql.append(" and WORK_TYPE_ID=:workTypeId");
					sql.setLong("workTypeId", workType.getWorkTypeId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				workType = new WorkTypeSVO();
				workType.setAbbWord(rs.getString("ABB_WORD"));
				workType.setActionId(rs.getString("ACTION_ID"));
				workType.setCreateDate(rs.getDate("CREATE_DATE"));
				workType.setName(rs.getString("NAME"));
				workType.setRemarks(rs.getString("REMARKS"));
				workType.setSts(rs.getString("STS"));
				workType.setStsDate(rs.getDate("STS_DATE"));
				workType.setType(rs.getString("TYPE"));
				workType.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
				res.add(workType);

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
		WorkTypeSVO workType = (WorkTypeSVO) vo;
		if (StringUtil.isBlank(workType.getWorkTypeId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("UPDATE WORK_TYPE SET ");
		try {
			if (workType.getFlagAbbWord() == 1) {
				sql.append("ABB_WORD=:abbWord,");
				sql.setString("abbWord", workType.getAbbWord());
			}

			if (workType.getFlagActionId() == 1) {
				sql.append("ACTION_ID=:actionId,");
				sql.setLong("actionId", workType.getActionId());
			}

			if (workType.getFlagCreateDate() == 1) {
				sql.append("CREATE_DATE=:createDate,");
				sql.setTimestamp("createDate", workType.getCreateDate());
			}

			if (workType.getFlagName() == 1) {
				sql.append("NAME=:name,");
				sql.setString("name", workType.getName());
			}

			if (workType.getFlagRemarks() == 1) {
				sql.append("REMARKS=:remarks,");
				sql.setString("remarks", workType.getRemarks());
			}

			if (workType.getFlagSts() == 1) {
				sql.append("STS=:sts,");
				sql.setString("sts", workType.getSts());
			}

			if (workType.getFlagStsDate() == 1) {
				sql.append("STS_DATE=:stsDate,");
				sql.setTimestamp("stsDate", workType.getStsDate());
			}

			if (workType.getFlagType() == 1) {
				sql.append("TYPE=:type,");
				sql.setString("type", workType.getType());
			}

			sql.removeSuffix(1);
			sql.append(" WHERE 1=1 ");
			sql.append(" and WORK_TYPE_ID=:workTypeId");
			sql.setLong("workTypeId", workType.getWorkTypeId());

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
				"INSERT INTO WORK_TYPE(ABB_WORD,ACTION_ID,CREATE_DATE,NAME,REMARKS,STS,STS_DATE,TYPE,WORK_TYPE_ID)");
		sql
				.append(" VALUES (:abbWord,:actionId,:createDate,:name,:remarks,:sts,:stsDate,:type,:workTypeId)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			for (int i = 0; i < list.size(); i++) {
				WorkTypeSVO workType = (WorkTypeSVO) list.get(i);
				if (workType == null) {
					throw new AppException("100001", "缺少DAO操作对象！");
				}
				if (StringUtil.isBlank(workType.getWorkTypeId())) {
					throw new AppException("100002", "缺少对象的主键！");
				}
				if (StringUtil.isBlank(workType.getAbbWord())) {
					sql.setNullString("abbWord");
				} else {
					sql.setString("abbWord", workType.getAbbWord());
				}

				if (StringUtil.isBlank(workType.getActionId())) {
					sql.setNullLong("actionId");
				} else {
					sql.setLong("actionId", workType.getActionId());
				}

				if (workType.getCreateDate() == null) {
					sql.setNullDate("createDate");
				} else {
					sql.setTimestamp("createDate", workType.getCreateDate());
				}

				if (StringUtil.isBlank(workType.getName())) {
					sql.setNullString("name");
				} else {
					sql.setString("name", workType.getName());
				}

				if (StringUtil.isBlank(workType.getRemarks())) {
					sql.setNullString("remarks");
				} else {
					sql.setString("remarks", workType.getRemarks());
				}

				if (StringUtil.isBlank(workType.getSts())) {
					sql.setNullString("sts");
				} else {
					sql.setString("sts", workType.getSts());
				}

				if (workType.getStsDate() == null) {
					sql.setNullDate("stsDate");
				} else {
					sql.setTimestamp("stsDate", workType.getStsDate());
				}

				if (StringUtil.isBlank(workType.getType())) {
					sql.setNullString("type");
				} else {
					sql.setString("type", workType.getType());
				}

				if (StringUtil.isBlank(workType.getWorkTypeId())) {
					sql.setNullLong("workTypeId");
				} else {
					sql.setLong("workTypeId", workType.getWorkTypeId());
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
		WorkTypeSVO workType = (WorkTypeSVO) vo;
		if (StringUtil.isBlank(workType.getWorkTypeId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("DELETE FROM WORK_TYPE WHERE 1=1  ");
		sql.append(" and WORK_TYPE_ID=:workTypeId");
		sql.setLong("workTypeId", workType.getWorkTypeId());

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
		//WorkTypeSVO workType = (WorkTypeSVO) vo;
	}

	
     public PagView findWorkTypesByPage(WorkTypeSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
        List results = null;
        WorkTypeSVO workType = (WorkTypeSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.WORK_TYPE_ID,a.NAME,a.ABB_WORD,a.TYPE,a.CREATE_DATE,a.co_meth ");
        sql.append(" from WORK_TYPE a where 1=1");
        

        if (vo == null && set != null) {
            sql.append(" and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "work_type_id", "workTypeId"));
            sql.append(" ) ");
        }
        if (vo != null && set == null) {
        if (workType.getWorkTypeId() != null&&!workType.getWorkTypeId().equals("")) {
            sql.append(" and WORK_TYPE_ID="+workType.getWorkTypeId());
        }
        if (workType.getName() != null&&!workType.getName().equals("")) {
            sql.append(" and NAME like '%"+workType.getName()+"%'");
        }
        if (workType.getAbbWord() != null) {
            sql.append(" and ABB_WORD="+workType.getAbbWord());
        }
        if (workType.getType() != null) {
            sql.append(" and TYPE='"+workType.getType()+"'");
        }
        if (workType.getCreateDate() != null) {
            sql.append(" and CREATE_DATE="+workType.getCreateDate());
        }
        if (workType.getCoMeth() != null) {
            sql.append(" and co_meth='"+workType.getCoMeth()+"'");
        }
        }
        sql.append(" order by work_type_id");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(WorkTypeSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs,WorkTypeSVO.class);
            pagView.setViewList(results);
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
        return pagView;
    }


}
