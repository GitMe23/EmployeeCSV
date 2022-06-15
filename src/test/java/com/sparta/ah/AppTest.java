package com.sparta.ah;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    String file = "src/test/java/com/sparta/ah/TestFile.csv";


    @Test
    public void checkSetEmployeesReadsAllLines() {
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        Assertions.assertEquals(10_000, EmployeeCollection.getEmployees().size());
    }


    @Test
    public void checkDuplicateIDs() {
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        EmployeeCollection.checkForDuplicateIDs();
        Assertions.assertEquals(114, EmployeeCollection.getDirtyList().size());
    }

    @Test
    public void checkGender() {
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        EmployeeCollection.checkGenderTypes();
        Assertions.assertEquals(5, EmployeeCollection.getDirtyList().size());
    }


}
