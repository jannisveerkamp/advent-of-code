package common

fun <T> List<List<T>>.transpose(): List<List<T>> = List(first().size) { j ->
    List(size) { i ->
        get(i)[j]
    }
}

fun Array<CharArray>.transpose(): Array<CharArray> = Array(first().size) { j ->
    CharArray(size) { i ->
        get(i)[j]
    }
}

fun Array<CharArray>.rotate90Clockwise(): Array<CharArray> = transpose().map { it.reversedArray() }.toTypedArray()
fun <T> List<T>.zipWithItselfAllUnique(): List<Pair<T, T>> {
    val result = mutableListOf<Pair<T, T>>()
    val seenPairs = mutableSetOf<Pair<T, T>>()
    forEach { point ->
        filterNot { it == point }.map { other ->
            val pair = point to other
            if (!seenPairs.contains(pair)) {
                result.add(pair)
                seenPairs.add(pair)
                seenPairs.add(Pair(other, point))
            }
        }
    }
    return result
}