# 12 — Testing, Golden Fixtures & Acceptance

## 1. Testing philosophy

A feature is complete only when behavior, domain rule, persistence semantics, provenance, failure path and UI state agree.

## 2. Test layers

```text
unit
→ domain/state machine
→ database/transaction
→ contract/event
→ worker/execution
→ model gateway/conformance
→ Android UI/instrumentation
→ end-to-end
→ security
→ restore/replay
```

## 3. Golden fixtures

Golden fixtures cover at least:

- raw intake with mixed Banglish;
- interpretation with unknowns;
- consequential question ranking;
- framing seal;
- expert artifacts;
- adversarial critique;
- synthesis packet;
- quality vector;
- stale approval;
- simulated vs provider provenance;
- schema-invalid provider output;
- semantic conflict;
- cycle detection/resolution;
- deterministic ADR rendering.

Replay mode must reproduce the fixture exactly or within an explicitly defined normalized equivalence rule.

## 4. Domain invariants

Automated tests must prove:

- AI/worker cannot invoke governance authority;
- stale writes are rejected;
- revision hashes are deterministic;
- material edits create revisions;
- approval is snapshot-bound;
- immutable records cannot be updated/deleted;
- failed runs do not advance governance;
- policy versions remain historical;
- outcome data does not rewrite t0 quality;
- offline mode cannot claim authority;
- simulated runs cannot be labeled live;
- provider runs never silently become mock;
- lenses do not alter truth.

## 5. Transaction tests

For every authoritative command verify atomicity under failure:

```text
state update + event + derived job
```

must commit together or roll back together according to the command contract.

## 6. Queue tests

Test concurrent workers, lease expiry, retries, duplicate delivery, dead-letter behavior and stale revision detection after lease acquisition.

## 7. Contract tests

Every API/event schema is checked against stored golden fixtures. Breaking changes require explicit versioning/migration.

## 8. AI conformance

For every provider/model candidate:

```text
schema conformance
→ role/task conformance
→ injection/refusal tests
→ provenance correctness
→ cost/latency measurement
→ regression fixture comparison
→ policy activation
```

## 9. Security acceptance

Minimum tests include unauthorized workspace read, forged owner, replayed governance command, stale approval, malicious URL, unsafe attachment, prompt injection attempt, secret exposure scan and unsigned update rejection.

## 10. Android acceptance

At minimum on supported narrow devices:

- capture works online and degraded;
- draft survives process death where intended;
- outbox does not create duplicate commands;
- stale conflict is understandable;
- review packet is usable at 375px;
- governance sheet exposes exact binding;
- cached state is visibly stale when offline;
- notifications deep-link safely.

## 11. Release candidate gate

No RC unless all P0 tests pass and the suite demonstrates:

1. complete happy path;
2. failed run + retry;
3. stale conflict recovery;
4. authorization denial;
5. provenance/quarantine;
6. approval revision binding;
7. deterministic ADR rendering;
8. projection rebuild;
9. backup/restore verification;
10. mobile degraded state acceptance.

## 12. Definition of Done for v7 vertical slice

A vertical slice is complete when the same decision can be captured, framed, run in SIMULATED mode, reviewed, governed, rendered into an ADR and projected into the Blueprint with every step traceable to the correct revision, policy and event sequence, while all P0 invariants remain true.
