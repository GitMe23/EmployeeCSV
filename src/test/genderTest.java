import com.sparta.ah.EmployeeCollection;
import com.sparta.ah.FileIO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class genderTest {
    @Test
    public void checkGender() {
        String file ="src/test/TestFile.csv";
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        EmployeeCollection.checkGenderTypes();
        Assertions.assertEquals(5, EmployeeCollection.getDirtyList().size());
    }

}
