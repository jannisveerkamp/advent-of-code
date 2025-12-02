private fun parseDay02(input: String): List<LongRange> = input.split(",").map {
    val (first, second) = it.split("-")
    first.toLong()..second.toLong()
}

private fun String.isInvalid(): Boolean = substring(0, length / 2) == substring(length / 2, length)

private fun String.isInvalidB(): Boolean = (1..(length / 2)).any { chunked(it).toSet().size == 1 }

private fun solveDay02a(input: String): Long = parseDay02(input).sumOf { range ->
    range.sumOf { current -> current.takeIf { current.toString().isInvalid() } ?: 0 }
}

private fun solveDay02b(input: String): Long = parseDay02(input).sumOf { range ->
    range.sumOf { current -> current.takeIf { current.toString().isInvalidB() } ?: 0 }
}

fun main() {
    val inputExample = readFile("day02_example.txt")
    val inputTask = readFile("day02.txt")

    println("Solution for task 1 example: ${solveDay02a(inputExample)}") // 1227775554
    println("Solution for task 1 task:    ${solveDay02a(inputTask)}") // 18700015741
    println("Solution for task 2 example: ${solveDay02b(inputExample)}") // 4174379265
    println("Solution for task 2 task:    ${solveDay02b(inputTask)}") // 20077272987
}

