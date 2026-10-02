# KingMaker v6.0 — Final Meta Blueprint
## Canonical Product, Decision-Intelligence, Governance, Execution, Persistence & Implementation Contract

**Status:** FINAL CANONICAL TARGET BASELINE  
**Supersedes for implementation:** v5.2 as the implementation authority  
**Historical input retained:** the complete v5.2 blueprint is preserved after this canonical layer for traceability.  
**Scope:** Product + user journey + decision reasoning + authoritative domain + AI execution + model gateway + persistence/history + Firebase identity/continuity + Android UX + security + reliability + testing + migration + delivery.

> **Canonical rule:** When this meta layer and the preserved v5.2 baseline differ, this v6.0 meta layer governs implementation. Historical claims, formulas, examples, and open-decision lists in the v5.2 appendix remain evidence only unless explicitly adopted here.

---

# 0. The final answer: what KingMaker actually is

KingMaker is a **persistent, human-governed Decision Intelligence Workspace** for technical and architectural decisions.

It takes:

```text
incomplete human idea
        ↓
decision framing
        ↓
minimal-sufficient context
        ↓
evidence + assumptions
        ↓
relevant expert perspectives
        ↓
adversarial challenge
        ↓
bounded synthesis
        ↓
validation
        ↓
human approval / rejection / defer / revision
        ↓
immutable ADR
        ↓
Living Blueprint projection
        ↓
real-world outcome observation
        ↓
future policy learning
```

It is **not** primarily:

- a chatbot;
- an AI coder;
- a fixed multi-agent swarm;
- a score calculator;
- a graph toy;
- an offline AI runtime;
- an autonomous approval/deployment system.

The durable product is the **decision history and reasoning trace**, not the model response.

---

# 1. Final architecture lock

## 1.1 Authority

The final authority stack is:

```text
AUTHORIZED HUMAN
      ↓
DECISION DOMAIN
      ↓
GOVERNANCE / POLICY ENGINE
      ↓
EXECUTION PLANE
      ↓
INTEGRATIONS / MODELS / TOOLS

ANDROID UI / LOCAL CACHE / FIREBASE PROJECTIONS
        = CLIENT / PROJECTION, NEVER AUTHORITY
```

The same rule in plain language:

- AI may propose.
- AI may critique.
- AI may investigate.
- AI may summarize.
- AI may simulate.
- AI may never approve or directly mutate authoritative decision state.

Only the Decision Domain may create authoritative revisions, accept evidence, commit graph changes, record approvals, publish ADRs, or change governance state.

## 1.2 Runtime topology

```text
┌───────────────────────────────────────────────────────────────┐
│                       ANDROID CLIENT                          │
│ Kotlin + Jetpack Compose                                     │
│ Inbox • Intake • Context • Framing • Run • Review • History  │
│ Draft cache • encrypted outbox • read projections             │
└──────────────────────────────┬────────────────────────────────┘
                               │ HTTPS + authenticated session
                               ▼
┌───────────────────────────────────────────────────────────────┐
│                     API / COMMAND BOUNDARY                    │
│ auth • object authorization • idempotency • revision checks  │
└──────────────────────────────┬────────────────────────────────┘
                               ▼
┌───────────────────────────────────────────────────────────────┐
│                   DECISION DOMAIN / POLICY                    │
│ Decision • Revision • Evidence • Graph • State • Approval   │
│ ADR • Projection • Outcomes • Governance Events               │
└───────────────┬───────────────────────┬───────────────────────┘
                │                       │
                ▼                       ▼
      ┌───────────────────┐    ┌───────────────────────────────┐
      │ AUTHORITATIVE DB  │    │ DURABLE EXECUTION QUEUE       │
      │ PostgreSQL        │    └──────────────┬────────────────┘
      └───────────────────┘                   ▼
                                    ┌───────────────────────────┐
                                    │ WORKERS / EXECUTION PLANE │
                                    │ experts • red team        │
                                    │ synthesis • research      │
                                    │ provider adapters         │
                                    │ MCP read/propose          │
                                    └──────────────┬────────────┘
                                                   ▼
                                      validated, revision-bound
                                         structured artifacts
                                                   │
                                                   ▼
                                      Decision Domain re-validates
                                                   │
                                                   ▼
                           ┌───────────────────────┴──────────────┐
                           │ projections / ADR / blueprint / UX  │
                           └──────────────────────────────────────┘
```

## 1.3 Persistence authority

**Primary authority:** PostgreSQL + immutable governance event history + current read projections.

**Object storage:** encrypted evidence blobs, attachments, exports, archives.

**Queue:** delivery and work scheduling only.

**Redis/equivalent:** ephemeral cache/rate limit/coordination only.

**Firebase:** identity and optional user-facing projections/notifications; never revision, approval, policy, ADR, or graph authority.

**Android Room/DataStore:** local draft/cache/outbox only.

---

# 2. Final implementation decisions

These are now treated as the default implementation contract rather than unresolved architecture questions.

| Area | Final decision |
|---|---|
| Mobile client | Native Kotlin + Jetpack Compose |
| App structure | Single Activity + feature-level state holders + UDF/StateFlow |
| Product posture | Online-first; graceful degraded-network behavior |
| Backend | Authoritative API + domain + separate worker process |
| Deployment shape | Modular monolith + worker first; microservices deferred |
| Authority database | PostgreSQL |
| Large content | Encrypted object storage |
| Work scheduling | Durable queue |
| Identity | Firebase Authentication in the first deployment profile, behind an auth abstraction |
| Model credentials | Backend/secret-store only in v1 |
| Model integration | Provider/model agnostic Model Gateway |
| Agent runtime | Structured adapter interface; no model/runtime becomes domain authority |
| AI output | Schema-validated structured artifacts |
| Evidence | Typed, revision-linked, provenance-aware |
| Quality | Explainable quality vector, never automatic approval |
| Weighting | Contextual, policy-versioned, deterministic |
| Human approval | Explicit, authenticated, revision-bound |
| History | Server-persistent; restored after reinstall/login |
| Offline | Drafts/cache/outbox only; no offline authority |
| Graph cycle handling | Tarjan SCC + guided human resolution |
| Cycle “what-if” | Simulation/preview only until human confirmation |
| Sync conflicts | Semantic diff + governed reconciliation |
| Outcome learning | Retrospective policy proposals create new policy versions; never mutate historical decisions |
| ADR | Immutable |
| Living Blueprint | Rebuildable projection |
| Lenses | Executive, Architecture, UX, Developer, Governance |
| Real provider enablement | Only after mock/replay/conformance/security gates |
| A2A / autonomous execution | Deferred |

### Important clarification

Firebase is **not** required to hold the entire decision history. The server is already the durable source of truth. Firebase Authentication is enough to satisfy the identity/continuity requirement. Firestore may be used as a projection adapter only where it is genuinely useful and formally secured.

---

# 3. Canonical product contract

Every user interaction must result in at least one of these effects:

```text
A. advance a decision artifact
B. improve context/evidence
C. reveal uncertainty/disagreement/risk
D. request or perform validation
E. record an explicit human governance action
F. record a later outcome observation
```

A response that only “sounds helpful” but does not advance, validate, explain, or preserve a decision artifact is not a core KingMaker capability.

---

# 4. Canonical user journey

## Step 1 — Capture

User can write a messy problem in natural language.

KingMaker preserves the raw text exactly.

Then it derives a **provisional** interpretation:

```text
raw statement
→ possible decision question
→ detected facts
→ constraints
→ preferences
→ assumptions
→ unknowns
→ candidate domains
→ preliminary impact/reversibility
```

The user can edit/confirm before the system treats the interpretation as accepted decision context.

## Step 2 — Ask only consequential questions

Questions are generated from decision impact, not from a generic questionnaire.

A candidate question should be evaluated against:

```text
unknown addressed
+ affected criteria/options
+ ability to change recommendation
+ uncertainty reduction
+ answerability
+ user effort
```

The user can always answer:

- Answer;
- I don't know;
- Not applicable;
- Defer.

“I don't know” remains an explicit state.

## Step 3 — Frame the decision

The user reviews:

```text
Objective
Non-goals
Stakeholders
Hard constraints
Preferences
Success criteria
Evaluation dimensions
Candidate options
Rejected alternatives
Dependencies
Validation budget
Revisit triggers
```

The frame must be understandable before deep reasoning begins.

## Step 4 — Readiness gate

Deterministic checks decide whether the system is ready for the next stage.

Required checks include:

```text
identity valid
current revision valid
framing confirmed
required questions handled
criteria present
options present or policy exception recorded
impact/reversibility classified
graph integrity valid
required evidence preconditions satisfied
run budget legal
```

No silent “continue anyway” path exists for blocking failures.

## Step 5 — Contextual specialist routing

The system selects only the roles relevant to the decision.

Role selection considers:

- domain;
- criteria;
- risk;
- reversibility;
- evidence gaps;
- operational consequences;
- affected stakeholders.

Each requested role has a visible mandate:

> “Why was this perspective requested?”

## Step 6 — Independent perspectives

Roles reason from the bounded revision context.

The system extracts:

```text
agreements
disagreements
unique evidence
contradictory claims
unsupported confidence
criteria with divergent views
```

Consensus is not a quality metric by itself.

## Step 7 — Red Team

The Devil’s Advocate attacks the strongest credible leading option.

Every critique has:

```text
target
challenge
evidence/evidence gap
severity
validation/test
resolution state
```

The Coverage Auditor is policy-selectable and checks for missing domains, hidden dependencies and unsupported assumptions.

## Step 8 — Synthesis

The synthesis must answer:

1. What are the realistic options?
2. Which criteria actually decide between them?
3. What is the strongest recommendation and why?
4. What is the strongest alternative and why?
5. What could still make the recommendation wrong?

The output is a structured review packet, not an opaque final answer.

## Step 9 — What-if validation

Before making any graph-changing or structural proposal real, the system can simulate:

```text
candidate change
→ affected nodes
→ dependency consequences
→ risk changes
→ criterion changes
→ validation requirements
→ projected graph hash
→ projected quality/policy effects
```

The simulation never mutates authoritative state.

## Step 10 — Human review

The review packet is revision-bound.

The human sees:

- recommendation;
- strongest alternative;
- decisive criteria;
- quality vector;
- evidence map;
- dissent;
- unresolved risks;
- validation result;
- consequences;
- revisit triggers;
- execution provenance.

The only governance actions are explicit:

```text
APPROVE
REJECT
DEFER
REQUEST_REVISION
```

## Step 11 — Approval

Approval is accepted only if all binding conditions still hold:

```text
authorized actor
+ current revision
+ current revision hash
+ current evidence snapshot
+ current policy snapshot
+ current review packet
+ explicit rationale
+ policy eligibility
```

Any mismatch produces a stale/invalid approval error.

## Step 12 — ADR + Living Blueprint

Approval creates an immutable ADR.

The Living Blueprint is rebuilt/projection-updated from accepted authoritative history.

Historical ADRs are never edited.

## Step 13 — Outcome loop

Later:

```text
expected outcome
→ observed outcome
→ divergence classification
→ possible new validation
→ possible new decision revision
```

The original decision-time quality is never rewritten.

## Step 14 — Policy learning

Outcome patterns may be analyzed to propose changes to future:

- policy thresholds;
- role-routing rules;
- evidence requirements;
- validation depth;
- model routing.

Such a change becomes a **new PolicyVersion**.

Historical decisions remain bound to their original policy.

---

# 5. Canonical data model

Core entities:

```text
Workspace
WorkspaceMember
WorkspacePolicy
Decision
DecisionRevision
Claim
EvidenceItem
ClaimEvidenceLink
ContextQuestion
ContextAnswer
DecisionOption
EvaluationCriterion
TradeOff
DecisionDependency
DebateRun
DebateTask
JobAttempt
AgentPerspective
Critique
SynthesisResult
QualityResult
ValidationPlan
ValidationResult
Approval
ADR
BlueprintNode
BlueprintEdge
ResolutionTask
ResolutionProposal
OutcomeReference
OutcomeObservation
PolicyVersion
CritiqueDimensionRegistry
PromptVersion
ModelRecord
IntegrationConnection
Artifact
Attachment
AuditEvent
IdempotencyRecord
CommandOutbox
```

## 5.1 Immutable vs mutable

**Immutable:**

- revisions;
- approvals;
- ADRs;
- governance events;
- evidence snapshots;
- executed review packets;
- provider execution provenance;
- outcome observations.

**Mutable projections:**

- current decision view;
- inbox cards;
- blueprint projection;
- lens projections;
- dashboards;
- caches.

## 5.2 Material-change rule

Any change capable of altering:

- recommendation;
- evidence interpretation;
- criteria;
- options;
- validation requirements;
- approval conditions;
- dependency semantics;
- material policy application

creates a new revision or a separately governed new run, never a silent in-place mutation.

---

# 6. Typed epistemology

Canonical claim types:

```text
FACT
CONSTRAINT
PREFERENCE
INFERENCE
RECOMMENDATION
RISK
ASSUMPTION
UNKNOWN
UNVERIFIED
```

Rules:

- `UNKNOWN` is not failure.
- `ASSUMPTION` is not fact.
- `INFERENCE` is not evidence.
- model confidence does not create truth.
- UI cannot promote claim status.
- evidence provenance is stored with the claim linkage.
- unsupported material claims remain visibly unsupported.

---

# 7. Evidence and provenance

Each material evidence item records at minimum:

```text
sourceKind
sourceLocator
capturedAt
contentHash
freshness
trust/provenance state
redaction state
access scope
metadata
```

Evidence can originate from:

- user-provided material;
- verified external sources;
- accepted experiment results;
- explicitly accepted human assertions under policy;
- bounded inferences linked to supporting claims.

### Quarantine rule

Any AI/tool output lacking:

- valid schema;
- revision linkage;
- execution provenance;
- required evidence metadata;

is quarantined and cannot affect approval or ADR generation.

---

# 8. Policy and proportionality

The Proportionality Engine chooses how much reasoning and validation a decision deserves.

The old v4 weighted equation is retained only for legacy compatibility:

```text
Risk × 0.35
+ Impact × 0.30
+ (1 - Changeability) × 0.20
+ Budget × 0.15
```

It is **not** the final universal production law.

The canonical production policy is:

```text
PolicyVersion
    ↓
context classification
    ↓
impact / reversibility
    ↓
required evidence
    ↓
required roles
    ↓
run depth
    ↓
quality requirements
    ↓
validation requirements
    ↓
approval eligibility
```

Adaptive behavior means **policy selection**, not self-changing weights.

AI may recommend a policy profile, but only a deterministic policy engine applies one.

---

# 9. Quality model

The quality vector is:

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

Every component stores:

- normalized value;
- explanation;
- calculation inputs;
- policy version;
- evidence links where relevant.

A composite summary may exist as a heuristic view.

It can never mean:

```text
quality = automatic approval
```

Approval is governed by policy conditions and human authorization, not by a number.

---

# 10. Model Gateway

The domain never imports a specific LLM SDK.

The execution plane uses:

```text
ModelGateway
   ├─ provider adapter
   ├─ capability registry
   ├─ model registry
   ├─ routing policy
   ├─ cost/quota checks
   ├─ timeout/retry policy
   └─ provenance recorder
```

Each model record contains:

```text
provider
modelId
capabilities
structuredOutputSupport
toolSupport
contextCapacity
latencyClass
cost/quota metadata
availability
retention/safety characteristics
adapterVersion
```

### Task-aware routing

Examples:

```text
context extraction       → efficient structured model
expert reasoning         → strong reasoning model
red team                 → independent strong reasoning model
synthesis                → strongest eligible synthesis model
evidence classification  → efficient/specialized model
```

These are capability classes, not permanent vendors.

### Model quality gate

Before a provider/model is promoted to production:

```text
schema conformance
→ golden fixture evaluation
→ refusal/injection tests
→ provenance correctness
→ latency/cost measurement
→ regression comparison
→ policy approval
```

A stronger model can be introduced without changing the Decision Domain.

---

# 11. Bounded execution

Every run has:

```text
maxRounds
maxTasks
deadline
costBudget
cancellationPolicy
retryPolicy
terminalReason
```

Stop conditions:

```text
coverage satisfied
budget exhausted
deadline
task limit
non-improvement
provider/tool capability failure
governance blocker
insufficient evidence
```

The engine does not optimize for maximum token usage.

The engine optimizes for **decision sufficiency under policy constraints**.

---

# 12. Decision graph and Guided Cycle Resolution

Accepted dependency semantics use typed edges:

```text
DEPENDS_ON
CONSTRAINS
SUPPORTS
SUPERSEDES
CONTRADICTS
DERIVED_FROM
AFFECTS
```

Tarjan SCC detects illegal/undesired cycles.

A cycle creates a deduplicated `ResolutionTask` based on:

```text
revisionHash
sorted node IDs
sorted cycle-edge IDs
```

The engine may produce proposals such as:

```text
A. remove/change lowest-supported dependency
B. restructure/reframe relationship
C. human-defined override
```

But the actual flow is:

```text
detect
→ propose
→ simulate
→ show impact
→ human selects
→ domain validates
→ human confirms
→ immutable resolution event
→ projection update
```

No cycle is silently deleted.

---

# 13. Semantic Diff and Conflict Reconciliation

This capability is mandatory for robust multi-device/degraded-network operation even though KingMaker is online-first.

When local edits meet newer server state:

```text
local revision
        +
server revision
        ↓
semantic diff
```

Classify changes:

```text
NON_CONFLICTING
FIELD_CONFLICT
DEPENDENCY_CONFLICT
EVIDENCE_CONFLICT
POLICY_CONFLICT
REVISION_STALE
```

### Merge rule

- Non-conflicting changes may be assembled into a **proposed merged revision**.
- Same-field conflicts remain visible.
- Evidence conflicts are never auto-resolved merely by timestamp.
- Policy conflicts require re-evaluation under the current policy.
- Governance conflicts never auto-merge.
- User must explicitly confirm the resulting revision.

The system must never silently overwrite server state.

---

# 14. Firebase + persistent history

The continuity contract is:

```text
uninstall
→ reinstall
→ Firebase Auth sign-in
→ backend identity resolution
→ workspace membership resolution
→ server decision index
→ revision history
→ run history
→ review history
→ ADR history
→ blueprint reconstruction
```

The account is the identity handle; the backend is the historical authority.

The same account on a new device restores the durable history.

---

# 15. Android architecture

Final client:

```text
Kotlin
Jetpack Compose
Single Activity
Navigation
Feature ViewModels / state holders
Use-case layer
Typed network client
Room/DataStore local cache
Encrypted command outbox
```

Suggested features:

```text
Inbox
NewDecision
Intake
Context
Framing
Run
Review
History
Blueprint
Outcome
Settings
```

The Android app may perform local responsiveness work:

- list transforms;
- graph visualization preparation;
- local hashing;
- draft caching;
- export preparation.

It may not become a second source of truth.

### Required UI states

Every important screen supports:

```text
empty
loading
partial
offline-draft
syncing
stale-conflict
permission-denied
validation-error
server-error
cancelled
failed-checkpoint
success
accessibility fallback
```

Target usability:

- narrow phone widths, approximately 375px and above;
- touch targets around 44dp or larger;
- no critical workflow requiring horizontal scrolling;
- text/list alternative for the graph;
- accessible approval rationale;
- explicit stale/simulated/live labels.

---

# 16. Five Lenses

The same immutable decision is projected as:

```text
Executive
Architecture
UX
Developer
Governance
```

Changing lens never changes:

- quality;
- evidence;
- recommendation;
- approval result;
- policy eligibility.

Progressive disclosure:

```text
DEFAULT
recommendation
strongest alternative
top critical findings
quality summary
critical risks
validation state
next action

EXPANDED
all criteria
all dissent
all perspectives
evidence map
critique registry
run history
policy inputs
audit history
```

Collapsed content is hidden, not deleted.

---

# 17. Security boundary

Threats explicitly covered:

```text
prompt injection
malicious attachments
tool poisoning
SSRF
provider compromise
cross-workspace access
replayed commands
stale approvals
device theft
log leakage
malicious update packages
```

Controls:

- authenticated sessions;
- workspace + object-level authorization;
- managed secrets;
- redaction before external calls;
- scoped tools;
- allowlisted/validated external URLs;
- attachment scanning as appropriate;
- idempotency;
- revision concurrency;
- signed releases;
- update verification;
- no provider secrets in the APK.

Authentication is an identity mechanism, not a governance decision.

---

# 18. Event architecture

Every authoritative event includes:

```text
eventId
eventType
aggregateType
aggregateId
sequence
workspaceId
revisionId
revisionHash
actor
policyVersion
correlationId
timestamp
schemaVersion
payloadHash
payload
```

Command flow:

```text
authenticate
→ authorize
→ idempotency check
→ revision check
→ policy check
→ domain command
→ append event + current projection update atomically
→ commit
```

Projection replay must reproduce equivalent authoritative views.

This is a release gate.

---

# 19. API contract

Base path:

```text
/api/v1
```

Mutation requests include:

```text
Authorization
Idempotency-Key
X-Correlation-Id
revisionId / revisionHash where applicable
```

Core commands:

```text
POST /workspaces
POST /workspaces/{workspaceId}/decisions
POST /decisions/{decisionId}/intake/submit
POST /decisions/{decisionId}/context/questions/generate
POST /decisions/{decisionId}/context/answers
POST /decisions/{decisionId}/framing/confirm
POST /decisions/{decisionId}/runs
POST /runs/{runId}/cancel
POST /decisions/{decisionId}/validations
POST /decisions/{decisionId}/approval
POST /decisions/{decisionId}/revisions
POST /decisions/{decisionId}/outcomes/observations

GET  /decisions/{decisionId}/graph/cycles
GET  /decisions/{decisionId}/resolution-tasks
POST /decisions/{decisionId}/resolution-tasks/{taskId}/proposals
POST /decisions/{decisionId}/resolution-tasks/{taskId}/resolve
```

Error classes:

```text
403 FORBIDDEN
409 STALE_REVISION
409 IDEMPOTENCY_CONFLICT
422 POLICY_FAILURE
422 VALIDATION_ERROR
5xx typed execution/service failures
```

---

# 20. Failure and recovery contract

A failure must always tell the user:

```text
what failed
what artifact is affected
whether the revision is still valid
whether retry is possible
whether user action is required
what must not be changed
```

Examples:

### Provider failure

```text
task attempt failed
→ retry if policy permits
→ otherwise dead-letter / run failed
→ no governance progression
```

### Stale approval

```text
approval rejected
→ preserve review packet
→ refresh current revision
→ show changed fields
→ require new review/approval
```

### Network loss

```text
cache stays visible as stale/last-known
draft remains editable
eligible commands may enter outbox
approval remains unconfirmed
```

### Restore failure

```text
restore to staging
→ hash/reference/policy validation
→ commit only complete snapshot
→ discard partial restore
```

---

# 21. Outcome learning without resulting fallacy

This is a hard canonical rule.

At decision time:

```text
quality_t0
```

is stored and never recalculated from the later outcome.

Later:

```text
outcome_t1
```

is classified.

Only a separate retrospective process may say:

```text
“Future policy could be improved because this class of decisions repeatedly shows X.”
```

That becomes:

```text
PolicyVersion N
→ proposal
→ human governance review
→ PolicyVersion N+1
```

The historical decision remains bound to `PolicyVersion N`.

---

# 22. Role registry

The runtime starts with a registry, not a fixed swarm.

Initial supported role catalogue:

```text
Software Architect
Data Architect
Security Architect
Reliability / Operations
UX / Human Factors
Migration Specialist
Developer / Implementation Reviewer
Compliance / Governance Specialist
Business / Product Analyst
Devil's Advocate
Coverage Auditor
```

Policy decides which of these are actually invoked.

Each role specifies:

```text
mandate
inputs
context scope
expected schema
allowed tools
evidence expectations
task budget
critique responsibilities
```

---

# 23. Critique Dimension Registry

Do not invent a missing legacy 14-dimension list.

Canonical approach:

```text
CritiqueDimensionRegistry
  registryId
  version
  dimensions[]
  severityRules
  requiredRoles
  requiredEvidence
  explanationRules
```

Until a verified legacy list is imported and locked, the UI must not falsely claim a canonical “14D” implementation.

---

# 24. Data lifecycle and privacy

Every data object has:

```text
owner/workspace
classification
retention rule
access scope
provenance
createdAt
updatedAt or immutable marker
```

Deletion must account for:

- active state;
- projections;
- attachments;
- caches;
- outbox;
- backups;
- exports.

A deletion request must be explicit about what is immediately deleted, what is tombstoned for consistency, and what is retained under documented backup/retention rules.

---

# 25. Export contract

Exports are projections and must be labeled as such.

Possible exports:

```text
ADR
Decision Summary
Living Blueprint
Evidence Package
Audit Timeline
Outcome Review
```

An export must preserve:

- revision identity;
- revision hash;
- provenance mode;
- policy version;
- evidence status;
- approval status;
- generated timestamp.

An export is not allowed to upgrade `UNVERIFIED`, `ASSUMPTION`, or simulated content into authoritative facts.

---

# 26. Observability and cost governance

Telemetry should capture:

```text
requestId
correlationId
traceId
workspace
run/task
queue delay
duration
provider/model
prompt version
policy version
schema version
retry class
redaction status
cost/usage estimate
terminal reason
```

Do not log by default:

- raw prompts;
- raw attachments;
- provider secrets;
- private approval rationale;
- sensitive evidence payloads.

Cost governance is part of decision reasoning.

The engine may stop, downgrade, retry, or ask for human input when the run budget is exhausted.

---

# 27. Testing architecture

## Domain/property tests

- legal/illegal transitions;
- revision hashing;
- stale writes;
- idempotency;
- authorization;
- approval binding;
- immutable ADR;
- projection replay equality.

## Evidence tests

- raw input preservation;
- claim typing;
- evidence linkage;
- content hashing;
- provenance downgrade/upgrade restrictions;
- redaction.

## Execution tests

- schema failures;
- timeout;
- cancellation;
- retry;
- dead-letter;
- provider diversity where policy requires;
- tool scope enforcement;
- prompt-injection isolation;
- simulation/live labeling.

## Graph tests

- Tarjan SCC;
- cycle deduplication;
- proposal generation;
- what-if simulation;
- preview graph validity;
- final human-confirmed mutation;
- rollback/revision semantics.

## Sync tests

- stale revision;
- semantic diff;
- non-conflicting merge proposal;
- same-field conflict;
- evidence conflict;
- policy conflict;
- user-confirmed reconciliation.

## Persistence tests

- event/projection atomicity;
- crash recovery;
- restore staging;
- outbox ordering;
- tombstones;
- workspace isolation.

## Android acceptance

- approximately 375px width;
- no horizontal scroll in critical flows;
- stale/failed/offline states;
- readable review/approval;
- graph text alternative;
- accessibility;
- session restoration after reinstall/login.

## Golden decision fixtures

Maintain a curated fixture set spanning:

```text
simple reversible choice
ambiguous decision
security-critical decision
high-impact irreversible decision
evidence-conflicted decision
single-option task masquerading as a decision
dependency-cycle decision
provider failure
stale approval
offline draft reconciliation
outcome invalidation
```

Every supported model/profile must pass the relevant fixture suite before production promotion.

---

# 28. Canonical state machines

## Decision state

```text
DRAFT
→ CONTEXT_REQUIRED
→ FRAMING
→ READY_FOR_DEBATE
→ DEBATING
→ CRITIQUE
→ SYNTHESIS
→ HUMAN_REVIEW
→ APPROVED
→ ADR_PUBLISHED
→ BLUEPRINT_UPDATED
→ OUTCOME_TRACKING
```

Alternate terminal/branching paths:

```text
HUMAN_REVIEW → REJECTED
HUMAN_REVIEW → DEFERRED
HUMAN_REVIEW → REVISION_REQUESTED → new revision
execution stage → FAILED → retry/new run for same revision
```

## Run state

Keep separate from decision state:

```text
QUEUED
→ RUNNING
→ CHECKPOINTED
→ COMPLETED

RUNNING → CANCELLED
RUNNING → FAILED
RUNNING → DEAD_LETTER
```

A run state must never directly equal a governance state.

---

# 29. Reference repository structure

Recommended:

```text
apps/
  android/
  api/
  worker/

packages/
  domain/
  contracts/
  policy/
  orchestration/
  integrations/
  provenance/
  quality/
  graph/
  projections/
  security/
  config/

infra/
  db/
  deploy/
  secrets/
  monitoring/

docs/
  architecture/
  contracts/
  runbooks/
  security/
  ADRs/
  test-fixtures/
```

Native Android:

```text
app/
  ui/
  feature/
  navigation/
  domain-client/
  data/
  network/
  local/
  security/
  sync/
  presentation/
```

The folder structure is implementation detail; the authority boundaries are the architectural contract.

---

# 30. Recommended implementation order

This is the final build sequence:

```text
M0  Contract freeze
 ↓
M1  Decision Domain + event authority
 ↓
M2  Firebase Auth + workspace authorization
 ↓
M3  Android D1-D3 client
 ↓
M4  Durable worker + mock execution
 ↓
M5  Evidence + policy + quality vector
 ↓
M6  Expert routing + Red Team + synthesis
 ↓
M7  Review + approval + ADR + Living Blueprint
 ↓
M8  Tarjan + cycle proposal + what-if simulation
 ↓
M9  Semantic diff + reconciliation
 ↓
M10 Real model/provider adapters
 ↓
M11 Production security/reliability
 ↓
M12 Outcome loop + policy feedback
 ↓
M13 Optional integrations
```

### Hard rule

Do not start with the hardest AI orchestration.

First prove:

```text
authoritative revision
→ persisted decision
→ deterministic policy
→ mock run
→ review
→ human approval
→ immutable ADR
→ rebuildable blueprint
```

Only then attach real model providers.

---

# 31. Migration from KingMaker-v4.1

v4.1 is a prototype/reference source, not the production authority.

Before migration:

- disable unsafe OTA;
- remove client-side provider secrets;
- remove insecure fallback paths;
- disable fake live-AI labels;
- prevent stale local approvals;
- remove production demo seeding.

Imported legacy records are marked:

```text
LEGACY_IMPORT
```

Legacy data does not automatically become v6 authoritative history.

Incomplete legacy revision histories become:

```text
legacy state
→ import revision
→ legacy provenance
→ validation required
```

Existing Room remains a local client cache/outbox during transition.

---

# 32. What is retained, adapted, deferred, rejected

## Retained

- Decision-first philosophy;
- Minimal-Sufficiency Intelligence;
- D1-D7 reasoning concept;
- selective specialists;
- Devil's Advocate;
- evidence provenance;
- graph integrity;
- Tarjan SCC;
- five lenses;
- Living Blueprint;
- human approval;
- proportionality;
- outcome tracking.

## Adapted

- DQS → quality vector;
- fixed agent swarm → policy-selected roles;
- fixed depth → bounded policy;
- local WAL → cache/outbox;
- universal evidence hierarchy → configurable policy;
- vendor SDKs → Model Gateway adapters;
- MCP → scoped read/propose;
- Firebase → identity + optional projection;
- cycle fixing → guided proposals + simulation + human authorization.

## Deferred

- A2A federation;
- training/DPO/RLHF pipeline;
- autonomous deployment/execution;
- hidden chain-of-thought storage;
- multi-region active-active;
- floating overlay;
- broad agent marketplace.

## Rejected

- client API keys;
- arbitrary APK installation;
- AI auto-approval;
- stale/offline approval truth;
- UI-generated evidence;
- silent cycle mutation;
- timeout-based approval;
- mathematical certainty claims;
- security bypass/root override;
- authentication by language/dialect/style;
- unverified vendor-specific assumptions.

---

# 33. Final invariants

```text
I-01  Human is final authority for material decisions.
I-02  AI never approves.
I-03  UI never approves by itself.
I-04  Only the Decision Domain mutates authoritative state.
I-05  Every material revision has a canonical hash.
I-06  Every governed command is idempotent and traceable.
I-07  Every revision-bound artifact points to one revision.
I-08  Stale writes fail closed.
I-09  Material edits create new revisions.
I-10  Approval is revision/policy/evidence/review bound.
I-11  ADRs are immutable.
I-12  Living Blueprint is rebuildable projection.
I-13  Claims cannot be upgraded by rendering.
I-14  Unproven model/tool output is quarantined.
I-15  Simulated ≠ live.
I-16  No provider secret ships in the APK.
I-17  External tools use declared scopes.
I-18  Cycle detection never silently mutates state.
I-19  Cycle resolution requires human authorization.
I-20  What-if simulation never mutates production state.
I-21  Quality is not approval.
I-22  Outcome data never rewrites decision-time quality.
I-23  Policy learning creates new policy versions.
I-24  Offline data never masquerades as cloud authority.
I-25  Firebase is never decision-domain authority.
I-26  Failed execution never advances governance state.
I-27  Lens changes never alter decision truth.
I-28  Adaptive policy is deterministic, versioned and replayable.
I-29  Semantic merges require conflict classification and user confirmation.
I-30  A component is incomplete until code + contract + persistence + provenance + failure path + tests agree.
```

---

# 34. Meta Definition of Done

KingMaker v6.0 is not “complete” when screens exist.

It is complete when the end-to-end chain works:

```text
USER INPUT
→ REVISION
→ CONTEXT
→ FRAME
→ POLICY
→ ROUTING
→ EXECUTION
→ EVIDENCE
→ CRITIQUE
→ SYNTHESIS
→ VALIDATION
→ HUMAN REVIEW
→ APPROVAL
→ ADR
→ BLUEPRINT
→ OUTCOME
→ POLICY LEARNING
```

And each arrow is:

```text
typed
authenticated where required
revision-bound
persisted
auditable
recoverable
test-covered
honestly labeled
```

---

# 35. Implementation-agent / AI Studio build brief

The implementation agent must understand the following as non-negotiable:

```text
1. Build the Android application as a client, not as the full decision engine.

2. Do not fake AI execution.
   A mock provider must be visibly labeled SIMULATED.

3. Do not put model/provider API keys in the APK.

4. Build server-side command authority before wiring real models.

5. Every material state transition must go through the Decision Domain.

6. Preserve raw input and explicit UNKNOWN / ASSUMPTION states.

7. Never silently invent missing facts.

8. Never silently overwrite stale revisions.

9. Never auto-approve.

10. Every approval must bind to the exact reviewed revision.

11. Persist the complete decision history server-side so reinstall/login restores it.

12. Use Firebase Authentication for the initial identity profile,
    while keeping the domain independent of Firebase.

13. Keep model/provider selection behind a Model Gateway.

14. Use contextual policy profiles, not hardcoded universal AI behavior.

15. Implement deterministic checks separately from LLM reasoning.

16. Implement graph cycle detection and guided resolution as a governed workflow.

17. Implement what-if simulation before graph mutations.

18. Implement semantic diff for stale/local-vs-server reconciliation.

19. Keep outcome observations separate from historical decision quality.

20. Make every important failure state visible and actionable.

21. Write tests against invariants, not only screens.

22. Do not introduce a generic multi-agent swarm merely because multiple models exist.

23. Do not add autonomous deployment or unrestricted shell/tool execution.

24. Do not claim a capability is live unless a real adapter executed it.

25. Preserve evidence, provenance, revision hashes and immutable history.
```

---

# 36. Final mental model

```text
                    HUMAN
                      │
                      ▼
             ┌─────────────────┐
             │ DECISION DOMAIN │
             │                 │
             │ revisions       │
             │ evidence        │
             │ policy          │
             │ graph           │
             │ quality         │
             │ approval        │
             │ ADR             │
             │ outcomes        │
             └───────┬─────────┘
                     │
            ┌────────┴────────┐
            ▼                 ▼
     EXECUTION PLANE     PROJECTION PLANE
     ├ experts           ├ Android
     ├ red team          ├ five lenses
     ├ synthesis         ├ ADR
     ├ models            ├ blueprint
     └ tools             └ history/outcomes
            │
            ▼
      TRUST / SECURITY / OPS
```

> **The model is replaceable. The client is replaceable. The queue is replaceable. Firebase is replaceable. The Decision Domain and the immutable decision history are the product.**

---

# 37. Canonical status declaration

**The final meta blueprint is `KingMaker v6.0 — Final Meta Blueprint`.**

The v5.2 document below remains preserved as the historical/consolidated source baseline that this meta blueprint reconciles.



---

# Appendix Z — Preserved v5.2 Complete Decision Intelligence System Blueprint

**Purpose of this appendix:** preserve the prior v5.2 document exactly for traceability. It is not a second competing architecture. Where it says an item is “open”, the v6.0 meta layer above governs the implementation posture. Where it contains legacy formulas/examples, those remain historical/reference material unless explicitly adopted by v6.0.

# KingMaker v5.2 — Complete Decision Intelligence System Blueprint

**Status:** Consolidated target baseline; candidate canonical architecture pending the explicitly listed open decisions

**Purpose:** Reconstruct the complete KingMaker system from the full source corpus, including product intent, user expectations, decision-reasoning flow, UX behavior, governance, domain authority, execution architecture, persistence/history, model routing, Firebase continuity, security, reliability, testing, migration and implementation. Source-derived facts remain distinguishable from consolidated design decisions and unresolved questions.

**Authority model:** This document is a consolidated design baseline. Source material is retained as evidence. Where this blueprint introduces or reconciles a design decision that was not explicitly canonical in the source package, it is marked as a **Consolidated Decision** or **Open Decision** rather than presented as an existing fact.

---

## 0. Executive architectural decision

KingMaker is a **human-governed Decision Intelligence System** whose durable authority lives in the **Decision Domain and Governance Layer**.

The Android client is an experience-first, online-first workspace with graceful degraded-network behavior. The backend is the authoritative command/query boundary. The execution plane performs bounded expert, adversarial, synthesis, evidence and investigation work through replaceable adapters. Model providers, agent runtimes, MCP tools, Antigravity, Firebase, mobile screens and caches are never the system of record and never receive approval authority.

The central separation is:

```text
Human Authority
      ↓
Decision Domain + Governance Policy
      ↓
Command / Revision / Evidence / Approval / ADR authority
      ↓
Execution Plane
      ├─ expert perspectives
      ├─ adversarial critique
      ├─ synthesis
      ├─ research / evidence adapters
      └─ optional investigators / MCP proposal tools
      ↓
Projections
      ├─ mobile read model
      ├─ ADR
      ├─ Living Blueprint
      └─ outcome views
```

The v5 package identifies this authority boundary as the architectural response to the principal v4.1 weakness: UI, mutable local projections, simulated AI, sync and approval logic had been mixed in one client.

---

# 0A. What “complete KingMaker” means

This blueprint treats KingMaker as a complete system only when all four layers are specified together:

1. **User intent and experience:** what the user is trying to accomplish, what the user sees, what the user can correct, and what the user must explicitly authorize.
2. **Decision reasoning:** how raw input becomes a decision frame, questions, evidence, options, perspectives, critique, synthesis, validation and a human decision.
3. **System authority:** which component owns truth, which mutations are legal, how revisions and events work, and how stale/unauthorized actions are rejected.
4. **Operational continuity:** what survives app restarts, uninstall/reinstall, device changes, provider failures, network loss, worker failure and later outcome review.

The earlier v5 package is the authoritative architecture baseline, but it does not by itself answer every user-journey detail. This section and the sections below explicitly fill only those gaps that can be derived from the supplied source material and the user’s corrections in this conversation.

## 0A.1 Evidence status labels

- **SOURCE-CANONICAL:** explicit in the v5 package or accepted source evidence.
- **SOURCE-ADOPTED:** explicit in earlier source material and intentionally retained.
- **CONSOLIDATED DECISION:** derived by reconciling multiple source items and user requirements.
- **OPEN DECISION:** intentionally unresolved; must be selected before the affected production implementation is frozen.

## 0A.2 User corrections incorporated in this version

The following are explicit user requirements from the current design discussion, not claims that they already existed in the v5 package:

- **Online-first operation** is preferred over making offline-first a primary capability.
- **Native Android/Kotlin + Jetpack Compose** remains the desired client.
- **Persistent account-linked history** must survive APK uninstall/reinstall when the same account is restored.
- **Firebase integration is desired**, but it must not become the authoritative decision domain.
- **Model integration should remain provider/model agnostic** so stronger available providers/models can be used instead of freezing the system to the old small-model examples from v4.0.

These requirements are incorporated as consolidated design decisions below.

---

# 1. Product identity and scope

## 1.1 Product statement

KingMaker converts an incomplete technical decision into a bounded, evidence-aware, challenge-tested and human-approved decision record that remains traceable as the surrounding system changes.

The system is **decision-first**, not conversation-first.

Every interaction must either:

- advance a decision artifact,
- improve its evidence/context,
- expose disagreement or risk,
- validate a candidate decision,
- record an explicit human governance action, or
- preserve a traceable outcome observation.

## 1.2 Primary initial scope

The first production scope is technical and architectural decision governance, including:

- architecture selection;
- data and storage strategy;
- integration design;
- security posture;
- reliability and operational readiness;
- migration choices;
- feature-readiness decisions;
- infrastructure and technology trade-offs.

## 1.3 Explicit non-goals

KingMaker does not become, in v1:

- an autonomous software company;
- an unrestricted agent marketplace;
- a general project/task manager;
- an autonomous deployment system;
- an unrestricted infrastructure-control agent;
- an automatic approval engine;
- a universal real-time information platform;
- a product whose private model chain-of-thought becomes an audit artifact.

---

# 2. Core principles

1. **Decision over conversation.**
2. **Minimal-sufficient context.** Ask only questions capable of changing the decision, risk, validation or approval requirement.
3. **Typed epistemology.** Facts, constraints, preferences, inferences, recommendations, risks, assumptions, unknowns and unsupported claims remain distinct.
4. **Independent perspectives.** Relevant experts reason independently before synthesis.
5. **Constructive adversarial validation.** Critique attacks claims/options/assumptions, not people and not theatrically invented objections.
6. **Human sovereignty.** AI recommends; an authorized human decides.
7. **Traceability.** Material outputs link to revision, actor, policy, evidence and run.
8. **Reversible progress.** Insufficient evidence produces validation, deferral or a reversible experiment rather than fabricated certainty.
9. **Projection over mutation.** ADRs and approvals are immutable; current views and the Living Blueprint are rebuildable projections.
10. **Android-first clarity.** Core workflows remain usable on narrow screens and degraded connectivity.
11. **Fail closed.** Missing identity, stale revision, invalid provenance, unsafe integration or failed policy blocks the command.
12. **Honest mode labeling.** Simulated, replayed and live/provider-backed executions are visibly and semantically distinct.
13. **Adaptive does not mean uncontrolled.** Context can change policy routing only through versioned, deterministic policy definitions.
14. **No UI truth.** Presentation code cannot upgrade provenance, approve decisions or fabricate missing evidence.

---

# 3. Authority hierarchy and trust model

## 3.1 Authority order

```text
1. Authorized human authority
2. KingMaker Decision Domain
3. Deterministic Governance / Policy
4. Execution workers and agent adapters
5. Integration tools / investigators
6. Client UI / caches / local drafts
```

This is not an AI “brain hierarchy”; it is an **authority hierarchy**.

## 3.2 Four-plane architecture

### Plane A — Experience Plane

Android client, mobile UX, accessibility, local drafts, offline outbox, read projections and user interactions.

### Plane B — Decision Domain Plane

The authoritative decision model:

- decision identity;
- immutable revisions;
- typed claims;
- evidence linkage;
- options and criteria;
- policy evaluation;
- state transitions;
- approval;
- ADR;
- blueprint projection commands;
- outcome classification;
- immutable governance events.

### Plane C — Execution Plane

Asynchronous work:

- question generation;
- role selection;
- expert perspectives;
- adversarial critique;
- synthesis;
- evidence collection;
- optional MCP read/propose operations;
- optional read-only investigator adapters;
- provider adapters.

### Plane D — Trust and Operations Plane

Cross-cutting controls:

- identity;
- object/workspace authorization;
- secret management;
- redaction;
- provenance validation;
- tool scopes;
- idempotency;
- observability;
- retention;
- backup/restore;
- update signing;
- incident response.

---

# 4. Runtime topology

## 4.1 Production topology

```text
                    ┌──────────────────────┐
                    │  Android Client      │
                    │  Kotlin/Compose      │
                    │  offline drafts      │
                    │  encrypted outbox    │
                    └──────────┬───────────┘
                               │ HTTPS
                               ▼
                    ┌──────────────────────┐
                    │ API / Command Plane  │
                    │ Auth + Authorization │
                    │ Idempotency          │
                    │ Revision checks      │
                    └──────────┬───────────┘
                               │
               ┌───────────────┼────────────────┐
               ▼               ▼                ▼
        ┌─────────────┐ ┌─────────────┐ ┌─────────────┐
        │ Domain      │ │ PostgreSQL  │ │ Policy      │
        │ Services    │ │ Authority   │ │ Engine      │
        └──────┬──────┘ └─────────────┘ └─────────────┘
               │
               ▼
        ┌─────────────┐
        │ Durable     │
        │ Queue       │
        └──────┬──────┘
               ▼
        ┌─────────────┐
        │ Worker      │
        │ Execution   │
        └──────┬──────┘
          ┌────┴─────────────┐
          ▼                  ▼
   Provider Adapter      MCP/Investigator
          │                  │
          └────────┬─────────┘
                   ▼
             Validated Artifacts
                   │
                   ▼
           Domain / Projection
```

## 4.2 First production deployment shape

Use a **modular monolith plus separate worker process** rather than microservices. This preserves one transaction boundary while keeping the execution plane independently scalable.

Separate services/process responsibilities are:

- API/command/query process;
- worker execution process;
- scheduled outcome-review process when needed.

The initial deployment should avoid premature service fragmentation.

---

# 5. Client technology decision

## 5.1 Consolidated decision: retain native Android client

The v5 package recommends React Native/Expo, but it explicitly allows a Kotlin-native client provided that it consumes the same contracts and does not become a second domain authority.

Because the current KingMaker-v4.1 prototype is already native Android/Jetpack Compose, the consolidated blueprint adopts:

**Kotlin + Jetpack Compose + single Activity + contract-driven backend integration.**

This decision is a delivery decision, not a change to the v5 authority architecture.

## 5.2 Client architecture

```text
Compose UI
   ↓
Feature ViewModel / State holder
   ↓
Use-case / Client command layer
   ↓
Generated or strongly typed API client
   ↓
Backend authority
```

The client must not contain authoritative domain rules duplicated from the backend.

Client-side validation is permitted for responsiveness; server/domain validation remains authoritative.

## 5.3 State-management rule

Use unidirectional data flow / StateFlow where appropriate, but do not create one giant “God ViewModel”.

Recommended feature state separation:

- InboxViewModel;
- DecisionIntakeViewModel;
- ContextViewModel;
- FramingViewModel;
- RunViewModel;
- ReviewViewModel;
- BlueprintViewModel;
- OutcomeViewModel;
- SettingsViewModel.

Shared session/workspace state should be small and stable.

---

# 6. Decision Domain: the authoritative core

## 6.1 What “Domain” means in KingMaker

The **Decision Domain** is the software authority that determines what a decision *is*, which revision is current, what evidence/provenance exists, what transitions are legal, which policy applies, whether approval conditions are satisfied, and which immutable events must be recorded.

The domain is not the AI model.

The domain is not the UI.

The domain is not the database by itself.

The database stores domain state; the domain defines what the stored state means and what mutations are legal.

## 6.2 Authoritative write rule

Only the Decision Domain may mutate authoritative business state.

```text
UI request
   ↓
API command
   ↓
authorization + revision validation
   ↓
policy evaluation
   ↓
domain command
   ↓
append event + update current projection atomically
```

No worker, model, tool, Firebase document, local Room row or UI callback may bypass this boundary.

---

# 7. Domain entities

The v5 package establishes the following core entities.

## Workspace

Owner, members, policy set, retention, integrations and workspace lifecycle state.

## WorkspaceMember

Identity, role, membership status and authorization scope.

## WorkspacePolicy

Versioned policy configuration controlling impact tiers, evidence requirements, role selection, quality evaluation, tool scopes and approval conditions.

## Decision

Stable identity plus current revision pointer, status, owner, impact, reversibility, timestamps and next action.

## DecisionRevision

Immutable material state of the decision:

- raw input;
- normalized question;
- context answers;
- frame;
- claims;
- unknowns;
- options;
- criteria;
- dependencies;
- selected policy snapshot;
- canonical content hash;
- creator and timestamp.

## Claim

Typed statement with provenance and evidence links.

## EvidenceItem

Source kind, locator, captured time, content hash, freshness, trust/provenance status, redaction status, access scope and metadata.

## ClaimEvidenceLink

Explicit relationship between a claim and supporting/contradicting evidence.

## ContextQuestion / ContextAnswer

Minimal-sufficiency questions with reason, affected criteria, answer, explicit unknown/not-applicable/defer state and policy version.

## DecisionOption

Candidate option with status, rationale, evidence links and criteria relationships.

## EvaluationCriterion

Decision-specific comparison criterion, weight/policy reference and evaluation explanation.

## TradeOff

Explicit benefit/cost relationship between alternatives or design consequences.

## DebateRun

Revision-bound run record containing policy snapshot, requested depth, task plan, deadline, budget, provenance mode, checkpoint and terminal reason.

## DebateTask / JobAttempt

Work item, role, task class, attempt state, lease, timeout, retry and result references.

## AgentPerspective

Structured expert perspective with role, position, criterion reasoning, claims, assumptions, trade-offs, failure modes, evidence links, dissent and revisit conditions.

## Critique

Targeted challenge to a claim, option, criterion or assumption.

## SynthesisResult

Agreement, disagreement, decisive criteria, leading option, strongest alternative, evidence gaps, unresolved risks, validation plan and revisit triggers.

## QualityResult

Versioned quality vector with component values, explanations, evidence links, policy version and calculation inputs.

## ValidationPlan / ValidationResult

What must be tested or verified, by whom, with what success criteria and what was observed.

## Approval

Immutable human governance action bound to revision hash, review packet hash, evidence snapshot, policy version and rationale.

## ADR

Immutable accepted decision record.

## BlueprintNode / BlueprintEdge

Rebuildable projection derived from ADRs and accepted relationships.

## DecisionActivityTimeline (derived projection)

**Consolidated Decision:** the app may expose a user-friendly historical timeline derived from immutable events, revisions, runs, reviews, approvals and outcomes.

This timeline is a projection, not a second source of truth. It exists to make “what happened in this decision?” immediately understandable after reinstall, device change or later review.

## OutcomeReference / OutcomeObservation

Expected metric and later observations. Outcome records do not rewrite decision-time quality.

## PromptVersion / ModelRecord / IntegrationConnection

Execution provenance and runtime configuration metadata.

## AuditEvent

Immutable governance/operational record.

## IdempotencyRecord

Maps an idempotency key to its original result and prevents duplicate material effects.

## Attachment

Hash-addressed protected content with scan/redaction/access metadata.

---

# 8. Typed epistemology and evidence model

## 8.1 Claim types

The following types are canonical:

- `FACT`
- `CONSTRAINT`
- `PREFERENCE`
- `INFERENCE`
- `RECOMMENDATION`
- `RISK`
- `ASSUMPTION`
- `UNKNOWN`
- `UNVERIFIED`

A presentation component cannot upgrade one claim into another type.

Only a domain command, backed by accepted evidence or explicit human confirmation according to policy, can change claim status.

## 8.2 Evidence rules

Every material claim must be traceable to one or more of:

- user-supplied source;
- verified external source;
- accepted evidence item;
- explicit human assertion where policy permits;
- bounded inference supported by referenced claims.

Unresolved support remains `UNVERIFIED` or `ASSUMPTION`.

Rendering must never manufacture `VERIFIED` status.

## 8.3 Evidence hierarchy

The old universal “Axiomatic > Empirical > Heuristic > Assumption” ordering is retained only as a historical reference.

**Consolidated decision:** trust hierarchy becomes configurable by domain/workspace policy because no single universal ranking is guaranteed to be valid for every technical domain.

---

# 9. Revision model and immutability

## 9.1 Revision principle

A revision is the exact material decision state used by analysis, validation or approval.

Each revision gets:

- revision ID;
- parent revision ID where applicable;
- canonical serialization;
- SHA-256 revision hash;
- policy version/hash;
- creator;
- creation time.

## 9.2 Material-change rule

Any change that can change the decision, recommendation, evidence interpretation, evaluation criteria, validation requirements, selected option or approval conditions creates a **new revision**.

## 9.3 Approval binding

Approval is bound to:

- current revision ID;
- current revision hash;
- review packet hash;
- evidence snapshot hash;
- policy version/hash;
- selected option;
- explicit human rationale;
- authenticated approving identity;
- approval timestamp.

A stale or mismatched approval is rejected.

---

# 10. Lifecycle: D1-D8 + outcome loop

## D1 — Intake

- Preserve raw user statement exactly.
- Create initial decision and revision.
- Infer only provisional classifications.
- Show normalized decision question for user confirmation.
- Do not silently invent missing requirements.

## D2 — Minimal Context

- Generate questions once per revision and policy version.
- Rank by expected decision impact, uncertainty reduction, answerability and user cost.
- Allow `ANSWER`, `UNKNOWN`, `NOT_APPLICABLE`, `DEFER`.
- A material unanswered question remains unknown/assumption rather than silently becoming fact.

## D3 — Framing

Build:

- objective;
- non-goals;
- stakeholders;
- hard constraints;
- preferences;
- success criteria;
- evaluation dimensions;
- candidate options;
- rejected alternatives;
- dependencies;
- validation budget;
- revisit triggers.

The decision owner confirms the frame.

## D4 — Independent Expert Perspectives

The role policy selects relevant domains rather than always using a fixed universal panel.

Each selected role receives a bounded context packet and returns a structured perspective.

The perspective must include position, criteria reasoning, assumptions, trade-offs, failure modes, evidence links, confidence contribution, dissent and revisit conditions.

## D5 — Adversarial Critique

Devil’s Advocate and specialist critique workers challenge leading options.

Critique must identify:

- target;
- challenge;
- supporting evidence or gap;
- severity;
- recommended validation/test;
- current resolution state.

The system preserves disagreement; it does not force artificial consensus.

## D6 — Synthesis and Refinement

Synthesis produces:

- agreement;
- disagreement;
- decisive criteria;
- leading option;
- strongest alternative;
- evidence gaps;
- unresolved risks;
- validation plan;
- revisit triggers;
- quality vector;
- execution provenance.

Refinement is bounded by policy, budget, time, task count and non-improvement.

When evidence is insufficient, the result is `INSUFFICIENT_CONFIDENCE`, not a forced recommendation.

## D7 — Validation + Human Review

Validation may include:

- scenario walkthrough;
- consistency check;
- risk checklist;
- small experiment;
- migration rehearsal;
- authorized peer review;
- other policy-approved verification methods.

The review packet is revision-bound and shows the exact artifacts under consideration.

Human actions are explicit:

- Approve
- Reject
- Defer
- Request Revision

## D8 — ADR + Living Blueprint

Approval creates an immutable ADR.

The blueprint projection rebuilds from ADRs and accepted relationships.

A later material change supersedes the old ADR through a new revision and new ADR; the old ADR is never edited.

## Outcome Review

Track:

- expected metrics;
- review date;
- actual observations;
- divergence classification.

Divergence categories:

- expected variance;
- evidence gap;
- operational issue;
- decision invalidation.

Only invalidation proposes a new decision. Outcome observations do not rewrite decision-time quality.

---

# 10A. User-centric Decision Reasoning Specification

This section defines the **actual cognitive workflow** KingMaker must implement. It is the layer that turns the architecture from a set of components into a decision-making system.

## 10A.1 User starts with a human problem, not a schema

The first user input is intentionally messy and may contain:

- a desired outcome;
- an implementation preference;
- a constraint;
- an assumption;
- multiple unrelated problems;
- missing context.

KingMaker must not assume the user already knows how to formulate a formal decision.

## 10A.2 The reasoning contract

For every stage, the internal contract is:

```text
INPUT
  ↓
INTERPRETATION / PROCESSING
  ↓
EVIDENCE / ASSUMPTIONS
  ↓
DETERMINISTIC POLICY CHECK
  ↓
OUTPUT ARTIFACT
  ↓
USER-VISIBLE EXPLANATION
  ↓
USER CORRECTION / CONFIRMATION
  ↓
EVENT + REVISION BINDING
  ↓
NEXT LEGAL STATE
```

No stage is complete merely because an AI response exists. The stage is complete when its domain conditions are met.

## 10A.3 D1 cognitive objective: “What are we actually deciding?”

Internal questions:

1. What is the raw statement?
2. Is this a decision, a task, a preference, a symptom of another decision, or a request for information?
3. What outcome is the user trying to choose between?
4. What appears fixed versus flexible?
5. What is known?
6. What is unknown?
7. What is merely assumed?
8. Which domains appear relevant?
9. What is the likely impact and reversibility?

User-facing output:

- normalized decision question;
- preliminary interpretation;
- detected claims/unknowns;
- impact/reversibility preview;
- “edit before continuing” controls.

**Hard rule:** inferred content remains provisional until the user accepts it into the revision.

## 10A.4 D2 cognitive objective: “What missing information could actually change the decision?”

Each candidate question is evaluated against:

```text
question
├─ unknown addressed
├─ decision elements affected
├─ criteria/options affected
├─ expected uncertainty reduction
├─ potential to change recommendation
├─ answerability
└─ user effort/cost
```

The system should ask only questions that can materially change the decision, risk, validation requirement or approval condition.

User controls:

- Answer;
- I don’t know;
- Not applicable;
- Defer.

A user saying “I don’t know” is not a failed answer. It is decision information.

## 10A.5 D3 cognitive objective: “What is the actual decision frame?”

The system turns context into a stable frame:

```text
Objective
Non-goals
Stakeholders
Hard constraints
Preferences
Success criteria
Evaluation dimensions
Candidate options
Rejected alternatives
Dependencies
Validation budget
Revisit triggers
```

The user must be able to see the frame as a coherent statement before D4 begins.

A frame should also answer:

> “What would count as a meaningful change to this decision?”

If there is effectively only one candidate option, the system should explicitly indicate that the current item may be an implementation task rather than a true comparative decision, unless policy allows a one-option validation decision.

This is a **derived product-design rule**, not an existing v5 canonical command, and must remain policy-configurable.

## 10A.6 Pre-run readiness gate

Before D4, deterministic policy checks:

- current revision valid;
- framing confirmed;
- material questions answered or explicitly unresolved under policy;
- criteria exist;
- option coverage meets policy or an exception is recorded;
- impact/reversibility are classified;
- run budget is legal;
- no blocking graph cycle/approval conflict exists;
- required evidence preconditions are satisfied.

Failure produces a specific next action. It does not silently push the user into debate.

## 10A.7 D4 cognitive objective: “Which perspectives could change this decision?”

Role selection is a policy decision, not a fixed agent count.

The role-selection service considers:

- decision domain;
- criteria;
- risk signals;
- reversibility;
- unresolved evidence;
- operational consequences;
- affected stakeholders.

Each selected role must have a stated mandate.

The UI should be able to answer:

> “Why was this perspective requested?”

## 10A.8 Independent reasoning before consensus

Selected roles reason independently before seeing the consolidated opinions of other roles, subject to bounded shared context.

The system then extracts:

- agreement;
- disagreement;
- unique evidence;
- contradictory claims;
- unsupported confidence;
- criteria where role views diverge.

Consensus is not itself a quality signal.

## 10A.9 D5 cognitive objective: “What is the strongest credible case against the leading option?”

Every critique must be target-directed:

```text
target
+ challenge
+ evidence or evidence gap
+ severity
+ recommended test
+ resolution state
```

The Devil’s Advocate should challenge the strongest version of the leading option rather than inventing generic objections.

The Coverage Auditor looks for missing perspectives, hidden dependencies and unknown unknowns.

## 10A.10 Disagreement handling

When two artifacts disagree, the system should not collapse them into a single number. It should preserve:

- claim A;
- claim B;
- evidence supporting each;
- reason for divergence;
- what additional evidence could resolve it;
- whether the disagreement affects the decision.

If the disagreement cannot be resolved at reasonable cost, the user should see the unresolved disagreement and its consequence.

## 10A.11 D6 cognitive objective: “What should a human understand before deciding?”

Synthesis must answer five user questions:

1. What are the realistic options?
2. Which criteria actually decide between them?
3. What is the strongest recommendation and why?
4. What is the strongest alternative and why?
5. What could still make this recommendation wrong?

The synthesis packet therefore contains:

- recommendation;
- strongest alternative;
- criteria comparison;
- decisive evidence;
- disagreements;
- unresolved risks;
- assumptions;
- validation plan;
- consequences;
- revisit triggers;
- quality vector;
- execution provenance.

## 10A.12 “Why?” and “What would change this?” are first-class UX requirements

Every material recommendation should expose:

**Why this?**
- decisive criteria;
- supporting evidence;
- constraints;
- trade-offs.

**What could change it?**
- unresolved assumption;
- evidence threshold;
- scenario condition;
- new outcome evidence;
- changed constraint/policy.

This is how KingMaker avoids turning a recommendation into an opaque answer.

## 10A.13 D7 cognitive objective: “Do I have enough basis to decide?”

The review stage is not a score screen. It is a decision checkpoint.

The review surface must show:

- exact revision;
- recommendation;
- strongest alternative;
- decisive criteria;
- quality vector;
- evidence map;
- dissent;
- unresolved risks;
- validation results;
- consequences;
- revisit triggers;
- execution provenance.

The user then explicitly chooses:

- Approve;
- Reject;
- Defer;
- Request revision.

Approval requires explicit rationale and current revision binding.

## 10A.14 Post-approval: “Did the decision hold in reality?”

Outcome review must compare expected versus observed results without rewriting the original decision-time quality.

This creates a learning loop:

```text
Expected outcome
    ↓
Observed outcome
    ↓
Divergence classification
    ├─ expected variance
    ├─ evidence gap
    ├─ operational issue
    └─ decision invalidation
           ↓
       new decision only when justified
```

## 10A.15 Stage failure principle

A failure should answer:

- what failed?
- which artifact is affected?
- does the revision remain valid?
- can the task retry?
- does the user need to act?
- what must not be changed?

A generic “Something went wrong” state is insufficient for a governance system.

---

# 11. Decision state machine

Decision state and execution-run state must be treated as different state machines.

## 11.1 Decision lifecycle

```text
DRAFT
  → CONTEXT_REQUIRED
  → FRAMING
  → READY_FOR_DEBATE
  → DEBATING
  → CRITIQUE
  → SYNTHESIS
  → HUMAN_REVIEW
      ├→ APPROVED
      │    → ADR_PUBLISHED
      │    → BLUEPRINT_UPDATED
      │    → OUTCOME_TRACKING
      ├→ REJECTED
      ├→ DEFERRED
      │    → explicit reactivation
      │    → new revision
      └→ REVISION_REQUESTED
           → new revision
           → FRAMING

DEBATING / CRITIQUE / SYNTHESIS
   → FAILED
   → retry with new run for same revision
```

## 11.2 Domain transition rule

UI navigation never changes authoritative status.

Every transition is a typed domain command with:

- actor;
- workspace;
- decision;
- revision ID/hash;
- idempotency key;
- correlation ID;
- policy version;
- preconditions.

---

# 12. Proportionality Engine

## 12.1 Purpose

The Proportionality Engine decides how much reasoning, validation, evidence gathering and compute a decision deserves.

Principle:

> Do not use maximum reasoning depth for a trivial decision, and do not use minimum reasoning depth for a high-impact irreversible decision.

## 12.2 Baseline complexity model

The v4.0 documents define:

```text
Complexity =
    Risk * 0.35
  + Impact * 0.30
  + (1 - Changeability) * 0.20
  + Budget * 0.15
```

This becomes the **legacy-compatible baseline profile**, not an eternal universal constant.

## 12.3 Versioned policy profiles

**Consolidated Decision:** complexity/depth policies are versioned and contextual.

A policy profile contains:

```text
policyId
version
contextClass
impactRules
reversibilityRules
complexityInputs
complexityWeights
qualityThresholds
maxRounds
maxTasks
maxRuntime
researchDepth
requiredRoles
requiredEvidence
requiredValidation
approvalConditions
toolScopes
```

Candidate profile classes can include:

- `GENERAL`
- `EARLY_DISCOVERY`
- `CRITICAL_RELEASE`
- `SECURITY_CRITICAL`
- `BUDGET_CONSTRAINED`
- `REGULATED`

These are policy slots, not hardcoded numerical promises. Coefficients must be calibrated before production.

## 12.4 Adaptive-weighting invariant

```text
Adaptive ≠ uncontrolled
```

AI may recommend a policy profile.

AI may not invent arbitrary weights and apply them silently.

The applied profile is:

- deterministic;
- versioned;
- persisted with the revision/run;
- replayable;
- auditable.

Changing the selected policy in a material way creates a new decision revision or a policy-bound new run according to domain rules.

---

# 13. Quality and confidence model

## 13.1 Replace “certainty score” with a quality vector

Canonical v5 quality vector:

```text
quality = {
  evidenceStrength,
  frameCompleteness,
  constraintFit,
  optionCoverage,
  reversibility,
  riskExposure,
  disagreement,
  validationReadiness,
  complexityPenalty
}
```

Each component:

- is normalized to `[0,1]`;
- stores an explanation;
- stores evidence links where applicable;
- stores policy version;
- stores calculation inputs;
- remains interpretable independently.

`riskExposure` and `complexityPenalty` are penalties, not hidden defects in a black-box aggregate.

## 13.2 Composite score

A composite is optional and must be explicitly labeled **heuristic quality summary**.

A composite can never be the sole basis for approval.

High-impact decisions require the policy-defined combination of:

- evidence sufficiency;
- alternative coverage;
- validation;
- reviewer conditions;
- policy eligibility;
- current revision validity.

## 13.3 Legacy DQS compatibility

The v4.0 developer handoff contains the seven-parameter formula:

```text
LegacyDQS7 =
  (0.20E + 0.20T + 0.18R + 0.17U + 0.12A + 0.08C + 0.05V) / 0.85
```

**Consolidated decision:** retain this only as a **legacy compatibility/calculation view** when importing or comparing v4-era records.

It must not become the v5 approval truth.

This avoids the contradiction between the earlier 7-parameter DQS and the v5 vector-based quality architecture.

---

# 14. Multi-agent execution architecture

## 14.1 Role registry

The system does not require a fixed four-agent or six-agent panel for every decision.

Roles are policy-selected.

Each role definition contains:

- role ID;
- mandate;
- required expertise;
- required evidence classes;
- expected output schema;
- allowed tools;
- allowed context scope;
- critique responsibilities;
- maximum task budget.

Example roles:

- Software Architect;
- Data Architect;
- Security Architect;
- UX / Human Factors;
- Reliability / Operations;
- Business Analyst;
- Migration Specialist;
- Compliance Specialist;
- Developer / Implementation reviewer.

## 14.2 Devil’s Advocate

The Devil’s Advocate remains a first-class adversarial role for applicable tiers.

It must:

- attack the strongest leading option;
- identify unsupported assumptions;
- search for contradictory evidence;
- expose hidden coupling and failure modes;
- challenge scope and reversibility;
- preserve its dissent independently of consensus pressure.

A critique without a target is invalid.

## 14.3 Coverage Auditor

The legacy v4 architecture names a Coverage Auditor for unknown-unknown detection. The consolidated blueprint keeps this capability as a policy-selectable critique role.

It should ask:

- what important domain is missing?
- what dependency is assumed but unverified?
- what operational consequence is not represented?
- what evidence gap could reverse the recommendation?
- what assumption has been silently upgraded?

## 14.4 Structured execution only

Model outputs must be schema-validated.

Invalid outputs are quarantined.

Model output cannot directly call governance commands.

No hidden chain-of-thought is stored as an authority artifact.

The system records concise structured reasoning artifacts: claims, reasons, evidence links, trade-offs, dissent, validation requests and conclusions.

---

# 15. Bounded debate and circuit breaking

## 15.1 Routing controls

Every run gets:

- maximum rounds;
- maximum tasks;
- deadline;
- cost/usage budget;
- cancellation policy;
- terminal reason requirement.

## 15.2 Stop conditions

A run may terminate because:

1. required policy coverage is satisfied;
2. budget is exhausted;
3. deadline is reached;
4. task limit is reached;
5. repeated non-improvement occurs;
6. required provider/tool capability fails;
7. a governance blocker is detected;
8. evidence is insufficient and policy requires stopping.

The old `Delta < 0.05` rule is retained only as one possible policy trigger, not a universal constant.

---

# 16. Cycle detection and Guided Resolution

## 16.1 Graph model

Accepted graph edges are typed:

- `DEPENDS_ON`
- `CONSTRAINS`
- `SUPPORTS`
- `SUPERSEDES`
- `CONTRADICTS`
- `DERIVED_FROM`
- `AFFECTS`

## 16.2 Tarjan SCC

Tarjan’s Strongly Connected Components algorithm is used to detect strongly connected components that violate the intended dependency structure.

A cycle is never silently deleted.

## 16.3 Cycle task identity

A resolution task is deduplicated by a canonical cycle identity derived from:

- revision hash;
- sorted node IDs;
- sorted cycle-edge IDs.

A new scan must not create duplicate tasks for the same open cycle.

## 16.4 Weakest-evidence analysis

The engine may identify the weakest-supported node or edge as a diagnostic candidate.

**It may not automatically convert that node into `ASSUMPTION`, delete an edge or rewrite decision evidence.**

That distinction is mandatory.

## 16.5 Guided resolution protocol

The consolidated system adds the podcast-derived proposal layer:

```text
Cycle detected
      ↓
Tarjan SCC
      ↓
Resolution Task
      ↓
AI analyzes actual cycle
      ↓
Candidate resolution proposals
      ├─ A: lowest-impact / weakest-supported break
      ├─ B: architectural restructuring / reframe
      └─ C: manual human override
      ↓
Human selects or manually defines action
      ↓
Preview expected graph result
      ↓
Validate acyclic graph
      ↓
Human confirms
      ↓
Immutable Resolution Event
      ↓
Projection update
```

### Proposal requirements

Each proposal includes:

- proposal ID;
- route type;
- affected nodes/edges;
- rationale;
- evidence used;
- predicted impact;
- reversibility;
- risks;
- resulting graph hash preview;
- validation result.

AI proposes; domain validates; human authorizes.

---

# 17. Five Lenses and Progressive Disclosure

## 17.1 Five presentation lenses

The five legacy lenses remain:

1. **Executive**
2. **Architecture**
3. **UX**
4. **Developer**
5. **Governance**

They are views of the same immutable evidence and decision record.

A lens must never create a new decision analysis independently.

## 17.2 Progressive disclosure rule

Default view:

- recommendation;
- strongest alternative;
- top three critical findings;
- current quality vector summary;
- unresolved critical risks;
- validation status;
- next required action.

Expanded layers reveal:

- all criteria;
- all dissent;
- all agent perspectives;
- evidence map;
- 14D critique registry;
- full event/run history;
- policy calculation inputs.

“Collapsed” means hidden from the initial viewport, not deleted from the packet.

## 17.3 Critical-finding ordering

The ordering of critical findings must be deterministic and policy-defined. It may consider:

- severity;
- unresolved state;
- evidence gap;
- dependency centrality;
- impact;
- reversibility;
- policy priority.

Lens changes must never change the underlying quality calculation or approval result.

## 17.4 The 14-dimension issue

The legacy v4 material explicitly references a 14-dimension critique and names System Integrity as the 14th dimension, with Coverage Auditor participation.

The current v5 package does **not** provide a complete canonical list of all 14 dimensions.

Therefore the blueprint does **not invent the missing 13 names**.

Instead, v5 uses a versioned `CritiqueDimensionRegistry`:

```text
registryId
version
dimensions[]
severityRules
requiredRoles
requiredEvidence
scoring/explanation rules
```

The actual legacy 14-dimension list must be imported and locked as a specific registry version before a production “14D” badge is used.

Until then, the system should say `Critique Dimensions vX`, not claim an unverified canonical 14D list.

---

# 18. Android UX architecture

## 18.1 Primary destinations

- Inbox;
- New Decision;
- Runs;
- Blueprint;
- Settings.

## 18.2 Progressive drill-down

The mobile interaction pattern is:

```text
Inbox/Card
   ↓
Decision Detail
   ↓
Bottom Sheet / quick summary
   ↓
Deep Review / Audit
   ↓
Exception workflow only when required
```

A separate Activity is not required for each detail state.

## 18.3 Single Activity

Use one Activity with Compose navigation and feature-level state.

Benefits:

- predictable back stack;
- shared session state;
- simpler exception routing;
- fewer lifecycle ownership problems;
- consistent progressive disclosure.

## 18.4 Required screen states

Every major screen must support:

- first-use empty;
- loading;
- partial data;
- offline draft;
- syncing;
- stale conflict;
- permission denied;
- validation error;
- server error/retry;
- cancelled run;
- failed run/checkpoint;
- success confirmation;
- accessibility fallback.

## 18.5 Trust language

Use:

- `heuristic quality vector`, not certainty;
- `simulated run`;
- `replayed run`;
- `provider-backed run`;
- `unresolved assumption`;
- `approval recorded` only after server confirmation.

---

# 19. Native Android engineering refinements

## 19.1 Background computation

Domain-like client computations that are needed for responsiveness may run off the UI thread using coroutines and appropriate dispatchers.

This is an implementation safety rule, not a new domain authority.

Examples:

- local graph visualization preparation;
- large list transforms;
- hash calculations;
- export preparation;
- offline cache work.

Authoritative graph mutation and approval remain server/domain commands.

## 19.2 Biometric approval step-up

Android biometric authentication may be used as a local step-up verification for an approval gesture.

Biometric authentication alone is **not** the governance authority.

Correct pattern:

```text
Current approved candidate
 + current server revision
 + policy eligibility
 + human rationale
 + authenticated session
 + optional biometric step-up
 → approval command
```

## 19.3 TTS

Native Android TTS is an optional UX capability for short decision summaries.

It has no governance authority and no effect on quality scores.

## 19.4 Floating overlay

A cross-app floating quick-intake bubble is deferred to a later capability phase because it introduces extra permissions, background lifecycle and battery/privacy complexity without being required for decision integrity.

---

# 19A. Model and execution-provider architecture

## 19A.1 Provider/model neutrality

**Consolidated Decision:** KingMaker must not be architecturally locked to the small-model examples used in older v4 documentation.

The source architecture already defines a replaceable `AgentRuntimeAdapter` and records `runtime` and `model` in provenance. The production implementation should therefore treat providers and models as configuration/policy choices rather than domain dependencies.

## 19A.2 Model registry

The execution plane maintains a versioned model registry with:

- provider;
- model ID;
- task capabilities;
- structured-output support;
- tool support;
- context capacity;
- expected latency class;
- cost/quota metadata;
- availability status;
- safety/retention characteristics;
- adapter version.

## 19A.3 Task-aware routing

The router may select different model classes for different work:

```text
D2 context questions
   → efficient model class

D4 expert perspective
   → strong reasoning model class

D5 adversarial critique
   → strong/independent reasoning model class

D6 synthesis
   → strongest policy-eligible synthesis class

Evidence extraction / classification
   → specialized efficient class where appropriate
```

These are capability classes, not permanent provider names.

## 19A.4 Multiple-provider execution

For critical reasoning, policy may require independent provider/model diversity where justified. Provider diversity is a configurable risk-control technique, not a requirement for every run.

## 19A.5 Free/low-cost provider handling

Free or low-cost providers may be used where they satisfy the task policy. Availability, quota and quality are runtime facts and must be measured rather than assumed.

A “free” model is not automatically the default, and a paid model is not automatically preferred. The selection rule is capability × evidence requirement × task criticality × budget × policy eligibility.

## 19A.6 Live provenance

A result is labeled `PROVIDER` only when the configured provider adapter actually executed successfully.

Simulation/replay remains explicitly separate.

# 20. API and command architecture

All APIs use `/api/v1` and typed command contracts.

## 20.1 Required mutation headers

- `Authorization: Bearer ...`
- `Idempotency-Key`
- `X-Correlation-Id`
- revision ID where applicable;
- revision hash where applicable.

## 20.2 Core commands

```text
POST /workspaces
POST /workspaces/{workspaceId}/decisions
POST /decisions/{decisionId}/intake/submit
POST /decisions/{decisionId}/context/questions/generate
POST /decisions/{decisionId}/context/answers
POST /decisions/{decisionId}/framing/confirm
POST /decisions/{decisionId}/runs
POST /runs/{runId}/cancel
POST /decisions/{decisionId}/validations
POST /decisions/{decisionId}/approval
POST /decisions/{decisionId}/revisions
POST /decisions/{decisionId}/outcomes/observations
```

## 20.3 Consolidated cycle-resolution extensions

```text
GET  /decisions/{decisionId}/graph/cycles
GET  /decisions/{decisionId}/resolution-tasks
POST /decisions/{decisionId}/resolution-tasks/{taskId}/proposals
POST /decisions/{decisionId}/resolution-tasks/{taskId}/resolve
```

These are proposed extensions for the v5 contract set.

## 20.4 Error semantics

- `409 STALE_REVISION`
- `403 FORBIDDEN`
- `422 POLICY_FAILURE`
- `409 IDEMPOTENCY_CONFLICT` where a key is reused with incompatible request content;
- typed validation/schema errors;
- typed execution failure with retryability.

## 20.5 Model gateway envelope

```json
{
  "runId": "uuid",
  "taskId": "uuid",
  "decisionId": "uuid",
  "revisionId": "uuid",
  "revisionHash": "sha256",
  "taskClass": "EXPERT_PERSPECTIVE",
  "role": "SECURITY_ARCHITECT",
  "runtime": "adapter-name",
  "model": "provider-model",
  "promptVersion": "prompt-version",
  "policyVersion": "policy-version",
  "idempotencyKey": "uuid",
  "deadline": "RFC3339",
  "toolScopes": ["evidence.read"],
  "redactionProfile": "default",
  "payload": {}
}
```

The runtime response must include:

- schema version;
- provenance mode;
- provider/model;
- latency;
- usage/cost estimate;
- redaction status;
- trace ID;
- structured artifact.

No hidden chain-of-thought authority artifact is returned.

---

# 21. Event architecture

Every authoritative event contains:

- event ID;
- event type;
- aggregate type/id;
- sequence;
- workspace ID;
- revision ID/hash;
- actor;
- policy version;
- correlation ID;
- timestamp;
- schema version;
- payload hash;
- payload.

## 21.1 Atomic event/projection rule

Authoritative mutation must be atomic:

```text
validate command
   ↓
append immutable event
   +
update current read projection
   ↓
commit transaction
```

A partial event/projection update is forbidden.

## 21.2 Replay requirement

Projection rebuild must produce equivalent authoritative views from the event stream.

This becomes a release gate.

---

# 22. Persistence strategy

## 22.1 Server authority

PostgreSQL:

- authoritative decision state;
- revision metadata;
- claims/evidence metadata;
- runs/tasks;
- approvals;
- ADRs;
- blueprint projection records;
- outcomes;
- audit events;
- idempotency records.

## 22.2 Object storage

Encrypted object storage for:

- large evidence blobs;
- attachments;
- protected export/archive material.

Objects are content-hash addressed.

## 22.3 Queue

Queue is for delivery/work scheduling only.

It is not business truth.

## 22.4 Redis/equivalent

Only short-lived cache/rate-limit/ephemeral coordination data.

Never authoritative approval state.

## 22.5 Android local store

Local database contains:

- encrypted drafts;
- cached read models;
- command outbox;
- UI support data.

The local database may not mark a server approval as accepted.

---

# 23. Online-first behavior and continuity

## 23.1 Product posture

**Consolidated Decision:** KingMaker is **online-first**, not offline-first.

The full production workflow assumes active network access because authoritative commands, live model execution, cloud history, synchronization and governance confirmation depend on the backend.

The client still keeps a small local safety layer for resilience:

- draft persistence;
- short-lived cache;
- pending command outbox when connectivity temporarily drops;
- last-known read state with explicit freshness labels.

The system must never weaken or duplicate authoritative logic merely to achieve offline feature parity.

## 23.2 Degraded network behavior

When connectivity is lost:

- current cached/read data remains visibly marked as last-known;
- local drafts remain editable;
- pending non-governance commands may enter the outbox;
- approval cannot be represented as accepted locally;
- live/provider-backed analysis cannot be represented as completed without server confirmation;
- stale state is clearly surfaced.

## 23.3 Outbox record

```text
commandId
idempotencyKey
workspaceId
decisionId
revisionId
revisionHash
dependencyOrder
payload
createdAt
retryCount
lastServerResult
syncState
```

## 23.4 Conflict rule

A `409 STALE_REVISION` produces a visible conflict workflow.

The client never silently overwrites server state. The user may refresh, discard the local change, or explicitly create/merge a new revision through a governed workflow.

## 23.5 Account continuity and historical recovery

**Consolidated Decision:** the user’s history is durable independently of the Android APK installation.

The minimum continuity contract is:

```text
APK uninstall
   ↓
reinstall
   ↓
authenticate with same account
   ↓
resolve workspace identity
   ↓
load authoritative decision index/history
   ↓
load current projections and revision metadata
   ↓
continue from server state
```

The durable history must preserve, at minimum:

- decisions;
- revisions;
- analysis runs;
- perspectives and critiques;
- review packets;
- approvals;
- ADRs;
- blueprint versions;
- outcome observations;
- relevant audit/history metadata.

Application UI sessions are not themselves authoritative. The durable source is the decision/event history plus rebuildable projections.

## 23.6 Firebase continuity role

**Consolidated Decision:** Firebase may be retained as a **continuity/integration layer** consisting of:

- Firebase Authentication for account identity;
- Firestore or another Firebase-supported store for user-facing history/projection data only if security rules, tenancy, conflict semantics and consistency boundaries are formally verified;
- notification services where useful.

Firebase must never become the source of truth for:

- revision authority;
- approval authority;
- policy authority;
- authoritative ADR history;
- graph truth.

The authoritative backend remains responsible for domain commands and immutable decision history. Firebase data, where used, is a replicated/user-continuity projection.

## 23.7 History UX

A user returning after reinstall should be able to discover:

- current decisions;
- previous revisions;
- run history;
- review/approval history;
- superseded ADRs;
- blueprint changes;
- outcome history.

A history record must show whether it is authoritative current state, historical state, cached/stale state, simulated execution or provider-backed execution.

# 24. Security and privacy architecture

## 24.1 Threat model

Protect against:

- prompt injection;
- malicious attachments;
- tool poisoning;
- SSRF;
- provider compromise;
- cross-workspace access;
- replayed commands;
- stale approvals;
- local device theft;
- log exfiltration;
- malicious update packages.

## 24.2 Identity

Use OIDC-compatible authentication.

Every request resolves:

- user;
- workspace membership;
- object scope;
- authorization policy.

Route-only authorization is insufficient.

## 24.3 Secret handling

Platform provider credentials live in a managed secret store.

Client release artifacts contain no provider secrets.

Optional personal provider integrations, if enabled later, must use:

- encrypted device storage;
- Android Keystore-backed protection;
- HTTPS only;
- strict host allowlist;
- redaction;
- no URL query parameter secrets;
- no export/clipboard persistence.

## 24.4 Redaction

Before external model/tool calls, scan for:

- API keys;
- bearer tokens;
- private keys;
- email/phone patterns when configured sensitive;
- connection strings;
- configured secret patterns.

Use stable placeholders and preserve only necessary redaction metadata/hashes in telemetry.

## 24.5 External URLs

Repository and URL investigation requires:

- allowlist;
- DNS/SSRF protection;
- redirect validation;
- timeouts;
- read-only credentials;
- malware/content scanning where applicable.

External content is evidence, not authority.

## 24.6 Update security

No arbitrary custom APK URL installation.

Updates must verify:

- package/application identity;
- expected version;
- file size limit;
- digest;
- signing certificate continuity;
- trusted manifest/distribution source.

Debug APKs are never production releases.

---

# 25. Reliability and operations

## 25.1 Run execution

Runs are asynchronous.

Tasks use:

- leases;
- idempotent execution;
- checkpoints;
- attempt IDs;
- retry classes;
- timeouts;
- dead-letter states.

A dead-letter task cannot advance approval state.

## 25.2 Cancellation

Cancellation is explicit, revision-safe and observable.

A cancelled run remains in history and cannot be presented as a completed analysis.

## 25.3 Provider/runtime failure

Provider failure:

```text
run remains incomplete
→ task attempt records failure
→ retry if policy allows
→ otherwise failed/dead-letter
→ no approval transition
```

## 25.4 Backup/restore

Restore must use a staging workspace:

```text
restore snapshot
→ validate hashes
→ validate references
→ validate relationships
→ validate policy/schema
→ commit complete snapshot
```

Partial restore is discarded.

## 25.5 Observability

Protected telemetry records:

- request/command IDs;
- correlation/trace IDs;
- workspace ID;
- queue delay;
- task duration;
- retry class;
- provider/model;
- prompt version;
- schema version;
- redaction status;
- cost estimate;
- terminal reason.

Do not log raw prompts, attachments, secrets or approval rationale by default.

## 25.6 Pilot SLO policy

Do not invent production SLO targets before pilot measurement.

Measure first:

- API availability;
- command correctness;
- run completion;
- failed-task recovery;
- review-packet freshness;
- projection consistency;
- outbox delivery;
- stale-conflict rate.

Calibrate target values from observed pilot data.

---

# 26. Testing architecture

Testing is part of the architecture, not a post-build activity.

## 26.1 Domain/property tests

Verify:

- every legal transition;
- every illegal transition;
- stale revision rejection;
- duplicate idempotency;
- worker/tool denial of governance commands;
- approval binding;
- material revision creation;
- projection rebuild equivalence.

## 26.2 Evidence tests

Verify:

- raw input preservation;
- typed claim preservation;
- provenance cannot be upgraded in UI;
- valid evidence links;
- content hashes detect changes;
- redaction before external calls.

## 26.3 Adapter tests

Mock, replay and provider adapters must implement the same interface.

Verify:

- malformed output quarantine;
- missing revision linkage rejection;
- timeout;
- cancellation;
- retry;
- dead-letter;
- tool scope enforcement;
- prompt-injection isolation.

## 26.4 Persistence tests

Verify:

- atomic event + projection;
- crash recovery;
- concurrent stale write handling;
- migration correctness;
- outbox ordering;
- restore staging;
- tombstones/deletion semantics;
- workspace isolation.

## 26.5 Security tests

Verify:

- no secrets in release bundles;
- no secrets in logs/exports/URLs;
- workspace/object authorization;
- attachment access validation;
- external URL protections;
- update package verification.

## 26.6 Android acceptance

At approximately 375px width:

- D1-D3 complete without horizontal scroll;
- perspectives/critique/quality vector accessible with progressive disclosure;
- offline/stale/failed/permission states visible;
- approval rationale readable;
- blueprint graph has list/text alternative;
- touch targets ≥44dp;
- large-text accessibility preserved.

## 26.7 Release candidate gate

No release candidate is accepted merely because the happy path works.

Required:

- all P0 tests;
- complete happy path;
- at least one failed run + retry;
- stale conflict recovery;
- redaction;
- authorization denial;
- projection rebuild;
- approval revision binding;
- mobile degraded state acceptance.

---

# 27. Build, release and APK architecture

## 27.1 Android build

The native client uses Gradle.

CI should:

- run tests;
- build signed release artifact;
- perform dependency scan;
- perform secret scan;
- publish checksum/signature metadata;
- store artifacts with immutable version identifiers.

## 27.2 Release permissions

Normal jobs use least privilege.

Release publication requires only the permissions needed by a tag/release job.

Never grant repository write permissions to ordinary pull-request validation.

## 27.3 Personal APK distribution

For personal testing, use a trusted signed distribution channel or manually install a signed build.

In-app OTA may be added later only with cryptographic verification.

---

# 28. Repository architecture

Recommended monorepo shape from the v5 package:

```text
apps/
  mobile/
  api/
  worker/

packages/
  domain/
  contracts/
  policy/
  orchestration/
  integrations/
  ui/
  config/

infra/
  migrations/
  deploy/

docs/
  runbooks/
  generated-api/
```

For the current native Android client, the analogous client structure can be:

```text
app/
  ui/
  feature/
  navigation/
  data/
  network/
  local/
  security/
  sync/
  presentation-model/
```

The client code must not become a second implementation of the backend domain authority.

---

# 29. Configuration and environments

Configuration is environment-scoped.

Each environment defines:

- API base URL;
- OIDC configuration;
- allowed provider hosts;
- tool scopes;
- attachment limits;
- run budgets;
- retention policy;
- policy version;
- feature flags;
- update channel.

Secrets are references to secret-store entries, not source code values.

---

# 30. Implementation roadmap

## M0 — Contract and terminology freeze

Lock:

- status enum;
- command envelope;
- event envelope;
- canonical revision serialization;
- revision hash format;
- provenance modes;
- policy versioning;
- error codes;
- domain terminology.

**No feature implementation proceeds against unstable contracts.**

## M1 — Domain skeleton

Implement:

- decision aggregate;
- revisions;
- claims/evidence;
- commands;
- event store;
- idempotency;
- policy interface;
- authorization ports;
- projection rebuild.

Acceptance:

- stale-write rejection;
- transaction atomicity;
- replay equivalence.

## M2 — Mobile D1-D3

Implement:

- Inbox;
- Intake;
- Context question UX;
- Framing;
- encrypted drafts;
- command outbox.

No silent defaults.

## M3 — Bounded execution

Implement:

- queue;
- planner;
- task dispatch;
- mock runtime adapter;
- schema validation;
- redaction;
- checkpoints;
- retry;
- partial-progress UI.

The mock must be visibly simulated.

## M4 — Debate / critique / synthesis / quality

Implement:

- role registry;
- policy-based role selection;
- expert perspectives;
- Devil’s Advocate;
- Coverage Auditor;
- critique registry;
- quality vector;
- contextual policy profiles;
- bounded refinement.

## M5 — Review / approval / ADR / blueprint

Implement:

- validation plans;
- human review;
- approval binding;
- ADR creation;
- blueprint projection;
- projection rebuild.

## M6 — Guided cycle resolution + lenses

Implement:

- Tarjan SCC;
- cycle task deduplication;
- proposal generator;
- preview validation;
- human confirmation;
- immutable resolution event;
- five-lens projection;
- progressive disclosure.

## M7 — Production hardening

Implement:

- OIDC;
- tenancy/object authorization;
- object storage;
- backup/restore;
- observability;
- rate limits;
- security scanning;
- release signing;
- update verification;
- migration drills.

## M8 — Real provider adapters

Only after conformance tests pass:

- real model provider;
- scoped MCP read/propose tools;
- optional technical investigator.

## M9 — Optional integrations

A2A remains deferred until a concrete cross-agent delegation use case exists.

---

# 31. v4.1 migration strategy

The v4.1 repository must be treated as a **prototype and migration evidence source**, not as the authority to patch directly into production.

## 31.1 Freeze unsafe behaviors

Before migration:

- disable arbitrary OTA installation;
- disable client-side provider secrets;
- remove insecure Firebase fallback;
- disable fake “live Gemini” labels;
- disable approval from stale local state;
- remove demo seeding from production flow.

## 31.2 Import policy

Legacy v4.1 records enter v5 as `LEGACY_IMPORT` evidence/provenance.

Legacy records must not automatically become authoritative approved records merely because they had `APPROVED` in the old database.

## 31.3 Revision mapping

Where revision history is incomplete:

```text
legacy current state
    ↓
legacy import revision
    ↓
provenance = LEGACY_IMPORT
    ↓
validation required
```

## 31.4 Approval migration

An old approval is migrated as historical evidence unless its revision hash, review artifact and approval identity can be independently validated against v5 requirements.

Otherwise the system must require a new human review.

## 31.5 Existing Room database

The current Room schema can remain as a local prototype cache/outbox during transition, but it is not the v5 authoritative persistence model.

---

# 32. v4.1 defects that v5 must permanently prevent

The audit identified the following classes of defect; the v5 architecture explicitly prevents them:

### Security / integrity

- arbitrary APK installation without signature/hash verification;
- client-side plaintext model keys;
- unauthenticated Firebase fallback;
- partial/non-atomic cloud restore;
- live AI labels without live execution.

### Domain correctness

- event/projection updates not atomic;
- no revision/hash concurrency binding;
- illegal state transitions;
- cycle mutation without revision/event;
- duplicate resolution tasks;
- stale local approval state.

### Data correctness

- string-built JSON payloads;
- fabricated UI evidence;
- automatic demo data substitution;
- incomplete sync/tombstone semantics;
- unsafe export behavior.

### Architecture/maintainability

- domain logic concentrated in ViewModel;
- package/namespace mismatch;
- misleading README/build commands;
- unused AI dependencies without actual adapter execution;
- insufficient tests.

### Release

- debug APK published as production release;
- excessive CI permissions;
- unverified update path;
- unsafe backup defaults.

---

# 33. Features intentionally retained, adapted, deferred or rejected

## Retained

- Decision-first product identity;
- Minimal-Sufficiency Intelligence;
- D1-D7 workflow concept;
- adversarial validation;
- evidence provenance;
- dependency DAG;
- Tarjan SCC;
- five lenses;
- Living Blueprint;
- human approval;
- proportionality;
- Android-first UX;
- outcome tracking.

## Adapted

- CEO Gate → authorized human approval;
- DQS → quality vector;
- fixed two-round cap → policy-bounded depth;
- SQLite WAL → local draft/outbox, not authority;
- trust hierarchy → configurable policy;
- ADK/Genkit → adapters;
- MCP → scoped read/propose;
- Antigravity → optional read-only investigator;
- Firebase → identity + optional user-continuity/history/notification projection adapter if rules, tenancy and consistency semantics are verified; never authority.

## Deferred

- A2A federation;
- training/DPO/RLHF pipeline;
- autonomous execution;
- hidden model reasoning artifacts;
- multi-region active-active;
- floating overlay quick capture;
- broad agent marketplace.

## Rejected

- client-embedded provider secrets;
- arbitrary APK installation;
- unauthenticated cloud fallback;
- hardcoded passwords;
- model output treated as evidence without provenance;
- UI-generated `VERIFIED` claims;
- AI auto-approval;
- timeout-based default approval;
- “mathematical certainty” or zero-entropy claims;
- safety bypass/root override;
- authentication by dialect/style;
- unverified internal vendor shard assumptions.

---

# 34. Open architectural decisions that must be locked before production

These are intentionally explicit; they are not hidden assumptions.

1. **Identity provider:** exact OIDC provider and region.
2. **Deployment environment:** exact hosting stack for API/worker/PostgreSQL/object storage/queue.
3. **Personal model access:** backend-only in v1, or a carefully isolated direct personal-provider adapter.
4. **Quality calibration set:** real evaluation dataset for quality-vector calibration.
5. **Policy profiles:** exact profile IDs and coefficients after calibration.
6. **Critique Dimension Registry:** exact canonical 14-dimension list and version.
7. **Initial role registry:** which expert roles are supported in the first technical-architecture pilot.
8. **Retention/deletion policy:** workspace and evidence retention requirements.
9. **Backup RPO/RTO:** first personal/small-workspace objectives.
10. **Native client contract generation:** selected OpenAPI/JSON-schema codegen path.

These decisions do not invalidate the domain architecture; they define deployment and calibration details that must be frozen before production integration.

---

# 35. Additional consolidated decisions from Podcast + Gemini review

## 35.1 Contextual weighting

Adopted as a controlled policy mechanism, not dynamic self-modifying weights.

## 35.2 Guided cycle resolution

Adopted. AI proposes; human selects; domain validates; immutable event records the result.

## 35.3 Progressive disclosure

Adopted. Full evidence remains available; default UI exposes only the critical surface.

## 35.4 Single Activity

Adopted for native Android client UX architecture.

## 35.5 Coroutine-based background client work

Adopted as implementation guidance for responsiveness.

## 35.6 Biometric step-up

Adopted as optional local step-up verification, never as a substitute for domain authorization.

## 35.7 Native TTS

Deferred/optional UX enhancement.

## 35.8 Floating overlay

Deferred.

---

# 36. Definition of Done for the KingMaker v5 production vertical slice

The vertical slice is complete only if it can:

1. preserve raw input;
2. create revision-safe decision records;
3. ask only high-value context questions;
4. keep unknowns explicit;
5. confirm framing before analysis;
6. execute bounded, cancellable expert and critique tasks;
7. preserve revision/provenance on every artifact;
8. produce an explanatory quality vector;
9. apply a versioned deterministic policy;
10. reject stale commands;
11. reject duplicate idempotent commands;
12. reject unauthorized governance commands;
13. survive failed run + retry;
14. block approval if review artifacts are stale;
15. require current revision + policy + evidence + human rationale;
16. create immutable ADRs;
17. rebuild the Living Blueprint from authoritative history;
18. keep proposed/invalid graph edges quarantined;
19. handle cycle resolution through human-confirmed events;
20. preserve outcome observations separately from decision-time quality;
21. keep all provider execution honestly labeled;
22. pass security/redaction/update-verification tests;
23. pass offline/stale/failed/permission mobile states;
24. remain usable at 375px;
25. pass projection replay equality and migration/restore tests.

---

# 37. Final architectural invariants

These are the non-negotiable rules for implementation.

```text
I-01  AI never approves.
I-02  UI never approves by itself.
I-03  Worker/tool identities cannot mutate governance state directly.
I-04  Only the Decision Domain writes authoritative state.
I-05  Every revision has a canonical hash.
I-06  Every governed command is idempotent and correlation-traced.
I-07  Every revision-bound artifact references exactly one revision.
I-08  Stale revision writes fail closed.
I-09  Material edits create new revisions.
I-10  ADRs and approvals are immutable.
I-11  Blueprint is rebuildable projection, not source of truth.
I-12  Claims cannot be upgraded by rendering.
I-13  Model output without valid provenance is quarantined.
I-14  Simulated output is never labeled live.
I-15  No secret is shipped in the client.
I-16  External tools use declared scopes.
I-17  Cycle detection never silently mutates the graph.
I-18  Cycle resolution requires explicit human authorization.
I-19  Quality score/vector never equals automatic approval.
I-20  Outcome data never rewrites decision-time quality.
I-21  Offline state never masquerades as authoritative cloud state.
I-22  Update installation requires trusted artifact verification.
I-23  Failed execution never advances governance state.
I-24  Lens changes never alter underlying decision truth.
I-25  Adaptive policy must be versioned, deterministic and reproducible.
```

---

# 38. Traceability matrix: source → consolidated blueprint

| Source | What it contributes | Final treatment |
|---|---|---|
| v4.0 Final Architecture Blueprint | Decision-first philosophy, human governance, D1-D7, proportionality, MAD, 14D concept, DQS, Tarjan, 5 lenses, phases | Retained/adapted as historical design foundation |
| v4.0 Developer Handoff | 7-param DQS, PEL, Model Gateway Envelope, evidence tags, two-round cap, implementation phases | Retained as evidence; reconciled into v5 contracts/policy |
| v4.1 Repository | Actual native Android implementation | Retained as prototype/reference only |
| v4.1 Audit Report | Concrete critical/high/medium/low defects | Converted into mandatory prevention/acceptance controls |
| “KingMaker কোডবেস ও এপিকে মূল্যায়ন.pdf” | Trust boundary, append-only principle, DQS formula, Tarjan, API envelope, APK concerns | Used as technical audit/reference; Expo-specific claims superseded where v5 differs |
| v5 Master System Blueprint | Canonical v5 domain/authority/lifecycle/security/reliability | Primary target architecture |
| v5 Final Blueprint Package | Domain, architecture, UX, contracts, security, reliability, testing, implementation and evidence package | Primary implementation baseline |
| Podcast critique | Contextual weights, guided cycle proposals, progressive disclosure | Consolidated extensions |
| Gemini native Android review | Single Activity, Compose drill-down, UDF, background work, biometric/TTS ideas | Native client refinements; unsafe/overreaching parts rejected |
| Architecture visual | Overall v4 structure and capability relationships | Visual evidence/reference |
| v4 Mind Map | Decomposition and exploratory relationships | Supporting reference only; not canonical authority |

---

# 39. What is deliberately NOT claimed

This blueprint does **not** claim that the current v4.1 APK/repository already implements this system.

The current v4.1 audit explicitly found that the repository was prototype/demo-grade and contained critical integrity/security gaps, including a simulated rather than live AI execution path, client-side key exposure, unsafe update behavior, non-transactional event/projection handling and incomplete approval/revision semantics.

This document therefore defines the **target architecture** that the current prototype must converge toward or be replaced by.

---

# 40. Final system mental model

The simplest accurate mental model is:

```text
                    HUMAN
                      │
                      ▼
             ┌─────────────────┐
             │  DECISION       │
             │  DOMAIN         │
             │                 │
             │ revisions       │
             │ evidence        │
             │ policy          │
             │ quality         │
             │ approval        │
             │ ADR             │
             │ graph           │
             └───────┬─────────┘
                     │
          ┌──────────┴──────────┐
          ▼                     ▼
   EXECUTION PLANE        PROJECTIONS
   ├─ experts             ├─ mobile
   ├─ critique            ├─ review lenses
   ├─ synthesis           ├─ ADR
   ├─ provider adapters   ├─ blueprint
   └─ tools               └─ outcomes

          ▲
          │
   Trust / Security / Operations
```

The core principle is:

> **AI generates bounded analysis. The Domain decides what the system is allowed to believe, record, change and approve. Humans remain the final authority for material decisions.**

---

## Appendix A — Immediate implementation sequence for the current KingMaker-v4.1 project

1. Freeze v4.1 as prototype.
2. Remove/disable unsafe OTA, client API-key and insecure Firebase paths.
3. Create v5 backend/domain repository.
4. Freeze contracts and revision hashing.
5. Implement domain commands and events.
6. Implement policy engine and contextual policy snapshots.
7. Implement evidence/claim service.
8. Implement mock worker and run lifecycle.
9. Implement real revision-safe review and approval.
10. Implement ADR + blueprint projection.
11. Add cycle resolution protocol.
12. Add five lenses/progressive disclosure.
13. Port/adapt native Android UI to v5 contracts.
14. Add real provider adapter only after mock/conformance tests pass.
15. Add production security/reliability gates.
16. Migrate selected v4.1 data only as explicitly marked legacy evidence.

---

## Appendix B — Canonical vocabulary

| Term | Meaning |
|---|---|
| Decision Domain | Authoritative business/governance logic for decisions |
| Revision | Immutable material state of one decision |
| Policy | Deterministic rules controlling authorization, depth, evidence and approval |
| Execution Plane | Untrusted worker/model/tool execution boundary |
| Run | One bounded analysis execution for one revision |
| Artifact | Structured output produced by a run/task |
| Provenance | What produced/verified the artifact and under which runtime/policy |
| Quality Vector | Explainable multidimensional quality representation |
| ADR | Immutable accepted decision record |
| Blueprint Projection | Rebuildable graph/view derived from authoritative records |
| Resolution Task | Human-governed workflow created by graph cycle detection |
| Lens | Presentation projection over the same underlying evidence |
| Outbox | Offline queue of commands awaiting server processing |
| Idempotency | Guarantee that the same command key cannot cause duplicate material effect |
| Legacy Import | Historical data imported from pre-v5 systems without automatic trust upgrade |

---


# 41. Completeness and source-coverage gate

This consolidation was checked against the full v5 package inventory rather than only the headline master document.

## Reviewed v5 package surfaces

- governance and canonical decisions;
- package manifest;
- product requirements;
- domain specification;
- system architecture;
- Android-first UX specification;
- API/event contracts;
- JSON event envelope schema;
- OpenAPI contract;
- security/privacy specification;
- reliability/operations specification;
- acceptance matrix;
- implementation plan;
- all four Mermaid diagrams and corresponding rendered PNGs;
- evidence register;
- evidence adoption matrix;
- formal legacy gap analysis;
- v4.0 architecture PDF;
- v4.0 handoff PDF;
- v4.1 audit report;
- assumptions/open-questions document;
- README/master package index.

## Additional source material reconciled

- the separate “KingMaker কোডবেস ও এপিকে মূল্যায়ন.pdf” assessment;
- the two previously supplied visual v4 architecture/mind-map references;
- the CRITIC PODCAST architectural review;
- Gemini’s native Android engineering review;
- the latest v4.1 repository audit performed in this conversation.

## Completeness rule

No capability should be declared “implemented” merely because it exists in an architectural diagram, old PDF or generated blueprint. Implementation status must be proven by code, tests and execution provenance.

No requirement should be treated as obsolete merely because it was omitted from the v5 master file. It must be classified explicitly as retained, adapted, deferred, rejected or unresolved.

No missing 14-dimension list, numerical policy coefficient, deployment vendor, identity provider or calibration threshold is silently invented in this blueprint.

---

# 42. Final architecture lock statement

The consolidated architecture is therefore:

```text
KINGMAKER v5.1

                 HUMAN AUTHORITY
                       │
                       ▼
          ┌────────────────────────┐
          │ DECISION DOMAIN        │
          │                        │
          │ revisions              │
          │ evidence               │
          │ policy                 │
          │ quality                │
          │ state machine          │
          │ graph integrity        │
          │ approval               │
          │ ADR                    │
          │ outcome classification │
          └───────────┬────────────┘
                      │
          ┌───────────┴───────────┐
          ▼                       ▼
   EXECUTION PLANE          PROJECTION PLANE
   ├ expert roles           ├ Android views
   ├ critique               ├ 5 lenses
   ├ synthesis              ├ ADR
   ├ provider adapters      ├ Living Blueprint
   ├ MCP proposals         └ outcome views
   └ investigator
          │
          ▼
   TRUST / SECURITY / OPS
   ├ identity
   ├ authorization
   ├ redaction
   ├ idempotency
   ├ audit
   ├ observability
   ├ backup/restore
   └ signed delivery
```

**The system of record is the Decision Domain.**

**The AI system is an execution capability, not the authority.**

**The Android app is a client, not a second domain.**

**The Living Blueprint is a projection, not the historical truth.**

**Quality is evidence-aware and policy-bound, not mathematical certainty.**

**Human approval is explicit, authenticated, revision-bound and irreversible within the approved revision history.**

**Adaptive behavior is versioned and reproducible, never self-authorizing.**

**This is the architecture that the implementation should converge toward.**


# 43. Final reconciliation: what KingMaker is from the user’s point of view

KingMaker is not primarily an AI chat app, a score calculator, a graph viewer or an APK.

It is a **persistent decision workspace** that helps a human move from:

```text
“I have a technical problem and an incomplete idea.”

        ↓

“I now understand what decision I am actually making.”

        ↓

“I know which information is missing and which questions matter.”

        ↓

“I have a stable frame, real alternatives and explicit criteria.”

        ↓

“I have independent expert perspectives and visible disagreement.”

        ↓

“I have seen the strongest credible counter-case.”

        ↓

“I understand what evidence supports the recommendation and what could change it.”

        ↓

“I have enough validation to make an informed human decision—or I know why I should not decide yet.”

        ↓

“My approval is tied to exactly what I reviewed.”

        ↓

“The decision is preserved as an ADR and reflected in a rebuildable Living Blueprint.”

        ↓

“Months later I can reopen the complete history, see what was known then, see what actually happened, and decide whether a new revision is warranted.”
```

That complete loop is the intended product, while the Android application is the primary client used to operate it.

# 44. Final implementation lock

The implementation should proceed only when the following are treated as one coherent contract:

```text
USER JOURNEY
      +
DECISION REASONING
      +
DOMAIN AUTHORITY
      +
EVIDENCE / PROVENANCE
      +
POLICY / PROPORTIONALITY
      +
MODEL EXECUTION ADAPTERS
      +
HUMAN GOVERNANCE
      +
PERSISTENT HISTORY / ACCOUNT CONTINUITY
      +
ADR / BLUEPRINT PROJECTION
      +
OUTCOME LEARNING
      +
SECURITY / RELIABILITY / TESTING
      =
KINGMAKER
```

A component is not considered complete merely because its screen, class, endpoint or model adapter exists. It is complete only when its user-visible behavior, domain rule, persistence semantics, provenance, failure path and acceptance tests agree.

**End of preserved v5.2 source baseline.**
