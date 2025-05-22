package com.example.RESTAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.RESTAPI.dto.EmployeeDTO;
import com.example.RESTAPI.model.Employee;
import com.example.RESTAPI.service.EmployeeService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> getAllEmployee() {
        return  employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable long id) {
        try {
            return employeeService.getEmployee(id);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found", ex);
        }
    }
    
    @GetMapping("/name")
    public String getName() {
        return "Employee Name";
    }

    // @PostMapping("/")
    // public Employee postEmployee(@RequestBody Employee employee) {
    //     return employeeService.createEmployee(employee);
    // }
    
    @PutMapping("/{id}")
    public Employee putEmployee(@PathVariable long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
    
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }

    @PostMapping("/")
    public EmployeeDTO postEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }
}
