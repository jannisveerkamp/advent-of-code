package net.grandcentrix.advent

import java.lang.Integer.parseInt
import java.lang.Integer.toBinaryString

private const val GRID_SIZE = 128

internal fun squaresUsed(input: String): Int {
    val grid = buildGrid(input)
    return grid.sumBy { row -> row.sumBy { it.toString().toInt() } }
}

internal fun distinctRegions(input: String): Int {
    val grid = buildGrid(input)
    return -1
}

internal fun toBinary(hexHash: String): String {
    return hexHash.map {
        val hex = parseInt(it.toString(), 16)
        toBinaryString(hex).padStart(4, '0')
    }.joinToString(separator = "")
}

private fun buildGrid(input: String): Sequence<String> {
    val rows = generateSequence(0, Int::inc).take(GRID_SIZE).map { "$input-$it" }
    val lengths = IntArray(256) { it -> it }.toList()
    return rows.map { toBinary(denseHash(it, lengths, 64)) }
}