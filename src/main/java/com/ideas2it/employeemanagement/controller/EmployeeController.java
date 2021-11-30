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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.impl.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.ideas2it.employeemanagement.exception.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLogger;

/**
 * <h1> Employees controller</h1>
 * Employee controller controls and manipulates the CRUD
 * operations and validation
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
@Controller
@SessionAttributes("employee")
public class EmployeeController extends HttpServlet {
	private EMSLogger EmsLogger = new EMSLogger(EmployeeController.class);
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
    /**
     * Getting employee details from the employee form and redirecting address form 
     *
     * @param employee employee details to be created
     * @param model handles the request attribute
     * @return  address form
     */
	@PostMapping(path="create")
    public String createEmployee(@ModelAttribute("employee") EmployeeDTO employee, Model model) {
		model.addAttribute("action", "createAddress");
		model.addAttribute("address", new AddressDTO());
		return "addressForm.jsp";
    }
    
    /**
     * forwarding the request to employee form for creating employee
     * 
     * @param model 
     * @return employee form to create employee
     */
    @RequestMapping(value = "employeeCreateForm")
    public String createEmployeeForm(Model model) {
    	model.addAttribute("action", "create");
    	model.addAttribute("employee", new EmployeeDTO());
    	return "employeeForm.jsp";
    }
    
    /**
     * forwarding the request to employee form for updating employee
     * 
     * @param id employee id to update
     * @param model 
     * @return employee form to get details
     */
    @GetMapping(value = "employeeUpdateForm")
    public String updateForm(@RequestParam int id, Model model) {
    	try {
    	    EmployeeDTO employeeDto = employeeService.getSingleEmployee(id);
    	
    	    model.addAttribute("action", "update");
    	    model.addAttribute("employee", employeeDto);
    	} catch (EMSException exception) {
    		EmsLogger.error(exception);
    	}
    	return "employeeForm.jsp";
    }
    
    /**
     * Creating the employee and storing in database
     * 
     * @param employee employee to be created
     * @param address of an employee
     * @param sessionStatus 
     * @return redirect site
     */
    @PostMapping(path = "createAddress")
    public String insertAddress(@SessionAttribute("employee") EmployeeDTO employee
    		, AddressDTO address, SessionStatus sessionStatus) {
    	String redirect = null;
    	List<AddressDTO> list = new ArrayList<>();
    	list.add(address);
    	
    	try {
            employee.setAddressDto(list);
            int id = employeeService.createEmployee(employee);
            sessionStatus.setComplete();
            if (0 < id) {
         	    EmsLogger.info("Employee created");
        	    redirect = "redirect:successMessage.jsp?message=Employee Details Created Successfully";
            } else {
        	    EmsLogger.error("Employee not created");
        	    redirect = "redirect:error.jsp?message=Employee creation failed";
            }
    	} catch (EMSException exception) {
    		EmsLogger.error(exception);
    	}
        return redirect;
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
    public String getAllEmployee(EmployeeDTO employee, Model model) {
    	try {
    	    List<EmployeeDTO> employees = employeeService.getAllEmployee();
    	    model.addAttribute("employees", employees);
    	} catch (EMSException exception) {
    		EmsLogger.error(exception);
    	}
    	return "viewEmployee.jsp";
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
    @PostMapping("update")
    public String updateAllFields(@RequestParam("toUpdate") String toUpdate
    		, @ModelAttribute("employee") EmployeeDTO employeeDto, Model model) throws EMSException{
    	int result;
    	String redirect = null;
    	
    	try {
            result = employeeService.updateAllFields(employeeDto);
        
    	    if ((0 < result) && (toUpdate.equals("Yes"))) {
    		    model.addAttribute("action", "updateAddress");
    		    model.addAttribute("address", employeeDto.getAddressDto().get(0));
    		    redirect = "addressForm.jsp";
    	    } else if((0 < result) && (toUpdate.equals("No"))) {
    		    EmsLogger.info("Employee fields updated");
    		    redirect = "redirect:successMessage.jsp?message=Employee updated Successfully";
    	    } else {
    		    EmsLogger.error("Employee not updated");
    		    redirect = "redirect:error.jsp?message=Employee updation failed";
     	    }
    	} catch (EMSException exception) {
    		EmsLogger.error(exception); 
    	}
    	return redirect;
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
    @PostMapping("updateAddress")
    public String updateAddressFields(@SessionAttribute("employee") EmployeeDTO employeeDto
    		, AddressDTO addressDto, SessionStatus sessionStatus) throws EMSException {
    	int result;
    	String redirect = null;

    	for (AddressDTO address : employeeDto.getAddressDto()) {
    	    address.setAddressLine(addressDto.getAddressLine());
    		address.setCity(addressDto.getCity());
    		address.setCountry(addressDto.getCountry());
    		address.setPincode(addressDto.getPincode());
    		address.setState(addressDto.getState());
    	}
    	
    	try {
    	    result = employeeService.updateAllFields(employeeDto);
    	    sessionStatus.setComplete();
    	    if(0 < result) {
    		    EmsLogger.info("Employee address fields updated");
    		    redirect = "redirect:successMessage.jsp?message=Employee updated Successfully";
    	    } else {
    		    EmsLogger.error("Employee address not updated");
    		    redirect = "redirect:error.jsp";
    	    } 
    	} catch (EMSException exception) {
    	    	EmsLogger.error(exception);
        }
    	return redirect;
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
    @GetMapping("delete")
    public String deleteSingleEmployee(@RequestParam("id") int id) {
    	String redirect = null;
    	try {
            int rowsDeleted = employeeService.deleteSingleEmployee(id);
        
            if (0 < rowsDeleted) {
        	    EmsLogger.info("Employee Deleted id: " + id);
        	    redirect = "redirect:viewEmployee";
            } else {
        	    EmsLogger.error("Employee not deleted");
        	    redirect = "redirect:error.jsp";
            }
        } catch (EMSException exception) {
        	EmsLogger.error(exception);
        }
		return redirect;
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
    @GetMapping("deleteAll")
    public String deleteAllEmployee() throws EMSException {
    	String redirect = null;
    	try {
            if (0 < employeeService.deleteAllEmployee()) {
        	    EmsLogger.info("All Employees Deleted");
        	    redirect = "redirect:successMessage.jsp?message=All Employees deleted Successfully";
            } else {
        	    EmsLogger.error("Employees not deleted");
        	    redirect = "redirect:error.jsp";
            }
    	} catch (EMSException exception) {
    		EmsLogger.error(exception);
    	}
        return redirect;
    }
    
    /**
     * Forwarding request to assign form to select projects 
     * for an employee
     * 
     * @param request
     * @param response
     * @throws EMSException
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("assign")
    public String assignProjects(@RequestParam("id") int id, ModelMap modelMap) {
    	try {
            EmployeeDTO employeeDto = employeeService.getSingleEmployee(id);
            List<ProjectDTO> availableProjects = employeeService.getAvailableProjects(employeeDto);
        
            modelMap.put("availableProjects", availableProjects);
            modelMap.put("action", "allocateProject");
            modelMap.addAttribute("employee", employeeDto);
    	} catch (EMSException exception) {
    		EmsLogger.error(exception);
    	}
        return "assign-unassignProjectsForm.jsp";
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
    @PostMapping("allocateProject")
    public String allocateProjects(@RequestParam(value = "selectedProjects", required = false) String[] selectedProjectIds
    		, @SessionAttribute("employee") EmployeeDTO employeeDto, SessionStatus sessionStatus) throws EMSException {
    	int result;
    	String redirect;
    	
    	if (null != selectedProjectIds) { 
    		employeeDto.getProjectsDto().addAll(employeeService.getSelectedProjectDtos(selectedProjectIds));
    	    result = employeeService.updateAllFields(employeeDto);
    	
    	    sessionStatus.setComplete();
    	    if (0 < result) {
    		    EmsLogger.info("Project assigned for an Employee id " + employeeDto.getId());
    		    redirect = "redirect:successMessage.jsp?message=Project assigned Successfully";
    	    } else {
    		    EmsLogger.error("Project not assigned for employee" + employeeDto.getId());
    		    redirect = "redirect:error.jsp";
    	    }
    	} else {
    		redirect = "redirect:error.jsp?message=No Projects Selected";
    	} 
    	return redirect;
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
    @GetMapping("unAssign")
    public String unAssignProjects(@RequestParam("id") int id, Model model) throws EMSException {
    	try {
    	    EmployeeDTO employeeDto = employeeService.getSingleEmployee(id);
    	    Set<ProjectDTO> availableProjects = employeeDto.getProjectsDto();
       
    	    model.addAttribute("availableProjects", availableProjects);
    	    model.addAttribute("action", "unAllocateProject");
    	    model.addAttribute("employee", employeeDto);
    	} catch (EMSException exception) {
    		EmsLogger.error(exception);
    	}
    	return "assign-unassignProjectsForm.jsp";
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
    @PostMapping("unAllocateProject")
    public String unAllocateProjects(@RequestParam(value = "selectedProjects", required = false) String[] selectedProjects
    		, @SessionAttribute("employee") EmployeeDTO employeeDto, SessionStatus sessionStatus)throws EMSException {
    	int result;
    	String redirect;
    	
    	if (null ==  selectedProjects) {
    		redirect = "redirect:error.jsp?message=No Projects Selected";
    	} else {
    	    employeeDto.getProjectsDto().removeAll(employeeService.getSelectedProjectDtos(selectedProjects));
    	    result = employeeService.updateAllFields(employeeDto);
    	    
    	    sessionStatus.setComplete();
            if (0 < result) {
        	    EmsLogger.info("Project unAssigned for an Employee id " + employeeDto.getId());
        	    redirect = "redirect:successMessage.jsp?message=Project unAssigned Successfully";
    	     } else {
    		    EmsLogger.error("Project not unassigned for employee" + employeeDto.getId());
    		    redirect = "redirect:error.jsp";
    	    }
    	}
        return redirect;
    }
}