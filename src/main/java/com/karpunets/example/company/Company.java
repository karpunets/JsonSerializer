package com.karpunets.example.company;

import com.karpunets.jsonSerializer.annotations.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karpunets
 * @since 15.12.2016
 */
public class Company {

    public byte[] lucky = {0, 1, 1, 0, 1, 3};

    @JsonProperty
    private Map<Integer, Department> departments = new HashMap<Integer, Department>();

    public transient long shares = 10000;

    public Department getDepartments(Integer departmentNumber) {
        return departments.get(departmentNumber);
    }

    public Department newDepartments(Integer departmentNumber, String departmentName) {
        Department department = new Department(departmentName);
        departments.put(departmentNumber, department);
        return department;
    }

    public static Company getTestCompany() {
        Company company = new Company();
        Department departmentTwelve = company.newDepartments(12, "twelve");
        departmentTwelve.inctementVacancie();
        departmentTwelve.inctementVacancie();
        departmentTwelve.addWorkers("Ann", "Bin", 20);
        departmentTwelve.addWorkers("Tom", "Rich", 25);
        departmentTwelve.addTask("To be happy", 777);
        departmentTwelve.addTask();

        Department departmentSeven = company.newDepartments(7, "seven");
        departmentSeven.inctementVacancie();
        departmentSeven.addWorkers("Steve", "Morison", 16);
        departmentSeven.addTask("To do nothing", 20);

        Department departmentThree = company.newDepartments(3, "three");
        departmentThree.inctementVacancie();
        departmentThree.addWorkers("Jim", "Raymond", 28);
        departmentThree.inctementVacancie();
        departmentThree.addWorkers("Jany", "Raymond", 25);
        departmentThree.inctementVacancie();
        departmentThree.addWorkers("Jak", "Nail", 25);
        departmentThree.addTask("To do something", 20);
        departmentThree.addTask();

        return company;
    }

}
