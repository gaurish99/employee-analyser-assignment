package org.swissre.service;

import org.junit.Test;
import org.swissre.model.Employee;
import org.swissre.util.CSVReader;
import org.swissre.util.ValidationResult;
import org.swissre.validator.EmployeeReportingLineRules;
import org.swissre.validator.SalaryValidationRules;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class EmployeeAnalyzerServiceTest {

    Map<String, Employee> employeeMap = CSVReader.readEmployees("data/employees.csv");

    public EmployeeAnalyzerServiceTest() throws IOException {
    }


    @Test
    public void testCSVParsing() throws Exception {
        Map<String, Employee> map = CSVReader.readEmployees("data/employees.csv");
        assertEquals(8, map.size());
        assertNotNull(map.get("123"));
        assertEquals("Joe Doe", map.get("123").getFullName());
    }

    @Test
    public void testDepthValidation_tooDeep() {
        EmployeeReportingLineRules depthRule = new EmployeeReportingLineRules();

        // Ram Singh should be too deep (5 levels from CEO)
        Employee ram = employeeMap.get("500");
        List<ValidationResult> results = depthRule.validate(ram);

        assertFalse(results.isEmpty());
        assertTrue(results.get(0).getMessage().contains("too long reporting line"));
    }

    @Test
    public void testSalaryValidation_tooLittle() {
        SalaryValidationRules salaryRule = new SalaryValidationRules();

        // Martin Chekov should earn too little
        Employee martin = employeeMap.get("124");
        List<ValidationResult> results = salaryRule.validate(martin);

        assertFalse(results.isEmpty());
        assertTrue(results.get(0).getMessage().contains("TOO LITTLE"));
    }
}