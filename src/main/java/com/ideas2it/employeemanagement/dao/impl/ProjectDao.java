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
import com.ideas2it.employeemanagement.dao.ProjectDaoInterface;
import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLogger;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.utils.Constants;

/**
 * <h1> Project DAO</h1>
 * Project data manipulation by inserting, updating and deleting the
 * project details from the database and displaying project records
 * from database
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class ProjectDao implements ProjectDaoInterface {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    /**
     * Inserting the project details from  user input
     * to the database
     *
     * @return Number of rows inserted
     */
    public int insertProject(Project project) throws EMSException {
        int id = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(project);
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            exception.printStackTrace();
            EMSLogger.logger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_010);
        } finally {
            session.close();
        }
        return id;
    }
    
    /**
     * Updating all project fields in the database
     *
     * @param proejct project details to update
     * @return Total number of rows updated in database
     */
    public int updateAllFields(Project project) throws EMSException {
        int projectUpdated = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        
        try {
            transaction = session.beginTransaction();
            Project newProject = (Project) session.merge(project);
            transaction.commit();
            if (newProject.getId() == project.getId()) {
                projectUpdated = 1;
            }
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EMSLogger.logger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_011);
        } finally {
            session.close();
        }
        return projectUpdated;
    }
    
    /**
     * Getting the project details from the database
     * by corresponding project id 
     *
     * @param id id of an project to get
     * @return Single project details
     */
    public Project getProject(int id) throws EMSException {
        Project project = null;
        Session session = sessionFactory.openSession();
        try {
            project = (Project) session.get(Project.class, id);
        } catch (HibernateException exception) {
            EMSLogger.logger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_012);
        } finally {
            session.close();
        }
        return project;
    }
    
    /** 
     * Getting all project details from the database 
     *
     * @return All employees and their details
     */
    public List<Project> getAllProject() throws EMSException {
        List<Project> projectList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        
        try {
            Query<Project> query = session.createQuery("FROM Project", Project.class);
            projectList = query.list();
        } catch (HibernateException exception) {
            throw new EMSException(Constants.ERROR_CODE_012);
        } finally {
            session.close();
        }
        return projectList;
    }
    
    /**
     * Deleting particular project in the database by 
     * corresponding project id
     *
     * @param id id of an project to delete
     * @return Total number of rows deleted in database
     */
    public int deleteProject(int id) throws EMSException {
        int rowsAffected = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        
        try {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("DELETE FROM Project p WHERE p.id = :id");
            query.setParameter("id", id);
            rowsAffected = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EMSLogger.logger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_013);
        } finally {
            session.close();
        }
        return rowsAffected;
    }
    
    /**
     * Deleting all projects in the database
     *
     * @return Total number of rows deleted in database
     */
    public int deleteAllProject() throws EMSException {
        int rowsAffected = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        
        try { 
            transaction = session.beginTransaction(); 
            Query<?> query = session.createQuery("DELETE FROM Project");
            rowsAffected = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EMSLogger.logger.error(exception);
            throw new EMSException(Constants.ERROR_CODE_013);
        } finally {
            session.close();
        }
        return rowsAffected;
    }
}
