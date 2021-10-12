/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12 
 */
package com.ideas2it.employeemanagement.view;

import java.util.Scanner;

/**
 * <h1> Address view</h1>
 * Address view , getting the user input for CRUD operations on address
 * and view the respective  address of an employee
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
 public class AddressView {
     private Scanner scanner = new Scanner(System.in);
     
     /**
     * Selecting the respective operation by user
     * for CRUD operations on address
     */
    public void choosingOperation() {
        int userOperationChoice;
        
        do {
            System.out.println("\n\t**EMPLOYEE ADDRESS**\n\t**1.Insert 2.Display"
                                + " 3.Update 4.Delete 5.Exit**");
            userOperationChoice = getAndValidateChoice();
            
            switch(userOperationChoice) {
                case 1:
                    createAddress();
                    break;
                case 2:
                    displayAddress();
                    break;
                case 3:
                    updateAddress();
                    break;
                case 4:
                    deleteAddress();
                    break;
                case 5:
                    System.out.println("\t**Exited successfully**");
                    break;
                default :
                    System.out.println("\t**Wrong choice**\n\t**Enter choice Again**");
            }
        } while (5 != userOperationChoice);
    }
    
    /**
     * Checking the entered choice of the 
     * respective operations is valid or not
     * 
     * @return proper format of choice 
     */
    private int getAndValidateChoice() {
        boolean isValidUserChoice = false; 
        int userChoice = 0;
        
        do {        
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                isValidUserChoice = true;
            } catch (NumberFormatException e) {
                System.out.println("\n\t**Select choice between the range!!!**\n\t**Enter choice again**");
            }
        } while (!isValidUserChoice);
        return userChoice;
    }
    
    /**
     * Checking the entered employee address is valid
     * 
     * @return proper format of address
     */
    private String getAndValidateAddress() {
        boolean isvalidAddress = false;
        String name = "";
        
        while (!isvalidAddress) {
            System.out.println("Enter Door Number, St Name And Locality Name");
            name = scanner.nextLine().trim();
            
            if (employeeController.validateAddress(address)) {
                isvalidAddress = true;
            } else {
                System.out.println("\n\tEnter address correctly!!!\n\t");
            }
        }
        return address;
    }
    
    /**
     * Checking the entered city is valid
     * 
     * @return proper format of city
     */ 
    private double getAndValidateCity() {
        boolean isvalidCity = false;
        String city = "";
        
        while (!isvalidCity) {
            System.out.println("Enter Door Number, St Name And Locality Name");
            city = scanner.nextLine().trim();
            
            if (employeeController.validateAddress(city)) {
                isvalidCity = true;
            } else {
                System.out.println("\n\tEnter City name correctly!!!\n\t");
            }
        }
        return city;
    }
    
    /**
     * Checking the entered pincode for city is valid
     * 
     * @return proper format of pincode
     */ 
    private double getAndValidatePincode() {
        boolean isValidPincode = true;
        int pincode = 0;
        
        do {
            try {
                System.out.println("Enter the pincode: ");
                pincode = Double.parseDouble(scanner.nextLine());
                                
                if (employeeController.validateSalary(pincode)) {
                    isValidPincode = false;
                } else {
                    System.out.println("\n\tEnter pincode correctly!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\t**pincode must be in numbers**");
            }
        } while (isValidPincode);
        
        return pincode;
    }
    
    /**
     * Checking the entered state is valid
     * 
     * @return proper format of state
     */ 
    private double getAndValidateState() {
        boolean isvalidState = false;
        String state = "";
        
        while (!isvalidState) {
            System.out.println("Enter the State");
            state = scanner.nextLine().trim();
            
            if (employeeController.validateAddress(state)) {
                isvalidState = true;
            } else {
                System.out.println("\n\tEnter State name correctly!!!\n\t");
            }
        }
        return state;
    }
    
    /**
     * Checking the entered country is valid
     * 
     * @return proper format of country
     */ 
    private double getAndValidateCountry() {
        boolean isvalidCountry = false;
        String country = "";
        
        while (!isvalidCountry) {
            System.out.println("Enter the country");
            country = scanner.nextLine().trim();
            
            if (employeeController.validateAddress(country)) {
                isvalidCountry = true;
            } else {
                System.out.println("\n\tEnter Country name correctly!!!\n\t");
            }
        }
        return country;
    }
    
    /** 
     * Selecting the ways of displaying the Address of an employee
     * 
     */           
    private void displayAddress() {
        int ViewChoice;
        int id;
        
        if (0 == getTotalEmployees()) {
            System.out.println("\tNo employee to view");
        } else {
            System.out.println("1:Display all\n2:display by id");
            do {
                ViewChoice = getAndValidateChoice();                
                switch (ViewChoice) {
                    case 1:
                        viewAllEmployee();
                        break;
                    case 2:
                        System.out.println("Enter Employee id to view:");
                        id = getAndValidateId();
                        viewEmployee(id);
                        break;
                    default :
                        System.out.println("\t**Wrong choice**\n\t**Enter choice again**");
                }
            }  while (2 < ViewChoice);
        }
    }
