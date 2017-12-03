package net.grandcentrix.advent

fun manhattanDistance(location: Int): Int {
    var turnDirection = 0 // east
    var stepLength = 1
    var step = 1
    var position = 1
    var x = 0
    var y = 0

    while (position < location) {
        when (turnDirection) {
            0 -> x++ // east
            1 -> y++ // north
            2 -> x-- // west
            3 -> y-- // south
        }

        if (step == stepLength) {
            turnDirection = (turnDirection + 1) % 4
            if (turnDirection % 2 == 0) {
                stepLength++
            }
            step = 1
        } else {
            step++
        }
        position++
    }
    return Math.abs(x) + Math.abs(y)
}
