package org.swissre.validator;


import org.swissre.model.Employee;
import org.swissre.util.ValidationResult;

import java.util.List;

/**
 * Interface for all validation rules (e.g., salary check, reporting line check).
 */
public interface ValidationRules {
    List<ValidationResult> validate(Employee employee);
}
