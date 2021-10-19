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
    private int employeeId;
    private String address;
    private String city;
    private String pincode;
    private String state;
    private String country;
    
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
    public Address(int id, int employeeId,String address, String city,
                              String pincode, String state, String country) {
        this.id = id;
        this.employeeId = employeeId;
        this.address = address;
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
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
     
    /**
     * displaying the respective employee address 
     * 
     * @return empolyee address
     */
    public String toString() {
        return new StringBuilder().append("\n\tEmp Address ID:   ").append(getId())
                   .append("\n\tEmp Id: ").append("\t  " + getEmployeeId())
                   .append("\n\tEmp Address: ").append("\t  " + getAddress())
                   .append("\n\tEmp City: ").append("\t  " + getCity())
                   .append("\n\tEmp Pincode: ").append("\t  " + getPincode())
                   .append("\n\tEmp State: ").append("\t  " + getState())
                   .append("\n\tEmp Country: ").append("\t  " + getCountry())
                   .toString();
    }
}
