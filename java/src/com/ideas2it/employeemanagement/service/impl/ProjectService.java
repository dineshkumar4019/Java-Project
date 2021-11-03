/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;
import org.hibernate.HibernateException;

import com.ideas2it.employeemanagement.dao.impl.ProjectDao;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.utils.ModelMapper;
import com.ideas2it.employeemanagement.service.impl.EmployeeService;

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
public class ProjectService {
    private ProjectDao projectDao = new ProjectDao();
    private ModelMapper modelMapper  = new ModelMapper();
    
    /**
     * Checking an project exist in database by id
     *
     * @param id project id for checking existance
     * @return project exist or not
     */
    public boolean isProjectExist(int id) throws HibernateException {
        boolean isExist = true;
        
        if(null == projectDao.getProject(id)) {
            isExist = false;
        }
        return isExist;
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
     * Creating the employee and storing in database
     *
     * @param employeeDto employee details to be inserted in the database
     * @return Number of rows created
     */
    public int createProject(ProjectDTO projectDto) throws HibernateException {
        return projectDao.insertProject(modelMapper.toProject(projectDto));
    }
    
    /**
     * Getting the particular project by id in the database
     *
     * @param id project id to get project details
     * @return single project details
     */
    public ProjectDTO getSingleProject(int id) throws HibernateException {
        Project project = projectDao.getProject(id);
        ProjectDTO projectDto = modelMapper.toProjectDto(project);
        Set<EmployeeDTO> set = new HashSet<>();
        
        for (Employee employee : project.getEmployees()) {
            set.add(modelMapper.toEmployeeDto(employee));
        }
        projectDto.setEmployeesDto(set);
        return projectDto;
    }
    
    /**
     * Getting all projects details in the database
     *
     * @return All projects details
     */
    public List<ProjectDTO> getAllProjects() throws HibernateException {
        List<ProjectDTO> projects = new ArrayList<>();
        Set<EmployeeDTO> employees = new HashSet<>();
        
        for (Project project : projectDao.getAllProject()) {
            projects.add(modelMapper.toProjectDto(project));
        }
        
        return projects;
    }
    
    /**
     * Getting all employees details in the database
     *
     * @return All employees details
     */
    public List<EmployeeDTO> getAllEmployees() throws HibernateException {
        EmployeeService employeeService = new EmployeeService();
        return employeeService.getAllEmployee();
    }
    
    /**
     * Updating the all fields of an project
     *
     * @param projectDto projects details to be update
     * @return Number of rows updated
     */
    public int updateAllFields(ProjectDTO projectDto) throws HibernateException {
        Set<Employee> set = new HashSet<>();
        Project project = modelMapper.toProject(projectDto);
        if (null != projectDto.getEmployeesDto()) {
            for (EmployeeDTO employeeDto : projectDto.getEmployeesDto()) {
                set.add(modelMapper.toEmployee(employeeDto));
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
    public int deleteSingleProject(int id) throws HibernateException {
        return projectDao.deleteProject(id);
    }
    
    /**
     * Deleting all projects in the database
     *
     * @return Total projects deleted
     */
    public int deleteAllProject() throws HibernateException {
        return projectDao.deleteAllProject();
    }
    
    /**
     * Getting the total projects present in the database
     *
     * @return total projects
     */
    public long getTotalProjects() throws HibernateException {
        return projectDao.getTotalProjects();
    }
    
}
