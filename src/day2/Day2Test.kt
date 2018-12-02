package day2

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests [Day2]
 */
internal class Day2Test {

    private lateinit var day2: Day2

    @BeforeEach
    fun setUp() {
        day2 = Day2()
    }

    @Test
    fun test_checksum() {
        val input: Array<String> = arrayOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
        val checksum = day2.checksum(input)
        assertThat(checksum, equalTo(12))
    }

    @Test
    fun test_count() {
        val input: Array<String> = arrayOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
        for (s in input) {
            println(s + ": " + day2.count(s))
        }
    }

    @Test
    fun test_findCommonLetters() {
        val input = arrayOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")
        assertThat(day2.findCommonLetters(input), equalTo("fgij"))
    }

    @Test
    fun test_compare() {
        assertThat(day2.compare("fghij", "fguij"), equalTo(1))
    }

    @Test
    fun test_findFabricsasdfasdf() {
        assertThat(day2.compare("abcde", "fguij"), equalTo(5))
    }


}