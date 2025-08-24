package org.example;

import org.example.entity.Employee;
import org.example.entity.EntityException;
import org.example.repository.EmployeeRepository;
import org.example.repository.RepositoryException;
import org.example.service.EmployeeService;
import org.example.service.ServiceException;
import org.example.utils.Formatters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            EmployeeRepository repository = new EmployeeRepository();
            EmployeeService service = new EmployeeService();
            List<Employee> employees = repository.loadEmployees("employees.csv");

            employees = service.removeEmployeeByName(employees, "João");

            UI.printEmployees(employees);
            service.increaseSalary(employees, 0.10);

            System.out.println("\nAgrupados por função");
            Map<String, List<Employee>> employeeMap = service.groupEmployeesByRole(employees);
            UI.printGroupedEmployees(employeeMap);

            System.out.println("\nFiltrados pelo mes 10 e 12");
            List<Employee> filteredByMonth = service.filterByBirthMonth(employees, List.of(Month.OCTOBER, Month.DECEMBER));
            UI.printEmployees(filteredByMonth);

            System.out.println("\nMais velho");
            Employee oldest = service.getOldestEmployee(employees);
            System.out.println("Nome: " + oldest.getName() + " Idade: " + (LocalDate.now().getYear() - oldest.getDateOfBirth().getYear()));

            System.out.println("\nOrdenada por nome");
            List<Employee> orderedEmployees = service.sortedByName(employees);
            UI.printEmployees(orderedEmployees);

            System.out.println("\nValor total de salarios");
            BigDecimal totalSalary = service.totalSalaryOfAllEmployees(orderedEmployees);
            System.out.println(Formatters.brPrice(totalSalary));

            System.out.println("\nQuantos salarios cada colaborador ganha");
            BigDecimal salaryValue = new BigDecimal("1212.00");
            employees.forEach((employee) -> {
                BigDecimal sal = service.calculateMinimumWages(employee, salaryValue);
                System.out.println(employee.getName() + " recebe " + Formatters.brPrice(sal));
            });
        } catch (EntityException | ServiceException | RepositoryException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }
}