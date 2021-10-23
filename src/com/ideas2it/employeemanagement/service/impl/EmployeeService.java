/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.service.impl;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern; 

import com.ideas2it.employeemanagement.dao.impl.EmployeeDao;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.service.EmployeeServiceInterface;
import com.ideas2it.employeemanagement.utils.ModelMapper;

/**
 * <h1> Employees service</h1>
 * Empolyee service provides CRUD operations
 * and respective validation and implementations
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class EmployeeService implements EmployeeServiceInterface {
    private EmployeeDao employeeDao = new EmployeeDao();
    private ModelMapper modelMapper  = new ModelMapper();
       
    /**
     * Getting the total employees present in the database
     *
     * @return total employees
     */
    public int getTotalEmployees() throws SQLException {
        return employeeDao.getTotalEmployees();
    }
    
    /**
     * Checking an employee exist in database by id
     *
     * @param id employee id for checking existance
     * @return employee exist or not
     */
    public boolean isEmployeeExist(int id) throws SQLException {
        return employeeDao.isEmployeeExist(id);
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
    public boolean isEmailExist(String email) throws SQLException {
        return employeeDao.getEmail(email);
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
    public boolean isPhoneNumberExist(long phoneNumber) throws SQLException {
        return employeeDao.getPhoneNumber(phoneNumber);
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
    public int createEmployee(EmployeeDTO employeeDto) throws SQLException {
        return employeeDao.insertEmployee(modelMapper.toEmployee(employeeDto));
    }
    
    /**
     * Getting the particular employee by id in the database
     *
     * @param id employee id to get employee details
     * @return single employee details
     */
    public EmployeeDTO getSingleEmployee(int id) throws SQLException {
        return modelMapper.toEmployeeDto(employeeDao.getEmployee(id));
    }
    
    /**
     * Getting all employees details in the database
     *
     * @return All employee details
     */
    public List<EmployeeDTO> getAllEmployee() throws SQLException {
        List<EmployeeDTO> employeeDto = new ArrayList<>();
        List <Employee> employeeData = employeeDao.getEmployees();
        
        for (Employee employee: employeeData) {
            employeeDto.add(modelMapper.toEmployeeDto(employee));
        }
        return employeeDto;
    }
    
    /**
     * Updating the all fields of an employee 
     *
     * @param employeeDto employee details to be update
     * @return Number of rows updated
     */
    public int updateAllFields(EmployeeDTO employeeDto) throws SQLException {
        return employeeDao.updateAllFields(modelMapper.toEmployee(employeeDto));
    }
    
    /**
     * Updating the particular fields of an employee 
     *
     * @param employeeDto employee details to be update
     * @return Number of rows updated
     */
    public int updateField(EmployeeDTO employeeDto) throws SQLException {
        return employeeDao.updateAllFields(modelMapper.toEmployee(employeeDto));
    }
    
    /**
     * Deleting the required employee by id
     *
     * @param id id for deleting the employee
     * @return Number of rows deleted
     */
    public int deleteSingleEmployee(int id) throws SQLException {
        return employeeDao.deleteEmployee(id);
    }
     
    /**
     * Deleting all employees in the database
     *
     * @return Total employees deleted
     */
    public int deleteAllEmployee() throws SQLException {
        return employeeDao.deleteAllEmployee();
    }
}
