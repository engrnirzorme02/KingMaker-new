# 02 — Architecture Decision Record Log

These decisions are normative for the Personal Edition unless superseded by a new versioned ADR before implementation.

## ADR-V7-001 — v6.0 remains architectural parent
**Decision:** v7 is an implementation/documentation consolidation of v6.0, not a competing architecture.

## ADR-V7-002 — Thirteen bounded implementation contracts
**Decision:** Product, ADR, Architecture, Domain, Database, API/Event, AI, Policy/Quality, UX, Android, Security/Ops/Cost, Testing and Implementation Plan are separate normative documents.

## ADR-V7-003 — Single-owner Personal Edition
**Decision:** one authorized owner in v1; schema keeps `workspace_id` and membership concepts for future expansion.

## ADR-V7-004 — Native Kotlin/Compose
**Decision:** Android client uses Kotlin, Jetpack Compose, one Activity, unidirectional data flow and StateFlow-backed feature state.

## ADR-V7-005 — Kotlin server + shared contracts
**Decision:** server uses Kotlin/Ktor; shared serializable DTO/enums/schema definitions live in `:contracts` where practical.

## ADR-V7-006 — PostgreSQL is authority
**Decision:** authoritative domain state, immutable events, sealed revisions, approvals and ADRs are stored in PostgreSQL.

## ADR-V7-007 — Event log + current state, not full event sourcing
**Decision:** authoritative mutation appends an immutable event and updates current state atomically. Full event sourcing is deferred.

## ADR-V7-008 — PostgreSQL job queue in Personal Edition
**Decision:** workers lease jobs using transactional row locking / `FOR UPDATE SKIP LOCKED`. The application exposes a queue abstraction so the deployment can later swap to another durable queue.

## ADR-V7-009 — One image, two runtime roles
**Decision:** one server image supports `api` and `worker` roles to minimize personal deployment complexity.

## ADR-V7-010 — Reference deployment profile
**Decision:** Google Cloud Run + Neon PostgreSQL + Google Cloud Storage + Secret Manager is a supported reference deployment, not a constitutional dependency. Equivalent providers may be substituted behind interfaces.

## ADR-V7-011 — Firebase Auth + owner allowlist
**Decision:** Firebase Authentication/Google Sign-In provides identity. Server authorization uses an owner allowlist. Firestore is not domain authority.

## ADR-V7-012 — Polling + FCM
**Decision:** authoritative progress lives in server state. Client polling obtains state; FCM sends ID-only wake-up hints/notifications.

## ADR-V7-013 — RFC 8785 JCS + SHA-256
**Decision:** sealed revision hash is `SHA-256(JCS(canonical_revision_json))`. Hashing is independent of UI or language runtime object ordering.

## ADR-V7-014 — Mutable draft / immutable revision
**Decision:** working drafts are editable; sealing creates a revision snapshot that cannot be changed.

## ADR-V7-015 — Provider-agnostic Model Gateway
**Decision:** the domain depends on capabilities, not provider SDKs. Providers are adapters.

## ADR-V7-016 — Provenance modes are explicit
**Decision:** `SIMULATED`, `REPLAY`, `PROVIDER` are first-class run provenance modes. No silent fallback from provider to mock.

## ADR-V7-017 — Structured artifact gate
**Decision:** provider output passes schema validation and semantic validation before acceptance. Failure leads to retry/quarantine, never silent coercion into truth.

## ADR-V7-018 — Deterministic ADR renderer
**Decision:** ADR content is deterministically rendered from approved structured authority records and never authored as unconstrained AI prose.

## ADR-V7-019 — Approval binding
**Decision:** approval must bind actor, revision hash, packet hash, evidence snapshot hash, policy hash, selected option, rationale, server timestamp and step-up state.

## ADR-V7-020 — Cost reservation
**Decision:** estimated run cost is reserved before work dispatch; per-run and monthly caps are enforced transactionally.

## ADR-V7-021 — Minimal prompt logging
**Decision:** store prompt/version metadata required for reproducibility and audit, but do not store hidden chain-of-thought.

## ADR-V7-022 — Semantic reconciliation
**Decision:** stale local edits are classified structurally; the result is a proposed draft. No silent overwrite or timestamp-based evidence conflict resolution.

## ADR-V7-023 — Server-authoritative time
**Decision:** governance ordering and deadlines use server time. Client time is informational only.

## ADR-V7-024 — No in-app arbitrary OTA
**Decision:** Android distribution relies on trusted signed release channels. The app does not implement arbitrary package download/install behavior.

## ADR-V7-025 — CDR-P1 Personal critique registry
**Decision:** v7 freezes a new Personal Edition registry `CDR-P1` with 11 explicit dimensions defined in Doc 08. This registry is derived from the v5/v6 role set and decision goals; it is not claimed to be the missing legacy v4 14D list. Legacy 14D remains historical reference unless its complete list is independently verified and imported as a separate registry version.
