package day1

fun main(args: Array<String>) {
    var day1 = Day1()

    val inputIntArray: IntArray = input.lines().map { it.toInt() }.toIntArray()
    val frequency = day1.changeFrequency(inputIntArray)
    println("Result part1: $frequency")

    day1 = Day1()

    val frequencyReachedTwice = day1.findFrequencyReachedTwice(inputIntArray)
    println("Result part2: $frequencyReachedTwice")
}

/**
 * Advent of Code Day 1
 */
class Day1 {

    private var frequency: Int = 0
    private var foundFrequencies: HashSet<Int> = HashSet()

    init {
        foundFrequencies.add(frequency)
    }

    private fun changeFrequency(change: Int): Int {
        frequency = frequency + change
        return frequency
    }

    /**
     * Change frequency solution for part1 of the riddle
     */
    fun changeFrequency(changes: IntArray): Int {
        for (change in changes) {
            changeFrequency(change)
        }
        return frequency
    }

    fun findFrequencyReachedTwice(changes: IntArray): Int {
        while (true) {
            for (change in changes) {
                val newFrequency = changeFrequency(change)
                if (!foundFrequencies.add(newFrequency)) {
                    //element already contained in set
                    return frequency
                }
            }
        }
    }
}