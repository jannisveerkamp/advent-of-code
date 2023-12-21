import common.DijkstraNode
import common.GenericNodeWithCost
import java.util.*
import kotlin.Comparator

interface ExactDijkstra<Node : DijkstraNode<Node>> {

    fun solve(start: Node, end: Node? = null, comparator: Comparator<Node>? = null): MutableMap<Node, List<Int>> {
        val toVisit = PriorityQueue<GenericNodeWithCost<Node>>()
        toVisit.add(start.withCost(minCost()))
        val visited = mutableSetOf<Pair<Node, Int>>()
        val currentCosts = mutableMapOf<Node, List<Int>>().withDefault { listOf(maxCost()) }
        currentCosts[start] = listOf(minCost())

        while (toVisit.isNotEmpty()) {
            val current: GenericNodeWithCost<Node> = toVisit.poll().also { visited.add(it.node() to it.cost) }

            val foundEnd: Boolean? = end?.let { node: Node ->
                comparator?.let { it.compare(current.node(), end) == 0 } ?: (current.node() == node)
            }

            if (foundEnd == true) {
                return currentCosts
            }

            current.neighbors().forEach { neighbor ->
                val newCost = current.cost() + neighbor.cost()
                if (!visited.contains(neighbor.node() to newCost)) {
                    if (!currentCosts.getValue(neighbor.node()).contains(newCost) && newCost <= maxCost()) {
                        currentCosts[neighbor.node()] = (currentCosts[neighbor.node()] ?: emptyList()) + newCost
                        toVisit.add(neighbor.node().withCost(newCost))
                    }
                }
            }
        }

        return currentCosts
    }

    fun Node.withCost(cost: Int): GenericNodeWithCost<Node> = GenericNodeWithCost(this, cost)
    fun minCost(): Int = 0
    fun maxCost(): Int = Int.MAX_VALUE
}


private data class Garden(
    val x: Int,
    val y: Int,
    val tile: Char
) : DijkstraNode<Garden> {

    private lateinit var blocks: List<List<Garden>>

    override fun neighbors(): Map<Garden, Int> {
        val u = if (y - 1 >= 0) blocks[y - 1][x] else null
        val d = if (y + 1 < blocks.size) blocks[y + 1][x] else null
        val l = if (x - 1 >= 0) blocks[y][x - 1] else null
        val r = if (x + 1 < blocks[y].size) blocks[y][x + 1] else null
        return listOfNotNull(u, d, l, r).filter { it.tile != '#' }.associateWith { 1 }
    }

    fun withBlocks(blocks: MutableList<MutableList<Garden>>) = apply { this.blocks = blocks }
}

private fun solveDay21a(input: String, steps: Int): Int {
    val garden: MutableList<MutableList<Garden>> = mutableListOf()
    lateinit var start: Garden
    input.split("\n").forEachIndexed { y, line ->
        val cliffRow = mutableListOf<Garden>()
        line.forEachIndexed { x, char ->
            val block = Garden(x, y, char)
            block.withBlocks(garden)
            if (block.tile == 'S') start = block
            cliffRow.add(block)
        }
        garden.add(cliffRow)
    }
    val dijkstra = object : ExactDijkstra<Garden> {
        override fun maxCost(): Int = steps + 1
    }
    val results = dijkstra.solve(start)
    val filtered = results.filter { it.value.contains(steps) }

    return filtered.size
}

private fun solveDay21b(input: String, steps: Int): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day21_example.txt")
    val inputTask = readFile("day21.txt")

    println("Solution for task 1 example: ${solveDay21a(inputExample, 6)}") // 16
    println("Solution for task 1 example: ${solveDay21b(inputExample, 10 + 2)}") // 50
    println("Solution for task 1 example: ${solveDay21b(inputExample, 50 + 2)}") // 1594
    println("Solution for task 1 example: ${solveDay21b(inputExample, 100 + 2)}") // 6536
    println("Solution for task 1 example: ${solveDay21b(inputExample, 500 + 2)}") // 167004
    println("Solution for task 1 example: ${solveDay21b(inputExample, 1000 + 2)}") // 668697
    println("Solution for task 1 example: ${solveDay21b(inputExample, 5000 + 2)}") // 16733044
    println("Solution for task 1 task:    ${solveDay21a(inputTask, 64)}") // 3830
//    println("Solution for task 1 task:    ${solveDay21b(inputTask, 26501365+2)}") // ???
}

