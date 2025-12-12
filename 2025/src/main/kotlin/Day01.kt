import kotlin.math.abs
import kotlin.math.sign

private fun parseDay01(input: String): List<Int> {
    return input.lines().map { line -> line.replace("L", "-").replace("R", "").toInt() }
}

private fun solveDay01a(input: String): Int {
    var current = 50
    return parseDay01(input).count { distance ->
        current = (current + distance) % 100
        current == 0
    }
}

private fun solveDay01b(input: String): Int {
    var current = 50
    return parseDay01(input).sumOf { distance ->
        val next = current + distance
        val numberOfZeros = abs(next) / 100 + if (next.sign * current.sign == -1 || next == 0) 1 else 0
        current = next % 100
        numberOfZeros
    }
}

fun main() {
    val inputExample = readFile("day01_example.txt")
    val inputTask = readFile("day01.txt")

    println("Solution for task 1 example: ${solveDay01a(inputExample)}") // 3
    println("Solution for task 1 task:    ${solveDay01a(inputTask)}") // 1139
    println("Solution for task 2 example: ${solveDay01b(inputExample)}") // 6
    println("Solution for task 2 task:    ${solveDay01b(inputTask)}") // 6684
}

