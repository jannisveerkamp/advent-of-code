private const val ROCK_CUBE = '#'
private const val ROCK_ROUND = 'O'
private const val EMPTY = '.'

private fun solveDay13a(input: String): Int {
    val grid = input.split("\n").map { line ->
        line.toCharArray().toTypedArray()
    }.toTypedArray()

    var counter = 0
    grid.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (char == ROCK_ROUND) {
                var currentY = y - 1
                var currentCounter = 0
                while (currentY >= 0 && grid[currentY][x] != ROCK_CUBE) {
                    if (grid[currentY][x] == EMPTY) {
                        currentCounter++
                    }
                    currentY--
                }
                counter += grid.size - y + currentCounter
            }
        }
    }

    return counter
}

private fun solveDay13b(input: String): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day14_example.txt")
    val inputTask = readFile("day14.txt")

    println("Solution for task 1 example: ${solveDay13a(inputExample)}") // 136
    println("Solution for task 1 task:    ${solveDay13a(inputTask)}") // 109424
    println("Solution for task 2 example: ${solveDay13b(inputExample)}") // 64
    println("Solution for task 2 task:    ${solveDay13b(inputTask)}") // ???
}

