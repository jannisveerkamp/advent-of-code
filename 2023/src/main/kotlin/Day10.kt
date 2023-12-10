import common.Point

private enum class Direction {
    N,
    S,
    W,
    E;

    fun toPoint(): Point = when (this) {
        N -> Point(0, -1)
        S -> Point(0, 1)
        W -> Point(-1, 0)
        E -> Point(1, 0)
    }
}

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

private fun solveDay10(input: String, startCell: MCell, startDirection: Direction): Int {
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
    var currentPath: List<MCell> = emptyList()
    var currentCell: MCell? = startCell
    var currentDirection: Direction? = startDirection
    while (currentDirection != null && currentCell != null) {
        val next = nextMazeCell(currentDirection, currentCell, xStart, yStart, maze)
        currentCell = next?.first
        currentDirection = next?.second
        xStart += currentDirection?.toPoint()?.x ?: 0
        yStart += currentDirection?.toPoint()?.y ?: 0
        currentCell?.let { currentPath = currentPath + it }
    }

    return (currentPath.size + 1) / 2
}

fun main() {
    val inputExample = readFile("day10_example.txt")
    val inputExample2 = readFile("day10_example_2.txt")
    val inputTask = readFile("day10.txt")

    println("Solution for task 1 example: ${solveDay10(inputExample, MCell.SE, Direction.E)}") // 4
    println("Solution for task 1 example: ${solveDay10(inputExample2, MCell.SE, Direction.E)}") // 8
    println("Solution for task 1 task:    ${solveDay10(inputTask, MCell.NE, Direction.E)}") // 6947
//    println("Solution for task 2 example: ${solveDay10(inputExample)}") // ???
//    println("Solution for task 2 task:    ${solveDay10(inputTask)}") // ???
}

