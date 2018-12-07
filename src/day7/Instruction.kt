package day7

data class Instruction(val id: Char,
                       val nextInstructions: MutableList<Instruction> = ArrayList(),
                       val prevInstructions: MutableList<Instruction> = ArrayList()) {

    var leftWork: Int

    init {
        leftWork = id.toInt() - 'A'.toInt() + 1
    }

    override fun toString(): String {
        return "$id"
    }

    fun isAvailable(): Boolean {
        for (prevInstruction in prevInstructions) {
            if (!prevInstruction.isDone()) {
                return false
            }
        }
        return true
    }

    fun isDone(): Boolean {
        return leftWork == 0
    }

    fun work() {
        leftWork -= 1
    }
}