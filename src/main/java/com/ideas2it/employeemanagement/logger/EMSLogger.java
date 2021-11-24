/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12 
 */
package com.ideas2it.employeemanagement.logger;

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

    public static final Logger logger = LogManager.getLogger(EMSLogger.class); 
    
    public static void info(Object message) {
    	logger.info(message);
    }
    
    public static void error(Object message) {
    	logger.error(message);
    }
}
