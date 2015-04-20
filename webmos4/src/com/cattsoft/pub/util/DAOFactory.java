package com.cattsoft.pub.util;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.cattsoft.pub.config.DAOConfig;
import com.cattsoft.pub.dao.IDAO;

public class DAOFactory {
	private static Logger log = Logger.getLogger(DAOFactory.class);

	private static final Properties config = DAOConfig.getConfig();

	private static DAOFactory instance;

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	/**
	 * get dao implementation instance from configuration file
	 * 
	 * @param ifaceClass
	 *            dao interface class
	 * @return instance of a dao implementation
	 */
	public IDAO getDAO(Class ifaceClass) {
		IDAO dao = null;
		try {
			Object obj = this.getImplClass(ifaceClass).newInstance();
			if (obj instanceof IDAO) {
				dao = (IDAO) obj;
			} else {
				throw new RuntimeException(
						"invalid implementation class for dao: "
								+ ifaceClass.getName());
			}
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(e.getMessage());
			}
			throw new RuntimeException(
					"failed to create implementation for dao: "
							+ ifaceClass.getName());
		}
		return dao;
	}

	private Class getImplClass(Class ifaceClass) {
		Class implClass = null;
		String qualifiedClassName = config.getProperty(ifaceClass.getName()
				.substring(ifaceClass.getName().lastIndexOf(".") + 1,
						ifaceClass.getName().length()));
		if (qualifiedClassName == null) {
			throw new RuntimeException(
					"no implementation class configured for dao: "
							+ ifaceClass.getName());
		}
		try {
			implClass = Class.forName(qualifiedClassName);
		} catch (ClassNotFoundException e) {
			if (log.isDebugEnabled()) {
				log.debug(e.getMessage());
			}
			throw new RuntimeException(
					"failed to get implementation class for dao: "
							+ ifaceClass.getName());
		}
		return implClass;
	}

}
