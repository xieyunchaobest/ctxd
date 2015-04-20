package com.cattsoft.pub.connection;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * Stores a final data source instance. <br>
 * Get from <code>getDataSource</code> whenever you need a data source instance. <br>
 * Apr 22, 2007 8:18:45 AM
 * 
 * @author Moon
 */
public class DataSourceUtil {
	private static Logger log = Logger.getLogger(DataSourceUtil.class);
	public static String DATA_SOURCE ="ds/mysql"; // "OracleNew97";
	
	private static  DataSource datasource;
	
	public static InitialContext getInitialContext(){
        String url = "t3://localhost:7001";
        String user = "weblogic";
        String password = "weblogic";
        Properties properties = null;
        try {
            properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,
                           "weblogic.jndi.WLInitialContextFactory");
            properties.put(Context.PROVIDER_URL, url);
            if (user != null) {
                properties.put(Context.SECURITY_PRINCIPAL, user);
                properties.put(Context.SECURITY_CREDENTIALS,
                               password == null ? " " : password);
            }
           
				return new InitialContext(properties);
		} catch (NamingException e) {
			throw new RuntimeException("fail to get Context!",e);
		}
       

	}

	
	public static void setJNDI(String jndiName){
		DATA_SOURCE = jndiName;
	}

	public static DataSource getDataSource() {
		if(null == datasource ){
			try {
				Context context = new InitialContext();
				//Context context = DataSourceUtil.getInitialContext();
				log.debug("look up datasource:" + DATA_SOURCE +"....");
				datasource = (DataSource) context.lookup(DATA_SOURCE);
				log.debug("get datasource:" + datasource);
			} catch (NamingException e) {
				if (log.isDebugEnabled()) {
					log.debug(e.getMessage());
				}
				//throw new RuntimeException("failed to look up datasource");
			} catch (Exception e) {
				e.printStackTrace();
				//throw new RuntimeException("failed to look up datasource");
				
			}
		}
		return datasource;
	}
}
