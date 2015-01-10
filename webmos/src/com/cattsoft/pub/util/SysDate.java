package com.cattsoft.pub.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException; 

/**
 * Title: CRM <br>
 * Description: 日期时间辅助类 <br>
 * Date: Jun 23, 2007 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author wangcl
 */
public class SysDate {

    private static Logger log = Logger.getLogger(SysDate.class);

    private static Long timeInterval = null; // 数据库时间和应用服务器时间差

    static {
        if (timeInterval == null) {
            try {
                Timestamp time = SysDate.getCurrentTimestampFromDB();
                java.util.Date date = new java.util.Date();
                timeInterval = new Long(time.getTime() - date.getTime());
            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    log.debug(e.getMessage());
                }
                timeInterval = new Long(0);
            }
        }
    }
    
    
    

    /**
     * 获取当前日期
     * 
     * @return
     */
    public static java.sql.Date getCurrentDate() {
        return new java.sql.Date(new java.util.Date().getTime() + timeInterval.longValue());
    }

    /**
     * 获取当前数据库日期，只能在<b>DAO</b>或<b>DOMAIN</b>层调用
     * 
     * @return
     * @throws AppException 
     */
    public static java.sql.Date getCurrentDateFromDB() throws SysException, AppException {
        return (java.sql.Date) getCurrentOracleSysDate(false);
    }

    /**
     * 获取当前时间
     * 
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        return new java.sql.Timestamp(new java.util.Date().getTime() + timeInterval.longValue());
    }

    /**
     * 获取当前数据库时间，只能在<b>DAO</b>或<b>DOMAIN</b>层调用
     * 
     * @return
     * @throws AppException 
     */
    public static Timestamp getCurrentTimestampFromDB() throws SysException, AppException {
        return (java.sql.Timestamp) getCurrentOracleSysDate(true);
    }

    /**
     * 取得系统当前时间的年
     * 
     * @return String
     */
    public static String getSysDateYear() {
        Date date = getCurrentDate();
        return getSysDateYear(date);
    }

    /**
     * 取得指定时间的年
     * 
     * @param date
     * @return String
     */
    public static String getSysDateYear(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        String year = String.valueOf(cd.get(Calendar.YEAR));
        return year;
    }

    /**
     * 取得系统当前时间的月
     * 
     * @return String
     */

    public static String getSysDateMonth() {
        Date date = getCurrentDate();
        return getSysDateMonth(date);
    }

    /**
     * 取得指定时间的月
     * 
     * @param date
     * @return String
     */
    public static String getSysDateMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        String month = String.valueOf(cd.get(Calendar.MONTH) + 1);
        if (month.length() == 1)
            month = "0" + month;
        return month;
    }

    private static java.util.Date getCurrentOracleSysDate(boolean showTime) throws SysException, AppException {
        java.util.Date date = null;
        String sql = "select sysdate from dual";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (showTime) {
                    date = rs.getTimestamp(1);
                } else {
                    date = rs.getDate(1);
                }
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
