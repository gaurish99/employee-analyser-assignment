👨‍💼 Employee Analyzer
📋 Overview

Employee Analyzer(I named it as do analysis in employess accoring to specfic rules) is a simple Java application designed to analyze an organization's structure by evaluating salary fairness and organizational depth. It reads employee data from a CSV file and applies business validation rules such as:

Ensuring managers are paid fairly compared to their direct subordinates.

Ensuring employees are not too deep in the reporting hierarchy (i.e., no more than 4 levels away from the CEO).

The app is clean, testable, modular, and extensible — built using Java SE and JUnit with Maven for build and dependency management.

🚀 Features

📄 CSV Parsing: Reads employee details from a .csv file.

📊 Salary Validation: Ensures managers earn 20%–50% more than the average of their direct subordinates.

🏢 Depth Validation: Flags employees who are more than 4 levels away from the CEO.

✅ Clean Console Output: Segregated, readable reporting of all validation issues.

🧪 Unit Tested: Core business logic is fully testable using JUnit.


🧠 Class Responsibilities
🔹 Employee.java

Represents an employee with attributes like ID, name, salary, manager.

Holds a list of direct subordinates.

Links to its manager (if any), forming the hierarchy.

🔹 CSVReader.java

Reads the CSV file.

Parses employee records.

Links employees with their respective managers and builds the full hierarchy.

🔹 ValidationRule.java (Interface)

Defines the contract for all validation rules.

Each rule validates an employee and returns one or more ValidationResults.

🔹 ValidationResult.java

Represents the output of a validation.

Contains severity (TOO_LITTLE, TOO_MUCH, TOO_DEEP) and a human-readable message.

🔹 SalaryValidationService.java

Implements ValidationRule.

Ensures a manager earns between 20% and 50% more than the average of their direct reports.

🔹 EmployeeReportingLineRule.java

Implements ValidationRule.

Flags employees who are more than 4 levels below the CEO.

🔹 EmployeeAnalyzer.java

Applies all validation rules to each employee.

Aggregates and prints the output in categorized groups.

🔹 Main.java

Entry point of the application.

Accepts path to the .csv file as command-line argument.

Triggers the analysis process and prints results.

✅ Sample Output

When you run the app with a sample CSV file:

=== Managers earning TOO LITTLE ===
Manager Martin Chekov earns TOO LITTLE by 15000.00
Manager Brett Hardleaf earns TOO LITTLE by 38000.00

=== Managers earning TOO MUCH ===
Manager Deep Reporter earns TOO MUCH by 15000.00


=== Employees with too long reporting lines ===
Employee Ram Singh has too long reporting line by 1 level

