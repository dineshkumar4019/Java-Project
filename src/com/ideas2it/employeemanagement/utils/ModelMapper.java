package com.ideas2it.employeemanagement.utils;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;

public class ModelMapper {
    public EmployeeDTO toEmployeeDto(Employee employee) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setDOB(employee.getDOB());

        return employeeDto;
    }
    
    public Employee toEmployee(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setDOB(employeeDto.getDOB());

        return employee;
    }
}
