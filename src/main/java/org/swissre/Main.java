package org.swissre;

import org.swissre.model.Employee;
import org.swissre.service.EmployeeAnalyzerService;
import org.swissre.util.CSVReader;
import org.swissre.validator.EmployeeReportingLineRules;
import org.swissre.validator.SalaryValidationRules;
import org.swissre.validator.ValidationRules;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the path to the employees CSV file as an argument.");
            System.out.println("Example: data/employees.csv");
            return;
        }

        String filePath = args[0];

        try {
            Map<String, Employee> employeeMap = CSVReader.readEmployees(filePath);

            List<ValidationRules> rules = List.of(
                    new SalaryValidationRules(),
                    new EmployeeReportingLineRules()
            );

            EmployeeAnalyzerService analyzer = new EmployeeAnalyzerService(rules);
            analyzer.analyze(employeeMap.values());

        } catch (IOException e) {
            System.err.println("Failed to read the CSV file: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}