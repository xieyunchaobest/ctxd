package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
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
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IAreaSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.AreaMVO;
import com.cattsoft.sm.vo.AreaSVO;
import com.cattsoft.sm.vo.LocalNetSVO;

public class AreaSDAOImpl implements IAreaSDAO {

	private static Logger log = Logger.getLogger(AreaSDAOImpl.class);

	// private static final String UNABLE_STS = "P";

	public void add(GenericVO vo) throws AppException, SysException {
		AreaSVO area = (AreaSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql
				.append(" AREA(AREA_ID,LOCAL_NET_ID,ABBREV_NAME,NAME,ISCENTER,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, area.getAreaId());
			ps.setString(2, area.getLocalNetId());
			ps.setString(3, area.getAbbrevName());
			ps.setString(4, area.getName());
			ps.setString(5, area.getIscenter());
			ps.setString(6, area.getSts());
			ps.setDate(7, area.getStsDate());
			ps.setDate(8, area.getCreateDate());
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
		AreaSVO area = (AreaSVO) vo;
		StringBuffer sql = new StringBuffer("update AREA set");
		if (area.getLocalNetId() != null) {
			sql.append(" LOCAL_NET_ID=?,");
		}
		if (area.getAbbrevName() != null) {
			sql.append(" ABBREV_NAME=?,");
		}
		if (area.getName() != null) {
			sql.append(" NAME=?,");
		}
		if (area.getIscenter() != null) {
			sql.append(" ISCENTER=?,");
		}
		if (area.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (area.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (area.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and AREA_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (area.getLocalNetId() != null) {
				ps.setString(index++, area.getLocalNetId());
			}
			if (area.getAbbrevName() != null) {
				ps.setString(index++, area.getAbbrevName());
			}
			if (area.getName() != null) {
				ps.setString(index++, area.getName());
			}
			if (area.getIscenter() != null) {
				ps.setString(index++, area.getIscenter());
			}
			if (area.getSts() != null) {
				ps.setString(index++, area.getSts());
			}
			if (area.getStsDate() != null) {
				ps.setDate(index++, area.getStsDate());
			}
			if (area.getCreateDate() != null) {
				ps.setDate(index++, area.getCreateDate());
			}
			ps.setString(index++, area.getAreaId());
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
		AreaSVO area = (AreaSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("update AREA set STS=? ");
		sql.append(" where AREA_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, Constant.STS_HISTORY);
			ps.setString(2, area.getAreaId());
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
		AreaSVO result = null;
		AreaSVO area = (AreaSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql
				.append(" a.AREA_ID,a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from AREA a where 1=1");
		sql.append(" and AREA_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, area.getAreaId());
			rs = ps.executeQuery();
			result = (AreaSVO) ResultSetUtil.convertToVo(rs, AreaSVO.class);
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
		AreaSVO area = (AreaSVO) vo;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql= new Sql("select");			
		sql
				.append(" a.AREA_ID,a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from AREA a where 1=1");
		try {
		if (area.getAreaId() != null) {			
			sql.append(" and AREA_ID=:areaID");			
			sql.setString("areaID", area.getAreaId());
		}
		if (area.getLocalNetId() != null) {			
			sql.append(" and LOCAL_NET_ID=:localNetID");			
			sql.setString("localNetID", area.getLocalNetId());
		}		
		if (area.getName() != null) {
			sql.append(" and NAME like '%"+ area.getName()+"%'");
		}
		if (area.getIscenter() != null) {
			sql.append(" and ISCENTER=:isCenter");
			sql.setString("isCenter", area.getIscenter());
		}
		if (area.getSts() != null) {
			sql.append(" and STS=:sts");
			sql.setString("sts", area.getSts());
		}
		/**
		if(area.getSts()== null && area.getLocalNetId()==null && area.getName()== null){//没设查询条件，则查询结果为null
			sql.append(" and NAME is null ");
			sql.append(" and LOCAL_NET_ID is null ");
			sql.append(" and STS is null ");			
		}
		*/
		if (area.getStsDate() != null) {
			sql.append(" and STS_DATE=:stsDate");
			sql.setTimestamp("stsDate", area.getStsDate());		
		}
		if (area.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=:createDate");
			sql.setTimestamp("createDate", area.getCreateDate());
		}
		conn = ConnectionFactory.getConnection();
		ps = conn.prepareStatement(sql.getSql());
		ps = sql.fillParams(ps);
		sql.log(this.getClass());
		rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, AreaSVO.class);
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

	public PagView findAreasByPage(AreaSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
		List results = null;
		AreaSVO area = (AreaSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql
				.append(" a.AREA_ID,a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE,b.name local_net_name,b.iscenter local_net_iscenter ");
		sql.append(" from local_net b,AREA a where 1=1 and a.local_net_id=b.local_net_id ");

		if (vo == null && set != null) {
			sql.append("and ( ");
			sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "area_id", "areaId"));
			sql.append(" ) ");
		}
		if (vo != null && set == null) {
			if (area.getAreaId() != null) {
				sql.append(" and a.AREA_ID=" + area.getAreaId());
			}
			if (area.getLocalNetId() != null) {
				sql.append(" and a.LOCAL_NET_ID=" + area.getLocalNetId());
			}
			if (area.getAbbrevName() != null) {
				sql.append(" and a.ABBREV_NAME=" + area.getAbbrevName());
			}
			if (area.getName() != null) {
				sql.append(" and a.NAME=" + area.getName());
			}
			if (area.getIscenter() != null) {
				sql.append(" and a.ISCENTER=" + area.getIscenter());
			}
			if (area.getSts() != null) {
				sql.append(" and a.STS='" + area.getSts() + "'");
			}
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PagView pagView = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			Sql sql2 = new Sql();
			sql2.setSql(sql.toString());
			sql2.log(AreaSDAOImpl.class);
			pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql2, pagInfo);
			results = (List) ResultSetUtil.convertToList(rs, AreaMVO.class);
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

	public List findByVO(GenericVO vo, String staffId, String localnetId) throws AppException,
			SysException {
		if (vo == null || staffId == null || localnetId == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		AreaSVO area = (AreaSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT a.ABBREV_NAME,a.AREA_ID,a.CREATE_DATE,a.ISCENTER,a.LOCAL_NET_ID,a.NAME,a.STS,a.STS_DATE  ");
		sql.append(" FROM area a,user_data_range b");
		sql
				.append(" WHERE 1 = 1 and b.sys_user_id=:staffId and a.area_id = b.data_range_id  and a.local_net_id=:localnetId and b.range_type_id='A' and b.sts='A' order by a.area_id ");
		sql.setString("staffId", staffId);
		sql.setString("localnetId", localnetId);

		try {

			if (!StringUtil.isBlank(area.getAbbrevName())) {

				sql.append(" and ABBREV_NAME=:abbrevName");
				sql.setString("abbrevName", area.getAbbrevName());
			}

			if (!StringUtil.isBlank(area.getAreaId())) {

				sql.append(" and AREA_ID=:areaId");
				sql.setLong("areaId", area.getAreaId());
			}

			if (area.getCreateDate() != null) {

				sql.append(" and CREATE_DATE=:createDate");
				sql.setTimestamp("createDate", area.getCreateDate());
			}

			if (!StringUtil.isBlank(area.getIscenter())) {

				sql.append(" and ISCENTER=:iscenter");
				sql.setString("iscenter", area.getIscenter());
			}

			if (!StringUtil.isBlank(area.getLocalNetId())) {

				sql.append(" and LOCAL_NET_ID=:localNetId");
				sql.setLong("localNetId", area.getLocalNetId());
			}

			if (!StringUtil.isBlank(area.getName())) {

				sql.append(" and NAME=:name");
				sql.setString("name", area.getName());
			}

			if (!StringUtil.isBlank(area.getSts())) {

				sql.append(" and STS=:sts");
				sql.setString("sts", area.getSts());
			}

			if (area.getStsDate() != null) {
				sql.append(" and STS_DATE=:stsDate");
				sql.setTimestamp("stsDate", area.getStsDate());
			}

			log.debug("根据SYSUSERID,LOCALNETID,获得服务区的SQL" + sql.getSql());
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				area = new AreaSVO();
				area.setAbbrevName(rs.getString("ABBREV_NAME"));
				area.setAreaId(rs.getString("AREA_ID"));
				area.setCreateDate(rs.getDate("CREATE_DATE"));
				area.setIscenter(rs.getString("ISCENTER"));
				area.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				area.setName(rs.getString("NAME"));
				area.setSts(rs.getString("STS"));
				area.setStsDate(rs.getDate("STS_DATE"));
				res.add(area);

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
