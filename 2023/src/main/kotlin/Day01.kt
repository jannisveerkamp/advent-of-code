private fun solveDay01a(input: String): Int = input.split("\n").sumOf { line ->
    ("${line.first { it.isDigit() }}${line.last { it.isDigit() }}").toInt()
}

private val numbers = mapOf(
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

private fun solveDay01b(input: String): Int {
    return input.split("\n").sumOf { line ->
        val firstDigit = line.indexOfFirst { it.isDigit() }
        val first = numbers
            .filter { line.contains(it.key) }
            .minByOrNull {
                line.indexOf(it.key)
            }
        val firstNumber = if (first != null) {
            val firstIndex = line.indexOf(first.key)
            if (firstDigit != -1 && firstDigit < firstIndex) {
                line[firstDigit].digitToInt()
            } else {
                first.value
            }
        } else {
            line[firstDigit].digitToInt()
        }

        val lastDigit = line.indexOfLast { it.isDigit() }
        val last = numbers
            .filter { line.contains(it.key) }
            .maxByOrNull {
                line.lastIndexOf(it.key)
            }
        val lastNumber = if (last != null) {
            val lastIndex = line.lastIndexOf(last.key)
            if (lastDigit != -1 && lastDigit > lastIndex) {
                line[lastDigit].digitToInt()
            } else {
                last.value
            }
        } else {
            line[lastDigit].digitToInt()
        }
        val number = "${firstNumber}${lastNumber}"
        number.toInt()
    }
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

