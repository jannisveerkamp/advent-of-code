import kotlin.math.min

private fun solveDay13(input: String): Int {
    val mirrors = input.split("\n\n").map { it.split("\n") }

    var counter = 0
    mirrors.forEach { mirror ->
        // Rows
        (1 until mirror.size).forEach { index ->
            val (first, second) = mirror.chunked(index)
            val firstSize = first.size
            val secondSize = second.size
            if (first.reversed().take(secondSize) == second.take(firstSize)) {
                counter += index * 100
            }
        }

//        val transposed = mirror.map { it.toList() }.transpose().map {it.joinToString("")}
//        (1 until transposed.size).forEach { index ->
//            val (first, second) = transposed.chunked(index)
//            val firstSize = first.size
//            val secondSize = second.size
//            if (first.reversed().take(secondSize) == second.take(firstSize)) {
//                counter += index
//            }
//        }

        // Columns
        (1 until mirror.first().length).forEach { index ->
            val cutOff = mirror.all { line ->
                val x = line.chunked(index).take(2)
                val size = min(x[0].length, x[1].length)
                val first = x[0].reversed().take(size)
                val second = x[1].take(size)
                first == second
            }
            if (cutOff) {
                counter += index
            }
        }
    }

    return counter
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    val result = (first().indices).map { mutableListOf<T>() }.toMutableList()
    forEach { list -> result.zip(list).forEach { it.first.add(it.second) } }
    return result
}


fun main() {
    val inputExample = readFile("day13_example.txt")
    val inputTask = readFile("day13.txt")

    println("Solution for task 1 example: ${solveDay13(inputExample)}") // 405
    println("Solution for task 1 task:    ${solveDay13(inputTask)}") // 34821
    println("Solution for task 2 example: ${solveDay13(inputExample)}") // ???
    println("Solution for task 2 task:    ${solveDay13(inputTask)}") // ???
}

