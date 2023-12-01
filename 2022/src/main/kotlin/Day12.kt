import common.Dijkstra
import common.DijkstraNode

data class Cliff(val char: Char, val x: Int, val y: Int) : DijkstraNode<Cliff> {
    private lateinit var cliffs: List<List<Cliff>>
    private val height = char.code

    override fun neighbors(): Map<Cliff, Int> {
        val u = if (y - 1 >= 0) cliffs[y - 1][x] else null
        val d = if (y + 1 < cliffs.size) cliffs[y + 1][x] else null
        val l = if (x - 1 >= 0) cliffs[y][x - 1] else null
        val r = if (x + 1 < cliffs[y].size) cliffs[y][x + 1] else null

        return listOfNotNull(u, d, l, r).filter { height - it.height <= 1 }.associateWith { 1 }
    }

    fun withCliffs(cliffs: MutableList<MutableList<Cliff>>) = apply { this.cliffs = cliffs }
}

fun solveDay12(input: String): Pair<Int, Int> {
    val lowElevationCliffs = mutableSetOf<Cliff>()
    lateinit var start: Cliff
    lateinit var end: Cliff

    val cliffs: MutableList<MutableList<Cliff>> = mutableListOf()
    input.split("\n").forEachIndexed { indexRow, line ->
        val cliffRow = mutableListOf<Cliff>()
        line.forEachIndexed { indexColumn, char ->
            val cliff = when (char) {
                'S' -> Cliff('a', indexColumn, indexRow).also {
                    start = it
                    lowElevationCliffs.add(it)
                }
                'a' -> Cliff('a', indexColumn, indexRow).also {
                    lowElevationCliffs.add(it)
                }
                'E' -> Cliff('z', indexColumn, indexRow).also { end = it }
                else -> Cliff(char, indexColumn, indexRow)
            }
            cliff.withCliffs(cliffs)
            cliffRow.add(cliff)
        }
        cliffs.add(cliffRow)
    }

    val dijkstra = object : Dijkstra<Cliff> {}
    val part1 = dijkstra.solve(end, start)[start]!!
    val part2 = dijkstra.solve(end).filterKeys { lowElevationCliffs.contains(it) }.minOf { it.value }
    return part1 to part2
}

fun main() {
    val inputExample = readFile("day12_example.txt")
    val inputTask = readFile("day12.txt")

    println("Solution for task 1 example: ${solveDay12(inputExample).first}") // 31
    println("Solution for task 1 task:    ${solveDay12(inputTask).first}") // 520
    println("Solution for task 2 example: ${solveDay12(inputExample).second}") // 29
    println("Solution for task 2 task:    ${solveDay12(inputTask).second}") // 508
}

