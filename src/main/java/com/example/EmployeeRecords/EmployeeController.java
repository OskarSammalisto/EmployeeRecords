package com.example.EmployeeRecords;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {


    private EmployeeDatabase employeeDatabase = new EmployeeDatabase();

    @RequestMapping(method =  RequestMethod.POST)
    public String postEmployee(@RequestBody Employee employee) {


        if(employeeDatabase.checkEmployeeId(employee)) return "Id already exists";

        if(employee.getId() <= 0) return "Please select a valid Id!";

        employeeDatabase.addEmployee(employee);




        return "Employee: " +employee.getName() +" added to database under Id: " +employee.getId() +".";

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployeeList(@RequestParam(value = "id", defaultValue = "-1") int id,
                                          @RequestParam(value = "name", defaultValue = "") String name,
                                          @RequestParam(value = "profession", defaultValue = "")String profession,
                                          @RequestParam(value = "age", defaultValue = "-1")int age) {


        return employeeDatabase.getEmployees(id, name, profession, age);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") int id) {


        if(employeeDatabase.deleteEmployee(id)) {
            return "Employee deleted";
        }

        return "Id not found!";

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Employee PatchEmployee(@PathVariable int id,
                                  @RequestBody  Map<String, Object> employeeMap) {
        return employeeDatabase.patchEmployeeWithMap(id, employeeMap);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Employee PutEmployee(@PathVariable int id,
                             @RequestBody Employee employee) {

        if(employeeDatabase.putEmployee(id, employee)) return employee;

    return null;
    }

}
