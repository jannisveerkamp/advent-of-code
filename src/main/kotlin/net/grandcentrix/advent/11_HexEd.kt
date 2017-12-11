package net.grandcentrix.advent

fun hexDistance(input: List<String>) = hexDistanceAndLargestDistance(input).first

fun largestHexDistance(input: List<String>) = hexDistanceAndLargestDistance(input).second

fun hexDistanceAndLargestDistance(input: List<String>): Pair<Int, Int> {
    var x = 0
    var y = 0
    var z = 0
    var maxDistance = 0

    input.forEach {
        when (it) {
            "n"  -> { y++; z--}
            "s"  -> { y--; z++}
            "nw" -> { y++; x--}
            "se" -> { y--; x++}
            "ne" -> { z--; x++}
            "sw" -> { z++; x--}
        }
        maxDistance = maxOf(distance(x, y, z), maxDistance)
    }

    return distance(x, y, z) to maxDistance
}

fun distance(x: Int, y: Int, z: Int): Int = maxOf(maxOf(x, y), z)
