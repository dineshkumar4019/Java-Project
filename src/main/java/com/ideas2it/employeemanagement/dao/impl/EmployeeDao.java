/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.connection.HibernateUtil;
import com.ideas2it.employeemanagement.dao.EmployeeDaoInterface;
import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLogger;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.utils.Constants;

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
    EMSLogger EmsLogger = new EMSLogger(EmployeeDao.class);
    
    /**
     * Inserting the employee details from  user input
     * to the database
     *
     * @return Number of rows inserted
     */
    public Employee insertEmployee(Employee employee) throws EMSException {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        
        try {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EmsLogger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_001);
        } finally {
            session.close();
        }
        return employee;
    }
       
    /**
     * Updating all employee fields in the database
     *
     * @param employee employee details to update
     * @return Total number of rows updated in database
     */
    public Employee updateAllFields(Employee employee) throws EMSException {
        Employee newEmployee;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        
        try {
            transaction = session.beginTransaction();
            newEmployee = (Employee) session.merge(employee);
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EmsLogger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_003);
        } finally {
            session.close();
        }
        return newEmployee;
    }

    /**
     * Deleting particular employee in the database by 
     * corresponding employee id
     *
     * @param id id of an employee to delete
     * @return Total number of rows deleted in database
     */
    public int deleteEmployee(int id) throws EMSException {
        int rowsAffected = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        
        try {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.delete(employee);
            rowsAffected = 1;
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EmsLogger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_004);
        } finally {
            session.close();
        } 
        return rowsAffected;
    }
    
    /**
     * Deleting all employees in the database
     *
     * @return Total number of rows deleted in database
     */
    public int deleteAllEmployee() throws EMSException {
        int rowsAffected = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        
        try {
            transaction = session.beginTransaction(); 
            Query<?> query = session.createQuery("DELETE FROM Employee");
            rowsAffected = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EmsLogger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_004);
        } finally {
            session.close();
        } 
        return rowsAffected;
    }
    
    /**
     * Getting the employee details from the database
     * by corresponding employee id 
     *
     * @param id id of an employee to get
     * @return Single employee details
     */
    public Employee getEmployee(int id) throws EMSException {
        Employee employee = null;
        Session session = sessionFactory.openSession();
        
        try {
        	Query<Employee> addressQuery = session.createQuery("SELECT e FROM Employee"
        			+ " e LEFT JOIN FETCH e.address WHERE e.id=:id", Employee.class);
        	Query<Employee> projectQuery = session.createQuery("SELECT e FROM Employee"
        	        + " e LEFT JOIN FETCH e.projects WHERE e.id=:id", Employee.class);
        	addressQuery.setParameter("id", id);
        	projectQuery.setParameter("id", id);
        	employee = addressQuery.getSingleResult();
        	employee = projectQuery.getSingleResult();
        } catch (HibernateException exception) {
            EmsLogger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_006);
        } finally {
            session.close();
        }
        return employee;
    }
 
    /** 
     * Getting all employees details from the database 
     *
     * @return All employees and their details
     */
    public List<Employee> getEmployees() throws EMSException {
        List<Employee> employeeList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        
        try {
            Query<Employee> query = session.createQuery("SELECT DISTINCT e FROM Employee"
        	        + " e LEFT JOIN FETCH e.projects", Employee.class);
        	Query<Employee> addressQuery = session.createQuery("SELECT DISTINCT e FROM Employee"
        			+ " e LEFT JOIN FETCH e.address", Employee.class);
        	employeeList = query.getResultList();
        	employeeList = addressQuery.getResultList();
        } catch (HibernateException exception) {
            EmsLogger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_006);
        } finally {
            session.close();
        }
        return employeeList;
    }

    /** 
     * Checking the email already exist in the database
     * for an employee
     *
     * @param email email to check the existance
     * @return email exist or not
     */
    public Employee getEmployeeEmail(String email) throws EMSException {
        Employee employee = null;
        Session session = sessionFactory.openSession();
        
        try {
            Query<Employee> query = session.createQuery("FROM Employee WHERE email = :email", Employee.class);
            employee = (Employee) query.setParameter("email", email).uniqueResult();
        } catch (HibernateException exception) {
            EmsLogger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_008);
        } finally {
            session.close();
        }
        return employee;
    }
   
    /** 
     * Checking the phone number already exist in the database
     * for an employee
     *
     * @param phoneNumber phone number to check the existence
     * @return email exist or not
     */
    public Employee getEmployeePhoneNumber(long phoneNumber) throws EMSException {
        Employee employee = null;
        Session session = sessionFactory.openSession();
        
        try {
            Query<Employee> query = session.createQuery("FROM Employee WHERE phoneNumber = :phoneNumber", Employee.class);
            employee = (Employee) query.setParameter("phoneNumber", phoneNumber).uniqueResult();
        } catch (HibernateException exception) {
            EmsLogger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_008);
        } finally {
            session.close();
        }
        return employee;
     }
}