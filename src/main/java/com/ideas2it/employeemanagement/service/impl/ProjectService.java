/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.impl.ProjectDao;
import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.utils.ModelMapper;
import com.ideas2it.employeemanagement.service.ProjectServiceInterface;

/**
 * <h1> Project service</h1>
 * Project service provides CRUD operations
 * and respective validation and implementations
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class ProjectService implements ProjectServiceInterface {
    private ProjectDao projectDao;
    private EmployeeService employeeService;
    
    public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public ProjectService(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	/**
     * Checking an project exist in database by id
     *
     * @param id project id for checking existance
     * @return project exist or not
     */
    public boolean isProjectExist(int id) throws EMSException {
        boolean isExist = true;
        
        if(null == projectDao.getProject(id)) {
            isExist = false;
        }
        return isExist;
    }
    
    /**
     * Checking if projects present in database
     *
     * @return database empty or not
     */
    public long isDataBaseEmpty() throws EMSException {
        return getAllProjects().size();
    }
    
    /**
     * Get and validate user input
     *
     * @param input inpur to validate
     * @param regex pattern to check input in pattern
     * @return pattern matches or not
     */
    private boolean isValidInput(String regex, String input) {
        return Pattern.matches(regex, input);
    }
    
    /**
     * Validating the corresponding project name  
     *
     * @param name project name for validation
     * @return name is valid or not
     */
    public boolean ValidateName(String name) {
        String regex = "^[a-zA-Z]{3,20}+([ ]?[a-zA-Z]{0,20})$";
        return isValidInput(regex, name);   
    }
    
    /**
     * Validating the corresponding manager name  
     *
     * @param name manager name for validation
     * @return name is valid or not
     */
    public boolean ValidateManagerName(String manager) {
        String regex = "^([A-Za-z]{3,20}+ ){1,2}+[A-Za-z]+$";
        return isValidInput(regex, manager);   
    }
    
    /*
     * Validating the corresponding project name  
     *
     * @param name project name for validation
     * @return name is valid or not
     */
    public boolean ValidateDescription(String description) {
        String regex = "^[a-zA-Z0-9 ]+{3,30}+$";
        return isValidInput(regex, description);   
    }
    
    /**
     * validate ids of employees 
     *
     * @param ids ids of employees
     * @return ids valid or not
     */
    public boolean validateIds(String[] ids) {
        boolean isValid = true;
        
        try {
            for (String id : ids) {
                Integer.parseInt(id);
            }
        } catch (NumberFormatException e) {
            System.out.println("\n\tID must be in numbers");
            isValid = false;
        }
        return isValid;
    }  
    
    /**
     * Creating the employee and storing in database
     *
     * @param employeeDto employee details to be inserted in the database
     * @return Number of rows created
     */
    public int createProject(ProjectDTO projectDto) throws EMSException {
        return projectDao.insertProject(ModelMapper.toProject(projectDto)).getId();
    }
    
    /**
     * Getting the particular project by id in the database
     *
     * @param id project id to get project details
     * @return single project details
     */
    public ProjectDTO getSingleProject(int id) throws EMSException {
        Project project = projectDao.getProject(id);
        ProjectDTO projectDto = ModelMapper.toProjectDto(project);
        return projectDto;
    }
    
    /**
     * Getting all projects details in the database
     *
     * @return All projects details
     */
    public List<ProjectDTO> getAllProjects() throws EMSException {
        List<ProjectDTO> projects = new ArrayList<>();
        ProjectDTO projectDto;
        for (Project project : projectDao.getAllProject()) {
        	projectDto = ModelMapper.toProjectDto(project);
            projects.add(projectDto);
        }
        
        return projects;
    }
    
    /**
     * Getting all employees details in the database
     *
     * @return All employees details
     */
    public List<EmployeeDTO> getAllEmployees() throws EMSException {
        return employeeService.getAllEmployee();
    }
    
    /**
     * Updating the all fields of an project
     *
     * @param projectDto projects details to be update
     * @return Number of rows updated
     */
    public int updateAllFields(ProjectDTO projectDto) throws EMSException {
        Set<Employee> set = new HashSet<>();
        Project project = ModelMapper.toProject(projectDto);
        if (null != projectDto.getEmployeesDto()) {
            for (EmployeeDTO employeeDto : projectDto.getEmployeesDto()) {
                set.add(ModelMapper.toEmployee(employeeDto));
            }
            project.setEmployees(set);
        }
        return projectDao.updateAllFields(project);
    }
    
    /**
     * Deleting the required project by id
     *
     * @param id id for deleting the project
     * @return Number of rows deleted
     */
    public int deleteSingleProject(int id) throws EMSException {
        return projectDao.deleteProject(id);
    }
    
    /**
     * Deleting all projects in the database
     *
     * @return Total projects deleted
     */
    public int deleteAllProject() throws EMSException {
        return projectDao.deleteAllProject();
    }
    
    public List<EmployeeDTO> getAvailableEmployees(ProjectDTO projectDto) throws EMSException {
        List<EmployeeDTO> employees = getAllEmployees();
        Set<EmployeeDTO> toRemove = projectDto.getEmployeesDto();
        if (null != projectDto.getEmployeesDto()) {
        	employees.removeAll(toRemove);
        }
        return employees;
    }
}
