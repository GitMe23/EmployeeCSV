package com.sparta.ah;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        EmployeeCollection.checkForDuplicateIDs();
        EmployeeCollection.checkGenderTypes();
        //EmployeeCollection.checkDates();



//
//        String todayString = LocalDate.now().toString();
//        LocalDate today = LocalDate.parse(todayString, dtf);

//        LocalDate localDate = LocalDate.parse();
//
//        LocalDate localDate = LocalDate.now();//For reference
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        String formattedString = localDate.format(dtf);
//        System.out.println(formattedString);
//
//
//

        //System.out.println(EmployeeCollection.getDirtyList().size());
    }
    // 1. loop - one big array of Employees  (for each, remove corrupted) Easy / takes longer
    // 2. while in loop, filter and add to clean array   Harder / faster



}
