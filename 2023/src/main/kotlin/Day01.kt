private fun solveDay01a(input: String): Int = input.split("\n").sumOf { line ->
    ("${line.first { it.isDigit() }}${line.last { it.isDigit() }}").toInt()
}


private val numbersMapping = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

private val numbers = numbersMapping.keys

private fun solveDay01b(input: String): Int = input.split("\n").sumOf { line ->
    val firstDigit = line.indexOfFirst { it.isDigit() }
    val firstWord = line.findAnyOf(numbers)

    val firstNumber = if (firstWord == null || (firstDigit < firstWord.first && firstDigit != -1)) {
        line[firstDigit]
    } else {
        numbersMapping[firstWord.second]
    }

    val lastDigit = line.indexOfLast { it.isDigit() }
    val lastWord = line.findLastAnyOf(numbers)

    val lastNumber = if (lastWord == null || (lastDigit > lastWord.first && lastDigit != -1)) {
        line[lastDigit]
    } else {
        numbersMapping[lastWord.second]
    }

    val number = "${firstNumber}${lastNumber}"
    number.toInt()
}

fun main() {
    val inputExample = readFile("day01_example_a.txt")
    val inputExample2 = readFile("day01_example_b.txt")
    val inputTask = readFile("day01.txt")

    println("Solution for task 1 example: ${solveDay01a(inputExample)}") // 142
    println("Solution for task 1 task:    ${solveDay01a(inputTask)}") // 54304
    println("Solution for task 2 example: ${solveDay01b(inputExample2)}") // 281
    println("Solution for task 2 task:    ${solveDay01b(inputTask)}") // 54418
}

