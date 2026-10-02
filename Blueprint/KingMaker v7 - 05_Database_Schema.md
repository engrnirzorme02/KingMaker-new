# 05 — Database Schema (PostgreSQL)

## 1. Schema principles

- PostgreSQL is the authoritative state store.
- Current state and immutable event history are written in one transaction.
- Sealed revisions, approvals, ADRs and governance events are append-only.
- Deletes are governed; immutable history is never casually deleted.
- `workspace_id` is present for future multi-user support even in single-owner Personal Edition.
- Every revision-bound artifact stores both revision ID and revision hash.

## 2. Core tables

```text
workspaces
workspace_members
workspace_policies
policy_versions
owner_allowlist

questions
context_answers

decisions
decision_revisions
decision_drafts
claims
evidence_items
claim_evidence_links
options
criteria
trade_offs
decision_dependencies

runs
run_tasks
job_attempts
artifacts
agent_perspectives
critiques
synthesis_results
quality_results
review_packets
validation_plans
validation_results

approvals
adrs
blueprint_nodes
blueprint_edges
resolution_tasks
resolution_proposals

outcome_references
outcome_observations

models
prompt_versions
integrations
attachments

domain_events
audit_events
idempotency_records
jobs
cost_ledger
```

## 3. Essential column contract

### decisions

`id UUID PK`, `workspace_id UUID FK`, `status`, `title`, `current_revision_id UUID`, `created_at`, `updated_at`, `archived_at`, `schema_version`.

### decision_revisions

`id UUID PK`, `decision_id FK`, `revision_number`, `canonical_json JSONB`, `revision_hash CHAR(64)`, `sealed_at TIMESTAMPTZ`, `parent_revision_id`, `policy_version_id`, `schema_version`.

Unique: `(decision_id, revision_number)`, `(decision_id, revision_hash)`.

### approvals

`id`, `decision_id`, `revision_id`, `revision_hash`, `packet_id`, `packet_hash`, `evidence_snapshot_hash`, `policy_version_id`, `policy_hash`, `selected_option_id`, `action`, `rationale`, `step_up_method`, `server_time`, `actor_user_id`, `created_at`.

### domain_events

`id UUID`, `aggregate_type`, `aggregate_id`, `sequence BIGINT`, `workspace_id`, `revision_id`, `revision_hash`, `actor_type`, `actor_id`, `policy_version`, `correlation_id`, `causation_id`, `event_type`, `schema_version`, `payload_hash`, `payload JSONB`, `created_at`.

Unique: `(aggregate_id, sequence)` and `id`.

## 4. Immutability rules

Database triggers/restricted permissions prevent update/delete of:

- decision_revisions;
- approvals;
- adrs;
- governance event records;
- accepted artifacts/provenance records;
- canonical outcome observations.

Corrections create new records/revisions rather than modifying history.

## 5. Idempotency

`idempotency_records` stores:

`key`, `actor_id`, `command_name`, `request_hash`, `result_reference`, `status`, `created_at`, `expires_at`.

Same key + same request hash returns the original result. Same key + different request hash is `IDEMPOTENCY_CONFLICT`.

## 6. Job queue

`jobs` includes:

`id`, `job_type`, `aggregate_id`, `revision_id`, `payload`, `status`, `available_at`, `attempt_count`, `lease_owner`, `lease_until`, `last_error`, `created_at`.

Lease query uses `FOR UPDATE SKIP LOCKED`. A worker must re-check revision/run legality after leasing.

## 7. Cost ledger

Records reserved, consumed and released budget units per run/task/provider. A reservation is atomically checked against per-run and monthly limits before dispatch.

## 8. Indexes

Required indexes include:

- decisions by workspace/status/updated_at;
- revision by decision/number/hash;
- events by aggregate/sequence and correlation;
- evidence by workspace/trust/validUntil;
- jobs by status/available_at;
- tasks by run/status;
- outcomes by reviewAt;
- ADRs by workspace/number/status;
- full-text indexes introduced in R2.

## 9. Transaction boundary

Governed command transaction:

```text
authorize
→ idempotency check
→ load aggregate
→ validate revision
→ evaluate policy
→ mutate current state
→ append immutable event
→ enqueue derived durable job if required
→ commit
```

No event may commit without its corresponding state change, and no authoritative state change may commit without its event.

## 10. Backup/restore

Encrypted backups must preserve database integrity, event history and object references. Restore drills verify projection rebuild and hash consistency before a restored deployment is declared healthy.
