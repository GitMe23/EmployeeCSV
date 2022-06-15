package com.sparta.ah;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
{


    String file = "src/test/java/com/sparta/ah/TestFile.csv";

    @Test
    @DisplayName("Check whether dirty list is empty")
    public void checkDirtyListIsEmpty(){

        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        Assertions.assertTrue(EmployeeCollection.getDirtyList().isEmpty());
    }

    @Test
    public void checkSetEmployeesReadsAllLines() {
        String file = "src/test/java/com/sparta/ah/TestFile.csv";
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        Assertions.assertEquals(10_000, EmployeeCollection.getEmployees().size());
    }


    @Test
    public void checkDuplicateIDs() {
        String file = "src/test/java/com/sparta/ah/TestFile.csv";
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        EmployeeCollection.checkForDuplicateIDs();
        Assertions.assertEquals(57, EmployeeCollection.getDirtyList().size());
    }

    @Test
    public void checkGender() {
        String file = "src/test/java/com/sparta/ah/TestFile.csv";
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        EmployeeCollection.checkGenderTypes();
        Assertions.assertEquals(62, EmployeeCollection.getDirtyList().size());
    }

    @Test
    @DisplayName("check employee array list returns zero")
    public void checkEmployeeArrayListReturnsZero()
    {
        String file = "src/test/java/com/sparta/ah/TestFile.csv";
        ArrayList<EmployeeDTO> emptyArrayList = new ArrayList<>();
        EmployeeCollection.setEmployees(emptyArrayList);
        Assertions.assertEquals(0, EmployeeCollection.getSize());
    }

    @Test
    @DisplayName("")
    public void getCleanArray(){
        String file = "src/test/java/com/sparta/ah/TestFile.csv";
        ArrayList<EmployeeDTO> blank = new ArrayList<>();
        EmployeeCollection.setEmployees(blank);
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        EmployeeCollection.checkForDuplicateIDs();
        EmployeeCollection.checkGenderTypes();
        EmployeeCollection.checkDates();
        EmployeeDTO[] cleanArray = EmployeeCollection.getCleanArray();
        Assertions.assertEquals(9881, cleanArray.length);
    }


}
