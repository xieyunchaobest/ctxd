package com.cattsoft.pub;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Title: 服务开通系统<br>
 * Description: <br>
 * Date: 2007-9-22 <br>
 * Copyright (c) 2007 CATTSoft<br>
 *
 * @author liaoyh
 */
public class LogConfig {
    private static final Logger log = Logger.getLogger(LogConfig.class);

    public static void init() {

        Properties config = new Properties();
        InputStream is = null;
        try {
            String log4jStr = System.getProperty("log4j");
            //log4j初始化前，只能System.out输出
            System.out.println("log4jStr=" + log4jStr);
            if (log4jStr != null) {
                PropertyConfigurator.configure(log4jStr);// 加载传入的配置文件
            } else {
                is = LogConfig.class.getClassLoader().getResourceAsStream("log4j.properties");//加载默认位置的配置文件
                config.load(is);
            }

        } catch (IOException e) {
            throw new RuntimeException("failed to read log4j configuration file");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        PropertyConfigurator.configure(config);
        log.debug("can log");
    }

}
