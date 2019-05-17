package com.example.EmployeeRecords;


import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private Gson gson = new Gson();
    private ArrayList<String> employeeList = new ArrayList<>();
    private EmployeeDatabase employeeDatabase = new EmployeeDatabase();

    @RequestMapping(method =  RequestMethod.POST)
    public String postEmployee(@RequestBody Employee employee) {


        if(employeeDatabase.checkEmployeeId(employee)) return "Id already exists";

        if(employee.getId() <= 0) return "Please select a valid Id!";

        employeeDatabase.addEmployee(employee);


//        for(String string : employeeList) {
//            Employee emp = gson.fromJson(string, Employee.class);
//            if(emp.getId() == id) return "Id: " +emp.getId() +". already exists";
//        }
//        if(id < 1) return "Please enter a valid id!";
//
//        Employee employee = new Employee(id, name, profession, age);
//
//        String employeeJson = gson.toJson(employee);
//        employeeList.add(employeeJson);

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


        return "Employee: " +employee.getName() +" added to database under Id: " +employee.getId() +".";

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployeeList(@RequestParam(value = "id", defaultValue = "-1") int id,
                                          @RequestParam(value = "name", defaultValue = "") String name,
                                          @RequestParam(value = "profession", defaultValue = "")String profession,
                                          @RequestParam(value = "age", defaultValue = "-1")int age) {
        //String employeeListJson = gson.toJson(employeeList);




//        ArrayList<String> returnList = new ArrayList<>();
//        for(String string : employeeList) {
//            Employee emp = gson.fromJson(string, Employee.class);
//           if(emp.getId() == id || emp.getName().equalsIgnoreCase(name) || emp.getProfession().equalsIgnoreCase(profession) || emp.getAge() == age){
//               returnList.add(string);
//           }
//           else if(id == -1 && name.equalsIgnoreCase(" ") && profession.equalsIgnoreCase(" ") && age == -1) {
//               returnList.add(string);
//           }
//        }

        return employeeDatabase.getEmployees(id, name, profession, age);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestParam(value = "id", defaultValue = "-1") int id) {
        for(String string :employeeList) {
            Employee emp = gson.fromJson(string, Employee.class);
            if(emp.getId() == id) {
                String name = emp.getName();
                employeeList.remove(string);
                return "Employee " +name +" with the Id: " +id +". deleted!";
            }
        }

        return "Id not found!";

    }


    @RequestMapping(method = RequestMethod.PATCH)
    public String patchEmployee(@RequestParam(value = "id", defaultValue = "-1") int id,
                                @RequestParam(value = "name", defaultValue = " ") String name,
                                @RequestParam(value = "profession", defaultValue = " ")String profession,
                                @RequestParam(value = "age", defaultValue = "-1")int age) {

        for(String string :employeeList) {
            Employee emp = gson.fromJson(string, Employee.class);
            if(emp.getId() == id) {

                if(!name.equalsIgnoreCase(" ")) emp.setName(name);
                if(!profession.equalsIgnoreCase(" ")) emp.setProfession(profession);
                if(age >= 0) emp.setAge(age);

                employeeList.remove(string);
                employeeList.add(gson.toJson(emp));


                return "Employee updated! \r\n" +gson.toJson(emp);
            }
        }



        return "Employee id not found!";
    }

}
