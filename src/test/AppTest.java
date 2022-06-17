package com.sparta.ah;

import com.sparta.ah.jdbc.ConnectionManager;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    String file = "src/test/java/com/sparta/ah/TestFile.csv";

    @Test
    public void checkDuplicateIDs() {

        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        EmployeeCollection.checkForDuplicateIDs();
        Assertions.assertEquals(57, EmployeeCollection.getDirtyList().size());
    }

    @Test
    @DisplayName("check employee array list returns zero")
    public void checkEmployeeArrayListReturnsZero()
    {

        ArrayList<EmployeeDTO> emptyArrayList = new ArrayList<>();
        EmployeeCollection.setEmployees(emptyArrayList);
        Assertions.assertEquals(0, EmployeeCollection.getSize());
    }
    @Test
    @DisplayName("check clean array zero to start off with")
    public void checkCleanArrayZero()
    {

        ArrayList<EmployeeDTO> emptyArrayList = new ArrayList<>();
        EmployeeCollection.setEmployees(emptyArrayList);
        Assertions.assertEquals(0, EmployeeCollection.getCleanArray().length);
    }

    @Test
    public void checkSetEmployeesReadsAllLines() {

        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        Assertions.assertEquals(10_000, EmployeeCollection.getEmployees().size());
    }
    @Test
    public void checkIfSmallFileIsRead() {
        String file2 = "src/test/java/com/sparta/ah/TestFileToFilter.csv";

        EmployeeCollection.setEmployees(FileIO.readFromFile(file2));
        Assertions.assertEquals(30, EmployeeCollection.getEmployees().size());
    }
    @Test
    public void checkIfLargeFileIsRead() {
        String file3 = "src/test/java/com/sparta/ah/EmployeeRecordsLarge.csv";

        EmployeeCollection.setEmployees(FileIO.readFromFile(file3));
        Assertions.assertEquals(65499, EmployeeCollection.getEmployees().size());
    }



}
