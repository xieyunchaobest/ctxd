package com.cattsoft.pub.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.config.SysConfig;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.DataCacheConfigSVO;
import com.cattsoft.sm.vo.StatusSVO;
import com.cattsoft.sm.vo.SysAreaConfigSVO;
import com.cattsoft.sm.vo.SysConfigSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-20 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author caoyunliang
 */
/*
 * 方法1，将HashMap或TreeMap换成LinkedHashMap，LinkedHashMap按照put的顺序进行访问 。性能会比HashMap稍低
 * 方法2，TreeMap具有构造函数TreeMap(Comparator),按照作为key的对象的指定属性排序。
 */
public class DataCache {
	private static Log log = LogFactory.getLog(DataCache.class);

	public DataCache() {
	}

	private static HashMap allHashMaps = new HashMap();

	private static Class clazz = DataCache.class;

	/* 用来缓存的HashMap变量名 */
	public static final String AREA = "area";

	public static final String SYS_CONFIG = "sysConfig";

	public static final String REGION = "region";

	public static final String PROD_SPEC_CAT = "prodSpecCat";

	public static final String PROD_CAT = "prodCat";

	public static final String PROD_CAT_T = "prodCat_t";

	/* 服务开通的产品对应到crm的通讯服务 */
	public static final String COMM_SERV_SPEC = "prod";
	public static final String SUB_SERV_SPEC = "subprod";
	/* 产品树 */
	public static final String PRODUCT_T = "prod_t";

	/* 服务开通的客户服务对应crm的服务动作 */
	public static final String SERV_ACT = "changeServSpec";

	/* 本地网 */
	public static final String LOCAL_NET = "localNet";

	/* 本地网树 */
	public static final String LOCAL_NET_T = "localNet_t";

	/* 工区 */
	public static final String WORK_AREA = "workArea";

	public static final String EXCH = "exch";

	public static final String STEP = "step";

	public static final String STATUS = "status";

	public static final String GUOUP_TYPE = "groupType";

	public static final String WORK_TYPE = "workType";

	public static final String SYS_AREA_CONFIG = "sysAreaConfig";

	public static String STATUS_SO_PAUSE = "status_SO_PAUSE";// 从status表中取

	public static String STATUS_SO_ACC_NBR = "status_SO_ACC_NBR";

	public static String STATUS_BUSINESS = "status_BUSINESS";

	public static String STATUS_SO = "status_SO";

	public static String STEP_TYPE_T = "stepType_t"; // 环节类型

	public static String LOCAL_NET_AREA_EXCH = "localExch_t"; // 本地网服务区局向

	public static String BUSINESS = "business"; //

	// add by 2007/10/26
	/**
	 * 服务区到营维区域级连数据集合
	 */
	public static String AREA_SERV_DEPT = "area_serv_dept_t";

	public static String SERV_DEPT = "serv_dept";

	/**
	 * 营维中心到支局级连数据集合
	 */
	public static String SERV_DEPT_BRANCH = "serv_dept_branch_t";

	public static String BRANCH = "branch";

	/**
	 * 本地网到服务区的级连数据集合localNet_t
	 */
	// public static String LOCAL_NET_AREA = "local_net_area";
	public static String LOCAL_NET_AREA = "localNet_t";
	/**
	 * 服务区到局向级连数据集合
	 */
	public static String AREA_EXCH = "area_exch_t";

	public static String CHANGE_SERV_SPEC_NO_FLAG = "changeServSpecNoFlag";

	/**
	 * Clone一个HashMap
	 * 
	 * @param hash
	 *            HashMap
	 * @throws DataCacheException
	 * @return HashMap
	 */
	private static HashMap hashClone(HashMap hash) throws DataCacheException {
		if (log.isDebugEnabled()) {
			log.debug("calling DataCache.hashClone()");
		}
		if (hash == null) {
			return null;
		}
		HashMap ret = new HashMap();
		Iterator ite = hash.keySet().iterator();
		Object key = null, keyObj = null;
		Object value = null, valueObj = null;
		try {
			while (ite.hasNext()) {
				key = ite.next();

				value = hash.get(key);
				if (key.getClass().getName().startsWith("java")) {
					keyObj = key;
				} else {
					try {
						keyObj = key.getClass().newInstance();
						PropertyUtils.copyProperties(keyObj, key);
					} catch (Exception ex) {
						keyObj = key;
					}
				}

				if (value.getClass().getName().startsWith("java")) {
					valueObj = value;
				} else {
					try {
						valueObj = key.getClass().newInstance();
						PropertyUtils.copyProperties(valueObj, value);
					} catch (Exception ex) {
						valueObj = value;
					}
				}
				ret.put(keyObj, valueObj);
			}
		} catch (Exception ex) {
			throw new DataCacheException(ex);
		}
		return ret;
	}

	/**
	 * 获取配置表DATA_CACHE_CONFIG信息
	 * 
	 * @throws DataCacheException
	 * @return List
	 */
	private static List getConfigTableInfo(String tableName, String treeFlag)
			throws DataCacheException {
		List results = null;
		String psql = " select                          "
				+ "       a.DATA_CACHE_CONFIG_ID,   "
				+ "       a.TABLE_NAME,             "
				+ "       a.CACHE_ID,               "
				+ "       a.CACHE_VALUE,            "
				+ "       a.CONDITION,              "
				+ "       a.CONDITION_VALUE,        "
				+ "       a.ORDER_ID,               "
				+ "       a.HASH_MAP,               "
				+ "       a.STS,                    "
				+ "       a.STS_DATE,               "
				+ "       a.PARENT_ID,              "
				+ "       a.TREE_FLAG,              "
				+ "       a.REMARKS                 "
				+ " from                            "
				+ "       DATA_CACHE_CONFIG a       "
				+ "        where 1=1                ";
		StringBuffer sql = new StringBuffer(psql);
		if (tableName != null) {
			sql.append(" and a.HASH_MAP ='" + tableName + "'");
		}
		if (treeFlag != null) {
			sql.append(" and a.TREE_FLAG ='" + treeFlag + "'");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs,
					DataCacheConfigSVO.class);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("get data from DATA_CHACHE_CONFIG error");
			}
			throw new DataCacheException(ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				if (log.isDebugEnabled()) {
					log.debug("close ps err");
				}
				throw new DataCacheException(ex);
			}

		}
		return results;
	}

	public static boolean isNumber(String val) {
		boolean isNum = true;
		if (StringUtil.isBlank(val))
			isNum = false;
		else {
			int len = val.length();
			for (int i = 0; i < len; i++) {
				int c = val.charAt(i);
				if (c < 48 || c > 57) {
					isNum = false;
					break;
				}
			}
		}
		return isNum;
	}

	/**
	 * 根据配置表中获取的基础表信息，读取相关的数据存储到一个hashmap，将其返回
	 * 
	 * @param dataConfig
	 * @throws DataCacheException
	 * @return HashMap
	 */
	private static Map setHashMap(DataCacheConfigSVO dataConfig,
			String conditon, String conditonValue) throws DataCacheException {
		Connection connection = null;
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select a." + dataConfig.getCacheId() + " , a."
				+ dataConfig.getCacheValue() + " from "
				+ dataConfig.getTableName() + " a where 1=1");
		if (dataConfig.getCondition() != null) {
			sql.append(" and " + dataConfig.getCondition()); // + "='"
			// + dataConfig.getConditionValue() + "' ");
		}
		/*if (conditon != null && isNumber(conditonValue)) {
			sql.append(" and a." + conditon + "=" + conditonValue + " ");
		} else */if (conditon != null) {
			sql.append(" and a." + conditon + "='" + conditonValue + "'");
		}
		if (dataConfig.getOrderId() != null) {
			sql.append(" order by a." + dataConfig.getOrderId());
		}
		// log.debug("setHashMap "+sql.toString());
		// if (dataConfig.getOrder() != null) {
		// sql.append(" order by a." + dataConfig.getOrder());
		// }
		ResultSet rs = null;
		Map hashMap = new LinkedHashMap();
		hashMap.clear();
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				hashMap.put(rs.getString(dataConfig.getCacheId()), rs
						.getObject(dataConfig.getCacheValue()));
			}
		} catch (SQLException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex);
			}
			throw new DataCacheException(ex);
		} catch (AppException e) {
			if (log.isDebugEnabled()) {
				log.debug(e);
			}
			throw new DataCacheException(e);
		} catch (SysException e) {
			if (log.isDebugEnabled()) {
				log.debug(e);
			}
			throw new DataCacheException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				if (log.isDebugEnabled()) {
					log.debug("close ps or rs error");
				}
			}

		}

		return hashMap;
	}

	/**
	 * 根据传入的配置表ID，查找其是否存在下级表，如果存在，只返回第一个下级表
	 * 
	 * @param dataCacheConfigId
	 * @return
	 * @throws DataCacheException
	 */
	private static DataCacheConfigSVO getChildTableInfo(String dataCacheConfigId)
			throws DataCacheException {
		DataCacheConfigSVO childTableInfo = null;
		List results = null;
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.DATA_CACHE_CONFIG_ID,a.TABLE_NAME,a.CACHE_ID,a.CACHE_VALUE, a.CONDITION,a.CONDITION_VALUE,"
						+ "a.ORDER_ID,a.HASH_MAP,a.STS,a.STS_DATE,a.PARENT_ID,a.TREE_FLAG,a.REMARKS from DATA_CACHE_CONFIG a where 1=1 ");
		if (dataCacheConfigId != null) {
			sql.append(" and a.PARENT_ID =" + dataCacheConfigId);
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs,
					DataCacheConfigSVO.class);
			if (results != null) {
				childTableInfo = (DataCacheConfigSVO) results.get(0);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("get data from DATA_CHACHE_CONFIG error");
			}
			throw new DataCacheException(ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				if (log.isDebugEnabled()) {
					log.debug("close ps err");
				}
				throw new DataCacheException(ex);
			}

		}
		return childTableInfo;
	}

	/**
	 * 获取一个树型列表，根据传入的表名，查找其是否有下级列表，如果有下级列表，将该列表添加到父表的VALUE字段，生成一个树型表结构
	 * 
	 * @param tableName
	 * @param condition
	 * @param condtionValue
	 * @return HashMap
	 * @throws DataCacheException
	 */
	private static Map getTreeHashMap(DataCacheConfigSVO parentTableInfo,
			String condition, String condtionValue) throws DataCacheException {
		Map parentHashMap = setHashMap(parentTableInfo, condition,
				condtionValue);// 取下级的上级节点
		DataCacheConfigSVO childTableInfo = (DataCacheConfigSVO) getChildTableInfo(parentTableInfo
				.getDataCacheConfigId());
		if (childTableInfo != null) {
			if (parentHashMap != null && !parentHashMap.isEmpty()) {
				Iterator ite = parentHashMap.keySet().iterator();
				Object key;
				while (ite.hasNext()) {
					key = ite.next();
					Map childHashMap = getTreeHashMap(childTableInfo,
							parentTableInfo.getCacheId(), key.toString());
					parentHashMap.put(key.toString(), childHashMap);
				}
			}
		}
		return parentHashMap;
	}

	/**
	 * 取一个cache对象
	 * 
	 * @param cacheObj
	 * @throws DataCacheException
	 * @return HashMap
	 */
	public static Map getCache(String cacheObj) throws DataCacheException {

		Map allObjHash = null;
		Map result = null;
		try {
			Field field = clazz.getDeclaredField("allHashMaps");// 获取所有hashmap集合
			allObjHash = (Map) field.get(null);
			result = (Map) allObjHash.get(cacheObj);// 根据传入参数返回指定的hashmap
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("The cache Object[" + cacheObj + "] is not exist");
			}
			throw new DataCacheException(ex);
		}
		return result;
	}

	/**
	 * 取一个cache对象
	 * 
	 * @param cacheObj
	 * @throws DataCacheException
	 * @return HashMap
	 */
	public static HashMap getCacheClone(String cacheObj)
			throws DataCacheException {

		HashMap allObjHash = null;
		HashMap result = null;
		try {
			Field field = clazz.getDeclaredField("allHashMaps");// 获取所有hashmap集合
			allObjHash = (HashMap) field.get(null);
			result = (HashMap) allObjHash.get(cacheObj);// 根据传入参数返回指定的hashmap
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("The cache Object[" + cacheObj + "] is not exist");
			}
			throw new DataCacheException(ex);
		}
		return hashClone(result);
	}

	/**
	 * 取一个cache对象，使用LabelValueBean重新封装
	 * 
	 * @param cacheObj
	 *            String 目标cache
	 * @throws DataCacheException
	 * @return HashMap
	 */
	public static List getListForOption(String cacheObj)
			throws DataCacheException {
		Map objHash = null;
		try {
			objHash = getCache(cacheObj);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("The cache Object[" + cacheObj + "] is not exist");
			}
			throw new DataCacheException(ex);
		}
		List lst = new ArrayList();
		if (objHash != null && !objHash.isEmpty()) {
			Iterator ite = objHash.keySet().iterator();
			Object value, label;
			while (ite.hasNext()) {
				LabelValueBean bean = new LabelValueBean();
				value = ite.next();
				label = objHash.get(value.toString());
				bean.setLabel(label.toString());
				bean.setValue(value.toString());
				lst.add(bean);
			}
		}
		return lst;
	}

	/**
	 * 根据表名和KEY值，从cache中读取Object数据
	 * 
	 * @param cacheObj
	 *            String 目标cache
	 * @param id
	 *            Object
	 * @throws DataCacheException
	 * @return Object
	 */
	public static Object getObject(String cacheObj, Object id)
			throws DataCacheException {
		Map objHash = new LinkedHashMap();
		try {
			objHash = getCache(cacheObj);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("The cache Object[" + cacheObj + "] is not exist");
			}
			throw new DataCacheException(ex);
		}
		Object obj = objHash.get((String) id);
		return obj;
	}

	/**
	 * 根据表名和KEY值， 从cache中读取String数据
	 * 
	 * @param cacheObj
	 *            String 目标cache
	 * @param id
	 *            Object
	 * @throws DataCacheException
	 * @return Object
	 */
	public static String getString(String cacheObj, Object id)
			throws DataCacheException {
		Map objHash = new LinkedHashMap();
		try {
			objHash = getCache(cacheObj);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("The cache Object[" + cacheObj + "] is not exist");
			}
			throw new DataCacheException(ex);
		}
		String obj = (String) objHash.get((String) id);
		if (obj == null) {
			String msg = "The id[" + id + "] is not in cache object["
					+ cacheObj + "]";
			if (log.isDebugEnabled()) {
				log.debug(msg);
			}
		}
		return obj;
	}

	/**
	 * 从cache中读取字符串数据,当找不到这个id的时候，返回空字符串("")
	 * 
	 * @param cacheObj
	 *            String 目标cache
	 * @param id
	 *            Object
	 * @throws DataCacheException
	 * @return Object
	 */
	public static String getStringNoException(String cacheObj, Object id)
			throws DataCacheException {
		Map objHash = new LinkedHashMap();
		try {
			objHash = getCache(cacheObj);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("The cache Object[" + cacheObj + "] is not exist");
			}
			throw new DataCacheException(ex);
		}
		String obj = (String) objHash.get((String) id);
		if (obj == null) {
			String msg = "The id[" + id + "] is not in cache object["
					+ cacheObj + "]";
			if (log.isDebugEnabled()) {
				log.debug(msg);
			}
			return "";
		}
		return obj;
	}

	/**
	 * 初始化方法，根据传入的数据库表名称，读取配置表中设置的需要初始化的基础表信息，将其存入一个hashMap集
	 * 
	 * @param tableName
	 * @param cachName
	 * @throws DataCacheException
	 */
	public static void initHashMaps(String tableName) throws DataCacheException {
		List configTablesInfo = getConfigTableInfo(tableName, null);// 获取配置表集合,如果传入null,则初始化配置表中所有数据集合
		if (configTablesInfo != null) {
			for (int i = 0; i < configTablesInfo.size(); i++) {
				DataCacheConfigSVO configInfo = (DataCacheConfigSVO) configTablesInfo
						.get(i);
				Map newTable = new LinkedHashMap();
				newTable = (LinkedHashMap) allHashMaps.get((String) configInfo
						.getHashMap());
				if (newTable != null) {
					newTable.clear();
				}
				try {
					newTable = setHashMap(configInfo, null, null);// 根据配置表中信息设置相应的hashMap
					allHashMaps.put((String) configInfo.getHashMap(),
							(LinkedHashMap) newTable);
				} catch (Exception ex) {
					throw new DataCacheException(ex);
				}
			}
		}
	}

	/**
	 * 初始化方法，根据传入的数据库表名称，读取配置表中设置的需要初始化的基础表信息，生成一个树型HashMap，存入hashMap集
	 * 
	 * @param tableName，传入null表示初始化配置表中所有有级连关系的数据集合
	 * @param cachName
	 * @throws DataCacheException
	 */
	public static void initTreeHashMap(String tableName)
			throws DataCacheException {
		if (tableName != null) {
			tableName = tableName.substring(0, tableName.lastIndexOf("_")); // 表名
		}
		List configTablesInfo = getConfigTableInfo(tableName, "Y");// 获取配置表集合
		if (configTablesInfo != null) {
			for (int i = 0; i < configTablesInfo.size(); i++) {
				DataCacheConfigSVO configInfo = (DataCacheConfigSVO) configTablesInfo
						.get(i);
				Map newTable = (LinkedHashMap) allHashMaps.get(configInfo
						.getHashMap()
						+ "_t");
				if (newTable != null) {
					newTable.clear();
				}
				try {
					newTable = getTreeHashMap(configInfo, null, null);// 根据配置表中设置获取相应的hashMap
					allHashMaps.put(configInfo.getHashMap() + "_t",
							(LinkedHashMap) newTable);
				} catch (Exception ex) {
					throw new DataCacheException(ex);
				}
			}
		}
	}
	
//	private static Map  getDataDicMap(String tableName,String columnName) throws DataCacheException {
//		
//		
//	}
//	
	
	
	

	/**
	 * 根据传入的表名，获取STATUS表中该表涉及到的数据集合，一个二级树型列表，COLUMN_NAM对应STS_ID,和STS_WORDS对应的HashMap对象
	 * 
	 * @param tableName,若传入null,获得STATUS表中所有数据集合
	 * @return
	 * @throws DataCacheException
	 */
	private static Map getStatusMap(String tableName) throws DataCacheException {
		List tableInfo = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.TABLE_NAME,a.COLUMN_NAME,a.STS_ID,a.STS_WORDS,a.ORDER_ID from STATUS a where 1=1 ");
		if (tableName != null) {
			sql.append(" and a.TABLE_NAME ='" + tableName + "'");
		}
		sql.append(" order by a.ORDER_ID,a.COLUMN_NAME");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			tableInfo = (List) ResultSetUtil.convertToList(rs, StatusSVO.class);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("get data from DATA_CHACHE_CONFIG error");
			}
			throw new DataCacheException(ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				if (log.isDebugEnabled()) {
					log.debug("close ps err");
				}
				throw new DataCacheException(ex);
			}

		}
		Map hashMaps = new LinkedHashMap();
		Map tableMap = new LinkedHashMap();
		Map columnMap = new LinkedHashMap();
		if (tableInfo != null) {
			for (int i = 0; i < tableInfo.size(); i++) {
				StatusSVO statusInfo = (StatusSVO) tableInfo.get(i);
				tableMap = (LinkedHashMap) hashMaps.get((String) statusInfo
						.getTableName());
				if (tableMap == null) {
					Map newTableMap = new LinkedHashMap();
					Map newColumnMap = new LinkedHashMap();
					newColumnMap.put((String) statusInfo.getStsId(), statusInfo
							.getStsWords());
					newTableMap.put((String) statusInfo.getColumnName(),
							newColumnMap);
					hashMaps.put((String) statusInfo.getTableName(),
							newTableMap);
				} else {
					columnMap = (LinkedHashMap) tableMap
							.get((String) statusInfo.getColumnName());
					if (columnMap == null) {
						Map newColumnMap = new LinkedHashMap();
						newColumnMap.put((String) statusInfo.getStsId(),
								statusInfo.getStsWords());
						tableMap.put((String) statusInfo.getColumnName(),
								newColumnMap);
						hashMaps.put((String) statusInfo.getTableName(),
								tableMap);
					} else {
						columnMap.put((String) statusInfo.getStsId(),
								statusInfo.getStsWords());
						tableMap.put((String) statusInfo.getColumnName(),
								columnMap);
						hashMaps.put((String) statusInfo.getTableName(),
								tableMap);
					}
				}
			}
		}
		return hashMaps;
	}

	/**
	 * 初始化STATUS表中指定表名的数据集合，生成一个树型HashMap，存入hashMap集
	 * 
	 * @param tableName
	 * @throws DataCacheException
	 */
	public static void initStatusHashMap(String tableName)
			throws DataCacheException {
		try {
			if (tableName != null) {
				tableName = tableName.substring(tableName.indexOf("_") + 1,
						tableName.length());
			}
			Map newTable = getStatusMap(tableName);// 根据传入的表名从STATUS表中获取相关数据集合，若传入null,则获取所有数据集合
			if (newTable != null && !newTable.isEmpty()) {
				Iterator ite = newTable.keySet().iterator();
				Object key;
				while (ite.hasNext()) {
					key = ite.next();
					Map tableMap = (LinkedHashMap) allHashMaps.get("status_"
							+ key.toString());
					if (tableMap != null) {
						tableMap.clear();
					}
					try {
						tableMap = (LinkedHashMap) newTable.get(key);
						allHashMaps.put("status_" + key.toString(),
								(LinkedHashMap) tableMap);
					} catch (Exception ex) {
						throw new DataCacheException(ex);
					}
				}
			}
		} catch (Exception ex) {
			throw new DataCacheException(ex);
		}
	}

	public static void clearAllMaps() {
		allHashMaps.clear();
	}

	private static Map getSysConfigMap() throws DataCacheException {
		Map result = new LinkedHashMap();
		List tableInfo = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.CONFIG_ID,a.NAME,a.SYSTEM_NAME,a.CONFIG_TYPE,a.CUR_VALUE,"
						+ "a.VALUE_DESC,a.CREATE_DATE from SYS_CONFIG a order by CONFIG_ID");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			tableInfo = (List) ResultSetUtil.convertToList(rs,
					SysConfigSVO.class);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("get data from SYS_CONFIG error");
			}
			throw new DataCacheException(ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				if (log.isDebugEnabled()) {
					log.debug("close ps err");
				}
				throw new DataCacheException(ex);
			}

		}
		if (tableInfo != null) {
			for (int i = 0, size = tableInfo.size(); i < size; i++) {
				SysConfigSVO vo = (SysConfigSVO) tableInfo.get(i);
				result.put((String) vo.getConfigId(), (SysConfigSVO) vo);
			}
		}
		return result;
	}
	
	private static Map getSysPropertiesMap() throws DataCacheException {
		Map result = new LinkedHashMap();
		List tableInfo = new ArrayList();
		Properties p=  SysConfig.getConfig();
			Enumeration enu = p.propertyNames();  
			while( enu .hasMoreElements()){
				String ele=(String)enu.nextElement();
				SysConfigSVO vo=new SysConfigSVO();
				vo.setConfigType(SysConstants.SYS_CONFIG_TYPE_PROVINCE);
				vo.setCurValue(p.getProperty(ele));
				result.put(ele,vo);
			} 
		
		return result;
	}

	private static Map getSysAreaConfig() throws DataCacheException {
		List tableInfo = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.SYS_AREA_CONFIG_ID,a.CONFIG_ID,a.SP_AREA_ID,"
				+ "a.CUR_VALUE ,a.VALUE_DESC from SYS_AREA_CONFIG a "
				+ " order by CONFIG_ID");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			tableInfo = (List) ResultSetUtil.convertToList(rs,
					SysAreaConfigSVO.class);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("get data from SYS_AREA_CONFIG error");
			}
			throw new DataCacheException(ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				if (log.isDebugEnabled()) {
					log.debug("close ps err");
				}
				throw new DataCacheException(ex);
			}

		}
		Map tableMap = new LinkedHashMap();
		Map columnMap = new LinkedHashMap();
		if (tableInfo != null) {
			for (int i = 0, size = tableInfo.size(); i < size; i++) {
				SysAreaConfigSVO configInfo = (SysAreaConfigSVO) tableInfo
						.get(i);
				if (tableMap == null) {
					Map newColumnMap = new LinkedHashMap();
					newColumnMap.put((String) configInfo.getSpAreaId(),
							configInfo);
					tableMap.put((String) configInfo.getConfigId(),
							newColumnMap);
				} else {
					columnMap = (HashMap) tableMap.get((String) configInfo
							.getConfigId());
					if (columnMap == null) {
						Map newColumnMap = new LinkedHashMap();
						newColumnMap.put((String) configInfo.getSpAreaId(),
								configInfo);
						tableMap.put((String) configInfo.getConfigId(),
								newColumnMap);
					} else {
						columnMap.put((String) configInfo.getSpAreaId(),
								configInfo);
						tableMap.put((String) configInfo.getConfigId(),
								columnMap);
					}
				}
			}
		}
		return tableMap;
	}

	public static void initSysConfigHashMap() throws DataCacheException {
		Map newTable = (Map) allHashMaps.get(DataCache.SYS_CONFIG);
		if (newTable != null) {
			newTable.clear();
		}
		try {
			newTable = getSysConfigMap();// 根据配置表中设置获取相应的hashMap
			allHashMaps.put(DataCache.SYS_CONFIG, newTable);
		} catch (Exception ex) {
			throw new DataCacheException(ex);
		}
	}
	
	public static void initPropertiesHashMap() throws DataCacheException{
		Map newTable = (Map) allHashMaps.get(DataCache.SYS_CONFIG);
		if (newTable != null) {
			newTable.clear();
		}
		try {
			newTable = getSysPropertiesMap();// 根据配置表中设置获取相应的hashMap
			allHashMaps.put(DataCache.SYS_CONFIG, newTable);
		} catch (Exception ex) {
			throw new DataCacheException(ex);
		}
		
	}

	public static void initSysAreaConfigHashMap() throws DataCacheException {
		Map newTable = (Map) allHashMaps.get(DataCache.SYS_AREA_CONFIG);
		if (newTable != null) {
			newTable.clear();
		}
		try {
			newTable = getSysAreaConfig();// 根据配置表中设置获取相应的hashMap
			allHashMaps
					.put(DataCache.SYS_AREA_CONFIG, (LinkedHashMap) newTable);
		} catch (Exception ex) {
			throw new DataCacheException(ex);
		}
	}

	/**
	 * 根据配置表初始化缓存信息
	 * 
	 * @throws DataCacheException
	 */
	public static void initial() throws DataCacheException {
		// 对配置中所有表对应数据进行缓存
		//initHashMaps(null);
		//initTreeHashMap(null);
		//initStatusHashMap(null);
		//initSysAreaConfigHashMap();
		//initSysConfigHashMap();
	}

	/**
	 * 根据数据库表名刷缓存
	 * 
	 * @param tableName
	 * @throws DataCacheException
	 */
	public static void refresh(String tableName) throws DataCacheException {
		System.out.println("根据数据库表名, 从DATA_CACHE_CONFIG查找对应的记录");
		if ("SYS_AREA_CONFIG".equals(tableName)) {
			initSysAreaConfigHashMap();
		} else if ("SYS_CONFIG".equals(tableName)) {
			initSysConfigHashMap();
		} else if (tableName != null) {
			List tableList = getConfigTableList(tableName);
			if (tableList == null) {
				throw new DataCacheException(new Exception(
						"从DATA_CACHE_CONFIG表中查找缓存配置错误"));
			} else if (tableList.size() == 0) {
				throw new DataCacheException(new Exception(
						"从DATA_CACHE_CONFIG表查不到缓存配置"));
			} else {
				for (int i = 0; i < tableList.size(); i++) {
					DataCacheConfigSVO dataCacheConfigSVO = (DataCacheConfigSVO) tableList
							.get(i);

					String mapKey = dataCacheConfigSVO.getHashMap();
					if ("Y".equals(dataCacheConfigSVO.getTreeFlag())) {
						mapKey += "_t";
					}

					Map tableMap = (LinkedHashMap) allHashMaps.get(mapKey);
					if (tableMap != null) {
						tableMap.clear();
					}
					tableMap = setHashMap(dataCacheConfigSVO, null, null);
					allHashMaps.put(mapKey, (LinkedHashMap) tableMap);
				}
			}
		} else {
			// 刷新整个缓存数据
			initial();
		}

	}

	private static List getConfigTableList(String tableName)
			throws DataCacheException {
		List rtnList = null;
		log.debug("根据数据库表名, 从DATA_CACHE_CONFIG查找对应的记录");

		Sql sql = new Sql(
				"SELECT * FROM DATA_CACHE_CONFIG C WHERE C.TABLE_NAME = :tableName");
		sql.setString("tableName", tableName);

		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cn = ConnectionFactory.getConnection();
			pstmt = cn.prepareStatement(sql.getSql());
			pstmt = sql.fillParams(pstmt);
			sql.log(DataCache.class);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rtnList == null) {
					rtnList = new ArrayList();
				}
				DataCacheConfigSVO dataCacheConfig = new DataCacheConfigSVO();

				dataCacheConfig.setDataCacheConfigId(rs
						.getString("DATA_CACHE_CONFIG_ID"));
				dataCacheConfig.setTableName(rs.getString("TABLE_NAME"));
				dataCacheConfig.setCacheId(rs.getString("CACHE_ID"));
				dataCacheConfig.setCacheValue(rs.getString("CACHE_VALUE"));
				dataCacheConfig.setCondition(rs.getString("CONDITION"));
				dataCacheConfig.setConditionValue(rs
						.getString("CONDITION_VALUE"));
				dataCacheConfig.setHashMap(rs.getString("HASH_MAP"));
				dataCacheConfig.setOrderId(rs.getString("ORDER_ID"));
				dataCacheConfig.setParentId(rs.getString("PARENT_ID"));
				dataCacheConfig.setTreeFlag(rs.getString("TREE_FLAG"));
				dataCacheConfig.setSts(rs.getString("STS"));
				dataCacheConfig.setStsDate(new Date(rs.getTimestamp("STS_DATE")
						.getTime()));
				dataCacheConfig.setRemarks(rs.getString("REMARKS"));

				rtnList.add(dataCacheConfig);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnList;
	}
}