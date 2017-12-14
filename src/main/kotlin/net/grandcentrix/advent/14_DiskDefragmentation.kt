package net.grandcentrix.advent

import java.lang.Integer.parseInt
import java.lang.Integer.toBinaryString

private const val GRID_SIZE = 128

internal fun squaresUsed(input: String): Int {
    val rows = generateSequence(0, Int::inc).take(GRID_SIZE).map { "$input-$it" }
    val lengths = IntArray(256) { it -> it }.toList()

    val squares = rows.map { toBinary(denseHash(it, lengths, 64)) }

    return squares.sumBy { row -> row.sumBy { it.toString().toInt() } }
}

internal fun toBinary(hexHash: String): String {
    return hexHash.map {
        val hex = parseInt(it.toString(), 16)
        toBinaryString(hex).padStart(4, '0')
    }.joinToString(separator = "")
}
