package net.grandcentrix.advent

fun hexDistance(input: List<String>): Int {
    var xCoordinate = 0
    var yCoordinate = 0
    var zCoordinate = 0

    input.forEach {
        when (it) {
            "n" -> {
                yCoordinate++
                zCoordinate--
            }
            "s" -> {
                yCoordinate--
                zCoordinate++
            }
            "nw" -> {
                yCoordinate++
                xCoordinate--
            }
            "se" -> {
                yCoordinate--
                xCoordinate++
            }
            "ne" -> {
                zCoordinate--
                xCoordinate++
            }
            "sw" -> {
                zCoordinate++
                xCoordinate--
            }
        }
    }

    return Math.max(Math.max(Math.abs(xCoordinate), Math.abs(yCoordinate)), Math.abs(zCoordinate))
}