/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12 
 */
package com.ideas2it.employeemanagement.view;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.controller.EmployeeController;

/**
 * <h1> Employees view</h1>
 * Empolyee view , getting the user input for 
 * CRUD operations and views the required information of an employee
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    
    /**
     * Selecting the respective operation by user
     */
    public void userOperationSelecting() {
        int userOperationChoice;git commit -m "initial commit"
        
        do {
            System.out.println("\n\t**1.Insert 2.Display 3.Update 4.Delete 5.Exit**");
            userOperationChoice = getAndValidateChoice();
            
            switch(userOperationChoice) {Z
                case 1:
                    createEmployee();
                    break;
                case 2:
                    viewEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    System.out.println("\t**Exited successfully**");
                    break;
                default :
                    System.out.println("\t**Wrong choice**\n\t**Enter choice Again**");
            }
        } while(5 != userOperationChoice);
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
            } catch(NumberFormatException e) {
                System.out.println("\n\t**Select choice between the range!!!**\n\t**Enter choice again**");
            }
        } while(!isValidUserChoice);
        return userChoice;
    }
    
    /**
     * Checking the entered employee id NOT present 
     * in database and validating the new id
     *
     * @return proper format of id
     */
    private int getAndValidateNewId() {
        boolean isValidId = false;
        int id = 0;
        
        while(!isValidId) {
            try {
                System.out.println("Enter the id: ");
                id = Integer.parseInt(scanner.nextLine());
                
                if (!employeeController.isEmployeeExist(id)) {                
                    if(0 < id) {
                        isValidId = true;
                    } else {
                        System.out.println("\n\t**ID must be greater than 0**\n\t**Enter again");
                    }
                } else {
                    System.out.println("Employee id already exits");
                }
            } catch(NumberFormatException e) {
                System.out.println("\n\t**ID must be in numbers**\n\t**Enter again**");
            }
        }
        return id;
    }
    
    /**
     * Checking the employee id present in database 
     * and validating the existing id
     * 
     * @return proper format of id
     */
    private int getAndValidateExistingId() {
        boolean isValidId = false;
        int id = 0;
        
        while(!isValidId) {
        
            try {
                id = Integer.parseInt(scanner.nextLine());
                
                if(!employeeController.isEmployeeExist(id)) {
                    System.out.println("\n\tEmpoloyee id doesn't exist\n\t**Enter id again**");
                } else {
                    isValidId = true;
                }
            } catch(NumberFormatException e) {
                System.out.println("\n\tID must be in numbers\n\t**Enter id Again**");
            }
        }
        return id;
    }
    
    /**
     * Checking the entered employee name is valid
     * 
     * @return proper format of name
     */
    private String getAndValidateName() {
        boolean isvalidName = false;
        String name = "";
        
        while (!isvalidName) {
            System.out.println("Enter the firstname and lastname: ");
            name = scanner.nextLine().trim();
            
            if(employeeController.validateName(name)) {
                isvalidName = true;
            } else {
                System.out.println("\n\tEnter name correctly!!!\n\t**Name must be in alphabets only..** ");
            }
        }
        return name;
    }
    
    /**
     * Checking the entered employee salary is valid
     * 
     * @return proper format of salary
     */ 
    private double getAndValidateSalary() {
        boolean isValidSalary = true;
        double salary = 0;
        
        do {
            try {
                System.out.println("Enter the salary: ");
                salary = Double.parseDouble(scanner.nextLine());
                
                if(0 < salary) {
                
                    if(employeeController.validateSalary(salary)) {
                        isValidSalary = false;
                    } else {
                        System.out.println("\n\tEnter salary correctly!!!");
                    }
                } else {
                    System.out.println("\n\t**salary must not be 0**\n\t**salary must be in numbers**");
                }
            } catch(NumberFormatException e) {
                System.out.println("\n\t**salary must be in numbers**");
            }
        } while(isValidSalary);
        
        return salary;
    }
    
    /**
     * Checking the entered employee email is valid
     * 
     * @return proper format of email
     */ 
    private String getAndValidateEmail() {
        boolean isvalidEmail = false;
        String email = "";
        
        while (!isvalidEmail) {
            System.out.println("Enter the email: ");
            email = scanner.nextLine().trim();
            
            if(!employeeController.isEmailExist(email)) {
                if(employeeController.validateEmail(email)) {
                    isvalidEmail = true;
                } else {
                    System.out.println("\n\t**Enter Email in correct format !!!**");
                }
            } else {
                System.out.println("\n\t**Entered email already exist**");
            }
        }
        return email;
    }
    
    /**
     * Checking the entered employee phone number is valid
     * 
     * @return proper format of phone number
     */ 
    private long getAndValidatePhoneNumber() {
        boolean isValidPhoneNumber = true;
        String phoneNumber = "";
        
        do {
            System.out.println("Enter the phone number: ");
            phoneNumber = scanner.nextLine().trim();
            if(employeeController.validatePhoneNumber(phoneNumber)) {
            
                if(!employeeController.isPhoneNumberExist(Long.valueOf(phoneNumber))) {
                    isValidPhoneNumber = false;
                } else {
                    System.out.println("\n\t**Entered number already exist**");
                }
            } else {
                System.out.println("\n\tEnter phone number correctly!!!\n\t*PhoneNumber must be 10 numbers*");
            }
        } while(isValidPhoneNumber);
        
        return Long.valueOf(phoneNumber);
    }
    
    /**
     * checking the employee eligibilty
     * 
     * @param DOB date of birth for checking eligibility
     * @return Eligible or not
     */
    private LocalDate getAndValidateDOB() {
        String DOB = "";
        boolean isValidDOB = false;
        
        while(!isValidDOB) {
        
            try {
                System.out.print("Please enter date of birth in DD-MM-YYYY: ");
                DOB = scanner.nextLine();
            
                if ((18 <= employeeController.getEmployeeAge(DOB))
                        && (60 >= employeeController.getEmployeeAge(DOB))) {
                    isValidDOB = true;
                } else {
                    System.out.println("\t**Employee age must be above 18 and below 60**");
                }                   
            } catch (DateTimeParseException e) {
                System.out.println("\t**invalid format**");
            }
        }
        return employeeController.dateConversion(DOB);
    }
    
    /** 
     * Selecting the ways of displaying the employee
     * 
     */           
    private void viewEmployee() {
        int employeeViewChoice;
        int id;
        
        if (employeeController.isDatabaseEmpty()) {
            System.out.println("\tNo employee to view");
        } else {
            System.out.println("1:Display all\n2:display by id");
            do {
                employeeViewChoice = getAndValidateChoice();                
                switch (employeeViewChoice) {
                    case 1:
                        viewAllEmployee();
                        break;
                    case 2:
                        System.out.println("Enter Employee id to view:");
                        id = getAndValidateExistingId();
                        viewUniqueEmployee(id);
                        break;
                    default :
                        System.out.println("\t**Wrong choice**\n\t**Enter choice again**");
                }
            }  while (2 < employeeViewChoice);
        }
    }
    
    /**
     * displaying the particular employee
     * 
     * @param id employee id to view
     */
    private void viewUniqueEmployee(int id) {
        System.out.println(employeeController.getSingleEmployee(id));
    }
    
    /**
     * displaying the entire employees
     */
    private void viewAllEmployee() {
        for (Object entry: employeeController.getAllEmployee()) {
            System.out.println(entry);
        }
    }
    
    /**
     * Creating employee and Storing in the database
     * 
     */
    private void createEmployee() {
        int id = getAndValidateNewId();
        String name = getAndValidateName();
        double salary = getAndValidateSalary();
        String email = getAndValidateEmail();
        long phoneNumber = getAndValidatePhoneNumber();
        LocalDate DOB = getAndValidateDOB();
        
        if(employeeController.createEmployee(id, name, salary, email, phoneNumber, DOB)) {
            System.out.println("\n\t***Details created Successfully***");
        } else {
            System.out.println("\tDetails not created!!!!");
        }
   }
    
    /**
     * Selecting the ways of updating the fields
     * and getting the employee id to update
     * 
     */
    private void updateEmployee() {
        int employeeUpdateChoice = 0;
        int id;
        
        if (employeeController.isDatabaseEmpty()) {
            System.out.println("No employees to update");
        } else {
            System.out.println("Enter the employee id to update: ");
            id = getAndValidateExistingId();
            System.out.println("1:Update all\n2:Update specific field");
            do {
                employeeUpdateChoice = getAndValidateChoice();                
                switch (employeeUpdateChoice) {
                    case 1:
                        updateAllFields(id);
                        break;
                    case 2:
                        updateField(id);
                        break;
                    default :
                        System.out.println("\t**Wrong choice**\n\t**Enter choice again**");
                }
            } while (2 < employeeUpdateChoice);
        }
    }
    
    /**
     * Updating all fields of an employee
     *
     * @param id employee id to update
     */
    private void updateAllFields(int id) {
        String name = getAndValidateName();
        double salary = getAndValidateSalary();
        String email = getAndValidateEmail();
        long phoneNumber = getAndValidatePhoneNumber();
        LocalDate DOB = getAndValidateDOB();
        if(employeeController.updateAllFields(id, name, salary, email, phoneNumber, DOB)) {
            System.out.println("\n\t***Details updated Successfully***");
        } else {
            System.out.println("\n\t***Details not updated***");
        }
    }
    
    /**
     * Updating the particular field of an employee
     * 
     * @param id employee id to update
     */
    private void updateField(int id) {
        double salary;
        long phoneNumber;
        int employeeField;
        String name;
        String email;
        LocalDate DOB;
        
        System.out.println("Select field to update\n1:Name\n2:Salary\n3:Email\n4:Phone\n5:DOB");
        do {
            employeeField = getAndValidateChoice();
            switch (employeeField) {
                case 1:
                    name = getAndValidateName();
                    employeeController.updateName(id, name);
                    break;
                case 2:
                    salary = getAndValidateSalary();
                    employeeController.updateSalary(id, salary);
                    break;
                case 3:
                    email = getAndValidateEmail();
                    employeeController.updateEmail(id, email);
                    break;
                case 4:
                    phoneNumber = getAndValidatePhoneNumber();
                    employeeController.updatePhoneNumber(id, phoneNumber);
                    break;
                case 5:
                    DOB = getAndValidateDOB();
                    employeeController.updateDOB(id, DOB);
                    break;
                default:
                    System.out.println("\n\t**Wrong field**\n\t**Select the field between the range");
            }
        } while(5 < employeeField);
    }
    
    private void deleteEmployee() {
        int employeeDeleteChoice;
        int id;
        
        if (employeeController.isDatabaseEmpty()) {
            System.out.println("\tNo employee to delete");
        } else {
            System.out.println("1:Delete all\n2:Delete by id");
            do {
                employeeDeleteChoice = getAndValidateChoice();                
                switch (employeeDeleteChoice) {
                    case 1:
                        deleteAllEmployee();
                        break;
                    case 2:
                        System.out.println("Enter Employee id to delete:");
                        id = getAndValidateExistingId();
                        deleteSingleEmployee(id);
                        break;
                    default :
                        System.out.println("\t**Wrong choice**\n\t**Enter choice again**");
                }
            }  while (2 < employeeDeleteChoice);
        }
    }
    
    /**
     * Deleting the required employee by id
     *
     * @param id id for deleting the employees
     * @return employee deleted
     */
    private void deleteSingleEmployee(int id) {
        if(!employeeController.deleteSingleEmployee(id)) {
            System.out.println("Deleted succesfully");
        } else {
            System.out.println("Employee not deleted");
        }
    }
    
    /**
     * Deleting the entire employee database
     */
    private void deleteAllEmployee() {
        if(employeeController.deleteAllEmployee()) {
            System.out.println("Deleted succesfully");
        } else {
            System.out.println("Employee not deleted");
        }
    }
}
