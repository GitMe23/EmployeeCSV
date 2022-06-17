import com.sparta.ah.EmployeeCollection;
import com.sparta.ah.EmployeeDTO;
import com.sparta.ah.FileIO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

public class getCleanArrayTest {

    @Test
    public void getCleanArray(){
        String file = "src/test/TestFile.csv";
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
