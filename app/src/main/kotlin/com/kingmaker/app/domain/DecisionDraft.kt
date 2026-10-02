package com.kingmaker.app.domain

import java.security.MessageDigest

enum class DecisionStage { RAW, INTERPRETATION, CONTEXT, FRAMING, READY_FOR_DEBATE, RUNNING, HUMAN_REVIEW, APPROVED }
enum class Provenance { SIMULATED, REPLAY, PROVIDER }

data class DecisionDraft(
    val id: String,
    val rawCapture: String,
    val title: String,
    val stage: DecisionStage = DecisionStage.RAW,
    val revision: Int = 1,
    val provenance: Provenance = Provenance.SIMULATED,
)

/**
 * Client-side representation only. Sealing and all governance transitions must be performed
 * by the authoritative server; this hash lets the client display the revision it is reviewing.
 */
object RevisionFingerprint {
    fun sha256(value: String): String = MessageDigest.getInstance("SHA-256")
        .digest(value.toByteArray(Charsets.UTF_8))
        .joinToString("") { "%02x".format(it) }
}
