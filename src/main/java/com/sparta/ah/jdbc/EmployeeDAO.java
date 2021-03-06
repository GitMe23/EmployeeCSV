package com.sparta.ah.jdbc;

import java.sql.*;

// DAO - data access object
public class EmployeeDAO {
   private Connection connection;
   private Statement statement;

   public EmployeeDAO(Connection connection) {
      this.connection = connection;
      try {
         statement = connection.createStatement();
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }

   public void printAllEmployees() {
      try {
         ResultSet resultSet = statement.executeQuery(SQLQueries.SELECT_ALL);

         while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
            System.out.println(resultSet.getString(5));
            System.out.println(resultSet.getString(6));
            System.out.println(resultSet.getString(7));
            System.out.println(resultSet.getString(8));
            System.out.println(resultSet.getString(9));
            System.out.println(resultSet.getString(10));

         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   // Insert in to public.employees (...) values
   public void insertEmployee(String empId,
                              String namePrefix,
                              String firstName,
                              String middleInitial,
                              String lastName,
                              String gender,
                              String email,
                              String dob,
                              String dateOfJoining,
                              String salary) {
      try {
         PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT_INTO_DB);
         preparedStatement.setString(1, empId);
         preparedStatement.setString(2, namePrefix);
         preparedStatement.setString(3, firstName);
         preparedStatement.setString(4, middleInitial);
         preparedStatement.setString(5, lastName);
         preparedStatement.setString(6, gender);
         preparedStatement.setString(7, email);
         preparedStatement.setString(8, dob);
         preparedStatement.setString(9, dateOfJoining);
         preparedStatement.setString(10, salary);
         preparedStatement.executeQuery();
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void clearDatabase() {
      try {
         PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_FROM);
         preparedStatement.executeQuery();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public String printCount()  {
      try {
         ResultSet resultSet = statement.executeQuery(SQLQueries.COUNT_ALL);
         while (resultSet.next()) {
            return resultSet.getString(1);
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
      return null;
   }

}
