package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.sm.component.dao.IDataRangeItemMDAO;
import com.cattsoft.sm.vo.DataRangeItemSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-14 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */
public class DataRangeItemMDAOImpl implements IDataRangeItemMDAO {
	
    
    private static Logger log = Logger.getLogger(DataRangeItemMDAOImpl.class);
    
    public List findDataRangeItem(String rangeId) throws AppException, SysException {
        List results = null;
        //DataRangeItemSVO dataRangeItem = new DataRangeItemSVO();
        log.debug("rangeId:" + rangeId );
        StringBuffer sql = new StringBuffer();
        sql.append("select  ");
        sql.append(" a.DATA_RANGE_ITEM_ID,a.RANGE_ID,a.DATA_RANGE_TYPE_ID,a.FIELD_RANGE_VALUE,a.SEQ,a.LEFT_BRACKET,a.RIGHT_BRACKET,a.OPERATOR,a.LOGICAL,a.CREATE_DATE,a.STS,a.STS_DATE");
        sql.append(" from DATA_RANGE_ITEM a where ");
        if (rangeId != null) {
            sql.append(" a.RANGE_ID=?");
        }
        log.debug(" sql.toString()" + sql.toString() );
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            if (rangeId != null) {
                ps.setString(1, rangeId);
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, DataRangeItemSVO.class);
            log.debug("results:" + results );
        } catch (Exception e) {
            e.printStackTrace();
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
        
       return  results;
    }    
    

}
