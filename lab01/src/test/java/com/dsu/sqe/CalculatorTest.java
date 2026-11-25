package com.dsu.sqe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    private final Calculator calc = new Calculator();

    // ---------- add ----------
    @Test
    void testAdd() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void testAddNegativeNumbers() {
        assertEquals(-5, calc.add(-2, -3));
    }

    @Test
    void testAddPositiveAndNegative() {
        assertEquals(-2, calc.add(-5, 3));
    }

    @Test
    void testAddZero() {
        assertEquals(7, calc.add(7, 0));
    }

    // ---------- subtract ----------
    @Test
    void testSubtract() {
        assertEquals(5, calc.subtract(10, 5));
    }

    @Test
    void testSubtractGivesNegativeResult() {
        assertEquals(-5, calc.subtract(5, 10));
    }

    @Test
    void testSubtractNegativeNumber() {
        assertEquals(15, calc.subtract(5, -10));
    }

    @Test
    void testSubtractSameNumbers() {
        assertEquals(0, calc.subtract(4, 4));
    }

    // ---------- multiply ----------
    // note: I did not use (2, 2) anywhere because 2 + 2 and 2 * 2 are both 4,
    // so that test could never catch the a + b fault from task 8
    @Test
    void testMultiply() {
        assertEquals(12, calc.multiply(3, 4));
    }

    @Test
    void testMultiplyNegativeNumbers() {
        assertEquals(25, calc.multiply(-5, -5));
    }

    @Test
    void testMultiplyPositiveByNegative() {
        assertEquals(-12, calc.multiply(4, -3));
    }

    @Test
    void testMultiplyByZero() {
        assertEquals(0, calc.multiply(9, 0));
    }

    // ---------- divide ----------
    @Test
    void testDivide() {
        assertEquals(5, calc.divide(10, 2));
    }

    @Test
    void testDivideNegativeNumber() {
        assertEquals(-5, calc.divide(-10, 2));
    }

    @Test
    void testDivideIsIntegerDivision() {
        assertEquals(3, calc.divide(7, 2));
    }

    @Test
    void testDivideZeroByNumber() {
        assertEquals(0, calc.divide(0, 10));
    }

    @Test
    void testDivideByZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}
