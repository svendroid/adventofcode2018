package day5

import kotlin.math.absoluteValue


fun main() {

    val day5 = Day5()

    var start = System.currentTimeMillis()
    println("Result part1: " + day5.getNumberOfUnitsAfterReact(INPUT))
    println((System.currentTimeMillis() - start) / 1000)

    start = System.currentTimeMillis()
    println("Result part2: " + day5.findShortestPolymer(INPUT))
    println((System.currentTimeMillis() - start) / 1000)
}

class Day5 {

    fun getNumberOfUnitsAfterReact(units: String): Int {
        return react(units).length
    }

    fun findShortestPolymer(units: String): Int {
        var minUnits = units.length
        for (lowerChar in 'a'.toInt()..'z'.toInt()) {
            val upperChar = lowerChar - 32

            var result = units.replace("" + lowerChar.toChar(), "")
            result = result.replace("" + upperChar.toChar(), "")

            val unitsCount = getNumberOfUnitsAfterReact(result)
            println(lowerChar.toChar() + "/" + upperChar.toChar() + ": " + unitsCount)
            if (unitsCount < minUnits) {
                minUnits = unitsCount
            }
        }

        return minUnits
    }

    fun react(units: String): String {
        val polymer = units.toCharArray()
        var polymerResult = ""

        var skipNext = false
        for (i in polymer.indices) {
            if (skipNext) {
                skipNext = false
                continue
            }
            val c = polymer[i]
            if (i == polymer.lastIndex) {
                polymerResult = polymerResult.plus(c)
                continue
            }
            val next = polymer[i + 1]

            if ((c.toInt() - next.toInt()).absoluteValue == 32) {
                skipNext = true
            } else {
                polymerResult = polymerResult.plus(c)
            }
        }

        if (polymerResult.length == polymer.size) {
            return polymerResult
        } else {
            return react(polymerResult)
        }
    }


}