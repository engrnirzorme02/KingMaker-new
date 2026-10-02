# 03 — System Architecture & Technology Stack

## 1. Runtime topology

```text
Android Client
   ↓ HTTPS authenticated commands/queries
API Role
   ↓ transactional domain commands
Decision Domain + Policy
   ├── PostgreSQL authority
   ├── transactional outbox/job rows
   └── projection/query state
        ↓ leased jobs
Worker Role
   ├── planner
   ├── role execution
   ├── model gateway
   ├── evidence/research adapters
   └── validation/quarantine
        ↓ structured artifacts
Decision Domain re-validation
        ↓
Review / ADR / Blueprint / Outcomes
```

## 2. Trust zones

1. Android device: untrusted client.
2. API boundary: authenticated and authorized command/query ingress.
3. Domain: authoritative business/governance trust zone.
4. Worker zone: untrusted execution relative to governance.
5. External providers/tools: lowest trust boundary.
6. Storage: encrypted persistence with separate authority responsibilities.

## 3. Repository layout

```text
kingmaker/
├── android/
│   ├── app/
│   ├── core-network/
│   ├── core-auth/
│   ├── core-local/
│   ├── core-design/
│   └── feature-*/
├── server/
│   ├── api/
│   ├── domain/
│   ├── policy/
│   ├── execution/
│   ├── persistence/
│   └── worker/
├── contracts/
├── migrations/
├── fixtures/
└── docs/
```

## 4. Technology choices

| Area | Choice |
|---|---|
| Android | Kotlin + Jetpack Compose |
| Android state | UDF + StateFlow |
| Android local | Room + encrypted storage; DataStore for settings |
| Background client | Kotlin coroutines / WorkManager for eligible sync work |
| API | Kotlin + Ktor |
| Server modules | modular monolith |
| Worker | same server image, worker role |
| DB | PostgreSQL |
| Large objects | encrypted object storage |
| Identity | Firebase Auth / Google Sign-In |
| Push | Firebase Cloud Messaging |
| AI | Model Gateway adapters |
| Serialization | Kotlinx Serialization + JSON Schema/OpenAPI-derived validation where appropriate |
| Hash | SHA-256 over RFC 8785 JCS |
| Queue | PostgreSQL jobs for personal deployment |
| CI | Gradle + automated tests + signed release pipeline |

## 5. Command flow

```text
authenticate
→ identify owner/workspace
→ idempotency check
→ load authoritative state
→ check revision precondition
→ evaluate policy
→ execute domain command
→ append event + update current state atomically
→ enqueue derived work if needed
→ return authoritative result
```

## 6. Worker flow

```text
claim job
→ verify job/run/revision still legal
→ reserve/validate budget
→ construct gateway envelope
→ redact
→ call adapter
→ schema validate
→ semantic validate
→ provenance record
→ accept or quarantine
→ domain command records result
```

## 7. Progress flow

The server stores durable run/task states. Android polls while a run is active. FCM may notify that a state changed. FCM payload contains identifiers/status hints only; the client fetches authoritative state.

## 8. Attachment flow

```text
upload metadata/request
→ object storage signed upload
→ server records object hash
→ scan/type validation
→ optional extraction
→ evidence excerpts become revision-bound evidence candidates
→ owner can attest/verify
```

## 9. Reference deployment

Personal reference profile:

- Google Cloud Run for API and worker roles;
- Neon PostgreSQL;
- Google Cloud Storage-compatible encrypted object storage;
- Secret Manager;
- Firebase Auth/FCM.

These are replaceable deployment choices, not architectural authority.

## 10. Environments

`dev`, `staging`, `prod` configurations are separate. Every environment explicitly defines API base URL, identity config, allowed provider hosts, tool scopes, attachment limits, run budgets, retention, policy version, feature flags and release channel.

Secrets are injected from secret management. No provider/API secret is committed to source or embedded in the APK.
