package day11

fun main() {
    val powerFinder = PowerFinder()
    val resultPart1 = powerFinder.findMostPowerfulFuelCell(7511)
    println("Result part1: ${resultPart1.second},${resultPart1.third}")

    println("Result part2: ")
    val resultPart2 = powerFinder.findLargest(7511)
    println("${resultPart2.first},${resultPart2.second},${resultPart2.third}")
}

class PowerFinder {

    private lateinit var grid: Array<IntArray>
    val gridSize = 300

    fun prepareGrid(gridSerial: Int) {

        grid = Array(gridSize) {
            IntArray(gridSize) {
                0
            }
        }

        for (y in 1..gridSize) {
            for (x in 1..gridSize) {
                grid[y - 1][x - 1] = calcPower(x, y, gridSerial)
            }
        }
    }


    fun findMostPowerfulFuelCell(gridSerial: Int): Triple<Int, Int, Int> {

        prepareGrid(gridSerial)

        var maxPower = 0
        var maxPowerX = 0
        var maxPowerY = 0
        for (y in 1..gridSize) {
            for (x in 1..gridSize) {
                val cellPower = calcPowerForCell(x, y, 3)
                if (cellPower > maxPower) {
                    maxPower = cellPower
                    maxPowerX = x
                    maxPowerY = y
                }
            }
        }

        println("$maxPower: $maxPowerX, $maxPowerY")

        return Triple(maxPower, maxPowerX, maxPowerY)
    }

    fun calcPowerForCell(x: Int, y: Int, cellSize: Int): Int {
        var cellPower = 0
        if (x + cellSize > gridSize + 1 || y + cellSize > gridSize + 1) {
            return 0
        }

        for (cellY in (y - 1).until(y - 1 + cellSize)) {
            for (cellX in (x - 1).until(x - 1 + cellSize)) {
                cellPower += grid[cellY][cellX]
            }
        }
        return cellPower
    }

    fun findLargest(gridSerial: Int): Triple<Int, Int, Int> {

        prepareGrid(gridSerial)

        var result: Triple<Int, Int, Int> = Triple(0, 0, 0)
        var maxPower = 0
        for (y in 1..gridSize) {
            for (x in 1..gridSize) {
                for (cellSize in 1..gridSize) {
                    val cellPower = calcPowerForCell(x, y, cellSize)
                    if (cellPower > maxPower) {
                        result = Triple(x, y, cellSize)
                        maxPower = cellPower
                    }
                }
            }
        }

        return result

    }

    fun calcPower(x: Int, y: Int, gridSerial: Int): Int {
        val rackId = x + 10
        var power = ((rackId * y + gridSerial) * rackId) / 100
        if (power > 0) {
            power = power.toString().last().toString().toInt()
        }
        return power - 5
    }


}