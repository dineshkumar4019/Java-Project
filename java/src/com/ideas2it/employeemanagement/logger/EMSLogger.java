/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12 
 */
package com.ideas2it.employeemanagement.logger;

import java.io.File;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Handling the log messages
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-11-10
 */
public class EMSLogger {
    //System.setProperty("log4j2.configurationFile",".src/resources/log4j2.xml");
    //System.setProperty("logFilePath", "src/resources/log4j2.xml");
    LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
    File file = new File("resources/log4j2.xml");
    context.setConfigLocation(file.toURI());
    public static final Logger logger = LogManager.getLogger(EMSLogger.class); 
}
