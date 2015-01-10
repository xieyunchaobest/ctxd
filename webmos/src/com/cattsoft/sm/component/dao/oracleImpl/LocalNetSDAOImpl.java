package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ILocalNetSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.LocalNetSVO;

public class LocalNetSDAOImpl implements ILocalNetSDAO {

	private static Logger log = Logger.getLogger(LocalNetSDAOImpl.class);

	// private static final String UNABLE_STS = "P";

	public void add(GenericVO vo) throws AppException, SysException {
		LocalNetSVO localNet = (LocalNetSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql
				.append(" LOCAL_NET(LOCAL_NET_ID,ABBREV_NAME,NAME,PROV_ID,DIST_NBR,ISCENTER,STS,STS_DATE,CREATE_DATE,JF_LOCAL_NET_ID) values(?,?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, localNet.getLocalNetId());
			ps.setString(2, localNet.getAbbrevName());
			ps.setString(3, localNet.getName());
			ps.setString(4, localNet.getProvId());
			ps.setString(5, localNet.getDistNbr());
			ps.setString(6, localNet.getIscenter());
			ps.setString(7, localNet.getSts());
			ps.setDate(8, (Date) localNet.getStsDate());
			ps.setDate(9, (Date) localNet.getCreateDate());
			ps.setString(10, localNet.getJfLocalNetId());
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
		LocalNetSVO localNet = (LocalNetSVO) vo;
		StringBuffer sql = new StringBuffer("update LOCAL_NET set");
		if (localNet.getAbbrevName() != null) {
			sql.append(" ABBREV_NAME=?,");
		}
		if (localNet.getName() != null) {
			sql.append(" NAME=?,");
		}
		if (localNet.getDistNbr() != null) {
			sql.append(" DIST_NBR=?,");
		}
		if (localNet.getProvId() != null) {
			sql.append(" PROV_ID=?,");
		}
		if (localNet.getIscenter() != null) {
			sql.append(" ISCENTER=?,");
		}
		if (localNet.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (localNet.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (localNet.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		if (localNet.getJfLocalNetId() != null) {
			sql.append(" JF_LOCAL_NET_ID=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and LOCAL_NET_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (localNet.getAbbrevName() != null) {
				ps.setString(index++, localNet.getAbbrevName());
			}
			if (localNet.getName() != null) {
				ps.setString(index++, localNet.getName());
			}
			if (localNet.getDistNbr() != null) {
				ps.setString(index++, localNet.getDistNbr());
			}
			if (localNet.getProvId() != null) {
				ps.setString(index++, localNet.getProvId());
			}
			if (localNet.getIscenter() != null) {
				ps.setString(index++, localNet.getIscenter());
			}
			if (localNet.getSts() != null) {
				ps.setString(index++, localNet.getSts());
			}
			if (localNet.getStsDate() != null) {
				ps.setDate(index++, (Date) localNet.getStsDate());
			}
			if (localNet.getCreateDate() != null) {
				ps.setDate(index++, (Date) localNet.getCreateDate());
			}
			if (localNet.getJfLocalNetId() != null) {
				ps.setString(index++, localNet.getJfLocalNetId());
			}
			ps.setString(index++, localNet.getLocalNetId());
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
		LocalNetSVO localNet = (LocalNetSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("update LOCAL_NET set STS=? ");
		sql.append("where LOCAL_NET_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, Constant.STS_HISTORY);
			ps.setString(2, localNet.getLocalNetId());			
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
		LocalNetSVO result = null;
		LocalNetSVO localNet = (LocalNetSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql
				.append(" a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.DIST_NBR,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE,a.JF_LOCAL_NET_ID ");
		sql.append(" from LOCAL_NET a where 1=1");
		sql.append(" and LOCAL_NET_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, localNet.getLocalNetId());
			rs = ps.executeQuery();
			result = (LocalNetSVO) ResultSetUtil.convertToVo(rs, LocalNetSVO.class);
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
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LocalNetSVO localNet = (LocalNetSVO) vo;
		Sql sql= new Sql("select");		
		sql
				.append(" a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.DIST_NBR,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE,a.JF_LOCAL_NET_ID ");
		sql.append(" from LOCAL_NET a where 1=1");
		try {
		if (localNet.getLocalNetId() != null) {
			sql.append(" and LOCAL_NET_ID=:localNetID");			
			sql.setString("localNetID", localNet.getLocalNetId());
		}
		if (localNet.getAbbrevName() != null) {
			sql.append(" and ABBREV_NAME=:abbName");
			sql.setString("abbName", localNet.getAbbrevName());
		}
		if (localNet.getName() != null) {
			sql.append(" and NAME like '%"+ localNet.getName()+"%'");
		}
		if (localNet.getDistNbr() != null) {
			sql.append(" and DIST_NBR=:distNbr");
			sql.setString("distNbr", localNet.getDistNbr());			
		}
		if (localNet.getIscenter() != null) {
			sql.append(" and ISCENTER=:isCenter");
			sql.setString("isCenter", localNet.getIscenter());
		}
		if (localNet.getSts() != null) {
			sql.append(" and STS=:sts");
			sql.setString("sts", localNet.getSts());
		}
		if(localNet.getSts()== null && localNet.getName()== null){//没设查询条件，则查询结果为null
			sql.append(" and NAME is null ");
			sql.append(" and STS is null ");			
		}
		if (localNet.getStsDate() != null) {
			sql.append(" and STS_DATE=:stsDate");
			sql.setTimestamp("stsDate", localNet.getStsDate());			
		}
		if (localNet.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=:createDate");
			sql.setTimestamp("createDate", localNet.getCreateDate());
		}
		if (localNet.getJfLocalNetId() != null) {
			sql.append(" and JF_LOCAL_NET_ID=:jfNetId");
			sql.setString("jfNetId", localNet.getJfLocalNetId());
		}
		
		conn = ConnectionFactory.getConnection();
		ps = conn.prepareStatement(sql.getSql());
		ps = sql.fillParams(ps);
		sql.log(this.getClass());
		rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, LocalNetSVO.class);
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

	public List findBySet(HashSet set) throws Exception {
		List results = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql
				.append(" a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.DIST_NBR,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE,a.JF_LOCAL_NET_ID");
		sql.append(" from LOCAL_NET a where 1=1 and ( ");
		sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "local_net_id", "localNetId"));
		sql.append(")");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, LocalNetSVO.class);
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

	public List findByVO(GenericVO vo, String sysUserId) throws AppException, SysException {
		if (vo == null || sysUserId == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		LocalNetSVO localNet = (LocalNetSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT a.ABBREV_NAME,a.CREATE_DATE,a.DIST_NBR,a.ISCENTER,a.JF_LOCAL_NET_ID,a.LOCAL_NET_ID,a.NAME,a.PROV_ID,a.STS,a.STS_DATE FROM LOCAL_NET a,user_data_range b ");
		sql.append(" WHERE 1=1 and b.sys_user_id=:sysuserid ");
		sql
				.append(" and a.local_net_id = b.data_range_id and b.range_type_id='L' and b.sts='A' order by a.local_net_id");
		sql.setString("sysuserid", sysUserId);
		try {
			if (!StringUtil.isBlank(localNet.getAbbrevName())) {
				sql.append(" and ABBREV_NAME=:abbrevName");
				sql.setString("abbrevName", localNet.getAbbrevName());
			}

			if (localNet.getCreateDate() != null) {
				sql.append(" and CREATE_DATE=:createDate");
				sql.setTimestamp("createDate", localNet.getCreateDate());
			}

			if (!StringUtil.isBlank(localNet.getDistNbr())) {
				sql.append(" and DIST_NBR=:distNbr");
				sql.setString("distNbr", localNet.getDistNbr());
			}

			if (!StringUtil.isBlank(localNet.getIscenter())) {
				sql.append(" and ISCENTER=:iscenter");
				sql.setString("iscenter", localNet.getIscenter());
			}

			if (!StringUtil.isBlank(localNet.getJfLocalNetId())) {

				sql.append(" and JF_LOCAL_NET_ID=:jfLocalNetId");
				sql.setLong("jfLocalNetId", localNet.getJfLocalNetId());
			}

			if (!StringUtil.isBlank(localNet.getLocalNetId())) {

				sql.append(" and LOCAL_NET_ID=:localNetId");
				sql.setLong("localNetId", localNet.getLocalNetId());
			}

			if (!StringUtil.isBlank(localNet.getName())) {

				sql.append(" and NAME=:name");
				sql.setString("name", localNet.getName());
			}

			if (!StringUtil.isBlank(localNet.getProvId())) {

				sql.append(" and PROV_ID=:provId");
				sql.setLong("provId", localNet.getProvId());
			}

			if (!StringUtil.isBlank(localNet.getSts())) {

				sql.append(" and STS=:sts");
				sql.setString("sts", localNet.getSts());
			}

			if (localNet.getStsDate() != null) {

				sql.append(" and STS_DATE=:stsDate");
				sql.setTimestamp("stsDate", localNet.getStsDate());
			}

			log.debug("根据SYSUSERID 获得本地网的SQL" + sql.getSql());
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				localNet = new LocalNetSVO();
				localNet.setAbbrevName(rs.getString("ABBREV_NAME"));
				localNet.setCreateDate(rs.getDate("CREATE_DATE"));
				localNet.setDistNbr(rs.getString("DIST_NBR"));
				localNet.setIscenter(rs.getString("ISCENTER"));
				localNet.setJfLocalNetId(rs.getString("JF_LOCAL_NET_ID"));
				localNet.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				localNet.setName(rs.getString("NAME"));
				localNet.setProvId(rs.getString("PROV_ID"));
				localNet.setSts(rs.getString("STS"));
				localNet.setStsDate(rs.getDate("STS_DATE"));
				res.add(localNet);

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

}
