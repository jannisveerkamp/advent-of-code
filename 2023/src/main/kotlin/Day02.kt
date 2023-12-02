import kotlin.math.max

private fun solveDay02a(input: String): Int {
    val redMax = 12
    val greenMax = 13
    val blueMax = 14

    var counter = 0
    input.split("\n").map { line ->
        val (gameNumber, allCubes) = line.removePrefix("Game ").split(": ")
        val cubes = allCubes.trim().split("; ")
        var possible = true
        cubes.map { draw ->
            val rgb = draw.split(", ")
            rgb.forEach {
                val (numberString, color) = it.split(" ")
                val number = numberString.toInt()
                when (color) {
                    "red" -> if (number > redMax) possible = false
                    "green" -> if (number > greenMax) possible = false
                    "blue" -> if (number > blueMax) possible = false
                }
            }
        }
        if (possible) {
            counter += gameNumber.toInt()
        }
    }
    return counter
}

private fun solveDay02b(input: String): Int {
    var counter = 0
    input.split("\n").map { line ->
        val (_, allCubes) = line.removePrefix("Game ").split(": ")
        val cubes = allCubes.trim().split("; ")
        var minRed = 0
        var minBlue = 0
        var minGreen = 0
        cubes.map { draw ->
            val rgb = draw.split(", ")
            rgb.forEach {
                val (numberString, color) = it.split(" ")
                val number = numberString.toInt()
                when (color) {
                    "red" -> minRed = max(minRed, number)
                    "green" -> minGreen = max(minGreen, number)
                    "blue" -> minBlue = max(minBlue, number)
                }
            }
        }
        counter += minRed * minGreen * minBlue
    }
    return counter
}

fun main() {
    val inputExample = readFile("day02_example.txt")
    val inputTask = readFile("day02.txt")

    println("Solution for task 1 example: ${solveDay02a(inputExample)}") // 8
    println("Solution for task 1 task:    ${solveDay02a(inputTask)}") // 3099
    println("Solution for task 2 example: ${solveDay02b(inputExample)}") // 2286
    println("Solution for task 2 task:    ${solveDay02b(inputTask)}") // 72970
}

