package net.grandcentrix.advent

internal fun permutate(input: String, permutations: String, iterations: Int = 1): String {
    val commands = permutations.split(",")
    var result = input
    val results = mutableListOf(input)

    repeat(iterations) {
        commands.forEach {
            result = when {
                it.startsWith("s") -> spin(result, it)
                it.startsWith("x") -> exchange(result, it)
                it.startsWith("p") -> partner(result, it)
                else -> it
            }
        }
        if (results.contains(result)) {
            // We already saw that iteration - so the following results will repeat itself.
            return results[iterations % results.size]
        } else {
            results.add(result)
        }
    }
    return result
}

internal fun spin(input: String, permutation: String): String {
    val shift = input.length - permutation.substring(1).toInt()
    return input.substring(shift) + input.substring(0, shift)
}

internal fun exchange(input: String, permutation: String): String {
    val pos = permutation.substring(1).split("/").map { it.toInt() }.sorted()
    val sb = StringBuilder(input)
    val temp = input[pos[0]]
    sb.setCharAt(pos[0], input[pos[1]])
    sb.setCharAt(pos[1], temp)
    return sb.toString()
}

internal fun partner(input: String, permutation: String): String {
    val char1 = permutation[1]
    val char2 = permutation[3]
    return exchange(input, "x" + input.indexOf(char1) + "/" + input.indexOf(char2))
}
