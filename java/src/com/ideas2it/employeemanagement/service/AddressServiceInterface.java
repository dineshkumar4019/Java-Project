/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.service;

import java.sql.SQLException;
import java.util.List; 

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;

/**
 * <h1> Employees service interface</h1>
 * Empolyee service provides CRUD operations
 * and respective validation.
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public interface AddressServiceInterface {
    
    /**
     * Getting the total address present in the database
     * for an employee
     *
     * @param id employee id to count addresses
     * @return total addresses
     *
    public int countAddress(int id) throws SQLException;
    
    /**
     * Checking an address exist in database by id
     *
     * @param id address id for checking existance
     * @return address exist or not
     *
    public boolean isAddressExist(int addressId) throws SQLException;
    
    /**
     * Validating the corresponding employee address  
     *
     * @param address employee address for validation
     * @return address is valid or not
     */     
    public boolean validateAddress(String address);
    
    /**
     * Validating the corresponding employee city 
     *
     * @param city employee city for validation
     * @return city is valid or not
     */
    public boolean validateCity(String city);
    
    /**
     * Validating the corresponding employee pincode
     *
     * @param pincode employee pincode for validation
     * @return pincode is valid or not
     */
    public boolean validatePincode(String pincode);
    
    /**
     * Validating the corresponding employee state 
     *
     * @param state employee state for validation
     * @return state is valid or not
     */
    public boolean validateState(String state);
    
    /**
     * Creating the employee and storing in database
     *
     * @param addressDto employee address details to create
     * @return total employees created
     */
    //public int insertAddress(AddressDTO addressDto) throws SQLException;
    
    /**
     * Getting the particular address by employee id
     *
     * @param id employee id to get address
     * @return single employee address details
     *
    public List<Address> getAddress(int id) throws SQLException;
    
    /**
     * Getting the particular address by address id
     *
     * @param id address id to get addresses
     * @return single employee details
     *
    public AddressDTO getAddressById(int addressId) throws SQLException;
    
    /**
     * Updating the all fields of an address
     *
     * @param addressDto address details to update
     * @return number of rows updated
     *
    public int updateAddressFields(AddressDTO addressDto) throws SQLException;
    
    /**
     * Deleting the required address
     *
     * @param id address id for deleting the address
     * @return total rows deleted
     *
    public int deleteAddress(int addressId) throws SQLException;
    */
}
