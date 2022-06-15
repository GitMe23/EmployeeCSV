package com.sparta.ah;

import java.util.logging.Level;

import static com.sparta.ah.logging.LogConfig.logger;

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
//        EmployeeCollection.checkForDuplicateIDs();

        EmployeeCollection.checkGenderTypes();
        EmployeeCollection.checkDates();
        EmployeeDTO[] toDB = EmployeeCollection.getCleanArray();
        EmployeeCollection.sendToDb(toDB);
        stop = System.nanoTime();
        long time = stop - start;
        logger.log(Level.INFO, "Time taken: " + (time / 1_000_000_000) + " seconds");
        System.out.println("Size of dirty list: " + EmployeeCollection.getDirtyList().size());

    }
    // 1. loop - one big array of Employees  (for each, remove corrupted) Easy / takes longer
    // 2. while in loop, filter and add to clean array   Harder / faster


}
