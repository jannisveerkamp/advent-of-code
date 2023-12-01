import common.Point3
import kotlin.collections.ArrayDeque

private fun Point3.neighbors(): Set<Point3> = setOf(
    Point3(x + 1, y, z),
    Point3(x - 1, y, z),
    Point3(x, y + 1, z),
    Point3(x, y - 1, z),
    Point3(x, y, z + 1),
    Point3(x, y, z - 1)
)

fun solveDay18a(input: String): Int {
    val cubes = input.split("\n").map { cube ->
        val (x, y, z) = cube.split(",").map { it.toInt() }
        Point3(x, y, z)
    }

    val remainingCubes = cubes.toMutableList()
    var visibleSides = cubes.size * 6
    cubes.forEach { cube ->
        remainingCubes.remove(cube)
        remainingCubes.forEach { otherCube ->
            if (cube.manhattan(otherCube) == 1) {
                visibleSides -= 2
            }
        }
    }

    return visibleSides
}

fun solveDay18b(input: String): Int {
    val cubes = input.split("\n").map { cube ->
        val (x, y, z) = cube.split(",").map { it.toInt() }
        Point3(x, y, z)
    }.toSet()
    val minX = cubes.minOf { it.x }
    val maxX = cubes.maxOf { it.x }
    val minY = cubes.minOf { it.y }
    val maxY = cubes.maxOf { it.y }
    val minZ = cubes.minOf { it.z }
    val maxZ = cubes.maxOf { it.z }

    fun Point3.reachedExterior() = x < minX || y < minY || z < minZ || x > maxX || y > maxY || z > maxZ

    fun canReachExterior(src: Point3): Boolean {
        val queue = ArrayDeque(setOf(src))
        val visited = mutableSetOf(src)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (cubes.contains(current)) {
                continue
            }

            if (current.reachedExterior()) {
                return true
            }

            for (neighbor in current.neighbors()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor)
                    queue.addFirst(neighbor)
                }
            }
        }
        return false
    }
    return cubes.sumOf { cube -> cube.neighbors().count { point -> canReachExterior(point) } }

}

fun main() {
    val inputExample = readFile("day18_example.txt")
    val inputTask = readFile("day18.txt")

    println("Solution for task 1 example: ${solveDay18a(inputExample)}") // 64
    println("Solution for task 1 task:    ${solveDay18a(inputTask)}") // 3396
    println("Solution for task 2 example: ${solveDay18b(inputExample)}") // 58
    println("Solution for task 2 task:    ${solveDay18b(inputTask)}") // 2044
}

