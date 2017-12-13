package net.grandcentrix.advent

internal fun severity(input: Map<Int, Int>): Int {
    var cursorPosition = 0
    var positions = input.mapValues { 0 }
    var directions = input.mapValues { 1 }
    var severity = 0

    repeat(input.keys.last() + 1) {
        if (positions[cursorPosition] == 0) {
            severity += cursorPosition * input[cursorPosition]!!
        }
        positions = moveScanners(positions, directions)
        directions = adjustDirections(positions, directions, input)
        cursorPosition++
    }

    return severity
}

private fun moveScanners(positions: Map<Int, Int>, directions: Map<Int, Int>): Map<Int, Int> {
    return positions.mapValues { entry ->
        entry.value + directions[entry.key]!!
    }
}

private fun adjustDirections(positions: Map<Int, Int>, directions: Map<Int, Int>, input: Map<Int, Int>): Map<Int, Int> {
    return directions.mapValues { entry ->
        when {
            positions[entry.key] == 0 -> 1
            positions[entry.key] == input[entry.key]!! - 1 -> -1
            else -> entry.value
        }
    }
}

internal fun minimumDelayWithoutGettingCaught(input: Map<Int, Int>): Int {
    for (currentDelay in 0..1000000000) {
        val caught = input.any {
            (currentDelay + it.key) % (it.value * 2 - 2) == 0
        }
        if (!caught) {
            return currentDelay
        }
    }
    return -1
}
