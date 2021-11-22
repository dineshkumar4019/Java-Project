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
    /**
	 * 
	 */
	private static final long serialVersionUID = 102831973239L;;
    private EmployeeService employeeService;
	
	public void init() throws ServletException {
		super.init();
	    employeeService = new EmployeeService();
	}
	
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
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
	
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
	            case "/viewEmployee":
	        	    getAllEmployee(request, response);
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
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}
	
    /**
     * Checking an employee presences in database
     *
     * @param id employee id for checking existance
     * @return employee exist or not
     */
    public boolean isEmployeeExist(int id) throws EMSException {
        return employeeService.isEmployeeExist(id);
    }
    
    /**
     * Checking if employees present in database
     *
     * @return database empty or not
     */
    public long isDataBaseEmpty() throws EMSException {
        return employeeService.isDataBaseEmpty();
    }
    
    /**
     * Checking an address presences in database
     *
     * @param id address id for checking existance
     * @return address exist or not
     */
    public boolean isAddressExist(int addressId) throws EMSException {
        return employeeService.isAddressExist(addressId);
    }
    
    /**
     * Validating the corresponding employee name  
     *
     * @param name employee name for validation
     * @return name is valid or not
     */
    public boolean validateName(String name) {
        return employeeService.validateName(name);
    }
    
    /**
     * Validating the corresponding employee salary  
     *
     * @param salary employee salary for validation
     * @return salary is valid or not
     */
    public boolean validateSalary(double salary) {
        return employeeService.validateSalary(salary);
    }
    
    /**
     * Checking the corresponding email is present in database
     *
     * @param email email for checking existence
     * @return email exist or not
     */
    public boolean isEmailExist(String email) throws EMSException {
        return employeeService.isEmailExist(email);
    }
    
    /**
     * Validating the corresponding employee email 
     *
     * @param email employee email for validation
     * @return email is valid or not
     */
    public boolean validateEmail(String email) {
        return employeeService.validateEmail(email);
    }
    
    /**
     * Checking the corresponding phone number is present in database
     *
     * @param phoneNumber phoneNumber for checking existence
     * @return phoneNumber exist or not
     */
    public boolean isPhoneNumberExist(long phoneNumber) throws EMSException {
        return employeeService.isPhoneNumberExist(phoneNumber);
    }
    
    /**
     * Validating the corresponding employee phoneNumber 
     *
     * @param phoneNumber employee phoneNumber for validation
     * @return phoneNumber is valid or not
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        return employeeService.validatePhoneNumber(phoneNumber);
    }
    
    /**
     * Validating the corresponding employee address 
     *
     * @param address employee address for validation
     * @return address is valid or not
     */  
    public boolean validateAddress(String address) {
        return employeeService.validateAddress(address);
    }
    
    /**
     * Validating the corresponding employee city 
     *
     * @param city employee city for validation
     * @return city is valid or not
     */
    public boolean validateCity(String city) {
        return employeeService.validateCity(city);
    }
    
    /**
     * Validating the corresponding employee pincode 
     *
     * @param pincode employee pincode for validation
     * @return pincode is valid or not
     */
    public boolean validatePincode(String pincode) {
        return employeeService.validatePincode(pincode);
    }
    
    /**
     * Validating the corresponding employee state 
     *
     * @param state employee state for validation
     * @return state is valid or not
     */
    public boolean validateState(String state) {
        return employeeService.validateState(state);
    }
    
    /**
     * Validating the corresponding employee country 
     *
     * @param country employee country for validation
     * @return country is valid or not
     */
    public boolean validateCountry(String country) {
        return employeeService.validateAddress(country);
    }
    
    /**
     * validate ids of projects 
     *
     * @param ids ids of projects
     * @return ids valid or not
     */
    public boolean validateIds(String[] ids) {
        return employeeService.validateIds(ids);
    }
    
    /**
     * converting the String to local date format
     * 
     * @param DOB date of birth to convert to local date format
     * @return DOB in local date format
     */
    public LocalDate stringDateConversion(String DOB) {
        return employeeService.stringDateConversion(DOB);
    }
  
    /**
     * getting the age of an employee
     *
     * @param DOB date of birth for the age
     * @return age of correcponding employee
     */
    public int getEmployeeAge(String DOB) {
        return employeeService.getEmployeeAge(DOB);
    }
    
    /**
     * Creating the employee and storing in database
     *
     * @param employeeDto employee details to be created
     * @return Number of employees added
     * @throws IOException 
     */
    public void createEmployee(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        long phoneNumber = Long.parseLong((String) request.getParameter("phoneNumber"));
        String dataOfBirth = request.getParameter("DOB");
        LocalDate DOB = LocalDate.parse(dataOfBirth);
     
        EmployeeDTO employeeDto = new EmployeeDTO(name, phoneNumber, email, phoneNumber, DOB); 
    	int id = employeeService.createEmployee(employeeDto);
    	employeeDto.setId(id);
    	if (0 < id) {
    		request.getSession().setAttribute("createEmployeeDto", employeeDto);
    		request.setAttribute("action", "createAddress");
    	    request.getRequestDispatcher("addressForm.jsp").forward(request, response);
    	} else {
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    }
    
    public void createEmployeeForm(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	request.setAttribute("action", "create");
    	request.getRequestDispatcher("employeeForm.jsp").forward(request, response);
    }
    
    public void createAddressForm(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	request.setAttribute("action", "createAddress");
    	request.getRequestDispatcher("addressForm.jsp").forward(request, response);
    }
    
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
     * @param employeeDto employee details to be created
     * @return Number of employees added
     */
    public void insertAddress(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id;
        EmployeeDTO employeeDto= (EmployeeDTO) request.getSession().getAttribute("createEmployeeDto");
    	String addressLine = request.getParameter("addressLine");
        String city = request.getParameter("city");
        String pincode = request.getParameter("pincode");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        
        AddressDTO addressDto = new AddressDTO(employeeDto, addressLine, city
        		, pincode, state, country);
        
        id = employeeService.insertAddress(addressDto);
        if (0 < id) {
        	request.setAttribute("Message", "Employee Details Created Successfully!!!");
            request.getRequestDispatcher("successMessage.jsp").forward(request, response);
        } else {
        	request.getRequestDispatcher("employee.jsp").forward(request, response);
        }
    }
    
    /**
     * Getting the particular employee by id
     *
     * @param id employee id to get employee details
     * @return particular employee details
     */
    public void getSingleEmployee(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException {
    	int id = Integer.parseInt(request.getParameter("id"));
        EmployeeDTO employeeDto = employeeService.getSingleEmployee(id);
        
        request.setAttribute("employee", employeeDto);
    }
    
    /**
     * Getting all projects in the database
     *
     * @return All projects details
     */
    public List<ProjectDTO> getAllProjects() throws EMSException {
        return employeeService.getAllProjects();
    }
    
    /**
     * Getting the particular address by id
     *
     * @param id address id to get employee details
     * @return particular address details
     */
    public AddressDTO getAddress(int id) throws EMSException {
        return employeeService.getAddress(id);
    }
    
    /**
     * Getting all employees in the database
     *
     * @return All employee details
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
     * @param employeeDto employee details to be updated
     * @return Number of rows updated
     * @throws IOException 
     */
    public void updateAllFields(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	HttpSession session = request.getSession();
    	EmployeeDTO employeeDto = (EmployeeDTO) session.getAttribute("employeeDto");
    	
		employeeDto.setName(request.getParameter("name"));
		employeeDto.setEmail(request.getParameter("email"));
		employeeDto.setPhoneNumber(Long.parseLong(request.getParameter("phoneNumber")));
		employeeDto.setDOB(LocalDate.parse((String) request.getParameter("DOB")));
        result = employeeService.updateAllFields(employeeDto);
        
    	if ((0 < result) && (request.getParameter("toUpdate").equals("Yes"))) {
    		request.setAttribute("action", "updateAddress");
    	    request.getRequestDispatcher("addressForm.jsp").forward(request, response);
    	    
    	} else if((0 < result) && (request.getParameter("toUpdate").equals("No"))) {
    		request.setAttribute("Message", "Employee updated Successfully!!!");
    		request.getRequestDispatcher("successMessage.jsp").forward(request, response);
    	} else {
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    }
    
    public void updateAddressFields(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	EmployeeDTO employeeDto = (EmployeeDTO) request.getSession().getAttribute("employeeDto");
    	
    	for (AddressDTO addressDto : employeeDto.getAddressDto()) {
    	    addressDto.setAddressLine(request.getParameter("addressLine"));
    	    addressDto.setCity(request.getParameter("city"));
    	    addressDto.setPincode(request.getParameter("pincode"));
    	    addressDto.setState(request.getParameter("state"));
    	    addressDto.setCountry(request.getParameter("country"));
    	}
    	result = employeeService.updateAllFields(employeeDto);
    	
    	if(0 < result) {
    		request.setAttribute("Message", "Employee updated Created Successfully!!!");
    		request.getRequestDispatcher("successMessage.jsp").forward(request, response);
    	} else {
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    }
    
    /**
     * Deleting the required employee
     *
     * @param id id for deleting the employee
     * @return Number of rows deleted
     * @throws IOException 
     */
    public void deleteSingleEmployee(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        int rowsDeleted = employeeService.deleteSingleEmployee(id);
        
        if (0 < rowsDeleted) {
        	response.sendRedirect("viewEmployee");
        } else {
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    /**
     * Deleting all employees in the database
     *
     * @return Number of rows deleted
     * @throws IOException 
     */
    public void deleteAllEmployee(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
        
        if (0 < employeeService.deleteAllEmployee()) {
        	request.setAttribute("Message", "All Employees deleted Successfully!!!");
    		request.getRequestDispatcher("successMessage.jsp").forward(request, response);
        } else {
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    /**
     * Deleting the required address
     *
     * @param id address id for deleting the address
     * @return total rows deleted
     */
    public int deleteAddress(int addressId) throws EMSException {
        return employeeService.deleteAddress(addressId);
    }
    
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
    
    public void allocateProjects(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	
    	if (null ==  request.getParameterValues("selectedProjects")) {
    		request.setAttribute("Message", "No Projects Selected");
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    	String[] selectedProjects = request.getParameterValues("selectedProjects");
    	EmployeeDTO employeeDto = (EmployeeDTO) request.getSession().getAttribute("assignEmployeeDto");
    	List<ProjectDTO> list = new ArrayList<>();
    	
    	for (String id : selectedProjects) {
    		ProjectDTO projectDto = new ProjectDTO();
    		projectDto.setId(Integer.parseInt(id));
    		list.add(projectDto);
    	}
    	employeeDto.getProjectsDto().addAll(list);
    	result = employeeService.updateAllFields(employeeDto);
    	
    	if (0 < result) {
    		request.setAttribute("Message", "Project assigned Successfully!!!");
    		request.getRequestDispatcher("successMessage.jsp").forward(request, response);
    	} else {
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    }
    
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
    
    public void unAllocateProjects(HttpServletRequest request, HttpServletResponse response)
    		throws EMSException, ServletException, IOException {
    	int result;
    	
    	if (null ==  request.getParameterValues("selectedProjects")) {
    		request.setAttribute("Message", "No Projects Selected");
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    	String[] selectedProjects = request.getParameterValues("selectedProjects");
    	EmployeeDTO employeeDto = (EmployeeDTO) request.getSession().getAttribute("unAssignEmployeeDto");
    	List<ProjectDTO> list = new ArrayList<>();
    	
    	for (String id : selectedProjects) {
    		ProjectDTO projectDto = new ProjectDTO();
    		projectDto.setId(Integer.parseInt(id));
    		list.add(projectDto);
    	}
    	employeeDto.getProjectsDto().removeAll(list);
    	result = employeeService.updateAllFields(employeeDto);
    	
        if (0 < result) {
        	request.setAttribute("Message", "Project unAssigned Successfully!!!");
    		request.getRequestDispatcher("successMessage.jsp").forward(request, response);
    	} else {
    		request.getRequestDispatcher("error.jsp").forward(request, response);
    	}
    }
}
