private data class Day06Operation(val numbers: List<Long>, val isMult: Boolean)

private fun parseDay06(input: String): List<Day06Operation> {
    val rows = input.lines().map { it.trim().split("\\s+".toRegex()) }

    var operations = mutableListOf<Day06Operation>()
    (0..rows.first().lastIndex).forEach { i ->
        val numbers = (0 until rows.lastIndex).map { row ->
            val item = rows[row][i]
            item.toLong()
        }
        operations.add(Day06Operation(numbers, rows.last()[i] == "*"))
    }
    return operations
}

private fun solveDay06a(input: String): Long {
    val operations = parseDay06(input)
    return operations.sumOf { operation ->
        val result = if (operation.isMult) {
            operation.numbers.fold(1L) { old, new -> old * new }
        } else {
            operation.numbers.fold(0L) { old, new -> old + new }
        }
        result
    }
}

private fun parseDay06b(input: String): List<Day06Operation> {
    val rows = input.split("\n")

    var currentOperationIsMult = true
    var currentNumbers = mutableListOf<Long>()
    val allOperations = mutableListOf<Day06Operation>()

    (0 until rows.maxBy { it.length }.length).mapNotNull { column ->
        val currentNumber = (0 until rows.lastIndex).mapNotNull {
            rows.getOrNull(it)?.getOrNull(column)?.takeIf { it != ' ' }
        }.joinToString("")
        when (rows.last().getOrNull(column)) {
            '*' -> {
                if (currentNumbers.isNotEmpty()) {
                    allOperations.add(Day06Operation(currentNumbers.toList(), currentOperationIsMult))
                }
                currentOperationIsMult = true
                currentNumbers.clear()
            }

            '+' -> {
                if (currentNumbers.isNotEmpty()) {
                    allOperations.add(Day06Operation(currentNumbers.toList(), currentOperationIsMult))
                }
                currentOperationIsMult = false
                currentNumbers.clear()
            }

            else -> {
            }
        }
        if (currentNumber != "") {
            currentNumbers += currentNumber.toLong()
        }
    }
    allOperations.add(Day06Operation(currentNumbers, currentOperationIsMult))
    return allOperations
}


private fun solveDay06b(input: String): Long = parseDay06b(input).sumOf { operation ->
    val result = if (operation.isMult) {
        operation.numbers.fold(1L) { old, new -> old * new }
    } else {
        operation.numbers.fold(0L) { old, new -> old + new }
    }
    result
}

fun main() {
    val inputExample = readFile("day06_example.txt")
    val inputTask = readFile("day06.txt")

    println("Solution for task 1 example: ${solveDay06a(inputExample)}") // 4277556
    println("Solution for task 1 task:    ${solveDay06a(inputTask)}") // 6635273135233
    println("Solution for task 2 example: ${solveDay06b(inputExample)}") // 3263827
    println("Solution for task 2 task:    ${solveDay06b(inputTask)}") // 12542543681221
}

