package day3

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests [Day3]
 */
internal class Day3Test {

    private lateinit var day3: Day3
    private val input = "#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2"

    @BeforeEach
    internal fun setUp() {
        day3 = Day3()
    }

    @Test
    fun testFindRepeatedlyClaimedFabric() {
        assertThat(day3.findRepeatedlyClaimedFabric(input.lines().toTypedArray(), true), equalTo(4))
    }

    @Test
    fun testFindUntouchedClaimId(){
        assertThat(day3.findUntouchedClaimId(input = input.lines().toTypedArray()), equalTo(3))
    }
}