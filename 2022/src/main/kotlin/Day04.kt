fun solveDay04a(input: String): Int = parseData(input).sumOf { assignment ->
    if (
        (assignment.first.contains(assignment.second.first) && assignment.first.contains(assignment.second.last)) ||
        (assignment.second.contains(assignment.first.first) && assignment.second.contains(assignment.first.last))
    ) {
        1 as Int
    } else {
        0 as Int
    }
}

fun solveDay04b(input: String): Int = parseData(input).sumOf { assignment ->
    if ((assignment.first).intersect(assignment.second).isEmpty()) {
        0 as Int
    } else {
        1 as Int
    }
}

private fun parseData(input: String): List<Pair<IntRange, IntRange>> {
    return input.split("\n").map { assignment ->
        val (first, second) = assignment.split(",")
        val (firstStart, firstEnd) = first.split("-").map { it.toInt() }
        val (secondStart, secondEnd) = second.split("-").map { it.toInt() }
        firstStart..firstEnd to secondStart..secondEnd
    }
}

fun main() {
    val inputExample = readFile("day04_example.txt")
    val inputTask = readFile("day04.txt")

    println("Solution for task 1 example: ${solveDay04a(inputExample)}") // 2
    println("Solution for task 1 task:    ${solveDay04a(inputTask)}") // 567
    println("Solution for task 2 example: ${solveDay04b(inputExample)}") // 4
    println("Solution for task 2 task:    ${solveDay04b(inputTask)}") // 907
}

