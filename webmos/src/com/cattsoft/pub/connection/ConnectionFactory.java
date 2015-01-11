package com.cattsoft.pub.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.StringUtil;

/**
 * 
 * Title: 服务开通系统<br>
 * Description: 通过配置的连接池、数据源获取连接<br>
 * Date: 2007-8-17 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class ConnectionFactory {
	private static Logger log = Logger.getLogger(ConnectionFactory.class);

	private static final String CONNECTIONS_FILE = "connection-config.xml";

	// 配置的多个connection的ThreadLocal
	// private Map threadConns = new HashMap();

	private ThreadLocal threadConn = new ThreadLocal();

	private Map dataSourceMap = new HashMap();

	// 默认dataSource名称
	private DataSource defaultDataSource = null;

	private String defaultName = "";

	private static ConnectionFactory instance;

	private ConnectionFactory(String path) throws SysException, AppException {
		ConnectionConfigReader connReader = new ConnectionConfigReader();
		InputStream in;

		log.debug("开始装载配置文件:" + CONNECTIONS_FILE);
		in = ConnectionFactory.class.getClassLoader().getResourceAsStream(CONNECTIONS_FILE);
		connReader.read(in);
		ConnectionConfigDef pro = connReader.getConfigDef();
		Iterator itor = pro.getConnections().keySet().iterator();
		String connName = null;
		DataSource ds = null;
		while (itor.hasNext()) {
			connName = (String) itor.next();
			ConnectionDef connDef = (ConnectionDef) pro.getConnections().get(connName);
			String datasource = connDef.getDatasource();
			if (!StringUtil.isBlank(datasource)) {
				ds = getJNDIDataSource(connDef);
			} else {
				ds = getDBCPDataSource(connDef);
			}
			if (null == ds) {
				log.error("系统获取连接的数据源失败！" + connName);
			}
			log.debug("ds: " + ds);
			dataSourceMap.put(connName, ds);

		}
		Object obj = dataSourceMap.get(pro.getDefaultName());
		if (obj == null)
			throw new AppException("", "系统未找到默认数据源！");
		this.defaultDataSource = (DataSource) obj;
		this.defaultName = pro.getDefaultName();
		log.info("默认数据源:" + defaultName + "/" + defaultDataSource);

		try {
			Connection conn = defaultDataSource.getConnection();
			log.debug("connection:" + conn);
			JdbcUtil.close(conn);

		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new SysException("", "系统获取默认连接的数据源失败！", e);
		}
		log.debug("成功装载连接配置文件：" + CONNECTIONS_FILE);

	}

	/**
	 * 获得DBCP连接池的数据源
	 * 
	 * @return
	 */
	private DataSource getDBCPDataSource(ConnectionDef connDef) {

		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(connDef.getDriverClass());
		ds.setUsername(connDef.getUserName());
		ds.setPassword(connDef.getPassword());
		ds.setUrl(connDef.getUrl());
		//ds.setTestOnBorrow(true);//从池中取得链接时做健康检查，该做法十分保守
		ds.setMaxActive(Integer.parseInt(connDef.getPoolMaxActive()));
		ds.setMaxIdle(Integer.parseInt(connDef.getPoolMaxIdle()));
		ds.setMaxWait(Integer.parseInt(connDef.getPoolMaxWait()));
		ds.setValidationQuery("select * from dual");
		ds.setTestOnBorrow(true);
		ds.setTestOnReturn(true);
		ds.setTestWhileIdle(true);
		return ds;

	}

	/**
	 * 获得JNDI的数据源
	 * 
	 * @return
	 */
	private DataSource getJNDIDataSource(ConnectionDef connDef) {
		DataSourceUtil.setJNDI(connDef.getDatasource());
		return DataSourceUtil.getDataSource();

	}

	/**
	 * 初始化连接配置和数据源
	 * 
	 * @param path
	 * @throws SysException
	 * @throws AppException
	 */
	synchronized public static void initConnectionFactory(String path) throws SysException,
			AppException {
		if (null == instance) {
			instance = new ConnectionFactory(path);
		}
	}

	/**
	 * 初始化连接配置和数据源
	 * 
	 * @param path
	 * @throws SysException
	 * @throws AppException
	 */
	synchronized public static void initConnectionFactory() throws SysException, AppException {
		if (null == instance) {
			instance = new ConnectionFactory(null);
		}
	}

	/**
	 * 创建连接
	 * 
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public static Connection createConnection() throws AppException, SysException {
		if (null == instance) {
			// throw new AppException("","系统未初始化ConnectionFactory！");
			initConnectionFactory();
		}
		return createConnection(instance.defaultName);
	}

	/**
	 * 创建连接
	 * 
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public static Connection createConnection(String name) throws AppException, SysException {
		if (null == instance) {
			// throw new AppException("","系统未初始化ConnectionFactory！");
			initConnectionFactory();
		}
		ThreadLocal threadConn = instance.threadConn;
		Object objConn = threadConn.get();
		if (objConn == null) {
			log.debug("获取连接中 ...");
			Connection conn = null;
			try {
				Object obj = instance.dataSourceMap.get(name);
				if (obj == null)
					throw new AppException("", "系统未找到默认数据源！");
				log.debug("已获取数据源配置 ...");
				DataSource ds = (DataSource) obj;
				conn = ds.getConnection();
				log.debug("成功创建连接:" + conn);
				threadConn.set(conn);
			} catch (SQLException e) {
				log.debug("系统创建连接失败！");
				throw new SysException("", "系统创建连接失败！", e);
			}

		} else {
			// 如果之前已有连接，先做关闭
			throw new AppException("1000002", "系统发现当前线程已存在一个可用连接，请先关闭已有连接或直接使用当前连接！");

		}
		return (Connection) threadConn.get();
	}

	/**
	 * 获得当前线程对应的连接
	 * 
	 * @return
	 * @throws SysException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws AppException, SysException {
		Object objConn = instance.threadConn.get();
		if (null == objConn) {
			throw new AppException("100001", "系统当前无可用连接!");
		}
		Connection conn = (Connection) objConn;
		return conn;
	}

	/**
	 * 提交当前连接的事务
	 * 
	 * @throws AppException
	 * @throws SysException
	 */
	public static void commit() throws AppException, SysException {
		Object objConn = instance.threadConn.get();
		if (null == objConn) {
			throw new AppException("100001", "系统当前无可用连接!");
		}
		Connection conn = (Connection) objConn;

		try {
			conn.commit();
		} catch (SQLException e) {
			throw new SysException("100001", "系统事务提交错误!", e);
		}
	}

	/**
	 * 回滚当前连接的事务
	 * 
	 * @throws AppException
	 * @throws SysException
	 */
	public static void rollback() throws AppException, SysException {
		Object objConn = instance.threadConn.get();
		if (null == objConn) {
			throw new AppException("100001", "系统当前无可用连接!");
		}
		Connection conn = (Connection) objConn;

		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new SysException("100001", "系统事务提交错误!", e);
		}
	}

	/**
	 * 释放当前连接
	 * 
	 * @throws SysException
	 * @throws AppException
	 * 
	 */
	public static void closeConnection() throws SysException, AppException {
		Object objConn = instance.threadConn.get();
		if (objConn == null)
			throw new AppException("100001", "系统找不到要关闭的连接！");
		Connection conn = (Connection) objConn;
		if (conn != null) {
			log.debug("释放连接：" + conn);
			JdbcUtil.close(conn);
			instance.threadConn.set(null);
		}
	}

	public static void main(String[] args) throws SysException, AppException {
		ConnectionFactory.initConnectionFactory();
	}

}
