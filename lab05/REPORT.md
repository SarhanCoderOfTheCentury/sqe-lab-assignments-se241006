# Lab 05 – Unit Testing with JUnit 5
**Task:** Unit test suite for `ShoppingCart`
**Course:** Software Quality Engineering, Fall 2026

## 1. Test case table

The test cases were written from the requirements only (not from the code). Rs. amounts are used for the examples.

| TC ID | Req | Test scenario | Input | Expected result | Test method |
|-------|-----|---------------|-------|-----------------|-------------|
| TC-01 | REQ-1 | Add one item | `addItem("Pen", 10, 2)` | Item count = 2, total = 20 | `addItemStoresTheItem` |
| TC-02 | REQ-1 | Add the same item again | `addItem("Pen", 10, 2)` then `addItem("Pen", 10, 3)` | Item count = 5, total = 50 | `addingSameItemIncreasesQuantity` |
| TC-03 | REQ-2 | Price = 0 | `addItem("Pen", 0, 1)` | `IllegalArgumentException` | `invalidItemIsRejected` |
| TC-04 | REQ-2 | Price negative | `addItem("Pen", -5, 1)` | `IllegalArgumentException` | `invalidItemIsRejected` |
| TC-05 | REQ-2 | Quantity = 0 | `addItem("Pen", 10, 0)` | `IllegalArgumentException` | `invalidItemIsRejected` |
| TC-06 | REQ-2 | Quantity negative | `addItem("Pen", 10, -1)` | `IllegalArgumentException` | `invalidItemIsRejected` |
| TC-07 | REQ-2 | Empty name | `addItem("", 10, 1)` | `IllegalArgumentException` | `invalidItemIsRejected` |
| TC-08 | REQ-2 | Rejected item must not enter the cart | `addItem("Pen", 0, 1)` | Exception, item count stays 0 | `rejectedItemDoesNotChangeTheCart` |
| TC-09 | REQ-3 | Empty cart | no items | Total = 0 | `emptyCartHasZeroTotal` |
| TC-10 | REQ-3 | Total of several items | Pen 10x2, Notebook 50x3, Bag 500.5x1 | Total = 670.5 | `totalIsSumOfPriceTimesQuantity` |
| TC-11 | REQ-4 | Discount 0 (lower boundary) | total 200, `applyDiscount(0)` | Total = 200 | `validDiscountReducesTheTotal` |
| TC-12 | REQ-4 | Discount 1 | total 200, `applyDiscount(1)` | Total = 198 | `validDiscountReducesTheTotal` |
| TC-13 | REQ-4 | Discount 25 (middle value) | total 200, `applyDiscount(25)` | Total = 150 | `validDiscountReducesTheTotal` |
| TC-14 | REQ-4 | Discount 49 | total 200, `applyDiscount(49)` | Total = 102 | `validDiscountReducesTheTotal` |
| TC-15 | REQ-4 | Discount 50 (upper boundary) | total 200, `applyDiscount(50)` | Total = 100 | `validDiscountReducesTheTotal` |
| TC-16 | REQ-4 | Discount -1 (just below range) | `applyDiscount(-1)` | `IllegalArgumentException` | `invalidDiscountIsRejected` |
| TC-17 | REQ-4 | Discount 51 (just above range) | `applyDiscount(51)` | `IllegalArgumentException` | `invalidDiscountIsRejected` |
| TC-18 | REQ-4 | Discount 100 | `applyDiscount(100)` | `IllegalArgumentException` | `invalidDiscountIsRejected` |
| TC-19 | REQ-4 | Discount -50 | `applyDiscount(-50)` | `IllegalArgumentException` | `invalidDiscountIsRejected` |
| TC-20 | REQ-4 | Rejected discount must not change total | total 200, `applyDiscount(51)` | Exception, total still 200 | `rejectedDiscountLeavesTotalUnchanged` |
| TC-21 | REQ-5 | Remove an item that exists | cart has Pen and Bag, `removeItem("Pen")` | `true`, count = 1, total = 500 | `removeExistingItemReturnsTrue` |
| TC-22 | REQ-5 | Remove an item that is not in the cart | `removeItem("Laptop")` | `false`, cart unchanged | `removeMissingItemReturnsFalse` |
| TC-23 | REQ-5 | Remove the same item twice | `removeItem("Pen")` two times | first `true`, second `false` | `removingTheSameItemTwiceReturnsFalseTheSecondTime` |
| TC-24 | REQ-6 | Empty cart | no items | Item count = 0 | `emptyCartHasZeroItems` |
| TC-25 | REQ-6 | Count is total quantity | Pen 2, Notebook 3, Pen 1 more | Item count = 6 | `itemCountIsTotalQuantity` |

**Coverage check:** REQ-1 (TC-01, 02), REQ-2 (TC-03 to 08), REQ-3 (TC-09, 10), REQ-4 (TC-11 to 20), REQ-5 (TC-21 to 23), REQ-6 (TC-24, 25). All six requirements are covered. The discount boundary values 0, 50, -1 and 51 are TC-11, TC-15, TC-16 and TC-17.

The parameterized tests count one test per row, so the class has 25 tests in total.

## 2. Project structure

```
lab05-unit-testing/
├── pom.xml
└── src/
    ├── main/java/com/sqelab/lab05/
    │   ├── BankAccount.java
    │   ├── PasswordValidator.java
    │   ├── LateFeeCalculator.java
    │   └── ShoppingCart.java          <- new
    └── test/java/com/sqelab/lab05/
        ├── BankAccountTest.java
        ├── PasswordValidatorTest.java
        ├── LateFeeCalculatorTest.java
        ├── LifecycleDemoTest.java
        └── ShoppingCartTest.java      <- new
```

## 3. Running the tests

```
cd lab05-unit-testing
mvn test
mvn test -Dtest=ShoppingCartTest
```

## 4. Defect description

> Fill this part in from your own failing run. The example below is the defect that the discount boundary tests are designed to catch.

**Failing test:** `ShoppingCartTest.validDiscountReducesTheTotal` (row `50, 200.0`), requirement REQ-4

- **Input:** cart total Rs. 200, then `applyDiscount(50)`.
- **Expected result:** the discount is accepted and `getTotal()` returns 100.0, because REQ-4 says 0 to 50 are both included.
- **Actual result:** `IllegalArgumentException` was thrown, so the test ended with an **Error**.
- **Cause:** the range check in `applyDiscount` used `percent >= 50` instead of `percent > 50`, so the upper boundary was wrongly rejected.

**Fix (one line):**

```java
// before
if (percent < 0 || percent >= 50) {
// after
if (percent < 0 || percent > 50) {
```

After the fix `mvn test` gives `BUILD SUCCESS`.

## 5. Expected output (successful run)

```
[INFO] Tests run: 25, Failures: 0, Errors: 0, Skipped: 0, ... -- in ShoppingCartTest
[INFO] BUILD SUCCESS
```

With the other four test classes of this lab in the project, the full run shows 55 tests (30 + 25), 1 skipped.

## 6. GitHub repository

https://github.com/YOUR-USERNAME/sqe-lab
