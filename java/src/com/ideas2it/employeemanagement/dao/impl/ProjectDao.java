/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.connection.HibernateUtil;
import com.ideas2it.employeemanagement.dao.ProjectDaoInterface;
import com.ideas2it.employeemanagement.model.Project;

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
    public int insertProject(Project project) throws HibernateException {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer) session.save(project);
        transaction.commit();
        session.close();
        return id;
    }
    
    /**
     * Updating all project fields in the database
     *
     * @param proejct project details to update
     * @return Total number of rows updated in database
     */
    public int updateAllFields(Project project) throws HibernateException {
        int projectUpdated = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Project newProject = (Project) session.merge(project);
        transaction.commit();
        session.close();
        if (newProject.getId() == project.getId()) {
            projectUpdated = 1;
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
    public Project getProject(int id) throws HibernateException {
        Session session = sessionFactory.openSession();
        Project project = (Project) session.get(Project.class, id);
        session.close();
        return project;
    }
    
    /** 
     * Getting all project details from the database 
     *
     * @return All employees and their details
     */
    public List<Project> getAllProject() throws HibernateException {
        List<Project> projectList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Project");
        projectList = query.list();
        session.close();
        return projectList;
    }
    
    /**
     * Deleting particular project in the database by 
     * corresponding project id
     *
     * @param id id of an project to delete
     * @return Total number of rows deleted in database
     */
    public int deleteProject(int id) throws HibernateException {
        int rowsAffected = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Project p WHERE p.id = :id");
        query.setParameter("id", id);
        rowsAffected = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowsAffected;
    }
    
    /**
     * Deleting all projects in the database
     *
     * @return Total number of rows deleted in database
     */
    public int deleteAllProject() throws HibernateException {
        int rowsAffected = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction(); 
        Query query = session.createQuery("DELETE FROM Project");
        rowsAffected = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowsAffected;
    }
    
    /** 
     * Getting the total number of projects present
     * in the database
     *
     * @return Total number of projects present
     */
    public long getTotalProjects() throws HibernateException {
        long totalProjects = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT COUNT(*) FROM Project");
        totalProjects = (Long) query.list().get(0); 
        transaction.commit();
        session.close();
        return totalProjects;
    }
}
