# 10 — Android Client Engineering

## 1. Architecture

Use a single Activity and Compose UI with unidirectional data flow.

```text
Compose UI
  ↓ events
ViewModel / StateHolder
  ↓
Use Case
  ↓
Repository
 ├── Remote API
 └── Local Store
```

The repository decides whether the UI is showing authoritative, cached or draft state. UI code cannot directly mutate domain truth.

## 2. Feature modules

Recommended feature boundaries:

`feature-inbox`, `feature-capture`, `feature-context`, `feature-framing`, `feature-run`, `feature-review`, `feature-history`, `feature-blueprint`, `feature-outcomes`, `feature-settings`.

## 3. Local persistence

Room stores:

- working drafts;
- local cache/read models;
- eligible pending commands;
- conflict copies.

DataStore stores user/device preferences.

Secrets/refresh tokens use platform-secure storage and auth-provider mechanisms.

## 4. Outbox

Outbox records contain:

`commandId`, `commandType`, `payload`, `baseRevisionId/hash`, `createdAt`, `attempts`, `status`, `lastError`.

Outbox excludes authority-sensitive commands:

- seal revision;
- start live run;
- governance action;
- cycle resolution confirmation;
- policy activation.

Those require live server confirmation.

## 5. Sync

```text
local draft
→ connectivity restored
→ authenticate
→ fetch current revision
→ compare base revision
→ push if current
→ otherwise semantic conflict flow
```

Timestamp alone never resolves material conflict.

## 6. Server freshness

Every read model has `serverAsOf` or equivalent freshness metadata. Local state is labeled `LAST_KNOWN`, `DRAFT_LOCAL`, or `SYNC_PENDING` as appropriate.

## 7. Network and retries

Use bounded retry with exponential backoff for transient requests. Idempotency keys protect repeat commands. Retry must never turn a failed governance command into a second governance event.

## 8. Security

- no model/API provider secrets in resources or BuildConfig;
- certificate/network security configured appropriately;
- logs scrubbed in release builds;
- debug-only simulated mode isolated from production;
- exported ADR/evidence follows user privacy settings.

## 9. Build

Gradle CI pipeline:

```text
lint/static checks
→ unit tests
→ contract tests
→ instrumented tests
→ fixture/conformance tests
→ security checks
→ assemble signed artifact
→ verify signature/hash
→ publish release metadata
```

## 10. App update

The app must not implement arbitrary unsigned in-app APK installation. Distribution uses a trusted release channel. Update metadata and artifact signatures are verified.

## 11. Deep links and notifications

Deep links identify entities only. On open, the app fetches current authoritative state and checks access before showing detail.

## 12. Accessibility / performance

Use stable keys, lazy lists for long histories, avoid blocking main thread, preserve state through configuration/process recreation where practical, and provide accessible content descriptions and semantics.
