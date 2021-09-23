/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern; 

import com.ideas2it.employeemanagement.model.Employee;

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
public class EmployeeService {
    public Map<Integer, Employee> employeeDetails = new HashMap<>();
    
    /**
     * Checking any empolyee  details present in database
     *
     * @return database empty or not
     */
    public boolean isDatabaseEmpty() {
        return employeeDetails.isEmpty();
    }
    
    /**
     * Checking an employee presences in database by id
     *
     * @param id employee id for checking existance
     * @return employee exist or not
     */ 
    public boolean isEmployeeExist(int id) {
        return employeeDetails.containsKey(id);
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
    public boolean isEmailExist(String email) {
        boolean isEmailExist = false;
        Employee employee;
         
        for (int id : employeeDetails.keySet() ) {
            employee = employeeDetails.get(id);
                
            if(employee.getEmail().equals(email)) {
                isEmailExist = true;
            }
        }
        return isEmailExist;
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
    public boolean isPhoneNumberExist(long phoneNumber) {
        boolean isPhoneNumberExist = false;
        Employee employee;
         
        for (int id : employeeDetails.keySet() ) {
            employee = employeeDetails.get(id);
                
            if(employee.getPhoneNumber() == phoneNumber) {
               isPhoneNumberExist = true;
            }
        }
        return isPhoneNumberExist;
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
     * @param id unique representation of an employee
     * @param name name of an employee
     * @param salary salary of an employee
     * @param email employee email
     * @param phoneNumber employee phoneNumber
     * @param DOB date of birth of an employee
     * @return employee created or not
     */
    public boolean createEmployee(int id, String name, double salary, String email,
                                  long phoneNumber, LocalDate DOB) {
        return (null == employeeDetails.put(id, new Employee(id, name, salary,
                                            email, phoneNumber, DOB)));
    }
    
    /**
     * Getting the particular employee by id in database
     *
     * @param id employee id to get
     * @return single employee details
     */
    public List<Employee> getSingleEmployee(int id) {
        List<Employee> employee = new ArrayList<>();
        
        employee.add(employeeDetails.get(id));
        return employee;
    }
    
    /**
     * Getting all employee in the database
     *
     * @return All employee details
     */
    public List<Employee> getAllEmployee() {
        return new ArrayList<>(employeeDetails.values());
        //employees.add(employeeDetails.values());
        //return employees;
    } 
    
    /**
     * Updating the all fields of an employee 
     *
     * @param id employee id to update
     * @param name name of an employee to update
     * @param salary salary of an employee to update
     * @param email employee email to update
     * @param phoneNumber employee phoneNumber to update
     * @param DOB date of birth of an employee to update
     * @return All employee fields updated or not
     */
    public boolean updateAllFields(int id, String name, double salary, String email
                                   , long phoneNumber, LocalDate DOB) {
        Employee employee = employeeDetails.get(id);
        employee.setName(name);
        employee.setSalary(salary);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setDOB(DOB);
        return true;
    }
    
    /**
     * Updating the name of the particular employee
     *
     * @param id id to get the required employee
     * @param name employee changed name
     */
    public void updateName(int id, String name) {
        Employee employee = employeeDetails.get(id);
        employee.setName(name);
    }
    
    /**
     * Updating the phoneNumber of the particular employee
     *
     * @param id id to get the required employee
     * @param phoneNumber employee changed phone number
     */
    public void updatePhoneNumber(int id, long phoneNumber) {
        Employee employee = employeeDetails.get(id);
        employee.setPhoneNumber(phoneNumber);
    }
    
    /**
     * Updating the salary of the particular employee
     *
     * @param id id to get the required employee
     * @param salary employee changed salary
     */
    public void updateSalary(int id, double salary) {
        Employee employee = employeeDetails.get(id);
        employee.setSalary(salary);
    }
    
    /**
     * Updating the email of the particular employee
     *
     * @param id id to get the required employee
     * @param email employee changed email
     */
    public void updateEmail(int id, String email) {
        Employee employee = employeeDetails.get(id);
        employee.setEmail(email);
    }
    
    /**
     * Updating the date of birth of the particular employee
     *
     * @param id id to get the required employee
     * @param DOB employee changed date of Birth
     */
    public void updateDOB(int id, LocalDate DOB) {
        Employee employee = employeeDetails.get(id);
        employee.setDOB(DOB);
    }
    
    /**
     * Deleting the required employee
     *
     * @param id id for deleting the employee
     * @return employee deleted
     */
    public boolean deleteSingleEmployee(int id) {
        return (null == employeeDetails.remove(id));
        
    }
     
    public boolean deleteAllEmployee() {
        employeeDetails.clear();
        return employeeDetails.isEmpty();
    }
}
