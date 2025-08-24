package org.example.service;

import org.example.entity.Employee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {
    public List<Employee> removeEmployeeByName(List<Employee> employees, String name) {
        if (name == null || name.isBlank()) {
            throw new ServiceException("Name cannot be null or blank");
        }
        return employees.stream().filter(employee -> !employee.getName().equals(name))
                .collect(Collectors.toList());
    }

    public void increaseSalary(List<Employee> employees, Double percentage) {
        if (percentage <= 0) {
            throw new ServiceException("Percentage have to be greater than 0");
        }
        employees.forEach(employee -> employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1 + percentage))));
    }

    public Map<String, List<Employee>> groupEmployeesByRole(List<Employee> employees) {
        return employees.stream()
               .collect(Collectors.groupingBy(Employee::getRole));
    }

    public List<Employee> filterByBirthMonth(List<Employee> employees, List<Month> months) {
        if (months == null || months.isEmpty()) {
            throw new ServiceException("Months cannot be null or blank");
        }
        return employees.stream().filter(employee ->   {
                    Month month = employee.getDateOfBirth().getMonth();
                    for (Month m : months) {
                        if(month.equals(m)) {
                            return true;
                        }
                    }

                    return false;
                })
                .collect(Collectors.toList());
    }

    public BigDecimal totalSalaryOfAllEmployees(List<Employee> employees) {
       return employees.stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Employee getOldestEmployee(List<Employee> employees) {
        return employees.stream().reduce((em1, em2) -> em1.getDateOfBirth().isBefore(em2.getDateOfBirth()) ? em1 : em2).orElse(null);
    }

    public List<Employee> sortedByName(List<Employee> employees) {
        return employees.stream().sorted(Comparator.comparing(Employee::getName)).toList();
    }

    public BigDecimal calculateMinimumWages(Employee employee, BigDecimal salaryValue) {
        return employee.getSalary().divide(salaryValue, 2, RoundingMode.HALF_UP);
    }

}
