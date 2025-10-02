package org.swissre.service;


import org.swissre.model.Employee;
import org.swissre.util.ValidationResult;
import org.swissre.validator.ValidationRules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeAnalyzerService {
    private final List<ValidationRules> rules;

    public EmployeeAnalyzerService(List<ValidationRules> rules) {
        this.rules = rules;
    }

    public void analyze(Collection<Employee> employees) {
        List<ValidationResult> allResults = new ArrayList<>();

        for (Employee emp : employees) {
            for (ValidationRules rule : rules) {
                allResults.addAll(rule.validate(emp));
            }
        }

        // Group by severity
        Map<ValidationResult.Severity, List<ValidationResult>> grouped = allResults.stream()
                .collect(Collectors.groupingBy(ValidationResult::getSeverity));

        if (grouped.containsKey(ValidationResult.Severity.TOO_LITTLE)) {
            System.out.println("=== Managers earning TOO LITTLE ===");
            grouped.get(ValidationResult.Severity.TOO_LITTLE)
                    .forEach(r -> System.out.println(r.getMessage()));
            System.out.println();
        }

        if (grouped.containsKey(ValidationResult.Severity.TOO_MUCH)) {
            System.out.println("=== Managers earning TOO MUCH ===");
            grouped.get(ValidationResult.Severity.TOO_MUCH)
                    .forEach(r -> System.out.println(r.getMessage()));
            System.out.println();
        }

        if (grouped.containsKey(ValidationResult.Severity.TOO_DEEP)) {
            System.out.println("=== Employees with too long reporting lines ===");
            grouped.get(ValidationResult.Severity.TOO_DEEP)
                    .forEach(r -> System.out.println(r.getMessage()));
            System.out.println();
        }
    }
}
