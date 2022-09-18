package com.avi.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.avi.springbootdemo.model.Employee;
import com.avi.springbootdemo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    @Qualifier("employeeServiceImpl")
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteEmployeeById(@PathVariable String id) {
        return employeeService.deleteEmployeeById(id);
    }
}
