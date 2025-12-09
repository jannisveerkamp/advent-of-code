import common.PointL
import common.zipWithItselfAllUnique
import kotlin.math.abs

private fun parseDay09(input: String): List<PointL> = input.split("\n").map {
    val (start, end) = it.split(",").map { it.toLong() }
    PointL(start, end)
}

private fun rectangleArea(first: PointL, second: PointL): Long {
    return (abs(first.x - second.x) + 1) * (abs(first.y - second.y) + 1)
}

private fun rectangleArea(points: List<Pair<PointL, PointL>>): Long {
    return points.maxOf { pointPair -> rectangleArea(pointPair.first, pointPair.second) }
}

private fun solveDay09a(input: String): Long = rectangleArea(parseDay09(input).zipWithItselfAllUnique())

private fun solveDay09b(input: String): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day09_example.txt")
    val inputTask = readFile("day09.txt")

    println("Solution for task 1 example: ${solveDay09a(inputExample)}") // 50
    println("Solution for task 1 task:    ${solveDay09a(inputTask)}") // 4744899849
    println("Solution for task 1 example: ${solveDay09b(inputExample)}") // ???
    println("Solution for task 1 task:    ${solveDay09b(inputTask)}") // ???
}

