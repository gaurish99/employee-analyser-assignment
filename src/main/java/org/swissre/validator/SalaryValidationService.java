package org.swissre.validator;

import org.swissre.model.Employee;
import org.swissre.util.ValidationResult;

import java.util.ArrayList;
import java.util.List;


/**
 * Rule: Manager must earn more than 20% and less than equal to 50% more than average of direct subordinates.
 */
public class SalaryValidationService implements ValidationRule {

    @Override
    public List<ValidationResult> validate(Employee manager) {
        List<ValidationResult> results = new ArrayList<>();
        List<Employee> subs = manager.getSubordinates();
        if (subs.isEmpty()) return results;

        double avg = subs.stream().mapToDouble(Employee::getSalary).average().orElse(0);
        double min = avg * 1.2;
        double max = avg * 1.5;
        double salary = manager.getSalary();

        if (salary < min) {
            results.add(new ValidationResult(
                    ValidationResult.Severity.TOO_LITTLE,
                    String.format("Manager %s earns TOO LITTLE by %.2f", manager.getFullName(), min - salary)));
        } else if (salary > max) {
            results.add(new ValidationResult(
                    ValidationResult.Severity.TOO_MUCH,
                    String.format("Manager %s earns TOO MUCH by %.2f", manager.getFullName(), salary - max)));
        }
        return results;
    }
}