package com.spring.rest.controller;

import com.spring.rest.entity.Employee;
import com.spring.rest.exception_handling.EmployeeIncorrectData;
import com.spring.rest.exception_handling.NoSuchEmployeeException;
import com.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees()
    {
        List<Employee> allE = employeeService.getAllEmployees();
        return allE;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id)
    {
        Employee emp = employeeService.getEmployee(id);

        if (emp == null)
        {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in DB");
        }

        return emp;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        employeeService.saveEmpl(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee)
    {
        employeeService.saveEmpl(employee);//т.к. внутри saveOrUpdate
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id)
    {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null)
        {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in DB");
        }
        employeeService.deleteEmployee(id);
        return "Empl whit ID = " + id + " was deleted";
    }

}
