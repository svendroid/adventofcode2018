package day9

import org.junit.jupiter.api.Test

internal class CircleTest {

    @Test
    fun add() {
        var circle = Circle()
        for (i in 1..24) {
            circle.add(i)
        }
    }
}