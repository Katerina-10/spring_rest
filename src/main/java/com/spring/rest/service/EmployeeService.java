package com.spring.rest.service;

import com.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public void saveEmpl(Employee employee);

    public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
