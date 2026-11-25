# Task 3: Defect report BUG-01

| Field | Details |
|---|---|
| Defect ID | BUG-01 |
| Title | average() returns a wrong value because the last element is skipped |
| Steps to reproduce | 1. Compile and run `Average.java` (`javac Average.java` then `java Average`)<br>2. Use the input array `{60, 70, 80}` |
| Expected result | Average = 70.0 |
| Actual result | Average = 43.333333333333336 |
| Severity / Priority | High / High |
| Status | Fixed - loop condition changed from `i < marks.length - 1` to `i < marks.length`, retested and the output is 70.0 |

**Root cause:** off-by-one fault in the for loop condition of `average()`. The last element never gets added to `sum`, but the sum is still divided by the full `marks.length`.
