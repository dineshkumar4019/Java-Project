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
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.service.AddressServiceInterface;
import com.ideas2it.employeemanagement.utils.ModelMapper;

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
public class AddressService implements AddressServiceInterface {
    private AddressDao addressDao = new AddressDao();
    private ModelMapper modelMapper  = new ModelMapper();
    
    /**
     * Getting the total address present in the database
     * for an employee
     *
     * @param id employee id to count addresses
     * @return total addresses
     *
    public int countAddress(int id) throws SQLException {
        return addressDao.countAddress(id);
    }
    
    /**
     * Checking an address exist in database by id
     *
     * @param id address id for checking existance
     * @return address exist or not
     *
    public boolean isAddressExist(int addressId) throws SQLException {
        return addressDao.isAddressExist(addressId);
    }
    
    /**
     * Validating the corresponding employee address  
     *
     * @param address employee address for validation
     * @return address is valid or not
     */  
    public boolean validateAddress(String address) {
        return Pattern.matches("^([A-Za-z0-9-,/])+{5,100}+$", address);
    }
    
    /**
     * Validating the corresponding employee city 
     *
     * @param city employee city for validation
     * @return city is valid or not
     */
    public boolean validateCity(String city) {
        return Pattern.matches("^[A-Za-z]+$", city);
    }
    
    /**
     * Validating the corresponding employee pincode
     *
     * @param pincode employee pincode for validation
     * @return pincode is valid or not
     */
    public boolean validatePincode(String pincode) {
        return Pattern.matches("^[0-9]{6}+$", pincode);
    }
    
    /**
     * Validating the corresponding employee state 
     *
     * @param state employee state for validation
     * @return state is valid or not
     */
    public boolean validateState(String state) {
        return Pattern.matches("^[A-Za-z]+$", state);
    }
    
    /**
     * Creating the employee and storing in database
     *
     * @param addressDto employee address details to create
     * @return total employees created
     */
    //public int insertAddress(AddressDTO addressDto) throws SQLException {
    //    return addressDao.insertAddress(modelMapper.toAddress(addressDto));
    //}
    
    /**
     * Getting the particular address by employee id
     *
     * @param id employee id to get address
     * @return single employee address details
     *
    public List<Address> getAddress(int id) throws SQLException {
        return addressDao.getAddress(id);
    }
    
    /**
     * Getting the particular address by address id
     *
     * @param id address id to get addresses
     * @return single employee details
     * 
    public AddressDTO getAddressById(int addressId) throws SQLException {
        return modelMapper.toAddressDto(addressDao.getAddressById(addressId));
    }
    
    /**
     * Getting the particular address by address id
     *
     * @param id address id to get addresses
     * @return single employee details
     */ 
    //public List<Address> getAllAddress() throws SQLException {
    //    return addressDao.getAllAddress();
    //}
    
    /**
     * Updating the all fields of an address
     *
     * @param addressDto address details to update
     * @return number of rows updated
     *
    public int updateAddressFields(AddressDTO addressDto) throws SQLException {
        return addressDao.updateAddressFields(modelMapper.toAddress(addressDto));
    }
    
    /**
     * Updating the particular field of an address
     *
     * @param addressDto address details to update
     * @return number of rows updated
     */
    //public int updateAddressField(AddressDTO addressDto) throws SQLException {
    //    return addressDao.updateAddressFields(modelMapper.toAddress(addressDto));
    //}
    
    /**
     * Deleting the required address
     *
     * @param id address id for deleting the address
     * @return total rows deleted
     *
    public int deleteAddress(int addressId) throws SQLException {
        return addressDao.deleteAddress(addressId);
    }
    */
}
