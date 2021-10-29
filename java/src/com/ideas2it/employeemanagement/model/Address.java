/*
 * Copyright (c) ideas2it.
 *
 * Date: 27/08/2021
 */
package com.ideas2it.employeemanagement.model;
 
/**
 * Employee address field creation and carries employee address
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class Address {
    private int id;
    private String addressLine;
    private String city;
    private String pincode;
    private String state;
    private String country;
    private Employee employee;
    
    public Address() {
        
    }
    
    /**
     * field value initialization 
     * 
     * @param address employee door no 
     * @param city employee current living city
     * @param pincode corresponding city pincode
     * @param state employee current living state
     * @param country employee current living country
     */
    public Address(Employee employee, String addressLine, String city,
                              String pincode, String state, String country) {
        this.employee = employee;                      
        this.addressLine = addressLine;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public String getAddressLine() {
        return addressLine;
    }
        
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
        
    public String getPincode() {
        return pincode;
    }
    
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
        
    public void setCountry(String country) {
        this.country = country;
    }
}
