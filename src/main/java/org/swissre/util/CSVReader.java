package org.swissre.util;

import org.swissre.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Reads employee data from CSV and builds manager-subordinate relationships.
 */
public class CSVReader {

    public static Map<String, Employee> readEmployees(String csvFilePath) throws IOException {
        Map<String, Employee> employees = getEmployees(csvFilePath);

        // Build the hierarchy (set managers and subordinates)
        for (Employee emp : employees.values()) {
            String managerId = emp.getManagerId();
            if (managerId != null) {
                Employee manager = employees.get(managerId);
                if (manager == null) {
                    throw new IllegalStateException("Manager ID not found for employee: " + emp.getId());
                }
                emp.setManager(manager);           // set direct manager
                manager.addSubordinate(emp);       // manager adds this as subordinate
            }
        }

        return employees;
    }

    private static Map<String, Employee> getEmployees(String csvFilePath) throws IOException {
        Map<String, Employee> employees = new HashMap<>();

        // Read file line by line
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 5) {
                    throw new IllegalStateException("Invalid CSV line: " + line);
                }

                String id = parts[0].trim();
                String firstName = parts[1].trim();
                String lastName = parts[2].trim();
                double salary = Double.parseDouble(parts[3].trim());
                String managerId = parts[4].trim();
                if (managerId.isEmpty()) managerId = null;

                Employee employee = new Employee(id, firstName, lastName, salary, managerId);
                employees.put(id, employee);
            }
        }
        return employees;
    }
}