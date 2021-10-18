/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12 
 */
package com.ideas2it.employeemanagement.view;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.controller.AddressController; 
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.AddressDTO;


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
    private AddressController addressController = new AddressController();
    private AddressDTO addressDto = new AddressDTO();
    
    /**
     * Selecting the respective operation by user
     * for CRUD operations 
     */
    public void choosingOperation() {
        int userOperationChoice;
        int id;
        
        do {
            System.out.println("\n\t**1.Insert 2.Display 3.Update 4.Delete 5:Add Address 6.Exit**");
            userOperationChoice = getAndValidateChoice();
            
            switch(userOperationChoice) {
                case 1:
                    addAddress(createEmployee());
                    break;
                case 2:
                    displayEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    System.out.println("Enter Employee ID to add address");
                    id = getAndValidateId();
                    addAddress(id);
                    break;
                case 6:
                    System.out.println("\t**Exited successfully**");
                    break;
                default :
                    System.out.println("\t**Wrong choice**\n\t**Enter choice Again**");
            }
        } while (6 != userOperationChoice);
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
     * Checking the employee id present in database 
     * and validating the existing id
     * 
     * @return proper format of id
     */
    private int getAndValidateId() {
        boolean isValidId = false;
        int id = 0;
        
        while (!isValidId) {
        
            try {
                id = Integer.parseInt(scanner.nextLine());
                
                if (!employeeController.isEmployeeExist(id)) {
                    System.out.println("\n\tEmpoloyee id doesn't exist\n\t**Enter id again**");
                } else {
                    isValidId = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\tID must be in numbers\n\t**Enter id Again**");
            } catch (SQLException e) {
                e.printStackTrace();
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
    private int getAndValidateAddressId(int id) {
        boolean isValidId = false;
        int addressId = 0;
        
        while (!isValidId) {
        
            try {
                addressId = Integer.parseInt(scanner.nextLine());
                
                if (!addressController.isAddressExist(id)) {
                    System.out.println("\n\tAddress id doesn't exist\n\t**Enter id again**");
                } else {
                    isValidId = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\tAddress ID must be in numbers\n\t**Enter id Again**");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return addressId;
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
            
            if (employeeController.validateName(name)) {
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
                
                if (0 < salary) {
                
                    if (employeeController.validateSalary(salary)) {
                        isValidSalary = false;
                    } else {
                        System.out.println("\n\tEnter salary correctly!!!");
                    }
                } else {
                    System.out.println("\n\t**salary must not be 0**\n\t**salary must be in numbers**");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\t**salary must be in numbers**");
            }
        } while (isValidSalary);
        
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
            
            try {
                if (!employeeController.isEmailExist(email)) {
                    if (employeeController.validateEmail(email)) {
                        isvalidEmail = true;
                    } else {
                        System.out.println("\n\t**Enter Email in correct format !!!**");
                    }
                } else {
                    System.out.println("\n\t**Entered email already exist**");
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
            
            if (employeeController.validatePhoneNumber(phoneNumber)) {
                try {
                    if (!employeeController.isPhoneNumberExist(Long.valueOf(phoneNumber))) {
                        isValidPhoneNumber = false;
                    } else {
                        System.out.println("\n\t**Entered number already exist**");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("\n\tEnter phone number correctly!!!\n\t*PhoneNumber must be 10 numbers*");
            }
        } while (isValidPhoneNumber);
        
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
        
        while (!isValidDOB) {
        
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
        return employeeController.stringDateConversion(DOB);
    }
    
    /** 
     * Selecting the ways of displaying the employee
     * 
     */           
    private void displayEmployee() {
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
    
    /**
     * displaying the particular employee by id
     * 
     * @param id employee id to view
     */
    private void viewEmployee(int id) {
        try {
            System.out.println(employeeController.getSingleEmployee(id));
            for (Object entry : addressController.getAddress(id)) {
                System.out.println(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * displaying all the employee in the database
     */
    private void viewAllEmployee() {
        try {
            //employeeController.getAllEmployee();
            for (EmployeeDTO entry: employeeController.getAllEmployee()) {
                List<AddressDTO> addresses = entry.getAddressDto();
                System.out.println(entry);
                for (AddressDTO addressDto : addresses) {
                    System.out.println(addressDto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creating employee and Storing in the database
     * 
     */
    private int createEmployee() {
        int id = 0;
        
        try {
            String name = getAndValidateName();
            double salary = getAndValidateSalary();
            String email = getAndValidateEmail();
            long phoneNumber = getAndValidatePhoneNumber();
            LocalDate DOB = getAndValidateDOB();
            id = employeeController.createEmployee(new EmployeeDTO(name, salary, email, phoneNumber, DOB));
            createAddress(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
   }
   
   private String getAndValidateAddress() {
        boolean isvalidAddress = false;
        String address = "";
        
        while (!isvalidAddress) {
            System.out.println("Enter the door no and locality name: ");
            address = scanner.nextLine().trim();
            
            if (addressController.validateAddress(address)) {
                isvalidAddress = true;
            } else {
                System.out.println("\n\tEnter address correctly!!!\n\t**Address only allows '-/,' only** ");
            }
        }
        return address;
    }
    
    private String getAndValidateCity() {
        boolean isvalidCity = false;
        String city = "";
        
        while (!isvalidCity) {
            System.out.println("Enter the city ");
            city = scanner.nextLine().trim();
            
            if (addressController.validateCity(city)) {
                isvalidCity = true;
            } else {
                System.out.println("\n\tEnter city correctly!!!\n\t**No special characters** ");
            }
        }
        return city;
    }
    
    private String getAndValidatePincode() {
        boolean isvalidPincode = false;
        String pincode = "";
        
        while (!isvalidPincode) {
            System.out.println("Enter the pincode: ");
            pincode = scanner.nextLine().trim();
            
            if (addressController.validatePincode(pincode)) {
                isvalidPincode = true;
            } else {
                System.out.println("\n\tEnter pincode correctly!!!\n\t**pincode should be 6 numbers** ");
            }
        }
        return pincode;
    }
    
    private String getAndValidateState() {
        boolean isvalidState = false;
        String state = "";
        
        while (!isvalidState) {
            System.out.println("Enter the state: ");
            state = scanner.nextLine().trim();
            
            if (addressController.validateState(state)) {
                isvalidState = true;
            } else {
                System.out.println("\n\tEnter state correctly!!!\n\t**No special characters** ");
            }
        }
        return state;
    }
    
    private String getAndValidateCountry() {
        boolean isvalidCountry = false;
        String country = "";
        
        while (!isvalidCountry) {
            System.out.println("Enter the country: ");
            country = scanner.nextLine().trim();
            
            if (addressController.validateCountry(country)) {
                isvalidCountry = true;
            } else {
                System.out.println("\n\tEnter country correctly!!!\n\t**No special characters** ");
            }
        }
        return country;
    }
    
    private void createAddress(int id) { 
        try {
            System.out.println("\n\tEnter Employee Address");
            String address = getAndValidateAddress();
            String city = getAndValidateCity();
            String pincode = getAndValidatePincode();
            String state = getAndValidateState();
            String country = getAndValidateCountry();
           
            if(1 == addressController.createAddress(new AddressDTO(id, address, city, pincode,
                                                     state, country))) {
                System.out.println("\n\tEMPLOYEE ID is: " + id 
                                   + "\n\t**Employee address added successfully**");
            } else {
                System.out.println("Employee NOT created");
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
   
    private void addAddress(int id) {
        int userChoice = 0;
       
        while (2 != userChoice){
            System.out.println("\n\t**Do You Want To Add Address\npress 1 for Yes and 2 for No");
            userChoice = getAndValidateChoice();
            
            switch (userChoice) {
                case 1:
                    createAddress(id);
                    break;
                case 2:
                    System.out.println("You Entered NO");
                    break;
                default :
                    System.out.println("\t**Wrong choice**\n\t**Enter choice Again**");
                    break;
            }
        }
    }
   
    /**
     * Selecting the ways of updating the fields
     * and getting the employee id to update
     * 
     */
    private void updateEmployee() {
        int UpdateChoice = 0;
        int id;
        
        if (0 == getTotalEmployees()) {
            System.out.println("No employees to update");
        } else {
            System.out.println("Enter the employee id to update: ");
            id = getAndValidateId();
            System.out.println("1:Update all\n2:Update specific field\n3:Update all address field"
                               + "\n4:update specific address field ");
            do {
                UpdateChoice = getAndValidateChoice();                
                switch (UpdateChoice) {
                    case 1:
                        updateAllFields(id);
                        break;
                    case 2:
                        updateField(id);
                        break;
                    case 3:
                        updateAddressFields(id);
                        break;
                    case 4:
                        updateAddessField(id);
                        break;
                    default :
                        System.out.println("\t**Wrong choice**\n\t**Enter choice again**");
                }
            } while (4 < UpdateChoice);
        }
    }
    
    /**
     * Updating all fields of an employee
     *
     * @param id employee id to update
     */
    private void updateAllFields(int id) {
        try {
            String name = getAndValidateName();
            double salary = getAndValidateSalary();
            String email = getAndValidateEmail();
            long phoneNumber = getAndValidatePhoneNumber();
            LocalDate DOB = getAndValidateDOB();
            if (1 == employeeController.updateAllFields(new EmployeeDTO(id, name, salary, email, phoneNumber, DOB))) {
                System.out.println("\n\t***Details updated Successfully***");
            } else {
                System.out.println("\n\t***Details not updated***");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        try {
            do {
                employeeField = getAndValidateChoice();
                EmployeeDTO employeeDto = employeeController.getSingleEmployee(id);
                employeeDto.setId(id);
                switch (employeeField) {
                    case 1:
                        employeeDto.setName(getAndValidateName());
                        if(1 == employeeController.updateField(employeeDto)) {
                            System.out.println("\n\t***Name updated Successfully***");
                        } else {
                            System.out.println("\n\t***Name not updated***");
                        }
                        break;
                    case 2:
                        employeeDto.setSalary(getAndValidateSalary());
                        if(1 == employeeController.updateField(employeeDto)) {
                            System.out.println("\n\t***Salary updated Successfully***");
                        } else {
                            System.out.println("\n\t***Salary not updated***");
                        }
                        break;
                    case 3:
                        employeeDto.setEmail(getAndValidateEmail());
                        if(1 == employeeController.updateField(employeeDto)) {
                            System.out.println("\n\t***Email updated Successfully***");
                        } else {
                            System.out.println("\n\t***Email not updated***");
                        }
                        break;
                    case 4:
                        employeeDto.setPhoneNumber(getAndValidatePhoneNumber());
                        if(1 == employeeController.updateField(employeeDto)) {
                            System.out.println("\n\t***Phone number updated Successfully***");
                        } else {
                            System.out.println("\n\t***Phone number not updated***");
                        }
                        break;
                    case 5:
                        employeeDto.setDOB(getAndValidateDOB());
                        if(1 == employeeController.updateField(employeeDto)) {
                            System.out.println("\n\t***DOB updated Successfully***");
                        } else {
                            System.out.println("\n\t***DOB number not updated***");
                        }
                        break;
                    default:
                        System.out.println("\n\t**Wrong field**\n\t**Select the field between the range");
                }
            } while (5 < employeeField);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateAddressFields(int id) {
        int addressId;
        
        try {  
            for (Object entry : addressController.getAddress(id)) {
                System.out.println(entry);
            }
            System.out.println("Enter addres Id to update");
            addressId = getAndValidateAddressId(id);
            String address = getAndValidateAddress();
            String city = getAndValidateCity();
            String pincode = getAndValidatePincode();
            String state = getAndValidateState();
            String country = getAndValidateCountry();
            if (1 == addressController.updateAddressFields(new AddressDTO(addressId, id, address, city, pincode, state, country))) {
                System.out.println("\n\t***Address updated Successfully***");
            } else {
                System.out.println("\n\t***Address not updated***");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateAddessField(int id) {
        int addressField;
        int addressId = 0;
        String address;
        String city;
        String pincode;
        String state;
        String country;
        
        try {
            for (Object entry : addressController.getAddress(id)) {
                System.out.println(entry);
            }
            System.out.println("Enter addres Id to update");
            addressId = getAndValidateAddressId(id);
            System.out.println("Select field to update\n1:Address\n2:City\n3:Pincode\n4:State\n5:Country");
            do {
                AddressDTO addressDto = addressController.getAddressById(addressId);
                addressDto.setId(addressId);
                addressField = getAndValidateChoice();
                switch (addressField) {
                    case 1:
                        addressDto.setAddress(getAndValidateAddress());
                        if(1 == addressController.updateAddressField(addressDto)) {
                            System.out.println("\n\t***Address updated Successfully***");
                        } else {
                            System.out.println("\n\t***Address not updated***");
                        }
                        break;
                    case 2:
                        addressDto.setCity(getAndValidateCity());
                        if(1 == addressController.updateAddressField(addressDto)) {
                            System.out.println("\n\t***City updated Successfully***");
                        } else {
                            System.out.println("\n\t***City not updated***");
                        }
                        break;
                    case 3:
                        addressDto.setPincode(getAndValidatePincode());
                        if(1 == addressController.updateAddressField(addressDto)) {
                            System.out.println("\n\t***Pincode updated Successfully***");
                        } else {
                            System.out.println("\n\t***Pincode not updated***");
                        }
                        break;
                    case 4:
                        addressDto.setState(getAndValidateState());
                        if(1 == addressController.updateAddressField(addressDto)) {
                            System.out.println("\n\t***State updated Successfully***");
                        } else {
                            System.out.println("\n\t***State not updated***");
                        }
                        break;
                    case 5:
                        addressDto.setCountry(getAndValidateCountry());
                        if(1 == addressController.updateAddressField(addressDto)) {
                            System.out.println("\n\t***Country updated Successfully***");
                        } else {
                            System.out.println("\n\t***Country not updated***");
                        }
                        break;
                    default:
                        System.out.println("\n\t**Wrong field**\n\t**Select the field between the range");
                }
            } while (5 < addressField);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteEmployee() {
        int DeleteChoice;
        int id;
        
        if (0 == getTotalEmployees()) {
            System.out.println("\tNo employee to delete");
        } else {
            System.out.println("1:Delete all\n2:Delete by id\n3:Delete address");
            do {
                DeleteChoice = getAndValidateChoice();                
                switch (DeleteChoice) {
                    case 1:
                        deleteAllEmployee();
                        break;
                    case 2:
                        System.out.println("Enter Employee id to delete:");
                        id = getAndValidateId();
                        deleteSingleEmployee(id);
                        break;
                    case 3:
                        System.out.println("Enter Employee id to delete:");
                        id = getAndValidateId();
                        deleteAddress(id);
                        break;
                    default:
                        System.out.println("\t**Wrong choice**\n\t**Enter choice again**");
                }
            }  while (3 < DeleteChoice);
        }
    }
    
    /**
     * Deleting the required employee by id
     *
     * @param id id for deleting the employees
     * @return employee deleted
     */
    private void deleteSingleEmployee(int id) {
        try {
            if (1 == employeeController.deleteSingleEmployee(id)) {
                System.out.println("Deleted succesfully");
            } else {
                System.out.println("Employee not deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deleting the entire employee database
     */
    private void deleteAllEmployee() {
        try {
            if (getTotalEmployees() == employeeController.deleteAllEmployee()) {
                System.out.println("Deleted succesfully");
            } else {
                System.out.println("Employee not deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteAddress(int id) {
        int addressId;
        
        try {
            if (1 == addressController.countAddress(id)) {
                System.out.println("Employee has only one address you can not able to delete");
            } else {
                for (Object entry : addressController.getAddress(id)) {
                    System.out.println(entry);
                }
                System.out.println("Enter address id to delete");
                addressId = getAndValidateAddressId(id);
                
                if (1 == addressController.deleteAddress(addressId)) {
                    System.out.println("Address deleted successfully");
                } else {
                    System.out.println("Address not deleted");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
       
    /**
     * Getting total employees present in the database
     */
    private int getTotalEmployees() {
        int totalEmployees = 0;
        try {
            totalEmployees = employeeController.getTotalEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalEmployees;
    }
}
