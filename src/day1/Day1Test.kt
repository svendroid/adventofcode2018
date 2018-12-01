package day1

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests [Day1]
 */
internal class Day1Test {

    private lateinit var day1: Day1

    @BeforeEach
    fun setUp() {
        day1 = Day1()
    }

    @Test
    fun test_getFrequency_example1() {
        val input: IntArray = intArrayOf(+1, +1, +1)
        val frequency = day1.changeFrequency(input)
        assert.that(frequency, equalTo(3))
    }

    @Test
    fun test_getFrequency_example2() {
        val input: IntArray = intArrayOf(+1, +1, -2)
        val frequency = day1.changeFrequency(input)
        assert.that(frequency, equalTo(0))
    }

    @Test
    fun test_getFrequency_example3() {
        val input: IntArray = intArrayOf(-1, -2, -3)
        val frequency = day1.changeFrequency(input)
        assert.that(frequency, equalTo(-6))
    }

    @Test
    fun test_findFrequencyReachedTwice_example0() {
        val input: IntArray = intArrayOf(+1, -2, +3, +1)
        val frequencyReachedTwice = day1.findFrequencyReachedTwice(input)
        assert.that(frequencyReachedTwice, equalTo(2))
    }

    @Test
    fun test_findFrequencyReachedTwice_example1() {
        val input: IntArray = intArrayOf(+1, -1)
        val frequencyReachedTwice = day1.findFrequencyReachedTwice(input)
        assert.that(frequencyReachedTwice, equalTo(0))
    }

    @Test
    fun test_findFrequencyReachedTwice_example2() {
        val input: IntArray = intArrayOf(+3, +3, +4, -2, -4)
        val frequencyReachedTwice = day1.findFrequencyReachedTwice(input)
        assert.that(frequencyReachedTwice, equalTo(10))
    }

    @Test
    fun test_findFrequencyReachedTwice_example3() {
        val input: IntArray = intArrayOf(-6, +3, +8, +5, -6)
        val frequencyReachedTwice = day1.findFrequencyReachedTwice(input)
        assert.that(frequencyReachedTwice, equalTo(5))
    }

    @Test
    fun test_findFrequencyReachedTwice_example4() {
        val input: IntArray = intArrayOf(+7, +7, -2, -7, -4)
        val frequencyReachedTwice = day1.findFrequencyReachedTwice(input)
        assert.that(frequencyReachedTwice, equalTo(14))
    }

}