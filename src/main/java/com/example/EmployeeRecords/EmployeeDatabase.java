package com.example.EmployeeRecords;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabase {

    private List<Employee> employeeDatabase;

    public EmployeeDatabase() {

        this.employeeDatabase = new ArrayList<>();

    }

    public List<Employee> getEmployees(int id, String name, String profession, int age) {


        if(name.equals("") && id == -1 && age == -1 && profession.equals("")) return employeeDatabase;

        ArrayList<Employee> returnList = new ArrayList<>();

        for(Employee employee : employeeDatabase) {

            if(employee.getName().toLowerCase().contains(name.toLowerCase())
            || employee.getProfession().toLowerCase().contains(profession)
            || employee.getAge() == age
            || employee.getId() == id) {
                returnList.add(employee);
            }

        }

        return returnList;
    }

    public boolean checkEmployeeId(Employee employee) {

        for(Employee emp : employeeDatabase) {
            if(emp.getId() == employee.getId()) return true;
        }
        return false;
    }

    public void addEmployee(Employee employee) {
        employeeDatabase.add(employee);
    }

    




}
