package com.example.springbootbackend.controller;

import com.example.springbootbackend.exception.ResourceNotFound;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employees rest api
    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    //create employee rest api
    @PostMapping("/add-employees")
    public Employee createEmployee(@RequestBody Employee employee){
        System.out.println("Received employee object: " + employee);
        return employeeRepository.save(employee);
    }

    //get employee rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        System.out.println("ID value: " + id); // log the value of id
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Employee not exist with id"+id));

        return ResponseEntity.ok(employee);
    }

    //update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
        System.out.println("ID value: " + id); // log the value of id
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Employee not exist with id"+id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
}
