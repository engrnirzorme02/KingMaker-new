package com.kingmaker.app.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class RevisionFingerprintTest {
    @Test fun `fingerprint is stable and uses SHA-256`() {
        val value = "sealed revision: sync architecture"
        assertEquals(RevisionFingerprint.sha256(value), RevisionFingerprint.sha256(value))
        assertEquals(64, RevisionFingerprint.sha256(value).length)
    }
}
