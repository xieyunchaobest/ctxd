package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.ILoginLogMDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.LoginLogMVO;
import com.cattsoft.sm.vo.LoginLogSVO;

public class LoginLogMDAOImpl implements ILoginLogMDAO {

    public PagView findLoginLogsByPage(LoginLogMVO vo, HashSet set, PagInfo pagInfo)
	throws Exception {
List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
LoginLogMVO loginLog = (LoginLogMVO) vo;
Sql sql = new Sql();
sql.append("select");
sql
		.append(" a.LOGIN_ID,a.SYS_USER_ID,a.IP_ADDR,a.SYS_DEVICE_MAC,a.LOGIN_TIME,a.LOGOUT_TIME,a.LOCAL_NET_ID, ");
sql.append(" b.sys_user_name,c.staff_id,d.name  ,");
sql.append("(SELECT LN.NAME FROM LOCAL_NET LN WHERE LN.LOCAL_NET_ID = a.LOCAL_NET_ID) LOCAL_NET ");
sql.append(" from LOGIN_LOG a,sys_user b,staff c,party d where  ");
sql
		.append("  a.sys_user_id=b.sys_user_id and b.party_role_id=c.staff_id and c.party_id=d.party_id ");

if (vo == null && set != null) {
	sql.append("and ( ");
	sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "login_id", "loginId"));
	sql.append(" ) ");
}
if (vo != null && set == null) {

	if (loginLog.getLl().getLoginId() != null) {
		sql.append(" and a.LOGIN_ID=:loginId");
		sql.setString("loginId", loginLog.getLl().getLoginId());
	}
	if (loginLog.getLl().getSysUserId() != null) {
		sql.append(" and a.SYS_USER_ID=:sysUserId");
		sql.setString("sysUserId", loginLog.getLl().getSysUserId());
	}
	if (loginLog.getLl().getIpAddr() != null) {
		sql.append(" and a.IP_ADDR=:ipAddr");
		sql.setString("ipAddr", loginLog.getLl().getIpAddr());
	}
	if (loginLog.getLl().getSysDeviceMac() != null) {
		sql.append(" and a.SYS_DEVICE_MAC=:sysDeviceMac");
		sql.setString("sysDeviceMac", loginLog.getLl().getSysDeviceMac());
	}
	if (loginLog.getLoginTime() != null) {
		// sql.append(" and a.LOGIN_TIME>to_date('" + loginLog.getLoginTime() +
		// "','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" and a.LOGIN_TIME>=:frm_date ");
		sql.setTimestamp("frm_date", DateUtil.to_date(loginLog.getLoginTime(),
				"yyyy-MM-dd HH:mm:ss"));
	}
	if (loginLog.getLoginTime2() != null) {
		// sql.append(" and a.LOGIN_TIME<to_date('" + loginLog.getLoginTime2() +
		// "','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" and a.LOGIN_TIME<=:to_date");
		sql.setTimestamp("to_date", DateUtil.to_date(loginLog.getLoginTime2(),
				"yyyy-MM-dd HH:mm:ss"));
	}
	if (loginLog.getLl().getLoginTime() != null) {
		// sql.append(" and a.LOGIN_TIME="+loginLog.getLl().getLoginTime());
		sql.append(" and a.LOGIN_TIME=:loginTime ");
		sql.setTimestamp("loginTime", loginLog.getLl().getLoginTime());
	}
	if (loginLog.getLogoutTime() != null) {
		// sql.append(" and a.LOGOUT_TIME>to_date('" + loginLog.getLogoutTime() +
		// "','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" and a.LOGOUT_TIME>=:to_date1 ");
		sql.setTimestamp("to_date1", DateUtil.to_date(loginLog.getLogoutTime(),
				"yyyy-MM-dd HH:mm:ss"));
	}
	if (loginLog.getLogoutTime2() != null) {
		// sql.append(" and a.LOGOUT_TIME<=to_date('" + loginLog.getLogoutTime2() +
		// "','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" and a.LOGOUT_TIME<=:to_date2 ");
		sql.setTimestamp("to_date2", DateUtil.to_date(loginLog.getLogoutTime2(),
				"yyyy-MM-dd HH:mm:ss"));
	}
	if (loginLog.getLl().getLogoutTime() != null) {
		sql.append(" and a.LOGOUT_TIME=:logoutTime1 ");
		sql.setTimestamp("logoutTime1", loginLog.getLl().getLogoutTime());
	}
	if (loginLog.getLl().getLocalNetId() != null) {
		sql.append(" and a.LOCAL_NET_ID=:localNetId ");
		sql.setString("localNetId", loginLog.getLl().getLocalNetId());
	}
	if (loginLog.getPartyName() != null) {
		sql.append(" and d.name=:partyName ");
		sql.setString("partyName", loginLog.getPartyName());
	}
	if (loginLog.getSysUserName() != null) {
		sql.append(" and b.sys_user_name=:sysUserName ");
		sql.setString("sysUserName", loginLog.getSysUserName());
	}
	if (loginLog.getStaffId() != null) {
		sql.append(" and c.staff_id=:staffId ");
		sql.setString("staffId", loginLog.getStaffId());
	}
}
sql.append(" order by a.login_time desc");
Connection connection = null;
PreparedStatement ps = null;
ResultSet rs = null;
PagView pagView = null;
try {
	connection = ConnectionFactory.getConnection();
	pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
	sql.log(this.getClass());
	rs = PagUtil.queryOracle(connection, sql, pagInfo);
	while (rs.next()) {
		LoginLogMVO mvo = new LoginLogMVO();
		LoginLogSVO svo = new LoginLogSVO();
		svo.setLoginId(rs.getString("login_id"));
		svo.setIpAddr(rs.getString("ip_addr"));
		svo.setLocalNetId(rs.getString("local_net_id"));
		svo.setSysUserId(rs.getString("sys_user_id"));
		svo.setSysDeviceMac(rs.getString("sys_device_mac"));
		svo.setLoginTime(rs.getTimestamp("login_time"));
		svo.setLogoutTime(rs.getTimestamp("logout_time"));
		mvo.setLl(svo);
		mvo.setSysUserName(rs.getString("sys_user_name"));
		mvo.setPartyName(rs.getString("name"));
		mvo.setStaffId(rs.getString("staff_id"));
		mvo.setLocalNet(rs.getString("LOCAL_NET"));
		results.add(mvo);
	}
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

    /**
     * 查询导出Excel,返回的list里是map
     */
    public List find(LoginLogMVO vo) throws SysException, AppException {
	List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
	LoginLogMVO loginLog = (LoginLogMVO) vo;
	Sql sql = new Sql();
	sql.append("select");
	sql
			.append(" a.LOGIN_ID,a.SYS_USER_ID,a.IP_ADDR,a.SYS_DEVICE_MAC,a.LOGIN_TIME,a.LOGOUT_TIME,a.LOCAL_NET_ID ");
	sql.append(" , b.sys_user_name,d.name,c.staff_id, ");
	sql.append("(SELECT LN.NAME FROM LOCAL_NET LN WHERE LN.LOCAL_NET_ID = a.LOCAL_NET_ID) LOCAL_NET ");
	sql.append(" from LOGIN_LOG a,sys_user b,staff c,party d where 1=1");
	sql
			.append(" and a.sys_user_id=b.sys_user_id and b.party_role_id=c.staff_id and c.party_id=d.party_id ");

	if (loginLog.getLl().getLoginId() != null) {
		sql.append(" and a.LOGIN_ID=" + loginLog.getLl().getLoginId());
	}
	if (loginLog.getLl().getSysUserId() != null) {
		sql.append(" and a.SYS_USER_ID=" + loginLog.getLl().getSysUserId());
	}
	if (loginLog.getLl().getIpAddr() != null) {
		sql.append(" and a.IP_ADDR=" + loginLog.getLl().getIpAddr());
	}
	if (loginLog.getLl().getSysDeviceMac() != null) {
		sql.append(" and a.SYS_DEVICE_MAC=" + loginLog.getLl().getSysDeviceMac());
	}
	if (loginLog.getLoginTime() != null) {
		sql.append(" and a.LOGIN_TIME>to_date('" + loginLog.getLoginTime()
				+ "','yyyy-mm-dd hh24:mi:ss')");
	}
	if (loginLog.getLoginTime2() != null) {
		sql.append(" and a.LOGIN_TIME<to_date('" + loginLog.getLoginTime2()
				+ "','yyyy-mm-dd hh24:mi:ss')");
	}
	if (loginLog.getLl().getLoginTime() != null) {
		sql.append(" and a.LOGIN_TIME=" + loginLog.getLl().getLoginTime());
	}
	if (loginLog.getLogoutTime() != null) {
		sql.append(" and a.LOGOUT_TIME>to_date('" + loginLog.getLogoutTime()
				+ "','yyyy-mm-dd hh24:mi:ss')");
	}
	if (loginLog.getLogoutTime2() != null) {
		sql.append(" and a.LOGOUT_TIME<=to_date('" + loginLog.getLogoutTime2()
				+ "','yyyy-mm-dd hh24:mi:ss')");
	}
	if (loginLog.getLl().getLogoutTime() != null) {
		sql.append(" and a.LOGOUT_TIME=" + loginLog.getLl().getLogoutTime());
	}
	if (loginLog.getLl().getLocalNetId() != null) {
		sql.append(" and a.LOCAL_NET_ID=" + loginLog.getLl().getLocalNetId());
	}
	if (loginLog.getPartyName() != null) {
		sql.append(" and d.name='" + loginLog.getPartyName() + "'");
	}
	if (loginLog.getSysUserName() != null) {
		sql.append(" and b.sys_user_name='" + loginLog.getSysUserName() + "'");
	}
	if (loginLog.getStaffId() != null) {
		sql.append(" and c.staff_id=" + loginLog.getStaffId());
	}
	sql.append(" order by a.login_time desc");
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Map map = null;
	try {
		connection = ConnectionFactory.getConnection();
		ps = connection.prepareStatement(sql.getSql());
		sql.log(this.getClass());
		rs = ps.executeQuery();
		while (rs.next()) {
			map = (Map)CollectionFactory.createMap(CollectionFactory.COLLECTION_MAP);
			map.put("SYS_USER_ID", rs.getString("SYS_USER_ID"));
			map.put("NAME", rs.getString("NAME"));
			map.put("IP_ADDR", rs.getString("IP_ADDR"));
			map.put("SYS_DEVICE_MAC", rs.getString("SYS_DEVICE_MAC"));
			map.put("LOGIN_TIME", rs.getString("LOGIN_TIME"));
			map.put("LOGOUT_TIME", rs.getString("LOGOUT_TIME"));
			map.put("LOCAL_NET", rs.getString("LOCAL_NET"));
			results.add(map);
		}

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
