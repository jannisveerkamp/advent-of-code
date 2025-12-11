import common.Point
import common.PointL
import common.zipWithItselfAllUnique
import java.awt.Polygon
import java.awt.geom.Rectangle2D
import kotlin.math.abs
import kotlin.math.absoluteValue

private fun parseDay09(input: String): List<Point> = input.lines()
    .map { it.split(",").map(String::toInt).let { (x, y) -> Point(x, y) } }

private fun rectangleArea(first: Point, second: Point): Long {
    return (abs(first.x - second.x) + 1).toLong() * (abs(first.y - second.y) + 1)
}

private fun rectangleArea(pointPairs: List<Pair<Point, Point>>): Long {
    return pointPairs.maxOf { pointPair -> rectangleArea(pointPair.first, pointPair.second) }
}

private fun solveDay09a(input: String): Long = rectangleArea(parseDay09(input).zipWithItselfAllUnique())

fun Rectangle2D.size() = ((width + 1) * (height + 1)).toLong()

private fun solveDay09b(input: String): Long {
    val coordinates = parseDay09(input)
    val polygon: Polygon = coordinates.fold(Polygon()) { acc, (x, y) -> acc.apply { addPoint(x, y) } }
    return coordinates.flatMapIndexed { row, point1 ->
        coordinates.drop(row + 1).map { point2 ->
            Rectangle2D.Double(
                minOf(point1.x, point2.x).toDouble(),
                minOf(point1.y, point2.y).toDouble(),
                (point2.x - point1.x).absoluteValue.toDouble(),
                (point2.y - point1.y).absoluteValue.toDouble()
            )
        }
    }.filter(polygon::contains).maxOf(Rectangle2D::size)
}

fun main() {
    val inputExample = readFile("day09_example.txt")
    val inputTask = readFile("day09.txt")

    println("Solution for task 1 example: ${solveDay09a(inputExample)}") // 50
    println("Solution for task 1 task:    ${solveDay09a(inputTask)}") // 4744899849
    println("Solution for task 2 example: ${solveDay09b(inputExample)}") // 24
    println("Solution for task 2 task:    ${solveDay09b(inputTask)}") // 1540192500
}

