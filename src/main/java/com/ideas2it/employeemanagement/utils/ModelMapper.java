/*
 * Copyright (c) ideas2it.
 *
 * Date: 27/08/2021
 */
package com.ideas2it.employeemanagement.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;

/**
 * Converts employee to employeeDto and vice versa,
 * converts address to addressDto and vice versa. 
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class ModelMapper {
    /**
     *  Converts employee to employeeDto 
     */
    public static EmployeeDTO toEmployeeDto(Employee employee) {
        List<AddressDTO> addressDto = new ArrayList<>();
        EmployeeDTO employeeDto = new EmployeeDTO();
        
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setDOB(employee.getDOB());
        if (null != employee.getAddress()) {
            for (Address address : employee.getAddress()) {
                addressDto.add(toAddressDto(address));
            }
            employeeDto.setAddressDto(addressDto);
        }
        return employeeDto;
    }
   
    /**
     *  Converts address to addressDto 
     */
    public static AddressDTO toAddressDto(Address address) {
        AddressDTO addressDto = new AddressDTO();
        addressDto.setId(address.getId());
        addressDto.setAddressLine(address.getAddressLine());
        addressDto.setCity(address.getCity());
        addressDto.setPincode(address.getPincode());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }
    
    /**
     *  Converts employeeDto to employee 
     */
    public static Employee toEmployee(EmployeeDTO employeeDto) {
        List<Address> address = new ArrayList<>();
        Set<Project> projects = new HashSet<>();
        Employee employee = new Employee();
        
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setDOB(employeeDto.getDOB());
        if (null != employeeDto.getAddressDto()) {
            for (AddressDTO addressDto : employeeDto.getAddressDto()) {
                address.add(toAddress(addressDto));
            }
            employee.setAddress(address);
        }
        if (null != employeeDto.getProjectsDto()) {
            for (ProjectDTO projectDto : employeeDto.getProjectsDto()) {
                projects.add(toProject(projectDto));
            }
            employee.setProjects(projects);
        }
        
        return employee;
    } 
    
    /**
     * Converts addressDto to address
     */
    public static Address toAddress(AddressDTO addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setAddressLine(addressDto.getAddressLine());
        address.setCity(addressDto.getCity());
        address.setPincode(addressDto.getPincode());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        return address;
    }
    
    /**
     * Converts projectDto to project
     */
    public static Project toProject(ProjectDTO projectDto) {
        Project project = new Project();
        
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setManager(projectDto.getManager());
        project.setStatus(projectDto.getStatus());
        return project;
    }
    
    /**
     * Converts project to projectDto
     */
    public static ProjectDTO toProjectDto(Project project) {
        ProjectDTO projectDto = new ProjectDTO();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setManager(project.getManager());
        projectDto.setStatus(project.getStatus());
        return projectDto;
    }
}
