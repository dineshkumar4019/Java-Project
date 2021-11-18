///*
// * Copyright (c) ideas2it.
// *
// * Date: 2021-09-12 
// */
//package com.ideas2it.employeemanagement.view;
//
//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.HashSet;
//
//import com.ideas2it.employeemanagement.controller.ProjectController;
//import com.ideas2it.employeemanagement.exception.EMSException;
//import com.ideas2it.employeemanagement.model.EmployeeDTO;
//import com.ideas2it.employeemanagement.model.ProjectDTO;
//import com.ideas2it.employeemanagement.model.ProjectStatus;
//
///**
// * <h1> Project view</h1>
// * Project view performing CRUD operations for the projects 
// * and asssign and unassign the projects for an employees
// * 
// * @author	Dinesh Kumar
// * @version	1.0
// * @since   2021-10-30
// * 
// */
// public class ProjectView {
//    private Scanner scanner = new Scanner(System.in);
//    private ProjectController projectController = new ProjectController();
//    
//    /**
//     * Selecting the respective operation by user
//     * for CRUD operations 
//     */
//    public void choosingOperation() {
//        int userOperationChoice;
//        
//        do {
//            System.out.println("\n\t**1.Create 2.Display 3.Update 4.Delete" 
//                               + " 5:Assign Employee 6:Unassign Employee 7.Exit **");
//            userOperationChoice = getAndValidateChoice();
//            
//            switch(userOperationChoice) {
//                case 1:
//                    createProject();
//                    break;
//                case 2:
//                    displayProject();
//                    break;
//                case 3:
//                    updateProject();
//                    break;
//                case 4:
//                    deleteProject();
//                    break;
//                case 5:
//                    assignEmployee();
//                    break;
//                case 6:
//                    unAssignEmployee();
//                    break;
//                case 7:
//                    System.out.println("\t**Exited successfully from projects**");
//                    break;
//                default :
//                    System.out.println("\t**Wrong choice**\n\t**Enter choice Again**");
//            }
//        } while (7 != userOperationChoice);
//    }
//    
//    /**
//     * Checking the entered choice of the 
//     * respective operations is valid or not
//     * 
//     * @return proper format of choice 
//     */
//    private int getAndValidateChoice() {
//        boolean isValidUserChoice = false; 
//        int userChoice = 0;
//        
//        do {        
//            try {
//                userChoice = Integer.parseInt(scanner.nextLine());
//                isValidUserChoice = true;
//            } catch (NumberFormatException e) {
//                System.out.println("\n\t**Select choice between the range!!!**\n\t**Enter choice again**");
//            }
//        } while (!isValidUserChoice);
//        return userChoice;
//    }
//    
//    /**
//     * Checking the project id present in database 
//     * and validating the existing id
//     * 
//     * @return details of the project
//     */
//    private ProjectDTO getAndValidateProject() {
//        boolean isValidId = false;
//        int id = 0;
//        ProjectDTO projectDto = null;
//        
//        while (!isValidId) {
//            try {
//                id = Integer.parseInt(scanner.nextLine());
//                if (!projectController.isProjectExist(id)) {
//                    System.out.println("\n\tproject id doesn't exist\n\t**Enter id again**");
//                } else {
//                    projectDto = projectController.getSingleProject(id);
//                    isValidId = true;
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("\n\tID must be in numbers\n\t**Enter id Again**");
//            } catch (EMSException exception) {
//                System.out.println("\n\t**" + exception + "**");
//            }
//        }
//        return projectDto;
//    }
//    
//    /**
//     * Creating projet and Storing in the database
//     * 
//     */
//    private void createProject() {
//        ProjectDTO projectDto = null;
//        int id = 0;
//        
//        try {
//            String name = getAndValidateName();
//            String description = getAndValidateDescription();
//            String manager = getAndValidateManager();
//            
//            projectDto = new ProjectDTO(name, description, manager, getStatus());
//            id = projectController.createProject(projectDto);
//            
//            if (0 != id) {
//                System.out.println("\n\t**Project created successfully**\n\tID = " + id);
//            } else {
//                System.out.println("\n\t**Project creation failed!!!**");
//            }
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//   }
//   
//   /**
//     * Selecting the status of the project
//     * 
//     * @return projectStatus status of the project
//     */
//   private ProjectStatus getStatus() {
//       int userChoice;
//       ProjectStatus status = null;
//       System.out.println("\n\t**select status**\n\t1: Developement\n\t2: Testing\n\t3: Live"
//                            + "\n\tType 1, 2, or 3 corresponding to above statement");
//       
//       do {
//           userChoice = getAndValidateChoice();
//           switch (userChoice) {
//               case 1:
//                   status = ProjectStatus.DEVELOPMENT;
//                   break;
//               case 2:
//                   status = ProjectStatus.TESTING;
//                   break;
//               case 3:
//                   status = ProjectStatus.LIVE;
//                   break;
//               default:
//                   System.out.println("\t**Wrong choice**\n\t**Enter choice Again**");
//           }
//       } while (3 < userChoice);
//       return status;
//   }     
//   
//   /**
//     * Checking the entered project name is valid
//     * 
//     * @return proper format of name
//     */
//    private String getAndValidateName() {
//        boolean isvalidName = false;
//        String name = "";
//        
//        while (!isvalidName) {
//            System.out.println("Enter the project name: ");
//            name = scanner.nextLine().trim();
//            
//            if (projectController.validateName(name)) {
//                isvalidName = true;
//            } else {
//                System.out.println("\n\tEnter project name correctly!!!\n\t**Name must be in alphabets only..** ");
//            }
//        }
//        return name;
//    }
//    
//    /**
//     * Checking the entered project manager name is valid
//     * 
//     * @return proper format of name
//     */
//    private String getAndValidateManager() {
//        boolean isvalidName = false;
//        String manager = "";
//        
//        while (!isvalidName) {
//            System.out.println("Enter the firstname and lastname of manager: ");
//            manager = scanner.nextLine().trim();
//            
//            if (projectController.validateManagerName(manager)) {
//                isvalidName = true;
//            } else {
//                System.out.println("\n\tEnter name correctly!!!\n\t**Name must be in alphabets only..** ");
//            }
//        }
//        return manager;
//    }
//    
//    /**
//     * Checking the entered description of the project is valid
//     * 
//     * @return proper format of description
//     */
//    private String getAndValidateDescription() {
//        boolean isvalidDescription = false;
//        String description = "";
//        
//        while (!isvalidDescription) {
//            System.out.println("Enter the description of project: ");
//            description = scanner.nextLine().trim();
//            
//            if (projectController.validateName(description)) {
//                isvalidDescription = true;
//            } else {
//                System.out.println("\n\tEnter project name correctly!!!");
//            }
//        }
//        return description;
//    }
//    
//    /** 
//     * Selecting the ways of displaying the projects
//     * 
//     */
//    private void displayProject() {
//        int ViewChoice;
//        
//        try {
//            if (0 == projectController.getAllProjects().size()) {
//                System.out.println("\tNo projects to view");
//            } else {
//                System.out.println("1:Display all projects\n2:display projects by id");
//                do {
//                    ViewChoice = getAndValidateChoice();  
//                              
//                    switch (ViewChoice) {
//                        case 1:
//                            viewAllProjects();
//                            break;
//                        case 2:
//                            viewProject();
//                            break;
//                        default :
//                            System.out.println("\t**Wrong choice**\n\t**Enter choice again**");
//                    }
//                } while (2 < ViewChoice);
//            }
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//    }
//    
//    /**
//     * displaying the particular project details and employees
//     * in the project
//     * 
//     * @param id project id to view
//     */
//    private void viewProject() {
//        System.out.println("Enter Project id to view:");
//        ProjectDTO projectDto = getAndValidateProject();
//        System.out.println(projectDto);
//        for (EmployeeDTO entry : projectDto.getEmployeesDto()) {
//            System.out.println(entry);
//        }
//    }
//    
//    /**
//     * displaying all the project details in the database
//     */
//    private void viewAllProjects() {
//        try {
//            List<ProjectDTO> projectDto = projectController.getAllProjects();  
//            
//            for(ProjectDTO project : projectDto) {
//                System.out.println(project);
//            }
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//    }
//    
//    /**
//     * Selecting the ways of updating the projects fields
//     * and getting the project id to update
//     * 
//     */
//    private void updateProject() {
//        int UpdateChoice = 0;
//        ProjectDTO projectDto;
//        
//        try {
//            if (0 == projectController.getAllProjects().size()) {
//                System.out.println("\n\tNo projects to update");
//            } else {
//                System.out.println("Enter the project id to update: ");
//                projectDto = getAndValidateProject();
//                System.out.println("1:Update all project fields\n2:Update specific project field");
//                do {
//                    UpdateChoice = getAndValidateChoice();
//                    switch (UpdateChoice) {
//                        case 1:
//                            updateAllFields(projectDto);
//                            break;
//                        case 2:
//                            updateField(projectDto);
//                            break;
//                        default :
//                            System.out.println("\t**Wrong choice**\n\t**Enter choice again**");
//                    }
//                } while (2 < UpdateChoice);
//            }
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//    }
//    
//    /**
//     * Updating all fields of an project in the database
//     *
//     * @param id project id to update
//     */
//    private void updateAllFields(ProjectDTO projectDto) {
//        try {
//            projectDto.setId(projectDto.getId());
//            projectDto.setName(getAndValidateName());
//            projectDto.setDescription(getAndValidateDescription());
//            projectDto.setManager(getAndValidateManager());
//            projectDto.setStatus(getStatus());
//            if (1 == projectController.updateAllFields(projectDto)) {
//                System.out.println("\n\t***Details updated Successfully***");
//            } else {
//                System.out.println("\n\t***Details not updated***");
//            }
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//    }
//    
//    /**
//     * Updating the particular field of an project in the database
//     * 
//     * @param id project id to update
//     */
//    private void updateField(ProjectDTO projectDto) {
//        int projectField;
//        System.out.println("Select project field to update\n1:Name\n2:Description\n3:Manager\n4:Update status");
//        try {
//            projectField = getAndValidateChoice();
//            do {
//                switch (projectField) {
//                    case 1:
//                        projectDto.setName(getAndValidateName());
//                        if(1 == projectController.updateAllFields(projectDto)) {
//                            System.out.println("\n\t***Name updated Successfully***");
//                        } else {
//                            System.out.println("\n\t***Name not updated***");
//                        }
//                        break;
//                    case 2:
//                        projectDto.setDescription(getAndValidateDescription());
//                        if(1 == projectController.updateAllFields(projectDto)) {
//                            System.out.println("\n\t***Description updated Successfully***");
//                        } else {
//                            System.out.println("\n\t***Description not updated***");
//                        }
//                        break;
//                    case 3:
//                        projectDto.setManager(getAndValidateManager());
//                        if(1 == projectController.updateAllFields(projectDto)) {
//                            System.out.println("\n\t***Manager updated Successfully***");
//                        } else {
//                            System.out.println("\n\t***Manager not updated***");
//                        }
//                        break;
//                    case 4:
//                        projectDto.setStatus(getStatus());
//                        if(1 == projectController.updateAllFields(projectDto)) {
//                            System.out.println("\n\t***Status updated Successfully***");
//                        } else {
//                            System.out.println("\n\t***Status not updated***");
//                        }
//                        break;
//                    default:
//                        System.out.println("\n\t**Wrong field**\n\t**Select the field between the range");
//                }
//            } while (4 < projectField);
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//    }
//    
//    /**
//     * Selecting the ways to delete projects details
//     *
//     */
//    private void deleteProject() {
//        int DeleteChoice;
//        
//        try {
//            if (0 == projectController.getAllProjects().size()) {
//                System.out.println("\tNo project to delete");
//            } else {
//                System.out.println("1:Delete all projects\n2:Delete project by id");
//                do {
//                    DeleteChoice = getAndValidateChoice();                
//                    switch (DeleteChoice) {
//                        case 1:
//                            deleteAllProject();
//                            break;
//                        case 2:
//                            System.out.println("Enter Project id to delete:");
//                            ProjectDTO projectDto = getAndValidateProject();
//                            deleteSingleProject(projectDto.getId());
//                            break;
//                        default:
//                            System.out.println("\t**Wrong choice**\n\t**Enter choice again**");
//                    }
//                } while (2 < DeleteChoice);
//            }
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//    }
//    
//    /**
//     * Deleting the required project by id
//     *
//     * @param id id for deleting the project
//     */
//    private void deleteSingleProject(int id) {
//        try {
//            if (1 == projectController.deleteSingleProject(id)) {
//                System.out.println("\n\t**Deleted succesfully**");
//            } else {
//                System.out.println("\n\t**Projects not deleted**");
//            }
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//    }
//    
//    /**
//     * Deleting all projects in the database
//     */
//    private void deleteAllProject() {
//        try {
//            if (projectController.getAllProjects().size() == projectController.deleteAllProject()) {
//                System.out.println("\n\t**Deleted succesfully**");
//            } else {
//                System.out.println("\n\t**projects not deleted*");
//            }
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//    }
//    
//    /**
//     * Assiging the employees to the project 
//     */
//    private void assignEmployee() {
//        String[] ids;
//        Set<EmployeeDTO> selectedEmployees = new HashSet<>();
//        List<EmployeeDTO> toRemove = new ArrayList<>();
//        List<EmployeeDTO> employees = new ArrayList<>();
//        
//        System.out.println("Enter the Project Id to assign employee");
//        ProjectDTO projectDto = getAndValidateProject();
//        
//        try {
//            employees = projectController.getAllEmployees();
//        
//            if (null != projectDto.getEmployeesDto()) {
//                for (EmployeeDTO existing : projectDto.getEmployeesDto()) {
//                    for (EmployeeDTO available : employees) {
//                        if (existing.getId() == available.getId()) {
//                            toRemove.add(available);
//                        }
//                    }
//                }
//                employees.removeAll(toRemove);
//            }
//            System.out.println("\n\t**Available Employees*");
//            for (EmployeeDTO employeeDto : employees) {
//                System.out.println("\n\tEmployee Id-->" + employeeDto.getId() 
//                                   + "\tEmployee name:-->" + employeeDto.getName());
//            }
//            do {
//                System.out.println("Enter Employee ids to assign(eg--> 1,2,4)");
//                String employeeIds = scanner.nextLine();
//                ids = employeeIds.split(",");
//            } while (!projectController.validateIds(ids));
//        
//            for (EmployeeDTO available : employees) {
//                for (String id : ids) {
//                    if (Integer.parseInt(id) == available.getId()) {
//                        selectedEmployees.add(available);
//                    }
//                }
//            }
//            for (EmployeeDTO employeeDto : selectedEmployees) {
//                projectDto.getEmployeesDto().add(employeeDto);
//            }
//            projectController.updateAllFields(projectDto);
//        } catch (EMSException exception) {
//            System.out.println("\n\t**" + exception + "**");
//        }
//    }
//     
//    /**
//     * Unassiging the employees to the project 
//     */ 
//    private void unAssignEmployee() {
//        String[] ids;
//        Set<EmployeeDTO> set = new HashSet<>();
//        Set<EmployeeDTO> toRemove = new HashSet<>();
//        
//        System.out.println("Enter the Project Id to unAssign project");
//        ProjectDTO projectDto = getAndValidateProject();
//        if (0 != projectDto.getEmployeesDto().size()) {
//            set =  projectDto.getEmployeesDto();
//            for (EmployeeDTO employeeDto : set) {
//                System.out.println("\n\tEmployee Id-->" + employeeDto.getId() 
//                               + "\tEmployee name:-->" + employeeDto.getName());
//            }
//        
//            do {
//                System.out.println("Enter Employee id to unassign(eg--> 1,2,4)");
//                String employeeIds = scanner.nextLine();
//                ids = employeeIds.split(",");
//            } while (!projectController.validateIds(ids));
//        
//            for (EmployeeDTO employeeDto : set) {
//                for (String id : ids) {
//                    if (Integer.parseInt(id) == employeeDto.getId()) {
//                        toRemove.add(employeeDto);
//                    }
//                }
//            }
//            set.removeAll(toRemove); 
//            projectDto.setEmployeesDto(set); 
//            try {
//                projectController.updateAllFields(projectDto);
//            } catch (EMSException exception) {
//                System.out.println("\n\t**" + exception + "**");
//            }
//        } else {
//            System.out.println("\n\t**No Projects for this employee to unassign**");
//        }
//    }
//}
