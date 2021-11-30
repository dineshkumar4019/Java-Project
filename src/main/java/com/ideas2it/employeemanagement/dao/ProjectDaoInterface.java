/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.dao;

import java.util.List;

import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.model.Project;

/**
 * <h1> Project DAO Interface</h1>
 * Project data manipulation by inserting, updating and deleting the
 * project details from the database and displaying project records
 * from database
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public interface ProjectDaoInterface {
    
    /**
     * Inserting the project details from  user input
     * to the database
     *
     * @return Number of rows inserted
     */
    public Project insertProject(Project project) throws EMSException;
    
    /**
     * Updating all project fields in the database
     *
     * @param proejct project details to update
     * @return Total number of rows updated in database
     */
    public int updateAllFields(Project project) throws EMSException;
    
    /**
     * Getting the project details from the database
     * by corresponding project id 
     *
     * @param id id of an project to get
     * @return Single project details
     */
    public Project getProject(int id) throws EMSException;
    
    /** 
     * Getting all project details from the database 
     *
     * @return All employees and their details
     */
    public List<Project> getAllProject() throws EMSException;
    
    /**
     * Deleting particular project in the database by 
     * corresponding project id
     *
     * @param id id of an project to delete
     * @return Total number of rows deleted in database
     */
    public int deleteProject(int id) throws EMSException;
    
    /**
     * Deleting all projects in the database
     *
     * @return Total number of rows deleted in database
     */
    public int deleteAllProject() throws EMSException;
}
