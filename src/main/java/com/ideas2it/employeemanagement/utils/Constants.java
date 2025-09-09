/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12 
 */
package com.ideas2it.employeemanagement.utils;

import java.util.Map;
import java.util.HashMap;

/**
 * <h1>Constants</h1>
 * Contains error code for the exception occurs in the project
 * and employee CRUD operations with respective error messages
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class Constants {
    public static final Map<String, String> errorCodes = getErrors();
    public static final String ERROR_CODE_001 = "001";
    public static final String ERROR_CODE_002 = "002";
    public static final String ERROR_CODE_003 = "003";
    public static final String ERROR_CODE_004 = "004";
    public static final String ERROR_CODE_005 = "005";
    public static final String ERROR_CODE_006 = "006";
    public static final String ERROR_CODE_007 = "007";
    public static final String ERROR_CODE_008 = "008";
    public static final String ERROR_CODE_009 = "009";
    public static final String ERROR_CODE_010 = "010";
    public static final String ERROR_CODE_011 = "011";
    public static final String ERROR_CODE_012 = "012";
    public static final String ERROR_CODE_013 = "013";
    
    private static Map<String, String> getErrors() {
        Map<String, String> customErrors = new HashMap<>();
        
        customErrors.put(ERROR_CODE_001,"Employee creation failed");
        customErrors.put(ERROR_CODE_002,"Address creation failed");
        customErrors.put(ERROR_CODE_003,"Employee updation failed");
        customErrors.put(ERROR_CODE_004,"Employee deletion failed");
        customErrors.put(ERROR_CODE_005,"Address deletion failed");
        customErrors.put(ERROR_CODE_006,"Getting employee failed");
        customErrors.put(ERROR_CODE_007,"Getting address failed");
        customErrors.put(ERROR_CODE_008,"Error occurred While getting email");
        customErrors.put(ERROR_CODE_009,"Error occurred While getting phone Number");
        customErrors.put(ERROR_CODE_010,"Project creation failed");
        customErrors.put(ERROR_CODE_011,"Project updation failed");
        customErrors.put(ERROR_CODE_012,"Getting project failed");
        customErrors.put(ERROR_CODE_013,"Project deletion failed");

        return customErrors;
    }
}
