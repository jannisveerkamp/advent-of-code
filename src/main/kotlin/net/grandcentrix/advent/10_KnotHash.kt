package net.grandcentrix.advent

fun knotHash(input: List<Int>, inputLengths: List<Int>): Int {
    val result = knot(input, inputLengths)
    return result[0] * result[1]
}

fun knot(input: List<Int>, inputLengths: List<Int>): List<Int> {
    return input
}
