# Upgrade Plan: TimeSheet Tracker App (20260802122320)

- **Generated**: 2026-08-02 12:23:20
- **HEAD Branch**: appmod/java-upgrade-20260802122320
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 21.0.7: C:\Program Files\Eclipse Adoptium\jdk-21.0.7.6-hotspot\bin (current project JDK, used by baseline if available)
- JDK 25.0.2: C:\Users\bacon\AppData\Local\jdks\jdk-25.0.2\bin (target runtime, required by step 3)

**Build Tools**
- Maven 3.9.14: C:\apache-maven-3.9.14-bin\apache-maven-3.9.14\bin

## Guidelines

- Upgrade the Java runtime to the latest LTS version available in the environment.
- Preserve existing project behavior and keep the build compilable.
- Use the installed JDK 25 toolchain for verification.

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260802122320
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java 25

## Technology Stack

| Technology/Dependency | Current | Min Compatible Version | Why Incompatible |
| ---------------------- | ------- | ---------------------- | ---------------- |
| Java | 21 | 25 | User requested |
| Maven Compiler Plugin | default via Maven | 3.11+ | Recommended for better Java 25 compatibility |

## Derived Upgrades

- Upgrade Maven compiler source/target properties from 21 to 25.
- Ensure the build uses the installed Java 25 runtime for compilation.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
| ---- | ---------- | ------- | ------ | ------ | ------ |
| pom.xml | maven.compiler.source | 21 | upgrade | 25 | User requested Java 25 target |
| pom.xml | maven.compiler.target | 21 | upgrade | 25 | User requested Java 25 target |

### Source Code Changes

No source code changes are required for this Java runtime-only upgrade.

### Configuration Changes

No configuration file changes are required beyond the Maven compiler properties.

### CI/CD Changes

No CI/CD files were detected in the project.

### Risks & Warnings

- The project uses a Swing desktop app; runtime compatibility must be verified with a compile-and-run check rather than relying only on static changes.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Confirm the target Java 25 toolchain is available before changing the build configuration.
  - **Changes to Make**: None; verify installed toolchain availability.
  - **Verification**: Command: "mvn -version"; JDK: C:\Users\bacon\AppData\Local\jdks\jdk-25.0.2\bin; Expected Result: Maven reports Java 25.

- Step 2: Setup Baseline
  - **Rationale**: Capture the current build state before changing the target runtime.
  - **Changes to Make**: None; run the baseline build and tests with the existing JDK 21 toolchain.
  - **Verification**: Command: "mvn clean test -q"; JDK: C:\Program Files\Eclipse Adoptium\jdk-21.0.7.6-hotspot\bin; Expected Result: Baseline build and tests complete.

- Step 3: Upgrade Java Runtime Target
  - **Rationale**: Align the Maven compiler settings with the target Java 25 runtime.
  - **Changes to Make**: Update the Maven compiler source/target properties in pom.xml from 21 to 25.
  - **Verification**: Command: "mvn clean test-compile -q"; JDK: C:\Users\bacon\AppData\Local\jdks\jdk-25.0.2\bin; Expected Result: Compilation succeeds with Java 25.

- Step 4: Final Validation
  - **Rationale**: Verify the upgraded project builds and tests successfully with the target runtime.
  - **Changes to Make**: None; fix any issues surfaced by the verification run.
  - **Verification**: Command: "mvn clean test -q"; JDK: C:\Users\bacon\AppData\Local\jdks\jdk-25.0.2\bin; Expected Result: Full test suite passes.
