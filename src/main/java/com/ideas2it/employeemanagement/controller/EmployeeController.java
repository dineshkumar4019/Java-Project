/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.impl.EmployeeService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLogger;

/**
 * <h1> Employees controller</h1>
 * Empolyee controller controls and manipulates the CRUD
 * operations and validation
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class EmployeeController extends HttpServlet {
	private EMSLogger EmsLogger = new EMSLogger(EmployeeController.class);

	private static final long serialVersionUID = 102831973239L;
    
	private EmployeeService employeeService;
	
	public void init() throws ServletException {
		super.init();
	    employeeService = new EmployeeService();
	}
	
	/**
	 * Performs Do get request action from the client
	 * 
	 * @param request http request from client
	 * @param response corresponding response to the client
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();
        try {
	        switch (action) {
	            case "/employeeCreateForm":
        	        createEmployeeForm(request, response);
        	    break;
	            case "/employeeUpdateForm":
	            	updateForm(request, response);
        	    break;
	            case "/viewEmployee":
	        	    getAllEmployee(request, response);
	        	    break;
	            case "/addressCreateForm":
        	        createAddressForm(request, response);
        	    break;
	            case "/AddressUpdateForm":
	            	updateForm(request, response);
        	    break;
	            case "/delete":
	        	    deleteSingleEmployee(request, response);
	        	    break;
	            case "/deleteAll":
	        	    deleteAllEmployee(request, response);
	        	    break;
	            case "/assign":
	        	    assignProjects(request, response);
	        	    break;
	            case "/unAssign":
	        	    unAssignProjects(request, response);
	        	    break;
	            default :
	        	    break;
	        }
        } catch (ServletException | EMSException | IOException e) {
        	EmsLogger.error(e);
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
	
	/**
	 * Performs Do post request action from the client
	 * 
	 * @param request http request from client
	 * @param response corresponding response to the client
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
	        switch (action) {
	            case "/create":
	        	    createEmployee(request, response);
	        	    break;
	            case "/createAddress":
	        	    insertAddress(request, response);
	        	    break;
	            case "/update":
	        	    updateAllFields(request, response);
	        	    break;
	            case "/updateAddress":
	        	    updateAddressFields(request, response);
	        	    break;
	            case "/allocateProject":
	            	allocateProjects(request, response);
	        	    break;
	            case "/unAllocateProject":
	            	unAllocateProjects(request, response);
	        	    break;
	            default :
	        	    break;
	        }
        } catch (ServletException | EMSException | IOException e) {
        	EmsLogger.error(e);
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}
    
    /**
     * Creating the employee and storing in database
     *
     * @param employeeDto employee details to be created
     * @throws IOException 
     */
    public void createEmployee(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String email = request.getParameter("email");
        long phoneNumber = Long.parseLong((String) request.getParameter("phoneNumber"));
        String dataOfBirth = request.getParameter("DOB");
        LocalDate DOB = LocalDate.parse(dataOfBirth);
     
        EmployeeDTO employeeDto = new EmployeeDTO(name, salary, email, phoneNumber, DOB); 
    		request.getSession().setAttribute("createEmployeeDto", employeeDto);
    		request.setAttribute("action", "createAddress");
    	    request.getRequestDispatcher("addressForm.jsp").forward(request, response);
    }
    
    /**
     * forwarding the request to employee form for creating employee
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void createEmployeeForm(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	request.setAttribute("action", "create");
    	request.getRequestDispatcher("employeeForm.jsp").forward(request, response);
    }
    
    /**
     * forwarding the request to address form for creating address
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void createAddressForm(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	request.setAttribute("action", "createAddress");
    	request.getRequestDispatcher("addressForm.jsp").forward(request, response);
    }
    
    /**
     * forwarding the request to employee form for updating employee
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void updateForm(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	EmployeeDTO employeeDto = employeeService.getSingleEmployee(id);
    	
    	request.setAttribute("action", "update");
    	request.setAttribute("cloneEmployeeDto", employeeDto);
    	request.getSession().setAttribute("employeeDto", employeeDto); 
    	request.getRequestDispatcher("employeeForm.jsp").forward(request, response);
    }
    
    /**
     * Creating the employee and storing in database
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void insertAddress(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id;
    	List<AddressDTO> list = new ArrayList<>();
        EmployeeDTO employeeDto = (EmployeeDTO) request.getSession().getAttribute("createEmployeeDto");
        request.getSession().removeAttribute("createEmployeeDto");
    	String addressLine = request.getParameter("addressLine");
        String city = request.getParameter("city");
        String pincode = request.getParameter("pincode");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        
        AddressDTO addressDto = new AddressDTO(employeeDto, addressLine, city
        		, pincode, state, country);
        list.add(addressDto);
        employeeDto.setAddressDto(list);
        id = employeeService.createEmployee(employeeDto);
        
        if (0 < id) {
        	EmsLogger.info("Employee created");
        	response.sendRedirect("successMessage.jsp?message=Employee Details Created Successfully");
        } else {
        	EmsLogger.error("Employee not created");
        	request.getRequestDispatcher("employee.jsp").forward(request, response);
        }
    }
    
    /**
     * Getting all employees in the database
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void getAllEmployee(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	List<EmployeeDTO> employees = employeeService.getAllEmployee();
    	request.setAttribute("employees", employees);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("viewEmployee.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * Updating the all fields of an employee 
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void updateAllFields(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	EmployeeDTO employeeDto = (EmployeeDTO)    request.getSession().getAttribute("employeeDto");
    	
		employeeDto.setName(request.getParameter("name"));
		employeeDto.setSalary(Double.parseDouble(request.getParameter("salary")));
		employeeDto.setEmail(request.getParameter("email"));
		employeeDto.setPhoneNumber(Long.parseLong(request.getParameter("phoneNumber")));
		employeeDto.setDOB(LocalDate.parse((String) request.getParameter("DOB")));
        result = employeeService.updateAllFields(employeeDto);
        
    	if ((0 < result) && (request.getParameter("toUpdate").equals("Yes"))) {
    		request.setAttribute("action", "updateAddress");
    	    request.getRequestDispatcher("addressForm.jsp").forward(request, response);
    	} else if((0 < result) && (request.getParameter("toUpdate").equals("No"))) {
    		request.getSession().removeAttribute("employeeDto");
    		EmsLogger.info("Employee fields updated");
    		response.sendRedirect("successMessage.jsp?message=Employee updated Successfully");
    	} else {
    		EmsLogger.error("Employee not updated");
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    }
    
    /**
     * Updating All fields of an address
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void updateAddressFields(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	EmployeeDTO employeeDto = (EmployeeDTO) request.getSession().getAttribute("employeeDto");
    	
    	request.getSession().removeAttribute("employeeDto");
    	for (AddressDTO addressDto : employeeDto.getAddressDto()) {
    	    addressDto.setAddressLine(request.getParameter("addressLine"));
    	    addressDto.setCity(request.getParameter("city"));
    	    addressDto.setPincode(request.getParameter("pincode"));
    	    addressDto.setState(request.getParameter("state"));
    	    addressDto.setCountry(request.getParameter("country"));
    	}
    	result = employeeService.updateAllFields(employeeDto);
    	
    	if(0 < result) {
    		EmsLogger.info("Employee address fields updated");
    		response.sendRedirect("successMessage.jsp?message=Employee updated Successfully");
    	} else {
    		EmsLogger.error("Employee address not updated");
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    }
    
    /**
     * Deleting the required employee
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void deleteSingleEmployee(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        int rowsDeleted = employeeService.deleteSingleEmployee(id);
        
        if (0 < rowsDeleted) {
        	EmsLogger.info("Employee Deleted id: " + id);
        	response.sendRedirect("viewEmployee");
        } else {
        	EmsLogger.error("Employee not deleted");
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    /**
     * Deleting all employees in the database
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void deleteAllEmployee(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
        
        if (0 < employeeService.deleteAllEmployee()) {
        	EmsLogger.info("All Employees Deleted");
        	response.sendRedirect("successMessage.jsp?message=All Employees deleted Successfully");
        } else {
        	EmsLogger.error("Employees not deleted");
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    /**
     * Forwarding request to assign form to select projetcs 
     * for an employee
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void assignProjects(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        EmployeeDTO employeeDto = employeeService.getSingleEmployee(id);
        List<ProjectDTO> availableProjects = employeeService.getAvailableProjects(employeeDto);
        
        request.getSession().setAttribute("assignEmployeeDto", employeeDto);
        request.setAttribute("availableProjects", availableProjects);
        request.setAttribute("action", "allocateProject");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("assign-unassignProjectsForm.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * Allocating projects to the employee
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void allocateProjects(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	
    	if (null ==  request.getParameterValues("selectedProjects")) {
    		request.setAttribute("Message", "No Projects Selected");
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    	String[] selectedProjects = request.getParameterValues("selectedProjects");
    	EmployeeDTO employeeDto = (EmployeeDTO) request.getSession().getAttribute("assignEmployeeDto");
    	request.getSession().removeAttribute("assignEmployeeDto");
    	List<ProjectDTO> list = new ArrayList<>();
    	
    	for (String id : selectedProjects) {
    		ProjectDTO projectDto = new ProjectDTO();
    		projectDto.setId(Integer.parseInt(id));
    		list.add(projectDto);
    	}
    	employeeDto.getProjectsDto().addAll(list);
    	result = employeeService.updateAllFields(employeeDto);
    	
    	if (0 < result) {
    		EmsLogger.info("Project assigned for an Employee id " + employeeDto.getId());
    		response.sendRedirect("successMessage.jsp?message=Project assigned Successfully");
    	} else {
    		EmsLogger.error("Project not assigned for employee" + employeeDto.getId());
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    }
    
    /**
     * Forwarding request to unAssign form to remove projects 
     * for an employee
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void unAssignProjects(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	EmployeeDTO employeeDto = employeeService.getSingleEmployee(id);
    	Set<ProjectDTO> availableProjects = employeeDto.getProjectsDto();

    	request.getSession().setAttribute("unAssignEmployeeDto", employeeDto);
        request.setAttribute("availableProjects", availableProjects);
        request.setAttribute("action", "unAllocateProject");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("assign-unassignProjectsForm.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * UnAllocating projects to the employee
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    public void unAllocateProjects(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	
    	if (null ==  request.getParameterValues("selectedProjects")) {
    		request.setAttribute("Message", "No Projects Selected");
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    	String[] selectedProjects = request.getParameterValues("selectedProjects");
    	EmployeeDTO employeeDto = (EmployeeDTO) request.getSession().getAttribute("unAssignEmployeeDto");
    	request.getSession().removeAttribute("unAssignEmployeeDto");
    	List<ProjectDTO> list = new ArrayList<>();
    	
    	for (String id : selectedProjects) {
    		ProjectDTO projectDto = new ProjectDTO();
    		projectDto.setId(Integer.parseInt(id));
    		list.add(projectDto);
    	}
    	employeeDto.getProjectsDto().removeAll(list);
    	result = employeeService.updateAllFields(employeeDto);
    	
        if (0 < result) {
        	EmsLogger.info("Project unAssigned for an Employee id " + employeeDto.getId());
        	response.sendRedirect("successMessage.jsp?message=Project unAssigned Successfully");
    	} else {
    		EmsLogger.error("Project not unassigned for employee" + employeeDto.getId());
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    }
}
