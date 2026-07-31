# Upgrade Plan: TimeSheet Tracker App (20260721215202)

- **Generated**: 2026-07-21 21:52:02
- **HEAD Branch**: main
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 21.0.7: C:\Program Files\Eclipse Adoptium\jdk-21.0.7.6-hotspot\bin (current project JDK, used by step 2)
- JDK 25: **<TO_BE_INSTALLED>** (required by step 3)

**Build Tools**
- Maven 3.9.14: C:\apache-maven-3.9.14-bin\apache-maven-3.9.14\bin

## Guidelines

- Upgrade the Java runtime to the latest LTS target supported by the project.
- Keep changes minimal and preserve existing application behavior.
- Use the available JDK and Maven toolchain for verification.

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260721215202
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java 25

## Technology Stack

| Technology/Dependency | Current | Min Compatible Version | Why Incompatible |
| ---------------------- | ------- | ---------------------- | ---------------- |
| Java                   | 21      | 25                     | User requested   |
| Maven                  | 3.9.14  | 3.9.14                 | Already compatible with the current toolchain |

## Derived Upgrades

- Update the Maven compiler target from Java 21 to Java 25.
- Ensure the build runs with the installed JDK 25 toolchain.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
| ---- | ---------- | ------- | ------ | ------ | ------ |
| pom.xml | maven.compiler.source | 21 | upgrade | 25 | User requested Java 25 target |
| pom.xml | maven.compiler.target | 21 | upgrade | 25 | User requested Java 25 target |

### Source Code Changes

| File | Location | Current | Required Change | Reason |
| ---- | -------- | ------- | --------------- | ------ |
| None | - | - | No source changes required | The project already uses compatible Java syntax for this upgrade |

### Configuration Changes

| File | Property/Setting | Current | Required Change | Reason |
| ---- | ---------------- | ------- | --------------- | ------ |
| pom.xml | compiler source/target | 21 | Set to 25 | Align build with Java 25 |

### CI/CD Changes

| File | Location | Current | Required Change |
| ---- | -------- | ------- | --------------- |
| None | - | - | No CI/CD files were found that require updates |

### Risks & Warnings

- The project is small and currently uses standard Maven compilation settings, so the upgrade is expected to be low risk.
- Verification will confirm that the build still compiles and tests pass under the new JDK.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Install the required JDK 25 toolchain so the project can be verified under the target runtime.
  - **Changes to Make**: Install JDK 25 and confirm it is available to Maven.
  - **Verification**: Run the JDK discovery tool and confirm that JDK 25 is present.

- Step 2: Setup Baseline
  - **Rationale**: Establish a baseline build and test run with the current Java 21 toolchain before changing the target version.
  - **Changes to Make**: Run the existing Maven build and test workflow.
  - **Verification**: Command: mvn clean test -q; JDK: JDK 21 path; Expected Result: Baseline build and tests complete.

- Step 3: Upgrade Compiler Target to Java 25
  - **Rationale**: Update the Maven compiler properties to target the requested Java LTS version.
  - **Changes to Make**: Apply the pom.xml compiler target upgrades from the Impact Analysis.
  - **Verification**: Command: mvn clean test-compile -q; JDK: JDK 25 path; Expected Result: Main and test sources compile successfully.

- Step 4: Final Validation
  - **Rationale**: Confirm the project passes the full Maven test suite using the upgraded runtime.
  - **Changes to Make**: Resolve any regressions caused by the runtime change.
  - **Verification**: Command: mvn clean test -q; JDK: JDK 25 path; Expected Result: Full test suite passes.
