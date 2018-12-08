package day8

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day8Test {

    private lateinit var day8: Day8

    val input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"

    @BeforeEach
    fun setUp() {
        day8 = Day8()
    }


    @Test
    fun test_getSumOfMetaData(){
        assertThat(day8.getSumOfMetaData(input), equalTo(138))
    }

    @Test
    fun test_getValueOfRootNode(){
        assertThat(day8.getValueOfRootNode(input), equalTo(66))
    }


}