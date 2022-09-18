package com.avi.springbootdemo.service;

import java.util.List;

import com.avi.springbootdemo.model.Employee;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    String deleteEmployeeById(String id);
}
