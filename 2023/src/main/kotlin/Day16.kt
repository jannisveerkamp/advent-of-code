import common.Direction
import common.Point

private fun outSideGrid(grid: Array<CharArray>, point: Point): Boolean {
    return point.x < 0 || point.x > grid.first().size - 1 || point.y < 0 || point.y > grid.size - 1
}

private fun next(grid: Array<CharArray>, current: Point, direction: Direction): Set<Pair<Point, Direction>> {
    val next = current + direction.toPoint()
    if (outSideGrid(grid, next)) return emptySet()

    val nextPoints: Set<Pair<Point, Direction>> = when (grid[next.y][next.x]) {
        '.' -> setOf(next to direction)
        '|' -> when (direction) {
            Direction.N, Direction.S -> setOf(next to direction)
            Direction.W, Direction.E -> setOf(next to Direction.N, next to Direction.S)
        }

        '-' -> when (direction) {
            Direction.N, Direction.S -> setOf(next to Direction.W, next to Direction.E)
            Direction.W, Direction.E -> setOf(next to direction)
        }

        '\\' -> when (direction) {
            Direction.N -> setOf(next to Direction.W)
            Direction.S -> setOf(next to Direction.E)
            Direction.W -> setOf(next to Direction.N)
            Direction.E -> setOf(next to Direction.S)
        }

        '/' -> when (direction) {
            Direction.N -> setOf(next to Direction.E)
            Direction.S -> setOf(next to Direction.W)
            Direction.W -> setOf(next to Direction.S)
            Direction.E -> setOf(next to Direction.N)
        }

        else -> error("Unknown tile: $next")
    }
    return nextPoints
}

private fun findEnergy(
    grid: Array<CharArray>,
    startBeam: Pair<Point, Direction>,
): Set<Pair<Point, Direction>> {
    val energizedBeams = mutableSetOf<Pair<Point, Direction>>()
    val currentBeams = mutableSetOf(startBeam)
    while (currentBeams.isNotEmpty()) {
        val currentBeam = currentBeams.first().also { currentBeams.remove(it) }
        val nextBeams = next(grid, currentBeam.first, currentBeam.second)
        nextBeams.forEach { next ->
            if (!energizedBeams.contains(next) && !outSideGrid(grid, next.first)) {
                currentBeams.add(next)
                energizedBeams.add(next)
            }
        }
    }
    return energizedBeams
}

private fun solveDay16a(input: String): Int {
    val grid = input.split("\n").map { it.toCharArray() }.toTypedArray()
    val energizedBeams = findEnergy(grid, Point(-1, 0) to Direction.E)
    return energizedBeams.map { it.first }.toSet().size
}

private fun solveDay16b(input: String): Int {
    val grid = input.split("\n").map { it.toCharArray() }.toTypedArray()

    val fromWest = (grid.indices).map { y -> Point(-1, y) to Direction.E }
    val fromEast = (grid.indices).map { y -> Point(grid.first().size, y) to Direction.W }
    val fromNorth = (grid.first().indices).map { x -> Point(x, -1) to Direction.S }
    val fromSouth = (grid.first().indices).map { x -> Point(x, grid.size) to Direction.N }

    return (fromWest + fromEast + fromNorth + fromSouth).maxOf { start ->
        val energizedBeams = findEnergy(grid, start)
        energizedBeams.map { it.first }.toSet().size
    }
}

fun main() {
    val inputExample = readFile("day16_example.txt")
    val inputTask = readFile("day16.txt")

    println("Solution for task 1 example: ${solveDay16a(inputExample)}") // 46
    println("Solution for task 1 task:    ${solveDay16a(inputTask)}") // 7477
    println("Solution for task 2 example: ${solveDay16b(inputExample)}") // 51
    println("Solution for task 2 task:    ${solveDay16b(inputTask)}") // 7853
}

