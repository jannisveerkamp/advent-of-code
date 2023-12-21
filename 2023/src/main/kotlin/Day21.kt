import common.Direction
import common.Point

private data class Garden(
    val x: Int,
    val y: Int,
    val tile: Char
)

private fun parseGarden(input: String): Pair<List<List<Garden>>, Garden> {
    lateinit var start: Garden
    val garden = input.split("\n").mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            val block = Garden(x, y, char)
            if (block.tile == 'S') start = block
            block
        }
    }
    return garden to start
}

private fun solveDay21(input: String, steps: Int): Long {
    val (garden, start) = parseGarden(input)

    val yMax = garden.size
    val xMax = garden.first().size
    val visited = mutableSetOf<Point>()
    var currentPoints = mutableSetOf(Point(start.x, start.y))
    val counts = LongArray(2)
    val borders = IntArray(xMax)
    val d1 = IntArray(xMax)
    val d2 = IntArray(xMax)
    var step = 0

    while (step < steps) {
        val newPoints = mutableSetOf<Point>()
        currentPoints.forEach { currentPoint ->
            Direction.values().map { it.toPoint() }.forEach { direction ->
                val x = currentPoint.x + direction.x
                val y = currentPoint.y + direction.y
                if (garden[y.mod(yMax)][x.mod(xMax)].tile != '#') {
                    val next = Point(x, y)
                    if (visited.add(next)) {
                        newPoints.add(next)
                    }
                }
            }
        }
        val borderSize = newPoints.size
        val old = counts[0]
        counts[0] = counts[1]
        counts[1] = borderSize + old
        val ix = step % xMax
        if (step >= xMax) {
            val dx = borderSize - borders[ix]
            d2[ix] = dx - d1[ix]
            d1[ix] = dx
        }
        borders[ix] = borderSize
        currentPoints = newPoints
        step++
        if (step >= 2 * xMax) {
            if (d2.all { it == 0 }) {
                break
            }
        }
    }

    // Extrapolate
    for (i in step until steps) {
        val ix = i % xMax
        d1[ix] += d2[ix]
        borders[ix] += d1[ix]
        val old = counts[0]
        counts[0] = counts[1]
        counts[1] = borders[ix] + old
    }
    return counts[1]
}

fun main() {
    val inputExample = readFile("day21_example.txt")
    val inputTask = readFile("day21.txt")

    println("Solution for task 1 example: ${solveDay21(inputExample, 6)}") // 16
    println("Solution for task 1 task:    ${solveDay21(inputTask, 64)}") // 3830

    println("Solution for task 1 example: ${solveDay21(inputExample, 10)}") // 50
    println("Solution for task 1 example: ${solveDay21(inputExample, 50)}") // 1594
    println("Solution for task 1 example: ${solveDay21(inputExample, 100)}") // 6536
    println("Solution for task 1 example: ${solveDay21(inputExample, 500)}") // 167004
    println("Solution for task 1 example: ${solveDay21(inputExample, 1000)}") // 668697
    println("Solution for task 1 example: ${solveDay21(inputExample, 5000)}") // 16733044
    println("Solution for task 1 task:    ${solveDay21(inputTask, 26501365)}") // 637087163925555
}

