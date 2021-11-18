/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.util.List;

import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.service.impl.ProjectService;
import com.ideas2it.employeemanagement.model.ProjectStatus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <h1> Project controller</h1>
 * Project controller controls and manipulates the CRUD
 * operations and validation
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class ProjectController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
    private ProjectService projectService;
	
	public void init() throws ServletException {
		super.init();
	    projectService = new ProjectService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();
		
        try {
	        switch (action) {
	            case "/ProjectCreateForm":
        	        createProjectForm(request, response);
        	    break;
	            case "/viewProject":
	            	getAllProjects(request, response);
        	    break;
	            case "/projectUpdateForm":
	            	updateProjectForm(request, response);
        	    break;
	            case "/deleteProject":
	            	deleteSingleProject(request, response);
        	    break;
	            case "/deleteAllProject":
	            	deleteAllProject(request, response);
        	    break;
	            default :
	        	    break;
	        }
        } catch (ServletException | EMSException | IOException e) {
        	e.printStackTrace();
        }
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
	        switch (action) {
	            case "/createProject":
	        	    createProject(request, response);
	        	    break;
	            case "/updateProject":
	            	updateAllFields(request, response);
	        	    break;
	            default :
	        	    break;
	        }
        } catch (ServletException | EMSException | IOException e) {
        	e.printStackTrace();
        }
	}
    
    /**
     * Checking an project presences in database
     *
     * @param id project id for checking existance
     * @return project exist or not
     */
    public boolean isProjectExist(int id) throws EMSException {
        return projectService.isProjectExist(id);
    }
    
    /**
     * Checking if projects present in database
     *
     * @return database empty or not
     */
    public long isDataBaseEmpty() throws EMSException {
        return projectService.isDataBaseEmpty();
    }
    
    /**
     * Validating the corresponding project name  
     *
     * @param name project name for validation
     * @return name is valid or not
     */
    public boolean validateName(String name) {
        return projectService.ValidateName(name);
    }
    
    /**
     * Validating the corresponding project manager name  
     *
     * @param manager project manager name for validation
     * @return name is valid or not
     */
    public boolean validateManagerName(String manager) {
        return projectService.ValidateManagerName(manager);
    }
    
    /**
     * Validating the corresponding project description  
     *
     * @param description project name for validation
     * @return description is valid or not
     */
    public boolean isValidateDescription(String description) {
        return projectService.ValidateDescription(description);
    }
    
    /**
     * validate ids of employees 
     *
     * @param ids ids of employees
     * @return ids valid or not
     */
    public boolean validateIds(String[] ids) {
        return projectService.validateIds(ids);
    }
    
    /**
     * Creating new project and storing in database
     *
     * @param projectDto project details to be created
     * @return Number of projects added
     */
    public void createProject(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	ProjectStatus status;
    	String name = request.getParameter("name");
    	String description = request.getParameter("description");
    	String manager = request.getParameter("manager");
    	String projectStatus = request.getParameter("status");
    	if (projectStatus.equals("DEVELOPMENT")) {
    		status = ProjectStatus.DEVELOPMENT;
    	} else if(projectStatus.equals("TESTING")) {
    		status = ProjectStatus.TESTING;
    	} else {
    		status = ProjectStatus.LIVE;
    	}
    	ProjectDTO projectDto = new ProjectDTO(name, description, manager, status);
        int id = projectService.createProject(projectDto);
        if (0 < id) {
        	response.getWriter().print("project create success");
        } else {
        	response.getWriter().print("error page");
        }
        
    }
    
    public void createProjectForm(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	request.setAttribute("action", "createProject");
    	request.getRequestDispatcher("projectForm.jsp").forward(request, response);
    }
    
    public void updateProjectForm(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	request.setAttribute("action", "updateProject");
    	ProjectDTO projectDto = projectService.getSingleProject(id);
    	//EmployeeDTO cloneEmployeeDto = employeeDto;
    	request.setAttribute("cloneProjectDto", projectDto);
    	request.getSession().setAttribute("updateProjectDto", projectDto); 
    	request.getRequestDispatcher("projectForm.jsp").forward(request, response);
    }
    
    /**
     * Getting the particular project by id
     *
     * @param id project id to get project details
     * @return particular project details
     */
    public ProjectDTO getSingleProject(int id) throws EMSException {
        return projectService.getSingleProject(id);
    }
    
    /**
     * Getting all projects in the database
     *
     * @return All projects details
     */
    public void getAllProjects(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	List<ProjectDTO> projects = projectService.getAllProjects();
    	request.setAttribute("projects", projects);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("viewProject.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * Getting all employees in the database
     *
     * @return All employees details
     */
    public List<EmployeeDTO> getAllEmployees() throws EMSException {
        return projectService.getAllEmployees();
    }
    
    /**
     * Updating the all fields of an project
     *
     * @param projectDto project details to be updated
     * @return Number of rows updated
     */
    public void updateAllFields(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	ProjectStatus status;
    	int result;
    	ProjectDTO projectDto = (ProjectDTO) request.getSession().getAttribute("updateProjectDto");
    	
    	projectDto.setName(request.getParameter("name"));
    	projectDto.setDescription(request.getParameter("description"));
    	projectDto.setManager(request.getParameter("manager"));
    	String projectStatus = request.getParameter("status");
    	if (projectStatus.equals("DEVELOPMENT")) {
    		status = ProjectStatus.DEVELOPMENT;
    	} else if(projectStatus.equals("TESTING")) {
    		status = ProjectStatus.TESTING;
    	} else {
    		status = ProjectStatus.LIVE;
    	}
    	projectDto.setStatus(status);
        result = projectService.updateAllFields(projectDto);
         if (0 < result) {
        	 response.getWriter().print("project update success");
         } else {
        	 response.getWriter().print("error page");
         }
    }
    
    /**
     * Deleting the required project
     *
     * @param id id for deleting the project
     * @return Number of rows deleted
     */
    public void deleteSingleProject(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        int rowsDeleted = projectService.deleteSingleProject(id);
        if (0 < rowsDeleted) {
        	response.getWriter().print("deleted");
        } else {
        	response.getWriter().print("error page");
        }
    }
    
    /**
     * Deleting all projects in the database
     *
     * @return Number of rows deleted
     */
    public void deleteAllProject(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
        int result = projectService.deleteAllProject();
        if (0 < result) {
        	response.getWriter().print("All projects deleted");
        } else {
        	response.getWriter().print("error page");
        }
    }
}
