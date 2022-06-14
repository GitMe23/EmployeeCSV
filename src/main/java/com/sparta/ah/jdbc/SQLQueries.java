package com.sparta.ah.jdbc;

public interface SQLQueries {
   // all vars in interfaces are public, static and final automatically
    String SELECT_ALL = "SELECT * FROM public.employees";
    String INSERT_INTO_DB = "INSERT INTO public.employees (\n" + "\"empId\", \"namePrefix\", \"firstName\", \"middleInitial\", \"lastName\", \"gender\", \"email\", \"dob\" , \"dateOfJoining\", \"salary\") VALUES (?,?,?,?,?,?,?,?,?,?)";








}
