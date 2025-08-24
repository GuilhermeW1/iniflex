package org.example.repository;

import org.example.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepositoryTest {
    @Test
    void testLoadEmployees() {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        List<Employee> employees = employeeRepository.loadEmployees("test-employees.csv");

        assertEquals(3, employees.size());
        assertEquals("Maria", employees.get(0).getName());
        assertEquals("João", employees.get(1).getName());
        assertEquals("Caio", employees.get(2).getName());

        assertThrows(RepositoryException.class, () -> employeeRepository.loadEmployees("wrong-file.csv"));
    }


}
