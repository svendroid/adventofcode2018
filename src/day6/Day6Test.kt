package day6

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class Day6Test {

    private lateinit var day6: Day6
    val input = "1, 1\n1, 6\n8, 3\n3, 4\n5, 5\n8, 9"

    @BeforeEach
    internal fun setUp() {
        day6 = Day6()
    }

    @Test
    fun test_getSizeOfLargestArea() {
        assertThat(day6.getSizeOfLargestArea(input), equalTo(17))
    }

    @Test
    fun test_getAreaSizeWithinRegion(){
        assertThat(day6.getAreaSizeWithinRegion(input, 32), equalTo(16))
    }

    @Test
    fun test_calcManhattenDistance() {
        assertThat(day6.calcManhattenDistance(Coordinate(0, 6), Coordinate(6, 0)), equalTo(12))
    }
}