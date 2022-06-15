package com.sparta.ah;

/**
 * Create ArrayList of Employees objects
 * Filter out clean, corrupted objects
 *
 */
public class App 
{

    public static void main( String[] args )
    {

        long start;
        long stop;
        String file = "src/main/resources/EmployeeRecordsLarge.csv";


        start = System.nanoTime();
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        EmployeeCollection.checkForDuplicateIDs();
        EmployeeCollection.checkGenderTypes();
        EmployeeCollection.checkDates();
        EmployeeDTO[] toDB = EmployeeCollection.getCleanArray();


        EmployeeCollection.sendToDb(toDB);
        stop = System.nanoTime();
        long time = stop - start;
        System.out.println( (time / 1_000_000) / 1000 + " seconds");


    }
    // 1. loop - one big array of Employees  (for each, remove corrupted) Easy / takes longer
    // 2. while in loop, filter and add to clean array   Harder / faster


}
