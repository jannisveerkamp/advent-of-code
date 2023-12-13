import common.transpose

private fun findReflection(mirror: List<String>, multiplier: Int, ignoreIndex: Int? = null): Pair<Int, Int> {
    (1 until mirror.size).forEach { index ->
        val (first, second) = mirror.chunked(index)
        val firstSize = first.size
        val secondSize = second.size
        if (first.reversed().take(secondSize) == second.take(firstSize) && index != ignoreIndex) {
            return index to index * multiplier
        }
    }
    return -1 to 0
}

private fun List<String>.transposed(): List<String> = map { it.toList() }.transpose().map { it.joinToString("") }

private fun List<String>.replaceChar(index: Int): List<String> = mapIndexed { y, line ->
    line.mapIndexed { x, char ->
        if (y * line.length + x == index) {
            if (char == '.') '#' else '.'
        } else {
            char
        }
    }.joinToString("")
}

private fun solveDay13a(input: String): Int {
    val mirrors = input.split("\n\n").map { it.split("\n") }
    var counter = 0
    mirrors.forEach { mirror ->
        // Rows
        counter += findReflection(mirror, 100).second
        // Columns
        counter += findReflection(mirror.transposed(), 1).second
    }

    return counter
}

private fun solveDay13b(input: String): Int {
    val mirrors = input.split("\n\n").map { it.split("\n") }

    var counter = 0
    mirrors.forEach { mirror ->
        val transposed = mirror.transposed()
        val size = mirror.size * mirror.first().length

        // Rows
        val baseRow = findReflection(mirror, 100).first
        counter += (0 until size).sumOf { index ->
            val row = findReflection(mirror.replaceChar(index), 100, baseRow)
            row.second
        }

        // Columns
        val baseColumn = findReflection(transposed, 1).first
        counter += (0 until size).sumOf { index ->
            val column = findReflection(transposed.replaceChar(index), 1, baseColumn)
            column.second
        }
    }
    return counter / 2
}

fun main() {
    val inputExample = readFile("day13_example.txt")
    val inputTask = readFile("day13.txt")

    println("Solution for task 1 example: ${solveDay13a(inputExample)}") // 405
    println("Solution for task 1 task:    ${solveDay13a(inputTask)}") // 34821
    println("Solution for task 2 example: ${solveDay13b(inputExample)}") // 400
    println("Solution for task 2 task:    ${solveDay13b(inputTask)}") // 36919
}

