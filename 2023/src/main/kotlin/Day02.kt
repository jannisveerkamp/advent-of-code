import kotlin.math.max

private data class Cube(val number: Int, val color: String)
private data class Game(val gameId: Int, val cubes: List<List<Cube>>)

private fun parseCubes(input: String): List<Game> = input.split("\n").map { line ->
    val (gameNumber, allCubes) = line.removePrefix("Game ").split(": ")
    val cubes = allCubes.trim().split("; ").map { draw ->
        draw.split(", ").map { rgb ->
            val (numberString, color) = rgb.split(" ")
            Cube(numberString.toInt(), color)
        }
    }
    Game(gameNumber.toInt(), cubes)
}

private fun solveDay02a(input: String): Int = parseCubes(input).sumOf { game ->
    game.cubes.forEach { setOfCubes ->
        setOfCubes.forEach { cube ->
            when (cube.color) {
                "red" -> if (cube.number > 12) return@sumOf 0
                "green" -> if (cube.number > 13) return@sumOf 0
                "blue" -> if (cube.number > 14) return@sumOf 0
            }
        }
    }
    game.gameId
}

private fun solveDay02b(input: String): Int = parseCubes(input).sumOf { game ->
    var minRed = 0
    var minBlue = 0
    var minGreen = 0
    game.cubes.forEach { setOfCubes ->
        setOfCubes.forEach { cube ->
            when (cube.color) {
                "red" -> minRed = max(minRed, cube.number)
                "green" -> minGreen = max(minGreen, cube.number)
                "blue" -> minBlue = max(minBlue, cube.number)
            }
        }
    }
    minRed * minGreen * minBlue
}

fun main() {
    val inputExample = readFile("day02_example.txt")
    val inputTask = readFile("day02.txt")

    println("Solution for task 1 example: ${solveDay02a(inputExample)}") // 8
    println("Solution for task 1 task:    ${solveDay02a(inputTask)}") // 3099
    println("Solution for task 2 example: ${solveDay02b(inputExample)}") // 2286
    println("Solution for task 2 task:    ${solveDay02b(inputTask)}") // 72970
}

