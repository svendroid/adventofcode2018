package day2

fun main(args: Array<String>) {

    val puzzleInput: Array<String> = INPUT.lines().toTypedArray()

    var day2 = Day2()
    println("Result part1: " + day2.checksum(puzzleInput))

    day2 = Day2()
    println("Result part2: " + day2.findCommonLetters(puzzleInput))

}

/**
 * Solves advent of code puzzle 2
 */
class Day2 {

    /**
     * Calculates checksum - part 1 of the puzzle
     */
    fun checksum(input: Array<String>): Int? {
        var checksum: Int? = null
        val countMapAll: HashMap<Int, Int> = HashMap()

        for (s in input) {
            val countMap = this.count(s)
            for (entry in countMap.entries) {
                countMapAll.merge(entry.key, entry.value) { t, u -> t + u }
            }
        }

        for (value in countMapAll.values) if (checksum == null) {
            checksum = value
        } else {
            checksum *= value
        }
        return checksum
    }

    fun count(inputStr: String): HashMap<Int, Int> {
        val countMap: HashMap<Int, Int> = HashMap()
        val inputChrs = inputStr.toCharArray()
        inputChrs.sort()
        var count = 0
        var prevChar: Char? = null
        for ((i, c) in inputChrs.withIndex()) {
            if (c != prevChar) {
                if (count > 1) {
                    countMap.put(count, 1)
                }
                count = 0
            }
            count++
            if (i == inputChrs.size - 1) {
                if (count > 1) {
                    countMap.put(count, 1)
                }
            }
            prevChar = c
        }
        return countMap
    }

    /**
     * Find common letters between boxes - part 2 of puzzle
     */
    fun findCommonLetters(input: Array<String>): String {

        for (s in input) {
            for (s2 in input) {
                if (compare(s, s2) == 1){
                    println("found: " + s)
                    println("found: " + s2)
                    var result = String()
                    for ((j, c) in s.withIndex()) {
                        if (c == s2[j]) {
                            result = result.plus(c)
                        }
                    }
                    return result
                }
            }
        }
        throw Exception("Could not find common characters")
    }

    fun compare(a: String, b: String): Int {
        val aInts = a.map { it.toInt() }.toIntArray()
        val bInts = b.map { it.toInt() }.toIntArray()

        var result = 0
        for ((i, aInt) in aInts.withIndex()) {
            if(aInt != bInts[i]){
                result += 1
            }
        }
        return result
    }

}