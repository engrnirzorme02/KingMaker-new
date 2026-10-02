# KingMaker v7.0 — Reconciliation Audit

## Executive finding

The move to v7 is justified, but **not because v6.0 had a different core architecture**. The justified change is documentation and implementation maturity.

v6.0 already established the durable architecture: human authority → Decision Domain → deterministic policy → bounded execution → projections, with PostgreSQL/event history as authority and Android/Firebase/model/tool layers below it. The partially generated v6.1 pack adds implementation-grade specificity to that architecture. v7 consolidates that specificity into a standard 13-contract build pack.

## What the interrupted v6.1 pages contributed

| v6.1 seed | v7 treatment | Why it matters |
|---|---|---|
| RFC 8785/JCS revision hashing | Canonical | Eliminates ambiguous serialization during revision hashing |
| Mutable draft / sealed revision | Canonical | Clean boundary between editing and governance |
| Owner allowlist | Canonical for Personal Edition | Prevents accidental second-user access |
| PostgreSQL `SKIP LOCKED` queue | Personal deployment default | Removes unnecessary queue infrastructure while preserving a queue abstraction |
| Polling + FCM | Personal deployment default | Durable progress remains server truth; push is only a wake-up hint |
| Structured + semantic artifact validation | Canonical | Prevents malformed/poisoned AI output from entering governance |
| Explicit `SIMULATED` / `REPLAY` / `PROVIDER` | Canonical | Prevents provenance ambiguity |
| Deterministic ADR renderer | Canonical | Historical ADR is reproducible and not model-style-dependent |
| Approval packet hash binding | Canonical | Prevents approving a packet different from what was reviewed |
| Cost reservation | Canonical | Budget control happens before dispatch, not only after usage |
| CDR-P1 explicit 11-dimension registry | V7 consolidated | v6.1 declared “11” but did not contain the definitions; v7 makes the Personal registry explicit without pretending it is the legacy 14D list |
| Semantic diff/reconciliation | R2 canonical | Prevents silent overwrite during stale synchronization |
| Minimal prompt logging | Canonical | Auditability without turning private model reasoning into stored authority |
| Server/client time split | Canonical | Avoids client-clock manipulation of governance timing |
| Bilingual/Banglish handling | Personal UX contract | Reflects actual primary-user input modality |

## Gaps in v6.0 that v7 closes

### 1. Documentation granularity

v6.0 is intentionally comprehensive but monolithic. It is difficult for an implementation agent to consume one concern at a time without accidentally creating a second interpretation of the same rule.

**v7 fix:** 13 bounded documents, plus a manifest and reconciliation audit.

### 2. Contract-level precision

v6.0 names many contracts but does not freeze every field in one implementation surface.

**v7 fix:** Docs 05–08 freeze schema, API/event, AI artifact and policy-level contracts.

### 3. Verification becomes a first-class artifact

v6.0 specifies testing requirements; v7 turns them into fixtures, conformance suites, release gates and cross-document consistency checks.

**v7 fix:** Doc 12.

### 4. Build dependency ordering

v6.0 has an implementation roadmap, but the 13-page pack gives an implementation agent an explicit source order.

**v7 fix:** Doc 13 uses contract freeze → domain → persistence → API → mock execution → governance → Android integration → production hardening → real providers → R2/R3.

## What v7 deliberately does not change

- The Decision Domain remains authoritative.
- PostgreSQL remains authoritative.
- Android remains a client.
- Firebase remains identity/notification/optional projection infrastructure, not domain truth.
- Model providers remain replaceable.
- MCP remains scoped and proposal-oriented.
- A2A remains deferred.
- The Living Blueprint remains a projection.
- Quality remains diagnostic, not approval authority.
- Human approval remains explicit.
- Historical records remain immutable.

## Remaining historical distinction

The complete legacy v4 14D list is still not present in the current canonical source set. v7 therefore deliberately uses its own explicit Personal registry instead of inventing a historical reconstruction. This removes ambiguity from implementation while preserving the historical lineage.

## The important design conclusion

**The 13-page structure is the right standardization move.** It converts one large architecture artifact into a set of engineering contracts that can be independently reviewed, generated, tested and implemented while still being tied together by the master blueprint and traceability rules.

v7 should therefore be treated as the final **implementation documentation baseline**, while v6.0 remains preserved as the canonical architectural parent.
