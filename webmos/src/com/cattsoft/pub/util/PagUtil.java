package com.cattsoft.pub.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;

/**
 * 
 * <p>
 * Title: 分页工具类
 * </p>
 * <p>
 * Description: 新九七系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Com ion 1.0
 */
public class PagUtil {
	public PagUtil() {
	}

	private static Logger log = Logger.getLogger(PagUtil.class);

	private static int PAGE_NUM = 1000;// 初始页数。

	/**
	 * 获得分页信息,包括每页显示记录数,和要显示的页.
	 * 当没有得到值时默认值为-1.PagInfo.getPagSize()必须有正确的值.若为-1就无法得到正确结果
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @return PagInfo
	 */
	static public PagInfo getPagInfo(HttpServletRequest req) {
		PagInfo pagInfo = new PagInfo();
		int pagSize = req.getParameter("pagSize") == null ? 1000 : Integer
				.parseInt(req.getParameter("pagSize"));
		int pageNo = req.getParameter("pageNo") == null ? -1 : Integer
				.parseInt(req.getParameter("pageNo"));
		log.debug("pageSize" + pagSize);
		log.debug(req.getParameter("pagSize"));
		pagInfo.setPagSize(pagSize);
		pagInfo.setPagNo(pageNo);
		return pagInfo;
	}

	/**
	 * 推荐使用] 获得分页信息,包括每页显示记录数,和要显示的页.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param pagSize
	 *            int
	 * @return PagInfo
	 */
	static public PagInfo getPagInfo(int pageNo, int pagSize) {
		PagInfo pagInfo = new PagInfo();
		pagInfo.setPagSize(pagSize);
		pagInfo.setPagNo(pageNo);
		return pagInfo;
	}
	/**
	 * 推荐使用] 获得分页信息,包括每页显示记录数,和要显示的页.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param pagSize
	 *            int
	 * @return PagInfo
	 */
	static public PagInfo getPagInfo(HttpServletRequest req, int pagSize) {
		PagInfo pagInfo = new PagInfo();
		pagSize = req.getParameter("pagSize") == null ? pagSize : Integer
				.parseInt(req.getParameter("pagSize"));
		int pageNo = req.getParameter("pageNo") == null ? -1 : Integer
				.parseInt(req.getParameter("pageNo"));
		pagInfo.setPagSize(pagSize);
		pagInfo.setPagNo(pageNo);
		return pagInfo;
	}

	/**
	 * 获得分页查询的SQL 获得的SQL仅仅使用于Oracle 用于JDBC方式的分页查询
	 * 
	 * @param sql
	 *            String
	 * @return String
	 */
	public static Sql getPagSql(Sql pageSql, PagInfo pagInfo) {
		int pageSize = pagInfo.getPagSize(); // 每页显示记录数
		int pageNo = pagInfo.getPagNo(); // 第几页
		if (pageNo < 0)
			pageNo = 1;

		pageSql.insert(0,
				" select * from ( select row_.*, rownum rownum_ from ( ");
		pageSql
				.append(" )  row_  where rownum <=:startOraclePageNum) where rownum_ >=:endOraclePageNum ");

		pageSql
				.setInteger("startOraclePageNum",
						new Integer(pageNo * pageSize));
		pageSql.setInteger("endOraclePageNum", new Integer((pageNo - 1)
				* pageSize + 1));

		return pageSql;
	}

	/**
	 * 获得查询数目总数的SQL, 用于JDBC的查询,仅使用于Oracle
	 * 
	 * @param sql
	 *            String
	 * @return String
	 */
	public static String getCountSql(String sql) {
		sql = deleteOrderBy(sql);
		StringBuffer pagingSelect = new StringBuffer();
		pagingSelect.append("select count(*) from (");
		pagingSelect.append(sql);
		pagingSelect.append(")");
		return pagingSelect.toString();
	}

	/**
	 * author gongzhen
	 * 
	 * 截取传递进来的sql中的最后一个order by
	 * 
	 * @return String
	 */
	public static String deleteOrderBy(String tempStr) {
		tempStr = tempStr.replaceAll(" {2,}", " ");
		String s = tempStr.toUpperCase();
		// 如果首尾对括号，则去掉
		if ("(".equals(s.substring(0, 1))
				&& ")".equals(s.substring(s.length() - 1, s.length()))) {
			tempStr = tempStr.substring(1, s.length() - 1);
			s = s.substring(1, s.length() - 1);
		}
		int i = s.lastIndexOf(" ORDER BY ");
		// 没有order by 直接返回 否则继续
		if (i != -1) {
			String temp = "";
			temp = tempStr.toString().substring(i, s.length() - 1);
			// 判断最后一个order by之后是否还存在“）” 则不做处理,否则截取最后一个order by之前的子句
			int count = 0;
			if (-1 == temp.indexOf("(") && -1 == temp.indexOf(")")) {
				count++;
			}
			// TODO 考虑其他可能的情况，补充if
			if (count > 0)
				tempStr = tempStr.substring(0, i);
		}
		return tempStr;
	}

	/**
	 * 获得查询数目总数的Hql,用于Herve
	 * 
	 * @param hql
	 *            String
	 * @return String
	 */
	public static String getCountHql(String hql) {
		String countHql = null;
		String tmpStr = null;
		if (hql.toLowerCase().indexOf("select") < 0) {
			countHql = "select count(*)  " + hql;
		} else {
			if (hql.toLowerCase().indexOf("distinct") < 0) {
				tmpStr = hql.substring(hql.toLowerCase().indexOf("from"));
				// System.out.println("hql:" + tmpStr);
				countHql = "select count(*)  " + tmpStr + "";
			} else {
				countHql = hql;
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("countHql:" + countHql);
		}
		return countHql;
	}

	/*
	 * public static PagView InitPagView(Sql sql, PagInfo pagInfo) { int count =
	 * pagInfo.getCount(); // 总记录数 int pageSize = pagInfo.getPagSize(); //
	 * 每页显示记录数 int pageNo = pagInfo.getPagNo(); // 第几页 if (pageNo < 0) { //
	 * 没有初始化默认为第一页 pageNo = 1; } if (pageSize < 0) pageSize = 1000; PagView pv =
	 * new PagView(); PreparedStatement ps =null; ResultSet rs = null; try {
	 * Connection conn = ConnectionFactory.getConnection(); ps =
	 * conn.prepareStatement(getCountHql(sql.getSql())); sql.fillParams(ps); if
	 * (sql.getSql().toLowerCase().indexOf("distinct") < 0) { rs =
	 * ps.executeQuery(); if(rs.next()){ count = rs.getInt(0); } } else { rs =
	 * ps.executeQuery(); int i = 0; while (rs.next()) { i++; } count = i; }
	 * pv.setCount(count); if (log.isDebugEnabled()) log.debug("count:" +
	 * count); if (count % pageSize == 0) { // 计算页数 pv.setPagCount(count /
	 * pageSize); } else { pv.setPagCount(count / pageSize + 1); } if (pageNo >
	 * pv.getPagCount()) pageNo = pv.getPagCount(); pv.setPagSize(pageSize);
	 * pv.setPagNo(pageNo); if (log.isDebugEnabled()) log.debug("pageSize：" +
	 * pv.getPagSize()); if (log.isDebugEnabled()) log.debug("pageNo：" +
	 * pv.getPagNo()); if (log.isDebugEnabled()) log.debug("hql：" + hql); Query
	 * q = session.createQuery(hql); q.setFirstResult((pageNo - 1) * pageSize);
	 * q.setMaxResults(pageSize);
	 * 
	 * Iterator ita=q.iterate(); List lt=new ArrayList(); while(ita.hasNext()){
	 * lt.add(ita.next()); }
	 * 
	 * pv.setViewList(q.list()); } catch (HibernateException ex) {
	 * ex.printStackTrace(); throw ex; } catch (HibernateSessionException ex) {
	 * ex.printStackTrace(); } return pv; }
	 */

	/**
	 * 获得总记录数 JDBC
	 * 
	 * @param conn
	 *            Connection
	 * @param sql
	 *            String
	 * @throws SQLException
	 * @return int
	 * @throws SysException
	 * @throws AppException
	 */
	public static int getCountJDBC(Connection conn, Sql sql)
			throws AppException, SysException {
		int count = 0;
		Sql coutSql = (Sql) sql.clone();
		PreparedStatement ps = null;
		ResultSet rs = null;
		coutSql.setSqlStr(getCountSql(sql.getSql()));
		// System.out.println("coutSql:" + coutSql);
		try {
			ps = conn.prepareStatement(coutSql.getSql());
			coutSql.log(PagUtil.class);
			coutSql.fillParams(ps);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException ex) {
			throw new SysException("", "", ex);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return count;
	}

	/**
	 * 查询数据库
	 * 
	 * @param conn
	 *            Connection 连接
	 * @param sql
	 *            String 用于查询的SQL
	 * @param pv
	 *            PagView 包含了页码信息和显示数组
	 * @throws SQLException
	 * @return ResultSet
	 * @throws SysException
	 * @throws AppException
	 */
	public static ResultSet queryOracle(Connection conn, Sql sql,
			PagInfo pagInfo) throws AppException, SysException {

		Sql pageSql = (Sql) sql.clone();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql pagSql = getPagSql(pageSql, pagInfo);

		try {
			ps = conn.prepareStatement(pagSql.getSql());
			pagSql.fillParams(ps);
			pagSql.log(PagUtil.class);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException ex) {
			throw new SysException("100010", "分页查询出现异常！", ex);
		}
	}

	/**
	 * 查询数据库
	 * 
	 * @param conn
	 *            Connection 连接
	 * @param sql
	 *            String 用于查询的SQL
	 * @param pv
	 *            PagView 包含了页码信息和显示数组
	 * @throws SQLException
	 * @return ResultSet
	 * @throws AppException
	 */
	/*
	 * public static ResultSet queryOracle(String sql, PagInfo pagInfo) throws
	 * SysException { int pageSize = pagInfo.getPagSize(); // 每页显示记录数 int pageNo =
	 * pagInfo.getPagNo(); // 第几页 if (pageNo < 0) pageNo = 1; Connection conn =
	 * null; PreparedStatement ps = null; ResultSet rs = null;
	 * 
	 * String pagSql = getPagSql(sql); List vos = new ArrayList(); try { Session
	 * session = HibernateSession.getSession(); conn = session.connection(); ps =
	 * conn.prepareStatement(pagSql); ps.setInt(1, pageNo * pageSize);
	 * ps.setInt(2, (pageNo - 1) * pageSize); rs = ps.executeQuery(); return rs; }
	 * catch (HibernateException ex) { throw new SysException("9000004",
	 * "queryOracle！HibernateException", ex); } catch (HibernateSessionException
	 * ex) { throw new SysException("9000004",
	 * "queryOracle！HibernateSessionException", ex); }
	 * 
	 * catch (SQLException ex) { throw new SysException("9000004",
	 * "queryOracle！SQLException", ex); } finally { } }
	 */

	public static PagView InitPagViewJDBC(Connection conn, Sql sql,
			PagInfo pagInfo) throws SysException, AppException {
		int count; // 总记录数
		int rowLimit;
		int pageSize = pagInfo.getPagSize(); // 每页显示记录数
		int pageNo = pagInfo.getPagNo(); // 第几页
		if (pageNo < 0)
			pageNo = 1;

		PagView pv = new PagView();

		count = getCountJDBC(conn, sql);
		rowLimit = pagInfo.getRowLimit();
		if (rowLimit == -1) {// 校验查询的记录是否大于系统规定的最大数目 Added by YingHS,May19
								// 2008
			String sysRowLimit = SysConfigData.getSysConfigCurValue(
					SysConstants.SYS_ROW_LIMIT, null, null, null, null, null);
			if (sysRowLimit == null || sysRowLimit.length() == 0) {
				sysRowLimit = "1000000000";
			}
			rowLimit = Integer.parseInt(sysRowLimit);
			if (count > rowLimit) {
				String errId = "00000000";
				String errMsg = "查询的记录数目超过系统限制：" + rowLimit
						+ "条记录，请重新设置条件以缩小查询范围。";

				throw new AppException(errId, errMsg);
			}
		} else if (rowLimit > 0) {
			if (count > rowLimit) {
				String errId = "00000000";
				String errMsg = "查询的记录数目超过系统限制：" + rowLimit
						+ "条记录，请重新设置条件以缩小查询范围。";

				throw new AppException(errId, errMsg);
			}
		} else {

		}
		// LogUtil.debug(log,"rowcount:"+count);
		pv.setCount(count);
		if (count % pageSize == 0) { // 计算页数
			pv.setPagCount(count / pageSize);
		} else {
			pv.setPagCount(count / pageSize + 1);
		}
		if (pageNo < 0) { // 没有初始化默认为第一页
			pageNo = 1;
		}
		pv.setPagSize(pageSize);
		pv.setPagNo(pageNo);

		return pv;
	}

	/**
	 * 关闭游标和连接
	 * 
	 * @param ps
	 *            PreparedStatement
	 * @param rs
	 *            ResultSet
	 * @param conn
	 *            Connection
	 */
	public static void close(PreparedStatement ps, ResultSet rs, Connection conn) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			log.error("游标关闭错误", ex);
		}
	}

	/**
	 * 返回定义的查询记录数。默认为PAGE_NUM
	 * 
	 * @param key
	 *            String
	 * @throws SysException
	 * @return int
	 */
	public static int getPageNum(String key) throws SysException {
		InputStream is = PagUtil.class.getResourceAsStream("/page.properties");
		try {
			PropertyResourceBundle prb = new PropertyResourceBundle(is);
			String str = prb.getString(key);
			if (str != null) {
				Integer it = new Integer(str);
				return it.intValue();
			} else
				// if not config ,return default value.
				return PAGE_NUM;

		} catch (IOException ex) {
			throw new SysException("", "", ex);
		}

	}

	public static void main(String[] args) {
		try {
			System.out.print("SM_SYS_USER" + PagUtil.getPageNum("SM_SYS_USER"));
		} catch (SysException ex) {
		}
	}
}
