import common.lcm

private data class RLInstruction(val src: String, val dstL: String, val dstR: String)

private fun parseDay8(input: String): Pair<String, List<RLInstruction>> {
    val (rl, allInstructions) = input.split("\n\n")
    val instructions = allInstructions.split("\n").map { line ->
        RLInstruction(line.substring(0, 3), line.substring(7, 10), line.substring(12, 15))
    }
    return rl to instructions
}

private fun findWay(
    rl: String,
    instructions: List<RLInstruction>,
    startNode: RLInstruction,
    goals: List<RLInstruction>
): Int {
    var position = 0
    var currentNote = startNode
    while (true) {
        val step = rl[position % rl.length]
        currentNote = if (step == 'R') {
            instructions.first { it.src == currentNote.dstR }
        } else {
            instructions.first { it.src == currentNote.dstL }
        }
        position++
        if (currentNote in goals) {
            return position
        }
    }
}

private fun solveDay08a(input: String): Int {
    val (rl, instructions) = parseDay8(input)
    val startNote = instructions.first { it.src == "AAA" }
    val goal = instructions.first { it.src == "ZZZ" }
    return findWay(rl, instructions, startNote, listOf(goal))
}

private fun solveDay08b(input: String): Long {
    val (rl, instructions) = parseDay8(input)
    val startNodes = instructions.filter { it.src.endsWith("A") }
    val goalNodes = instructions.filter { it.src.endsWith("Z") }
    val results = startNodes.map { startNode -> findWay(rl, instructions, startNode, goalNodes).toLong() }
    return lcm(results)
}

fun main() {
    val inputExample = readFile("day08_example.txt")
    val inputExample2 = readFile("day08_example_2.txt")
    val inputExample3 = readFile("day08_example_3.txt")
    val inputTask = readFile("day08.txt")

    println("Solution for task 1 example: ${solveDay08a(inputExample)}") // 2
    println("Solution for task 1 example: ${solveDay08a(inputExample2)}") // 6
    println("Solution for task 1 task:    ${solveDay08a(inputTask)}") // 17873
    println("Solution for task 2 example: ${solveDay08b(inputExample3)}") // 6
    println("Solution for task 2 task:    ${solveDay08b(inputTask)}") // 15746133679061
}

