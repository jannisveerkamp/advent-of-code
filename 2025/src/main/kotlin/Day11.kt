private fun parseDay11(input: String): List<Rack> = input.lines().map { line ->
    val (input, outputs) = line.split(": ")
    Rack(input, outputs.split(" "))
}

private data class Rack(val input: String, val outputs: List<String>)

private val knownPaths = mutableMapOf<String, Long>()

private fun findNext(input: String, racks: List<Rack>, visitsLeft: List<String>): Long {
    return racks.filter { it.input == input }.map { it.outputs }.flatten().sumOf { next ->
        when {
            next == "out" -> if (visitsLeft.isEmpty()) 1 else 0
            else -> knownPaths[next + visitsLeft.joinToString("")] ?: findNext(next, racks, visitsLeft - next).apply {
                knownPaths[next + visitsLeft.joinToString("")] = this
            }
        }
    }
}

private fun solveDay11a(input: String): Long = findNext("you", parseDay11(input), emptyList())
private fun solveDay11b(input: String): Long = findNext("svr", parseDay11(input), listOf("dac", "fft"))

fun main() {
    val inputExample = readFile("day11_example.txt")
    val inputTask = readFile("day11.txt")

    println("Solution for task 1 example: ${solveDay11a(inputExample)}") // 5
    println("Solution for task 1 task:    ${solveDay11a(inputTask)}") // 613
    println("Solution for task 2 example: ${solveDay11b(inputExample)}") // 2
    println("Solution for task 2 task:    ${solveDay11b(inputTask)}") // 372918445876116
}

