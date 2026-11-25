# Lab 02: Test Planning and Test Case Design

Module tested: Course Registration (University Student Portal)

Sarhan Ahmed, SE241006 - Software Quality Engineering Lab, Fall 2026

| File | What it is |
|---|---|
| `test-plan.md` | Test plan with all 11 sections (open it in VS Code and press Ctrl + Shift + V for the preview) |
| `lab02-course-registration.xlsx` | Sheet `TestCases` (10 test cases) and sheet `RTM` (COUNTIF / IF formulas, coverage in G2 = 100%) |
| `Lab02_Report_SE241006.docx` | Lab report (objective, procedure, screenshots, observations, conclusion) |
| `screenshots/` | Put the 3 screenshots here (see the README inside) |

## Where it goes

This folder goes inside the same `sqe-lab` repository that was made in Lab 01:

```
sqe-lab/
├── pom.xml
├── src/ ...                      (Lab 01)
├── lab01-docs/ ...               (Lab 01)
└── lab02-course-registration/    <- this folder
```

## Push to GitHub

```powershell
cd $HOME\sqe-lab
git add .
git commit -m "Add Lab 02 course registration test plan, test cases and RTM"
git push
```

## Quick summary

- 5 requirements (REQ-01 to REQ-05), 10 test cases (6 positive, 4 negative)
- Boundary test: TC-04 (6th course, allowed) and TC-05 (7th course, refused)
- Requirement coverage: 100% (every requirement has at least one test case)
- Tests are only designed here, not run. That is why Actual Result is empty and Status is "Not Run".
