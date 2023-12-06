private fun parseRace(input: String): Map<Int, Int> {
    val lines = input.split("\n").map { line ->
        line.split("\\s+".toRegex()).drop(1).map { it.toInt() }
    }
    return lines[0].zip(lines[1]).associate { it }
}

private fun solveDay06a(input: String): Long {
    val race = parseRace(input)
    return 0
}

private fun solveDay06b(input: String): Long {
    return 0
}

fun main() {
    val inputExample = readFile("day06_example.txt")
    val inputTask = readFile("day06.txt")

    println("Solution for task 1 example: ${solveDay06a(inputExample)}") // 288
    println("Solution for task 1 task:    ${solveDay06a(inputTask)}") // ???
    println("Solution for task 2 example: ${solveDay06b(inputExample)}") // ???
    println("Solution for task 2 task:    ${solveDay06b(inputTask)}") // ???
}

