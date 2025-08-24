package org.example;

import org.example.entity.Employee;

import java.util.List;
import java.util.Map;

public class UI {

    public static void printEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static void printGroupedEmployees(Map<String, List<Employee>> employees) {
        employees.forEach((role, list) -> {
            System.out.println(role);
            list.forEach(System.out::println);
        });
    }
}
