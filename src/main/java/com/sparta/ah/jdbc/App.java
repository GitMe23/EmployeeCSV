package com.sparta.ah.jdbc;

import com.sparta.ah.EmployeeDTO;

import java.sql.*;

public class App {
    public static void main(String[] args) {

        // clear DB method in DTO that drops the table

        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());

        employeeDAO.insertEmployee("1", "Mr.", "Manish", "X", "Gadhvi", "M", "me@me.com", "99/99/9999", "99/99/9999", "100k");



        employeeDAO.printAllEmployees();

        ConnectionManager.closeConnection();


    }
}
