package jp.co.axa.apidemo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTests {

    // mock the repository
    @Mock
    private EmployeeRepository mockRepository;
    // test the service
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    // test all methods in EmployeeService
    @Test
    public void testRetrieveEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        employee.setSalary(1000);
        employee.setDepartment("Engineering");
        employees.add(employee);
        employee = new Employee();
        employee.setId(2L);
        employee.setName("Jane");
        employee.setSalary(2000);
        employee.setDepartment("human resources");
        employees.add(employee);

        when(mockRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.retrieveEmployees();
        assertThat(result).isEqualTo(employees);
    }

    @Test
    public void testGetEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        employee.setSalary(1000);
        employee.setDepartment("Engineering");
        Optional<Employee> employeeOptional = Optional.of(employee);

        when(mockRepository.findById(1L)).thenReturn(employeeOptional);
        assertEquals(employee, employeeService.getEmployee(1L));
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        employee.setSalary(1000);
        employee.setDepartment("Engineering");

        when(mockRepository.save(employee)).thenReturn(employee);
        Employee result = employeeService.saveEmployee(employee);
        assertEquals(employee, result);
    }

    @Test
    public void testDeleteEmployee() {
        doNothing().when(mockRepository).deleteById(1L);
        employeeService.deleteEmployee(1L);
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        employee.setSalary(1000);
        employee.setDepartment("Engineering");

        employeeService.updateEmployee(employee);
    }

}
