package day14

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class ScoreTrackerTest {

    private lateinit var scoreTracker: ScoreTracker

    @BeforeEach
    internal fun setUp() {
        scoreTracker = ScoreTracker()
    }

    @Test
    fun test_getScoreAfterRecipeTries_example1() {
        assertThat(scoreTracker.getScoreAfterRecipeTries(9), equalTo("5158916779"))
    }

    @Test
    fun test_getScoreAfterRecipeTries_example2() {
        assertThat(scoreTracker.getScoreAfterRecipeTries(5), equalTo("0124515891"))
    }

    @Test
    fun test_getScoreAfterRecipeTries_example3() {
        assertThat(scoreTracker.getScoreAfterRecipeTries(18), equalTo("9251071085"))
    }

    @Test
    fun test_getScoreAfterRecipeTries_example4() {
        assertThat(scoreTracker.getScoreAfterRecipeTries(2018), equalTo("5941429882"))
    }

    @Test
    fun test_getResultsLeftOfScore_example1(){
        scoreTracker = ScoreTracker(true)
        assertThat(scoreTracker.getResultsLeftOfScore(51589.toString().toCharArray()), equalTo(9))
    }

    @Test
    fun test_getResultsLeftOfScore_example2(){
        scoreTracker = ScoreTracker(true)
        assertThat(scoreTracker.getResultsLeftOfScore("01245".toCharArray()), equalTo(5))
    }

    @Test
    fun test_getResultsLeftOfScore_example3(){
        scoreTracker = ScoreTracker(true)
        assertThat(scoreTracker.getResultsLeftOfScore("92510".toCharArray()), equalTo(18))
    }

    @Test
    fun test_getResultsLeftOfScore_example4(){
        scoreTracker = ScoreTracker(false)
        assertThat(scoreTracker.getResultsLeftOfScore("59414".toCharArray()), equalTo(2018))
    }





}