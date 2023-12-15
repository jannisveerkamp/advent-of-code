private fun String.customHash(): Int {
    var sum = 0
    forEach { char ->
        sum = (sum + char.code) * 17 % 256
    }
    return sum
}

private fun solveDay15a(input: String): Int = input.split(",").sumOf { step -> step.customHash() }

private sealed interface LenseOperation {
    val value: String

    data class Assign(override val value: String, val number: Int) : LenseOperation
    data class Dash(override val value: String) : LenseOperation
}

private fun solveDay15b(input: String): Int {
    val boxes = Array(256) { mutableListOf<LenseOperation.Assign>() }
    val operations = input.split(",").map { operation ->
        if (operation.contains("=")) {
            val (first, second) = operation.split("=")
            LenseOperation.Assign(first, second.toInt())
        } else {
            LenseOperation.Dash(operation.removeSuffix("-"))
        }
    }

    operations.forEach { operation ->
        val hash = operation.value.customHash()
        val box = boxes[hash]
        when (operation) {
            is LenseOperation.Assign -> {
                if (box.find { it.value == operation.value } != null) {
                    box.replaceAll { if (it.value == operation.value) operation else it }
                } else {
                    box.add(operation)
                }
            }

            is LenseOperation.Dash -> {
                box.removeIf { it.value == operation.value }
            }
        }
    }

    return boxes.withIndex().sumOf { (indexBox, box) ->
        box.withIndex().sumOf { (indexLense, lense) ->
            (indexBox + 1) * (indexLense + 1) * lense.number
        }
    }
}

fun main() {
    val inputExample = readFile("day15_example.txt")
    val inputTask = readFile("day15.txt")

    println("Solution for task 1 example: ${solveDay15a(inputExample)}") // 1320
    println("Solution for task 1 task:    ${solveDay15a(inputTask)}") // 510013
    println("Solution for task 2 example: ${solveDay15b(inputExample)}") // 145
    println("Solution for task 2 task:    ${solveDay15b(inputTask)}") // 268497
}

