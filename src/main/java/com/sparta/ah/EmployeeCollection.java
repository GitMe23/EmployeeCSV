package com.sparta.ah;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;

import com.sparta.ah.logging.*;

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


    public static void setEmployees(ArrayList<EmployeeDTO> employees) {
        EmployeeCollection.employees = employees;
    }

    public static void addToDirtyList(EmployeeDTO employee) {
        dirtyList.add(employee);
    }

    public static int getSize() {
        return employees.size();
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
            //
            // methods for interacting with employees
            // EmployeeCollection.addToCollection()




    public static void checkDates() {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        LocalDate today = LocalDate.now();
//        String formattedString = today.format(dtf);

//        for (EmployeeDTO employee : employees) {
//            if (employee.getDob().length() < 10 ) {
//                employee.formatDob();
//            }
//            LocalDate dob = LocalDate.parse(employee.getDob(), dtf);
//            if (dob.isAfter(today)) {
//                addToDirtyList(employee);
//            }
//        }
        //logger.log(Level.INFO, allEmployeesCount + " records checked, " + dirtyCount + " moved to dirty data list.");
    }
}
