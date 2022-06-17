package com.sparta.ah;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class checkIfAllFilteringWorksTogether {
    @Test
    @DisplayName("Checking whether all filtering works together - Duplicate IDS, Gender, DOB")
    public void checkIfAllFilteringWorksTogether() {
        String file2 = "src/test/java/com/sparta/ah/TestFileToFilter.csv";
//        ArrayList<EmployeeDTO> blank = new ArrayList<>();

        EmployeeCollection.setEmployees(FileIO.readFromFile(file2));
        EmployeeCollection.checkForDuplicateIDs();
        EmployeeCollection.checkGenderTypes();
        EmployeeCollection.checkDates();
        //EmployeeDTO[] cleanArray = EmployeeCollection.getDirtyList();
        Assertions.assertEquals(14, EmployeeCollection.getDirtyList().size());
    }
}
