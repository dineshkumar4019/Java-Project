/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.controller;

import java.util.List;
import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.service.impl.ProjectService;

/**
 * <h1> Project controller</h1>
 * Project controller controls and manipulates the CRUD
 * operations and validation
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class ProjectController {
    private ProjectService projectService = new ProjectService();
    
    /**
     * Checking an project presences in database
     *
     * @param id project id for checking existance
     * @return project exist or not
     */
    public boolean isProjectExist(int id) throws EMSException {
        return projectService.isProjectExist(id);
    }
    
    /**
     * Checking if projects present in database
     *
     * @return database empty or not
     */
    public long isDataBaseEmpty() throws EMSException {
        return projectService.isDataBaseEmpty();
    }
    
    /**
     * Validating the corresponding project name  
     *
     * @param name project name for validation
     * @return name is valid or not
     */
    public boolean validateName(String name) {
        return projectService.ValidateName(name);
    }
    
    /**
     * Validating the corresponding project manager name  
     *
     * @param manager project manager name for validation
     * @return name is valid or not
     */
    public boolean validateManagerName(String manager) {
        return projectService.ValidateManagerName(manager);
    }
    
    /**
     * Validating the corresponding project description  
     *
     * @param description project name for validation
     * @return description is valid or not
     */
    public boolean isValidateDescription(String description) {
        return projectService.ValidateDescription(description);
    }
    
    /**
     * validate ids of employees 
     *
     * @param ids ids of employees
     * @return ids valid or not
     */
    public boolean validateIds(String[] ids) {
        return projectService.validateIds(ids);
    }
    
    /**
     * Creating new project and storing in database
     *
     * @param projectDto project details to be created
     * @return Number of projects added
     */
    public int createProject(ProjectDTO projectDto) throws EMSException {
        return projectService.createProject(projectDto);
    }
    /**
     * Getting the particular project by id
     *
     * @param id project id to get project details
     * @return particular project details
     */
    public ProjectDTO getSingleProject(int id) throws EMSException {
        return projectService.getSingleProject(id);
    }
    
    /**
     * Getting all projects in the database
     *
     * @return All projects details
     */
    public List<ProjectDTO> getAllProjects() throws EMSException {
        return projectService.getAllProjects();
    }
    
    /**
     * Getting all employees in the database
     *
     * @return All employees details
     */
    public List<EmployeeDTO> getAllEmployees() throws EMSException {
        return projectService.getAllEmployees();
    }
    
    /**
     * Updating the all fields of an project
     *
     * @param projectDto project details to be updated
     * @return Number of rows updated
     */
    public int updateAllFields(ProjectDTO projectDto)  throws EMSException {
        return projectService.updateAllFields(projectDto);
    }
    
    /**
     * Deleting the required project
     *
     * @param id id for deleting the project
     * @return Number of rows deleted
     */
    public int deleteSingleProject(int id) throws EMSException {
        return projectService.deleteSingleProject(id);
    }
    
    /**
     * Deleting all projects in the database
     *
     * @return Number of rows deleted
     */
    public int deleteAllProject() throws EMSException {
        return projectService.deleteAllProject();
    }
}
