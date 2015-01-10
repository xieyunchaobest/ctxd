package com.cattsoft.pub.util;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class JndiUtil {
	private static Logger log = Logger.getLogger(JndiUtil.class);

	private static Map jndi = new HashMap();

	public static Object lookup(String name) {
		Object obj = null;
		if (jndi.containsKey(name)) {
			obj = jndi.get(name);
		} else {
			Context context = ContextUtil.getContext();
			try {
				obj = context.lookup(name);
				jndi.put(name, obj);
			} catch (NamingException e) {
				if (log.isDebugEnabled()) {
					log.debug(e.getMessage());
				}
				throw new RuntimeException(
						"failed to look up object for name: " + name);
			}
		}
		return obj;
	}
}
