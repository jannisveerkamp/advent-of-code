package net.grandcentrix.advent

fun knotHash(input: List<Int>, inputLengths: List<Int>): Int {
    val result = knot(input, inputLengths)
    return result[0] * result[1]
}

fun knot(input: List<Int>, inputLengths: List<Int>): List<Int> {
    val currentList = input.toMutableList()
    var currentPosition = 0
    var skipSize = 0

    inputLengths.forEach { length ->
        val start = currentPosition
        val end = (currentPosition + length) % currentList.size

        val replacement = if (end >= start) {
            currentList.subList(start, end).reversed()
        } else {
            (currentList.subList(start, currentList.size) + currentList.subList(0, end)).reversed()
        }
        for (i in 0..(replacement.size - 1)) {
            currentList[(i + start) % currentList.size] = replacement[i]
        }

        currentPosition = (currentPosition + length + skipSize) % currentList.size
        skipSize++
    }

    return currentList
}
