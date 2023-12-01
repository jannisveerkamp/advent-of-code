package net.grandcentrix.advent

import java.lang.Integer.parseInt
import java.lang.Integer.toBinaryString

private const val GRID_SIZE = 128

internal fun squaresUsed(input: String) = buildGrid(input).sumBy { row -> row.sum() }

internal fun distinctRegions(input: String): Int {
    val grid = buildGrid(input)
    var label = 2 // start with a label of 2

    grid.forEachIndexed { indexRow, row ->
        row.forEachIndexed { indexColumn, value ->
            if (value == 1) {
                markAround(grid, indexRow, indexColumn, label++)
            }
        }
    }

    return label - 2
}

private fun markAround(grid: Array<IntArray>, row: Int, column: Int, label: Int) {
    if (grid[row][column] == 1) {
        grid[row][column] = label

        if (row > 0) {
            markAround(grid, row - 1, column, label)
        }
        if (row < GRID_SIZE - 1) {
            markAround(grid, row + 1, column, label)
        }
        if (column > 0) {
            markAround(grid, row, column - 1, label)
        }
        if (column < GRID_SIZE - 1) {
            markAround(grid, row, column + 1, label)
        }
    }
}

internal fun toBinary(hexHash: String): String {
    return hexHash.map {
        val hex = parseInt(it.toString(), 16)
        toBinaryString(hex).padStart(4, '0')
    }.joinToString(separator = "")
}

private fun buildGrid(input: String): Array<IntArray> {
    val rows = generateSequence(0, Int::inc).take(GRID_SIZE).map { "$input-$it" }.toList()
    val lengths = IntArray(256) { it -> it }.toList()
    return rows.map {
        toBinary(denseHash(it, lengths, 64)).map { it.toString().toInt() }.toIntArray()
    }.toTypedArray()
}