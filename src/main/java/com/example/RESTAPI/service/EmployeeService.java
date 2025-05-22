package com.example.RESTAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.RESTAPI.dto.EmployeeDTO;
import com.example.RESTAPI.mapper.EmployeeMapper;
import com.example.RESTAPI.model.Employee;
import com.example.RESTAPI.repository.EmployeeRepository;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    
    // Example method to get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }
    
    // Example method to add a new employee
    // public Employee createEmployee(Employee employee) {
    //     return employeeRepository.save(employee);
    // }
    
    // Example method to update an existing employee
    @Transactional
    public Employee updateEmployee(long id, Employee employee) {
        return employeeRepository.findById(id)
            .map(existingEmployee -> {
                if (employee.getName() != null) existingEmployee.setName(employee.getName());
                if (employee.getDesignation() != null) existingEmployee.setDesignation(employee.getDesignation());
                if (employee.getDepartment() != null) existingEmployee.setDepartment(employee.getDepartment());
                if (employee.getAddress() != null) existingEmployee.setAddress(employee.getAddress());
                return employeeRepository.save(existingEmployee);
            })
            .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }
    
    // Example method to delete an employee
    public void deleteEmployee(long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id " + id);
        }
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(savedEmployee);
    } 
}
