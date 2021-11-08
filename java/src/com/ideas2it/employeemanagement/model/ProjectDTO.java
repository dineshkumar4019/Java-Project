/*
 * Copyright (c) ideas2it.
 *
 * Date: 27/08/2021
 */
package com.ideas2it.employeemanagement.model;

import java.time.LocalDate; 
import java.util.Set;

import com.ideas2it.employeemanagement.model.ProjectStatus;

/**
 * Employee fields creation and carries employee details
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class ProjectDTO {
    private int id;
    private String name;
    private String description;
    private String manager;
    private ProjectStatus status;
    private Set<EmployeeDTO> employeesDto;
    
    public ProjectDTO() {
        
    }
    
    /**
     * field value initialization
     * 
     * @param id unique representation of an address
     * @param name Name of the project
     * @param decription project decription
     * @param manager manager of the project
     * @param status of the project
     */
    public ProjectDTO(String name, String description, String manager
                    , ProjectStatus status) {
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.status = status;
    }
      
    public int getId() {
        return id;
    }
        
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
        
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getManager() {
        return manager;
    }
    
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    public ProjectStatus getStatus() {
        return status;
    }
    
    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
    
    public Set<EmployeeDTO> getEmployeesDto() {
        return employeesDto;
    }
    
    public void setEmployeesDto(Set<EmployeeDTO> employeesDto) {
        this.employeesDto = employeesDto;
    }
    
    /**
     * displaying the respective project details 
     * 
     * @return project details
     */
    public String toString() {
        return new StringBuilder().append("\n\tProect Id: ").append(getId())
                   .append("\n\tProject Name: ").append(getName())
                   .append("\n\tProject Description: ").append(getDescription())
                   .append("\n\tProject Manager : ").append(getManager())
                   .append("\n\tProject status: ").append(getStatus())
                   .toString();
    }
}
