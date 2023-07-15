package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testVerifyNumberParityReturnsTrueForEvenNumber() {
        Main.verifyNumberParity(2);
        assertEquals(true, Main.isEvenNumber);
    }
}