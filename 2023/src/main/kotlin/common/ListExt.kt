package common

fun <T> List<List<T>>.transpose(): List<List<T>> {
    val result = (first().indices).map { mutableListOf<T>() }.toMutableList()
    forEach { list -> result.zip(list).forEach { it.first.add(it.second) } }
    return result
}

fun Array<CharArray>.transpose(): Array<CharArray> {
    val cols = first().size
    val rows = size
    return Array(cols) { j ->
        CharArray(rows) { i ->
            get(i)[j]
        }
    }
}

fun Array<CharArray>.rotate90Clockwise(): Array<CharArray> = transpose().map { it.reversedArray() }.toTypedArray()