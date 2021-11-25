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
    
	public Logger logger;
	
	public EMSLogger(Class<?> className) {
	    logger = LogManager.getLogger(className);
	}
	
    public void debug(Object message) {
    	logger.debug(message);
    }
    
    public void info(Object message) {
    	logger.info(message);
    }
    
    public void error(Object message) {
    	logger.error(message);
    }
    
    public void fatal(Object message) {
    	logger.fatal(message);
    }
}
