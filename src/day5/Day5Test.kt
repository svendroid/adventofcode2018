package day5

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day5Test {

    private lateinit var day5: Day5

    @BeforeEach
    fun setUp() {
        day5 = Day5()
    }

    @Test
    fun test_react_aA(){
        assertThat(day5.react("aA"), equalTo(""))
    }

    @Test
    fun test_react_abBA(){
        assertThat(day5.react("abBA"), equalTo(""))
    }

    @Test
    fun test_react_abAB(){
        assertThat(day5.react("abAB"), equalTo("abAB"))
    }

    @Test
    fun test_react_aabAAB(){
        assertThat(day5.react("aabAAB"), equalTo("aabAAB"))
    }

    @Test
    fun test_react_long_example(){
        assertThat(day5.react("dabAcCaCBAcCcaDA"), equalTo("dabCBAcaDA"))
    }

    @Test
    fun test_getNumberOfUnitsAfterReact(){
        assertThat(day5.getNumberOfUnitsAfterReact("dabAcCaCBAcCcaDA"), equalTo(10))
    }

    @Test
    internal fun test_findShortest() {
        assertThat(day5.findShortestPolymer("dabAcCaCBAcCcaDA"), equalTo(4))
    }
}