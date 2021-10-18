/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.ideas2it.employeemanagement.service.impl.AddressService;

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
public class AddressController {
    private AddressService addressService = new AddressService();
    
    /**
     * Checking an address presences in database
     *
     * @param id address id for checking existance
     * @return address exist or not
     */ 
    public boolean isAddressExist(int addressId, int id) throws SQLException {
        return addressService.isAddressExist(addressId, id);
    }
    
    public boolean validateAddress(String address) {
        return addressService.validateAddress(address);
    }
    
    public boolean validateCity(String city) {
        return addressService.validateCity(city);
    }
    
    public boolean validatePincode(String pincode) {
        return addressService.validatePincode(pincode);
    }
    
    public boolean validateState(String state) {
        return addressService.validateState(state);
    }
    
    public boolean validateCountry(String country) {
        return addressService.validateAddress(country);
    }
    
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
    public int createAddress(int id, String address, String city, String pincode,
                                  String state, String country) throws SQLException {
        return addressService.insertAddress(id, address, city, pincode, state, country);
    }
    
    /**
     * Getting the particular employee by id
     *
     * @param id employee id to get
     * @return single employee details
     */ 
    public List getAddress(int id) throws SQLException {
        return addressService.getAddress(id);
    }
    
    /**
     * Getting all employee in the database
     *
     * @return All employee details
     */
    public List getAllAddress() throws SQLException {
        return addressService.getAllAddress();
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
        return addressService.updateAddressFields(id, address, city, pincode, state, country);
    }
    
    /**
     * Updating the address of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param address employee changed address
     */
    public int updateAddress(int addressId, String address) throws SQLException {
        return addressService.updateAddress(addressId, address);
    }
    
    /**
     * Updating the city of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param city employee changed city
     */
    public int updateCity(int addressId, String city) throws SQLException {
        return addressService.updateCity(addressId, city);
    }
    
    /**
     * Updating the pincode of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param pincode employee changed pincode
     */
    public int updatePincode(int addressId, String pincode) throws SQLException {
        return addressService.updatePincode(addressId, pincode);
    }
    
    /**
     * Updating the state of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param state employee changed state
     */
    public int updateState(int addressId, String state) throws SQLException {
        return addressService.updateState(addressId, state);
    }
    
    /**
     * Updating the country of the particular employee
     *
     * @param addressid addressid to get the required address
     * @param country employee changed country
     */
    public int updateCountry(int addressId, String country) throws SQLException {
        return addressService.updateCountry(addressId, country);
    }
    
    /**
     * Deleting the required employee
     *
     * @param id id for deleting the employee
     * @return employee deleted
     */
    //public int deleteSingleEmployee(int id) throws SQLException {
    //    return employeeService.deleteSingleEmployee(id);
    //}
    
    /**
     * Deleting all employees in the database
     *
     * @return employees deleted 
     */
    //public int deleteAllEmployee() throws SQLException {
    //    return employeeService.deleteAllEmployee();
    //}
}
