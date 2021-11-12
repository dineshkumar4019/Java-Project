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

    public static final Logger logger = LogManager.getLogger(EMSLogger.class); 
}
