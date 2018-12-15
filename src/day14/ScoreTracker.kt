package day14

fun main() {

    val scoreTracker = ScoreTracker()
    val input = 640441
    println("Result part1: " + scoreTracker.getScoreAfterRecipeTries(numRecipeTries = input))
    println("Result part2: " + scoreTracker.getResultsLeftOfScore(input.toString().toCharArray()))

}

class ScoreTracker(var debug: Boolean = false) {

    var scoreBoard: MutableList<Char> = "37".toCharArray().toMutableList()
    var elves: Array<Int> = Array(2) { it }

    fun reset() {
        scoreBoard = "37".toCharArray().toMutableList()
        elves = Array(2) { it }
    }

    fun getScoreAfterRecipeTries(numRecipeTries: Int): String {
        reset()

        printScoreBoard()

        while (true) {
            val newRecipes = (getValue(elves[0]) + getValue(elves[1])).toString().toCharArray()
            for (newRecipe in newRecipes) {
                scoreBoard.add(newRecipe)
                if (scoreBoard.size == numRecipeTries + 10) {
                    return scoreBoard.subList(numRecipeTries, numRecipeTries + 10).joinToString("")
                }
            }

            for (i in elves.indices) {
                elves[i] = stepForward(elves[i])
            }
            printScoreBoard()
        }
    }

    fun getResultsLeftOfScore(score: CharArray): Int? {
        reset()

        var compareIdx = 0
        while (true) {
            val newRecipes = (getValue(elves[0]) + getValue(elves[1])).toString().toCharArray()
            for (newRecipe in newRecipes) {
                scoreBoard.add(newRecipe)
                if (newRecipe.equals(score[compareIdx])) {
                    compareIdx += 1
                    if (compareIdx == score.size) {
                        return scoreBoard.size - score.size
                    }
                } else if (newRecipe.equals(score[0])) {
                    compareIdx = 1
                } else {
                    compareIdx = 0
                }
            }

            for (i in elves.indices) {
                elves[i] = stepForward(elves[i])
            }
            printScoreBoard()

        }

    }

    fun printScoreBoard(override: Boolean = false) {
        if (!debug && !override) return
        print("${scoreBoard.size}:")
        for ((i, score) in scoreBoard.withIndex()) {
            if (i == elves[0]) {
                print("(")
            } else if (i == elves[1]) {
                print("[")
            } else {
                print(" ")
            }
            print("$score")
            if (i == elves[0]) {
                print(")")
            } else if (i == elves[1]) {
                print("]")

            } else {
                print(" ")
            }
        }
        println()
    }

    private fun stepForward(i: Int): Int {
        return (i + 1 + getValue(i)) % scoreBoard.size
    }

    fun getValue(i: Int): Int {
        return scoreBoard[i].toString().toInt()
    }

}
