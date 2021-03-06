package net.grandcentrix.advent

private val START = listOf(
        ".#.",
        "..#",
        "###"
)

internal class Grid(var items: Array<Array<GridPiece>>) {
    fun flatten() {
        val flat = mutableListOf<String>()
        items.forEach { row ->
            flat.add(row.joinToString("") { it.rows[0] })
            flat.add(row.joinToString("") { it.rows[1] })
            flat.add(row.joinToString("") { it.rows[2] })
            if (items[0][0].rows.size == 4) {
                flat.add(row.joinToString("") { it.rows[3] })
            }
        }
        items = carveGrid(flat)
    }

    fun grow(rules: Map<String, String>) {
        items.forEach { row ->
            row.forEach {
                it.grow(rules)
            }
        }
    }
}

internal class GridPiece(var rows: List<String>) {
    fun grow(rules: Map<String, String>) {
        rows = applyRule(rules, rows.joinToString("/")).split("/")
    }
}

internal fun carveGrid(input: List<String>): Array<Array<GridPiece>> {
    val size = if (input.size % 2 == 0) 2 else 3
    return input.chunked(size).map { rows ->
        val list = rows.map { it.chunked(size) }
        (0 until list[0].size).map { it -> GridPiece(list.map { it2 -> it2[it] }) }.toTypedArray()
    }.toTypedArray()
}

internal fun doArt(input: List<String>, iterations: Int): Int {
    val rules = parseRuleInput(input)
    val grid = Grid(arrayOf(arrayOf(GridPiece(START))))

    repeat(iterations) {
        grid.grow(rules)
        grid.flatten()
    }
    return grid.items.sumBy { it.sumBy { it.rows.sumBy { it.sumBy { if (it == '#') 1 else 0 } } } }
}

internal fun applyRule(rules: Map<String, String>, sequence: String): String {
    // Rotations
    var rotation = sequence
    repeat(4) {
        if (it > 0) {
            rotation = rotateArray(rotation.split("/").toTypedArray()).joinToString("/") { it.joinToString("") }
        }
        if (rules.containsKey(rotation)) {
            return rules[rotation]!!
        }
        // Flip vertically (one direction is sufficient)
        val flip = flipVertical(rotation)
        if (rules.containsKey(flip)) {
            return rules[flip]!!
        }
    }

    // This shouldn't happen (haha)
    throw IllegalArgumentException("No matching rule found for $sequence :(")
}

private fun flipVertical(sequence: String) = sequence.split("/").reversed().joinToString("/")

internal fun rotateArray(input: Array<String>): Array<CharArray> {
    val n = input.size
    val newArray = Array(n) { CharArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            newArray[i][j] = input[n - j - 1][i]
        }
    }
    return newArray
}

private fun parseRuleInput(input: List<String>) = input.map { it.split(" => ") }.associate { it[0] to it[1] }
