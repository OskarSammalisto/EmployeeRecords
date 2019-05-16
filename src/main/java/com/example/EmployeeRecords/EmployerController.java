package com.example.EmployeeRecords;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employer")
public class EmployerController {

    @RequestMapping(method = RequestMethod.GET)
    public Employer employer (@RequestParam(value = "name", defaultValue = "employer name") String name,
                              @RequestParam(value = "industry", defaultValue = "industry") String industry) {
        Employer employer = new Employer(name, industry);

        return employer;
    }

}
