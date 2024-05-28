package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

/**
 * EmployeeServiceImpl
 * an implementation of the employee service
 * it is responsible for handling the business logic for the employee entity
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * setEmployeeRepository
     * setter for the employee repository
     * 
     * @param employeeRepository
     */
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * retrieveEmployees
     * a method to retrieve all the employees
     * 
     * @return a list of employees
     */
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    /**
     * getEmployee
     * a method to retrieve a single employee
     * 
     * @param employeeId
     * @return Employee
     */
    @Cacheable(value = "employees", key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.get();
    }

    /**
     * saveEmployee
     * a method to save an employee
     * 
     * @param employee
     * @return boolean
     */
    @CachePut(value = "employees", key = "#employee.id")
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * deleteEmployee
     * a method to delete an employee
     * 
     * @param employeeId
     */
    @CacheEvict(value = "employees", key = "#employeeId")
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    /**
     * updateEmployee
     * a method to update an employee
     * 
     * @param employee
     */
    @CachePut(value = "employees", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}