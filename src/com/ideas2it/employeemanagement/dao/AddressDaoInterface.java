/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;

/**
 * CRUD operations between database and java application
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public interface AddressDaoInterface {
    /**
     * Inserting the employee address from  user input
     * to the database
     *
     * @param address employee address details
     * @return Number of rows created
     */
    public int insertAddress(Address address) throws SQLException;
    
   /**
     * Getting all employee address by the employee id   
     * in the database
     *
     * @param employee id to get address
     * @return Single employee addresses
     */
    public List<Address> getAddress(int id) throws SQLException;
    
    /**
     * Getting the employee address from the database
     * by corresponding address id 
     *
     * @param id address id to get address
     * @return Single employee address details
     */
    public Address getAddressById(int id) throws SQLException;

    /** 
     * Checking the Address already exist
     * in the database 
     *
     * @param id id of an employee
     * @return Address exist or not
     */
    public boolean isAddressExist(int addressId) throws SQLException;
    
    /**
     * Updating all address fields in the database
     *
     * @param address employee address details
     * @return Number of rows updated
     */
    public int updateAddressFields(Address address) throws SQLException;
    
    /**
     * Counting the addresses for an employee by employee id
     *
     * @param id id of an employee
     * @return Total Addresses for an employee
     */ 
    public int countAddress(int id) throws SQLException;
    
    /**
     * Deleting particular address in the database by 
     * corresponding address id
     *
     * @param id address id to delete address details
     * @return Total number of rows deleted in database
     */
    public int deleteAddress(int addressId) throws SQLException;
}
