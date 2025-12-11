private fun parseDay11(input: String): List<Rack> = input.lines().map { line ->
    val (input, outputs) = line.split(": ")
    Rack(input, outputs.split(" "))
}

private data class Rack(val input: String, val outputs: List<String>)

private fun findNext(input: String, racks: List<Rack>): Long {
    return racks.filter { it.input == input }.map { it.outputs }.flatten().sumOf { next ->
        when {
            next == "out" -> 1
            else -> findNext(next, racks)
        }
    }
}

private fun solveDay11a(input: String): Long {
    val racks = parseDay11(input)
    return findNext("you", racks)
}

private fun solveDay11b(input: String): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day11_example.txt")
    val inputTask = readFile("day11.txt")

    println("Solution for task 1 example: ${solveDay11a(inputExample)}") // 5
    println("Solution for task 1 task:    ${solveDay11a(inputTask)}") // 613
    println("Solution for task 1 example: ${solveDay11b(inputExample)}") // ???
    println("Solution for task 1 task:    ${solveDay11b(inputTask)}") // ???
}

