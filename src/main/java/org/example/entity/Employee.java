package org.example.entity;

import org.example.utils.Formatters;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee extends Person {
    private BigDecimal salary;
    private String role;

    public Employee(String name, LocalDate dateOfBirth, BigDecimal salary, String role) {
        super(name, dateOfBirth);
        this.salary = salary;
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        if (salary == null || salary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new EntityException("Salary must be greater than zero");
        }
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isBlank()) {
            throw new EntityException("Role cannot be blank");
        }

        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Nome=" + super.getName() +
                ", Data Nascimento=" + Formatters.localDateToStringBrPattern(super.getDateOfBirth()) +
                ", Salário=" + Formatters.brPrice(salary) +
                ", Função=" + role  +
                '}';
    }
}
