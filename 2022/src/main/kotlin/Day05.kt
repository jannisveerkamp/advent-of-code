private val regex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

private data class MoveInstruction(val count: Int, val from: Int, val to: Int)

private fun parseField(input: String): List<ArrayDeque<Char>> {
    val lines = input.split("\n")
    val columns = lines.last().trim().split("   ").last().toInt()
    val result = List(columns) { ArrayDeque<Char>() }
    lines.reversed().forEach { line ->
        line.chunked(4).map { it[1] }.forEachIndexed { index, char ->
            if (char.isLetter()) {
                result[index].addLast(char)
            }
        }
    }
    return result
}

private fun parseInstructions(instructionsList: String) = instructionsList.split("\n").map { instruction ->
    val (count, from, to) = regex.find(instruction)!!.destructured
    MoveInstruction(count.toInt(), from.toInt(), to.toInt())
}

fun solveDay05(input: String, reversed: Boolean): String {
    val (fieldString, instructionsList) = input.split("\n\n")
    val field = parseField(fieldString)
    val instructions = parseInstructions(instructionsList)

    instructions.forEach { instruction ->
        val from = field[instruction.from - 1]
        val to = field[instruction.to - 1]
        val removed = (0 until instruction.count).map {
            from.removeLast()
        }
        (if (reversed) removed.reversed() else removed).forEach {
            to.addLast(it)
        }
    }

    return field.map { it.last() }.toCharArray().concatToString()
}

fun main() {
    val inputExample = readFile("day05_example.txt")
    val inputTask = readFile("day05.txt")

    println("Solution for task 1 example: ${solveDay05(inputExample, false)}") // CMZ
    println("Solution for task 1 task:    ${solveDay05(inputTask, false)}") // CNSZFDVLJ
    println("Solution for task 2 example: ${solveDay05(inputExample, true)}") // MCD
    println("Solution for task 2 task:    ${solveDay05(inputTask, true)}") // QNDWLMGNS
}

