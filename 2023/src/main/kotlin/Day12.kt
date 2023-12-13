private data class Spring(val spring: String, val numbers: List<Int>)

private val cache = mutableMapOf<Pair<String, List<Int>>, Long>()

private fun countSprings(spring: Spring): Long {
    val config: String = spring.spring
    val groups: List<Int> = spring.numbers

    if (groups.isEmpty()) {
        return if ("#" in config) 0 else 1
    }
    if (config.isEmpty()) {
        return 0
    }

    return cache.getOrPut(config to groups) {
        var result = 0L
        if (config.first() in ".?") {
            result += countSprings(Spring(config.drop(1), groups))
        }
        if (
            config.first() in "#?" &&
            groups.first() <= config.length &&
            "." !in config.take(groups.first()) &&
            (groups.first() == config.length || config[groups.first()] != '#')
        ) {
            result += countSprings(Spring(config.drop(groups.first() + 1), groups.drop(1)))
        }
        result
    }
}

private fun solveDay10a(input: String): Long {
    val springs = input.split("\n").map { line ->
        val (spring, orders) = line.split(" ")
        val numbers = orders.split(",").map(String::toInt)
        Spring(spring, numbers)
    }
    return springs.sumOf { countSprings(it) }
}

private fun solveDay10b(input: String): Long {
    val springs = input.split("\n").map { line ->
        val (spring, orders) = line.split(" ")
        val numbers = "$orders,".repeat(5).split(",").filter { it.isNotBlank() }.map(String::toInt)
        Spring("$spring?".repeat(5).dropLast(1), numbers)
    }
    return springs.sumOf { countSprings(it) }
}

fun main() {
    val inputExample = readFile("day12_example.txt")
    val inputTask = readFile("day12.txt")

    println("Solution for task 1 example: ${solveDay10a(inputExample)}") // 21
    println("Solution for task 1 task:    ${solveDay10a(inputTask)}") // 7286
    println("Solution for task 2 example: ${solveDay10b(inputExample)}") // 525152
    println("Solution for task 2 task:    ${solveDay10b(inputTask)}") // 25470469710341
}

