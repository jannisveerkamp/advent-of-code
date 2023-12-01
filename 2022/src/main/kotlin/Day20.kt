private data class Item(val number: Long, val originalPosition: Int)

private const val decryptionKey = 811_589_153L

fun solveDay20a(input: String, iterations: Int, key: Long): Long {
    val originalSequence = input.split("\n").map { it.toLong() * key }
        .mapIndexed { index, item -> Item(item, index) }
    val positions = originalSequence.toMutableList()
    val numberOfNumbers = positions.size

    repeat(iterations) {
        originalSequence.forEach { number ->
            val index = positions.indexOf(number)
            positions.removeAt(index)
            positions.add((index + number.number).mod(numberOfNumbers - 1), number)
        }
    }

    val zero = positions.indexOfFirst { it.number == 0L }
    return listOf(1000L, 2000L, 3000L).sumOf { positions[((zero + it) % numberOfNumbers).toInt()].number }
}

fun main() {
    val inputExample = readFile("day20_example.txt")
    val inputTask = readFile("day20.txt")

    println("Solution for task 1 example: ${solveDay20a(inputExample, 1, 1)}") // 3
    println("Solution for task 1 task:    ${solveDay20a(inputTask, 1, 1)}") // 988
    println("Solution for task 2 example: ${solveDay20a(inputExample, 10, decryptionKey)}") // 1623178306
    println("Solution for task 2 task:    ${solveDay20a(inputTask, 10, decryptionKey)}") // 7768531372516
}

