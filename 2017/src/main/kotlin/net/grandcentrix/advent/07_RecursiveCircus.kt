package net.grandcentrix.advent

data class CircusProgram(val name: String, val weight: Int, val children: List<String>)

fun parseCircusInput(filename: String): MutableList<CircusProgram> {
    val lines = linesFromResource(filename)
    val circusPrograms = mutableListOf<CircusProgram>()

    lines.forEach { line ->
        val space = line.indexOf(" ")
        val weightStart = line.indexOf("(") + 1
        val weightEnd = line.indexOf(")")
        val listStart = line.indexOf("->")

        val name = line.substring(0, space)
        val weight = line.substring(weightStart, weightEnd).toInt()

        val list = if (listStart != -1) {
            line.substring(listStart + 3, line.length).split(", ")
        } else {
            emptyList()
        }
        circusPrograms.add(CircusProgram(name, weight, list))
    }

    return circusPrograms
}

class Node(var value: CircusProgram) {
    var parent: Node? = null
    var children: MutableList<Node> = mutableListOf()

    fun addChild(node: Node) {
        children.add(node)
        node.parent = this
    }

    override fun toString(): String {
        var s = "$value"
        if (!children.isEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }
}

fun buildTree(input: MutableList<CircusProgram>): Node {
    var root: Node? = null

    // Find the root node
    input.forEach { circusProgram ->
        val parent = findParent(input, circusProgram.name)
        if (parent == null) {
            root = Node(circusProgram)
        }
    }

    // Build the whole tree
    buildTreeRecursive(root!!, input)

    return root!!
}

fun buildTreeRecursive(root: Node, input: MutableList<CircusProgram>) {
    root.value.children.forEach { childName ->
        input.forEach { circusProgram ->
            if (childName == circusProgram.name) {
                val node = Node(circusProgram)
                root.addChild(node)
            }
        }
    }

    root.children.forEach { node ->
        buildTreeRecursive(node, input)
    }
}

fun findParent(input: List<CircusProgram>, name: String): CircusProgram? {
    var parent: CircusProgram? = null
    input.forEach({ circusProgram ->
        if (circusProgram.children.contains(name)) {
            parent = circusProgram
        }
    })
    return parent
}

class NodeException(val number: Int) : Exception("")

fun valueToBalance(node: Node): Int {
    try {
        valueForNode(node)
    } catch (e: NodeException) {
        return e.number
    }
    return -1
}

@Throws(NodeException::class)
fun valueForNode(node: Node): Int {
    if (node.children.isEmpty()) {
        return node.value.weight
    } else {
        val childValues = node.children.associateBy({ it }, { valueForNode(it) })
        val uniqueValues = childValues.values.toSet()

        if (uniqueValues.size > 1) {
            val first = childValues.values.filter { it == uniqueValues.first() }
            val second = childValues.values.filter { it == uniqueValues.last() }

            val wrongNode: Node
            val weightOffset: Int
            if (first.size > second.size) {
                wrongNode = childValues.filter { entry -> entry.value == second.first() }.keys.first()
                weightOffset = second.first() - first.first()
            } else {
                wrongNode = childValues.filter { entry -> entry.value == first.first() }.keys.first()
                weightOffset = first.first() - second.first()
            }

            throw NodeException(wrongNode.value.weight - weightOffset)
        }

        return node.value.weight + childValues.values.sum()
    }
}