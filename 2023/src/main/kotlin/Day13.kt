import common.transpose

private fun findReflection(mirror: List<String>, multiplier: Int): Int {
    (1 until mirror.size).forEach { index ->
        val (first, second) = mirror.chunked(index)
        val firstSize = first.size
        val secondSize = second.size
        if (first.reversed().take(secondSize) == second.take(firstSize)) {
           return index * multiplier
        }
    }
    return 0
}

private fun solveDay13(input: String): Int {
    val mirrors = input.split("\n\n").map { it.split("\n") }

    var counter = 0
    mirrors.forEach { mirror ->
        // Rows
        counter += findReflection(mirror, 100)

        // Columns
        val transposed = mirror.map { it.toList() }.transpose().map {it.joinToString("")}
        counter += findReflection(transposed, 1)
    }

    return counter
}

fun main() {
    val inputExample = readFile("day13_example.txt")
    val inputTask = readFile("day13.txt")

    println("Solution for task 1 example: ${solveDay13(inputExample)}") // 405
    println("Solution for task 1 task:    ${solveDay13(inputTask)}") // 34821
    println("Solution for task 2 example: ${solveDay13(inputExample)}") // 400
    println("Solution for task 2 task:    ${solveDay13(inputTask)}") // ???
}

