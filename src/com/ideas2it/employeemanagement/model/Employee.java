/*
 * Copyright (c) ideas2it.
 *
 * Date: 27/08/2021
 */
package com.ideas2it.employeemanagement.model;

import java.lang.StringBuilder;
import java.time.LocalDate; 
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;

/**
 * Employee fields creation and carries employee details
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class Employee {
    private int id;
    private String name;
    private double salary;
    private String email;
    private long phoneNumber;
    private LocalDate DOB;
    private List<Address> address;
    
    public Employee() {
        
    }
    
    /**
     * field value initialization
     * 
     * @param id unique representation of an employee
     * @param name name of an employee
     * @param salary salary of an employee
     * @param phone number employee phone number
     * @param email employee email
     * @param DOB date of birth of an employee
     */
    public Employee(int id, String name, double salary, String email
                    , long phoneNumber, LocalDate DOB, List<Address> address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
        this.address = address;
    }
    
    /**
     * field value initialization 
     * 
     * @param id unique representation of an employee
     * @param name name of an employee
     * @param salary salary of an employee
     * @param phone number employee phone number
     * @param email employee email
     * @param DOB date of birth of an employee
     */
    public Employee(int id, String name, double salary
                   , String email, long phoneNumber, LocalDate DOB) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
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
        
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public long getPhoneNumber() {
        return phoneNumber;
    }
        
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public LocalDate getDOB() {
        return DOB;
    }
    
    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }
    
    public List<Address> getAddress() {
        return address;
    }
    
    public void setAddress(List<Address> address) {
        this.address = address;
    }
    
    /**
     * displaying the respective employee details 
     * 
     * @return empolyee details
     */
    public String toString() {
        return new StringBuilder().append("\n\tEmp Id: ").append(getId())
                   .append("\n\tEmp Name: ").append(getName())
                   .append("\n\tEmp salary: ").append(getSalary())
                   .append("\n\tEmp email: ").append(getEmail())
                   .append("\n\tEmp phone number: ").append(getPhoneNumber())
                   .append("\n\tEmp Date of birth: ").append(getDOB())
                   .append("\n\t    ").append(getAddress())
                   .toString();
    }
}
