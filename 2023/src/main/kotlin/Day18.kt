import common.Direction
import common.Point
import common.shoelaceArea

private fun lavaArea(instructions: List<Pair<Direction, Int>>): Long {
    val border = instructions.sumOf { it.second }
    val vertices = instructions.runningFold(Point(0, 0)) { last, dig -> last + dig.first.toPoint() * dig.second }
    val interior = shoelaceArea(vertices) - (border / 2) + 1
    return interior + border
}

private fun solveDay18a(input: String): Long {
    val directions = input.split("\n").map { line ->
        val (dir, amount) = line.split(" ")
        when (dir) {
            "U" -> Direction.N
            "D" -> Direction.S
            "L" -> Direction.W
            "R" -> Direction.E
            else -> error("Unknown direction: $dir")
        } to amount.toInt()
    }
    return lavaArea(directions)
}

private fun solveDay18b(input: String): Long {
    val directions = input.split("\n").map { line ->
        val (_, _, colorString) = line.split(" ")
        val color = colorString.removePrefix("(#").removeSuffix(")")
        when (color.last()) {
            '0' -> Direction.E
            '1' -> Direction.S
            '2' -> Direction.W
            '3' -> Direction.N
            else -> error("Unknown direction: ${color.last()}")
        } to color.dropLast(1).toInt(radix = 16)
    }
    return lavaArea(directions)
}

fun main() {
    val inputExample = readFile("day18_example.txt")
    val inputTask = readFile("day18.txt")

    println("Solution for task 1 example: ${solveDay18a(inputExample)}") // 62
    println("Solution for task 1 task:    ${solveDay18a(inputTask)}") // 53300
    println("Solution for task 2 example: ${solveDay18b(inputExample)}") // 952408144115
    println("Solution for task 2 task:    ${solveDay18b(inputTask)}") // 64294334780659
}

