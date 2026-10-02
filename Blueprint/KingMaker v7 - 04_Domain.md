# 04 — Domain Model, State Machines & Rules

## 1. Core aggregates

```text
Workspace
Decision
DecisionRevision
Run
Approval
ADR
Blueprint
Outcome
PolicyVersion
```

Supporting entities include claims, evidence, questions, answers, options, criteria, trade-offs, dependencies, tasks, artifacts, validation plans/results, critique records, resolution tasks/proposals, audit events and idempotency records.

## 2. Canonical revision

A sealed revision contains the complete material decision state required to reproduce the analysis context:

- decision identity;
- frame;
- claims and epistemic types;
- evidence links and trust/freshness snapshot;
- options and criteria;
- dependencies;
- validation requirements;
- selected policy binding;
- revisit triggers;
- expected outcomes where applicable;
- schema version.

Canonical hash:

```text
revisionHash = SHA256(JCS(canonicalRevisionJson))
```

## 3. Draft lifecycle

```text
WORKING_DRAFT
   ↓ SealRevision
SEALED_REVISION
```

A sealed revision is immutable. Any material modification creates a new working draft and a new sealed revision.

## 4. Decision state machine

```text
RAW
 → INTERPRETATION
 → CONTEXT
 → FRAMING
 → READY_FOR_DEBATE
 → RUNNING
 → HUMAN_REVIEW
 ├→ APPROVED → ADR_PUBLISHED → BLUEPRINT_UPDATED → OUTCOME_TRACKING
 ├→ REJECTED
 ├→ DEFERRED
 └→ REVISION_REQUESTED → FRAMING
```

Archive/unarchive is an orthogonal non-governance flag.

## 5. Run state machine

```text
QUEUED → RUNNING ⇄ CHECKPOINTED → COMPLETED
RUNNING → CANCELLED | FAILED | DEAD_LETTER
```

Stages:

`PERSPECTIVES → CRITIQUE → SYNTHESIS → optional REFINEMENT/RESYNTHESIS → PACKET`.

Tasks:

`PENDING → LEASED → SUCCEEDED | FAILED_RETRYABLE → ... → FAILED_FINAL | QUARANTINED | CANCELLED`.

## 6. Governance binding

An APPROVE command must match:

```text
actor == authenticated owner
revisionId/hash == current
packetId/hash == current packet
 evidenceSnapshotHash == current evidence snapshot
policyVersionId/hash == bound policy
selectedOptionId ∈ sealed revision options
rationale length >= policy minimum
stepUp == configured acceptable factor
serverTime == governance timestamp
```

T3 additionally requires a pre-mortem or equivalent policy-required acceptance statement.

## 7. Typed claims

Canonical types:

`FACT`, `CONSTRAINT`, `PREFERENCE`, `INFERENCE`, `RECOMMENDATION`, `RISK`, `ASSUMPTION`, `UNKNOWN`, `UNVERIFIED`.

The domain does not allow a rendering layer or model confidence score to convert one epistemic type into another.

## 8. Evidence

Evidence source kinds:

`USER_NOTE`, `URL`, `ATTACHMENT`, `EXPERIMENT_RESULT`, `HUMAN_ASSERTION`, `AI_INFERENCE`.

Trust:

`UNREVIEWED`, `USER_ATTESTED`, `VERIFIED_SOURCE`, `DISPUTED`, `STALE`.

`AI_INFERENCE` cannot itself become verified source.

## 9. Events

Core event catalogue:

`DecisionCreated`, `DraftPatched`, `IntakeSubmitted`, `QuestionsGenerated`, `QuestionsAnswered`, `FramingConfirmed`, `ReadinessEvaluated`, `RunRequested`, `RunStarted`, `TaskLeased`, `TaskSucceeded`, `TaskFailed`, `ArtifactAccepted`, `ArtifactQuarantined`, `StageAdvanced`, `RunCheckpointed`, `RunCompleted`, `RunFailed`, `RunCancelled`, `QualityComputed`, `ReviewPacketBuilt`, `ReviewPacketStaled`, `ValidationResultRecorded`, `GovernanceActionRecorded`, `AdrPublished`, `BlueprintProjected`, `OutcomeReferenceCreated`, `OutcomeObserved`, `DivergenceClassified`, `EvidenceAdded`, `EvidenceTrustChanged`, `CycleDetected`, `ResolutionTaskCreated`, `ResolutionProposed`, `ResolutionSimulated`, `ResolutionConfirmed`, `ConflictDetected`, `ReconciliationConfirmed`, `PolicyVersionProposed`, `PolicyVersionActivated`, `DecisionArchived`.

## 10. Decision graph

Structural edges:

`DEPENDS_ON`, `CONSTRAINS`, `DERIVED_FROM`, `SUPERSEDES`.

Informational edges:

`SUPPORTS`, `CONTRADICTS`, `AFFECTS`.

Tarjan SCC runs on the structural subset. Accepted and proposed structural edges may be analyzed so that problematic dependencies are detected before approval.

Cycle identity:

```text
hash(JCS({graphHash, sortedNodeIds, sortedEdgeIds}))
```

No duplicate OPEN resolution task for the same cycle identity.

## 11. Guided resolution

AI may propose:

- break weakest candidate edge;
- restructure/split/reframe;
- human-defined operation.

Simulation is a pure operation over a copy. It returns affected nodes, readiness/approval consequences, cycle status and projected graph hash. Human confirmation must present and bind to the same projected graph hash; the domain re-simulates before committing.

## 12. What-if and semantic reconciliation

What-if computes without mutation:

- semantic diff;
- affected graph nodes/edges;
- cycle status;
- readiness result;
- stale packet/validation items;
- projected tier/policy effects.

Conflict classes:

`NON_CONFLICTING`, `FIELD_CONFLICT`, `DEPENDENCY_CONFLICT`, `EVIDENCE_CONFLICT`, `POLICY_CONFLICT`, `REVISION_STALE`.

A merged result is always a draft and needs explicit confirmation.

## 13. Outcomes

On approval, expected outcomes become `OutcomeReference` records. Later observations are immutable and classified as:

`NONE`, `EXPECTED_VARIANCE`, `EVIDENCE_GAP`, `OPERATIONAL_ISSUE`, `DECISION_INVALIDATION`.

Invalidation may open a superseding amendment. Original `quality_t0` remains unchanged.

## 14. Invariant enforcement

The server domain is the only place where authoritative transitions are produced. Database constraints/triggers supplement application checks for immutability and referential correctness. Every governed command is idempotent.
