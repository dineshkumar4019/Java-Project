/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.controller;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.impl.EmployeeService;
import com.ideas2it.employeemanagement.exception.EMSException;

/**
 * <h1> Employees controller</h1>
 * Empolyee controller controls and manipulates the CRUD
 * operations and validation
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();
    
    /**
     * Checking an employee presences in database
     *
     * @param id employee id for checking existance
     * @return employee exist or not
     */
    public boolean isEmployeeExist(int id) throws EMSException {
        return employeeService.isEmployeeExist(id);
    }
    
    /**
     * Checking if employees present in database
     *
     * @return database empty or not
     */
    public long isDataBaseEmpty() throws EMSException {
        return employeeService.isDataBaseEmpty();
    }
    
    /**
     * Checking an address presences in database
     *
     * @param id address id for checking existance
     * @return address exist or not
     */
    public boolean isAddressExist(int addressId) throws EMSException {
        return employeeService.isAddressExist(addressId);
    }
    
    /**
     * Validating the corresponding employee name  
     *
     * @param name employee name for validation
     * @return name is valid or not
     */
    public boolean validateName(String name) {
        return employeeService.validateName(name);
    }
    
    /**
     * Validating the corresponding employee salary  
     *
     * @param salary employee salary for validation
     * @return salary is valid or not
     */
    public boolean validateSalary(double salary) {
        return employeeService.validateSalary(salary);
    }
    
    /**
     * Checking the corresponding email is present in database
     *
     * @param email email for checking existence
     * @return email exist or not
     */
    public boolean isEmailExist(String email) throws EMSException {
        return employeeService.isEmailExist(email);
    }
    
    /**
     * Validating the corresponding employee email 
     *
     * @param email employee email for validation
     * @return email is valid or not
     */
    public boolean validateEmail(String email) {
        return employeeService.validateEmail(email);
    }
    
    /**
     * Checking the corresponding phone number is present in database
     *
     * @param phoneNumber phoneNumber for checking existence
     * @return phoneNumber exist or not
     */
    public boolean isPhoneNumberExist(long phoneNumber) throws EMSException {
        return employeeService.isPhoneNumberExist(phoneNumber);
    }
    
    /**
     * Validating the corresponding employee phoneNumber 
     *
     * @param phoneNumber employee phoneNumber for validation
     * @return phoneNumber is valid or not
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        return employeeService.validatePhoneNumber(phoneNumber);
    }
    
    /**
     * Validating the corresponding employee address 
     *
     * @param address employee address for validation
     * @return address is valid or not
     */  
    public boolean validateAddress(String address) {
        return employeeService.validateAddress(address);
    }
    
    /**
     * Validating the corresponding employee city 
     *
     * @param city employee city for validation
     * @return city is valid or not
     */
    public boolean validateCity(String city) {
        return employeeService.validateCity(city);
    }
    
    /**
     * Validating the corresponding employee pincode 
     *
     * @param pincode employee pincode for validation
     * @return pincode is valid or not
     */
    public boolean validatePincode(String pincode) {
        return employeeService.validatePincode(pincode);
    }
    
    /**
     * Validating the corresponding employee state 
     *
     * @param state employee state for validation
     * @return state is valid or not
     */
    public boolean validateState(String state) {
        return employeeService.validateState(state);
    }
    
    /**
     * Validating the corresponding employee country 
     *
     * @param country employee country for validation
     * @return country is valid or not
     */
    public boolean validateCountry(String country) {
        return employeeService.validateAddress(country);
    }
    
    /**
     * validate ids of projects 
     *
     * @param ids ids of projects
     * @return ids valid or not
     */
    public boolean validateIds(String[] ids) {
        return employeeService.validateIds(ids);
    }
    
    /**
     * converting the String to local date format
     * 
     * @param DOB date of birth to convert to local date format
     * @return DOB in local date format
     */
    public LocalDate stringDateConversion(String DOB) {
        return employeeService.stringDateConversion(DOB);
    }
  
    /**
     * getting the age of an employee
     *
     * @param DOB date of birth for the age
     * @return age of correcponding employee
     */
    public int getEmployeeAge(String DOB) {
        return employeeService.getEmployeeAge(DOB);
    }
    
    /**
     * Creating the employee and storing in database
     *
     * @param employeeDto employee details to be created
     * @return Number of employees added
     */
    public int createEmployee(EmployeeDTO employeeDto) throws EMSException {
        return employeeService.createEmployee(employeeDto);
    }
    
    /**
     * Creating the employee and storing in database
     *
     * @param employeeDto employee details to be created
     * @return Number of employees added
     */
    public int insertAddress(AddressDTO addressDto ) throws EMSException {
        return employeeService.insertAddress(addressDto);
    }
    
    /**
     * Getting the particular employee by id
     *
     * @param id employee id to get employee details
     * @return particular employee details
     */
    public EmployeeDTO getSingleEmployee(int id) throws EMSException {
        return employeeService.getSingleEmployee(id);
    }
    
    /**
     * Getting all projects in the database
     *
     * @return All projects details
     */
    public List<ProjectDTO> getAllProjects() throws EMSException {
        return employeeService.getAllProjects();
    }
    
    /**
     * Getting the particular address by id
     *
     * @param id address id to get employee details
     * @return particular address details
     */
    public AddressDTO getAddress(int id) throws EMSException {
        return employeeService.getAddress(id);
    }
    
    /**
     * Getting all employees in the database
     *
     * @return All employee details
     */
    public List<EmployeeDTO> getAllEmployee() throws EMSException {
        return employeeService.getAllEmployee();
    }
    
    /**
     * Updating the all fields of an employee 
     *
     * @param employeeDto employee details to be updated
     * @return Number of rows updated
     */
    public int updateAllFields(EmployeeDTO employeeDto)  throws EMSException {
        return employeeService.updateAllFields(employeeDto);
    }
    
    /**
     * Deleting the required employee
     *
     * @param id id for deleting the employee
     * @return Number of rows deleted
     */
    public int deleteSingleEmployee(int id) throws EMSException {
        return employeeService.deleteSingleEmployee(id);
    }
    
    /**
     * Deleting all employees in the database
     *
     * @return Number of rows deleted
     */
    public int deleteAllEmployee() throws EMSException {
        return employeeService.deleteAllEmployee();
    }
    
    /**
     * Deleting the required address
     *
     * @param id address id for deleting the address
     * @return total rows deleted
     */
    public int deleteAddress(int addressId) throws EMSException {
        return employeeService.deleteAddress(addressId);
    }
}
