package org.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    Employee employee;
    @BeforeEach
    void setUp() {
        employee = new Employee("João", LocalDate.of(1990, 1, 1), new BigDecimal("1000.00"), "Operador");
    }

    @Test
    void testValidEmployee() {
        assertEquals("João", employee.getName());
        assertEquals(new BigDecimal("1000.00"), employee.getSalary());
        assertEquals("Operador", employee.getRole());
    }

    @Test
    void testSetSalary() {
        assertThrows(EntityException.class, () -> employee.setSalary(new BigDecimal("0")));
        assertThrows(EntityException.class, () -> employee.setSalary(null));

        employee.setSalary(new BigDecimal("2000.00"));
        assertEquals(0, employee.getSalary().compareTo(new BigDecimal("2000.00")));
    }

    @Test
    void testSetRole() {
        assertThrows(EntityException.class, () -> employee.setRole(""));
        assertThrows(EntityException.class, () -> employee.setRole(null));
        employee.setRole("Operador");
        assertEquals("Operador", employee.getRole());
    }

    @Test
    void testSetName() {
        assertThrows(EntityException.class, () -> employee.setName(""));
        assertThrows(EntityException.class, () -> employee.setName("Op"));
        employee.setName("Teste");
        assertEquals("Teste",  employee.getName());
    }

    @Test
    void testSetDateOfBirth() {
        assertThrows(EntityException.class, () -> employee.setDateOfBirth(LocalDate.now().plusYears(121)));
        assertThrows(EntityException.class, () -> employee.setDateOfBirth(LocalDate.now().minusYears(121)));
        LocalDate now = LocalDate.now();
        employee.setDateOfBirth(now);
        assertEquals(now , employee.getDateOfBirth());
    }
}
