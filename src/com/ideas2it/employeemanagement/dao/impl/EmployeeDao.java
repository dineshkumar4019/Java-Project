/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dao.EmployeeDaoInterface;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.connection.DataBaseConnection;

/**
 * <h1> Employee DAO</h1>
 * Employees data manipulation by inserting, updating and deleting the
 * employee from the database and displaying employee records
 * from database
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class EmployeeDao implements EmployeeDaoInterface {
    private DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
    
    /**
     * Inserting the employee details from  user input
     * to the database
     *
     * @return database empty or not
     */
    public int insertEmployee(String name, double salary, String email,
                              long phoneNumber, LocalDate DOB) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        int id;
        
        String insert = "INSERT INTO employees VALUES(NULL,?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, name);
        statement.setDouble(2, salary);
        statement.setString(3, email);
        statement.setLong(4, phoneNumber);
        statement.setDate(5, Date.valueOf(DOB));
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        id = resultSet.getInt(1);
        dataBaseConnection.closeConnection();
        return id;
    }
    
    /**
     * Updating all employee fields in the database
     *
     * @return Total number of rows updated in database
     */
    public int updateAllFields(int id, String name, double salary, String email,
                               long phoneNumber, LocalDate DOB) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        String update = "UPDATE employees  SET name = ?, salary = ?, email = ?,"
                        +"phone_number = ?, DOB = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(update);
        
        statement.setString(1, name);
        statement.setDouble(2, salary);
        statement.setString(3, email);
        statement.setLong(4, phoneNumber);
        statement.setDate(5, Date.valueOf(DOB));
        statement.setInt(6, id);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee name in the database by 
     * corresponding employee id
     *
     * @return Total number of rows updated in database
     */
    public int updateName(int id, String name) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees "
                                                                  + "SET name = ? WHERE id = ?");
        statement.setString(1, name);
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee salary in the database by 
     * corresponding employee id
     *
     * @return Total number of rows updated in database
     */
    public int updateSalary(int id, Double salary) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees "
                                                                    + "SET salary = ? WHERE id = ?");
        
        statement.setDouble(1, salary);
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee email in the database by 
     * corresponding employee id
     *
     * @return Total number of rows updated in database
     */
    public int updateEmail(int id, String email) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees "
                                                                   + "SET email = ? WHERE id = ?");
        
        statement.setString(1, email);
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee phone number in the database by 
     * corresponding employee id
     *
     * @return Total number of rows updated in database
     */
    public int updatePhoneNumber(int id, long phoneNumber) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees "
                                                                   + "SET phone_number = ? WHERE id = ?");
        
        statement.setLong(1, phoneNumber);
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating employee date of birth in the database by 
     * corresponding employee id
     *
     * @return Total number of rows updated in database
     */
    public int updateDOB(int id, LocalDate DOB) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("UPDATE employees "
                                                                   + "SET DOB = ? WHERE id = ?");
        
        statement.setDate(1, Date.valueOf(DOB));
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Deleting particular employee in the database by 
     * corresponding employee id
     *
     * @return Total number of rows deleted in database
     */
    public int deleteEmployee(int id) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
        
        statement.setInt(1, id);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Deleting all employees in the database
     *
     * @return Total number of rows deleted in database
     */
    public int deleteAllEmployee() throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employees");
        rowsAffected = statement.executeUpdate();
        statement = connection.prepareStatement("ALTER TABLE employees AUTO_INCREMENT = 1");
        statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Getting the employee details from the database
     * by corresponding employee id 
     *
     * @return Single employee details
     */
    public List<Employee> getEmployee(int id) throws SQLException {
        List<Employee> employee = new ArrayList<>();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
        
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            employee.add(setEmployee(resultSet));
        }
        dataBaseConnection.closeConnection();
        return employee;
    }
    
    /** 
     * Getting all employees details from the database 
     *
     * @return All employees and their details
     */
    public List<Employee> getEmployees() throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        List<Employee> employees = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            employees.add(setEmployee(resultSet));
        }
        dataBaseConnection.closeConnection();
        return employees;
    }
    
    /**
     * Assigning  values to respective fields from the resultSet for
     * the purpose of "viewEmployee"
     *
     * @return Total number of rows updated in database
     */
    private Employee setEmployee(ResultSet employeesSet) throws SQLException {
        Employee employee = new Employee();
        
        employee.setId(employeesSet.getInt("id"));
        employee.setName(employeesSet.getString("name"));
        employee.setSalary(employeesSet.getDouble("salary"));
        employee.setEmail(employeesSet.getString("email"));
        employee.setPhoneNumber(employeesSet.getLong("phone_number"));
        employee.setDOB(employeesSet.getDate("DOB").toLocalDate());
        return employee;
    }
    
    /** 
     * Getting the total number of employees present
     * in the database
     *
     * @return Total number of employees present
     */
    public int getTotalEmployees() throws SQLException {
        int totalEmployees = 0;
        Connection connection = dataBaseConnection.getConnection();
        
        PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM employees");
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            totalEmployees = resultSet.getInt(1);
        }
        dataBaseConnection.closeConnection();
        return totalEmployees;
    }
    
    /** 
     * Checking the phone number already 
     * exist in the database 
     *
     * @return Phone number exist or not
     */
    public boolean getPhoneNumber(long phoneNumber) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        boolean isExist = false;
        
        PreparedStatement statement = connection.prepareStatement("SELECT phone_number"
                                                                  +" FROM employees WHERE phone_number = ?");
        
        statement.setLong(1, phoneNumber);
        if (statement.executeQuery().next()) {
            isExist = true;
        }
        dataBaseConnection.closeConnection();
        return isExist;
    }
    
    /** 
     * Checking the email already exist
     * in the database 
     *
     * @return Email exist or not
     */
    public boolean getEmail(String email) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        boolean isExist = false;
        
        PreparedStatement statement = connection.prepareStatement("SELECT email FROM employees WHERE email = ?");
        
        statement.setString(1, email);
        if (statement.executeQuery().next()) {
             isExist = true;
        }
        dataBaseConnection.closeConnection();
        return isExist;
    }
    
    /** 
     * Checking the employee already exist
     * in the database 
     *
     * @return Employee exist or not
     */
    public boolean isEmployeeExist(int id) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        boolean isExist = false;
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM employees WHERE id = ?");
        
        statement.setInt(1, id);
        if (statement.executeQuery().next()) {
             isExist = true;
        }
        dataBaseConnection.closeConnection();
        return isExist;
    }
}

