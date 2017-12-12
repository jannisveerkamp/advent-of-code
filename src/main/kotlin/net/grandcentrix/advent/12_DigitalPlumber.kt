package net.grandcentrix.advent

internal fun numberOfGroups(input: List<String>) = joinGroups(input).values.toSet().size

internal fun joinGroups(input: List<String>): Map<Int, MutableSet<Int>> {
    val groups = parseInput(input)

    var changed = true
    while (changed) {
        changed = false
        groups.forEach { _, values ->
            values.map {
                changed = groups[it]!!.addAll(values) || changed
            }
        }
    }

    return groups
}

private fun parseInput(input: List<String>): Map<Int, MutableSet<Int>> {
    return input.associate {
        val key = it.substring(0, it.indexOf(" ")).toInt()
        val values = it.substring(it.indexOf(">") + 2, it.length).split(", ").map { it2 -> it2.toInt() }
        key to values.toMutableSet()
    }
}