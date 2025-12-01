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