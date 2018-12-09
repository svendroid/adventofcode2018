package day9

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CircleGameTest {

    private lateinit var circleGame: CircleGame

    @BeforeEach
    fun setUp() {
        circleGame = CircleGame()
        CircleGame.logEnabled = false
    }

    @Test
    fun test_getWinningScore_example1() {
        CircleGame.logEnabled = true
        assertThat(circleGame.getWinningScore(9, 25), equalTo(32.toBigInteger()))
    }

    @Test
    fun test_getWinningScore_example2() {
        assertThat(circleGame.getWinningScore(10, 1618), equalTo(8317.toBigInteger()))
    }
    @Test
    fun test_getWinningScore_example3() {
        assertThat(circleGame.getWinningScore(13, 7999), equalTo(146373.toBigInteger()))
    }

    @Test
    fun test_getWinningScore_example4() {
        assertThat(circleGame.getWinningScore(17, 1104), equalTo(2764.toBigInteger()))
    }

    @Test
    fun test_getWinningScore_example5() {
        assertThat(circleGame.getWinningScore(21, 6111), equalTo(54718.toBigInteger()))
    }




}