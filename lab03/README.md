# Lab 03 - Black-Box Testing (Lab Task: Marks Entry Checker)

DHA Suffa University, Department of Software Engineering
Course: Software Quality Engineering

## What this folder contains

```
sqe-lab/
└── lab03-blackbox/
    ├── MarksEntryChecker.java     <- given by the instructor (copy it here, do not edit)
    ├── lab03-task-marks.xlsx      <- partitions, boundary values, test cases, defects
    ├── screenshots/               <- put your 3 screenshots here
    │   ├── 01-partitions-and-boundaries.png
    │   ├── 02-test-cases-results.png
    │   └── 03-failed-test-output.png
    ├── Lab03_Report.docx          <- final lab report (exported from the report doc)
    └── README.md
```

## Requirement tested

Marks must be between 0 and 100. Both 0 and 100 are allowed.

## Techniques used

- Equivalence Partitioning (3 partitions: below 0, 0 to 100, above 100)
- Boundary Value Analysis (-1, 0, 1, 99, 100, 101)

## How to run one test case

```
cd lab03-blackbox
java MarksEntryChecker.java
```

Type the input value, press Enter, and copy the output into the Actual Result column.

## Submit to GitHub

```
cd ..
git add .
git commit -m "Add Lab 03 task: marks entry testing"
git push
```
