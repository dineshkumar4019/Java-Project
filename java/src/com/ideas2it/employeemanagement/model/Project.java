/*
 * Copyright (c) ideas2it.
 *
 * Date: 27/08/2021
 */
package com.ideas2it.employeemanagement.model;

import java.util.Set;

/**
 * Employee fields creation and carries employee details
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class Project {
    private int id;
    private String name;
    private String description;
    private String manager;
    private ProjectStatus status;
    private Set<Employee> employees;
    
    public Project() {
        
    }
    
    /**
     * field value initialization
     * 
     * @param id unique representation of an address
     * @param name Name of the project
     * @param decription project decription
     * @param manager manager of the project
     * @param 
     * @param list of empployees for the project
     */
    public Project(int id, String name, String description, String manager
                    , ProjectStatus status) {
        this.id = id;
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
    
    public Set<Employee> getEmployees() {
        return employees;
    }
    
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
