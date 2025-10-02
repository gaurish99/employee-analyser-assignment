package org.swissre.util;

/**
 * Represents a validation result from a rule (e.g., salary too low).
 */
public class ValidationResult {
    public enum Severity {TOO_LITTLE, TOO_MUCH, TOO_DEEP}

    private final Severity severity;
    private final String message;

    public ValidationResult(Severity severity, String message) {
        this.severity = severity;
        this.message = message;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }
}
