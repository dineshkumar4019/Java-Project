/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;

public interface EmployeeServiceInterface {
    /**
     * Checking an employee presences in database by id
     *
     * @param id employee id for checking existance
     * @return employee exist or not
     */
    public boolean isEmployeeExist(int id) throws EMSException;
    
    /**
     * Checking if employees present in database
     *
     * @return database empty or not
     */
    public long isDataBaseEmpty() throws EMSException;
    
    /**
     * Validating the corresponding employee name  
     *
     * @param name employee name for validation
     * @return name is valid or not
     */
    public boolean validateName(String name);
    
    /**
     * Validating the corresponding employee salary  
     *
     * @param salary employee salary for validation
     * @return salary is valid or not
     */
    public boolean validateSalary(double salary);
    
    /**
     * Checking the corresponding email is present in database
     *
     * @param email email for checking existence
     * @return email exist or not
     */
    public boolean isEmailExist(String email) throws EMSException;
    
    /**
     * Validating the corresponding employee email 
     *
     * @param email employee email for validation
     * @return email is valid or not
     */
    public boolean validateEmail(String email);
    
    /**
     * Checking the corresponding phone number is present in database
     *
     * @param phoneNumber phoneNumber for checking existence
     * @return phoneNumber exist or not
     */
    public boolean isPhoneNumberExist(long phoneNumber) throws EMSException;
    
    /**
     * Validating the corresponding employee phoneNumber 
     *
     * @param phoneNumber employee phoneNumber for validation
     * @return phoneNumber is valid or not
     */
    public boolean validatePhoneNumber(String phoneNumber);
    
    /**
     * Getting the age of an employee
     *
     * @param DOB date of birth for the age
     * @return age of corresponding employee
     */
    public int getEmployeeAge(String DOB);
    
    /**
     * Converting the String to local date format
     * 
     * @param DOB date of birth to convert to local date format
     * @return DOB in local date format
     */
    public LocalDate stringDateConversion(String DOB);
    
    /**
     * Creating the employee and storing in database
     *
     * @param id unique representation of an employee
     * @param name name of an employee
     * @param salary salary of an employee
     * @param email employee email
     * @param phoneNumber employee phoneNumber
     * @param DOB date of birth of an employee
     * @return employee created or not
     */
    public int createEmployee(EmployeeDTO employeeDto) throws EMSException;
    
    /**
     * Getting the particular employee by id in database
     *
     * @param id employee id to get
     * @return single employee details
     */
    public EmployeeDTO getSingleEmployee(int id) throws EMSException;
    
    /**
     * Getting all employee in the database
     *
     * @return All employee details
     */
    public List<EmployeeDTO> getAllEmployee() throws EMSException;
    
    /**
     * Updating the all fields of an employee 
     *
     * @param id employee id to update
     * @param name name of an employee to update
     * @param salary salary of an employee to update
     * @param email employee email to update
     * @param phoneNumber employee phoneNumber to update
     * @param DOB date of birth of an employee to update
     * @return All employee fields updated or not
     */
    public int updateAllFields(EmployeeDTO employeeDto) throws EMSException;
    
    /**
     * Deleting the required employee
     *
     * @param id id for deleting the employee
     * @return employee deleted
     */
    public int deleteSingleEmployee(int id) throws EMSException;
    
    /**
     * Deleting all employees in the database
     *
     * @return Total employees deleted
     */
    public int deleteAllEmployee() throws EMSException;
}
