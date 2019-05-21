package com.example.EmployeeRecords;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/employer")
public class EmployerController {


    private EmployerDatabase employerDatabase = new EmployerDatabase();

    @RequestMapping(method = RequestMethod.GET)
    public List<Employer> getEmployerList (@RequestParam(value = "id", defaultValue = "-1") int id,
                                           @RequestParam(value = "name", defaultValue = "") String name,
                                           @RequestParam(value = "industry", defaultValue = "") String industry) {


        return employerDatabase.getEmployers(id, name, industry);

    }

    @RequestMapping(method =  RequestMethod.POST)
    public String postEmployer(@RequestBody Employer employer) {

        if(employerDatabase.checkEmployerId(employer)) return "Id already exists";

        if(employer.getId() <= 0) return "Please select a valid Id!";

        employerDatabase.addEmployer(employer);


        return "Employer: " +employer.getName() +" added under Id: " +employer.getId() +".";

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteEmployer(@PathVariable(value = "id") int id) {

        if(employerDatabase.deleteEmployer(id)) {
            return "Employer deleted!";
        }

        return "Id not found!";

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    public String patchEmployer(@PathVariable(value = "id") int id,
                                @RequestParam(value = "name", defaultValue = " ") String name,
                                @RequestParam(value = "industry", defaultValue = " ")String industry) {

        if(id <= 0) return "Please enter a valid Id!";

        if(employerDatabase.patchEmployer(id, name, industry)) return "Employer info updated!";


        return "Employer id not found!";
    }
    

}
