# 13 — Implementation Plan & AI Developer Brief

## 1. Build order

The implementation agent must follow this dependency order. It must not jump directly to UI polish or real-provider integration.

### M0 — Contract freeze

Implement/lock:

- identifiers and status enums;
- command/event envelopes;
- error codes;
- revision canonicalization/JCS hashing;
- provenance modes;
- policy/version identifiers;
- core DTO schemas.

Gate: contract fixtures pass.

### M1 — Domain skeleton

Implement:

- Decision aggregate;
- draft/revision sealing;
- claims/evidence;
- policy ports;
- commands/events;
- idempotency;
- projection replay.

Gate: stale write, atomic transaction and replay tests pass.

### M2 — PostgreSQL persistence

Implement schema, migrations, immutability triggers, indexes, job table and cost ledger.

Gate: clean install + migration + rollback/restore drill.

### M3 — API

Implement authentication boundary, owner allowlist, authorization, commands, queries, versioning and error contract.

Gate: API contract tests.

### M4 — Mock governed execution

Implement queue leasing, planner, task lifecycle, mock gateway, structured artifacts, semantic validator, quarantine and checkpoints.

Gate: full SIMULATED run reaches human review with no fabricated live labels.

### M5 — Review/governance/ADR/Blueprint

Implement review packet, approval binding, governance actions, deterministic ADR renderer and projection rebuild.

Gate: approval hash-binding and replay tests.

### M6 — Android core loop

Implement capture, interpretation, questions, framing, readiness, run monitor, review, governance, history and Blueprint.

Gate: end-to-end Android against simulated backend.

### M7 — Real provider

Add one real provider adapter only after mock/fixture conformance passes. Real execution must remain `PROVIDER` and must not fall back silently.

Gate: provider conformance + security + budget tests.

### M8 — Production hardening

Implement object storage, attachment safety, secrets, observability, FCM, backup/restore, signed release, update verification, rate limits and operational dashboards.

Gate: RC checklist.

### M9 — R2

Outcome observations, semantic diff/reconciliation, cross-decision graph/cycles, guided resolution, what-if, attachments/extraction and full-text search.

### M10 — R3

Policy-learning proposals, model evaluation/replay UI and optional Quick Settings/TTS features.

## 2. AI developer rules

The AI developer must:

- read `00_CANONICAL_MASTER_BLUEPRINT.md` first;
- then read Docs 01–04 before creating domain code;
- treat Docs 05–08 as machine-facing contracts;
- treat Docs 09–10 as client behavior/engineering contracts;
- treat Doc 11 as release/security contract;
- use Doc 12 as the acceptance oracle;
- use this document as the only implementation sequence.

The AI developer must not invent a new approval path, alternate source of truth, hidden fallback, provider secret path, local authority path or direct worker-to-governance mutation.

## 3. Repository conventions

Use stable IDs, explicit schema versions and domain terminology from Doc 04. Keep provider-specific DTOs behind adapters. Keep domain code independent of Ktor/Compose/LLM SDK details where practical.

## 4. Migration rule

Legacy v4/v4.1 data is imported as historical/legacy evidence where relevant. An old `APPROVED` flag cannot automatically become a v7 authoritative approval. Incomplete history becomes a legacy import requiring explicit validation.

## 5. Vertical slice target

The first “done enough to trust the architecture” slice is:

```text
Android capture
→ server decision
→ working draft
→ sealed revision
→ SIMULATED run
→ expert + adversarial + synthesis artifacts
→ quality/review packet
→ human approval
→ immutable ADR
→ Blueprint projection
→ history replay
```

## 6. Definition of Done

Implementation is not complete because screens or classes compile. It is complete only when:

- all applicable domain invariants pass;
- all authoritative mutations are server-owned;
- revision and packet bindings are deterministic;
- provider outputs are honestly labeled and validated;
- failures preserve legal state;
- history survives reinstall/login;
- projections rebuild;
- release artifacts are signed;
- the P0 acceptance suite passes.

## 7. Change-control rule

Any architectural change that affects authority, state transitions, revision semantics, event schema, approval binding, policy interpretation, persistence, security boundary or provider contract requires a new ADR and corresponding updates to affected v7 documents.

Do not silently “fix” one document while leaving contradictory contracts elsewhere.
