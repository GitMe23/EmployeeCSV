package com.sparta.ah;

import com.sparta.ah.jdbc.ConnectionManager;
import com.sparta.ah.jdbc.EmployeeDAO;

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


        EmployeeCollection.clearEmployeesFromDatabase();

        long start;
        long stop;

        String fileToRead = "src/main/resources/EmployeeRecords.csv";
        String dirtyFilePath = "src/main/java/com/sparta/ah/DirtyData.csv";

        start = System.nanoTime();
        EmployeeCollection.setEmployees(FileIO.readFromFile(fileToRead));
        EmployeeCollection.checkForDuplicateIDs();
        EmployeeCollection.checkGenderTypes();
        EmployeeCollection.checkDates();
        ThreadManager.sendCleanSetToDb();
        EmployeeCollection.writeDirtyData(dirtyFilePath);

        stop = System.nanoTime();
        long time = stop - start;
        logger.log(Level.WARNING, "Size of dirty list: " + EmployeeCollection.getDirtyList().size());
        logger.log(Level.INFO, "Time taken: " + (time / 1_000_000_000) + " seconds");


    }
    // 1. loop - one big array of Employees  (for each, remove corrupted) Easy / takes longer
    // 2. while in loop, filter and add to clean array   Harder / faster


}
