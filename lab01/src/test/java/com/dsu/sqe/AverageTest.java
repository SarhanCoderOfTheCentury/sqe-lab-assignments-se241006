package com.dsu.sqe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AverageTest {

    @Test
    void testAverageOfThreeMarks() {
        assertEquals(70.0, Average.average(new int[]{60, 70, 80}), 0.0001);
    }

    @Test
    void testAverageOfSingleMark() {
        assertEquals(55.0, Average.average(new int[]{55}), 0.0001);
    }

    @Test
    void testAverageWithDecimalResult() {
        assertEquals(1.5, Average.average(new int[]{1, 2}), 0.0001);
    }

    @Test
    void testAverageOfNegativeNumbers() {
        assertEquals(-20.0, Average.average(new int[]{-10, -20, -30}), 0.0001);
    }

    @Test
    void testAverageOfAllZeros() {
        assertEquals(0.0, Average.average(new int[]{0, 0, 0}), 0.0001);
    }
}
