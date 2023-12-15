
private fun String.customHash(): Int {
    var sum = 0
    forEach { char ->
        sum = (sum + char.code) * 17 % 256
    }
    return sum
}

private fun solveDay15a(input: String): Int = input.split(",").sumOf { step -> step.customHash() }

private fun solveDay15b(input: String): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day15_example.txt")
    val inputTask = readFile("day15.txt")

    println("Solution for task 1 example: ${solveDay15a(inputExample)}") // 1320
    println("Solution for task 1 task:    ${solveDay15a(inputTask)}") // ???
    println("Solution for task 2 example: ${solveDay15b(inputExample)}") // ???
    println("Solution for task 2 task:    ${solveDay15b(inputTask)}") // ???
}

