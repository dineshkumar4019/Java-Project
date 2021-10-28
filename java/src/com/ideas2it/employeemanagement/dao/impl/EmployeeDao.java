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
import com.ideas2it.employeemanagement.connection.HibernateUtil;


import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.Iterator;

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
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    /**
     * Inserting the employee details from  user input
     * to the database
     *
     * @return Number of rows inserted
     */
    public int insertEmployee(Employee employee) throws HibernateException {
        List<Address> list = new ArrayList<>();
        int id = 0;
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
            //for (Address address: employee.getAddress()) {
            //    address.setEmployee(employee);
            //    list.add(address);
            //}
            //employee.setAddress(list);
        id = (Integer) session.save(employee);
        transaction.commit();
        session.close();
        return id;
    }
    
    
    /**
     * Inserting the employee details from  user input
     * to the database
     *
     * @return Number of rows inserted
     */
    public int insertAddress(Address address) throws HibernateException {
        int id = 0;
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer) session.save(address);
        transaction.commit();
        session.close();
        return id;
    }
    
   
    /**
     * Updating all employee fields in the database
     *
     * @param employee employee details to update
     * @return Total number of rows updated in database
     */
    public int updateAllFields(Employee employee) throws HibernateException {
        int rowsAffected = 0;
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(employee);
        transaction.commit();
        session.close();
        return rowsAffected;
    }
    
    /**
     * Updating all employee fields in the database
     *
     * @param employee employee details to update
     * @return Total number of rows updated in database
     */
    public int updateAddressFields(Address address) throws HibernateException {
        int rowsAffected = 0;
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //Employee employeeDetails = session.load(Employee.class, address.getEmployee());
            //address.setEmployee(employeeDetails);
            //employeeDetails.getAddress.add(address);
        session.update(address);
        transaction.commit();
        session.close();
        return rowsAffected;
    }
    
    /**
     * Deleting particular employee in the database by 
     * corresponding employee id
     *
     * @param id id of an employee to delete
     * @return Total number of rows deleted in database
     */
    public int deleteEmployee(int id) throws HibernateException {
        int rowsAffected = 0;
        Employee employee = null;
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        employee = session.get(Employee.class, id);
        session.delete(employee);         
        transaction.commit();
        return rowsAffected;
    }
    
    /**
     * Deleting particular employee in the database by 
     * corresponding employee id
     *
     * @param id id of an employee to delete
     * @return Total number of rows deleted in database
     */
    public int deleteAddress(int addressId) throws HibernateException {
        int rowsAffected = 0;
        Address address = null;
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        address = session.get(Address.class, addressId);
        session.delete(address);         
        transaction.commit();
        return rowsAffected;
    }
    
    /**
     * Deleting all employees in the database
     *
     * @return Total number of rows deleted in database
     */
    public int deleteAllEmployee() throws HibernateException {
        int rowsAffected = 0;
       
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction(); 
            //String hql = String.format("delete from ",myTable);
        Query query = session.createQuery("delete from Employee");
        query.executeUpdate();
            //Query deleteEmployee = session.createQuery("delete Employee");
            //rowsAffected = deleteEmployee.executeUpdate();   
        transaction.commit();
        return rowsAffected;
    }
    
    /**
     * Getting the employee details from the database
     * by corresponding employee id 
     *
     * @param id id of an employee to get
     * @return Single employee details
     */
    public Employee getEmployee(int id) throws HibernateException {
        Session session = sessionFactory.openSession();
        Employee employee = null;
        
            //Query query = session.createQuery("FROM Employee");
        employee = (Employee)session.get(Employee.class, id);
        return employee;
    }
    
    public Address getAddress(int addressId) throws HibernateException {
        Address address = null;
        Session session = sessionFactory.openSession();
        
            //Query query = session.createQuery("FROM Employee");
        address = (Address) session.get(Address.class, addressId);
        return address;
    }
    
    /*
        Employee employee = null;
        List<Employee> employeeList = new ArrayList<>();
        List<Address> addressList = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query1 = session.createQuery("FROM Employee e WHERE e.id = :id");
            query1.setParameter("id", id);
            employeeList = query1.getResultList();
            //Query query2 = session.createQuery("SELECT a FROM Address a WHERE a.employee_id = :employee_id", Address.class);
            //query2.setParameter("employee_id", id);
            //addressList = query2.getResultList();
            for (Employee employees : employeeList) {
                for (Address address : addressList) {
                    System.out.println(address);
                    addresses.add(address);
                }
                employees.setAddress(addresses);
            }           
            transaction.commit();
            for (Employee employeeDetails : employeeList) {
                employee = employeeDetails;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return employeeList.get(0);
    }
    /** 
     * Getting all employees details from the database 
     *
     * @return All employees and their details
     */
    public List<Employee> getEmployees() throws HibernateException {
        List<Employee> employeeList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Employee employee = null;
        
        Query query = session.createQuery("FROM Employee");
        employeeList = query.list();
        for (Employee emp: employeeList) {
            System.out.println(emp.getAddress());
        }
        return employeeList;
    }
    
    
    /** 
     * Getting the total number of employees present
     * in the database
     *
     * @return Total number of employees present
     */
    public long getTotalEmployees() throws HibernateException {
        long totalEmployees = 0;
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT COUNT(*) FROM Employee");
        totalEmployees = (Long) query.list().get(0); 
        transaction.commit();
        return totalEmployees;
    }

    public boolean isEmailExist(String email) throws HibernateException {
      Session session = sessionFactory.openSession();
      Employee employee = null;
      
      Query query = session.createQuery("FROM Employee WHERE email = :email");
      employee = (Employee) query.setParameter("email", email).uniqueResult();
      return (null != employee) ? true : false;
   }
   
   public boolean isPhoneNumberExist(long phoneNumber) throws HibernateException {
      Session session = sessionFactory.openSession();
      Employee employee = null;
      
      Query query = session.createQuery("FROM Employee WHERE phoneNumber = :phoneNumber");
      employee = (Employee) query.setParameter("phoneNumber", phoneNumber).uniqueResult();
      return (null != employee) ? true : false;
   }
}

