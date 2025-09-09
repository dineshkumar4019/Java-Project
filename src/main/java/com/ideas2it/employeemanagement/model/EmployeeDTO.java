/*
 * Copyright (c) ideas2it.
 *
 * Date: 27/08/2021
 */
package com.ideas2it.employeemanagement.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Employee fields creation and carries employee details
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class EmployeeDTO {
    private int id;
    private String name;
    private double salary;
    private String email;
    private long phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate DOB;
    private List<AddressDTO> addressDto;
    private Set<ProjectDTO> projectsDto;
    
    public EmployeeDTO() {
        
    }
    
     /* field value initialization 
     * 
     * @param name name of an employee
     * @param salary salary of an employee
     * @param phone number employee phone number
     * @param email employee email
     * @param DOB date of birth of an employee
     */
    public EmployeeDTO(String name, double salary
                   , String email, long phoneNumber, LocalDate DOB) {
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
    

    public List<AddressDTO> getAddressDto() {
        return addressDto;
    }
    
    public void setAddressDto(List<AddressDTO> addressDto) {
        this.addressDto = addressDto;
    }
    
    public Set<ProjectDTO> getProjectsDto() {
        return projectsDto;
    }
    
    public void setProjectsDto(Set<ProjectDTO> projectsDto) {
        this.projectsDto = projectsDto;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(id);
	}
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDTO other = (EmployeeDTO) obj;
		return id == other.id;
	}

	/**
     * displaying the respective employee details 
     * 
     * @return empolyee details
     */
    public String toString() {
        return new StringBuilder().append("\n\tEmp Id:\t\t   ").append(getId())
                   .append("\n\tEmp Name:\t   ").append(getName())
                   .append("\n\tEmp salary:\t   ").append(getSalary())
                   .append("\n\tEmp email:\t   ").append(getEmail())
                   .append("\n\tEmp phone number:  ").append(getPhoneNumber())
                   .append("\n\tEmp Date of birth: ").append(getDOB())
                   .toString();
    }
}
