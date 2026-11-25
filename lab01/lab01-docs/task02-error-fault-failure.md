# Task 2: Average program - error, fault and failure

Program: `lab01-demo/task02-average/faulty/Average.java`
Input used: `{60, 70, 80}`

## What I ran

```
PS> javac Average.java
PS> java Average
Average = 43.333333333333336
```

The correct answer is (60 + 70 + 80) / 3 = **70.0**.

## In my own words

**Error:** I made a mistake while writing the loop condition. I wanted to go through all the marks but wrote `marks.length - 1` as the limit, probably mixing it up with the last index. That is a human mistake and it only lives in the programmer's head until it gets typed into the code.

**Fault:** the line `for (int i = 0; i < marks.length - 1; i++)`. Because of `< marks.length - 1` the loop stops one element early, so the last mark is never added to `sum`. This is the actual bug sitting in the code.

**Failure:** the program printed `43.333333333333336` when the right answer is `70.0`. This is the wrong behaviour that the user sees when the faulty line gets executed. Testing found it because the actual result (43.33) is not equal to the expected result (70.0).

## The fix

Change the loop condition to `i < marks.length`:

```java
for (int i = 0; i < marks.length; i++) {
    sum += marks[i];
}
```

After the fix:

```
PS> javac Average.java
PS> java Average
Average = 70.0
```

## One more thing I noticed

A fault does not always give a failure. If the last mark is 0, for example `{60, 70, 0}`, the faulty loop skips a 0, so the sum is still 130 and the output is still correct (43.33). The fault is there but the failure does not show. So the input that we test with matters, which is why I also wrote tests with different arrays in `AverageTest.java`.
