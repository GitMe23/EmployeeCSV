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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sendArrayToDb(employeeArray);
    }


    public static void sendCleanSetToDb() {
        EmployeeDTO[] cleanArray = EmployeeCollection.getCleanArray();
        int middleValue = cleanArray.length / 2;
        EmployeeDTO[] array1 = Arrays.copyOfRange(cleanArray, 0, middleValue);
        EmployeeDTO[] array2 = Arrays.copyOfRange(cleanArray, middleValue, cleanArray.length);

        ThreadManager tm1 = new ThreadManager(array1);
        ThreadManager tm2 = new ThreadManager(array2);
        Thread thread1 = new Thread(tm1);
        Thread thread2 = new Thread(tm2);

        thread1.start();
        thread2.start();

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
