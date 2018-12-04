package day4

import java.util.Comparator
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


fun main() {

    val day4 = Day4()

    val result = INPUT.lines()
            .stream()
            .sorted(Comparator.comparing<String, String> { e -> e.substring(0, 18) })
            .collect(Collectors.joining("\n"))

    println("Part One: " + day4.findSleepyGuard(result.lines()))
    println("Part Two: " + day4.findMostFrequentlyAsleepGuard(result.lines()))

}

/**
 * Solution for puzzle of day 4
 */
class Day4 {

    private var guards: MutableMap<Int, Guard> = HashMap()

    private fun prepareData(input: List<String>) {
        guards = HashMap()

        val logEntries = input.map { parseLog(it) }

        for (logEntry in logEntries) {
            if (logEntry.guard != null) {
                guards[logEntry.guard.id] = logEntry.guard
            }

        }

        var currentGuard: Guard? = null
        var shift: IntArray? = null
        for (logEntry in logEntries) {
            if (logEntry.guard != null) {
                if (currentGuard != null && shift != null) {
                    currentGuard.shifts.add(shift)
                }
                currentGuard = guards[logEntry.guard.id]
                shift = IntArray(60) { it -> 0 }
            } else if (logEntry.action == "falls asleep") {
                for (i in logEntry.minute..59) {
                    shift!![i] = 1
                }
            } else if (logEntry.action == "wakes up") {
                for (i in logEntry.minute..59) {
                    shift!![i] = 0
                }
            }
        }
        if (currentGuard != null && shift != null) {
            currentGuard.shifts.add(shift)
        }
    }

    fun findSleepyGuard(input: List<String>, print: Boolean = false): Int {
        prepareData(input)

        if (print) printGuards()

        var guardSleptLongest = guards.values.first()
        var maxAsleepMinutes = guardSleptLongest.getMinutesAsleep()

        guards.values.forEach {
            val minutesAsSleep = it.getMinutesAsleep()
            if (maxAsleepMinutes < minutesAsSleep) {
                guardSleptLongest = it
                maxAsleepMinutes = minutesAsSleep
            }
        }

        return guardSleptLongest.getMinuteMostAsleep().first * guardSleptLongest.id
    }

    private fun printGuards() {
        guards.values.forEach {
            println("#${it.id}: ${it.getMinutesAsleep()}")
            for (i in 0..59) {
                print(i / 10)
            }
            println()
            for (i in 0..59) {
                print(i % 10)
            }
            println()
            it.shifts.forEach { shift ->
                for (i in shift) {
                    print(if (i == 0) "." else "#")
                }
                println()
            }
        }
    }

    fun findMostFrequentlyAsleepGuard(input: List<String>, print: Boolean = false): Int {
        prepareData(input)

        if (print) printGuards()

        var guardSleptMostFrequently = guards.values.first()
        var maxFreq = guardSleptMostFrequently.getMinuteMostAsleep().second

        guards.values.forEach {
            val freq = it.getMinuteMostAsleep().second
            if (maxFreq < freq) {
                guardSleptMostFrequently = it
                maxFreq = freq
            }
        }

        println("minuteMostAsleep: " + guardSleptMostFrequently.getMinuteMostAsleep().first)
        println("freqMinuteMostAsleep: " + guardSleptMostFrequently.getMinuteMostAsleep().second)
        println("guard: " + guardSleptMostFrequently.id)

        return guardSleptMostFrequently.getMinuteMostAsleep().first * guardSleptMostFrequently.id
    }


    fun parseLog(log: String): LogEntry {

        val regex = Regex("^\\[(\\d{4})-(?<month>\\d{2})-(?<day>\\d{2}) (?<hour>\\d{2}):(?<minute>\\d{2})]\\s(?<action>.*)\$")

        val groups = regex.matchEntire(log)!!.groups
        var min: Int = groups["minute"]!!.value.toInt()
        val day: Int = groups["day"]!!.value.toInt()
        val month: Int = groups["month"]!!.value.toInt()
        var action = groups["action"]!!.value
        val hour = groups["hour"]!!.value.toInt()

        val regexGuardBeginsShift = Regex("^Guard #(?<id>\\d*) begins shift\$")
        val guardId = regexGuardBeginsShift.matchEntire(action)?.groups?.get("id")?.value?.toInt()
        var guard: Guard? = null
        if (guardId != null) {
            guard = Guard(id = guardId)
            action = "begins shift"
        }

        if (hour == 23) {
            min = 0
        }

        return LogEntry(minute = min, guard = guard, action = action, day = day, month = month)
    }

}

data class LogEntry(val minute: Int, val guard: Guard?, val action: String, val day: Int, val month: Int)

data class Guard(val id: Int, var shifts: MutableList<IntArray>) {
    constructor(id: Int) : this(id, ArrayList())

    fun getMinutesAsleep(): Int {
        var count = 0
        for (shift in shifts) {
            count += shift.count { it == 1 }
        }
        return count
    }

    /**
     * @return first minute, second frequency
     */
    fun getMinuteMostAsleep(): Pair<Int, Int> {
        val minutes = IntArray(60) { it -> 0 }
        for (i in 0..59) {
            for (shift in shifts) {
                minutes[i] += shift[i]
            }
        }
        return Pair(minutes.indexOf(minutes.max()!!), minutes.max()!!)
    }


}