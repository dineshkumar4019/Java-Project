/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.service;

import java.util.List;
import java.util.Set;

import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;

/**
 * <h1> Project service Interface</h1>
 * Project service provides CRUD operations
 * and respective validation and implementations
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public interface ProjectServiceInterface {
    
    /**
     * Checking an project exist in database by id
     *
     * @param id project id for checking existance
     * @return project exist or not
     */
    public boolean isProjectExist(int id) throws EMSException;
    
    /**
     * Checking if projects present in database
     *
     * @return database empty or not
     */
    public long isDataBaseEmpty() throws EMSException;
    
    /**
     * Validating the corresponding project name  
     *
     * @param name project name for validation
     * @return name is valid or not
     */
    public boolean ValidateName(String name);
    
    /**
     * Validating the corresponding manager name  
     *
     * @param name manager name for validation
     * @return name is valid or not
     */
    public boolean ValidateManagerName(String manager);
    
    /*
     * Validating the corresponding project name  
     *
     * @param name project name for validation
     * @return name is valid or not
     */
    public boolean ValidateDescription(String description);
    
    /**
     * Creating the employee and storing in database
     *
     * @param employeeDto employee details to be inserted in the database
     * @return Number of rows created
     */
    public int createProject(ProjectDTO projectDto) throws EMSException;
    
    /**
     * Getting the particular project by id in the database
     *
     * @param id project id to get project details
     * @return single project details
     */
    public ProjectDTO getSingleProject(int id) throws EMSException;
    
    /**
     * Getting all projects details in the database
     *
     * @return All projects details
     */
    public List<ProjectDTO> getAllProjects() throws EMSException;
    
    /**
     * Getting all employees details in the database
     *
     * @return All employees details
     */
    public List<EmployeeDTO> getAllEmployees() throws EMSException;
    
    /**
     * Updating the all fields of an project
     *
     * @param projectDto projects details to be update
     * @return Number of rows updated
     */
    public int updateAllFields(ProjectDTO projectDto) throws EMSException;
    
    /**
     * Deleting the required project by id
     *
     * @param id id for deleting the project
     * @return Number of rows deleted
     */
    public int deleteSingleProject(int id) throws EMSException;
    
    /**
     * Deleting all projects in the database
     *
     * @return Total projects deleted
     */
    public int deleteAllProject() throws EMSException;
}
