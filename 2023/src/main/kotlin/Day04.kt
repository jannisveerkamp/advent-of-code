import kotlin.math.pow

private fun parseLine(line: String): Pair<Int, Int> {
    val (card, numbers) = line.split(": ")
    val cardNumber = card.removePrefix("Card ").trim().toInt()
    val (winning, myNumbers) = numbers.replace("  ", " ").split(" | ")
    val winningNumbers = winning.split(" ")
    return cardNumber to myNumbers.split(" ").count { number ->
        winningNumbers.contains(number)
    }
}

private fun solveDay04a(input: String): Int = input.split("\n").sumOf { line ->
    val (_, myWinningNumbers) = parseLine(line)
    2.toDouble().pow(myWinningNumbers - 1).toInt()
}

private fun solveDay04b(input: String): Int {
    val extraCopies = mutableMapOf<Int, Int>()
    return input.split("\n").sumOf { line ->
        val (cardNumber, myWinningNumbers) = parseLine(line)
        val currentExtraCopies = extraCopies[cardNumber] ?: 0
        (0 until myWinningNumbers).forEach {
            extraCopies[cardNumber + it + 1] = (extraCopies[cardNumber + it + 1] ?: 0) + 1 + currentExtraCopies
        }
        currentExtraCopies + 1
    }
}

fun main() {
    val inputExample = readFile("day04_example.txt")
    val inputTask = readFile("day04.txt")

    println("Solution for task 1 example: ${solveDay04a(inputExample)}") // 13
    println("Solution for task 1 task:    ${solveDay04a(inputTask)}") // 25004
    println("Solution for task 2 example: ${solveDay04b(inputExample)}") // 30
    println("Solution for task 2 task:    ${solveDay04b(inputTask)}") // 14427616
}

