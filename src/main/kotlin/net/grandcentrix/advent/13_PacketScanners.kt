package net.grandcentrix.advent

internal fun severity(input: Map<Int, Int>): Int {
    var cursorPosition = 0
    var positions = input.mapValues { 0 }
    var directions = input.mapValues { 1 }
    var hits = 0

    repeat(input.keys.last() + 1) {
        if (positions[cursorPosition] == 0) {
            hits += cursorPosition * input[cursorPosition]!!
        }
        positions = moveScanners(positions, directions)
        directions = adjustDirections(positions, directions, input)
        cursorPosition++
    }

    return hits
}

private fun moveScanners(positions: Map<Int, Int>, directions: Map<Int, Int>): Map<Int, Int> {
    return positions.mapValues { entry ->
        entry.value + directions[entry.key]!!
    }
}

fun adjustDirections(positions: Map<Int, Int>, directions: Map<Int, Int>, input: Map<Int, Int>): Map<Int, Int> {
    return directions.mapValues { entry ->
        when {
            positions[entry.key] == 0 -> 1
            positions[entry.key] == input[entry.key]!! - 1 -> -1
            else -> entry.value
        }
    }
}
