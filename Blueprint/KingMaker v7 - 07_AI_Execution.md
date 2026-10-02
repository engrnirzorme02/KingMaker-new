# 07 — AI Execution, Model Gateway & Prompts

## 1. Execution principle

The AI system is an **execution capability**. It produces bounded structured artifacts that the Decision Domain validates and records.

## 2. Run envelope

```json
{
  "runId": "uuid",
  "taskId": "uuid",
  "decisionId": "uuid",
  "revisionId": "uuid",
  "revisionHash": "sha256",
  "taskClass": "EXPERT_PERSPECTIVE",
  "role": "SECURITY_ARCHITECT",
  "runtime": "gateway",
  "model": "provider:model",
  "promptVersion": "prompt-SECURITY-003",
  "policyVersion": "pv-2026-01",
  "provenanceMode": "PROVIDER",
  "idempotencyKey": "uuid",
  "deadline": "RFC3339",
  "toolScopes": ["evidence.read"],
  "redactionProfile": "default",
  "payload": {}
}
```

## 3. Provenance modes

### SIMULATED
Deterministic mock output for development and conformance. Must be visibly labeled.

### REPLAY
Output reproduced from a stored golden fixture/replay artifact. Used for testing and evaluation.

### PROVIDER
Real external model execution. Provider execution can fail; it cannot silently downgrade to simulated.

## 4. Capability classes

- `EFFICIENT_STRUCTURED`: extraction, classification, small transformations.
- `STRONG_REASONING`: expert perspectives, red-team critique, difficult analysis.
- `STRONGEST_SYNTHESIS`: final synthesis where policy permits.

Routing is policy-bound and versioned.

## 5. Role registry

Examples:

- Product/Problem Framer
- Solution Architect
- Security Architect
- Reliability/Operations Architect
- Data Architect
- UX/Interaction Specialist
- Cost/FinOps Analyst
- Devil's Advocate
- Coverage Auditor
- Synthesis Lead

Each role has a mandate, forbidden behaviors, input contract and output schema.

## 6. Execution stages

```text
PERSPECTIVES
→ DEVIL'S ADVOCATE
→ COVERAGE AUDITOR
→ SYNTHESIS
→ optional bounded REFINEMENT
→ REVIEW PACKET
```

Independent perspectives are generated before synthesis. Refinement is capped by policy, cost and deadline.

## 7. Output artifact contract

Each artifact includes:

```text
artifactId
schemaVersion
runId/taskId
revisionId/revisionHash
artifactType
provenanceMode
producerRole
runtime/model
promptVersion
policyVersion
createdAt
contentHash
structuredPayload
validationStatus
```

## 8. Validation gate

```text
raw provider output
→ parse/schema validation
→ semantic validation
→ provenance validation
→ security/redaction checks
→ accept OR quarantine
```

Semantic validation includes reference integrity, allowed enum values, revision binding, required fields, unsupported-claim checks and role/task compatibility.

## 9. Prompt contract

Prompts are versioned artifacts. A prompt version records:

- purpose;
- system constraints;
- input schema;
- output schema;
- tool scopes;
- redaction profile;
- model capability requirements;
- evaluator/fixture IDs;
- activation status.

Prompt changes do not mutate historical runs.

## 10. Injection defense

External text is untrusted data. The execution layer distinguishes system instructions, decision context, evidence content and tool output. Retrieved text cannot override system/policy instructions. Tool results cannot define new scopes. URLs and attachments are validated before retrieval/processing.

## 11. Model Gateway

```text
ModelGateway
├── capability registry
├── model registry
├── routing policy
├── quota/cost estimator
├── provider adapters
├── timeout/retry policy
└── provenance recorder
```

Personal Edition adapters may include Mock, Replay, Gemini, OpenAI-compatible and Anthropic-compatible implementations. Adapter availability is deployment configuration; domain code imports only gateway interfaces.

## 12. Tool / MCP policy

Tools are scoped by capability and operation:

`evidence.read`, `url.fetch`, `repo.read`, `analysis.propose`, etc.

Read and propose are preferred over mutation. Any structural mutation must become a domain command subject to the same authorization and revision rules as a human edit.

## 13. Cost governance

Planner estimates cost, reserves it, then dispatches tasks. Actual usage settles the ledger. Reservation failure prevents dispatch.

Stops include budget exhaustion, monthly cap, deadline, task limit, non-improvement, provider failure, governance blocker and insufficient evidence.

## 14. Observability

Capture run/task IDs, latency, provider/model, token/usage estimates, error class, validation state and cost. Do not capture hidden chain-of-thought or raw secrets.
