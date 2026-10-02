# 01 — Product Requirements

## 1. Product statement

KingMaker is a persistent, human-governed Decision Intelligence Workspace for technical and architectural decisions. It turns a messy problem into a framed decision, asks only consequential questions, executes bounded analysis, exposes dissent and uncertainty, presents a revision-bound review packet, records a human governance action, publishes an immutable ADR and later compares expected versus observed outcomes.

## 2. Primary user — Personal Edition

- one owner;
- Android phone as primary client, 360dp+ target;
- Bangla, English and mixed/Banglish input;
- mostly online with intermittent mobile-network loss;
- sole approval authority;
- multi-year history across reinstalls/devices;
- visible AI and infrastructure budget.

Schema retains `workspace_id` to avoid blocking future multi-user expansion, but v1 authorization is single-owner.

## 3. Jobs to be done

1. Capture a technical thought quickly.
2. Clarify the real decision and epistemic status of its contents.
3. Ask less: answer only high-consequence questions.
4. Challenge the preferred option with relevant independent perspectives and adversarial critique.
5. Decide safely with exact review binding.
6. Remember and audit decisions later.
7. Understand cross-decision dependencies.
8. Control AI cost.

## 4. Core user loop

```text
Capture
→ Provisional Interpretation
→ Consequential Questions
→ Framing
→ Readiness Gate
→ Bounded Run
→ Review Packet
→ Governance Action
→ ADR
→ Living Blueprint
→ Outcome Observation
→ New Revision / Policy Proposal when justified
```

## 5. Release scope

| Capability | R1 | R2 | R3 |
|---|:---:|:---:|:---:|
| Google sign-in + owner allowlist | ✓ | | |
| Text/voice/share capture | ✓ | | |
| Interpretation + context questions + framing | ✓ | | |
| Bounded run + mock + one real provider | ✓ | more providers | model diversity |
| Quality vector + CDR-P1 | ✓ | | |
| Review packet + core lenses | ✓ | all five lenses | |
| Governance + step-up | ✓ | | |
| Immutable ADR + Markdown export | ✓ | PDF/evidence package | |
| Blueprint list + simple graph | ✓ | cycle management/what-if | |
| Evidence notes + URLs | ✓ | attachments/extraction | |
| History/revisions/diff | ✓ | full-text search | |
| Encrypted draft/outbox | ✓ | semantic reconciliation | |
| Outcome review | | ✓ | |
| Cross-decision dependency/cycles | | ✓ | |
| Policy learning | | | ✓ |
| Model golden-fixture evaluation | | | ✓ |
| Quick capture widget/TTS | | | ✓ optional |

## 6. Functional requirements

- Raw capture is preserved verbatim.
- AI interpretation is provisional until user acceptance.
- Consequential questions support `ANSWERED`, `UNKNOWN`, `NOT_APPLICABLE`, `DEFERRED`.
- Framing includes objective, non-goals, stakeholders, constraints, preferences, success criteria, evaluation criteria, options, rejected alternatives, dependencies, validation budget and revisit triggers.
- Readiness blockers have direct fix actions; no silent “continue anyway.”
- Run setup exposes tier, mandatory roles, rationale/mandate for each role, estimated cost/time and provenance mode.
- Run monitor exposes task progress, spend and cancellation.
- Review exposes recommendation, strongest alternative, critical findings, quality, risks, validation state, dissent and “what could change this?”.
- Governance actions are `APPROVE`, `REJECT`, `DEFER`, `REQUEST_REVISION`.
- Approval requires exact revision/packet/evidence/policy binding and configurable step-up; Personal Edition defaults to biometric/device credential when available.
- Approved decisions produce immutable ADRs and Blueprint projections.
- Mock execution is visibly marked `SIMULATED` everywhere.
- Offline mode is limited to drafts, cache and eligible outbox commands.
- Approval and live runs require connectivity.
- Cost budgets have pre-dispatch reservation and hard stop.

## 7. Non-functional requirements

- 375px usability target.
- Server authoritative for governance and current state.
- All material writes idempotent.
- Stale writes fail closed.
- Full history survives reinstall when identity is restored.
- Projection rebuild must reproduce equivalent views.
- No provider key in APK.
- Release artifacts signed and verified.
- Logs must not expose secrets or private reasoning.

## 8. Explicit non-goals

No autonomous approval, autonomous deployment, unrestricted infrastructure control, autonomous software-company behavior, A2A federation, agent marketplace, multi-user collaboration, Firestore-as-authority, or storage of hidden chain-of-thought.
