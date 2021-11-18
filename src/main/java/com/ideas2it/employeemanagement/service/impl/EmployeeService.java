/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.service.impl;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.impl.EmployeeDao;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeServiceInterface;
import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.utils.ModelMapper;

/**
 * <h1> Employees service</h1>
 * Employee service provides CRUD operations
 * and respective validation and implementations
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class EmployeeService implements EmployeeServiceInterface {
    private EmployeeDao employeeDao = new EmployeeDao();

    /**
     * Checking an employee exist in database by id
     *
     * @param id employee id for checking existance
     * @return employee exist or not
     */
    public boolean isEmployeeExist(int id) throws EMSException {
        boolean isExist = true;
        
        if(null == employeeDao.getEmployee(id)) {
            isExist = false;
        }
        return isExist;
    }
    
    /**
     * Checking if employees present in database
     *
     * @return database empty or not
     */
    public long isDataBaseEmpty() throws EMSException {
        return getAllEmployee().size();
    }
    
    /**
     * Checking an address exist in database by id
     *
     * @param id address id for checking existance
     * @return address exist or not
     */
    public boolean isAddressExist(int addressId) throws EMSException {
        boolean isExist = true;
        
        if(null == employeeDao.getAddress(addressId)) {
            isExist = false;
        }
        return isExist;
    }
    
    /**
     * Validating the corresponding employee name  
     *
     * @param name employee name for validation
     * @return name is valid or not
     */
    public boolean validateName(String name) {
        return Pattern.matches("^([A-Za-z]{3,20}+ ){1,2}+[A-Za-z]+$", name);
    }
    
    /**
     * Validating the corresponding employee salary  
     *
     * @param salary employee salary for validation
     * @return salary is valid or not
     */
    public boolean validateSalary(double salary) {
        return Pattern.matches("^[0-9]*[.]?[0-9]{0,2}?$", String.valueOf(salary));
    }
    
    /**
     * Checking the corresponding email is present in database
     *
     * @param email email for checking existence
     * @return email exist or not
     */
    public boolean isEmailExist(String email) throws EMSException {
        return employeeDao.isEmailExist(email);
    }
    
    /**
     * Validating the corresponding employee email 
     *
     * @param email employee email for validation
     * @return email is valid or not
     */
    public boolean validateEmail(String email) {
        String emailFormat = "^([a-z0-9])+([_.+&*-])?[a-z0-9]+@[a-z0-9]+"
                             + ".([a-z]{2,5}).([a-z]{2,5})?$";
        return Pattern.matches(emailFormat, email);
    }
    
    /**
     * Checking the corresponding phone number is present in database
     *
     * @param phoneNumber phoneNumber for checking existence
     * @return phoneNumber exist or not
     */
    public boolean isPhoneNumberExist(long phoneNumber) throws EMSException {
        return employeeDao.isPhoneNumberExist(phoneNumber);
    }
    
    /**
     * Validating the corresponding employee phoneNumber 
     *
     * @param phoneNumber employee phoneNumber for validation
     * @return phoneNumber is valid or not
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        return Pattern.matches("^(0|[6-9][0-9]{9})$", phoneNumber);
    }
    
    /**
     * Validating the corresponding employee address  
     *
     * @param address employee address for validation
     * @return address is valid or not
     */  
    public boolean validateAddress(String address) {
        return Pattern.matches("^([A-Za-z0-9-,/])+{5,100}+$", address);
    }
    
    /**
     * Validating the corresponding employee city 
     *
     * @param city employee city for validation
     * @return city is valid or not
     */
    public boolean validateCity(String city) {
        return Pattern.matches("^[A-Za-z]+$", city);
    }
    
    /**
     * Validating the corresponding employee pincode
     *
     * @param pincode employee pincode for validation
     * @return pincode is valid or not
     */
    public boolean validatePincode(String pincode) {
        return Pattern.matches("^[0-9]{6}+$", pincode);
    }
    
    /**
     * Validating the corresponding employee state 
     *
     * @param state employee state for validation
     * @return state is valid or not
     */
    public boolean validateState(String state) {
        return Pattern.matches("^[A-Za-z]+$", state);
    }
    
    /**
     * validate ids of projects 
     *
     * @param ids ids of projects
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
     * getting the age of an employee
     *
     * @param DOB date of birth for the age
     * @return age of corresponding employee
     */
    public int getEmployeeAge(String DOB) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        LocalDate dob = LocalDate.parse(DOB, formatter);
        
        return Period.between(dob, LocalDate.now()).getYears();
    }
    
    /**
     * converting the String to local date format
     * 
     * @param DOB date of birth to convert to local date format
     * @return DOB in local date format
     */
    public LocalDate stringDateConversion(String DOB) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        LocalDate localDate = LocalDate.parse(DOB, formatter);
        
        return localDate;
    }
    
    /**
     * Creating the employee and storing in database
     *
     * @param employeeDto employee details to be inserted in the database
     * @return Number of rows created
     */
    public int createEmployee(EmployeeDTO employeeDto) throws EMSException {
        int id = employeeDao.insertEmployee(ModelMapper.toEmployee(employeeDto));
        
        return id;
    }
    
    /**
     * Creating the employee and storing in database
     *
     * @param employeeDto employee details to be inserted in the database
     * @return Number of rows created
     */
    public int insertAddress(AddressDTO addressDto) throws EMSException {
        Address address = ModelMapper.toAddress(addressDto);
        address.setEmployee(ModelMapper.toEmployee(addressDto.getEmployeeDto()));
        return employeeDao.insertAddress(address);
    }
    
    
    /**
     * Getting the particular employee by id in the database
     *
     * @param id employee id to get employee details
     * @return single employee details
     */
    public EmployeeDTO getSingleEmployee(int id) throws EMSException {
        Employee employee = employeeDao.getEmployee(id);
        EmployeeDTO employeeDto = ModelMapper.toEmployeeDto(employee);
        Set<ProjectDTO> set = new HashSet<>();
        
        for (Project project : employee.getProjects()) {
            set.add(ModelMapper.toProjectDto(project));
        }
        employeeDto.setProjectsDto(set);
        return employeeDto;
    }
    
    public AddressDTO getAddress(int id) throws EMSException {
        return ModelMapper.toAddressDto(employeeDao.getAddress(id));
    }
    
    /**
     * Getting all projects details in the database
     *
     * @return All projects details
     */
    public List<ProjectDTO> getAllProjects() throws EMSException {
        ProjectService projectService = new ProjectService();
        return projectService.getAllProjects();
    }
    
    /**
     * Getting all employees details in the database
     *
     * @return All employee details
     */
    public List<EmployeeDTO> getAllEmployee() throws EMSException {
        List<EmployeeDTO> employeeDto = new ArrayList<>();
        
        for (Employee employee: employeeDao.getEmployees()) {
            employeeDto.add(ModelMapper.toEmployeeDto(employee));
        }
        return employeeDto;
    }
    
    /**
     * Updating the all fields of an employee 
     *
     * @param employeeDto employee details to be update
     * @return Number of rows updated
     */
    public int updateAllFields(EmployeeDTO employeeDto) throws EMSException {
        Employee employee = new Employee();
        employee = ModelMapper.toEmployee(employeeDto);
        for (Address address : employee.getAddress()) {
            address.setEmployee(employee);
        }
        //if (null != employeeDto.getProjectsDto()) {
        //    for (ProjectDTO projectDto : employeeDto.getProjectsDto()) {
       //         set.add(modelMapper.toProject(projectDto));
       //     }
       //     employee.setProjects(set);
        //}
        return employeeDao.updateAllFields(employee);
    }
    
    /**
     * Deleting the required employee by id
     *
     * @param id id for deleting the employee
     * @return Number of rows deleted
     */
    public int deleteSingleEmployee(int id) throws EMSException {
        return employeeDao.deleteEmployee(id);
    }
     
    /**
     * Deleting all employees in the database
     *
     * @return Total employees deleted
     */
    public int deleteAllEmployee() throws EMSException {
        return employeeDao.deleteAllEmployee();
    }
    
    /**
     * Deleting the required address
     *
     * @param id address id for deleting the address
     * @return total rows deleted
     */
    public int deleteAddress(int addressId) throws EMSException {
        return employeeDao.deleteAddress(addressId);
    }
    
    public List<ProjectDTO> getAvailableProjects(int id) throws EMSException {
        List<ProjectDTO> projects = getAllProjects();
        EmployeeDTO employeeDto = getSingleEmployee(id);
        Set<ProjectDTO> toRemove = employeeDto.getProjectsDto();
        if (null != employeeDto.getProjectsDto()) {
        	projects.removeAll(toRemove);
        }
        return projects;
    }
}
