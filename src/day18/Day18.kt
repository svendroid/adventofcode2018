package day18

import java.io.File

fun main() {

    val day18 = Day18()
    val input = File(ClassLoader.getSystemResource("input_18.txt").toURI()).readLines()
    println("Result part1: " + day18.getResourceValue(input, 10))
    println("Result part2: " + day18.getResourceValue(input, 1000000000))

}

class Day18 {

    lateinit var grid: Array<CharArray>

    val OPEN: Char = '.'
    val TREES: Char = '|'
    val LUMBERYARD: Char = '#'

    fun getResourceValue(input: List<String>, minTotalIn: Int): Int {

        grid = Array(input.size) {
            input[it].toCharArray()
        }

        printGrid()

        val cache = HashMap<Int, Int>()


        var min = 1
        var minTotal = minTotalIn
        var alreadyreset = false
        while (min <= minTotal) {

            val newGrid = Array(grid.size) { CharArray(grid.size) { 'x' } }

            for ((y, line) in grid.withIndex()) {
                for ((x, acre) in line.withIndex()) {
                    var openCount = 0
                    var treesCount = 0
                    var lumberCount = 0

                    for (yOffset in -1..1) {
                        for (xOffset in -1..1) {
                            if (yOffset == 0 && xOffset == 0) {
                                continue
                            }
                            val yTry = y + yOffset
                            val xTry = x + xOffset
                            if (yTry >= 0 && yTry <= grid.lastIndex && xTry >= 0 && xTry <= line.lastIndex) {
                                when (grid[yTry][xTry]) {
                                    OPEN -> {
                                        openCount++
                                    }
                                    TREES -> {
                                        treesCount++
                                    }
                                    LUMBERYARD -> {
                                        lumberCount++
                                    }
                                }
                            }

                        }
                    }

                    var result = acre
                    when (acre) {
                        OPEN -> {
                            if (treesCount >= 3) {
                                result = TREES
                            }
                        }
                        TREES -> {
                            if (lumberCount >= 3) {
                                result = LUMBERYARD
                            }
                        }
                        LUMBERYARD -> {
                            if (lumberCount >= 1 && treesCount >= 1) {
                                result = LUMBERYARD
                            } else {
                                result = OPEN
                            }

                        }
                    }
                    newGrid[y][x] = result
                }
            }
            grid = newGrid

            var woodCount = 0
            var lumberCount = 0
            for (line in grid) {
                woodCount += line.count { it == TREES }
                lumberCount += line.count { it == LUMBERYARD }
            }
            val result = woodCount * lumberCount

            var diff = -1
            if (cache.containsKey(result)) {
                diff = min - cache.get(result)!!
            }

            cache.put(result, min)
            println("Minute $min: $result $diff")

            if (min == 603 && !alreadyreset) {
                println("first")

                val stepsToDo = (minTotal - min) % 28
                println("stepsToDo: $stepsToDo")
                minTotal = min + stepsToDo
                alreadyreset = true
            }
            min++

//            printGrid()
        }
        var woodCount = 0
        var lumberCount = 0
        for (line in grid) {
            woodCount += line.count { it == TREES }
            lumberCount += line.count { it == LUMBERYARD }
        }
        return woodCount * lumberCount
    }

    fun printGrid() {
        for ((y, line) in grid.withIndex()) {
            for ((x, acre) in line.withIndex()) {
                print(acre)
            }
            println()
        }
        println()
    }


}