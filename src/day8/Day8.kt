package day8

import java.io.File

fun main() {

    val input = File(ClassLoader.getSystemResource("input_8.txt").toURI()).readText()
    val day8 = Day8()

    println("Result part1: " + day8.getSumOfMetaData(input))
    println("Result part2: " + day8.getValueOfRootNode(input))

}

class Day8 {

    private var nodes = ArrayList<Node>()

    fun getSumOfMetaData(input: String): Int {

        val inNodes = input.split(' ').map { it.toInt() }.toIntArray()

        readNode(inNodes, 0)

        return nodes.map { it.metaDataSum() }.sum()
    }


    fun readNode(input: IntArray, start: Int, parent: Node? = null): Int {
        var i = start
        val numChildren = input[i]
        val numMetaData = input[++i]

        val node = Node(numChildren, numMetaData)

        for (cIdx in 0..(numChildren - 1)) {
            i = readNode(input, ++i, node)
        }

        for (mIdx in node.metaData.indices) {
            node.metaData[mIdx] += input[++i]
        }

        nodes.add(node)
        parent?.children?.add(node)

        return i
    }

    fun getValueOfRootNode(input: String): Int {
        val inNodes = input.split(' ').map { it.toInt() }.toIntArray()
        nodes.clear()
        readNode(input = inNodes, start = 0)
        return nodes.last().getValue()
    }


}

data class Node(val numChildren: Int, val numMetadata: Int) {

    var metaData: IntArray = IntArray(numMetadata)
    var children: ArrayList<Node?> = ArrayList(numChildren)

    fun getValue(): Int {
        var value = 0
        if (numChildren == 0) {
            value = metaDataSum()
        } else {
            for (childIdx in metaData) {
                val i = childIdx - 1
                val child = if (i <= children.size - 1 && i >= 0) children[i] else null
                value += child?.getValue() ?: 0
            }
        }
        return value
    }

    fun metaDataSum(): Int {
        return metaData.sum()
    }


}