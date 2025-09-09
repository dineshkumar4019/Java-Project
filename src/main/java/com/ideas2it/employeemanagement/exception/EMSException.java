/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12 
 */
package com.ideas2it.employeemanagement.exception;

import com.ideas2it.employeemanagement.utils.Constants;

/**
 * <h1> Employee Management System Exception</h1>
 * Handling the exception created while performing actions on the database
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
 
public class EMSException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EMSException(String errorCode) {
        super(Constants.errorCodes.get(errorCode));
    }
}
