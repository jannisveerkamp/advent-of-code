package common

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
    fun manhattan(other: Point): Int = abs(other.x - x) + abs(other.y - y)
}

data class Point3(val x: Int, val y: Int, val z: Int) {
    fun manhattan(other: Point3): Int = abs(other.x - x) + abs(other.y - y) + abs(other.z - z)
}