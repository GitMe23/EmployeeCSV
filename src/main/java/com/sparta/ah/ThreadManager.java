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
        logger.log(Level.INFO, "---------------------------------------------" );logger.log(Level.INFO, "---------------------------------------------" );logger.log(Level.INFO, "---------------------------------------------" );
        logger.log(Level.INFO, "---------------------------------------------" );
        logger.log(Level.INFO, " ------ " + totalTime + " milliseconds  -----" );
        logger.log(Level.INFO, "Time run time: " + totalTime/1000 + " seconds -  " + EmployeeCollection.getCleanSet().size() + " employee records sent to database. " + EmployeeCollection.getDirtyList().size() + " added to dirty list.");

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

    public static void sendCleanSetToDbEightThreads() {
        EmployeeDTO[] cleanArray = EmployeeCollection.getCleanArray();
        int eighthValue = cleanArray.length / 8;


        EmployeeDTO[] array1 = Arrays.copyOfRange(cleanArray, 0, eighthValue);
        EmployeeDTO[] array2 = Arrays.copyOfRange(cleanArray, eighthValue, eighthValue*2);
        EmployeeDTO[] array3 = Arrays.copyOfRange(cleanArray, eighthValue*2, eighthValue*3);
        EmployeeDTO[] array4 = Arrays.copyOfRange(cleanArray, eighthValue*3, eighthValue*4);
        EmployeeDTO[] array5 = Arrays.copyOfRange(cleanArray, eighthValue*4, eighthValue*5);
        EmployeeDTO[] array6 = Arrays.copyOfRange(cleanArray, eighthValue*5, eighthValue*6);
        EmployeeDTO[] array7 = Arrays.copyOfRange(cleanArray, eighthValue*6, eighthValue*7);
        EmployeeDTO[] array8 = Arrays.copyOfRange(cleanArray, eighthValue*7, cleanArray.length);

        ThreadManager tm1 = new ThreadManager(array1);
        ThreadManager tm2 = new ThreadManager(array2);
        ThreadManager tm3 = new ThreadManager(array3);
        ThreadManager tm4 = new ThreadManager(array4);

        ThreadManager tm5 = new ThreadManager(array5);
        ThreadManager tm6 = new ThreadManager(array6);
        ThreadManager tm7 = new ThreadManager(array7);
        ThreadManager tm8 = new ThreadManager(array8);
        Thread thread1 = new Thread(tm1);
        Thread thread2 = new Thread(tm2);
        Thread thread3 = new Thread(tm3);
        Thread thread4 = new Thread(tm4);
        Thread thread5 = new Thread(tm5);
        Thread thread6 = new Thread(tm6);
        Thread thread7 = new Thread(tm7);
        Thread thread8 = new Thread(tm8);
        thread1.setName("Thread 1");
        thread2.setName("Thread 2");
        thread3.setName("Thread 3");
        thread4.setName("Thread 4");
        thread5.setName("Thread 5");
        thread6.setName("Thread 6");
        thread7.setName("Thread 7");
        thread8.setName("Thread 8");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
    }



}
