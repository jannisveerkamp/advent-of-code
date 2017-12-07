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

fun buildTree(input: MutableList<CircusProgram>): Node {
    var root: Node? = null

    // Find the root node
    input.forEach { circusProgram ->
        val parent = findParent(input, circusProgram.name)
        if (parent == null) {
            root = Node(circusProgram)
            circusProgram.children.forEach {
                root!!.addTempChild(it)
            }
        }
    }
    input.remove(root!!.value)

    // Build the whole tree
    buildTreeRecursive(root!!, input)

    return root!!
}

fun buildTreeRecursive(root: Node, input: MutableList<CircusProgram>) {
    root.tempChildren.forEach { tempName ->
        input.forEach { circusProgram ->
            if (tempName == circusProgram.name) {
                val node = Node(circusProgram)
                circusProgram.children.forEach {
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

fun findParent(input: List<CircusProgram>, name: String): CircusProgram? {
    var parent: CircusProgram? = null
    input.forEach({ circusProgram ->
        if (circusProgram.children.contains(name)) {
            parent = circusProgram
        }
    })
    return parent
}
