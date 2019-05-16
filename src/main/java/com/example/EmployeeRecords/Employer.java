package com.example.EmployeeRecords;

public class Employer {

    String name;
    String industry;

    public Employer(String name, String industry) {
        this.name = name;
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }
}
