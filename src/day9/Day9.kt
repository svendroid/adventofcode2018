package day9

import java.io.File

fun main() {

    val input = File(ClassLoader.getSystemResource("input_8.txt").toURI()).readText()
    val day9 = Day9()

    println("Result part1: " + day9.getWinningScore(416, 71617))
    println("Result part2: " + day9.getWinningScore(416, 71617*100))

}

class Day9 {


    fun getWinningScore(numPlayers: Int, numMarbles: Int): Int {

        var circle = Circle()

        var players = Array(numPlayers) { i -> Player(id = i + 1) }

        for (marble in 1..numMarbles) {
            var player = players[(marble-1) % numPlayers]


            player.score += circle.add(marble)
//            print("[${player.id}]" )
//            println(circle.toString())
        }

//        for (player in players) {
//            println("${player.id}: ${player.score}")
//        }

        val winner = players.maxBy { it -> it.score }

        return winner!!.score
    }

}

data class Player(var score: Int = 0, var id: Int)

class Circle {

    val circle: MutableList<Int> = ArrayList()
    var current: Int = 0

    init {
        circle.add(0)
    }

    fun add(marble: Int): Int {
        var score = 0

        if (marble != 0 && marble % 23 == 0) {
            var idxToRemove = getSafeIdx(current - 7)
            score += circle.removeAt(idxToRemove)
            score += marble
            current = getSafeIdx(idxToRemove)
        } else {
            current = getSafeIdx(current + 1) + 1
            circle.add(current, marble)
        }

        return score
    }

    fun getSafeIdx(i: Int): Int {
        val size = circle.size
        if (i < 0) return (i % size + size) % size
        else return i % size
    }

    override fun toString(): String {
        var builder: StringBuilder = StringBuilder()
        for ((i, marble) in circle.withIndex()) {
            builder.append(if (i == current) "(" else " ")
            builder.append(marble)
            builder.append(if (i == current) ")" else " ")
        }
        return builder.toString()
    }


}