import common.PointL

private fun extraSpace(grid: Array<Array<Char>>): Pair<List<Int>, List<Int>> {
    val extraSpaceX = (0 until grid.first().size).mapNotNull { x ->
        if (grid.all { it[x] == '.' }) x else null
    }
    val extraSpaceY = grid.mapIndexedNotNull { index, line ->
        if (line.contains('#')) null else index
    }
    return extraSpaceX to extraSpaceY
}

private fun solveDay11(input: String, extraGalaxies: Int): Long {
    val grid = input.split("\n").map { line -> line.toCharArray().map { it }.toTypedArray() }.toTypedArray()
    val (extraSpaceX, extraSpaceY) = extraSpace(grid)

    val galaxies = grid.mapIndexed { y, line ->
        line.mapIndexedNotNull { x, char ->
            if (char == '#') PointL(x.toLong(), y.toLong()) else null
        }
    }.flatten()
    return galaxies.sumOf { first ->
        galaxies.sumOf { second ->
            val extraY = (extraSpaceY).count { it in (first.y..second.y) || it in (second.y..first.y) }
            val extraX = (extraSpaceX).count { it in (first.x..second.x) || it in (second.x..first.x) }
            first.manhattan(second) + (extraX + extraY) * extraGalaxies
        }
    } / 2L
}


fun main() {
    val inputExample = readFile("day11_example.txt")
    val inputTask = readFile("day11.txt")

    println("Solution for task 1 example: ${solveDay11(inputExample, 1)}") // 374
    println("Solution for task 1 task:    ${solveDay11(inputTask, 1)}") // 10154062
    println("Solution for task 2 example: ${solveDay11(inputExample, 10 - 1)}") // 1030
    println("Solution for task 2 example: ${solveDay11(inputExample, 100 - 1)}") // 8410
    println("Solution for task 2 task:    ${solveDay11(inputTask, 1000000 - 1)}") // 553083047914
}

