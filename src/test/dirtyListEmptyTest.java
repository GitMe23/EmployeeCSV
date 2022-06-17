package com.sparta.ah;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class dirtyListEmptyTest {

    @Test
    @DisplayName("Check whether dirty list is empty")
    public void checkDirtyListIsEmpty(){
        String file = "src/test/java/com/sparta/ah/TestFile.csv";
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        Assertions.assertTrue(EmployeeCollection.getDirtyList().isEmpty());
    }

}
