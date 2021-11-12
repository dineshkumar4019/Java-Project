/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.dao;

import java.util.List;
import com.ideas2it.employeemanagement.exception.EMSException;
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
 * 
 */
public interface EmployeeDaoInterface {
    
    /**
     * Inserting the employee details from  user input
     * to the database
     *
     * @return Number of rows inserted
     */
    public int insertEmployee(Employee employee) throws EMSException;
    
    /**
     * Inserting the employee address details from  user input
     * to the database
     *
     * @return Number of rows inserted
     */
    public int insertAddress(Address address) throws EMSException;
    
    /**
     * Updating all employee fields in the database
     *
     * @param employee employee details to update
     * @return Total number of rows updated in database
     */
    public int updateAllFields(Employee employee) throws EMSException;

    /**
     * Deleting particular employee in the database by 
     * corresponding employee id
     *
     * @param id id of an employee to delete
     * @return Total number of rows deleted in database
     */
    public int deleteEmployee(int id) throws EMSException;
    
    /**
     * Deleting particular employee in the database by 
     * corresponding employee id
     *
     * @param id id of an employee to delete
     * @return Total number of rows deleted in database
     */
    public int deleteAddress(int addressId) throws EMSException;
    
    /**
     * Deleting all employees in the database
     *
     * @return Total number of rows deleted in database
     */
    public int deleteAllEmployee() throws EMSException;
    /**
     * Getting the employee details from the database
     * by corresponding employee id 
     *
     * @param id id of an employee to get
     * @return Single employee details
     */
    public Employee getEmployee(int id) throws EMSException;
    /**
     * Getting the address details from the database
     * by corresponding address id 
     *
     * @param id id of an address to get
     * @return Single address details
     */
    public Address getAddress(int addressId) throws EMSException;
 
    /** 
     * Getting all employees details from the database 
     *
     * @return All employees and their details
     */
    public List<Employee> getEmployees() throws EMSException;

    /** 
     * Checing the email already exist in the database
     * for an employee
     *
     * @param email email to check the existance
     * @return email exist or not
     */
    public boolean isEmailExist(String email) throws EMSException;
   
    /** 
     * Checing the phonenumber already exist in the database
     * for an employee
     *
     * @param phoneNumber phone number to check the existance
     * @return email exist or not
     */
   public boolean isPhoneNumberExist(long phoneNumber) throws EMSException;
}

