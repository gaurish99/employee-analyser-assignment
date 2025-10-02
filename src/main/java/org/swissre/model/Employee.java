package org.swissre.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single employee in the company.
 */
public class Employee {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final double salary;
    private final String managerId;

    private Employee manager;
    private final List<Employee> subordinates = new ArrayList<>();

    public Employee(String id, String firstName, String lastName, double salary, String managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = (managerId == null || managerId.isEmpty()) ? null : managerId;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Employee getManager() {
        return manager;
    }

    public void addSubordinate(Employee emp) {
        subordinates.add(emp);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public String getId() { return id; }

    public String getManagerId() { return managerId; }

    public double getSalary() { return salary; }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
