private fun parseDay04(input: String): List<List<Int>> {
    return input.split("\n").map { it.map { if (it == '@') 1 else 0 } }
}

private fun List<List<Int>>.read(x: Int, y: Int): Int = getOrNull(y)?.getOrNull(x) ?: 0

private fun checkElement(x: Int, y: Int, grid: List<List<Int>>): Boolean {
    return grid.read(x + 1, y) + grid.read(x - 1, y) +
            grid.read(x + 1, y + 1) + grid.read(x, y + 1) + grid.read(x - 1, y + 1) +
            grid.read(x + 1, y - 1) + grid.read(x, y - 1) + grid.read(x - 1, y - 1) < 4
}

private fun removeBox(grid: List<List<Int>>): Pair<List<List<Int>>, Int> {
    var removed = 0
    return grid.mapIndexed { y, row ->
        row.mapIndexed { x, element ->
            if (element == 1 && checkElement(x, y, grid)) {
                removed++
                0
            } else {
                element
            }
        }
    } to removed
}

private fun solveDay04a(input: String): Int = removeBox(parseDay04(input)).second

private fun solveDay04b(input: String): Int {
    var grid = parseDay04(input)
    var totalRemoved = 0

    while (true) {
        val (newGrid, removed) = removeBox(grid)
        if (removed > 0) {
            grid = newGrid
            totalRemoved += removed
        } else {
            break
        }
    }
    return totalRemoved
}

fun main() {
    val inputExample = readFile("day04_example.txt")
    val inputTask = readFile("day04.txt")

    println("Solution for task 1 example: ${solveDay04a(inputExample)}") // 13
    println("Solution for task 1 task:    ${solveDay04a(inputTask)}") // 1537
    println("Solution for task 2 example: ${solveDay04b(inputExample)}") // 43
    println("Solution for task 2 task:    ${solveDay04b(inputTask)}") // 8707
}

