# KingMaker v7.0 — Final Canonical Implementation Blueprint

**Status:** FINAL CANONICAL IMPLEMENTATION TARGET
**Architecture parent:** KingMaker v6.0 — Final Meta Blueprint
**Implementation seed:** KingMaker v6.1 — Personal Android Build Pack
**Edition:** Personal Android / single owner / single primary workspace
**Document role:** This document freezes the reconciled implementation contract. It does not claim that the software is already implemented.

## 0. Purpose and precedence

KingMaker v7 is a **documentation and implementation consolidation release**, not a replacement for the v6.0 architecture. It takes the v6.0 canonical architecture and converts it into a complete, implementation-grade package whose concerns are separated into explicit contracts.

Precedence:

```text
AUTHORIZED HUMAN
  ↓
KINGMAKER DOMAIN + GOVERNANCE
  ↓
v7 CANONICAL CONTRACTS
  ↓
v6.0 FINAL META ARCHITECTURE
  ↓
v6.1 PERSONAL IMPLEMENTATION PROFILE
  ↓
v5.2 / v5.1 / v5 historical evidence
```

Where an older document conflicts with v7, v7 governs implementation. Historical material remains useful as traceability evidence only.

## 1. The system in one sentence

KingMaker converts an incomplete technical decision into a bounded, evidence-aware, adversarially challenged, revision-safe and explicitly human-governed decision record, then preserves that record as an immutable historical artifact while projecting its current consequences into a Living Blueprint and later outcome loop.

The durable product is the **decision history and reasoning trace**, not the LLM response.

## 2. Constitutional authority boundary

```text
AUTHORIZED HUMAN
      ↓
DECISION DOMAIN
      ↓
DETERMINISTIC GOVERNANCE / POLICY
      ↓
EXECUTION PLANE
      ├─ expert roles
      ├─ adversarial critique
      ├─ synthesis
      ├─ evidence/research adapters
      ├─ model providers
      └─ scoped MCP/investigator tools
      ↓
PROJECTIONS
      ├─ Android read models
      ├─ ADRs
      ├─ Living Blueprint
      └─ outcome views
```

Rules:

- AI can interpret, propose, critique, simulate, investigate and summarize.
- AI cannot approve.
- Workers cannot mutate governance state directly.
- UI cannot approve or upgrade truth.
- Firebase cannot become domain authority.
- Local Room/DataStore state cannot become cloud authority.
- A model provider cannot be embedded into domain logic.
- A graph projection cannot become graph truth.
- A quality value cannot become approval authority.

## 3. Standard 13-document implementation pack

| Doc | Contract | Primary responsibility |
|---|---|---|
| 01 | Product Requirements | User value, scope, release boundaries, acceptance intent |
| 02 | ADR Log | Explicit implementation decisions and rationale |
| 03 | System Architecture | Runtime topology, modules, deployment and infrastructure |
| 04 | Domain | Aggregates, state machines, rules, invariants |
| 05 | Database Schema | PostgreSQL tables, constraints, indexes, immutability |
| 06 | API & Event Contract | Commands, queries, envelopes, event schemas, errors |
| 07 | AI Execution | Runs, tasks, model gateway, prompts, provenance, tools |
| 08 | Policy & Quality | Proportionality, quality vector, critique registry, governance eligibility |
| 09 | UX/UI | Screen/state contract, progressive disclosure, review and degraded states |
| 10 | Android Engineering | Compose architecture, local persistence, sync/outbox, build |
| 11 | Security/Ops/Cost | Threat controls, privacy, secrets, backups, release, budgets |
| 12 | Testing/Acceptance | Golden fixtures, conformance, state/invariant tests, release gates |
| 13 | Implementation Plan | Milestones, dependencies, AI developer brief and Definition of Done |

The package intentionally separates **what**, **why**, **how**, **domain truth**, **storage**, **interfaces**, **AI execution**, **governance policy**, **UX**, **client engineering**, **operations**, **verification**, and **delivery**. This is the principal structural upgrade over a single monolithic blueprint.

## 4. v7 decisions promoted from the interrupted v6.1 build pack

The following v6.1 implementation details are promoted into v7 because they make v6.0 materially more deterministic without changing its architecture:

1. **Revision hashing:** SHA-256 over RFC 8785 JSON Canonicalization Scheme (JCS) bytes.
2. **Working draft / sealed revision:** drafts are mutable; sealed revisions are immutable and hash-bound.
3. **Personal authorization:** Firebase Authentication plus server-side owner allowlist for the first deployment.
4. **PostgreSQL queue:** `FOR UPDATE SKIP LOCKED` job leasing for the personal deployment; queue remains replaceable.
5. **One image, two roles:** the same server artifact can run API or worker role.
6. **Polling + FCM:** progress uses durable server state and polling; FCM provides wake-up/notification hints, never authority.
7. **Structured artifact gate:** JSON/schema validation followed by semantic validation; invalid or suspicious output is quarantined.
8. **Strict provenance:** `SIMULATED`, `REPLAY`, and `PROVIDER` are explicit and immutable per run; real-provider execution never silently falls back to mock.
9. **Deterministic ADR renderer:** an ADR is rendered from approved authoritative data, not generated as free-form model prose.
10. **Approval packet binding:** approval binds actor, revision, packet, evidence snapshot, policy snapshot, selected option, rationale and step-up result.
11. **Cost reservation:** planner reserves estimated budget before dispatch; hard caps stop execution before overspend.
12. **Semantic conflict handling:** local/server conflicts are classified by materiality; merged output is always a draft requiring confirmation.
13. **Personal bilingual contract:** Bangla, English and mixed/Banglish input are accepted; AI output language is a user-controlled setting.
14. **No hidden reasoning storage:** prompt logging is minimized; private chain-of-thought is not an audit artifact.
15. **Server-authoritative time:** governance timestamps, deadlines and approval ordering use server time; client clock is display-only.

## 5. Final release model

### R1 — Core governed loop

Capture → interpretation → consequential questions → framing → readiness → bounded run → review → human governance → immutable ADR → Living Blueprint → history.

R1 includes text/voice/share capture, mock + one real provider, quality vector, core lenses, evidence notes/URLs, cost ledger, offline drafts/outbox, notifications, Markdown ADR export and history.

### R2 — Connected decisions

Outcome observations, semantic diff/reconciliation, cross-decision dependencies, Tarjan cycle detection, guided cycle resolution, what-if simulation, all five lenses, document attachments/extraction and full-text search.

### R3 — Controlled learning

Policy-learning proposals, model evaluation/replay view, optional Quick Settings/widget capture, optional TTS summary. Learning changes create new policy versions; history is never silently rewritten.

Beyond R3: multi-user workspaces, invited reviewers, A2A federation, autonomous execution, Firestore domain projections, floating overlays and unrestricted agent marketplace behavior.

## 6. Canonical decision lifecycle

```text
RAW_CAPTURE
   ↓
INTERPRETATION_PROVISIONAL
   ↓ user confirms/edits
CONTEXT
   ↓
FRAME_DRAFT
   ↓ confirm / seal
READY_FOR_DEBATE
   ↓
RUNNING / CHECKPOINTED
   ↓
HUMAN_REVIEW
   ├─ APPROVE → ADR_PUBLISHED → BLUEPRINT_UPDATED → OUTCOME_TRACKING
   ├─ REJECT
   ├─ DEFER
   └─ REQUEST_REVISION → new draft/revision
```

A run state never equals a decision state. Failed execution does not advance governance.

## 7. Typed epistemology

Canonical claim types:

`FACT`, `CONSTRAINT`, `PREFERENCE`, `INFERENCE`, `RECOMMENDATION`, `RISK`, `ASSUMPTION`, `UNKNOWN`, `UNVERIFIED`.

Rules:

- UNKNOWN is meaningful decision information.
- ASSUMPTION is not FACT.
- INFERENCE is not verified evidence.
- AI output cannot upgrade provenance.
- Rendering cannot upgrade claim status.

## 8. Evidence and provenance

Evidence is linked to claims and revisions. Supported source kinds include user note, URL, attachment, experiment result, human assertion and AI inference excerpt.

Trust states:

`UNREVIEWED`, `USER_ATTESTED`, `VERIFIED_SOURCE`, `DISPUTED`, `STALE`.

Only the owner can attest or verify. AI inference remains unreviewed. Freshness is computed from validity metadata; stale evidence is visible as stale, not silently trusted.

Every material review uses an evidence snapshot hash.

## 9. Quality and governance

Quality is multidimensional:

```text
evidenceStrength
frameCompleteness
constraintFit
optionCoverage
reversibility
riskExposure
disagreement
validationReadiness
complexityPenalty
```

The old v4 DQS equation and the old “14D” label remain legacy reference material. v7 does **not** claim to have reconstructed the missing legacy 14-dimension list. Instead, v7 explicitly defines a new **CDR-P1 Personal Edition registry with 11 dimensions** in Doc 08; this is a V7 consolidated design decision, not a historical claim.

Quality is diagnostic. Approval is a policy + eligibility + authenticated human action.

## 10. AI execution contract

Each task carries:

```text
runId
 taskId
 decisionId
 revisionId
 revisionHash
 taskClass
 role
 runtime
 model
 promptVersion
 policyVersion
 idempotencyKey
 deadline
toolScopes
redactionProfile
payload
```

Responses carry schema version, provenance mode, provider/model, latency, usage/cost estimate, redaction status, trace ID and structured artifact.

All structured outputs are validated before entering the domain. Failed validation never becomes accepted evidence or governance input.

## 11. Persistence architecture

### Authoritative

PostgreSQL stores current domain state, immutable governance events, sealed revisions, approvals, ADRs, policy versions, evidence metadata, runs/tasks, audit records and idempotency records.

### Large content

Encrypted object storage for attachments, large evidence, protected exports and archives. Objects are content-hash addressed.

### Client

Room/DataStore store encrypted drafts, cached projections and an encrypted command outbox. Local approval is impossible.

### Identity

Firebase Authentication + Google Sign-In in the first profile. The server converts identity into authorization through the owner allowlist. Firestore is not used as v7 domain authority.

## 12. Security contract

Threats include prompt injection, malicious files, tool poisoning, SSRF, provider compromise, cross-workspace access, replay, stale approval, device theft, log leakage and malicious update packages.

Required controls:

- authenticated sessions;
- object-level authorization;
- managed provider secrets;
- redaction before external calls;
- scoped tools;
- URL allowlists/validation;
- attachment scanning where applicable;
- idempotency;
- revision concurrency;
- signed releases;
- verified updates;
- no provider secrets in APK;
- immutable governance records;
- deletion/export workflows;
- auditability without storing hidden chain-of-thought.

## 13. Graph / Living Blueprint

Nodes include ADRs, declared system elements and open decisions. Relations use typed edges such as `DEPENDS_ON`, `CONSTRAINS`, `SUPPORTS`, `SUPERSEDES`, `CONTRADICTS`, `DERIVED_FROM`, `AFFECTS`.

Tarjan SCC is run on the structural subset. Cycles produce deduplicated resolution tasks. AI may propose break/restructure/human-defined operations; simulation is pure; confirmation is human-governed; accepted structural changes create the necessary new revisions rather than mutating sealed history.

The Living Blueprint is rebuildable. It is never the source of truth.

## 14. Outcome loop and policy learning

Approved decisions may declare expected outcomes. Later observations classify divergence without rewriting decision-time quality.

A `DECISION_INVALIDATION` outcome can offer a superseding revision. Outcome patterns may produce a `PolicyVersion` proposal; only the human owner may activate it. Historical runs retain their original policy binding.

## 15. Core invariants

```text
I-01  AI never approves.
I-02  UI never approves by itself.
I-03  Worker/tool identities cannot mutate governance state directly.
I-04  Only the Decision Domain writes authoritative state.
I-05  Every sealed revision has a canonical SHA-256(JCS) hash.
I-06  Governed commands are idempotent and correlation-traced.
I-07  Revision-bound artifacts reference exactly one sealed revision.
I-08  Stale revision writes fail closed.
I-09  Material changes create new revisions.
I-10  Approvals and ADRs are immutable.
I-11  Blueprint is a rebuildable projection.
I-12  Claim/provenance status cannot be upgraded by rendering.
I-13  Invalid/unprovenanced model output is quarantined.
I-14  Simulated output is never labeled live.
I-15  No provider secret is shipped in the client.
I-16  External tools have explicit scopes.
I-17  Cycle resolution never silently mutates graph truth.
I-18  Cycle resolution requires explicit human confirmation.
I-19  Quality never equals automatic approval.
I-20  Outcome data never rewrites historical quality.
I-21  Offline state never masquerades as authoritative state.
I-22  Release/update artifacts are verified.
I-23  Failed execution never advances governance.
I-24  Lens changes never alter decision truth.
I-25  Policy learning creates new versions, never silent retroactive mutation.
I-26  Local outbox excludes seal/run/governance authority commands.
I-27  Real-provider runs never silently fall back to mock.
I-28  Approval uses server time and current authoritative snapshots.
I-29  A semantic merge is always a draft until explicitly confirmed.
I-30  Every canonical contract is schema-versioned and backward-compatibility tested.
```

## 16. Definition of done

KingMaker v7 is architecturally complete when the 13 documents agree on identifiers, states, revision binding, envelopes, error codes, policy versions, provenance modes and release milestones; and when the production vertical slice passes the P0 gates in Doc 12.

Architecture completeness does not mean the APK already exists. It means an implementation team or AI developer can build against a stable, auditable contract without inventing authority semantics locally.
