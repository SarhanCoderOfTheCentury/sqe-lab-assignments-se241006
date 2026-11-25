# sqe-lab

Software Quality Engineering Lab, Fall 2026, DHA Suffa University

Name: Sarhan Ahmed
Student ID: SE241006

This is the one Maven project I am using for all the SQE labs. Lab 01 is in here now, and the later labs get added as new folders / new classes.

## What is in this repo (Lab 01)

```
sqe-lab/
├── pom.xml                         Maven config (JDK 17, JUnit 5.10.2, surefire 3.2.5)
├── .gitignore                      ignores target/
├── src/
│   ├── main/java/com/dsu/sqe/
│   │   ├── Calculator.java         add, subtract, multiply, divide
│   │   └── Average.java            average of an int array (fixed version)
│   └── test/java/com/dsu/sqe/
│       ├── CalculatorTest.java     17 tests
│       └── AverageTest.java        5 tests
├── lab01-demo/
│   ├── task02-average/
│   │   ├── faulty/Average.java     the buggy program from the handout
│   │   └── fixed/Average.java      same program after the fix
│   └── task08-multiply-fault/
│       └── Calculator.java         multiply() with the a + b fault, used for BUG-02
└── lab01-docs/
    ├── Lab01_Report_SE241006.docx  lab report (objective, procedure, screenshots, observations, conclusion)
    ├── task02-error-fault-failure.md
    ├── task03-defect-report-BUG-01.md
    ├── task04-05-classification.md
    ├── task08-test-cases-and-BUG-02.md
    └── screenshots/                put your screenshots here (see the README inside)
```

## How to run

Run these from the `sqe-lab` folder in PowerShell.

Task 2 (plain javac, no Maven):

```powershell
cd lab01-demo\task02-average\faulty
javac Average.java
java Average          # Average = 43.333333333333336   <- failure
cd ..\fixed
javac Average.java
java Average          # Average = 70.0
```

Tasks 6 and 7 (Maven):

```powershell
mvn test              # Tests run: 22, Failures: 0, Errors: 0  -> BUILD SUCCESS
```

For task 7 change the `5` to a `6` in `testAdd`, run `mvn test` again, take the BUILD FAILURE screenshot, then change it back to `5`.

Task 8: copy `lab01-demo/task08-multiply-fault/Calculator.java` over `src/main/java/com/dsu/sqe/Calculator.java`, run `mvn test` (4 tests fail), then put the correct `a * b` back.

## Pushing to GitHub (task 9)

Make an empty repo called `sqe-lab-se241006` on github.com (no README), then:

```powershell
git config --global user.name "Sarhan Ahmed"
git config --global user.email "your-email@example.com"
git init
git add .
git commit -m "Lab 01"
git branch -M main
git remote add origin https://github.com/<your-username>/sqe-lab-se241006.git
git push -u origin main
```

## A mistake in the handout

The `New-Item` command on page 4 of the Lab 01 handout creates `sqelab\src\main\...` (no hyphen) for the first path and `sqe-lab\src\test\...` for the second, so you end up with two different folders. Use `sqe-lab` for both. The folder layout above is already correct.
