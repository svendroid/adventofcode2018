package day11

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PowerFinderTest {


    private lateinit var powerFinder: PowerFinder

    @BeforeEach
    fun setUp() {
        powerFinder = PowerFinder()
    }

    @Test
    internal fun test_findMostPowerfulFuelCell_example0() {
        assertThat(powerFinder.findMostPowerfulFuelCell(18), equalTo(Triple(29, 33, 45)))
    }

    @Test
    internal fun test_findMostPowerfulFuelCell_example1() {
        assertThat(powerFinder.findMostPowerfulFuelCell(42), equalTo(Triple(30, 21, 61)))
    }

    @Test
    fun test_calcPower_example0() {
        assertThat(powerFinder.calcPower(3, 5, 8), equalTo(4))
    }

    @Test
    fun test_calcPower_example1() {
        assertThat(powerFinder.calcPower(122, 79, 57), equalTo(-5))
    }

    @Test
    fun test_calcPower_example2() {
        assertThat(powerFinder.calcPower(217, 196, 39), equalTo(0))
    }

    @Test
    fun test_calcPower_example3() {
        assertThat(powerFinder.calcPower(101, 153, 71), equalTo(4))
    }

    @Test
    fun test_calcPower_example4() {
        assertThat(powerFinder.calcPower(33, 45, 18), equalTo(4))
    }

    @Test
    fun test_findLargestPowerfulFuelCell_example0() {
        assertThat(powerFinder.findLargest(18), equalTo(Triple(90, 269, 16)))
    }

    @Test
    fun test_findLargestPowerfulFuelCell_example1() {
        assertThat(powerFinder.findLargest(42), equalTo(Triple(232, 251, 12)))
    }
}