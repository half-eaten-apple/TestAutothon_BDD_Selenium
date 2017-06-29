package com.epam.utils;

/**
 * Created by vsharma on 22-09-2016.
 */
import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.PropertyConfigurator;

public class Log4jLogger {
	public static final Logger frameworkLog = LoggerFactory.getLogger(Log4jLogger.class);
	private static Logger logger = null;

	private Log4jLogger() {
		// TODO Auto-generated constructor stub
	}

	public static Logger getLog4jInstance() throws IOException {
		try {
			frameworkLog.error("logger-->"+logger);
			if (logger == null) {
				File log4jfile = new File(Config.getConfig().getConfigProperty("Log4JPropertiesFilePath"));
				PropertyConfigurator.configure(log4jfile.getAbsolutePath());
				logger = LoggerFactory.getLogger(" Common Automation Framework ");
			}
		} catch(Exception e) {
			logger.debug("Issues with the Log4j Config Properties File : " + e.getMessage());
			return null;
		}
		return logger;
	}
}