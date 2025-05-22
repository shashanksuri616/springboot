package com.example.RESTAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RESTAPI.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // This interface will automatically provide CRUD operations for Employee entity
    // No need to implement any methods here
    // Spring Data JPA will handle it for you

    
}
