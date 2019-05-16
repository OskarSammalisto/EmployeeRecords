package com.example.EmployeeRecords;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/employer")
public class EmployerController {

    private Gson gson = new Gson();
    private ArrayList<String> employerList = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<String> getEmployerList (@RequestParam(value = "id", defaultValue = "-1") int id,
                                              @RequestParam(value = "name", defaultValue = " ") String name,
                                              @RequestParam(value = "industry", defaultValue = " ") String industry) {
        ArrayList<String> returnList = new ArrayList<>();
        for(String string : employerList) {
            Employer emp = gson.fromJson(string, Employer.class);
            if(emp.getId() == id || emp.getName().equalsIgnoreCase(name) || emp.getIndustry().equalsIgnoreCase(industry)){
                returnList.add(string);
            }
            else if(id == -1 && name.equalsIgnoreCase(" ") && industry.equalsIgnoreCase(" ")) {
                returnList.add(string);
            }
        }

        return returnList;
    }

    @RequestMapping(method =  RequestMethod.POST)
    public String postEmployer(@RequestParam(value = "id", defaultValue = "-1") int id,
                               @RequestParam(value = "name", defaultValue = "Name") String name,
                               @RequestParam(value = "industry", defaultValue = "Profession") String industry) {

        for(String string : employerList) {
            Employer emp = gson.fromJson(string, Employer.class);
            if(emp.getId() == id) return "Id: " +emp.getId() +". already exists";
        }
        if(id < 1) return "Please enter a valid id!";

        Employer employer = new Employer (id, name, industry);

        String employeeJson = gson.toJson(employer);
        employerList.add(employeeJson);

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


        return employeeJson;

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteEmployer(@RequestParam(value = "id", defaultValue = "-1") int id) {
        for(String string :employerList) {
            Employer emp = gson.fromJson(string, Employer.class);
            if(emp.getId() == id) {
                String name = emp.getName();
                employerList.remove(string);
                return "Employer " +name +" with the Id: " +id +". deleted!";
            }
        }

        return "Id not found!";

    }

    @RequestMapping(method = RequestMethod.PATCH)
    public String patchEmployer(@RequestParam(value = "id", defaultValue = "-1") int id,
                                @RequestParam(value = "name", defaultValue = " ") String name,
                                @RequestParam(value = "industry", defaultValue = " ")String industry) {

        for(String string :employerList) {
            Employer emp = gson.fromJson(string, Employer.class);
            if(emp.getId() == id) {

                if(!name.equalsIgnoreCase(" ")) emp.setName(name);
                if(!industry.equalsIgnoreCase(" ")) emp.setIndustry(industry);

                employerList.remove(string);
                employerList.add(gson.toJson(emp));


                return "Employer updated! \r\n" +gson.toJson(emp);
            }
        }



        return "Employer id not found!";
    }

}
