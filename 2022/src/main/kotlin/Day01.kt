fun solveDay01(input: String, take: Int): Int {
    return input.split("\n\n")
        .map { elf -> elf.split("\n").sumOf { calories -> calories.toInt() } }
        .sortedDescending()
        .take(take)
        .sum()
}

fun main() {
    val inputExample = readFile("day01_example.txt")
    val inputTask = readFile("day01.txt")

    println("Solution for task 1 example: ${solveDay01(inputExample, 1)}") // 24000
    println("Solution for task 1 task:    ${solveDay01(inputTask, 1)}") // 70764
    println("Solution for task 2 example: ${solveDay01(inputExample, 3)}") // 45000
    println("Solution for task 2 task:    ${solveDay01(inputTask, 3)}") // 203905
}

