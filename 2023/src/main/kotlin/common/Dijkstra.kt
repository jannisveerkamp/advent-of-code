package common

import java.util.*
import kotlin.Comparator

interface DijkstraNode<Node> {
    fun neighbors(): Map<Node, Int>
}

interface DijkstraNodeWithCost<Node> : Comparable<DijkstraNodeWithCost<Node>> {
    fun neighbors(): List<DijkstraNodeWithCost<Node>>
    fun node(): Node
    fun cost(): Int
}

data class GenericNodeWithCost<Node : DijkstraNode<Node>>(val node: Node, val cost: Int) : DijkstraNodeWithCost<Node> {
    override fun neighbors() = node.neighbors().map { GenericNodeWithCost(it.key, it.value) }
    override fun compareTo(other: DijkstraNodeWithCost<Node>) = cost.compareTo(other.cost())
    override fun node() = node
    override fun cost() = cost
}

interface Dijkstra<Node : DijkstraNode<Node>> {

    fun solve(start: Node, end: Node? = null, comparator: Comparator<Node>? = null): MutableMap<Node, Int> {
        val toVisit = PriorityQueue<GenericNodeWithCost<Node>>()
        toVisit.add(start.withCost(minCost()))
        val visited = mutableSetOf<Node>()
        val currentCosts = mutableMapOf<Node, Int>().withDefault { maxCost() }
        currentCosts[start] = minCost()

        while (toVisit.isNotEmpty()) {
            val current: GenericNodeWithCost<Node> = toVisit.poll().also { visited.add(it.node()) }

            val foundEnd: Boolean? = end?.let { node: Node ->
                comparator?.let { it.compare(current.node(), end) == 0 } ?: (current.node() == node)
            }

            if (foundEnd == true) {
                return currentCosts
            }

            current.neighbors().forEach { neighbor ->
                if (!visited.contains(neighbor.node())) {
                    val newCost = current.cost() + neighbor.cost()
                    if (newCost < currentCosts.getValue(neighbor.node())) {
                        currentCosts[neighbor.node()] = newCost
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
