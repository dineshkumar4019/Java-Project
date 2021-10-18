/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern; 

import com.ideas2it.employeemanagement.dao.impl.AddressDao;
import com.ideas2it.employeemanagement.model.Address;

/**
 * <h1> Employees service</h1>
 * Empolyee service provides CRUD operations
 * and respective validation and implementations
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class AddressService {
    AddressDao addressDao = new AddressDao();
    /**
     * Getting the total employees present in the database
     *
     * @return total employees
     */
    //public int getTotalEmployees() throws SQLException {
    //    return employeeDao.getTotalEmployees();
    //}
    
    /**
     * Checking an address exist in database by id
     *
     * @param id address id for checking existance
     * @return address exist or not
     */ 
    public boolean isAddressExist(int addressId, int id) throws SQLException {
        return addressDao.isAddressExist(addressId, id);
    }
    
    /**
     * Validating the corresponding employee name  
     *
     * @param name employee name for validation
     * @return name is valid or not
     */     
    public boolean validateAddress(String address) {
        return Pattern.matches("^([A-Za-z0-9-,/])+{5,100}+$", address);
    }
    
    /**
     * Validating the corresponding employee salary  
     *
     * @param salary employee salary for validation
     * @return salary is valid or not
     */
    public boolean validateCity(String city) {
        return Pattern.matches("^[A-Za-z]+$", city);
    }
    
    /**
     * Checking the corresponding email is present in database
     *
     * @param email email for checking existence
     * @return email exist or not
     */
    //public boolean isEmailExist(String email) throws SQLException {
    //    return employeeDao.getEmail(email);
    //}
    
    /**
     * Validating the corresponding employee email 
     *
     * @param email employee email for validation
     * @return email is valid or not
     */
    public boolean validatePincode(String pincode) {
        return Pattern.matches("^[0-9]{6}+$", pincode);
    }
    
    /**
     * Checking the corresponding phone number is present in database
     *
     * @param phoneNumber phoneNumber for checking existence
     * @return phoneNumber exist or not
     */
    //public boolean isPhoneNumberExist(long phoneNumber) throws SQLException {
    //    return employeeDao.getPhoneNumber(phoneNumber);
    //}
    
    /**
     * Validating the corresponding employee phoneNumber 
     *
     * @param phoneNumber employee phoneNumber for validation
     * @return phoneNumber is valid or not
     */
    public boolean validateState(String state) {
        return Pattern.matches("^[A-Za-z]+$", state);
    }
    
    /**
     * getting the age of an employee
     *
     * @param DOB date of birth for the age
     * @return age of corresponding employee
     */
    //public int getEmployeeAge(String DOB) {
    //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
    //    LocalDate dob = LocalDate.parse(DOB, formatter);
        
    //    return Period.between(dob, LocalDate.now()).getYears();
    //}
    
    /**
     * converting the String to local date format
     * 
     * @param DOB date of birth to convert to local date format
     * @return DOB in local date format
     */
    //public LocalDate stringDateConversion(String DOB) {
    //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
    //    LocalDate localDate = LocalDate.parse(DOB, formatter);
        
    //    return localDate;
    //}
    
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
    public int insertAddress(int id, String address, String city, String pincode,
                             String state, String country) throws SQLException{
        return addressDao.insertAddress(id, address, city, pincode, state, country);
    }
    
    public List<Address> getAddress(int id) throws SQLException {
        return addressDao.getAddress(id);
    }
    
    public List<Address> getAllAddress() throws SQLException {
        return addressDao.getAllAddress();
    }
    
    /**
     * Updating the all fields of an address
     *
     * @param id address id to update
     * @param address address of an employee in address
     * @param city city of an employee in address
     * @param pincode pincode of an employee in address
     * @param state state of an employee in address
     * @param counrty country of an employee in address
     * @return number of rows updated
     */
    public int updateAddressFields(int id, String address, String city, String pincode,
                             String state, String country) throws SQLException {
        return addressDao.updateAddressFields(id, address, city, pincode, state, country);
    }
    
    /**
     * Updating the address of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param address employee changed address
     */
    public int updateAddress(int addressId, String address) throws SQLException {
        return addressDao.updateAddress(addressId, address);
    }
    
    /**
     * Updating the city of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param city employee changed city
     */
    public int updateCity(int addressId, String city) throws SQLException {
        return addressDao.updateCity(addressId, city);
    }
    
    /**
     * Updating the pincode of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param pincode employee changed pincode
     */
    public int updatePincode(int addressId, String pincode) throws SQLException {
        return addressDao.updatePincode(addressId, pincode);
    }
    
    /**
     * Updating the state of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param state employee changed state
     */
    public int updateState(int addressId, String state) throws SQLException {
        return addressDao.updateState(addressId, state);
    }
    
    /**
     * Updating the country of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param country employee changed country
     */
    public int updateCountry(int addressId, String country) throws SQLException {
        return addressDao.updateCountry(addressId, country);
    }
}
