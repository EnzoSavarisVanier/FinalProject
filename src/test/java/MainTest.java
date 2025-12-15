import org.example.Address;
import org.example.Department;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @DisplayName("isPostalCodeValid: ")
    void isPostalCodeValid() {
        String input = "";
        boolean expResult = false;
        boolean result = Address.isPostalCodeValid(input);
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("isPostalCodeValid: dsa")
    void isPostalCodeValiddsa() {
        String input = "dsa";
        boolean expResult = false;
        boolean result = Address.isPostalCodeValid(input);
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("isPostalCodeValid: H3H3H3")
    void isPostalCodeValidH3H3H3() {
        String input = "H3H3H3";
        boolean expResult = true;
        boolean result = Address.isPostalCodeValid(input);
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("isPostalCodeValid: H00000")
    void isPostalCodeValidH00000() {
        String input = "H00000";
        boolean expResult = false;
        boolean result = Address.isPostalCodeValid(input);
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("isDepartmentNameValid: ")
    void isDepartmentNameValid() {
        String input = "";
        boolean expResult = false;
        boolean result = Department.isDepartmentNameValid(input);
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("isDepartmentNameValid: DSA")
    void isDepartmentNameValiddsa() {
        String input = "DSA";
        boolean expResult = true;
        boolean result = Department.isDepartmentNameValid(input);
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("isDepartmentNameValid: ds7j")
    void isDepartmentNameValidds7j() {
        String input = "ds7j";
        boolean expResult = false;
        boolean result = Department.isDepartmentNameValid(input);
        assertEquals(expResult, result);
    }
}