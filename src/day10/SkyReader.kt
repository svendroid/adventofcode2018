package day10

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val input = File(ClassLoader.getSystemResource("input_10.txt").toURI()).readLines()
    val skyReader = SkyReader()

    val secondsToWait = skyReader.findSecondsToWaitForMessage(input = input)
    println("Seconds to wait: " + secondsToWait)
    println("You would wait for ${secondsToWait/3600}h ${(secondsToWait%3600)/60}m ${secondsToWait%60}s")
    println("Message: ")
    skyReader.printMessage()
}


class SkyReader {

    var regex = "^position=<\\s*(?<x>-?\\d*),\\s*(?<y>-?\\d*)>\\svelocity=<\\s*(?<velX>-?\\d*),\\s*(?<velY>-?\\d*)>\$".toRegex()

    /**
     * Method to observe changes to points second by second
     */
    fun printSecondBySecond(input: List<String>) {

        val points = input.map {
            val groups = regex.matchEntire(it)!!.groups
            Point(groups["x"]!!.value.toInt(), groups["y"]!!.value.toInt(), groups["velX"]!!.value.toInt(), groups["velY"]!!.value.toInt())
        }.sortedBy { point -> point.y }

        for (second in 0..10) {
            println("#" + second)

            val maxY = points.maxBy { point -> point.y }!!.y + 1
            val maxX = points.maxBy { point -> point.x }!!.x + 1
            val minY = points.minBy { point -> point.y }!!.y
            val minX = points.minBy { point -> point.x }!!.x

            println("xDist: " + (maxX + minX * -1))
            println("yDist: " + (maxY + minY * -1))

            val yOffset = if (minY < 0) minY.absoluteValue else 0
            val xOffset = if (minX < 0) minX.absoluteValue else 0

            val grid: Array<Array<Char>> = Array(maxY + yOffset) {
                Array(maxX + xOffset) {
                    '.'
                }
            }

            println(grid.size.toString() + "," + grid[0].size)

            for (point in points) {
                grid[point.y + yOffset][point.x + xOffset] = '#'
                point.move()
            }

            for (lines in grid) {
                for (line in lines) {
                    print(line)
                }
                println(" ")
            }

            println()
        }
    }

    private lateinit var points: List<Point>

    fun findSecondsToWaitForMessage(input: List<String>): Int {

        points = parseData(input)

        var maxX = points.maxBy { point -> point.x }!!.x + 1
        var minX = points.minBy { point -> point.x }!!.x

        var prevXDist = maxX + minX * -1

        var seconds = -1
        while (true) {
            seconds++

            maxX = points.maxBy { point -> point.x }!!.x + 1
            minX = points.minBy { point -> point.x }!!.x


            val xDist = maxX + minX * -1
            if (xDist > prevXDist) {
                for (point in points) {
                    point.moveBack()
                }
                return seconds - 1
            } else {
                prevXDist = xDist
                for (point in points) {
                    point.move()
                }
            }
        }
    }

    private fun parseData(input: List<String>): List<Point> {
        return input.map {
            val groups = regex.matchEntire(it)!!.groups
            Point(groups["x"]!!.value.toInt(), groups["y"]!!.value.toInt(), groups["velX"]!!.value.toInt(), groups["velY"]!!.value.toInt())
        }.sortedBy { point -> point.y }
    }

    fun printMessage() {
        val maxY = points.maxBy { point -> point.y }!!.y + 1
        val maxX = points.maxBy { point -> point.x }!!.x + 1
        val minY = points.minBy { point -> point.y }!!.y
        val minX = points.minBy { point -> point.x }!!.x

        val yOffset = if (minY < 0) minY.absoluteValue else 0
        val xOffset = if (minX < 0) minX.absoluteValue else 0

        val grid: Array<Array<Char>> = Array(maxY + yOffset) {
            Array(maxX + xOffset) {
                '.'
            }
        }

        println(grid.size.toString() + "," + grid[0].size)

        for (point in points) {
            grid[point.y + yOffset][point.x + xOffset] = '#'
            point.move()
        }

        for (y in grid) {
            for (x in y) {
                print(x)
            }
            println(" ")
        }

        println()
    }


}

data class Point(var x: Int, var y: Int, var velX: Int, var velY: Int) {
    fun move() {
        x += velX
        y += velY
    }

    fun moveBack() {
        x -= velX
        y -= velY
    }
}