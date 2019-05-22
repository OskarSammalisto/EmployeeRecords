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

public class EmployerDatabase {

    private List<Employer> employerDatabase;
    private Gson gson = new Gson();

    public EmployerDatabase() {
        this.employerDatabase = loadEmployerList();
    }

    public List<Employer> getEmployers(int id, String name, String industry) {


        if(name.equals("") && id == -1 && industry.equals("")) return employerDatabase;

        ArrayList<Employer> returnList = new ArrayList<>();

        for(Employer employer : employerDatabase) {

            if(employer.getName().toLowerCase().contains(name.toLowerCase())
                    || employer.getIndustry().toLowerCase().contains(industry)
                    || employer.getId() == id) {
                returnList.add(employer);
            }

        }

        return returnList;
    }

    public boolean checkEmployerId(Employer employer) {

        for(Employer emp : employerDatabase) {
            if(emp.getId() == employer.getId()) return true;
        }
        return false;
    }

    public void addEmployer(Employer employer) {
        employerDatabase.add(employer);
        writeToTextFile();

    }

    public boolean deleteEmployer(int id) {

        for(Employer emp : employerDatabase) {
            if(emp.getId() == id) {
                employerDatabase.remove(emp);
                writeToTextFile();
                return true;
            }
        }
        return false;
    }


    public boolean putEmployer(int id, Employer employer) {
        for(Employer emp : employerDatabase) {
            if(emp.getId() == id) {
                employerDatabase.remove(emp);
                employer.setId(emp.getId());
                employerDatabase.add(employer);
                writeToTextFile();
                return true;
            }
        }
        return false;
    }

    public Employer patchEmployerWithMap(int id, Map<String, Object> employerMap) {
        for(Employer emp : employerDatabase) {
            if(emp.getId() == id) {


                ObjectMapper mapper = new ObjectMapper();
                Employer employer = mapper.convertValue(employerMap, Employer.class);
               // System.out.println(employer.getName());

                if (employer.getName() != null ) {
                    emp.setName(employer.getName());
                }
                if (employer.getIndustry() != null) {
                    emp.setIndustry(employer.getIndustry());
                }


                return emp;
            }
        }
        return null;
    }

    private void writeToTextFile() {
        ArrayList<String> jsonemployerList = new ArrayList<>();
        for(Employer employer : employerDatabase) {
            String emp = gson.toJson(employer, Employer.class);
            jsonemployerList.add(emp);
        }

        File employerRecord = new File("employerRecord.txt");

        try {
            PrintWriter out = new PrintWriter(new FileWriter(employerRecord, false));
            out.println(jsonemployerList);
            out.close();
        }catch (Exception e) {
            System.out.println("error writing to text file");
        }
    }

    private ArrayList<Employer> loadEmployerList() {

        Type type = new TypeToken<List<Employer>>() {}.getType();
        ArrayList<Employer> returnList = new ArrayList<>();

        try {
            String employeer = new String(Files.readAllBytes(Paths.get("employerRecord.txt")), StandardCharsets.UTF_8);
            if(!employeer.isEmpty())returnList = gson.fromJson(employeer, type);


        } catch (IOException e) {
            e.printStackTrace();
        }



        return returnList;

    }

}
