package org.swissre.validator;

import org.swissre.model.Employee;
import org.swissre.util.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Rule: Employee more than 4 levels deep in Reporting Line from CEO Should be Highlighted with depth (By How Much).
 */
public class EmployeeReportingLineRule implements ValidationRule {

    @Override
    public List<ValidationResult> validate(Employee employee) {
        List<ValidationResult> results = new ArrayList<>();

        int depth = 0;
        Employee current = employee.getManager();
        while (current != null) {
            depth++;
            current = current.getManager();
        }
        if (depth > 4) {
            results.add(new ValidationResult(
                    ValidationResult.Severity.TOO_DEEP,
                    String.format("Employee %s has too long reporting line by %d level%s", employee.getFullName(), depth - 4, (depth - 4) > 1 ? "s" : "")));
        }

        return results;
    }
}