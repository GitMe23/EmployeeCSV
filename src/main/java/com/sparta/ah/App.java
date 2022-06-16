package com.sparta.ah;

import com.sparta.ah.jdbc.ConnectionManager;
import com.sparta.ah.jdbc.EmployeeDAO;

import java.util.Date;
import java.util.logging.Level;

import static com.sparta.ah.logging.LogConfig.logger;

/**
 * Create ArrayList of Employees objects
 * Filter out clean, corrupted objects
 *
 */
public class App 
{




    static long start;



    public static void main( String[] args )  {


        EmployeeCollection.clearEmployeesFromDatabase();



        String fileToRead = "src/main/resources/EmployeeRecordsLarge.csv";
        String dirtyFilePath = "src/main/java/com/sparta/ah/DirtyData.csv";

        setStart(System.currentTimeMillis());
        EmployeeCollection.setEmployees(FileIO.readFromFile(fileToRead));
        EmployeeCollection.checkForDuplicateIDs();
        EmployeeCollection.checkGenderTypes();
        EmployeeCollection.checkDates();
        EmployeeCollection.writeDirtyData(dirtyFilePath);
        ThreadManager.sendCleanSetToDb();

        logger.log(Level.WARNING, "Size of dirty list: " + EmployeeCollection.getDirtyList().size());



    }

    private static void setStart(long currentTimeMillis) {
        start = currentTimeMillis;
    }
    // 1. loop - one big array of Employees  (for each, remove corrupted) Easy / takes longer
    // 2. while in loop, filter and add to clean array   Harder / faster


    public static long getStart() {
        return start;
    }




}
