# 09 — Android UX & UI Specification

## 1. UX principle

The application is **decision-first, progressive-disclosure and narrow-screen-first**. Every screen shows the user's current authority and what is provisional versus authoritative.

## 2. Primary navigation

```text
Inbox / Decisions
├── Capture
├── Decision Workspace
│   ├── Interpretation
│   ├── Context
│   ├── Framing
│   ├── Run
│   ├── Review
│   ├── History
│   └── Outcomes
├── Living Blueprint
└── Settings
```

## 3. Capture

Input modes:

- text;
- voice-to-text (`bn-BD`, `en-US`);
- Android Share Target.

Raw input is displayed as captured. AI interpretation is a separate editable layer.

## 4. Interpretation screen

Shows:

- normalized decision question;
- suggested title;
- claims and epistemic types;
- unknowns;
- domains;
- preliminary impact/reversibility;
- edit/accept/reject per item.

Accepted context only enters the working draft.

## 5. Consequential questions

Questions are ranked by potential decision impact. Each supports:

`Answer`, `I don't know`, `Not applicable`, `Defer`.

Blocking questions show why the answer can change the decision.

## 6. Framing

Sections:

Objective, non-goals, stakeholders, constraints, preferences, criteria, options, rejected alternatives, dependencies, validation budget, revisit triggers.

Draft changes are visible as revision material.

## 7. Readiness

A checklist of named checks. Each blocker offers a fix. No deceptive “continue anyway” for a hard blocker.

## 8. Run setup

Shows:

- tier;
- required roles and mandates;
- optional role additions;
- cost estimate/reservation;
- time estimate;
- provenance mode;
- tool scopes.

## 9. Run monitor

Displays per-task states, stage progress, spend, elapsed time, failures and retry state. User can cancel eligible work.

## 10. Review packet

### Default

Recommendation, strongest alternative, top critical findings, quality summary, critical risks, validation state, next action.

### Expanded

All criteria, perspectives, dissent, evidence map, critique registry, run history, policy inputs, audit/history.

Every recommendation exposes:

- **Why this?**
- **What could change this?**

## 11. Governance sheet

The review sheet must make the exact binding context obvious:

revision hash, packet hash, evidence snapshot, policy version, selected option, rationale, risk acceptances, step-up and server time.

Actions:

`APPROVE`, `REJECT`, `DEFER`, `REQUEST REVISION`.

## 12. Blueprint

Supports list/text view even when graph rendering is unavailable. Proposed/open decision relations are visibly distinct from accepted ADR relations.

## 13. History

Timeline, revisions, revision diff, run history, governance actions and provenance. Historical records are read-only.

## 14. Degraded connectivity

Cached data has a freshness label. Draft editing remains available. Pending eligible edits can queue. Approval and live run controls are disabled until connectivity returns.

## 15. Conflict UX

Show:

1. what changed on server;
2. local changes;
3. conflict classification;
4. proposed merged draft.

Actions are explicit: refresh, discard local, keep local copy, resolve/confirm merged draft.

## 16. Accessibility and layout

Target 360dp+, minimum acceptance 375px equivalent. Use accessible labels, touch targets, content descriptions, large-text resilience, contrast and state announcements. Critical status is never conveyed by color alone.

## 17. Notifications

FCM messages contain IDs/status hints. Tapping opens the app, which retrieves authoritative state.

Notification classes: run complete, run failed, assist job ready, outcome review due, conflict requiring attention.

## 18. Settings

Language, AI output language, provider/model routing, budgets, approval step-up, export/delete, notification settings. Simulated-mode toggle is development-only.
