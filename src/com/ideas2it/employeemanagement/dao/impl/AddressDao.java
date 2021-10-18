/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.connection.DataBaseConnection;
import com.ideas2it.employeemanagement.model.Address;

public class AddressDao {
    private DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
    
    /**
     * Inserting the employee address from  user input
     * to the database
     *
     * @return database empty or not
     */
    public int insertAddress(Address address) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        String insert = "INSERT INTO employees_address VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insert);
        
        statement.setInt(1, address.getEmployeeId());
        statement.setString(2, address.getAddress());
        statement.setString(3, address.getCity());
        statement.setString(4, address.getPincode());
        statement.setString(5, address.getState());
        statement.setString(6, address.getCountry());
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
   /**
     * Getting the employee details from the database
     * by corresponding employee id 
     *
     * @return Single employee details
     */
    public List<Address> getAddress(int id) throws SQLException {
        List<Address> address = new ArrayList<>();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees_address WHERE employee_id = ?");
        
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            address.add(setAddress(resultSet));
        }
        dataBaseConnection.closeConnection();
        return address;
    }
    
    /**
     * Getting the employee details from the database
     * by corresponding employee id 
     *
     * @return Single employee details
     */
    public Address getAddressById(int id) throws SQLException {
        Address address= new Address();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees_address WHERE id = ?");
        
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            address.setId(resultSet.getInt("id"));
            address.setEmployeeId(resultSet.getInt("employee_id"));
            address.setAddress(resultSet.getString("address"));
            address.setCity(resultSet.getString("city"));
            address.setPincode(resultSet.getString("pincode"));
            address.setState(resultSet.getString("state"));
            address.setCountry(resultSet.getString("country"));
        }
        dataBaseConnection.closeConnection();
        return address;
    }
    
    /** 
     * Getting all employees details from the database 
     *
     * @return All employees and their details
     */
    public List<Address> getAllAddress() throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        List<Address> address = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees_address");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            address.add(setAddress(resultSet));
        }
        dataBaseConnection.closeConnection();
        return address;
    }
    
    /**
     * Assigning  values to respective fields from the resultSet for
     * the purpose of "viewEmployee"
     *
     * @return Total number of rows updated in database
     */
    private Address setAddress(ResultSet addressSet) throws SQLException {
        Address address= new Address();
        
        address.setId(addressSet.getInt("id"));
        address.setEmployeeId(addressSet.getInt("employee_id"));
        address.setAddress(addressSet.getString("address"));
        address.setCity(addressSet.getString("city"));
        address.setPincode(addressSet.getString("pincode"));
        address.setState(addressSet.getString("state"));
        address.setCountry(addressSet.getString("country"));
        return address;
    }
    
    /** 
     * Checking the Address already exist
     * in the database 
     *
     * @param id id of an employee
     * @return Address exist or not
     */
    public boolean isAddressExist(int id) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        boolean isExist = false;
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM employees_address" 
                                                                  + " WHERE employee_id = ?");
        
        statement.setInt(1, id);
        if (statement.executeQuery().next()) {
             isExist = true;
        }
        dataBaseConnection.closeConnection();
        return isExist;
    }
    
    /**
     * Updating all address fields in the database
     *
     * @return Total number of rows updated in database
     */
    public int updateAddressFields(Address address) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        String update = "UPDATE employees_address  SET address = ?, city = ?, pincode = ?,"
                        +"state = ?, country = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(update);
        
        statement.setString(1, address.getAddress());
        statement.setString(2, address.getCity());
        statement.setString(3, address.getPincode());
        statement.setString(4, address.getState());
        statement.setString(5, address.getCountry());
        statement.setInt(6, address.getId());
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating all address fields in the database
     *
     * @return Total number of rows updated in database
     */
    public int updateAddressField(Address address) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        String update = "UPDATE employees_address  SET address = ?, city = ?, pincode = ?,"
                        +"state = ?, country = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(update);
        
        statement.setString(1, address.getAddress());
        statement.setString(2, address.getCity());
        statement.setString(3, address.getPincode());
        statement.setString(4, address.getState());
        statement.setString(5, address.getCountry());
        statement.setInt(6, address.getId());
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    public int countAddress(int id) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int totalAddress = 0;
        
        PreparedStatement statement = connection.prepareStatement("SELECT COUNT(id) FROM employees_address "
                                                                  + "WHERE employee_id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            totalAddress = resultSet.getInt(1);
        }
        return totalAddress;
    }
    
    /**
     * Deleting particular address in the database by 
     * corresponding address id
     *
     * @return Total number of rows deleted in database
     */
    public int deleteAddress(int addressId) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employees_address"
                                                                  + " WHERE id = ?");
        statement.setInt(1, addressId);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
}
