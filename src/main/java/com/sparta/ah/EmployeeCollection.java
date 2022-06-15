package com.sparta.ah;



import com.sparta.ah.jdbc.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;


import static com.sparta.ah.logging.LogConfig.logger;

public class EmployeeCollection {

    private static ArrayList<EmployeeDTO> employees = new ArrayList<>();

    private static HashSet<EmployeeDTO> cleanSet = new HashSet<>();



    public static ArrayList<EmployeeDTO> getDirtyList() {
        return dirtyList;
    }

    private static ArrayList<EmployeeDTO> dirtyList = new ArrayList<>();


    public static ArrayList<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static void setEmployees(ArrayList<EmployeeDTO> employees) {
        EmployeeCollection.employees = employees;
    }

    public static void addToDirtyList(EmployeeDTO employee) {
        dirtyList.add(employee);
    }

    public static int getSize() {
        return employees.size();
    }

    public static void checkForDuplicateIDs() {
        int allEmployeesCount = 0;
        int dirtyCount = 0;
        for (EmployeeDTO employee : employees) {
            allEmployeesCount++;
            for (EmployeeDTO emp : employees) {
                if (employee != emp && employee.getEmpId().equals(emp.getEmpId())) {
                    dirtyCount++;
                    addToDirtyList(employee);
                }
            }
        }
        // change to log:
        logger.log(Level.INFO, allEmployeesCount + " records checked, " + dirtyCount + " moved to dirty data list.");
    }
    public static void checkGenderTypes() {
        int allEmployeesCount = 0;
        int dirtyCount = 0;
        for (EmployeeDTO employee : employees) {
            allEmployeesCount++;
            if (!employee.getGender().equals("M") && !employee.getGender().equals("F")) {
                addToDirtyList(employee);
                dirtyCount++;
            }
        }
        logger.log(Level.INFO, allEmployeesCount + " records checked, " + dirtyCount + " moved to dirty data list.");
    }

    public static void checkDates() {
        int allEmployeesCount = 0;
        int dirtyCount = 0;
        LocalDate thisYear = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        int thisYearInt = Integer.parseInt(thisYear.format(dtf));
        for (EmployeeDTO employee : employees) {

            int dobYear = Integer.parseInt(employee.getDob().substring(employee.getDob().length() - 4));
            if (dobYear > thisYearInt) {
                addToDirtyList(employee);
                dirtyCount++;
            }
            allEmployeesCount++;
        }
        logger.log(Level.INFO, allEmployeesCount + " records checked, " + dirtyCount + " moved to dirty data list.");
    }

    public static EmployeeDTO[] getCleanArray() {
        for (EmployeeDTO employee : employees) {
            if (!dirtyList.contains(employee)) {
                cleanSet.add(employee);
            }
        }
         return cleanSet.toArray(new EmployeeDTO[0]);
    }

    public static void sendToDb(EmployeeDTO[] cleanArray) {
        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());

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
}

