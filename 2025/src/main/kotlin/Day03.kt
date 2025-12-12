private fun parseDay03(input: String): List<List<Int>> = input.lines().map { it.map { it.digitToInt() } }

private fun solveDay03(input: String, digits: Int): Long = parseDay03(input).sumOf { bank ->
    var currentBank = bank.toList()
    (0 until digits).map {
        val max = currentBank.dropLast(digits - 1 - it).max()
        val maxPos = currentBank.indexOf(max)
        currentBank = currentBank.drop(maxPos + 1)
        max
    }.joinToString("").toLong()
}

fun main() {
    val inputExample = readFile("day03_example.txt")
    val inputTask = readFile("day03.txt")

    println("Solution for task 1 example: ${solveDay03(inputExample, 2)}") // 357
    println("Solution for task 1 task:    ${solveDay03(inputTask, 2)}") // 17432
    println("Solution for task 2 example: ${solveDay03(inputExample, 12)}") // 3121910778619
    println("Solution for task 2 task:    ${solveDay03(inputTask, 12)}") // 173065202451341
}

