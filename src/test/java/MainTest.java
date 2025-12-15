import org.example.Address;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
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
}