package day9

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day9Test {

    private lateinit var day9: Day9

    @BeforeEach
    fun setUp() {
        day9 = Day9()
    }

    @Test
    fun test_getWinningScore_example1() {
        assertThat(day9.getWinningScore(9, 25), equalTo(32))
    }

    @Test
    fun test_getWinningScore_example2() {
        assertThat(day9.getWinningScore(10, 1618), equalTo(8317))
    }
    @Test
    fun test_getWinningScore_example3() {
        assertThat(day9.getWinningScore(13, 7999), equalTo(146373))
    }

    @Test
    fun test_getWinningScore_example4() {
        assertThat(day9.getWinningScore(17, 1104), equalTo(2764))
    }

    @Test
    fun test_getWinningScore_example5() {
        assertThat(day9.getWinningScore(21, 6111), equalTo(54718))
    }




}