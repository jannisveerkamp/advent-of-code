import common.Point3
import common.zipWithItselfAllUnique

private fun parseDay08(input: String): List<Point3> = input.split("\n").map { line ->
    val (x, y, z) = line.split(",").map { it.toInt() }
    Point3(x, y, z)
}

private fun mergeCircuits(circuit: MutableSet<MutableSet<Point3>>, first: Point3, second: Point3): Boolean {
    if (circuit.any { first in it && second in it }) {
        return false
    }
    val circuitFirst = circuit.first { first in it }
    val circuitSecond = circuit.first { second in it }

    circuit.removeIf { it == circuitFirst }
    circuitSecond.addAll(circuitFirst)
    return true
}

private fun solveDay08a(input: String, iterations: Int): Int {
    val points = parseDay08(input)
    val circuit = mutableSetOf<MutableSet<Point3>>()
    circuit.addAll(points.map { mutableSetOf(it) })

    points.zipWithItselfAllUnique()
        .sortedBy { it.first.euclideanSquared(it.second) }
        .take(iterations)
        .forEach { currentPoint ->
            mergeCircuits(circuit, currentPoint.first, currentPoint.second)
        }
    return circuit.sortedByDescending { it.size }.take(3).map { it.size }.reduce { x, y -> x * y }
}


private fun solveDay08b(input: String): Int {
    val points = parseDay08(input)
    val circuit = mutableSetOf<MutableSet<Point3>>()
    circuit.addAll(points.map { mutableSetOf(it) })

    points.zipWithItselfAllUnique()
        .sortedBy { it.first.euclideanSquared(it.second) }
        .forEach { currentPoint ->
            mergeCircuits(circuit, currentPoint.first, currentPoint.second)
            if (circuit.size == 1) {
                return currentPoint.first.x * currentPoint.second.x
            }
        }
    error("Should not happen.")
}

fun main() {
    val inputExample = readFile("day08_example.txt")
    val inputTask = readFile("day08.txt")

    println("Solution for task 1 example: ${solveDay08a(inputExample, 10)}") // 40
    println("Solution for task 1 task:    ${solveDay08a(inputTask, 1000)}") // 68112
    println("Solution for task 1 example: ${solveDay08b(inputExample)}") // 25272
    println("Solution for task 1 task:    ${solveDay08b(inputTask)}") // 44543856
}

