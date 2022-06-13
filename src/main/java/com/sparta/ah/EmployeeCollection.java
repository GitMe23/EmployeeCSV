package com.sparta.ah;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeCollection {

    private static ArrayList<EmployeeDTO> employees = new ArrayList<>();



    public static ArrayList<EmployeeDTO> getEmployees() {
        return employees;
    }
    public static void setEmployees(ArrayList<EmployeeDTO> employees) {
        EmployeeCollection.employees = employees;
    }

    public static int getSize() {
        return employees.size();
    }


    // methods for interacting with employees
    // EmployeeCollection.addToCollection()
    //Employee.addToCorruptedCollection()
    // get size
    //remove duplicates


}
