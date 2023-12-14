import common.rotate90Clockwise

private const val ROCK_CUBE = '#'
private const val ROCK_ROUND = 'O'
private const val EMPTY = '.'

private fun solveDay13a(input: String): Int {
    val grid = input.split("\n").map { line -> line.toCharArray() }.toTypedArray()
    return grid.moveUp().totalLoad()
}

private fun Array<CharArray>.totalLoad(): Int {
    return withIndex().sumOf { (index, row) -> row.count { it == ROCK_ROUND } * (size - index) }
}

private fun Array<CharArray>.moveUp(): Array<CharArray> {
    val new = Array(size) { CharArray(first().size) { '.' } }
    for (j in 0 until first().size) {
        var index = 0
        for (i in indices) {
            if (get(i)[j] == ROCK_ROUND) {
                new[index++][j] = ROCK_ROUND
            } else if (get(i)[j] == ROCK_CUBE) {
                new[i][j] = ROCK_CUBE
                index = i + 1
            }
        }
    }
    return new
}

private fun runCycle(currentGrid: Array<CharArray>): Array<CharArray> = currentGrid
    .moveUp().rotate90Clockwise()
    .moveUp().rotate90Clockwise()
    .moveUp().rotate90Clockwise()
    .moveUp().rotate90Clockwise()

private fun solveDay13b(input: String): Int {
    val grid = input.split("\n").map { line -> line.toCharArray() }.toTypedArray()

    var currentGrid = grid
    var cycle = 0
    val knownPatterns: MutableMap<List<String>, Int> = mutableMapOf()
    val maxCycles = 1_000_000_000

    while (cycle < maxCycles) {
        cycle++
        currentGrid = runCycle(currentGrid)
        val pattern = currentGrid.map { it.concatToString() }

        if (knownPatterns.contains(pattern)) {
            val remainingCycles = (maxCycles - cycle) % (cycle - knownPatterns[pattern]!!)
            for (i in 0 until remainingCycles) {
                currentGrid = runCycle(currentGrid)
            }
            return currentGrid.totalLoad()
        } else {
            knownPatterns += pattern to cycle
        }
    }
    return 0
}

fun main() {
    val inputExample = readFile("day14_example.txt")
    val inputTask = readFile("day14.txt")

    println("Solution for task 1 example: ${solveDay13a(inputExample)}") // 136
    println("Solution for task 1 task:    ${solveDay13a(inputTask)}") // 109424
    println("Solution for task 2 example: ${solveDay13b(inputExample)}") // 64
    println("Solution for task 2 task:    ${solveDay13b(inputTask)}") // 102509
}

