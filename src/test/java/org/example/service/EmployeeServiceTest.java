package org.example.service;

import org.example.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private EmployeeService service;
    private List<Employee> employees;

    @BeforeEach
    void setup() {
        service = new EmployeeService();
        employees = List.of(
                new Employee("João", LocalDate.of(1990, 1, 1), new BigDecimal("1000.00"), "Operador"),
                new Employee("Maria", LocalDate.of(1985, 10, 15), new BigDecimal("2000.00"), "Gerente"),
                new Employee("Pedro", LocalDate.of(1995, 12, 20), new BigDecimal("1500.00"), "Operador")
        );
    }


    @Test
    void testRemoveEmployeeByName() {
        List<Employee> newList = service.removeEmployeeByName(employees, "João");
        assertEquals(2, newList.size());
        assertFalse(newList.stream().anyMatch(employee -> employee.getName().equals("João")));

        assertTrue(newList.stream().anyMatch(employee -> employee.getName().equals("Maria")));
        assertTrue(newList.stream().anyMatch(employee -> employee.getName().equals("Pedro")));
    }

    @Test
    void removeEmployeeByName_WithNoName_ShouldThrow() {
        assertThrows(ServiceException.class, () -> service.removeEmployeeByName(employees, ""));
        assertThrows(ServiceException.class, () -> service.removeEmployeeByName(employees, null));
    }

    @Test
    void testIncreaseSalary() {
        service.increaseSalary(employees, 0.10);
        assertEquals(0, new BigDecimal("1100.00").compareTo(employees.get(0).getSalary()));
        assertEquals(0, new BigDecimal("2200.00").compareTo(employees.get(1).getSalary()));
        assertEquals(0, new BigDecimal("1650.00").compareTo(employees.get(2).getSalary()));
    }

    @Test
    void testIncreaseSalary_WithNoPercentage_ShouldThrow() {
        assertThrows(ServiceException.class ,() -> service.increaseSalary(employees, 0.00));
    }

    @Test
    void testGroupEmployeesByRole() {
        Map<String, List<Employee>> map = service.groupEmployeesByRole(employees);
        assertEquals(2, map.get("Operador").size());
        assertEquals(1, map.get("Gerente").size());
        assertEquals(2, map.size());
    }

    @Test
    void testFilterByBirthMonth() {
        List<Employee> employee = service.filterByBirthMonth(employees, List.of(Month.OCTOBER, Month.DECEMBER));
        assertEquals(2, employee.size());
        assertEquals("Maria", employee.get(0).getName());
        assertEquals("Pedro", employee.get(1).getName());
    }

    @Test
    void testFilterByBirthMonth_WithNoMonths_ShouldThrow() {
        assertThrows(ServiceException.class ,() -> service.filterByBirthMonth(employees, null));
        assertThrows(ServiceException.class, () -> service.filterByBirthMonth(employees, List.of()));
    }

    @Test
    void testTotalSalaryOfEmployees() {
        BigDecimal totalSalary = service.totalSalaryOfAllEmployees(employees);
        assertEquals(0, new BigDecimal("4500.00").compareTo(totalSalary));
    }

    @Test
    void testGetOldestEmployee() {
        Employee employee = service.getOldestEmployee(employees);
        assertEquals(employees.get(1), employee);
    }

    @Test
    void testSortByName() {
        List<Employee> unsorted = List.of(
                new Employee("Pedro", LocalDate.of(1990, 1, 1), new BigDecimal("1000.00"), "Operador"),
                new Employee("Maria", LocalDate.of(1985, 10, 15), new BigDecimal("2000.00"), "Gerente"),
                new Employee("Joca", LocalDate.of(1995, 12, 20), new BigDecimal("1500.00"), "Operador")
        );

        List<Employee> sorted = service.sortedByName(unsorted);

        assertEquals(3, unsorted.size());
        assertEquals("Joca", sorted.get(0).getName());
        assertEquals("Maria", sorted.get(1).getName());
        assertEquals("Pedro", sorted.get(2).getName());
    }

    @Test
    void testCalculateWages() {
        BigDecimal minimumWage = new BigDecimal("1212.00");
        BigDecimal wages = service.calculateMinimumWages(employees.get(0), minimumWage);
        assertEquals(new BigDecimal("0.83"), wages);
    }

}
