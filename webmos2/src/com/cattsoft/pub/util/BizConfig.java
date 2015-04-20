package com.cattsoft.pub.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;




import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.StatusSVO;

public class BizConfig {
	private static Map map = new HashMap();
	/**
     * 根据表名、列名取得该列的所有类型对象。
     * 
     * @param tabName
     * @param colName
     * @return
     * @throws DataCacheException
     */
    public static List getStatusSVOLst(String tabName, String colName) throws DataCacheException {
        if (tabName == null || colName == null) {
            return null;
        }
        List voList = new ArrayList();
        try {
            HashMap tableMap = (HashMap) DataCache.getCache("status_" + tabName);
            if (tableMap != null) {
                HashMap columnMap = (HashMap) tableMap.get(colName);
                if (columnMap != null) {
                    Iterator ite = columnMap.keySet().iterator();
                    Object key, value;
                    int orderId = 1;
                    while (ite.hasNext()) {
                        StatusSVO vo = new StatusSVO();
                        vo.setColumnName(colName);
                        vo.setTableName(tabName);
                        vo.setOrderId(String.valueOf(orderId));
                        key = ite.next();
                        value = columnMap.get(key);
                        vo.setStsId(key.toString());
                        vo.setStsWords(value.toString());
                        voList.add(vo);
                        orderId++;
                    }
                }
            }
        } catch (Exception ex) {
            throw new DataCacheException(ex);
        }
        return voList;
    }

    /**
     * 根据表名、列名取得该列的所有类型对象。
     * 
     * @param tabName
     * @param colName
     * @return
     * @throws DataCacheException
     */
    public static Map getStatusHashMap(String tabName, String colName)
            throws DataCacheException {
        if (tabName == null || colName == null) {
            return null;
        }
        Map result = new LinkedHashMap();
        try {
            Map tableMap = (LinkedHashMap) DataCache.getCache("status_" + tabName);
            if (tableMap != null) {
                result = (LinkedHashMap) tableMap.get(colName);
            }
        } catch (Exception ex) {
            throw new DataCacheException(ex);
        }
        return result;
    }
    
   /**
    *<p>Description:根据表名、列名、状态ID取得对应的状态SVO信息</p>
    * @author 黄永欣 2008-4-8
    * @param tabName
    * @param colName
    * @param stsId
    * @return
    * @throws DataCacheException
    */
    public static StatusSVO getStatusSVO(String tabName, String colName,String stsId)
            throws DataCacheException {
        Map result = getStatusHashMap(tabName,colName);
        if(result==null||result.size()<1){
        	return null;
        }
        StatusSVO statusSVO=new StatusSVO();
        statusSVO.setTableName(tabName);
        statusSVO.setColumnName(colName);
        statusSVO.setStsId(stsId);
        statusSVO.setStsWords((String) result.get(stsId));
        return statusSVO;
    }
    /**
     * 根据ID得到相应的名
     * @author xd 2008-4-30
     * @param tableName String
     * @param objectId   String
     * @param IdName String 列名 eg: LOCAL_NET_ID
     * @return String
     * @throws AppException
     * @throws SysException
     */
    public static String getNameByobjectId(String tableName,String objectId,String IdName)throws AppException, SysException{
    	String name = "";
        name = (String)map.get(objectId);
		if (name == null || name.equals("")){
    		map = getNameByMap(tableName,objectId,IdName);
    		name = (String)map.get(objectId);
		}
    	return name;
    }
    public static Map getNameByMap(String tableName,String objectId,String IdName)throws AppException, SysException{
    	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		Sql sql = new Sql(); 
    	sql.append("SELECT A.NAME FROM "+tableName+" A where 1=1 ");
		if (objectId == null) {
			sql.append(" and "+ IdName+" is null ");
		} else {
			sql.append(" and "+IdName+"=:areaId");
			sql.setString("areaId",objectId);
		}
		try{
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("NAME");
			}
			map.put(objectId, name);
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return map;
    }
    /**
     * 根据ID得到ACTION_LOG相应的名
     * @author 
     * @param objectId   String
     * @return String
     * @throws AppException
     * @throws SysException
     */
    public static String getActionMessageById(String objectId)throws AppException, SysException{
    	
    	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String resultName = "";
		String funcNodeName = "";
		String actionType = "";
		String username = "";
		Sql sql = new Sql(); 
    	sql.append("SELECT A.ACTION_TYPE,B.FUNC_NODE_NAME,C.SYS_USER_NAME FROM ACTION_LOG A,FUNC_NODE B,SYS_USER C where 1=1 ");
    	sql.append(" and A.LOGIN_ID=C.SYS_USER_ID and A.ACTION_MODULE=B.FUNC_NODE_ID ");
		if (objectId == null) {
			sql.append(" and ACTION_ID is null ");
		} else {
			sql.append(" and ACTION_ID=:actionId");
			sql.setLong("actionId",objectId);
		}
		try{
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				username = rs.getString("SYS_USER_NAME");
				funcNodeName = rs.getString("FUNC_NODE_NAME");
				actionType = rs.getString("ACTION_TYPE");
			}
			if (!username.equals("")){
				StatusSVO vo = getStatusSVO("ACTION_LOG","ACTION_TYPE",actionType);
				resultName = "用户: "+username+" 功能点: "+funcNodeName+" 操作: "+vo.getStsWords();
			}
			
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} catch (DataCacheException de) {
			de.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return resultName;
    }
    
    /**
     *<p>Description:根据表名、列名、状态ID取得对应的状态SVO信息</p>
     * @param tabName
     * @param colName
     * @param stsId
     * @return
     */
    public static StatusSVO getStatusMVO(String tabName, String colName,String stsId){
    	StatusSVO statusSVO=new StatusSVO();
    	Map result = null;
    	try{
    		result = getStatusHashMap(tabName,colName);
			if(result==null||result.size()<1){
				return null;
			}
			statusSVO.setTableName(tabName);
			statusSVO.setColumnName(colName);
			statusSVO.setStsId(stsId);
			statusSVO.setStsWords((String) result.get(stsId));
    	}catch(DataCacheException e){
    		statusSVO.setTableName(tabName);
			statusSVO.setColumnName(colName);
			statusSVO.setStsId(stsId);
			statusSVO.setStsWords("系统未纪录");
    	}
		return statusSVO;
	}
}
