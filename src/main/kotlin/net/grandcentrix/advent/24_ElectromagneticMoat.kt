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

private fun parseInput(input: List<String>): List<Pair<Int, Int>> {
    return input.map {
        val split = it.split("/")
        split[0].toInt() to split[1].toInt()
    }.toList()
}

private fun strength(bridge: List<Pair<Int, Int>>) = bridge.sumBy { it.first + it.second }
