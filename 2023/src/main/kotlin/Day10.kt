import common.Direction
import common.Point



private enum class MCell(val char: Char) {
    NS('|'),
    WE('-'),
    NE('L'),
    NW('J'),
    SW('7'),
    SE('F'),
    G('.'),
    START('S');

    fun next(oldDirection: Direction): Direction? = when (this) {
        NS -> if (oldDirection == Direction.N) Direction.N else Direction.S
        WE -> if (oldDirection == Direction.W) Direction.W else Direction.E
        NE -> if (oldDirection == Direction.S) Direction.E else Direction.N
        NW -> if (oldDirection == Direction.S) Direction.W else Direction.N
        SW -> if (oldDirection == Direction.N) Direction.W else Direction.S
        SE -> if (oldDirection == Direction.N) Direction.E else Direction.S
        G -> null
        START -> null
    }

    companion object {

        fun fromChar(input: Char): MCell = values().first { it.char == input }

        fun fromString(input: String): Array<Array<MCell>> = input.split("\n").map { line ->
            line.toCharArray().map { fromChar(it) }.toTypedArray()
        }.toTypedArray()
    }
}

private data class MCellWithCoordinates(val cell: MCell, val x: Int, val y: Int)

private fun nextMazeCell(
    oldDirection: Direction, currentCell: MCell, x: Int, y: Int, maze: Array<Array<MCell>>
): Pair<MCell, Direction>? {
    val nextDirection = currentCell.next(oldDirection)
    return if (nextDirection == null) {
        null
    } else {
        val delta = nextDirection.toPoint()
        val newX = x + delta.x
        val newY = y + delta.y
        if (newX < 0 || newY < 0 || newX > maze.first().size - 1 || newY > maze.size - 1) {
            null
        } else {
            val nextCell = maze[newY][newX]
            nextCell to nextDirection
        }
    }
}

private fun buildPath(input: String, startCell: MCell, startDirection: Direction): List<MCellWithCoordinates> {
    val maze = MCell.fromString(input)
    var xStart = 0
    var yStart = 0
    maze.forEachIndexed { y, line ->
        line.forEachIndexed { x, cell ->
            if (cell == MCell.START) {
                xStart = x
                yStart = y
            }
        }
    }
    var currentPath: List<MCellWithCoordinates> = emptyList()
    var currentCell: MCell? = startCell
    var currentDirection: Direction? = startDirection
    while (currentDirection != null && currentCell != null) {
        val next = nextMazeCell(currentDirection, currentCell, xStart, yStart, maze)
        currentCell = next?.first
        currentDirection = next?.second
        val x = xStart
        val y = yStart
        xStart += currentDirection?.toPoint()?.x ?: 0
        yStart += currentDirection?.toPoint()?.y ?: 0
        currentCell?.let { currentPath = currentPath + MCellWithCoordinates(it, x, y) }
    }
    return currentPath
}

private fun solveDay10a(input: String, startCell: MCell, startDirection: Direction): Int {
    val path = buildPath(input, startCell, startDirection)
    return (path.size + 1) / 2
}

private fun solveDay10b(input: String, startCell: MCell, startDirection: Direction): Int {
    val path = buildPath(input, startCell, startDirection)
    val pipes = if (startCell in listOf(MCell.NS, MCell.NE, MCell.NW)) "|LJS" else "|LJ"
    val maze = MCell.fromString(input).mapIndexed { y, line ->
        line.mapIndexed { x, cell -> MCellWithCoordinates(cell, x, y) }
    }

    var count = 0
    for (y in maze.indices) {
        var inside = false
        for (x in maze.first().indices) {
            if (path.any { it.x == x && it.y == y } && maze[y][x].cell.char in pipes) {
                inside = !inside
            }
            if (path.none { it.x == x && it.y == y } && inside) {
                count++
            }
        }
    }
    return count
}

fun main() {
    val inputExample = readFile("day10_example.txt")
    val inputExample2 = readFile("day10_example_2.txt")
    val inputExample3 = readFile("day10_example_3.txt")
    val inputExample4 = readFile("day10_example_4.txt")
    val inputExample5 = readFile("day10_example_5.txt")
    val inputTask = readFile("day10.txt")

    println("Solution for task 1 example: ${solveDay10a(inputExample, MCell.SE, Direction.E)}") // 4
    println("Solution for task 1 example: ${solveDay10a(inputExample2, MCell.SE, Direction.E)}") // 8
    println("Solution for task 1 task:    ${solveDay10a(inputTask, MCell.NE, Direction.E)}") // 6947
    println("Solution for task 2 example: ${solveDay10b(inputExample3, MCell.SE, Direction.E)}") // 4
    println("Solution for task 2 example: ${solveDay10b(inputExample4, MCell.SE, Direction.E)}") // 8
    println("Solution for task 2 example: ${solveDay10b(inputExample5, MCell.SW, Direction.S)}") // 10
    println("Solution for task 2 task:    ${solveDay10b(inputTask, MCell.NE, Direction.E)}") // 273
}

