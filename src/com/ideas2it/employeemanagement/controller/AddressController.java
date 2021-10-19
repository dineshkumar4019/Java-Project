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
    public boolean isAddressExist(int addressId) throws SQLException {
        return addressService.isAddressExist(addressId);
    }
    
    /**
     * Counting addresses for an employee in database
     *
     * @param id address id to count addresses
     * @return total addresses for an employee
     */ 
    public int countAddress(int id) throws SQLException {
        return addressService.countAddress(id);
    }
    
    /**
     * Validating the corresponding employee address 
     *
     * @param address employee address for validation
     * @return address is valid or not
     */  
    public boolean validateAddress(String address) {
        return addressService.validateAddress(address);
    }
    
    /**
     * Validating the corresponding employee city 
     *
     * @param city employee city for validation
     * @return city is valid or not
     */
    public boolean validateCity(String city) {
        return addressService.validateCity(city);
    }
    
    /**
     * Validating the corresponding employee pincode 
     *
     * @param pincode employee pincode for validation
     * @return pincode is valid or not
     */
    public boolean validatePincode(String pincode) {
        return addressService.validatePincode(pincode);
    }
    
    /**
     * Validating the corresponding employee state 
     *
     * @param state employee state for validation
     * @return state is valid or not
     */
    public boolean validateState(String state) {
        return addressService.validateState(state);
    }
    
    /**
     * Validating the corresponding employee country 
     *
     * @param country employee country for validation
     * @return country is valid or not
     */
    public boolean validateCountry(String country) {
        return addressService.validateAddress(country);
    }
    
    /**
     * Creating the employee and storing in database
     *
     * @param addressDto address details to be created
     * @return Number of rows created
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
     * @param id address id to get address details
     * @return single employee address details
     */ 
    public AddressDTO getAddressById(int addressId) throws SQLException {
        return addressService.getAddressById(addressId);
    }
    
    /**
     * Getting all employee in the database
     *
     * @return All employee details
     */
    //public List getAllAddress() throws SQLException {
    //    return addressService.getAllAddress();
    //}
    
    /**
     * Updating the all fields of an address
     *
     * @param addressDto address details to update
     * @return number of rows updated
     */
    public int updateAddressFields(AddressDTO addressDto) throws SQLException {
        return addressService.updateAddressFields(addressDto);
    }
    
    /**
     * Updating the particular field of an address
     *
     * @param addressDto address details to update
     * @return number of rows updated
     */
    //public int updateAddressField(AddressDTO addressDto) throws SQLException {
     //   return addressService.updateAddressField(addressDto);
    //}
    
    /**
     * Deleting the required address
     *
     * @param id address id for deleting the address
     * @return total rows deleted
     */
    public int deleteAddress(int addressId) throws SQLException {
        return addressService.deleteAddress(addressId);
    }
}
