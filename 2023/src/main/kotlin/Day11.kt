import common.Point

private fun solveDay11(input: String): Int {
    val grid = input.split("\n").map { it.toCharArray().map { it }.toTypedArray() }.toTypedArray()

    val extraSpaceX = mutableListOf<Int>()
    (0 until grid.first().size).forEach { x ->
        if (grid.all { it[x] == '.' }) {
            extraSpaceX.add(x)
        }
    }
    val extraSpaceY = grid.mapIndexedNotNull { index, line ->
        if (line.contains('#')) null else index
    }
    val galaxies = grid.mapIndexed { y, line ->
        line.mapIndexedNotNull { x, char ->
            if (char == '#') Point(x, y) else null
        }
    }.flatten()
    return galaxies.sumOf { first ->
        galaxies.sumOf { second ->
            val extraY = (extraSpaceY).count {
                it in (first.y + 1 until second.y) || it in (second.y + 1 until first.y)
            }
            val extraX = (extraSpaceX).count {
                it in (first.x + 1 until second.x) || it in (second.x + 1 until first.x)
            }
            val manhattan = first.manhattan(second)
            if (manhattan > 0) {
                val result = manhattan + extraX + extraY
                result
            } else {
                0
            }

        }
    } / 2
}


fun main() {
    val inputExample = readFile("day11_example.txt")
    val inputTask = readFile("day11.txt")

    println("Solution for task 1 example: ${solveDay11(inputExample)}") // 374
    println("Solution for task 1 task:    ${solveDay11(inputTask)}") // 10154062
    println("Solution for task 2 example: ${solveDay11(inputExample)}") // ???
    println("Solution for task 2 task:    ${solveDay11(inputTask)}") // ???
}

