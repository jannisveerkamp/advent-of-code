private fun parseRace(input: String): Map<Long, Long> {
    val lines = input.split("\n").map { line ->
        line.split("\\s+".toRegex()).drop(1).map { it.toLong() }
    }
    return lines[0].zip(lines[1]).associate { it }
}

private fun parseRaceB(input: String): Pair<Long, Long> {
    val lines = input.split("\n").map { line ->
        line.split("\\s+".toRegex()).drop(1).map { it.toInt() }
    }
    return lines[0].joinToString("").toLong() to lines[1].joinToString("").toLong()
}

private fun race(time: Long, distance: Long): Int = (1 until time).count { currentTime ->
    (time - currentTime) * currentTime > distance
}

private fun solveDay06a(input: String): Int {
    val races = parseRace(input)
    return races.map { race ->
        race(race.key, race.value)
    }.reduce { one, two ->
        one * two
    }
}

private fun solveDay06b(input: String): Int {
    val (time, distance) = parseRaceB(input)
    return race(time, distance)
}

fun main() {
    val inputExample = readFile("day06_example.txt")
    val inputTask = readFile("day06.txt")

    println("Solution for task 1 example: ${solveDay06a(inputExample)}") // 288
    println("Solution for task 1 task:    ${solveDay06a(inputTask)}") // 1710720
    println("Solution for task 2 example: ${solveDay06b(inputExample)}") // 71503
    println("Solution for task 2 task:    ${solveDay06b(inputTask)}") // 35349468
}

