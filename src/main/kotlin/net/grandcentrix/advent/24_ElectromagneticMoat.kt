package net.grandcentrix.advent

internal fun strengthOfLongestBridge(input: List<String>): Int {
    val strongestBridge = findPossibleParts(0, listOf(), parseInput(input),
            compareBy<List<Pair<Int, Int>>> { it.size } then compareBy { strength(it) })
    return strength(strongestBridge)
}

internal fun strongestBridge(input: List<String>): Int {
    val strongestBridge = findPossibleParts(0, listOf(), parseInput(input), compareBy { strength(it) })
    return strength(strongestBridge)
}

private fun findPossibleParts(joint: Int, before: List<Pair<Int, Int>>, rest: List<Pair<Int, Int>>,
                              comparator: Comparator<List<Pair<Int, Int>>>): List<Pair<Int, Int>> {
    return rest.filter { it.first == joint || it.second == joint }
            .map { findPossibleParts(if (joint == it.first) it.second else it.first, before + it, rest - it, comparator) }
            .maxWith(comparator) ?: before
}

private fun parseInput(input: List<String>) = input.map { it.split("/").map { it.toInt() } }.map { it[0] to it[1] }

private fun strength(bridge: List<Pair<Int, Int>>) = bridge.sumBy { it.first + it.second }
