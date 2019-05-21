package com.example.EmployeeRecords;


public class Employee {

    private int id;
    private String name;
    private String profession;
    private int age;




    public Employee(int id, String name, String profession, int age) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
