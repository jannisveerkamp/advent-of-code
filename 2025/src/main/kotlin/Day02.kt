private fun parseDay02(input: String): List<LongRange> = input.split(",").map {
    val (first, second) = it.split("-")
    first.toLong()..second.toLong()
}

private fun Long.isInvalid(): Boolean {
    val string = toString()
    return string.substring(0, string.length / 2) == string.substring(string.length / 2, string.length)
}

private fun Long.isInvalidB(): Boolean {
    val string = toString()
    return (1..(string.length / 2)).any {
        val list = string.chunked(it)
        val set = list.toSet()
        set.size == 1
    }
}

private fun solveDay02a(input: String): Long = parseDay02(input).sumOf { range ->
    range.sumOf { current ->
        current.takeIf { current.isInvalid() } ?: 0
    }
}

private fun solveDay02b(input: String): Long = parseDay02(input).sumOf { range ->
    range.sumOf { current ->
        current.takeIf { current.isInvalidB() } ?: 0
    }
}

fun main() {
    val inputExample = readFile("day02_example.txt")
    val inputTask = readFile("day02.txt")

    println("Solution for task 1 example: ${solveDay02a(inputExample)}") // 1227775554
    println("Solution for task 1 task:    ${solveDay02a(inputTask)}") // 18700015741
    println("Solution for task 2 example: ${solveDay02b(inputExample)}") // 4174379265
    println("Solution for task 2 task:    ${solveDay02b(inputTask)}") // 20077272987
}

