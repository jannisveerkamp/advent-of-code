package net.grandcentrix.advent

fun numberOfGroups(input: List<String>): Int {
    return -1
}

fun groupPrograms(input: List<String>): Map<Int, MutableSet<Int>> {
    val programs = parseInput(input)

    var changed = true
    while (changed) {
        changed = false
        programs.forEach { key, values ->
            values.forEach {
                val sizeBefore = programs[it]!!.size
                programs[it]!!.addAll(values)
                if (sizeBefore != programs[it]!!.size) {
                    changed = true
                }
            }
        }
    }

    return programs
}

fun parseInput(input: List<String>): Map<Int, MutableSet<Int>> {
    return input.associate {
        val key = it.substring(0, it.indexOf(" ")).toInt()
        val values = it.substring(it.indexOf(">") + 2, it.length).split(", ").map { it2 -> it2.toInt() }
        key to values.toMutableSet()
    }
}