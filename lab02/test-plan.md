# Test Plan: University Student Portal - Course Registration Module

## 1. Introduction
This plan describes how the Course Registration module of the University Student Portal will be tested. The module lets a logged-in student add courses and drop courses for the semester.

## 2. Objectives
- Check that a student can add a course that is open and has seats available.
- Check that a full course, a duplicate course and a 7th course are rejected with a clear message.
- Check that the limit of 6 courses per semester works at the boundary (6th course allowed, 7th course refused).
- Check that a student can drop a course before the drop deadline, and cannot drop it after the deadline.

## 3. Scope
**In scope:** adding a course, full course check, maximum 6 courses per semester, duplicate course check, dropping a course, error and success messages.

**Out of scope:** login and password reset, fee payment, timetable clash checking, performance testing.

## 4. Requirements to be Tested
| ID | Requirement |
|----|-------------|
| REQ-01 | A logged-in student can add a course that is open and has seats available |
| REQ-02 | The system shall not allow adding a course that is full |
| REQ-03 | The system shall not allow a student to register for more than 6 courses in a semester |
| REQ-04 | The system shall not allow adding the same course twice |
| REQ-05 | A student can drop a registered course before the drop deadline |

## 5. Test Approach
- Manual testing using test cases written in a spreadsheet (`lab02-course-registration.xlsx`).
- Both positive and negative test cases will be written. Every requirement gets at least one test case.
- Boundary value testing for the course limit: the 6th course (allowed) and the 7th course (refused).
- Test cases are run in the order of their IDs, because some of them depend on the data left by earlier ones (for example TC-08 needs CS-310 to be full, which TC-03 also uses). The test that needs the drop deadline to be over (TC-10) is run last, because the deadline has to be changed on the test site for it. The test data is reset before every new test cycle.
- Defects will be recorded in GitHub Issues.

## 6. Test Environment
- Windows laptop with the latest Google Chrome.
- Test site of the Student Portal (not the live site). The drop deadline on the test site can be changed by the developers.
- Test accounts (password for all of them is `Pass@123`):

| Student ID | Courses already registered |
|------------|----------------------------|
| S1001 | none |
| S1002 | CS-301 |
| S1003 | CS-101, CS-102, CS-103, CS-104, CS-105 (5 courses) |
| S1004 | CS-101, CS-102, CS-103, CS-104, CS-105, CS-106 (6 courses) |
| S1005 | CS-310 |

- Test courses:

| Course | Name | Seats available |
|--------|------|-----------------|
| CS-301 | Database Systems | 5 |
| CS-305 | Computer Networks | 1 |
| CS-310 | Operating Systems | 0 (full) |

## 7. Roles
| Role | Name | Responsibility |
|------|------|----------------|
| Test Lead | Sarhan Ahmed | Prepares the plan and reviews results |
| Tester | Sarhan Ahmed | Writes and runs the test cases |

## 8. Schedule
| Activity | Day |
|----------|-----|
| Write test cases and RTM | Day 1 to 2 |
| Prepare test accounts and course data | Day 2 |
| Run the test cases | Day 3 to 4 |
| Report the results | Day 5 |

## 9. Entry and Exit Criteria
**Entry:** requirements approved, test site ready, test accounts S1001 to S1005 and courses CS-301, CS-305 and CS-310 created with the data given above.

**Exit:** all test cases run, no open High priority defects, and every requirement has at least one test case (100% requirement coverage in the RTM).

## 10. Risks
| Risk | What we will do |
|------|-----------------|
| Test site is not available | Ask the developers for a backup test server |
| Requirements change (for example the limit of 6 courses becomes 7) | Update the test cases and the RTM |
| Test data is changed by earlier tests (seats, number of courses) | Reset the data before each test cycle and run the tests in order |
| Drop deadline cannot be changed on the test site, so the "after deadline" test cannot be run | Ask the developers to set the deadline manually, or run this test after the real deadline passes |

## 11. Deliverables
Test plan (`test-plan.md`), test cases and RTM (`lab02-course-registration.xlsx`), and the defect list in GitHub Issues.
