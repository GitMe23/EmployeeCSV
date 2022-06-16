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

    private static HashSet<String> checkSet = new HashSet<>();

    public static ArrayList<EmployeeDTO> getDirtyList() {
        return dirtyList;
    }

    private static ArrayList<EmployeeDTO> dirtyList = new ArrayList<>();


    public static ArrayList<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static void setEmployees(ArrayList<EmployeeDTO> employees) {
        EmployeeCollection.employees = employees;
        logger.log(Level.INFO, "Updated employee ArrayList in EmployeeCollection");
    }

    public static void addToDirtyList(EmployeeDTO employee) {
        dirtyList.add(employee);
    }

    public static int getSize() {
        return employees.size();
    }

    public static void checkForDuplicateIDs() {
        logger.log(Level.INFO, "checking for duplicate ids..........");
        int dirtyCount = 0;
        for (EmployeeDTO employee : employees) {

            if (!checkSet.add(employee.getEmpId())) {
                dirtyCount++;
                addToDirtyList(employee);

            }
        }
        logger.log(Level.WARNING, dirtyCount + " records added to dirty data list");
        logger.log(Level.INFO, "Duplicate ID check done");
    }
    public static void checkGenderTypes() {
        logger.log(Level.INFO, "checking gender formats..........");
        int dirtyCount = 0;
        for (EmployeeDTO employee : employees) {
            if (!employee.getGender().equals("M") && !employee.getGender().equals("F")) {
                dirtyCount++;
                addToDirtyList(employee);

            }
        }
        logger.log(Level.WARNING, dirtyCount + " records added to dirty data list");
        logger.log(Level.INFO, "Gender type check done");
    }

    public static void checkDates() {
        logger.log(Level.INFO, "checking DOB formats..........");
        int dirtyCount = 0;
        LocalDate thisYear = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        int thisYearInt = Integer.parseInt(thisYear.format(dtf));
        for (EmployeeDTO employee : employees) {

            int dobYear = Integer.parseInt(employee.getDob().substring(employee.getDob().length() - 4));
            if (dobYear > thisYearInt) {
                dirtyCount++;
                addToDirtyList(employee);

            }
        }
        logger.log(Level.WARNING, dirtyCount + " records added to dirty data list");
        logger.log(Level.INFO, "DOB format check done");
    }

    public static EmployeeDTO[] getCleanArray() {
        logger.log(Level.INFO, "Checking if records are in dirty data list....");
        for (EmployeeDTO employee : employees) {
            if (!dirtyList.contains(employee)) {
                cleanSet.add(employee);
            }
        }
        EmployeeDTO[] cleanArray = cleanSet.toArray(new EmployeeDTO[0]);
        logger.log(Level.INFO, cleanArray.length + " clean records");
        return cleanArray;
    }

    public static void writeDirtyData(String filePath) {
        DirtyListFileWriter dirtyListFileWriter = new DirtyListFileWriter(filePath);
        dirtyListFileWriter.writeDirtyDataToFile(EmployeeCollection.getDirtyList());
    }
    public static HashSet<EmployeeDTO> getCleanSet() {
        return cleanSet;
    }

    public static void clearEmployeesFromDatabase() {
        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());
        employeeDAO.clearDatabase();
    }
}

