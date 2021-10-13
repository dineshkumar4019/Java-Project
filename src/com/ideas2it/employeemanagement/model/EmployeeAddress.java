/*
 * Copyright (c) ideas2it.
 *
 * Date: 27/08/2021
 */
package com.ideas2it.employeemanagement.model;

import java.lang.StringBuilder; 

/**
 * Employee address field creation and carries employee details
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class EmployeeAddress {
    private String address;
    private String city;
    private String pincode;
    private String state;
    private String country;
    
    public EmployeeAddress() {
        
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
    public EmployeeAddress(String address, String city, String pincode, String state, String country) {
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
    }
      
    public String getAddress() {
        return address;
    }
        
    public void setAddress(String address) {
        this.address = address;
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
