import common.Direction

private const val ROCK_CUBE = '#'
private const val ROCK_ROUND = 'O'
private const val EMPTY = '.'

private fun solveDay13a(input: String): Int {
    val grid = input.split("\n").map { line -> line.toCharArray() }

    return moveUp(grid).withIndex().sumOf { (i, row) ->
        row.count { it == ROCK_ROUND } * (grid.size - i)
    }
}

private fun Direction.next(): Direction = when (this) {
    Direction.N -> Direction.W
    Direction.S -> Direction.E
    Direction.W -> Direction.S
    Direction.E -> Direction.N
}

private fun moveUp(input: List<CharArray>): List<CharArray> {
    val new = Array(input.size) { CharArray(input.first().size) { '.' } }
    for (j in 0 until input.first().size) {
        var index = 0
        for (i in input.indices) {
            if (input[i][j] == ROCK_ROUND) {
                new[index++][j] = ROCK_ROUND
            } else if (input[i][j] == ROCK_CUBE) {
                new[i][j] = ROCK_CUBE
                index = i + 1
            }
        }
    }
    return new.toList()
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

