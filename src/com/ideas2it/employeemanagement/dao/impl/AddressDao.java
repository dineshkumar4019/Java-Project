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
    public int insertAddress(int id, String address, String city, String pincode,
                             String state, String country) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        String insert = "INSERT INTO employees_address VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insert);
        
        statement.setInt(1, id);
        statement.setString(2, address);
        statement.setString(3, city);
        statement.setString(4, pincode);
        statement.setString(5, state);
        statement.setString(6, country);
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
     * @return Address exist or not
     */
    public boolean isAddressExist(int addressId, int id) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        boolean isExist = false;
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM employees_address" 
                                                                  + " WHERE id = ? AND employee_id = ?");
        
        statement.setInt(1, addressId);
        statement.setInt(2, id);
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
    public int updateAddressFields(int id, String address, String city, String pincode,
                                   String state, String country) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        String update = "UPDATE employees_address  SET address = ?, city = ?, pincode = ?,"
                        +"state = ?, country = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(update);
        
        statement.setString(1, address);
        statement.setString(2, city);
        statement.setString(3, pincode);
        statement.setString(4, state);
        statement.setString(5, country);
        statement.setInt(6, id);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee address in the database by 
     * corresponding address id
     *
     * @return Total number of rows updated in database
     */
    public int updateAddress(int addressId, String address) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees_address "
                                                                  + "SET address = ? WHERE id = ?");
        statement.setString(1, address);
        statement.setInt(2, addressId);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee city in the database by 
     * corresponding address id
     *
     * @return Total number of rows updated in database
     */
    public int updateCity(int addressId, String city) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees_address "
                                                                  + "SET city = ? WHERE id = ?");
        statement.setString(1, city);
        statement.setInt(2, addressId);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee pincode in the database by 
     * corresponding address id
     *
     * @return Total number of rows updated in database
     */
    public int updatePincode(int addressId, String pincode) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees_address "
                                                                  + "SET pincode = ? WHERE id = ?");
        statement.setString(1, pincode);
        statement.setInt(2, addressId);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee state in the database by 
     * corresponding address id
     *
     * @return Total number of rows updated in database
     */
    public int updateState(int addressId, String state) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees_address "
                                                                  + "SET state = ? WHERE id = ?");
        statement.setString(1, state);
        statement.setInt(2, addressId);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee country in the database by 
     * corresponding address id
     *
     * @return Total number of rows updated in database
     */
    public int updateCountry(int addressId, String country) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees_address "
                                                                  + "SET country = ? WHERE id = ?");
        statement.setString(1, country);
        statement.setInt(2, addressId);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
}
