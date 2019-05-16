package com.example.EmployeeRecords;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private Gson gson = new Gson();
    private ArrayList<String> employeeList = new ArrayList<>();

    @RequestMapping(method =  RequestMethod.POST)
    public Employee employee(@RequestParam(value = "name", defaultValue = "Name") String name,
                             @RequestParam(value = "profession", defaultValue = "Profession") String profession,
                             @RequestParam(value = "age", defaultValue = "100") int age) {


       // String returnString = name +", " + profession +", " +age +".";
        Employee employee = new Employee(name, profession, age);

        String employeeJson = gson.toJson(employee);
        employeeList.add(employeeJson);

//        File employeeRecord = new File("employeeRecord.txt");
//
//        try {
//            PrintWriter out = new PrintWriter(new FileWriter(employeeRecord, true));
//            out.append(employeeJson);
//            out.append(",\r\n");
//            out.close();
//        }catch (Exception e) {
//            System.out.println("error writing to text file");
//        }


        return employee;

    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<String> string(@RequestParam(value = "name", defaultValue = " ") String name,
                                    @RequestParam(value = "profession", defaultValue = " ")String profession,
                                    @RequestParam(value = "age", defaultValue = "0")int age) {
        //String employeeListJson = gson.toJson(employeeList);

        ArrayList<String> returnList = new ArrayList<>();
        for(String string : employeeList) {
            Employee emp = gson.fromJson(string, Employee.class);
           if(emp.getName().equalsIgnoreCase(name)){
               returnList.add(string);
           }
        }

        return returnList;
    }

}
