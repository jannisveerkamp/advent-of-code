import common.Dijkstra
import common.DijkstraNode
import common.Direction
import common.Point

private class Block(
    val point: Point,
    val direction: Direction,
    val line: Int,
    val nodes: List<List<Int>>
) : DijkstraNode<Block> {

    override fun neighbors(): Map<Block, Int> = buildMap {
        val straight = point + direction.toPoint()
        if (line < 3 && straight.x in nodes.first().indices && straight.y in nodes.indices) {
            put(Block(straight, direction, line + 1, nodes), nodes[straight.y][straight.x])
        }
        val left = point + direction.turnLeft().toPoint()
        if (left.x in nodes.first().indices && left.y in nodes.indices) {
            put(Block(left, direction.turnLeft(), 1, nodes), nodes[left.y][left.x])
        }
        val right = point + direction.turnRight().toPoint()
        if (right.x in nodes.first().indices && right.y in nodes.indices) {
            put(Block(right, direction.turnRight(), 1, nodes), nodes[right.y][right.x])
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Block

        if (point != other.point) return false
        if (direction != other.direction) return false
        return line == other.line
    }

    override fun hashCode(): Int {
        var result = point.hashCode()
        result = 31 * result + direction.hashCode()
        result = 31 * result + line
        return result
    }
}

private class UltraBlock(
    val point: Point,
    val direction: Direction,
    val line: Int,
    val nodes: List<List<Int>>
) : DijkstraNode<UltraBlock> {

    override fun neighbors(): Map<UltraBlock, Int> = buildMap {
        val straight = point + direction.toPoint()
        if (line < 10 && straight.x in nodes.first().indices && straight.y in nodes.indices) {
            put(UltraBlock(straight, direction, if (line == 0) 2 else line + 1, nodes), nodes[straight.y][straight.x])
        }
        val left = point + direction.turnLeft().toPoint()
        if ((line >= 4 || line == 0) && left.x in nodes.first().indices && left.y in nodes.indices) {
            put(UltraBlock(left, direction.turnLeft(), 1, nodes), nodes[left.y][left.x])
        }
        val right = point + direction.turnRight().toPoint()
        if ((line >= 4 || line == 0) && right.x in nodes.first().indices && right.y in nodes.indices) {
            put(UltraBlock(right, direction.turnRight(), 1, nodes), nodes[right.y][right.x])
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UltraBlock

        if (point != other.point) return false
        if (direction != other.direction) return false
        return line == other.line
    }

    override fun hashCode(): Int {
        var result = point.hashCode()
        result = 31 * result + direction.hashCode()
        result = 31 * result + line
        return result
    }
}

private fun solveDay17a(input: String): Int {
    val nodes = input.split("\n").map { line -> line.toCharArray().map { char -> char.digitToInt() } }
    val start = Block(Point(0, 0), Direction.E, 0, nodes)
    val end = Block(Point(nodes.first().size - 1, nodes.size - 1), Direction.E, 1, nodes)

    val dijkstra = object : Dijkstra<Block> {}
    val result = dijkstra.solve(start, end, null)
    return result[end]!!
}

private fun solveDay17b(input: String): Int {
    val nodes = input.split("\n").map { line -> line.toCharArray().map { char -> char.digitToInt() } }
    val start = UltraBlock(Point(0, 0), Direction.E, 0, nodes)
    val end = UltraBlock(Point(nodes.first().size - 1, nodes.size - 1), Direction.E, 1, nodes)

    val dijkstra = object : Dijkstra<UltraBlock> {}
    val result = dijkstra.solve(start, end) { o1, o2 ->
        if (o1.point == o2.point && o1.line >= 4) 0 else 1
    }
    return result[result.keys.first { it.point == end.point }]!!
}

fun main() {
    val inputExample = readFile("day17_example.txt")
    val inputExample2 = readFile("day17_example_2.txt")
    val inputTask = readFile("day17.txt")

    println("Solution for task 1 example: ${solveDay17a(inputExample)}") // 102
    println("Solution for task 1 task:    ${solveDay17a(inputTask)}") // 686
    println("Solution for task 2 example: ${solveDay17b(inputExample)}") // 94
    println("Solution for task 2 example: ${solveDay17b(inputExample2)}") // 71 algorithm doesn't work somehow :(
    println("Solution for task 2 task:    ${solveDay17b(inputTask)}") // 801
}

