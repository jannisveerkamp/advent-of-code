package common

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
    fun manhattan(other: Point): Int = abs(other.x - x) + abs(other.y - y)
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)
}

data class PointL(val x: Long, val y: Long) {
    fun manhattan(other: PointL): Long = abs(other.x - x) + abs(other.y - y)
}

data class Point3(val x: Int, val y: Int, val z: Int) {
    fun manhattan(other: Point3): Int = abs(other.x - x) + abs(other.y - y) + abs(other.z - z)
}