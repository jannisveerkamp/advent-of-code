fun solveDay06a(input: String, markerSize: Int): Int {
    return input.indexOf(input.windowed(markerSize).first { it.toSet().size == markerSize }) + markerSize
}

fun main() {
    val inputTask = readFile("day06.txt")

    println("Solution for task 1 example: ${solveDay06a("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4)}") // 7
    println("Solution for task 1 example: ${solveDay06a("bvwbjplbgvbhsrlpgdmjqwftvncz", 4)}") // 5
    println("Solution for task 1 example: ${solveDay06a("nppdvjthqldpwncqszvftbrmjlhg", 4)}") // 6
    println("Solution for task 1 example: ${solveDay06a("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4)}") // 10
    println("Solution for task 1 example: ${solveDay06a("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4)}") // 11
    println("Solution for task 1 task:    ${solveDay06a(inputTask, 4)}") // 1361

    println("Solution for task 1 example: ${solveDay06a("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14)}") // 19
    println("Solution for task 1 example: ${solveDay06a("bvwbjplbgvbhsrlpgdmjqwftvncz", 14)}") // 23
    println("Solution for task 1 example: ${solveDay06a("nppdvjthqldpwncqszvftbrmjlhg", 14)}") // 23
    println("Solution for task 1 example: ${solveDay06a("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14)}") // 29
    println("Solution for task 1 example: ${solveDay06a("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14)}") // 26
    println("Solution for task 2 task:    ${solveDay06a(inputTask, 14)}") // 3263
}

