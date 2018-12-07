package day6

import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

fun main() {
    val day6 = Day6(false)

    println("Result part1: " + day6.getSizeOfLargestArea(INPUT))

    println("Result part2: " + day6.getAreaSizeWithinRegion(INPUT))
}


class Day6(var debugPrint: Boolean = true) {

    private lateinit var grid: Array<Array<Int>>
    private lateinit var coordsMap: HashMap<Int, Coordinate>
    private var maxX: Int = 0
    private var maxY: Int = 0

    fun getAreaSizeWithinRegion(input: String, region: Int = 10000): Int {
        createGrid(input)
        var areaSize = 0
        for ((y, line) in grid.withIndex()) {
            for (x in line.indices) {
                var totalDistance = 0
                for (coord in coordsMap.values) {
                    totalDistance += calcManhattenDistance(Coordinate(x, y), coord)
                }
                grid[y][x] = totalDistance
                if (totalDistance < region) {
                    areaSize++
                }
            }
        }

        printGrid()

        return areaSize
    }


    fun getSizeOfLargestArea(input: String): Int {

        createGrid(input)

        val infinite = HashSet<Int>()
        for ((y, line) in grid.withIndex()) {
            for ((x, c) in line.withIndex()) {
                if (c == -1) {
                    var minDistance = Int.MAX_VALUE
                    var minId = coordsMap.keys.first()
                    for ((id, coord) in coordsMap) {
                        val distance = calcManhattenDistance(Coordinate(x, y), coord)
                        if (distance < minDistance) {
                            minId = id
                            minDistance = distance
                        } else if (distance == minDistance) {
                            minId = -2
                        }
                    }
                    grid[y][x] = minId
                    if (minId != -2) {
                        coordsMap[minId]!!.count++
                    }
                    if (y == 0 || y == maxY - 1 || x == 0 || x == maxX - 1) {
                        infinite.add(minId)
                    }
                } else {
                    coordsMap[c]!!.count++
                    if (y == 0 || y == maxY - 1 || x == 0 || x == maxX - 1) {
                        infinite.add(c)
                    }
                }

            }
        }

        printGrid()

        if (debugPrint) println(infinite)
        coordsMap.keys.removeAll(infinite)

        if (debugPrint) for ((id, coord1) in coordsMap) {
            println("${id}: " + coord1.count)
        }

        val max = coordsMap.values.map { it.count }.max()
        return max!!
    }

    fun printGrid() {
        if (debugPrint) {
            for (chars in grid) {
                for (char in chars) {
                    print("$char|")
                }
                println()
            }
        }
    }


    fun calcManhattenDistance(coord1: Coordinate, coord2: Coordinate): Int {
        return (coord1.x - coord2.x).absoluteValue + (coord1.y - coord2.y).absoluteValue
    }

    fun createGrid(input: String) {

        val regex = Regex("^(?<x>\\d*), (?<y>\\d*)\$")

        coordsMap = HashMap<Int, Coordinate>()

        for ((i, line) in input.lines().withIndex()) {
            val groups = regex.matchEntire(line)!!.groups
            coordsMap.put(i, Coordinate(groups["x"]!!.value.toInt(), groups["y"]!!.value.toInt()))
        }

        var minX = coordsMap.values.first().x
        var minY = coordsMap.values.first().y
        for (coord in coordsMap.values) {
            maxX = max(maxX, coord.x)
            maxY = max(maxY, coord.y)
            minX = min(minX, coord.x)
            minY = min(minY, coord.y)
        }

        maxX += minX
        maxY += minY

        grid = Array(maxY) {
            Array(maxX) {
                -1
            }
        }

        for ((id, coord) in coordsMap) {
            grid[coord.y][coord.x] = id
        }

    }

}


data class Coordinate(val x: Int, val y: Int, var count: Int = 0) {}

