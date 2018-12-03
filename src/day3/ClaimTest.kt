package day3

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

/**
 * Tests [Claim]
 */
internal class ClaimTest {

    @Test
    fun testParseClaim() {
        assertThat(Claim.createFromString("#123 @ 3,2: 5x4"), equalTo(Claim(123, 3, 2, 5, 4)))
    }

}