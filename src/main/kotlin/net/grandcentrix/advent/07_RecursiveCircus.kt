package net.grandcentrix.advent

data class CircusProgram(val name: String, val weight: Int)

infix fun Pair<String, Int>.children(that: List<String>): Pair<CircusProgram, List<String>> = Pair(CircusProgram(first, second), that)

fun parseCircusInput(filename: String): Map<CircusProgram, List<String>> {
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

fun rootElement(input: Map<CircusProgram, List<String>>): String {
    var root = ""

    input.forEach { circusProgram, children ->
        val parent = findParent(input, circusProgram.name)
        if (parent == null) {
            root = circusProgram.name
        }
    }
    return root
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
