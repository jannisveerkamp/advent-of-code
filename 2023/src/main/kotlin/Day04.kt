import kotlin.math.pow

private fun solveDay04a(input: String): Int = input.split("\n").sumOf { line ->
    val (_, numbers) = line.split(": ")
    val (winning, my) = numbers.replace("  ", " ").split(" | ")
    val winningNumbers = winning.split(" ")
    val myNumbers = my.split(" ")

    val myWinningNumbers = myNumbers.count { number ->
        winningNumbers.contains(number)
    }
    when (myWinningNumbers) {
        0, 1 -> myWinningNumbers
        else -> 2.toDouble().pow(myWinningNumbers - 1).toInt()
    }
}

private fun solveDay04b(input: String): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day04_example.txt")
    val inputTask = readFile("day04.txt")

    println("Solution for task 1 example: ${solveDay04a(inputExample)}") // 13
    println("Solution for task 1 task:    ${solveDay04a(inputTask)}") // 25004
    println("Solution for task 2 example: ${solveDay04b(inputExample)}") // 30
    println("Solution for task 2 task:    ${solveDay04b(inputTask)}") // ???
}

