package com.dsu.sqe;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a + b;   // deliberate fault for task 8 (BUG-02)
    }

    // integer division, so 7 / 2 gives 3. Dividing by zero throws ArithmeticException
    public int divide(int a, int b) {
        return a / b;
    }
}
