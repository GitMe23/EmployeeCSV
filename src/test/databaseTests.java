package com.sparta.ah;

import com.sparta.ah.jdbc.ConnectionManager;
import com.sparta.ah.jdbc.EmployeeDAO;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class databaseTests {
    @Test
    public void databaseConnection() {

        Assertions.assertNotNull(ConnectionManager.getConnection());
    }

    @Test
    public void printAllEmployeesFromDB() {


        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());
        employeeDAO.printAllEmployees();


        System.out.println(employeeDAO);
    }

    @Test
    public void printParticularEmployee() {

        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());
        employeeDAO.printAllEmployees();

        System.out.println(employeeDAO.equals("Manish"));
    }

    @Test
    public void checkInsertEmployee() {
        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());

        employeeDAO.insertEmployee("70000", "Miss", "Katie", "E", "Lisbon", "F", "katie@gmail.com", "10/03/1990", "10/06/2020", "3928327");
        employeeDAO.insertEmployee("70002", "Miss", "Katie", "E", "Lisbon", "F", "katie@gmail.com", "10/03/1990", "10/06/2020", "3928327");


        String expected = "2";
        String actual = employeeDAO.getCount();

        Assertions.assertEquals(expected, actual);
    }



    @Test
    public void checkPrintCount() {
        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());

        employeeDAO.insertEmployee("70000", "Miss", "Katie", "E", "Lisbon", "F", "katie@gmail.com", "10/03/1990", "10/06/2020", "3928327");
        employeeDAO.insertEmployee("70002", "Miss", "Katie", "E", "Lisbon", "F", "katie@gmail.com", "10/03/1990", "10/06/2020", "3928327");

        String expected = "2";
        String actual = employeeDAO.getCount();

        Assertions.assertEquals(expected, actual);
    }
        //EmployeeCollection.clearEmployeesFromDatabase();
//        ResultSet resultSet = statement.executeQuery("SELECT COUNT(\"empId\") FROM employees");

    @Test
    public void clearDatabase(){
        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());

        employeeDAO.insertEmployee("70000", "Miss", "Katie", "E", "Lisbon", "F", "katie@gmail.com", "10/03/1990", "10/06/2020", "3928327");
        employeeDAO.insertEmployee("1", "Mr.", "Tom", "X", "Roberts", "M", "me@me.com", "99/99/9999", "99/99/9999", "100k");
        employeeDAO.insertEmployee("5332005", "Mrs", "Marinda", "J", "James", "F", "me@hotmail.com", "21/02/1999", "01/02/2020", "1035240");
        employeeDAO.insertEmployee("53657588", "Mr", "Richard", "L", "Liams", "M", "Richards@hotmail.com", "02/02/1995", "06/06/2022", "23423");

        employeeDAO.clearDatabase();
        String expected = "0";
        String actual = employeeDAO.getCount();

        Assertions.assertEquals(expected, actual);
    }


    }



