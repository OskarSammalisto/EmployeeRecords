package com.example.EmployeeRecords;

import java.io.File;

public class Employee {

    private String name;
    private String profession;
    private int age;

   // File employee = new File("employeeRecord.txt");

    public Employee(String name, String profession, int age) {
        this.name = name;
        this.profession = profession;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }

    public int getAge() {
        return age;
    }
}
