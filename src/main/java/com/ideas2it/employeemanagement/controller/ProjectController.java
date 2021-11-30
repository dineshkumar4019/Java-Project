/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLogger;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.service.impl.ProjectService;
import com.ideas2it.employeemanagement.model.ProjectStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
@Controller
public class ProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    private ProjectService projectService;
	
	public ProjectController(ProjectService projectService) {
	    this.projectService = projectService;
	}
	
	EMSLogger EmsLogger = new EMSLogger(ProjectController.class);
	
//	/**
//	 * Performs Do get request action from the client
//	 * 
//	 * @param request http request from client
//	 * @param response corresponding response to the client
//	 */
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		String action = request.getServletPath();
//		
//        try {
//	        switch (action) {
//	            case "/ProjectCreateForm":
//        	        createProjectForm(request, response);
//        	    break;
//	            case "/viewProject":
//	            	getAllProjects(request, response);
//        	    break;
//	            case "/projectUpdateForm":
//	            	updateProjectForm(request, response);
//        	    break;
//	            case "/deleteProject":
//	            	deleteSingleProject(request, response);
//        	    break;
//	            case "/deleteAllProject":
//	            	deleteAllProject(request, response);
//        	    break;
//	            case "/assignEmployee":
//	            	assignEmployees(request, response);
//        	    break;
//	            case "/unAssignEmployee":
//	            	unAssignEmployees(request, response);
//        	    break;
//	            default :
//	        	    break;
//	        }
//        } catch (ServletException | EMSException | IOException e) {
//        	EmsLogger.error(e);
//        	request.getRequestDispatcher("error.jsp").forward(request, response);
//        }
//	}
//	
//	/**
//	 * Performs Do post request action from the client
//	 * 
//	 * @param request http request from client
//	 * @param response corresponding response to the client
//	 */
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getServletPath();
//        try {
//	        switch (action) {
//	            case "/createProject":
//	        	    createProject(request, response);
//	        	    break;
//	            case "/updateProject":
//	            	updateAllFields(request, response);
//	        	    break;
//	            case "/allocateEmployee":
//	            	allocateEmployees(request, response);
//	        	    break;
//	            case "/unAllocateEmployee":
//	            	unAllocateEmployees(request, response);
//	        	    break;
//	            default :
//	        	    break;
//	        }
//        } catch (ServletException | EMSException | IOException e) {
//        	EmsLogger.error(e);
//        	request.getRequestDispatcher("error.jsp").forward(request, response);
//        }
//	}
    
    /**
     * Creating new project and storing in database
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
	@PostMapping("createProject")
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
        	EmsLogger.info("Project Created");
        	response.sendRedirect("SuccessMessageProject.jsp"
        			+ "?message=Project+Details+Created+Successfully");
        } else {
        	EmsLogger.error("Project Not created");
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    /**
     * forwarding the request to project form for creating project
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
	@GetMapping("ProjectCreateForm")
    public String createProjectForm(Model model) throws EMSException {
		model.addAttribute("action", "createProject");
		model.addAttribute("project", new ProjectDTO());
		return "projectForm.jsp";
//    	request.setAttribute("action", "createProject");
//    	request.getRequestDispatcher("projectForm.jsp").forward(request, response);
    }
    
    /**
     * forwarding the request to project form for updating project
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
	@GetMapping("projectUpdateForm")
    public void updateProjectForm(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	request.setAttribute("action", "updateProject");
    	ProjectDTO projectDto = projectService.getSingleProject(id);
    	request.setAttribute("cloneProjectDto", projectDto);
    	request.getSession().setAttribute("updateProjectDto", projectDto); 
    	request.getRequestDispatcher("projectForm.jsp").forward(request, response);
    }
    
    /**
     * Getting all projects in the database
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("viewProject")
    public void getAllProjects(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	List<ProjectDTO> projects = projectService.getAllProjects();
    	request.setAttribute("projects", projects);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("viewProject.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * Updating the all fields of an project
     *
     * @param projectDto project details to be updated
     * @return Number of rows updated
     */
    @PostMapping("updateProject")
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
        	 EmsLogger.info("Project Updated id:" + projectDto.getId());
        	 response.sendRedirect("SuccessMessageProject.jsp"
        	 		+ "?message=Project+Details+updated+Successfully");
         } else {
        	 EmsLogger.error("Project not updated" + projectDto.getId());
        	 request.getRequestDispatcher("error.jsp").forward(request, response);
         }
    }
    
    /**
     * Deleting the required project
     *
     * @param id id for deleting the project
     * @return Number of rows deleted
     */
    @GetMapping("deleteProject")
    public void deleteSingleProject(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        int rowsDeleted = projectService.deleteSingleProject(id);
        if (0 < rowsDeleted) {
        	EmsLogger.info("Project deleted id:" + id);
        	response.sendRedirect("viewProject");
        } else {
        	EmsLogger.error("Project not deleted id:" + id);
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    /**
     * Deleting all projects in the database
     *
     * @return Number of rows deleted
     */
    @GetMapping("deleteAllProject")
    public void deleteAllProject(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
        int result = projectService.deleteAllProject();
        if (0 < result) {
        	EmsLogger.info("All projects deleted");
        	response.sendRedirect("SuccessMessageProject.jsp?message=All projects Deleted Successfully");
        } else {
        	EmsLogger.error("projects not deleted");
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    @GetMapping("assignEmployee")
    public void assignEmployees(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        ProjectDTO projectDto = projectService.getSingleProject(id);
        List<EmployeeDTO> availableEmployees = projectService.getAvailableEmployees(projectDto);
        request.getSession().setAttribute("assignProjectDto", projectDto);
        request.setAttribute("availableEmployees", availableEmployees);
        request.setAttribute("action", "allocateEmployee");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("assign-unassignEmployeeForm.jsp");
        dispatcher.forward(request, response);
    }
    
    @PostMapping("allocateEmployee")
    public void allocateEmployees(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	
    	if (null ==  request.getParameterValues("selectedEmployees")) {
    		request.setAttribute("Message", "No Employees Selected");
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    	String[] selectedEmployees = request.getParameterValues("selectedEmployees");
    	ProjectDTO projectDto = (ProjectDTO) request.getSession().getAttribute("assignProjectDto");
    	List<EmployeeDTO> list = new ArrayList<>();
    	
    	for (String id : selectedEmployees) {
    		EmployeeDTO employeeDto = new EmployeeDTO();
    		employeeDto.setId(Integer.parseInt(id));
    		list.add(employeeDto);
    	}
    	projectDto.getEmployeesDto().addAll(list);
    	result = projectService.updateAllFields(projectDto);
    	if (0 < result) {
    		EmsLogger.info("Employees Assigned for project id:" + projectDto.getId());
    		response.sendRedirect("SuccessMessageProject.jsp?message=Employee Assigned Successfully");
        } else {
        	EmsLogger.error("Employees not assigned for project id:" + projectDto.getId());
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    @GetMapping("unAssignEmployee")
    public void unAssignEmployees(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	ProjectDTO projectDto = projectService.getSingleProject(id);
    	Set<EmployeeDTO> availableEmployees = projectDto.getEmployeesDto();
    	request.getSession().setAttribute("unAssignProjectDto", projectDto);
        request.setAttribute("availableEmployees", availableEmployees);
        request.setAttribute("action", "unAllocateEmployee");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("assign-unassignEmployeeForm.jsp");
        dispatcher.forward(request, response);
    }
    
    @PostMapping("unAllocateEmployee")
    public void unAllocateEmployees(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	
    	if (null ==  request.getParameterValues("selectedEmployees")) {
    		request.setAttribute("Message", "No Employees Selected");
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    	String[] selectedEmployees = request.getParameterValues("selectedEmployees");
    	ProjectDTO projectDto = (ProjectDTO) request.getSession().getAttribute("unAssignProjectDto");
    	List<EmployeeDTO> list = new ArrayList<>();
    	for (String id : selectedEmployees) {
    		EmployeeDTO employeeDto = new EmployeeDTO();
    		employeeDto.setId(Integer.parseInt(id));
    		list.add(employeeDto);
    	}
    	projectDto.getEmployeesDto().removeAll(list);
    	result = projectService.updateAllFields(projectDto);
    	
    	if (0 < result) {
    		EmsLogger.error("Employees unAssigned for project id:" + projectDto.getId());
    		response.sendRedirect("SuccessMessageProject.jsp?message=Employee Un Assigned Successfully");
        } else {
        	EmsLogger.error("Employees not unAssigned for project id:" + projectDto.getId());
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
