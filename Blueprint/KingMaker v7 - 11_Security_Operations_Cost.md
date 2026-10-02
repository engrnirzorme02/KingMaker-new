# 11 — Security, Privacy, Operations & Cost

## 1. Threat model

Primary threats:

- account takeover;
- unauthorized workspace access;
- prompt injection;
- malicious attachments;
- SSRF;
- tool poisoning;
- provider compromise;
- stale/replayed approval;
- client tampering;
- log leakage;
- malicious release/update;
- budget exhaustion.

## 2. Identity and authorization

Firebase Auth authenticates. Server policy authorizes. Personal Edition checks owner allowlist before any workspace command.

Authentication alone never grants governance authority.

## 3. Secrets

Provider keys, database credentials and signing material live in secret management or CI secret stores. APK contains no provider credential.

## 4. Data protection

Encrypt data in transit and at rest. Large attachments use encrypted object storage. Keep evidence and audit retention deliberately scoped. Support export and deletion workflows subject to immutable-history policy and documented legal/operational retention requirements.

## 5. Prompt injection controls

- mark external content untrusted;
- separate instructions from evidence text;
- do not allow evidence to grant tool scopes;
- validate URLs/hosts;
- constrain tool operations;
- validate structured output;
- quarantine suspicious outputs;
- never treat model-produced authorization as authorization.

## 6. Attachment safety

Validate declared/actual MIME type, size, content hash and extraction pathway. Scan where appropriate. Extracted text is evidence content, not instructions.

## 7. Object authorization

Every object read/write is authorized against workspace ownership/membership. IDs alone never imply access.

## 8. Replay/stale protection

Use idempotency keys, revision hashes, server time, approval snapshot hashes and unique event sequences.

## 9. Observability

Track:

- request latency;
- worker queue age;
- task failure rate;
- provider errors;
- cost/reservation/settlement;
- stale conflicts;
- quarantine rate;
- approval failures;
- backup/restore status.

Logs must contain correlation IDs and avoid secrets/private reasoning.

## 10. Backup and recovery

Define and test RPO/RTO suitable for personal use. Database backups and object-store references must be restorable together. After restore:

```text
restore
→ integrity check
→ event/hash verification
→ projection rebuild
→ golden consistency checks
→ service health
```

## 11. Release security

Release builds are signed. CI verifies source/test gates before signing. Update metadata includes expected artifact hash/signature. Unsigned/untrusted update packages are rejected.

## 12. Cost governance

Reference Personal Edition planning budget:

- T1 around `$0.10/run`;
- T2 around `$0.50/run`;
- T3 around `$2/run`;
- monthly AI cap around `$20`;
- infrastructure target around `$15/month`.

These are **planning defaults**, not universal pricing promises. Actual provider prices are external configuration and must be refreshed in deployment configuration.

Cost controls:

```text
estimate
→ reserve
→ dispatch
→ settle actual
→ release unused reserve
```

Monthly hard cap stops new eligible expensive work. Existing governance history remains accessible.

## 13. Incident response

At minimum: provider outage, credential leak, unauthorized access suspicion, corrupted projection, queue poison, malicious artifact, compromised release. Each incident has containment, evidence preservation, rotation/revocation and recovery steps.
