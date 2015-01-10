package com.cattsoft.pub.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SysConfig {
	private static Logger log = Logger.getLogger(SysConfig.class);

	private static Properties config;

	public synchronized static Properties getConfig() {
		if (config == null) {
			config = new Properties();
			InputStream is = null;
			try {
				is = SysConfig.class.getClassLoader().getResourceAsStream("sys_config.properties");
				if (is == null) {
					log.debug("读取系统配置文件【sys_config.properties】失败");
				}
				config.load(is);
			} catch (IOException e) {
				if (log.isDebugEnabled()) {
					log.debug(e.getMessage());
				}
				throw new RuntimeException("failed to read dao configuration file");
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						if (log.isDebugEnabled()) {
							log.debug(e.getMessage());
						}
					}
				}
			}
		}
		return config;
	}
}
