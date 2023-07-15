package numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberVerifierTest {

    private NumberVerifier numberVerifier;
    @BeforeEach
    void setUp() {
        numberVerifier = new NumberVerifier();
    }

    @Test
    void testVerifyNumberParityReturnsTrueForEvenNumber() {
        numberVerifier.verifyNumberParity(2);
        assertEquals(true, numberVerifier.isEvenNumber);
    }

    @Test
    void testVerifyNumberParityReturnsTrueForOddNumber() {
        numberVerifier.verifyNumberParity(1);
        assertEquals(true, numberVerifier.isOddNumber);
    }

    @Test
    void testVerifyNumberParityReturnsTrueForZero() {
        numberVerifier.verifyNumberParity(0);
        assertEquals(true, numberVerifier.isEvenNumber);
    }

    @Test
    void testVerifyNumberParityThrowsExceptionForNegativeNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    numberVerifier.verifyNumberParity(-2);
                });
    }

    @Test
    void testVerifyNumberParityReturnsTrueForLongMaximumValueAsOddNumber() {
        numberVerifier.verifyNumberParity(Long.MAX_VALUE);
        assertEquals(true, numberVerifier.isOddNumber);
    }

    @Test
    void testVerifyNumberParityThrowsExceptionForLongMinValue() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    numberVerifier.verifyNumberParity(-2);
                });
    }

    @Test
    void testCheckSquareNumberReturnsTrueForSquareNumber() {
        numberVerifier.checkSquareNumber(4);
        assertEquals(true, numberVerifier.isSquareNumber);
    }

    @Test
    void testCheckSquareNumberReturnsFalseForNonSquareNumber() {
        numberVerifier.checkSquareNumber(10);
        assertEquals(false, numberVerifier.isSquareNumber);
    }

    @Test
    void testCheckSquareNumberReturnsFalseForNegativeNumber() {
        numberVerifier.checkSquareNumber(-4);
        assertEquals(false, numberVerifier.isSquareNumber);
    }

    @Test
    void testCheckSquareNumberReturnsTrueForZero() {
        numberVerifier.checkSquareNumber(0);
        assertEquals(true, numberVerifier.isSquareNumber);
    }

    @Test
    void testCheckSquareNumberThrowsExceptionForMaxValue() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    numberVerifier.checkSquareNumber(Long.MAX_VALUE);
                });
    }

    @Test
    public void testCheckBuzzNumber_DivisibleBySeven_ReturnsTrue() {
        numberVerifier.checkBuzzNumber(14);
        assertEquals(true, numberVerifier.isBuzzNumber);
    }

    @Test
    public void testCheckBuzzNumber_EndsWithSeven_ReturnsTrue() {
        numberVerifier.checkBuzzNumber(17);
        assertEquals(true, numberVerifier.isBuzzNumber);
    }

    @Test
    public void testCheckBuzzNumber_NumberIsSeven_ReturnsTrue() {
        numberVerifier.checkBuzzNumber(7);
        assertEquals(true, numberVerifier.isBuzzNumber);
    }

    @Test
    public void testCheckBuzzNumber_NotDivisibleBySevenAndDoesNotEndWithSeven_ReturnsFalse() {
        numberVerifier.checkBuzzNumber(75);
        assertEquals(false, numberVerifier.isBuzzNumber);
    }

    @Test
    public void testCheckBuzzNumber_NegativeNumber_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    numberVerifier.checkBuzzNumber(-1);
                });
    }

    @Test
    public void testCheckSunnyNumber_NotPerfectSquare_ReturnsFalse() {
        numberVerifier.checkSunnyNumber(10);
        assertEquals(false, numberVerifier.isSunnyNumber);
    }

    @Test
    public void testCheckSunnyNumber_NumberIsPerfectSquare_ReturnsFalse() {
        numberVerifier.checkSunnyNumber(16);
        assertEquals(false, numberVerifier.isSunnyNumber);
    }

    @Test
    public void testCheckSunnyNumber_NumberIsPerfectSquarePlusOne_ReturnsTrue() {
        numberVerifier.checkSunnyNumber(24);
        assertEquals(true, numberVerifier.isSunnyNumber);
    }

    @Test
    public void testCheckSunnyNumber_NumberIsZero_ReturnsFalse() {
        numberVerifier.checkSunnyNumber(0);
        assertEquals(true, numberVerifier.isSunnyNumber);
    }

    @Test
    public void testCheckSunnyNumber_NegativeNumber_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    numberVerifier.checkSunnyNumber(-1);
                });
    }


}