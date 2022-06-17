package com.sparta.ah;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class genderTest {
    @Test
    public void checkGender() {
        String file ="src/test/java/com/sparta/ah/TestFile.csv";
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        EmployeeCollection.checkGenderTypes();
        Assertions.assertEquals(5, EmployeeCollection.getDirtyList().size());
    }

}
