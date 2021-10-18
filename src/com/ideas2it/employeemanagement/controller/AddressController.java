/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.ideas2it.employeemanagement.model.AddressDTO;
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
    public boolean isAddressExist(int id) throws SQLException {
        return addressService.isAddressExist(id);
    }
    
    public int countAddress(int id) throws SQLException {
        return addressService.countAddress(id);
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
    public int createAddress(AddressDTO addressDto) throws SQLException {
        return addressService.insertAddress(addressDto);
    }
    
    /**
     * Getting the addessess by employee id
     *
     * @param id employee id to get
     * @return single employee details
     */ 
    public List getAddress(int id) throws SQLException {
        return addressService.getAddress(id);
    }
    
    /**
     * Getting the particular address by address id
     *
     * @param id employee id to get
     * @return single employee details
     */ 
    public AddressDTO getAddressById(int addressId) throws SQLException {
        return addressService.getAddressById(addressId);
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
    public int updateAddressFields(AddressDTO addressDto) throws SQLException {
        return addressService.updateAddressFields(addressDto);
    }
    
    public int updateAddressField(AddressDTO addressDto) throws SQLException {
        return addressService.updateAddressField(addressDto);
    }
    
    /**
     * Deleting the required address
     *
     * @param id id for deleting the address
     * @return rows deleted
     */
    public int deleteAddress(int addressId) throws SQLException {
        return addressService.deleteAddress(addressId);
    }
    
    /**
     * Deleting all employees in the database
     *
     * @return employees deleted 
     */
    //public int deleteAllEmployee() throws SQLException {
    //    return employeeService.deleteAllEmployee();
    //}
}
