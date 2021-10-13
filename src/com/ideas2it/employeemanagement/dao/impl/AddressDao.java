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
import com.ideas2it.employeemanagement.connection.DataBaseConnection;

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
}
