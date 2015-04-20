package com.cattsoft.pub.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ReleaseTimeUtil {

	public static void writePrperties(String date) throws IOException {
		FileOutputStream fileOutputStream = null;
		try {
			Properties config = new Properties();
			String path = ReleaseTimeUtil.class.getClassLoader().getResource(
					"sys_config.properties").getPath();
			config.load(ReleaseTimeUtil.class.getClassLoader()
					.getResourceAsStream("sys_config.properties"));
			config.setProperty("system.buildTime", date);

			fileOutputStream = new FileOutputStream(path);
			config.store(fileOutputStream, "system configurations");
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

	public static void main(String[] args) {
		String buildTime = null;
		if (args != null && args.length > 0 && args[0] != null
				&& !"".equals(args[0])) {
			buildTime = args[0].replace(',', ' ');
		} else {
			SimpleDateFormat datetimef = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			buildTime = datetimef.format(new Date());
		}
		try {
			writePrperties(buildTime);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
