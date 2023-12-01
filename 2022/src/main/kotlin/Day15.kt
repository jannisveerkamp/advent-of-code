import common.Point
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private fun parseNumbers(input: String) = input.replace("[^-?0-9]+".toRegex(), " ").trim().split(" ").map { it.toInt() }

fun solveDay15a(input: String, rowNumber: Int): Int {
    val sensors = mutableMapOf<Point, Point>()
    val row = Array(rowNumber * 4) { false }
    input.split("\n").map { line ->
        val (xS, yS, xB, yB) = parseNumbers(line)
        sensors[Point(xS + rowNumber, yS)] = Point(xB + rowNumber, yB)
    }

    sensors.forEach { sensor ->
        row.forEachIndexed { index, _ ->
            val distance = sensor.key.manhattan(Point(index, rowNumber))
            val closestBeaconDistance = sensor.key.manhattan(sensor.value)
            if (distance <= closestBeaconDistance) {
                row[index] = true
            }
        }

    }

    return row.count { it } - 1
}

private fun rangeWithNoBeacon(sensors: Map<Point, Point>, y: Int): List<Point> {
    return sensors.mapNotNull { sensor ->
        val distance = sensor.key.manhattan(sensor.value)
        val xRest: Int = distance - abs(sensor.key.y - y)
        if (xRest >= 0) {
            Point(sensor.key.x - xRest, sensor.key.x + xRest)
        } else {
            null
        }
    }
}

fun mergeRanges(unsortedRanges: List<Point>): List<Point> {
    val ranges = unsortedRanges.sortedBy { it.x }
    val newRanges = mutableListOf(ranges[0])
    for ((low, high) in ranges.drop(1)) {
        val (prevLow, prevHigh) = newRanges.last()
        newRanges += if (low <= prevHigh + 1) {
            newRanges.removeLast()
            Point(min(low, prevLow), max(high, prevHigh))
        } else {
            Point(low, high)
        }
    }
    return newRanges
}

fun solveDay15b(input: String, max: Int): Long {
    val sensors = mutableMapOf<Point, Point>()

    input.split("\n").map { line ->
        val (xS, yS, xB, yB) = parseNumbers(line)
        sensors[Point(xS, yS)] = Point(xB, yB)
    }

    (max downTo 0).forEach { rowIndex ->
        val ranges = rangeWithNoBeacon(sensors, rowIndex)
        val rangesMerged = mergeRanges(ranges)
        if (rangesMerged.size > 1) {
            val x = (0 until max).first { x ->
                rangesMerged.firstOrNull { (low, high) -> x in low..high } == null
            }
            return x * 4_000_000L + rowIndex
        }
    }

    return 0
}

fun main() {
    val inputExample = readFile("day15_example.txt")
    val inputTask = readFile("day15.txt")

    println("Solution for task 1 example: ${solveDay15a(inputExample, 10)}") // 26
    println("Solution for task 1 task:    ${solveDay15a(inputTask, 2_000_000)}") // 5508234
    println("Solution for task 2 example: ${solveDay15b(inputExample, 20)}") // 56000011
    println("Solution for task 2 task:    ${solveDay15b(inputTask, 4_000_000)}") // 10457634860779
}

