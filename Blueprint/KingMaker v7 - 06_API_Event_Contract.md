# 06 — API & Event Contract

## 1. API base

`/api/v1`

Mutation headers:

```http
Authorization: Bearer <token>
Idempotency-Key: <uuid-or-stable-key>
X-Correlation-Id: <uuid>
X-Causation-Id: <uuid, optional>
```

Revision-bound commands include `revisionId` and `revisionHash`.

## 2. Canonical command envelope

```json
{
  "commandId": "uuid",
  "commandType": "FramingConfirmed",
  "workspaceId": "uuid",
  "decisionId": "uuid",
  "actor": {"type": "HUMAN", "id": "uuid"},
  "revisionId": "uuid",
  "revisionHash": "sha256-hex",
  "idempotencyKey": "uuid",
  "correlationId": "uuid",
  "clientSchemaVersion": "7.0",
  "payload": {}
}
```

## 3. Canonical event envelope

```json
{
  "eventId": "uuid",
  "eventType": "GovernanceActionRecorded",
  "aggregateType": "Decision",
  "aggregateId": "uuid",
  "sequence": 42,
  "workspaceId": "uuid",
  "revisionId": "uuid",
  "revisionHash": "sha256-hex",
  "actor": {"type": "HUMAN", "id": "uuid"},
  "policyVersion": "pv-2026-01",
  "correlationId": "uuid",
  "causationId": "uuid",
  "timestamp": "RFC3339",
  "schemaVersion": "7.0",
  "payloadHash": "sha256-hex",
  "payload": {}
}
```

## 4. Core endpoints

```text
POST /workspaces
GET  /workspaces/{workspaceId}
POST /workspaces/{workspaceId}/decisions
GET  /decisions/{decisionId}
POST /decisions/{decisionId}/intake/submit
POST /decisions/{decisionId}/questions/generate
POST /decisions/{decisionId}/context/answers
POST /decisions/{decisionId}/framing/confirm
POST /decisions/{decisionId}/runs
GET  /runs/{runId}
POST /runs/{runId}/cancel
GET  /decisions/{decisionId}/review-packet
POST /decisions/{decisionId}/validations
POST /decisions/{decisionId}/governance-action
POST /decisions/{decisionId}/revisions
POST /decisions/{decisionId}/evidence
GET  /decisions/{decisionId}/history
GET  /decisions/{decisionId}/graph
GET  /decisions/{decisionId}/resolution-tasks
POST /decisions/{decisionId}/resolution-tasks/{taskId}/proposals
POST /decisions/{decisionId}/resolution-tasks/{taskId}/resolve
POST /decisions/{decisionId}/outcomes/observations
GET  /decisions/search?q=...
GET  /health
GET  /version
```

## 5. Error contract

```json
{
  "errorCode": "STALE_REVISION",
  "message": "Authoritative state changed.",
  "retryable": false,
  "correlationId": "uuid",
  "details": {"currentRevisionId":"uuid","changedPaths":[]}
}
```

Core codes:

`UNAUTHENTICATED`, `FORBIDDEN`, `NOT_FOUND`, `STALE_REVISION`, `IDEMPOTENCY_CONFLICT`, `POLICY_FAILURE`, `VALIDATION_ERROR`, `PROVENANCE_INVALID`, `QUOTA_EXCEEDED`, `RUN_NOT_CANCELLABLE`, `CONFLICT_REQUIRES_REVIEW`, `RATE_LIMITED`, `TEMPORARY_FAILURE`.

## 6. Event semantics

Events are facts about authoritative mutations. They are not model messages.

An event has one aggregate sequence. Replays must reproduce equivalent projections.

## 7. Command consistency

Every mutation follows:

```text
authenticate
→ authorize
→ idempotency
→ revision precondition
→ policy
→ domain command
→ atomic state + event
→ response
```

## 8. Pagination and freshness

Collection queries return stable cursors and `serverAsOf` timestamps. Mobile caches display freshness and never infer current authority from local timestamps alone.

## 9. Versioning

API versioning is explicit. Schema changes use additive evolution where possible. Removing/renaming a field requires a new contract version and migration plan. Golden contract fixtures are maintained for command/event compatibility.
