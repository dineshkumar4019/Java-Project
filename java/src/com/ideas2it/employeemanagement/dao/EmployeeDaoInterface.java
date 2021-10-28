/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Address;

/**
 * <h1> Employee DAO</h1>
 * Employees data manipulation by inserting, updating and deleting the
 * employee from the database and displaying employee records
 * from database
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public interface EmployeeDaoInterface {

    /**
     * Inserting the employee details from  user input
     * to the database
     *
     * @return database empty or not
     */
    public int insertEmployee(Employee employee) throws SQLException;
    //public int insertAddress(Employee employee) throws SQLException;
    
    /**
     * Updating all employee fields in the database
     *
     * @return Total number of rows updated in database
     */
    public int updateAllFields(Employee employee) throws SQLException;
    
    //public int updateField(Employee employee) throws SQLException;
    
    /**
     * Deleting particular employee in the database by 
     * corresponding employee id
     *
     * @return Total number of rows deleted in database
     *
    public int deleteEmployee(int id) throws SQLException;
    
    /**
     * Deleting all employees in the database
     *
     * @return Total number of rows updated in database
     *
    public int deleteAllEmployee() throws SQLException;
    
    /**
     * Getting the employee details from the database
     * by corresponding employee id 
     *
     * @return Single employee details
     */
    public Employee getEmployee(int id) throws SQLException;
    
    /** 
     * Getting all employees details from the database 
     *
     * @return All employees and their details
     */
    public List<Employee> getEmployees() throws SQLException;
    
    /** 
     * Getting the total number of employees present
     * in the database
     *
     * @return Total number of employees present
     */
    public long getTotalEmployees() throws SQLException;
    
    /** 
     * Checking the phone number already 
     * exist in the database 
     *
     * @return Phone number exist or not
     *
    public boolean getPhoneNumber(long phoneNumber) throws SQLException;
    
    /** 
     * Checking the email already exist
     * in the database 
     *
     * @return Email exist or not
     *
    public boolean getEmail(String email) throws SQLException;
    
    /** 
     * Checking the employee already exist
     * in the database 
     *
     * @return Employee exist or not
     *
    public boolean isEmployeeExist(int id) throws SQLException;
    */
}
