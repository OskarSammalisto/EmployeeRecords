package com.example.EmployeeRecords;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/employer")
public class EmployerController {

    private Gson gson = new Gson();
    private ArrayList<String> employerList = new ArrayList<>();
    private EmployerDatabase employerDatabase = new EmployerDatabase();

    @RequestMapping(method = RequestMethod.GET)
    public List<Employer> getEmployerList (@RequestParam(value = "id", defaultValue = "-1") int id,
                                           @RequestParam(value = "name", defaultValue = "") String name,
                                           @RequestParam(value = "industry", defaultValue = "") String industry) {


        return employerDatabase.getEmployers(id, name, industry);


//        ArrayList<String> returnList = new ArrayList<>();
//        for(String string : employerList) {
//            Employer emp = gson.fromJson(string, Employer.class);
//            if(emp.getId() == id || emp.getName().equalsIgnoreCase(name) || emp.getIndustry().equalsIgnoreCase(industry)){
//                returnList.add(string);
//            }
//            else if(id == -1 && name.equalsIgnoreCase(" ") && industry.equalsIgnoreCase(" ")) {
//                returnList.add(string);
//            }
//        }
//
//        return returnList;
    }

    @RequestMapping(method =  RequestMethod.POST)
    public String postEmployer(@RequestBody Employer employer) {

        if(employerDatabase.checkEmployerId(employer)) return "Id already exists";

        if(employer.getId() <= 0) return "Please select a valid Id!";

        employerDatabase.addEmployer(employer);

//        for(String string : employerList) {
//            Employer emp = gson.fromJson(string, Employer.class);
//            if(emp.getId() == id) return "Id: " +emp.getId() +". already exists";
//        }
//        if(id < 1) return "Please enter a valid id!";
//
//        Employer employer = new Employer (id, name, industry);
//
//        String employeeJson = gson.toJson(employer);
//        employerList.add(employeeJson);

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


        return "Employer: " +employer.getName() +" added under Id: " +employer.getId() +".";

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteEmployer(@PathVariable(value = "id") int id) {

        if(employerDatabase.deleteEmployer(id)) {
            return "Employer deleted!";
        }
//        for(String string :employerList) {
//            Employer emp = gson.fromJson(string, Employer.class);
//            if(emp.getId() == id) {
//                String name = emp.getName();
//                employerList.remove(string);
//                return "Employer " +name +" with the Id: " +id +". deleted!";
//            }
//        }

        return "Id not found!";

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    public String patchEmployer(@PathVariable(value = "id") int id,
                                @RequestParam(value = "name", defaultValue = " ") String name,
                                @RequestParam(value = "industry", defaultValue = " ")String industry) {

        if(id <= 0) return "Please enter a valid Id!";

        if(employerDatabase.patchEmployer(id, name, industry)) return "Employer info updated!";

//        for(String string :employerList) {
//            Employer emp = gson.fromJson(string, Employer.class);
//            if(emp.getId() == id) {
//
//                if(!name.equalsIgnoreCase(" ")) emp.setName(name);
//                if(!industry.equalsIgnoreCase(" ")) emp.setIndustry(industry);
//
//                employerList.remove(string);
//                employerList.add(gson.toJson(emp));
//
//
//                return "Employer updated! \r\n" +gson.toJson(emp);
//            }
//        }



        return "Employer id not found!";
    }
    

}
