/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.connection.HibernateUtil;
import com.ideas2it.employeemanagement.dao.EmployeeDaoInterface;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Address;

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
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer) session.save(employee);
        transaction.commit();
        session.close();
        return id;
    }
    
    /**
     * Inserting the employee address details from  user input
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
        int employeeUpdated = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee newEmployee = (Employee) session.merge(employee);
        transaction.commit();
        session.close();
        if (newEmployee.getId() == employee.getId()) {
            employeeUpdated = 1;
        }
        return employeeUpdated;
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Employee e WHERE e.id = :id");
        query.setParameter("id", id);
        rowsAffected = query.executeUpdate();
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
    public int deleteAddress(int addressId) throws HibernateException {
        int rowsAffected = 0; 
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Address e WHERE e.id = :id");
        query.setParameter("id", addressId);
        rowsAffected = query.executeUpdate();        
        transaction.commit();
        session.close();
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
        Query query = session.createQuery("DELETE FROM Employee");
        rowsAffected = query.executeUpdate();
        transaction.commit();
        session.close();
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
        Employee employee = (Employee) session.get(Employee.class, id);
        session.close();
        return employee;
    }
    
    /**
     * Getting the address details from the database
     * by corresponding address id 
     *
     * @param id id of an address to get
     * @return Single address details
     */
    public Address getAddress(int addressId) throws HibernateException {
        Session session = sessionFactory.openSession();
        Address address = (Address) session.get(Address.class, addressId);
        session.close();
        return address;
    }
 
    /** 
     * Getting all employees details from the database 
     *
     * @return All employees and their details
     */
    public List<Employee> getEmployees() throws HibernateException {
        List<Employee> employeeList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Employee");
        employeeList = query.list();
        session.close();
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
        session.close();
        return totalEmployees;
    } 

    /** 
     * Checing the email already exist in the database
     * for an employee
     *
     * @param email email to check the existance
     * @return email exist or not
     */
    public boolean isEmailExist(String email) throws HibernateException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Employee WHERE email = :email");
        Employee employee = (Employee) query.setParameter("email", email).uniqueResult();
        session.close();
        return (null != employee) ? true : false;
   }
   
    /** 
     * Checing the phonenumber already exist in the database
     * for an employee
     *
     * @param phoneNumber phone number to check the existance
     * @return email exist or not
     */
   public boolean isPhoneNumberExist(long phoneNumber) throws HibernateException {
      Session session = sessionFactory.openSession();
      Query query = session.createQuery("FROM Employee WHERE phoneNumber = :phoneNumber");
      Employee employee = (Employee) query.setParameter("phoneNumber", phoneNumber).uniqueResult();
      session.close();
      return (null != employee) ? true : false;
   }
}

