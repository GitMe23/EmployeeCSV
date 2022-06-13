package com.sparta.ah;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Create ArrayList of Employees objects
 * Filter out clean, corrupted objects
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String file = "src/main/resources/EmployeeRecords.csv";
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        System.out.println(EmployeeCollection.getEmployees().get(0));
    }
    // 1. loop - one big array of Employees  (for each, remove corrupted) Easy / takes longer
    // 2. while in loop, filter and add to clean array   Harder / faster



}
