package day7

import java.io.File
import java.util.*

fun main() {

    val input = File(ClassLoader.getSystemResource("input_7.txt").toURI()).readText()
    val day7 = Day7()

    println("Result for part1: " + day7.getInstructionsOrder(input))
    println("Result for part2: " + day7.getCompletionTime(input, 5, 60))

}

class Day7(var printDebug: Boolean = false) {

    private lateinit var instructionsMap: HashMap<Char, Instruction>

    fun getInstructionsOrder(input: String): String {
        prepareInstructionMap(input)

        val availableInstruction = TreeSet<Instruction>(Comparator<Instruction> { x, y -> x.id.compareTo(y.id) })
        availableInstruction.addAll(instructionsMap.values.filter { it.isAvailable() })

        var result = ""
        while (availableInstruction.isNotEmpty()) {
            val first = availableInstruction.pollFirst()
            first.leftWork = 0
            result = result.plus(first.id)
            availableInstruction.addAll(first.nextInstructions.filter { it.isAvailable() })
        }

        return result
    }

    fun getCompletionTime(input: String, numWorkers: Int, timePerInstruction: Int): Int {
        prepareInstructionMap(input)

        val availableInstruction = TreeSet<Instruction>(Comparator<Instruction> { x, y -> x.id.compareTo(y.id) })
        availableInstruction.addAll(instructionsMap.values.filter { it.isAvailable() })

        var seconds = 0
        val workers: Array<Instruction?> = Array(numWorkers) { null }
        var stillWorking = true
        while (availableInstruction.isNotEmpty() || stillWorking) {
            if (printDebug) print("%2d: ".format(seconds))
            for (i in workers.indices) {
                if (workers[i] == null) {
                    workers[i] = availableInstruction.pollFirst()
                    if (workers[i] != null) {
                        workers[i]!!.leftWork += timePerInstruction
                    }
                }

                if (workers[i] != null) {
                    workers[i]!!.work()
                    if (printDebug) print(workers[i]!!.id)
                } else {
                    if (printDebug) print(".")
                }
            }
            if (printDebug) println()

            for (i in workers.indices) {
                if (workers[i] != null && workers[i]!!.isDone()) {
                    availableInstruction.addAll(workers[i]!!.nextInstructions.filter { it.isAvailable() })
                    workers[i] = null
                }
            }

            stillWorking = workers.filter({ it != null }).isNotEmpty()
            seconds++
        }

        return seconds
    }

    fun prepareInstructionMap(input: String) {
        val regex = "^Step (?<instr>.) must be finished before step (?<next>.) can begin.\$".toRegex()

        instructionsMap = HashMap<Char, Instruction>()
        for (line in input.lines()) {
            val groups = regex.matchEntire(line)!!.groups

            val instrId = groups["instr"]!!.value[0]
            val nextId = groups["next"]!!.value[0]

            var instr: Instruction? = instructionsMap.get(instrId)
            var nextInstr: Instruction? = instructionsMap.get(nextId)
            if (instr == null) {
                instr = Instruction(instrId)
                instructionsMap.put(instrId, instr)
            }
            if (nextInstr == null) {
                nextInstr = Instruction(nextId)
                instructionsMap.put(nextId, nextInstr)
            }
            instr.nextInstructions.add(nextInstr)
            instr.nextInstructions.sortBy { it.id }
            nextInstr.prevInstructions.add(instr)
            nextInstr.nextInstructions.sortBy { it.id }
        }
    }

}