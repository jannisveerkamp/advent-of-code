package net.grandcentrix.advent

internal fun permutate(input: String, permutations: String): String {
    val commands = permutations.split(",")
    var result = input

    commands.forEach {
        result = when {
            it.startsWith("s") -> spin(result, it)
            it.startsWith("x") -> exchange(result, it)
            it.startsWith("p") -> partner(result, it)
            else -> it
        }
    }

    return result
}

internal fun spin(input: String, permutation: String): String {
    val shift = input.length - permutation.substring(1).toInt()
    return input.substring(shift) + input.substring(0, shift)
}

internal fun exchange(input: String, permutation: String): String {
    val pos1 = permutation.substring(1, permutation.indexOf("/")).toInt()
    val pos2 = permutation.substring(permutation.indexOf("/") + 1).toInt()
    val builder = StringBuilder(input)
    val charAtPos1 = builder[pos1]
    builder.setCharAt(pos1, builder[pos2])
    builder.setCharAt(pos2, charAtPos1)
    return builder.toString()
}

internal fun partner(input: String, permutation: String): String {
    val char1 = permutation[1]
    val char2 = permutation[3]
    return exchange(input, "x" + input.indexOf(char1) + "/" + input.indexOf(char2))
}
