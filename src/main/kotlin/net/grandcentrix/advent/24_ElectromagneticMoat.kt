package net.grandcentrix.advent

internal fun strongestBridge(input: List<String>): Int {
    val strongestBridge = findPossibleParts(0, listOf(), parseInput(input))
    return strengthForBridge(strongestBridge)
}

private fun findPossibleParts(joint: Int, before: List<Pair<Int, Int>>, rest: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    return rest.filter { it.first == joint || it.second == joint }
            .map { findPossibleParts(if (joint == it.first) it.second else it.first, before + it, rest - it) }
            .maxWith(compareBy { strengthForBridge(it) }) ?: before
}

private fun parseInput(input: List<String>): List<Pair<Int, Int>> {
    return input.map {
        val split = it.split("/")
        split[0].toInt() to split[1].toInt()
    }.toList()
}

private fun strengthForBridge(bridge: List<Pair<Int, Int>>) = bridge.sumBy { it.first + it.second }
