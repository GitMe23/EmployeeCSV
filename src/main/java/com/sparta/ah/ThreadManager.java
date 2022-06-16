package com.sparta.ah;

import com.sparta.ah.jdbc.ConnectionManager;
import com.sparta.ah.jdbc.EmployeeDAO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;

import static com.sparta.ah.logging.LogConfig.logger;

public class ThreadManager implements Runnable {

    private EmployeeDTO[] employeeArray;

    public ThreadManager(EmployeeDTO[] employeeArray) {
        this.employeeArray = employeeArray;
    }




    @Override
    public void run() {
        logger.log(Level.INFO, Thread.currentThread().getName() + " sending records to database");
        sendArrayToDb(employeeArray);
        long stopTime = System.currentTimeMillis();
        logger.log(Level.INFO, Thread.currentThread().getName() + " finished.");


        long totalTime = stopTime - App.getStart();
        logger.log(Level.INFO, "Time run time: " + totalTime/1000 + " seconds -  " + EmployeeCollection.getCleanSet().size() + " employee records sent to database ");

    }

    public static void sendCleanSetToDbTwoThreads() {
        EmployeeDTO[] cleanArray = EmployeeCollection.getCleanArray();
        int middleValue = cleanArray.length / 2;
        EmployeeDTO[] array1 = Arrays.copyOfRange(cleanArray, 0, middleValue);
        EmployeeDTO[] array2 = Arrays.copyOfRange(cleanArray, middleValue, cleanArray.length);

        ThreadManager tm1 = new ThreadManager(array1);
        ThreadManager tm2 = new ThreadManager(array2);
        Thread thread1 = new Thread(tm1);
        Thread thread2 = new Thread(tm2);
        thread1.setName("Thread 1");
        thread2.setName("Thread 2");
        thread1.start();
        thread2.start();

    }


    public static void sendCleanSetToDbFourThreads() {
        EmployeeDTO[] cleanArray = EmployeeCollection.getCleanArray();
        int middleValue = cleanArray.length / 2;
        int quarterValue = middleValue/2;
        int threeQuarterValue = middleValue + quarterValue;
        EmployeeDTO[] array1 = Arrays.copyOfRange(cleanArray, 0, quarterValue);
        EmployeeDTO[] array2 = Arrays.copyOfRange(cleanArray, quarterValue, middleValue);
        EmployeeDTO[] array3 = Arrays.copyOfRange(cleanArray, middleValue, threeQuarterValue);
        EmployeeDTO[] array4 = Arrays.copyOfRange(cleanArray, threeQuarterValue, cleanArray.length);

        ThreadManager tm1 = new ThreadManager(array1);
        ThreadManager tm2 = new ThreadManager(array2);
        ThreadManager tm3 = new ThreadManager(array3);
        ThreadManager tm4 = new ThreadManager(array4);
        Thread thread1 = new Thread(tm1);
        Thread thread2 = new Thread(tm2);
        Thread thread3 = new Thread(tm3);
        Thread thread4 = new Thread(tm4);
        thread1.setName("Thread 1");
        thread2.setName("Thread 2");
        thread3.setName("Thread 3");
        thread4.setName("Thread 4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

    public static void sendArrayToDb(EmployeeDTO[] cleanArray) {
        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());
        //logger.log(Level.INFO, "Sending clean records to DB..................");
        for (EmployeeDTO employee : cleanArray) {
            employeeDAO.insertEmployee(
                    employee.getEmpId(),
                    employee.getNamePrefix(),
                    employee.getFirstName(),
                    employee.getMiddleInitial(),
                    employee.getLastName(),
                    employee.getGender(),
                    employee.getEmail(),
                    employee.getDob(),
                    employee.getDateOfJoining(),
                    employee.getSalary());

        }
        //logger.log(Level.INFO, "Clean data sent to database");
    }
}
