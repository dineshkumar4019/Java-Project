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

import com.ideas2it.employeemanagement.dao.AddressDaoInterface;
import com.ideas2it.employeemanagement.model.Address;

import com.ideas2it.employeemanagement.connection.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 * Connecting data base and java application and performing
 * CRUD operations in database
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class AddressDao implements AddressDaoInterface {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    /**
     * Inserting the employee address from  user input
     * to the database
     *
     * @param address employee address details
     * @return Number of rows created
     *
    public int insertAddress(Address address) throws SQLException {
        int rowsAffected = 0;
        StringBuilder query = new StringBuilder();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement(query.append("INSERT INTO employees_address ")
                                                     .append("VALUES(NULL, ?, ?, ?, ?, ?, ?)")
                                                     .toString());
        
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
    */
    
    public int insertAddress(Address address) {
        int id = 0;
        
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(address);
            //System.out.println("123");
            transaction.commit();
            //session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return id;
    }
    
   /**
     * Getting all employee address by the employee id   
     * in the database
     *
     * @param employee id to get address
     * @return Single employee addresses
     *
    public List<Address> getAddress(int id) throws SQLException {
        List<Address> address = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement(query.append("SELECT * FROM employees_address")
                                                     .append(" WHERE employee_id = ?")
                                                     .toString());
        
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            address.add(setAddress(resultSet));
        }
        dataBaseConnection.closeConnection();
        return address;
    }
    
    /**
     * Getting the employee address from the database
     * by corresponding address id 
     *
     * @param id address id to get address
     * @return Single employee address details
     *
    public Address getAddressById(int id) throws SQLException {
        Address address = new Address();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement("SELECT * FROM employees_address WHERE id = ?");
        
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            address = setAddress(resultSet);
        }
        dataBaseConnection.closeConnection();
        return address;
    }
    
    /** 
     * Getting all employees details from the database 
     *
     * @return All employees and their details
     */
    //public List<Address> getAllAddress() throws SQLException {
    //    Connection connection = dataBaseConnection.getConnection();
    //    List<Address> address = new ArrayList<>();
    //    
    //    PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees_address");
    //    ResultSet resultSet = statement.executeQuery();
    //    while (resultSet.next()) {
    //        address.add(setAddress(resultSet));
    //    }
    //    dataBaseConnection.closeConnection();
    //    return address;
    //}
    
    /**
     * Assigning  values to respective fields from the resultSet for
     * the purpose of "viewEmployee"
     *
     * @return address details
     *
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
     *
    public boolean isAddressExist(int addressId) throws SQLException {
        boolean isExist = false;
        StringBuilder query = new StringBuilder();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement(query.append("SELECT id FROM employees_address")
                                                     .append(" WHERE id = ?")
                                                     .toString());
        
        statement.setInt(1, addressId);
        if (statement.executeQuery().next()) {
             isExist = true;
        }
        dataBaseConnection.closeConnection();
        return isExist;
    }
    
    /**
     * Updating all address fields in the database
     *
     * @param address employee address details
     * @return Number of rows updated
     *
    public int updateAddressFields(Address address) throws SQLException {
        int rowsAffected = 0;
        StringBuilder query = new StringBuilder();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;

        statement = connection.prepareStatement(query.append("UPDATE employees_address  SET address = ?,")
                                                     .append("city = ?, pincode = ?, state = ?,")
                                                     .append("country = ? WHERE id = ?")
                                                     .toString());
        
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
     * Updating particular address fields in the database
     *
     * @param address employee address details
     * @return Number of rows updated
     */
    //public int updateAddressField(Address address) throws SQLException {
    //    Connection connection = dataBaseConnection.getConnection();
    //    int rowsAffected = 0;
   //     StringBuilder query = new StringBuilder();
   //     
   //     String update = "UPDATE employees_address  SET address = ?, city = ?, pincode = ?,"
   //                     +"state = ?, country = ? WHERE id = ?";
   //     PreparedStatement statement = connection.prepareStatement(update);
   //     
   //     statement.setString(1, address.getAddress());
   //     statement.setString(2, address.getCity());
   //     statement.setString(3, address.getPincode());
    //    statement.setString(4, address.getState());
   //     statement.setString(5, address.getCountry());
   //     statement.setInt(6, address.getId());
   //     rowsAffected = statement.executeUpdate();
   //     dataBaseConnection.closeConnection();
   //     return rowsAffected;
    //}
    
    /**
     * Counting the addresses for an employee by employee id
     *
     * @param id id of an employee
     * @return Total Addresses for an employee
     *
    public int countAddress(int id) throws SQLException {
        int totalAddress = 0;
        StringBuilder query = new StringBuilder();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement(query.append("SELECT COUNT(id) FROM employees_address ")
                                                     .append("WHERE employee_id = ?")
                                                     .toString());
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
     * @param id address id to delete address details
     * @return Total number of rows deleted in database
     *
    public int deleteAddress(int addressId) throws SQLException {
        int rowsAffected = 0;
        StringBuilder query = new StringBuilder();
        Connection connection = dataBaseConnection.getConnection();
        PreparedStatement statement;
        
        statement = connection.prepareStatement(query.append("DELETE FROM employees_address")
                                                     .append(" WHERE id = ?")
                                                     .toString());
        statement.setInt(1, addressId);
        rowsAffected = statement.executeUpdate();
        dataBaseConnection.closeConnection();
        return rowsAffected;
    }
    */
}
