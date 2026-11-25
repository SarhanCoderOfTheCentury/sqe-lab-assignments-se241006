# Task 8: Test cases for Calculator, deliberate fault and BUG-02

## 5 test cases (run against the version with the fault in multiply)

For this run I changed `multiply()` to `return a + b;` (file: `lab01-demo/task08-multiply-fault/Calculator.java`).

| TC ID | Feature | Input | Expected Result | Actual Result | Status |
|---|---|---|---|---|---|
| TC-01 | add() | a = 2, b = 3 | 5 | 5 | Pass |
| TC-02 | subtract() | a = 10, b = 5 | 5 | 5 | Pass |
| TC-03 | multiply() | a = 3, b = 4 | 12 | 7 | **Fail** |
| TC-04 | multiply() | a = -5, b = -5 | 25 | -10 | **Fail** |
| TC-05 | divide() | a = 10, b = 0 | ArithmeticException | ArithmeticException | Pass |

TC-03 and TC-04 are `testMultiply` and `testMultiplyNegativeNumbers` in `CalculatorTest.java`. JUnit reported `expected: <12> but was: <7>` and `expected: <25> but was: <-10>`. In the whole suite 4 of the 22 tests failed with the fault in place (the other two are `testMultiplyByZero`: expected 0 but was 9, and `testMultiplyPositiveByNegative`: expected -12 but was 1).

After fixing multiply back to `a * b` all 5 test cases pass again (22 of 22 in the full suite).

A small observation: if I had only tested `multiply(2, 2)` the fault would have stayed hidden, because 2 + 2 and 2 * 2 are both 4. So the test data has to be chosen carefully.

## Defect report BUG-02

| Field | Details |
|---|---|
| Defect ID | BUG-02 |
| Title | multiply() returns the sum of the two numbers instead of the product |
| Steps to reproduce | 1. Open `Calculator.java` where `multiply()` has `return a + b;`<br>2. Run `mvn test` (or call `calc.multiply(3, 4)`) |
| Expected result | multiply(3, 4) = 12 |
| Actual result | multiply(3, 4) = 7 (JUnit message: `expected: <12> but was: <7>`) |
| Severity / Priority | High / High |
| Status | Fixed - `return a + b;` changed back to `return a * b;`, all tests pass again |

**Fix:**

```java
public int multiply(int a, int b) {
    return a * b;
}
```
