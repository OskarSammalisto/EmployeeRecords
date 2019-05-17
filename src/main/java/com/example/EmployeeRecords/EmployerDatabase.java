package com.example.EmployeeRecords;

import java.util.ArrayList;
import java.util.List;

public class EmployerDatabase {

    private List<Employer> employerDatabase;

    public EmployerDatabase() {
        this.employerDatabase = new ArrayList<>();
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

    }

    public boolean deleteEmployer(int id) {

        for(Employer emp : employerDatabase) {
            if(emp.getId() == id) {
                employerDatabase.remove(emp);
                return true;
            }
        }
        return false;
    }

    public boolean patchEmployer(int id, String name, String industry) {

        for(Employer emp : employerDatabase) {
            if(emp.getId() == id) {
                if(!name.equals("")){
                    emp.setName(name);
                }
                if(!industry.equals("")) {
                    emp.setIndustry(industry);
                }
                return true;
            }
        }

        return false;
    }

}
