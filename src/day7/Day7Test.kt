package day7

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day7Test {

    private lateinit var day7: Day7

    val input = """Step C must be finished before step A can begin.
Step C must be finished before step F can begin.
Step A must be finished before step B can begin.
Step A must be finished before step D can begin.
Step B must be finished before step E can begin.
Step D must be finished before step E can begin.
Step F must be finished before step E can begin."""

    @BeforeEach
    fun setUp() {
        day7 = Day7(printDebug = true)
    }


    @Test
    fun test_getInstructionsOrder(){
        assertThat(day7.getInstructionsOrder(input), equalTo("CABDFE"))
    }

    @Test
    fun test_getCompletionTime(){
        assertThat(day7.getCompletionTime(input, 2, 0), equalTo(15))
    }
}