# 08 — Policy, Proportionality & Quality Vector

## 1. Policy principle

Policy determines **how much decision support is justified**. It is deterministic, versioned, reproducible and bound to the revision/run that used it.

```text
context
→ impact / reversibility
→ policy profile
→ required evidence
→ roles
→ run depth
→ quality requirements
→ validation requirements
→ approval eligibility
```

AI may recommend a policy profile; only deterministic policy code applies it.

## 2. Personal Edition tiers

### T1 — Low consequence / highly reversible

Small role set, shallow critique, lower evidence burden, low cost ceiling.

### T2 — Material technical consequence

More perspectives, explicit adversarial pass, stronger evidence and validation requirements.

### T3 — High impact / low reversibility / sensitive

Strongest eligible synthesis, expanded critique, explicit pre-mortem, stronger validation, higher approval friction and stricter cost reservation.

Exact numeric thresholds are configuration, not hidden model behavior.

## 3. Quality Vector

Canonical dimensions:

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

Every dimension stores:

- normalized value;
- explanation;
- calculation inputs;
- policy version;
- relevant evidence/claim references where applicable.

A composite summary may be shown as a convenience view but never determines approval alone.

## 4. CDR-P1 — KingMaker Personal 11-Dimension Critique Registry

**V7 Consolidated Decision:** `CDR-P1` is an explicit 11-dimension Personal Edition registry. It is a new, versioned production registry derived from the v5/v6 role set and decision-intelligence goals. It is **not** presented as the missing legacy v4 “14D” list.

### CDR-P1 dimensions

| ID | Dimension | Core question | Typical role sources |
|---|---|---|---|
| CDR-01 | Problem & Goal Integrity | Are we solving the right decision and outcome? | Framer, Product Analyst |
| CDR-02 | Context & Constraint Integrity | Are material facts, constraints, assumptions and unknowns explicit? | Framer, Domain Architect |
| CDR-03 | Option & Trade-off Coverage | Are meaningful alternatives and trade-offs represented? | Solution Architect, Product Analyst |
| CDR-04 | Technical Feasibility & Architecture Fit | Can the proposed option actually work within the stated architecture? | Software Architect, Developer Reviewer |
| CDR-05 | Data & Integration Integrity | Are data flows, interfaces, dependencies and migration effects coherent? | Data Architect, Integration/Developer Reviewer |
| CDR-06 | Security, Privacy & Trust Boundary | Are security/privacy risks and trust-boundary violations addressed? | Security Architect, Governance Specialist |
| CDR-07 | Reliability, Operations & Failure Modes | What happens under failure, degradation, recovery and operational load? | Reliability/Ops, Migration Specialist |
| CDR-08 | Scalability, Performance & Complexity | Does the option remain sustainable as usage, data and system complexity grow? | Software Architect, Reliability/Ops |
| CDR-09 | Cost & Resource Sustainability | Are recurring, one-time and operational costs proportionate to value? | Business/Product Analyst, FinOps-oriented reviewer |
| CDR-10 | UX, Human Factors & Adoption | Can the affected humans understand, operate and recover from the system behavior? | UX/Human Factors, Product Analyst |
| CDR-11 | Evidence, Governance & System Integrity | Is the decision evidence-aware, policy-compliant, auditable and boundary-safe? | Coverage Auditor, Governance Specialist, Devil's Advocate |

Each dimension is versioned and defines:

`id`, `name`, `mandate`, `severity mapping`, `evidence expectation`, `role sources`, `required artifact fields`, `enabled_from`, `enabled_to`.

### Legacy 14D handling

The older material references a legacy 14-dimension concept and identifies System Integrity as its 14th dimension, but the complete historical list is not present in the canonical v6.0 source. Therefore v7 does not pretend to have reconstructed that list. `CDR-P1` is the explicit Personal Edition registry instead. A future verified legacy registry may be imported as a separate `LEGACY-14D` version and compared without silently replacing CDR-P1.

## 5. Coverage Auditor

Coverage auditing asks whether the review packet contains the dimensions, claims, options, evidence and validation that policy requires. It does not create arbitrary objections merely to increase output volume.

## 6. Devil's Advocate

The adversarial stage must attack:

- key assumptions;
- evidence weaknesses;
- alternative feasibility;
- hidden coupling;
- reversibility assumptions;
- failure modes;
- cost and operational implications.

It must produce falsifiable objections and cite the underlying claim/criterion where applicable.

## 7. Proportionality inputs

Canonical decision factors:

- impact;
- reversibility;
- uncertainty/evidence weakness;
- dependency centrality;
- security/sensitivity;
- change scope;
- operational cost;
- policy context.

The old v4 weighted equation is legacy-only.

## 8. Approval eligibility

A recommendation is eligible only when policy-required conditions hold:

```text
current revision
+ current packet
+ current evidence snapshot
+ current policy snapshot
+ required quality dimensions complete
+ required validation complete
+ no unaccepted blocker
+ explicit human rationale
```

Risk acceptance may waive only policy-permitted blockers and must itself be recorded.

## 9. Adaptive behavior

Adaptive behavior means selection among existing policy profiles and model/role routes. It never means self-modifying thresholds or self-authorized policy updates.

## 10. Policy learning

Outcome data may generate a `PolicyVersionProposal`. The proposal contains evidence, affected rules, expected effect, regression fixtures and activation scope. The owner must explicitly activate the new policy version.

Historical runs retain prior policy bindings.
