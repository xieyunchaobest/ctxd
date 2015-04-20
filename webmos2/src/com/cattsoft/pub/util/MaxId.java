package com.cattsoft.pub.util;

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
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.SysConfigSVO;

/**
 * 
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-6-6 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author liaoyh
 */
public class MaxId {
    private static Log log = LogFactory.getLog(MaxId.class);

    /**
     * getSequenceNextVal:得到下一个Sequence
     * 
     * @param seqName
     * @throws AppException
     * @throws SysException
     * @return String
     */
    public static String getSequenceNextVal(String seqName) throws SysException, AppException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        String seq = "0";
        Sql sql = new Sql();
        sql.append("select " + seqName + "_SEQ.nextval from dual");
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql.getSql());
            sql.fillParams(ps);
            sql.log(MaxId.class);
            rs = ps.executeQuery();
            if (rs.next()) {
                seq = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new SysException("100000", "查找序列" + seqName + "的最大值失败", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.debug("cannot close resource");
            }
        }
        return seq;
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
    public static Long getMaxIdFromTable(String tableName, String columnName, String condition)
            throws SysException, AppException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        Long seq = null;
        Sql sql = new Sql();
        sql.append("select nvl(max(");
        sql.append(columnName);
        sql.append("),0) from ");
        sql.append(tableName);

        if (!StringUtil.isBlank(condition)) {
            sql.append(" where ");
            sql.append(condition);
        }
        System.out.println("sql is >>>>> " + sql.getSql());
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql.getSql());
            sql.log(MaxId.class);
            rs = ps.executeQuery();
            if (rs.next()) {
                seq = new Long(rs.getLong(1));
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
        return seq;
    }

    /**
     * getMaxSeqFromTable:从某表中根据vo。得到seq最大值
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
    public static Long getMaxSeqFromTable(String tableName, String columnName, String condition)
            throws SysException, AppException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        Long seq = null;
        Sql sql = new Sql();
        sql.append("select nvl(max(");
        sql.append(columnName);
        sql.append("),0) from ");
        sql.append(tableName);

        if (!StringUtil.isBlank(condition)) {
            sql.append(" where ");
            sql.append(condition);
        }
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql.getSql());
            sql.log(MaxId.class);
            rs = ps.executeQuery();
            if (rs.next()) {
                seq = new Long(rs.getLong(1));
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
        return seq;
    }

    /**
     * @author wangdongxun getMaxSeqByServId:营业servId读取serv系列表的大seq,当前最大的seq+1
     * 
     * @param tableName
     *            String
     * @param columnName
     *            String
     * @param servId
     *            String
     * @throws AppException
     * @throws SysException
     * @throws NamingException
     * @return String
     * @throws AppException
     */
    public static String getMaxSeqByServId(String tableName, String columnName, String servId)
            throws SysException, AppException {
        String seqTmp = getCurrSeqByServId(tableName, columnName, servId);
        int seq = Integer.parseInt(seqTmp) + 1;
        return new Integer(seq).toString();
    }

    /**
     * @author wangdongxun getCurrSeqByServId:营业servId读取serv系列表的大seq,当前最大的seq
     * 
     * @param tableName
     *            String
     * @param columnName
     *            String
     * @param servId
     *            String
     * @throws AppException
     * @throws SysException
     * @throws NamingException
     * @return Stirng
     * @throws AppException
     */

    public static String getCurrSeqByServId(String tableName, String columnName, String servId)
            throws SysException, AppException {
        Long seq = MaxId.getMaxSeqFromTable(tableName, columnName, "serv_id=" + servId);
        return seq.toString();
    }
    /**
     * 得到客户或营销区域Id
     * @param jfLocalNetId
     * @param seqName
     * @return
     * @throws SysException
     * @throws AppException
     */
    public static String getNewMaxId(String localNetId,String seqName)throws SysException, AppException {
        String id = ""; 
        String  seq=getSequenceNextVal(seqName);
        SysConfigSVO sysVo = new SysConfigSVO();
        try {
            sysVo = SysConfigData.getSysConfigById( "100019",  localNetId , null, null, null, null );
            id = ( new Long( sysVo.getCurValue() ).longValue() * 1000000000 )+Long.parseLong(seq)+"";
             // id = new Long( ( new Long( sysVo.getCurValue() ).longValue() * new Long("10000000000").longValue() ) + new Long( seq ).longValue() ).toString();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        } catch (DataCacheException e) {
            e.printStackTrace();
            return null;
        }
        //Partition_id*1000000000+CRM的流水
        
        return id;
    }
        
     /**
     * <p>
     * 使用方法示例1:getMaxId,取最大值(一般情况下都用这个方法)
     * </p>
     * <p>
     * 说明:
     * </p>
     * <p>
     * 如果是oracle请确认已经建好Sequence
     * </p>
     * <p>
     * 如果是Sybase请确认在MAX_IDS表中已经有相关数据
     * </p>
     * <p>
     * long commServSpecId ;
     * </p>
     * <p>
     * MaxId maxId = new MaxId() ;
     * </p>
     * <p>
     * commServSpecId = maxId.getMaxId("comm_serv_spec_id");
     * </p>
     * <p>
     * .......
     * </p>
     * 
     * 
     * 
     * <p>
     * 使用方法示例2:getMaxAreaId,取最大值(工单打印时按照区域取最大值)
     * </p>
     * <p>
     * 说明:
     * </p>
     * <p>
     * 请确认在MAX_IDS表中已经有相关数据
     * </p>
     * 
     * <p>
     * long printNbr ;
     * </p>
     * <p>
     * MaxId maxId = new MaxId() ;
     * </p>
     * <p>
     * printNbr = maxId.getMaxAreaId("PRINT_NBR","10501");
     * </p>
     * 
     * 
     * 
     * <p>
     * 使用方法示例2:getMaxIdFromTable,从某表中根据条件得到指定字段的最大值
     * </p>
     * 
     * <p>
     * long prodId ;
     * </p>
     * <p>
     * MaxId maxId = new MaxId() ;
     * </p>
     * <p>
     * prodId = maxId.getMaxIdFromTable("product","prod_id","sts = 'A'");
     * </p>
     * <p>
     * .......
     * </p>
     * 
     * 
     */
}
