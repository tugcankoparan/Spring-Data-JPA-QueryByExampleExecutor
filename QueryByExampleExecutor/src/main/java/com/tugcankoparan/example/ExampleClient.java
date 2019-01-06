package com.tugcankoparan.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class ExampleClient {

    @Autowired
    private EmployeeRepository repo;

    public void run() {
        List<Employee> employees = createEmployees();
        repo.saveAll(employees);

        findAllEmployees();
        findEmployeesByName();
        findEmployeesByNameAndDept();
    }

    private void findEmployeesByName() {
        System.out.println("-- finding employees with name Tim --");
        Employee employee = new Employee();
        employee.setName("Tim");
        Example<Employee> employeeExample = Example.of(employee);
        //calling QueryByExampleExecutor#findAll(Example)
        Iterable<Employee> employees = repo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private void findEmployeesByNameAndDept() {
        System.out.println("-- finding employees with name Jack and dept IT --");
        Employee employee = new Employee();
        employee.setName("Jack");
        employee.setDept("IT");
        Example<Employee> employeeExample = Example.of(employee);
        //calling QueryByExampleExecutor#findAll(Example)
        Iterable<Employee> employees = repo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private void findAllEmployees() {
        System.out.println(" -- getting all Employees --");
        Iterable<Employee> iterable = repo.findAll();
        for (Employee employee : iterable) {
            System.out.println(employee);
        }
    }

    private static List<Employee> createEmployees() {
        return Arrays.asList(Employee.create("Diana", "IT"),
                Employee.create("Mike", "Admin"),
                Employee.create("Tim", "QA"),
                Employee.create("Jack", "IT"));
    }
}