package com.sparta.ah;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

public class getCleanArrayTest {

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
        Assertions.assertEquals(9938, cleanArray.length);
    }
}
