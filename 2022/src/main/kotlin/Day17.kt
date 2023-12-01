import common.Point

private sealed interface TetrisCommand {
    object Left : TetrisCommand
    object Right : TetrisCommand
}

private val tile1 = setOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0))
private val tile2 = setOf(Point(1, 0), Point(0, 1), Point(1, 1), Point(2, 1), Point(1, 2))
private val tile3 = setOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1), Point(2, 2))
private val tile4 = setOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3))
private val tile5 = setOf(Point(0, 0), Point(1, 0), Point(0, 1), Point(1, 1))
private val tiles = listOf(tile1, tile2, tile3, tile4, tile5)
private const val WIDTH = 7

private fun Point.moveRight() = Point(x + 1, y)
private fun Point.moveLeft() = Point(x - 1, y)
private fun Point.moveDown() = Point(x, y - 1)
private operator fun Point.plus(current: Point): Point = Point(x + current.x, y + current.y)

private fun canGo(tile: Set<Point>, current: Point, offset: Point, grid: Array<Array<Boolean>>): Boolean {
    return tile.none { field ->
        val spot = field + offset + current
        spot.x !in 0 until WIDTH || spot.y !in grid.indices || grid[spot.y][spot.x]
    }
}


fun solveDay17a(input: String, iterations: Int): Int {
    val commands = input.map { if (it == '<') TetrisCommand.Left else TetrisCommand.Right }
    val grid = Array(7000) { Array(7) { false } }
    var highestPoint = -1
    var currentCommandPosition = -1

    repeat(iterations) { index ->
        val tile = tiles[index % tiles.size]
        var position = Point(2, highestPoint + 4)

        while (true) {
            currentCommandPosition = (currentCommandPosition + 1) % commands.size
            val command = commands[currentCommandPosition]
            // 1. Move
            position = when (command) {
                TetrisCommand.Left -> if (canGo(tile, position, Point(-1, 0), grid)) position.moveLeft() else position
                TetrisCommand.Right -> if (canGo(tile, position, Point(1, 0), grid)) position.moveRight() else position
            }

            // 2. Fall
            if (canGo(tile, position, Point(0, -1), grid)) {
                position = position.moveDown()
            } else {
                break
            }
        }

        tile.forEach { grid[it.y + position.y][it.x + position.x] = true }
        highestPoint = grid.indexOfLast { it.contains(true) }
    }

    return highestPoint + 1
}

fun main() {
    val inputExample = readFile("day17_example.txt")
    val inputTask = readFile("day17.txt")

    println("Solution for task 1 example: ${solveDay17a(inputExample, 2022)}") // 3068
    println("Solution for task 1 task:    ${solveDay17a(inputTask, 2022)}") // 3147
//    println("Solution for task 2 example: ${solveDay17a(inputExample)}") // 1514285714288
//    println("Solution for task 2 task:    ${solveDay17a(inputTask)}") // ???
}

