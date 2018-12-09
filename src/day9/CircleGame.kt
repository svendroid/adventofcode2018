package day9

import day9.CircleGame.Companion.logEnabled
import java.math.BigInteger
import java.util.*
import kotlin.math.absoluteValue

fun main() {
    val circleGame = CircleGame()
    logEnabled = false //toString of Circle is expensive only turn on logging for example input
    println("Result part1: " + circleGame.getWinningScore(416, 71617))
    println("Result part2: " + circleGame.getWinningScore(416, 71617 * 100))
}

class CircleGame() {

    companion object {
        var logEnabled = false
    }

    fun getWinningScore(numPlayers: Int, numMarbles: Int): BigInteger {

        val circle = Circle()

        val players = Array(numPlayers) { i -> Player(id = i + 1) }

        for (marble in 1..numMarbles) {
            val player = players[(marble - 1) % numPlayers]


            player.score += circle.add(marble).toBigInteger()
            if(logEnabled) println("[${player.id}] $circle")//turn of logging on final run
        }

        for (player in players) {
            if(logEnabled) println("${player.id}: ${player.score}")
        }

        val winner = players.maxBy { it -> it.score }

        return winner!!.score
    }

}

data class Player(var score: BigInteger = 0.toBigInteger(), var id: Int)

class Circle {

    private val circle: LinkedList<Int> = LinkedList()

    private var node: MutableListIterator<Int>

    init {
        circle.add(0)
        node = circle.listIterator(0)
    }

    fun add(marble: Int): Int {
        var score = 0

        if (marble != 0 && marble % 23 == 0) {
            score += moveNode(-7)
            if(logEnabled) print("Remove $score and ignore marble: $marble")
            node.remove()
            moveNode(1)
            score += marble
            if(logEnabled) println(", score: $score")
        } else {
            moveNode(1)
            node.add(marble)
        }

        return score
    }

    private fun moveNode(steps: Int): Int {
        var value = 0
        if (steps > 0) {
            for (step in 1..steps) {
                if (!node.hasNext()) {
                    node = circle.listIterator(0)
                }
                value = node.next()

            }
        } else {
            for (step in 0..steps.absoluteValue) {
                if (!node.hasPrevious()) {
                    node = circle.listIterator(circle.lastIndex)
                } else {
                    value = node.previous()
                }
            }
        }
        return value
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for ((i, marble) in circle.withIndex()) {
            builder.append(if (i == node.nextIndex() - 1) "(" else " ")
            builder.append(marble)
            builder.append(if (i == node.nextIndex() - 1) ")" else " ")
        }
        return builder.toString()
    }


}