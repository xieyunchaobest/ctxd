package com.cattsoft.pub.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DAOConfig {
    private static Logger log = Logger.getLogger(DAOConfig.class);

    private static Properties config;

    public synchronized static Properties getConfig() {
        if (config == null) {
            config = new Properties();
            InputStream is = null;
            try {
                is = DAOConfig.class.getClassLoader().getResourceAsStream("dao_config.properties");
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
