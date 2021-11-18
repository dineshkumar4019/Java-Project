/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
import java.util.Scanner;

//import com.ideas2it.employeemanagement.view.EmployeeView;
//import com.ideas2it.employeemanagement.view.ProjectView;

/**
 * <h1> Employees management system </h1>
 * Collecting the employee information and performing
 * the user required operation on stored employees
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 *
public class EmployeeManagement {
    private Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int userChoice;
        boolean isValidChoice = true;
        EmployeeManagement employeeManagement = new EmployeeManagement();
        
        while (isValidChoice) {
            System.out.println("\n\t**Employee Management System**\n\t1.Employee\n\t2.Project\n\t3.Exit");
            userChoice = employeeManagement.getAndValidateChoice();
            switch(userChoice) {
                case 1:
                    EmployeeView employeeView =  new EmployeeView();
                    employeeView.choosingOperation();
                    break;
                case 2:
                    ProjectView projectView = new ProjectView();
                    projectView.choosingOperation();
                    break;
                case 3:
                    isValidChoice = false;
                    System.out.println("\n\t**Exited successfully**");
                    break;
                default:
                    System.out.println("\n\t**Wrong Choice**");
            }
        }
    }
    
    /**
     * Checking the entered choice of the 
     * respective operations is valid or not
     * 
     * @return proper format of choice 
     *
    private int getAndValidateChoice() {
        boolean isValidUserChoice = false; 
        int userChoice = 0;
        
        do {        
            try {
                userChoice =  Integer.valueOf(scanner.nextLine());
                isValidUserChoice = true;
            } catch (NumberFormatException e) {
                System.out.println("\n\t**Select choice between the range!!!**\n\t**Enter choice again**");
            }
        } while (!isValidUserChoice);
        return userChoice;
    }
}
 */
