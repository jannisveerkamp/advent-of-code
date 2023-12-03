import kotlin.math.max
import kotlin.math.min

private data class Cell(val value: Char, var marked: Boolean)

private fun parseGrid(input: String): Array<Array<Cell>> {
    return input.split("\n").map { it.map { Cell(it, false) }.toTypedArray() }.toTypedArray()
}

private fun solveDay03a(input: String): Int {
    val grid = parseGrid(input)
    val symbols = input.toSet().minus('.').filterNot { it.isDigit() }
    val xMax = grid.first().size - 1
    val yMax = grid.size - 1

    grid.forEachIndexed { y, line ->
        line.forEachIndexed { x, cell ->
            if (cell.value in symbols) {
                if (x > 0 && y > 0 && grid[y - 1][x - 1].value.isDigit()) grid[y - 1][x - 1].marked = true
                if (x > 0 && grid[y][x - 1].value.isDigit()) grid[y][x - 1].marked = true
                if (y > 0 && grid[y - 1][x].value.isDigit()) grid[y - 1][x].marked = true
                if (x < xMax && y < yMax && grid[y + 1][x + 1].value.isDigit()) grid[y + 1][x + 1].marked = true
                if (x < xMax && grid[y][x + 1].value.isDigit()) grid[y][x + 1].marked = true
                if (y < yMax && grid[y + 1][x].value.isDigit()) grid[y + 1][x].marked = true
                if (x < xMax && y > 0 && grid[y - 1][x + 1].value.isDigit()) grid[y - 1][x + 1].marked = true
                if (x > 0 && y < yMax && grid[y + 1][x - 1].value.isDigit()) grid[y + 1][x - 1].marked = true
            }
        }
    }

    var numberSum = 0

    // yes, yes...
    repeat(2) {
        grid.forEachIndexed { y, line ->
            line.forEachIndexed { x, cell ->
                if (cell.marked) {
                    if (x > 0 && grid[y][x - 1].value.isDigit()) grid[y][x - 1].marked = true
                    if (x < xMax && grid[y][x + 1].value.isDigit()) grid[y][x + 1].marked = true
                }
            }
        }
    }

    val regex = "(\\d+)".toRegex()

    grid.forEach { line ->
        val result = regex.findAll(line.joinToString("") {
            if (it.marked || !it.value.isDigit()) {
                it.value.toString()
            } else {
                ""
            }
        }
        )
        result.forEach {
            numberSum += it.value.toInt()
        }
    }

    return numberSum
}

private fun solveDay03b(input: String): Int {
    val grid = parseGrid(input)
    val xMax = grid.first().size - 1
    val yMax = grid.size - 1

    grid.forEachIndexed { y, line ->
        line.forEachIndexed { x, cell ->
            if (cell.value == '*') {
                if (x > 0 && y > 0 && grid[y - 1][x - 1].value.isDigit()) grid[y - 1][x - 1].marked = true
                if (x > 0 && grid[y][x - 1].value.isDigit()) grid[y][x - 1].marked = true
                if (y > 0 && grid[y - 1][x].value.isDigit()) grid[y - 1][x].marked = true
                if (x < xMax && y < yMax && grid[y + 1][x + 1].value.isDigit()) grid[y + 1][x + 1].marked = true
                if (x < xMax && grid[y][x + 1].value.isDigit()) grid[y][x + 1].marked = true
                if (y < yMax && grid[y + 1][x].value.isDigit()) grid[y + 1][x].marked = true
                if (x < xMax && y > 0 && grid[y - 1][x + 1].value.isDigit()) grid[y - 1][x + 1].marked = true
                if (x > 0 && y < yMax && grid[y + 1][x - 1].value.isDigit()) grid[y + 1][x - 1].marked = true
            }
        }
    }

    var numberSum = 0

    // yes, yes...
    repeat(2) {
        grid.forEachIndexed { y, line ->
            line.forEachIndexed { x, cell ->
                if (cell.marked) {
                    if (x > 0 && grid[y][x - 1].value.isDigit()) grid[y][x - 1].marked = true
                    if (x < xMax && grid[y][x + 1].value.isDigit()) grid[y][x + 1].marked = true
                }
            }
        }
    }

    val regex = "(\\d+)".toRegex()

    grid.forEach { line ->
        val result = regex.findAll(line.joinToString("") {
            if (it.marked || !it.value.isDigit()) {
                it.value.toString()
            } else {
                ""
            }
        }
        )
        result.forEach {
            numberSum += it.value.toInt()
        }
    }

    return numberSum
}


fun main() {
    val inputExample = readFile("day03_example_2.txt")
    val inputTask = readFile("day03.txt")

    println("Solution for task 1 example: ${solveDay03a(inputExample)}") // 4361
    println("Solution for task 1 task:    ${solveDay03a(inputTask)}") // 530849
    println("Solution for task 2 example: ${solveDay03b(inputExample)}") // 467835
    println("Solution for task 2 task:    ${solveDay03b(inputTask)}") // ???
}

