private data class RLInstruction(val src: String, val dstL: String, val dstR: String)

private fun solveDay08(input: String): Int {
    val (rl, allInstructions) = input.split("\n\n")
    val instructions = allInstructions.split("\n").map { line ->
        RLInstruction(line.substring(0, 3), line.substring(7, 10), line.substring(12, 15))
    }
    var position = 0
    var currentNote = instructions.first { it.src == "AAA" }
    val goal = instructions.first { it.src == "ZZZ" }

    while (true) {
        val step = rl[position % rl.length]
        currentNote = if (step == 'R') {
            instructions.first { it.src == currentNote.dstR }
        } else {
            instructions.first { it.src == currentNote.dstL }
        }
        position++
        if (currentNote == goal) {
            return position
        }
    }
}

fun main() {
    val inputExample = readFile("day08_example.txt")
    val inputExample2 = readFile("day08_example_2.txt")
    val inputTask = readFile("day08.txt")

    println("Solution for task 1 example: ${solveDay08(inputExample)}") // 2
    println("Solution for task 1 example: ${solveDay08(inputExample2)}") // 6
    println("Solution for task 1 task:    ${solveDay08(inputTask)}") // 17873
    println("Solution for task 2 example: ${solveDay08(inputExample)}") // ???
    println("Solution for task 2 task:    ${solveDay08(inputTask)}") // ???
}

