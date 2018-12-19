package day18

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

internal class Day18Test {

    private lateinit var day18: Day18

    val input = """.#.#...|#.
.....#|##|
.|..|...#.
..|#.....#
#.#|||#|#|
...#.||...
.|....|...
||...#|.#|
|.||||..|.
...#.|..|."""

    @BeforeEach
    fun setUp() {
        day18 = Day18()
    }


    @Test
    fun test(){
        assertThat(day18.getResourceValue(input.lines(), 10), equalTo(1147))
    }

    @Test
    fun test_large_example(){
        assertThat(day18.getResourceValue(File(ClassLoader.getSystemResource("input_18.txt").toURI()).readLines(), 840), equalTo(248810))
    }

}