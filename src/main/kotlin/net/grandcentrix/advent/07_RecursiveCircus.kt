package net.grandcentrix.advent

data class CircusProgram(val name: String, val weight: Int)

infix fun Pair<String, Int>.children(that: List<String>): Pair<CircusProgram, List<String>> = Pair(CircusProgram(first, second), that)

fun parseCircusInput(filename: String): MutableMap<CircusProgram, List<String>> {
    val lines = linesFromResource(filename)
    val map = mutableMapOf<CircusProgram, List<String>>()

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
        map.put(CircusProgram(name, weight), list)
    }

    return map
}

class Node(var value: CircusProgram) {
    var parent: Node? = null
    var children: MutableList<Node> = mutableListOf()
    var tempChildren: MutableList<String> = mutableListOf()

    fun addChild(node: Node) {
        children.add(node)
        node.parent = this
    }

    fun addTempChild(nodeName: String) {
        tempChildren.add(nodeName)
    }

    override fun toString(): String {
        var s = "${value}"
        if (!children.isEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }
}

fun buildTree(input: MutableMap<CircusProgram, List<String>>): Node {
    var root: Node? = null

    // Find the root node
    input.forEach { circusProgram, children ->
        val parent = findParent(input, circusProgram.name)
        if (parent == null) {
            root = Node(circusProgram)
            children.forEach {
                root!!.addTempChild(it)
            }
        }
    }
    input.remove(root!!.value)

    buildTreeRecursive(root!!, input)

    return root!!
}

fun buildTreeRecursive(root: Node, input: MutableMap<CircusProgram, List<String>>) {
    root.tempChildren.forEach { tempName ->
        input.forEach { circusProgram, children ->
            if (tempName == circusProgram.name) {
                val node = Node(circusProgram)
                children.forEach {
                    node.addTempChild(it)
                }
                root.addChild(node)
            }
        }
    }

    root.children.forEach { node ->
        buildTreeRecursive(node, input)
    }
}

fun findParent(input: Map<CircusProgram, List<String>>, name: String): CircusProgram? {
    var parent: CircusProgram? = null
    input.forEach({ program, children ->
        if (children.contains(name)) {
            parent = program
        }
    })
    return parent
}
