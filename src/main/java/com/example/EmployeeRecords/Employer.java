package com.example.EmployeeRecords;




public class Employer {



    private String name;
    private String industry;
    private int id;

    public Employer() {}

    public Employer(int id,String name, String industry) {
        this.id = id;
        this.name = name;
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setId(int id) {
        this.id = id;
    }

}
