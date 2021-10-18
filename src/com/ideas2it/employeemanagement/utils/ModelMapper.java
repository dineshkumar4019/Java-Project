package com.ideas2it.employeemanagement.utils;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Address;

public class ModelMapper {
    public EmployeeDTO toEmployeeDto(Employee employee) {
        //List<Address> addresses = new ArrayList<>();
        
        //for (Address entry : employee.getAddress()) {
        //    addresses.add(entry);
        //}
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getSalary(), employee.getEmail()
                           , employee.getPhoneNumber(), employee.getDOB());
    }
    
    public AddressDTO toAddressDto(Address address) {
        return new AddressDTO(address.getId(), address.getEmployeeId(), address.getAddress()
                              , address.getCity(), address.getPincode()
                              , address.getState(), address.getCountry());
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
