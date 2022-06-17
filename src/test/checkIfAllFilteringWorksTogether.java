import com.sparta.ah.EmployeeCollection;
import com.sparta.ah.FileIO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class checkIfAllFilteringWorksTogether {
    @Test
    @DisplayName("Checking whether all filtering works together - Duplicate IDS, Gender, DOB")
    public void checkIfAllFilteringWorksTogether() {
        String file2 = "src/test/TestFileToFilter.csv";
        EmployeeCollection.setEmployees(FileIO.readFromFile(file2));
        EmployeeCollection.checkForDuplicateIDs();
        EmployeeCollection.checkGenderTypes();
        EmployeeCollection.checkDates();
        Assertions.assertEquals(14, EmployeeCollection.getDirtyList().size());
    }
}
