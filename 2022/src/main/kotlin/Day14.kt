import kotlin.math.max
import kotlin.math.min
import common.Point

private fun debug(array: Array<Array<Char>>, minX: Int, maxX: Int, minY: Int, maxY: Int) {
    val slicedArray = array.sliceArray(IntRange(minY - 1, maxY + 3)).map {
        it.sliceArray(IntRange(minX - 1, maxX + 1))
    }
    println(slicedArray.joinToString("\n") { it.joinToString("") })
}

private const val AIR = '.'
private const val ROCK = '#'
private const val SAND = 'o'

private fun Point.moveDown(): Point = Point(x, y + 1)
private fun Point.moveDownLeft(): Point = Point(x - 1, y + 1)
private fun Point.moveDownRight(): Point = Point(x + 1, y + 1)

private data class Grid(val grid: Array<Array<Char>>, var minX: Int, var maxX: Int, var minY: Int, var maxY: Int)

private fun generateGrid(input: String): Grid {
    val grid: Array<Array<Char>> = Array(200) { Array(1000) { AIR } }
    var minX = Int.MAX_VALUE
    var maxX = 0
    var minY = Int.MAX_VALUE
    var maxY = 0

    input.split("\n").forEach { line ->
        val rockEnds = line.split(" -> ").map { coordinate ->
            val (x, y) = coordinate.split(",").map { it.toInt() }
            minX = min(minX, x)
            maxX = max(maxX, x)
            minY = min(minY, y)
            maxY = max(maxY, y)
            Point(x, y)
        }
        rockEnds.zipWithNext().forEach { (first, second) ->
            when {
                first.x < second.x -> (first.x..second.x).forEach { grid[first.y][it] = ROCK }
                first.x > second.x -> (second.x..first.x).forEach { grid[first.y][it] = ROCK }
                first.y < second.y -> (first.y..second.y).forEach { grid[it][first.x] = ROCK }
                first.y > second.y -> (second.y..first.y).forEach { grid[it][first.x] = ROCK }
                else -> error("Unknown: $first, $second")
            }
        }
    }
    return Grid(grid, minX, maxX, minY, maxY)
}

private fun fillGrid(maxY: Int, grid: Array<Array<Char>>) {
    var sandCoordinate = Point(500, 0)
    while (true) {
        when {
            sandCoordinate.y > maxY -> break
            grid[sandCoordinate.y + 1][sandCoordinate.x] == AIR -> sandCoordinate = sandCoordinate.moveDown()
            grid[sandCoordinate.y + 1][sandCoordinate.x - 1] == AIR -> sandCoordinate = sandCoordinate.moveDownLeft()
            grid[sandCoordinate.y + 1][sandCoordinate.x + 1] == AIR -> sandCoordinate = sandCoordinate.moveDownRight()
            else -> {
                grid[sandCoordinate.y][sandCoordinate.x] = SAND
                if (sandCoordinate == Point(500, 0)) {
                    break
                }
                sandCoordinate = Point(500, 0)
            }
        }
    }
}

fun solveDay14a(input: String): Int {
    val (grid, minX, maxX, minY, maxY) = generateGrid(input)
    fillGrid(maxY, grid)
    return grid.sumOf { row -> row.count { it == SAND } }
}

fun solveDay14b(input: String): Int {
    val (grid, minX, maxX, minY, maxY) = generateGrid(input)
    (0..999).forEach {
        grid[maxY + 2][it] = ROCK
    }
    fillGrid(maxY + 2, grid)
    return grid.sumOf { row -> row.count { it == SAND } }
}

fun main() {
    val inputExample = readFile("day14_example.txt")
    val inputTask = readFile("day14.txt")

    println("Solution for task 1 example: ${solveDay14a(inputExample)}") // 24
    println("Solution for task 1 task:    ${solveDay14a(inputTask)}") // 719
    println("Solution for task 2 example: ${solveDay14b(inputExample)}") // 93
    println("Solution for task 2 task:    ${solveDay14b(inputTask)}") // 23390
}

