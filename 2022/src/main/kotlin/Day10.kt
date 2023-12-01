import kotlin.math.abs

private sealed interface Instruction {
    object NoOp : Instruction
    data class Add(val value: Int) : Instruction

    companion object {
        fun fromString(input: String): Instruction = when (input) {
            "noop" -> NoOp
            else -> Add(input.removePrefix("addx ").toInt())
        }
    }
}

fun solveDay10(input: String): Int {
    val instructions = input.split("\n").map { Instruction.fromString(it) }
    val output = mutableListOf<Char>()

    var x = 1
    var pendingInstruction: Instruction? = null
    var iteration = 1
    var cursor = 0
    var signalStrengths = 0

    while (iteration <= 240) {
        if (iteration % 40 == 20) {
            val signalStrength = x * iteration
            signalStrengths += signalStrength
        }

        if (abs((output.size % 40) - x) <= 1) {
            output.add('#')
        } else {
            output.add('.')
        }

        when (val instruction = instructions[cursor]) {
            is Instruction.NoOp -> cursor++
            is Instruction.Add -> {
                if (pendingInstruction == null) {
                    pendingInstruction = instruction
                } else {
                    x += instruction.value
                    cursor++
                    pendingInstruction = null
                }
            }
        }


        iteration++
    }
    println(output.chunked(40).joinToString("\n") { String(it.toCharArray()) })
    return signalStrengths
}

fun main() {
    val inputExample = readFile("day10_example.txt")
    val inputTask = readFile("day10.txt")

    println("Solution for task 1 example: ${solveDay10(inputExample)}") // 13140
    println("Solution for task 1 task:    ${solveDay10(inputTask)}") // 14060 and PAPKFKEJ
}

