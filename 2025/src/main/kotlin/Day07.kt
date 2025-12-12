import common.Point

private const val START = 'S'
private const val EMPTY = '.'
private const val SPLITTER = '^'
private const val BEAM = '|'

private fun parseDay07(input: String): List<MutableList<Char>> = input.lines().map { it.toMutableList() }

private fun solveDay07a(input: String): Int {
    val tree = parseDay07(input)
    var numberOfSplits = 0
    tree.forEachIndexed { y, row ->
        row.forEachIndexed { x, field ->
            when (field) {
                EMPTY -> {}
                SPLITTER -> {
                    if (y < tree.lastIndex && tree[y - 1][x] == BEAM) {
                        tree[y + 1][x - 1] = BEAM
                        tree[y + 1][x + 1] = BEAM
                        numberOfSplits++
                    }
                }

                BEAM, START -> {
                    if (y < tree.lastIndex && tree[y + 1][x] == EMPTY) {
                        tree[y + 1][x] = BEAM
                    }
                }
            }
        }
    }
    return numberOfSplits
}

private val memory = mutableMapOf<Point, Long>()

private fun timelines(x: Int, y: Int, tree: List<List<Char>>): Long {
    if (y == tree.lastIndex) return 1
    if (memory.contains(Point(x, y))) return memory[Point(x, y)]!!

    return when (tree[y + 1][x]) {
        EMPTY -> timelines(x, y + 1, tree)
        SPLITTER -> (timelines(x - 1, y + 1, tree) + timelines(x + 1, y + 1, tree)).apply {
            memory[Point(x, y)] = this
        }

        else -> error("???")
    }
}

private fun solveDay07b(input: String): Long {
    return parseDay07(input).let { tree -> timelines(tree.first().indexOf(START), 0, tree) }
}

fun main() {
    val inputExample = readFile("day07_example.txt")
    val inputTask = readFile("day07.txt")

    println("Solution for task 1 example: ${solveDay07a(inputExample)}") // 21
    println("Solution for task 1 task:    ${solveDay07a(inputTask)}") // 1613
    println("Solution for task 2 example: ${solveDay07b(inputExample)}") // 40
    println("Solution for task 2 task:    ${solveDay07b(inputTask)}") // 48021610271997
}

