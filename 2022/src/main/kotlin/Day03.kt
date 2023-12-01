fun solveDay03a(input: String): Int = input.split("\n").sumOf { line ->
    intersection(
        line.substring(0, line.length / 2).toSet(),
        line.substring(line.length / 2).toSet()
    )
}

fun solveDay03b(input: String): Int = input.split("\n").chunked(3).sumOf { chunk ->
    intersection(chunk[0].toSet(), chunk[1].toSet(), chunk[2].toSet())
}

private fun intersection(vararg list: Set<Char>): Int = priority(list.reduce { acc, it -> acc.intersect(it) }.first())

/**
 * Lowercase item types a through z have priorities 1 through 26.
 * Uppercase item types A through Z have priorities 27 through 52.
 */
private fun priority(common: Char): Int = if (common.isUpperCase()) {
    common - 'A' + 27
} else {
    common - 'a' + 1
}

fun main() {
    val inputExample = readFile("day03_example.txt")
    val inputTask = readFile("day03.txt")

    println("Solution for task 1 example: ${solveDay03a(inputExample)}") // 157
    println("Solution for task 1 task:    ${solveDay03a(inputTask)}") // 7821
    println("Solution for task 2 example: ${solveDay03b(inputExample)}") // 70
    println("Solution for task 2 task:    ${solveDay03b(inputTask)}") // 2752
}

