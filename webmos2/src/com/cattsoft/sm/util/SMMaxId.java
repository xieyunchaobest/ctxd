package com.cattsoft.sm.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.StringUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-15 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class SMMaxId {

    private static Log log = LogFactory.getLog(SMMaxId.class);
    

    private static boolean bol = true;

    public static String getStaffMaxId(String localNetId, String areaId) throws AppException,
    SysException {

    	String maxId = null;
    	if (bol == false) {
    		bol = true;
    	}
    	Sql sql = new Sql();
    	sql
    	.append("select max(substr(staff_id,6,10)) from staff a,party b where a.party_id=b.party_id and length(staff_id)=10 ");
    	if (localNetId != null) {
    		sql.append("and b.local_net_id=?");
    	}
    	if (areaId != null) {
    		//sql.append(" and b.area_id=?");
    		if(areaId .equals("0")){
    			sql.append(" and substr(staff_id,4,2) = '00'");
    		}else{
    			sql.append(" and substr(staff_id,0,5) = "+areaId);
    		}
    	}

    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		ps = getConnection().prepareStatement(sql.getPartitionSql(null));
    		int index = 1;
    		if (localNetId != null) {
    			sql.setString(index++, localNetId);
    		}
/*    		if (areaId != null) {
    			sql.setString(index++, areaId);
    		}*/
    		sql.fillParams(ps);
    		sql.logPartition(null, SMMaxId.class);
    		rs = ps.executeQuery();
    		if (rs.next()) {
    			maxId = rs.getString(1);
    		}
    	} catch (Exception e) {
    		throw new SysException("222", "findStaffWorkArea", e);
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
    	closeConnection();
    	if (maxId == null) {
    		if (areaId.equals("0")) {
    			maxId = localNetId + "00" + "00000";
    		} else {
    			maxId = areaId + "00000";
    		}
    	} else
    	{
    		if(!areaId.equals("0"))
    			maxId = areaId + maxId;
    		else
    			maxId = localNetId + "00" + maxId;
    	}
    	if (log.isDebugEnabled())
    		log.debug("[得到员工最大编号][maxId=" + maxId + "]");
    	return String.valueOf(Long.parseLong(maxId) + 1);
    }

    public static String getWorkAreaMaxId(String localNetId, String areaId) throws SQLException,
            SysException, AppException {

        long maxId = 0;
        if (bol == false) {
            bol = true;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("1=1 ");
        if (localNetId != null) {
            sql.append("and local_net_id=" + localNetId);
        }
        if (areaId != null) {
            sql.append(" and area_id=" + areaId);
        }
        maxId = getMaxIdFromTable("work_area", "work_area_id", sql.toString());
        if (maxId == 0) {
            if (areaId.equals("0")) {
                maxId = Long.parseLong(localNetId + "00" + "0000"); // 数据库有脏数据,所以暂时先从2开始加
            } else {
                maxId = Long.parseLong(areaId + "0000");
            }
        }
        log.debug("maxId=" + maxId);
        return String.valueOf(maxId + 1);
    }

    public static String getNextSeqByPartyHist(String partyId) throws SQLException, SysException,
            AppException {
        long maxId = 0;
        if (bol == false) {
            bol = true;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("1=1 and party_id= " + partyId);
        maxId = getMaxIdFromTable("party_hist", "seq", sql.toString());

        return String.valueOf(maxId + 1);

    }

    public static String getOrgDeptMaxId(String localNetId) throws SQLException, SysException,
            AppException {

        long maxId = 0;
        if (bol == false) {
            bol = true;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("1=1 ");
        if (localNetId != null) {
            sql.append("and local_net_id=" + localNetId);
        }

        maxId = getMaxIdFromTable("org_dept", "dept_id", sql.toString());
        if (maxId == 0) {
            if (localNetId.equals("0")) {
                maxId = Long.parseLong("1300000000");
            } else
                maxId = Long.parseLong("13" + localNetId + "00000");
        }
        log.debug("maxId=" + maxId);
        return String.valueOf(maxId + 1);
    }

    public static String getAreaMaxId(String localNetId) throws SQLException, SysException,
            AppException {

        long maxId = 0;
        if (bol == false) {
            bol = true;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("1=1 ");
        if (localNetId != null) {
            sql.append("and local_net_id=" + localNetId);
        }

        maxId = getMaxIdFromTable("area", "area_id", sql.toString());
        if (maxId == 0) {
            maxId = Long.parseLong(localNetId + "00");
        }
        log.debug("maxId=" + maxId);
        return String.valueOf(maxId + 1);
    }

    public static String getServDeptMaxId(String localNetId, String areaId) throws SQLException,
            SysException, AppException {

        long maxId = 0;
        if (bol == false) {
            bol = true;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("1=1 ");
        if (localNetId != null) {
            sql.append("and local_net_id= " + localNetId);
        }
        if (areaId != null) {
            sql.append("and area_id= " + areaId);
        }
        maxId = getMaxIdFromTable("serv_dept", "serv_dept_id", sql.toString());
        if (maxId == 0) {
            if (areaId.equals("0")) {
                maxId = Long.parseLong(localNetId + "0000");
            } else
                maxId = Long.parseLong(areaId + "000");
        }
        log.debug("maxId=" + maxId);
        return String.valueOf(maxId + 1);
    }

    public static String getBranchMaxId(String localNetId, String areaId, String servDeptId)
            throws SQLException, SysException, AppException {

        long maxId = 0;
        if (bol == false) {
            bol = true;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("1=1 ");
        if (localNetId != null) {
            sql.append("and local_net_id=" + localNetId);
        }
        if (areaId != null) {
            sql.append("and area_id=" + areaId);
        }
        if (servDeptId != null) {
            sql.append("and serv_dept_id=" + servDeptId);
        }
        maxId = getMaxIdFromTable("branch", "branch_id", sql.toString());
        if (maxId == 0) {
            if (servDeptId.equals("0")) {
                maxId = Long.parseLong(areaId + "00000");
            } else
                maxId = Long.parseLong(servDeptId + "000");
        }
        log.debug("maxId=" + maxId);
        return String.valueOf(maxId + 1);
    }

    public static String getExchMaxId(String localNetId) throws SQLException, SysException,
            AppException {

        long maxId = 0;
        if (bol == false) {
            bol = true;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("1=1 ");
        if (localNetId != null) {
            sql.append("and local_net_id=" + localNetId);
        }
        maxId = getMaxIdFromTable("exch", "exch_id", sql.toString());
        if (maxId == 0) {
            maxId = Long.parseLong(localNetId + "000");
        }
        log.debug("maxId=" + maxId);
        return String.valueOf(maxId + 1);
    }

    public static String getSysAreaConfigMaxId(String configId) throws SQLException, SysException,
            AppException {

        long maxId = 0;
        if (bol == false) {
            bol = true;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("1=1 ");

        sql.append("and config_id=" + configId);

        maxId = getMaxIdFromTable("sys_area_config", "sys_area_config_id", sql.toString());
        if (maxId == 0) {
            maxId = Long.parseLong(configId + "00");
        }
        log.debug("maxId=" + maxId);
        return String.valueOf(maxId + 1);
    }

    private static Connection getConnection() throws AppException, SysException {
        Connection connection = ConnectionFactory.getConnection();
        if (connection == null) {
            bol = false;
            ConnectionFactory.createConnection();
            connection = ConnectionFactory.getConnection();

        }
        return connection;
    }

    private static void closeConnection() throws AppException, SysException {
        if (bol == false) {
            ConnectionFactory.closeConnection();
        }
    }

    public static String change(String str, int n, boolean isLeft) {
        if (str == null || str.length() >= n)
            return str;
        String s = "";
        for (int i = str.length(); i < n; i++)
            s += "0";
        if (isLeft)
            return s + str;
        else
            return str + s;
    }

    /**
     * getMaxIdFromTable:从某表中根据条件得到指定字段的最大值
     * 
     * @param tableName
     *            String
     * @param columnName
     *            String
     * @param condition
     *            String
     * @throws AppException
     * @throws SysException
     * @throws NamingException
     * @return long
     * @throws AppException
     */
    public static long getMaxIdFromTable(String tableName, String columnName, String condition)
            throws SysException, AppException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        long seq = 0;
        Sql sql = new Sql();
        sql.append("select nvl(max(");
        sql.append("to_number(");
        sql.append(columnName);
        sql.append(")),0) from ");
        sql.append(tableName);

        if (!StringUtil.isBlank(condition)) {
            sql.append(" where ");
            sql.append(condition);
        }
        try {
            ps = getConnection().prepareStatement(sql.getSql());
            log.debug(sql.toString());
            sql.log(SMMaxId.class);
            rs = ps.executeQuery();
            if (rs.next()) {
                seq = rs.getLong(1);
                // //System.out.println("max_id is :" + seq);
            }
        } catch (SQLException ex) {
            throw new SysException("", "查找" + tableName + "表字段" + columnName + "的最大值失败" + "sql is "
                    + sql, ex);
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
        closeConnection();
        return seq;

    }

    public static java.sql.Timestamp getSysDate() throws SQLException, SysException, AppException {

        if (bol == false) {
            bol = true;
        }
        java.sql.Timestamp date = null;
        String sql = "select sysdate from dual";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                date = rs.getTimestamp(1);

            }
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug(e.getMessage());
            }
            throw new SysException("", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }
        return date;
    }
}
