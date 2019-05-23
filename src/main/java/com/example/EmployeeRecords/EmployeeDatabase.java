package com.example.EmployeeRecords;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeDatabase {

    private List<Employee> employeeDatabase;

    private Gson gson = new Gson();

    public EmployeeDatabase() {

        this.employeeDatabase = loadEmployeeList();


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
        writeToTextFile();
    }

    public boolean deleteEmployee(int id) {

        for(Employee emp : employeeDatabase) {
            if(emp.getId() == id) {
                employeeDatabase.remove(emp);
                writeToTextFile();
                return true;
            }
        }
        return false;
    }


    public boolean putEmployee(int id, Employee employee) {
        for(Employee emp : employeeDatabase) {
            if(emp.getId() == id) {
                employeeDatabase.remove(emp);
                employee.setId(emp.getId());
                employeeDatabase.add(employee);
                writeToTextFile();
                return true;
            }
        }
        return false;
    }

    public Employee patchEmployeeWithMap(int id, Map<String, Object> employeeMap) {
        for(Employee emp : employeeDatabase) {
            if(emp.getId() == id) {


              ObjectMapper mapper = new ObjectMapper();
              Employee employee = mapper.convertValue(employeeMap, Employee.class);

              if (employee.getName() != null ) {
                  emp.setName(employee.getName());
              }
              if (employee.getProfession() != null) {
                  emp.setProfession(employee.getProfession());
              }
              if (employee.getAge() != 0) {
                  emp.setAge(employee.getAge());
              }

                return emp;
            }
        }
        return null;
    }

    private void writeToTextFile() {
        ArrayList<String> jsonemployeeList = new ArrayList<>();
        for(Employee employee : employeeDatabase) {
            String emp = gson.toJson(employee, Employee.class);
            jsonemployeeList.add(emp);
        }

        File employeeRecord = new File("employeeRecord.txt");

        try {
            PrintWriter out = new PrintWriter(new FileWriter(employeeRecord, false));
            out.println(jsonemployeeList);
            out.close();
        }catch (Exception e) {
            System.out.println("error writing to text file");
        }

    }

    private ArrayList<Employee> loadEmployeeList() {


        Type type = new TypeToken<List<Employee>>() {}.getType();
        ArrayList<Employee> returnList = new ArrayList<>();

        try {
            String employees = new String(Files.readAllBytes(Paths.get("employeeRecord.txt")), StandardCharsets.UTF_8);
            if(!employees.isEmpty())returnList = gson.fromJson(employees, type);

        } catch (IOException e) {
            e.printStackTrace();
        }



        return returnList;

    }


}
