package org.example.repository;

import org.example.Main;
import org.example.entity.Employee;
import org.example.utils.Formatters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    public List<Employee> loadEmployees(String fileName) {
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RepositoryException("Unable to load employees file");
            }

            ArrayList<Employee> employees = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            String line =  bufferedReader.readLine(); //pula primeira linha

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Employee employee = new Employee(data[0], LocalDate.parse(data[1], Formatters.brDateLocalDatePattern()), new BigDecimal(data[2]), data[3]);
                employees.add(employee);
            }

            return employees;
        } catch (IOException e) {
            throw new RepositoryException("Unable to load employees file");
        }
    }
}
