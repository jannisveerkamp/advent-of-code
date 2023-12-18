package common

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
    fun manhattan(other: Point): Int = abs(other.x - x) + abs(other.y - y)

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)
    operator fun times(other: Int): Point = Point(x * other, y * other)

    val down: Point get() = Point(x, y + 1)
    val up: Point get() = Point(x, y - 1)
    val left: Point get() = Point(x - 1, y)
    val right: Point get() = Point(x + 1, y)
}

data class PointL(val x: Long, val y: Long) {
    fun manhattan(other: PointL): Long = abs(other.x - x) + abs(other.y - y)
}

data class Point3(val x: Int, val y: Int, val z: Int) {
    fun manhattan(other: Point3): Int = abs(other.x - x) + abs(other.y - y) + abs(other.z - z)
}

fun shoelaceArea(vertices: List<Point>): Long = vertices.indices.sumOf { i ->
    val (x1, y1) = vertices[i]
    val (x2, y2) = vertices[(i + 1) % vertices.size]
    x1.toLong() * y2 - y1.toLong() * x2
} / 2
