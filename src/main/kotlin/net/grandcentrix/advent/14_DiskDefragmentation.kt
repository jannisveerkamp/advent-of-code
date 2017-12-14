package net.grandcentrix.advent

import java.lang.Integer.parseInt
import java.lang.Integer.toBinaryString

private const val GRID_SIZE = 128

internal fun squaresUsed(input: String): Int {
    val grid = buildGrid(input)
    println(grid.toList().joinToString(separator = "\n"))
    return grid.sumBy { row -> row.sumBy { it.toString().toInt() } }
}

internal fun distinctRegions(input: String): Int {
    val grid = to2dArray(buildGrid(input))
    var label = 2 // start with a label of 2

    grid.forEachIndexed { indexRow, row ->
        row.forEachIndexed { indexColumn, value ->
            if (value == 1) {
                markAround(grid, indexRow, indexColumn, label)
                label++
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

private fun to2dArray(buildGrid: Sequence<String>): Array<IntArray> {
    val array = array2dOfInt(GRID_SIZE, GRID_SIZE)
    buildGrid.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { charIndex, char ->
            array[rowIndex][charIndex] = char.toString().toInt()
        }
    }
    return array
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