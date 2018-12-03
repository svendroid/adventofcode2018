package day3

fun main(args: Array<String>) {
    var day3 = Day3()
    println("Result part one: " + day3.findRepeatedlyClaimedFabric(INPUT.lines().toTypedArray()))

    day3 = Day3()
    println("Result part two: " + day3.findUntouchedClaimId(INPUT.lines().toTypedArray()))
}

class Day3 {

    private fun createGrid(input: Array<String>): Array<Array<String>> {
        val claims = input.map { Claim.createFromString(it) }

        var maxX = 0
        var maxY = 0
        for (claim in claims) {
            if (maxX < claim.getMaxX()) {
                maxX = claim.getMaxX()
            }
            if (maxY < claim.getMaxY()) {
                maxY = claim.getMaxY()
            }
        }
        maxX += 1
        maxY += 1

        val fabricGrid: Array<Array<String>> = Array(maxY) {
            Array(maxX) {
                "."
            }
        }

        for (claim in claims) {
            for (x in claim.x..claim.getMaxX()) {
                for (y in claim.y..claim.getMaxY()) {
                    fabricGrid[y][x] = if (fabricGrid[y][x] == ".") claim.id.toString() else "X"
                }
            }
        }

        return fabricGrid
    }

    fun findRepeatedlyClaimedFabric(input: Array<String>, printGrid: Boolean = false): Int {
        val fabricGrid = createGrid(input)

        if (printGrid) {
            for (lines in fabricGrid) {
                for (line in lines) {
                    print(line)
                }
                println(" ")
            }
        }

        var count = 0
        for (lines in fabricGrid) {
            for (line in lines) {
                if (line.contains("X")) {
                    count++
                }
            }
        }

        return count
    }

    fun findUntouchedClaimId(input: Array<String>, printGrid: Boolean = false): Int {
        val fabricGrid = createGrid(input)
        val claims = input.map { Claim.createFromString(it) }
        var untouchedId = -1

        if (printGrid) {
            for (lines in fabricGrid) {
                for (line in lines) {
                    print(line)
                }
                println(" ")
            }
        }

        loop@ for (claim in claims) {
            for (x in claim.x..claim.getMaxX()) {
                for (y in claim.y..claim.getMaxY()) {
                    if (fabricGrid[y][x] == "X") {
                        continue@loop
                    }
                }
            }
            untouchedId = claim.id
        }
        return untouchedId
    }


}

data class Claim(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int) {
    fun getMaxX(): Int {
        return x + width - 1
    }

    fun getMaxY(): Int {
        return y + height - 1
    }

    companion object {
        fun createFromString(claim: String): Claim {
            val regex = Regex("^#(?<id>\\d*)\\s@\\s(?<x>\\d*),(?<y>\\d*):\\s(?<width>\\d*)x(?<height>\\d*)\$")
            val matchGroups = regex.matchEntire(claim)!!.groups

            return Claim(matchGroups["id"]!!.value.toInt(),
                    matchGroups["x"]!!.value.toInt(),
                    matchGroups["y"]!!.value.toInt(),
                    matchGroups["width"]!!.value.toInt(),
                    matchGroups["height"]!!.value.toInt())
        }
    }
}