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
import com.ideas2it.employeemanagement.model.Address;
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
     * @return Number of rows inserted
     */
    public int insertEmployee(Employee employee) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        int id;
        
        String insert = "INSERT INTO employees VALUES(NULL,?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, employee.getName());
        statement.setDouble(2, employee.getSalary());
        statement.setString(3, employee.getEmail());
        statement.setLong(4, employee.getPhoneNumber());
        statement.setDate(5, Date.valueOf(employee.getDOB()));
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
     * @param employee employee details to update
     * @return Total number of rows updated in database
     */
    public int updateAllFields(Employee employee) throws SQLException {
        int rowsAffected = 0;
        Connection connection = dataBaseConnection.getConnection();
        StringBuilder query = new StringBuilder();
        PreparedStatement statement;

        statement = connection.prepareStatement(query.append("UPDATE employees  SET name = ?,")
                                                     .append("salary = ?, email = ?, phone_number = ?,")
                                                     .append("DOB = ? WHERE id = ?")
                                                     .toString());
        
        statement.setString(1, employee.getName());
        statement.setDouble(2, employee.getSalary());
        statement.setString(3, employee.getEmail());
        statement.setLong(4, employee.getPhoneNumber());
        statement.setDate(5, Date.valueOf(employee.getDOB()));
        statement.setInt(6, employee.getId());
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Updating single employee fields in the database
     *
     * @param employee employee details to update
     * @return Total number of rows updated in database
     */
    //public int updateField(Employee employee) throws SQLException {
    //    int rowsAffected = 0;
    //    StringBuilder query = new StringBuilder();
    //    Connection connection = dataBaseConnection.getConnection();
    //    
    //    String update = query.append("UPDATE employees  SET name = ?, salary = ?,") 
    //                         .append("email = ?,phone_number = ?, DOB = ? WHERE id = ?")
    //                         .toString();
    //    PreparedStatement statement = connection.prepareStatement(update);
        
    //    statement.setString(1, employee.getName());
    //    statement.setDouble(2, employee.getSalary());
    //    statement.setString(3, employee.getEmail());
    //    statement.setLong(4, employee.getPhoneNumber());
    //    statement.setDate(5, Date.valueOf(employee.getDOB()));
    //    statement.setInt(6, employee.getId());
    //    rowsAffected = statement.executeUpdate();
    //    dataBaseConnection.closeConnection();
    //    return rowsAffected;
    //}
    
    /**
     * Deleting particular employee in the database by 
     * corresponding employee id
     *
     * @param id id of an employee to delete
     * @return Total number of rows deleted in database
     */
    public int deleteEmployee(int id) throws SQLException {
        int rowsAffected = 0;
        StringBuilder query = new StringBuilder();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement(query.append("DELETE FROM employees")
                                                     .append(" WHERE id = ?")
                                                     .toString());
        
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
        int rowsAffected = 0;
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement("DELETE FROM employees");
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    
    /**
     * Getting the employee details from the database
     * by corresponding employee id 
     *
     * @param id id of an employee to get
     * @return Single employee details
     */
    public Employee getEmployee(int id) throws SQLException {
        StringBuilder query = new StringBuilder();
        Employee employee = new Employee();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement(query.append("SELECT * FROM employees")
                                                     .append(" WHERE id = ?")
                                                     .toString());
        
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            employee = setEmployee(resultSet);
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
        StringBuilder query = new StringBuilder();
        Connection connection = dataBaseConnection.getConnection();
        List<Employee> employees = new ArrayList<>();
        PreparedStatement statement;
        
        statement = connection.prepareStatement(query.append("SELECT employees.id AS id, employees.name AS name,")
                                                     .append(" employees.salary AS salary, employees.email")
                                                     .append(" AS email, employees.phone_number AS phone_number,")
                                                     .append(" employees.DOB AS DOB, employees_address.id AS")
                                                     .append(" address_id, employees_address.address AS address,")
                                                     .append(" employees_address.city AS city, employees_address.pincode AS pincode,")
                                                     .append(" employees_address.state AS state, employees_address.country AS country")
                                                     .append(" FROM employees LEFT JOIN employees_address ON")
                                                     .append(" employees.id = employees_address.employee_id")
                                                     .toString());
        ResultSet resultSet = statement.executeQuery();
        List<Integer> list = new ArrayList<>();
        while (resultSet.next()) {
            if (!list.contains(resultSet.getInt("id"))) {
                list.add(resultSet.getInt("id"));
                employees.add(setEmployee(resultSet));
            }
            for (Employee employee : employees) {
                if (employee.getId() == resultSet.getInt("id")) {
                    employee.getAddress().add(setAddress(resultSet));
                }
            }
        }
        dataBaseConnection.closeConnection();
        return employees;
    }
    
    /**
     * Assigning  values to respective fields from the resultSet for
     * the purpose of "viewEmployee"
     *
     * @employyesSet employye details 
     * @return Total number of rows updated in database
     */
    private Employee setEmployee(ResultSet employeesSet) throws SQLException {
        Employee employee = new Employee();
        
        employee.setId(employeesSet.getInt(1));
        employee.setName(employeesSet.getString(2));
        employee.setSalary(employeesSet.getDouble(3));
        employee.setEmail(employeesSet.getString(4));
        employee.setPhoneNumber(employeesSet.getLong(5));
        employee.setDOB(employeesSet.getDate(6).toLocalDate());
        return employee;
    }
    
    /**
     * Assigning  values to respective fields from the resultSet for
     * the purpose of "viewEmployee"
     *
     * @addressSet employye address details 
     * @return Total number of rows updated in database
     */
    private Address setAddress(ResultSet addressSet) throws SQLException {
        Address address= new Address();
        
        address.setId(addressSet.getInt("id"));
        address.setAddress(addressSet.getString("address"));
        address.setCity(addressSet.getString("city"));
        address.setPincode(addressSet.getString("pincode"));
        address.setState(addressSet.getString("state"));
        address.setCountry(addressSet.getString("country"));
        return address;
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
        PreparedStatement statement;
        
        statement = connection.prepareStatement("SELECT COUNT(*) FROM employees");
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
     * @param phoneNumber phone number to find existance
     * @return Phone number exist or not
     */
    public boolean getPhoneNumber(long phoneNumber) throws SQLException {
        boolean isExist = false;
        StringBuilder query = new StringBuilder();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement(query.append("SELECT phone_number")
                                                     .append(" FROM employees WHERE phone_number = ?")
                                                     .toString());
        
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
     * @param email email to find existance
     * @return Email exist or not
     */
    public boolean getEmail(String email) throws SQLException {
        boolean isExist = false;
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement("SELECT email FROM employees WHERE email = ?");
        
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
     * @param id id of an employee
     * @return Employee exist or not
     */
    public boolean isEmployeeExist(int id) throws SQLException {
        boolean isExist = false;
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement("SELECT id FROM employees WHERE id = ?");
        
        statement.setInt(1, id);
        if (statement.executeQuery().next()) {
             isExist = true;
        }
        dataBaseConnection.closeConnection();
        return isExist;
    }
}

