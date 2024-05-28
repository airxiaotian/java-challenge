package jp.co.axa.apidemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.constants.UrlConstants;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * EmployeeController
 * a rest controller for the employee entity
 * it is responsible for handling the requests and responses for the employee
 */
@RestController
@RequestMapping(UrlConstants.API_V1)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * setEmployeeService
     * setter for the employee service
     * 
     * @param employeeService
     */
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * getEmployees
     * a get request to retrieve all the employees
     * 
     * @return List<Employee>
     */
    @GetMapping(UrlConstants.EMPLOYEES)
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    /**
     * getEmployee
     * a get request to retrieve a single employee
     * 
     * @param employeeId
     * @return Employee
     */
    @GetMapping(UrlConstants.EMPLOYEES_ID)
    public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    /**
     * saveEmployee
     * a post request to save an employee
     * 
     * @param employee
     */
    @PostMapping(UrlConstants.EMPLOYEES)
    public void saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
    }

    /**
     * deleteEmployee
     * a delete request to delete an employee
     * 
     * @param employeeId
     */
    @DeleteMapping(UrlConstants.EMPLOYEES_ID)
    public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }

    /**
     * updateEmployee
     * a put request to update an employee
     * 
     * @param employee
     * @param employeeId
     */
    @PutMapping(UrlConstants.EMPLOYEES_ID)
    public void updateEmployee(@RequestBody Employee employee,
            @PathVariable(name = "employeeId") Long employeeId) {
        Employee emp = employeeService.getEmployee(employeeId);
        if (emp != null) {
            employeeService.updateEmployee(employee);
        }

    }

}
