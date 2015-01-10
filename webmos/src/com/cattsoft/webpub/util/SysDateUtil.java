package com.cattsoft.webpub.util;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.SysDate;

/**
 * Title: CRM <br>
 * Description: 日期时间辅助类，只能在<b>表现层</b>调用 <br>
 * Date: Jun 23, 2007 <br>
 * Copyright (c) 2007 CATTSoft <br>
 */
public class SysDateUtil {
    private static Logger log = Logger.getLogger(SysDateUtil.class);

    /**
     * 获取当前日期
     * 
     * @return
     */
    public static Date getCurrentDate() throws AppException, SysException {
        return SysDate.getCurrentDate();
    }

    /**
     * 获取当前数据库日期
     * 
     * @return
     * @throws AppException 
     */
    public static Date getCurrentDateFromDB() throws SysException, AppException {
        Date date = null;
        try {
            ConnectionFactory.createConnection();
            date = SysDate.getCurrentDateFromDB();
        } catch (SysException e) {
            if (log.isDebugEnabled()) {
                log.debug(e.getMessage());
            }
            throw new SysException("", e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return date;
    }

    /**
     * 获取当前时间
     * 
     * @return
     */
    public static Timestamp getCurrentTimestamp() throws AppException, SysException {
        return SysDate.getCurrentTimestamp();
    }

    /**
     * 获取当前数据库时间
     * 
     * @return
     * @throws AppException 
     */
    public static Timestamp getCurrentTimestampFromDB() throws SysException, AppException {
        Timestamp time = null;
        try {
            ConnectionFactory.createConnection();
            time = SysDate.getCurrentTimestamp();
        } catch (AppException e) {
            if (log.isDebugEnabled()) {
                log.debug(e.getMessage());
            }
            throw new SysException("", e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return time;
    }
}
