package net.grandcentrix.advent

fun knotHash(input: List<Int>, inputLengths: List<Int>): Int {
    val result = knot(input, inputLengths)
    return result[0] * result[1]
}

fun knot(input: List<Int>, inputLengths: List<Int>, iterations: Int = 1): List<Int> {
    val currentList = input.toMutableList()
    var currentPosition = 0
    var skipSize = 0

    repeat(iterations) {
        inputLengths.forEach { length ->
            val start = currentPosition
            val end = (currentPosition + length) % currentList.size

            val replacement = if (end >= start) {
                currentList.subList(start, end)
            } else {
                currentList.subList(start, currentList.size) + currentList.subList(0, end)
            }.reversed()

            repeat(replacement.size) {
                currentList[(it + start) % currentList.size] = replacement[it]
            }

            currentPosition = (currentPosition + length + skipSize) % currentList.size
            skipSize++
        }
    }

    return currentList
}

fun denseHash(input: String, inputLengths: List<Int>, iterations: Int = 1): String {
    val sequence = appendSequence(toAscii(input))
    val knot = knot(inputLengths, sequence, iterations)
    val xor16 = xor16(knot)
    return toHexadecimal(xor16)
}

fun toHexadecimal(input: List<Int>): String {
    return input.joinToString(separator = "") {
        java.lang.Integer.toHexString(it).padStart(2, '0')
    }
}

fun xor16(input: List<Int>): List<Int> {
    return input.chunked(16).map { it.reduce { current, next -> current xor next }}
}

fun toAscii(input: String): List<Int> = input.map { it.toInt() }

fun appendSequence(input: List<Int>): List<Int> = input + listOf(17, 31, 73, 47, 23)
