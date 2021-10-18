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
     * @return database empty or not
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
     * @return Total number of rows updated in database
     */
    public int updateAllFields(Employee employee) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        String update = "UPDATE employees  SET name = ?, salary = ?, email = ?,"
                        +"phone_number = ?, DOB = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(update);
        
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
     * Updating all employee fields in the database
     *
     * @return Total number of rows updated in database
     */
    public int updateField(Employee employee) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        String update = "UPDATE employees  SET name = ?, salary = ?, email = ?,"
                        +"phone_number = ?, DOB = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(update);
        
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
     * Deleting particular employee in the database by 
     * corresponding employee id
     *
     * @return Total number of rows deleted in database
     */
    public int deleteEmployee(int id) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        int rowsAffected = 0;
        
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employees"
                                                                  + " WHERE id = ?");
        
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
    public Employee getEmployee(int id) throws SQLException {
        Employee employee = new Employee();
        
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees"
                                                                   + " WHERE id = ?");
        
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
        Connection connection = dataBaseConnection.getConnection();
        List<Employee> employees = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT employees.id AS id, employees.name AS name,"
                                                                  + " employees.salary AS salary, employees.email"
                                                                  + " AS email, employees.phone_number AS phone_number,"
                                                                  + " employees.DOB AS DOB, employees_address.id AS"
                                                                  + " address_id, employees_address.address AS address,"
                                                                  + " employees_address.city AS city, employees_address.pincode AS pincode,"
                                                                  + " employees_address.state AS state, employees_address.country AS country"
                                                                  + " FROM employees LEFT JOIN employees_address ON"
                                                                  + " employees.id = employees_address.employee_id");
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
     * @return Total number of rows updated in database
     */
    private Address setAddress(ResultSet addressSet) throws SQLException {
        Address address= new Address();
        
        address.setId(addressSet.getInt("id"));
        //address.setEmployeeId(addressSet.getInt("employee_id"));
        address.setAddress(addressSet.getString("address"));
        address.setCity(addressSet.getString("city"));
        address.setPincode(addressSet.getString("pincode"));
        address.setState(addressSet.getString("state"));
        address.setCountry(addressSet.getString("country"));
        return address;
    }
    
    /**
     * Assigning  values to respective fields from the resultSet for
     * the purpose of "viewEmployee"
     *
     * @return Total number of rows updated in database
     */
    private Employee setAllEmployee(ResultSet employeesSet) throws SQLException {
        List<Address> employeeAddress = new ArrayList<>();
        Address address = new Address(employeesSet.getInt(7), employeesSet.getInt(8)
                                      , employeesSet.getString(9), employeesSet.getString(10)
                                      , employeesSet.getString(11), employeesSet.getString(12)
                                      , employeesSet.getString(13));
        
        employeeAddress.add(address);
        Employee employee = new Employee(employeesSet.getInt(1), employeesSet.getString(2) 
                                         , employeesSet.getDouble(3), employeesSet.getString(4)
                                         , employeesSet.getLong(5), employeesSet.getDate(6).toLocalDate()
                                         , employeeAddress);
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

