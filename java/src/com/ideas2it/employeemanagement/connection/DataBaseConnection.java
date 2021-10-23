/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <h1> Employee database connection</h1>
 * Establishing  a connection with a database 
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class DataBaseConnection {
    private static Connection connection = null;
    private static DataBaseConnection dataBaseInstance;
    
    private DataBaseConnection() {
    }

    public static DataBaseConnection getInstance() {
        if (null == dataBaseInstance) {
            dataBaseInstance = new DataBaseConnection();
        }
        return dataBaseInstance;
    }
   
    /**
     * Creating connection with the requried database
     *
     * @return Connection with the database
     */
    public Connection getConnection() {
        if (null == connection) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees_record","root","Test@321");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    /**
     * Closing the connection with database
     *
     * @return Closed connection
     */
    public void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
