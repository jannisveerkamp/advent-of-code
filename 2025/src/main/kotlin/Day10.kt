import common.Dijkstra
import common.DijkstraNode

private const val OFF = '.'
private const val ON = '#'
private const val REGEX_DAY10 = """(\[.*?\])\s*(.*?)\s*(\{.*\})"""

private fun List<Boolean>.print(): String = map { if (it) ON else OFF }.joinToString("")

private data class Button(val numbers: List<Int>) {

    fun applyDiagram(diagram: List<Boolean>): List<Boolean> = diagram.mapIndexed { index, x ->
        if (numbers.contains(index)) !x else x
    }
}

private data class Machine(
    val lightDiagram: List<Boolean>,
    val buttons: List<Button>,
    val placeholder: List<Int>,
) : DijkstraNode<Machine> {

    override fun neighbors(): Map<Machine, Int> = buttons.associate { button ->
        Machine(button.applyDiagram(lightDiagram), buttons - button, placeholder) to 1
    }
}

private fun parseDay10(input: String): List<Machine> = input.lines().map { line ->
    val groups = REGEX_DAY10.toRegex().matchEntire(line)!!.groupValues
    val lightDiagram = groups[1].removePrefix("[").removeSuffix("]").map { it == ON }
    val buttons = groups[2].trim().split(" ").map {
        it.removePrefix("(").removeSuffix(")").split(",").map { it.toInt() }
    }.map { Button(it) }
    val placeholder = groups[3].removePrefix("{").removeSuffix("}").split(",").map { it.toInt() }
    Machine(lightDiagram, buttons, placeholder)
}

private fun solveDay10a(input: String): Int = parseDay10(input).sumOf { machine ->
    val dijkstra = object : Dijkstra<Machine> {}
    val result = dijkstra.solve(
        Machine(machine.lightDiagram.map { false }, machine.buttons, machine.placeholder), machine
    ) { one, other -> if (one.lightDiagram == other.lightDiagram) 0 else -1 }
    result.filter { it.key.lightDiagram == machine.lightDiagram }.minOf { it.value }
}

private fun solveDay10b(input: String): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day10_example.txt")
    val inputTask = readFile("day10.txt")

    println("Solution for task 1 example: ${solveDay10a(inputExample)}") // 7
    println("Solution for task 1 task:    ${solveDay10a(inputTask)}") // 578
    println("Solution for task 2 example: ${solveDay10b(inputExample)}") // ???
    println("Solution for task 2 task:    ${solveDay10b(inputTask)}") // ???
}

