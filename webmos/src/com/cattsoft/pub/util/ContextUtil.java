package com.cattsoft.pub.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class ContextUtil {
	private static Logger log = Logger.getLogger(ContextUtil.class);

	public static Context getContext() {
		Context context = null;
		try {
			context = new InitialContext();
			if (context == null) {
				InputStream is = ContextUtil.class.getClassLoader()
						.getResourceAsStream("jndi.properties");
				Properties properties = new Properties();
				properties.load(is);
				context = new InitialContext(properties);
			}
		} catch (NamingException e) {
			if (log.isDebugEnabled()) {
				log.debug(e.getMessage());
			}
			throw new RuntimeException("failed to initialize context");
		} catch (IOException e) {
			if (log.isDebugEnabled()) {
				log.debug(e.getMessage());
			}
			throw new RuntimeException("failed to load jndi.properties");
		}
		return context;
	}
}
