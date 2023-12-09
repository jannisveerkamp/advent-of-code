fun parseHistory(input: String): List<List<Int>> {
    return input.split("\n").map { it.split(" ").map { it.toInt() } }
}

private fun buildHistory(entry: List<Int>): List<List<Int>> {
    val lists = mutableListOf(entry)
    repeat(entry.size) {
        val current = lists.last()
        val windowed = current.windowed(2)
        val result = windowed.map {
            it[1] - it[0]
        }
        if (result.all { it == 0 }) {
            return lists
        } else {
            lists.add(result)
        }
    }
    return lists
}

private fun solveDay09a(input: String): Int {
    val history = parseHistory(input)
    return history.sumOf { entry ->
        val lists = buildHistory(entry)
        lists.sumOf { it.last() }
    }
}

private fun solveDay09b(input: String): Int {
    val history = parseHistory(input)
    return history.sumOf { entry ->
        val lists = buildHistory(entry)
        lists.first().first() - lists.drop(1).mapIndexed { index, value ->
            if (index % 2 == 0) value.first() else -value.first()
        }.sum()
    }
}

fun main() {
    val inputExample = readFile("day09_example.txt")
    val inputTask = readFile("day09.txt")

    println("Solution for task 1 example: ${solveDay09a(inputExample)}") // 114
    println("Solution for task 1 task:    ${solveDay09a(inputTask)}") // 1708206096
    println("Solution for task 2 example: ${solveDay09b(inputExample)}") // 2
    println("Solution for task 2 task:    ${solveDay09b(inputTask)}") // 1050
}

