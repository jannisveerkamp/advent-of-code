import common.Dijkstra
import common.DijkstraNode

private enum class Maze(val char: Char) {

    NS('|'),
    WE('-'),
    NE('L'),
    NW('J'),
    SW('7'),
    SE('F'),
    G('.'),
    START('S');

    companion object {

        fun fromChar(input: Char): Maze = values().first { it.char == input }

        fun fromString(input: String): Array<Array<Maze>> = input.split("\n").map { line ->
            line.toCharArray().map { fromChar(it) }.toTypedArray()
        }.toTypedArray()
    }
}

private fun solveDay10(input: String): Int {
    val maze = Maze.fromString(input)
    return 0
}

fun main() {
    val inputExample = readFile("day10_example.txt")
    val inputExample2 = readFile("day10_example_2.txt")
    val inputTask = readFile("day10.txt")

    println("Solution for task 1 example: ${solveDay10(inputExample)}") // 4
    println("Solution for task 1 example: ${solveDay10(inputExample2)}") // 8
    println("Solution for task 1 task:    ${solveDay10(inputTask)}") // ???
    println("Solution for task 2 example: ${solveDay10(inputExample)}") // ???
    println("Solution for task 2 task:    ${solveDay10(inputTask)}") // ???
}

