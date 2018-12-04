package day4

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests [Day4]
 */
class Day4Test {

    private lateinit var day4: Day4

    private val input: String = """[1518-11-01 00:00] Guard #10 begins shift
[1518-11-01 00:05] falls asleep
[1518-11-01 00:25] wakes up
[1518-11-01 00:30] falls asleep
[1518-11-01 00:55] wakes up
[1518-11-01 23:58] Guard #99 begins shift
[1518-11-02 00:40] falls asleep
[1518-11-02 00:50] wakes up
[1518-11-03 00:05] Guard #10 begins shift
[1518-11-03 00:24] falls asleep
[1518-11-03 00:29] wakes up
[1518-11-04 00:02] Guard #99 begins shift
[1518-11-04 00:36] falls asleep
[1518-11-04 00:46] wakes up
[1518-11-05 00:03] Guard #99 begins shift
[1518-11-05 00:45] falls asleep
[1518-11-05 00:55] wakes up"""

    @BeforeEach
    internal fun setUp() {
        day4 = Day4()
    }

    @Test
    internal fun test_parseLog_beginsShift() {
        val log = day4.parseLog("[1518-11-01 00:00] Guard #10 begins shift")
        assertThat(log, equalTo(LogEntry(0, Guard(10), "begins shift", 1, 11)))
    }

    @Test
    internal fun test_parseLog_beginsShift_23() {
        val log = day4.parseLog("[1518-11-01 23:58] Guard #99 begins shift")
        assertThat(log, equalTo(LogEntry(0, Guard(99), "begins shift", 1, 11)))
    }

    @Test
    internal fun test_parseLog_fallsAsleep() {
        val log = day4.parseLog("[1518-11-01 00:05] falls asleep")
        assertThat(log, equalTo(LogEntry(5, null, "falls asleep", 1, 11)))
    }

    @Test
    internal fun test_findSleepyGuard() {
        assertThat(day4.findSleepyGuard(input.lines()), equalTo(240))
    }

    @Test
    internal fun test_findMostFrequentlyAsleepGuard() {
        assertThat(day4.findMostFrequentlyAsleepGuard(input.lines()), equalTo(4455))
    }
}