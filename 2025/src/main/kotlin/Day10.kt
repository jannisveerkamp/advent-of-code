import common.Dijkstra
import common.DijkstraNode
import com.microsoft.z3.Context
import com.microsoft.z3.Status

private const val OFF = '.'
private const val ON = '#'
private const val REGEX_DAY10 = """(\[.*?])\s*(.*?)\s*(\{.*})"""

private data class Button(val numbers: List<Int>) {

    fun applyDiagram(diagram: List<Boolean>): List<Boolean> = diagram.mapIndexed { index, x ->
        if (numbers.contains(index)) !x else x
    }

    fun applyJoltage(joltages: List<Int>): List<Int> = joltages.mapIndexed { index, x ->
        if (numbers.contains(index)) x + 1 else x
    }
}

private data class Machine(
    val lightDiagram: List<Boolean>,
    val buttons: List<Button>,
    val joltages: List<Int>,
    val canpressMultipleTimes: Boolean
) : DijkstraNode<Machine> {

    override fun neighbors(): Map<Machine, Int> = buttons.associate { button ->
        Machine(
            button.applyDiagram(lightDiagram),
            if (canpressMultipleTimes) buttons else buttons - button,
            button.applyJoltage(joltages),
            canpressMultipleTimes
        ) to 1
    }
}

private fun parseDay10(input: String): List<Machine> = input.lines().map { line ->
    val groups = REGEX_DAY10.toRegex().matchEntire(line)!!.groupValues
    val lightDiagram = groups[1].removePrefix("[").removeSuffix("]").map { it == ON }
    val buttons = groups[2].trim().split(" ").map {
        it.removePrefix("(").removeSuffix(")").split(",").map { it.toInt() }
    }.map { Button(it) }
    val placeholder = groups[3].removePrefix("{").removeSuffix("}").split(",").map { it.toInt() }
    Machine(lightDiagram, buttons, placeholder, true)
}

private fun solveDay10a(input: String): Int = parseDay10(input).sumOf { machine ->
    val dijkstra = object : Dijkstra<Machine> {}
    val result = dijkstra.solve(
        Machine(machine.lightDiagram.map { false }, machine.buttons, machine.joltages, false), machine
    ) { one, other -> if (one.joltages == other.lightDiagram) 0 else -1 }
    result.filter { it.key.lightDiagram == machine.lightDiagram }.minOf { it.value }
}

private fun solveDay10b(input: String): Int = parseDay10(input).sumOf { machine ->
    val targetJoltages = machine.joltages
    Context().use { ctx ->
        val optimize = ctx.mkOptimize()
        val variables = machine.buttons.indices.map { ctx.mkIntConst("b$it") }
        variables.forEach { optimize.Add(ctx.mkGe(it, ctx.mkInt(0))) }

        targetJoltages.indices.forEach { i ->
            val terms = machine.buttons.withIndex().filter { i in it.value.numbers }.map { variables[it.index] }
            val sum = ctx.mkAdd(*terms.toTypedArray())
            optimize.Add(ctx.mkEq(sum, ctx.mkInt(targetJoltages[i])))
        }
        optimize.MkMinimize(ctx.mkAdd(*variables.toTypedArray()))
        if (optimize.Check() == Status.SATISFIABLE) {
            variables.sumOf { optimize.model.evaluate(it, false).toString().toInt() }
        } else {
            0
        }
    }
}

fun main() {
    val inputExample = readFile("day10_example.txt")
    val inputTask = readFile("day10.txt")

    println("Solution for task 1 example: ${solveDay10a(inputExample)}") // 7
    println("Solution for task 1 task:    ${solveDay10a(inputTask)}") // 578
    println("Solution for task 2 example: ${solveDay10b(inputExample)}") // 33
    println("Solution for task 2 task:    ${solveDay10b(inputTask)}") // 20709
}

