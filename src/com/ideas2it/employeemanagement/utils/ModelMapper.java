/*
 * Copyright (c) ideas2it.
 *
 * Date: 27/08/2021
 */
package com.ideas2it.employeemanagement.utils;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Address;

/**
 * Converts employee to employeeDto and vice versa,
 * converts address to addressDto and vice versa. 
 * 
 * @author	Dinesh Kumar
 * @version	1.0
 * 
 */
public class ModelMapper {
    /**
     *  Converts employee to employeeDto 
     */
    public EmployeeDTO toEmployeeDto(Employee employee) {
        List<AddressDTO> addressDto = new ArrayList<>();
        EmployeeDTO employeeDto = new EmployeeDTO();
        
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setDOB(employee.getDOB());
        if (null != employee.getAddress()) {
            for (Address address : employee.getAddress()) {
                addressDto.add(toAddressDto(address));
            }
            employeeDto.setAddressDto(addressDto);
        }
        return employeeDto;
    }
    
    /**
     *  Converts address to addressDto 
     */
    public AddressDTO toAddressDto(Address address) {
        return new AddressDTO(address.getId(), address.getEmployeeId(), address.getAddress()
                              , address.getCity(), address.getPincode()
                              , address.getState(), address.getCountry());
    }
    
    /**
     *  Converts employeeDto to employee 
     */
    public Employee toEmployee(EmployeeDTO employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getSalary(), employeeDto.getEmail()
                            , employeeDto.getPhoneNumber(), employeeDto.getDOB());
    }
    
    /**
     *  Converts addressDto to address
     */
    public Address toAddress(AddressDTO addressDto) {
        return new Address(addressDto.getId(), addressDto.getEmployeeId(), addressDto.getAddress()
                           , addressDto.getCity(), addressDto.getPincode()
                           , addressDto.getState(), addressDto.getCountry());
    }
}
