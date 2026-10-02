# KingMaker Android

A native Android R1 scaffold for the KingMaker decision-intelligence workspace. It implements the first narrow-screen capture/dashboard experience from the v7 Blueprint using **Kotlin domain types** and a **Java Android activity**.

## What is included

- A Kotlin domain model for decision stages, explicit provenance, and review fingerprints.
- A Java `MainActivity` with a decision-first dashboard, capture validation, and framing progression.
- An accessible, 360dp-friendly XML layout that visibly identifies simulated execution and preserves the human-authority boundary.
- A unit test for deterministic SHA-256 review fingerprints.

## Build

Install Android SDK Platform 35 and set `ANDROID_HOME`, then run:

```bash
gradle testDebugUnitTest
gradle assembleDebug
```

This is an Android-client vertical-slice scaffold. Per the Blueprint, server-authoritative sealing, AI runs, approval, ADR publication, and immutable history must be integrated through an authenticated backend before production use.
