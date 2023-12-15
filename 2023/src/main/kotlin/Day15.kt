private fun String.customHash(): Int = fold(0) { acc, char -> (acc + char.code) * 17 % 256 }

private fun solveDay15a(input: String): Int = input.split(",").sumOf { step -> step.customHash() }

private sealed interface LenseOperation {
    val value: String

    data class Assign(override val value: String, val number: Int) : LenseOperation
    data class Remove(override val value: String) : LenseOperation
}

private fun solveDay15b(input: String): Int {
    val boxes = Array(256) { mutableListOf<LenseOperation.Assign>() }
    val operations = input.split(",").map { operation ->
        if (operation.contains("=")) {
            val (first, second) = operation.split("=")
            LenseOperation.Assign(first, second.toInt())
        } else {
            LenseOperation.Remove(operation.removeSuffix("-"))
        }
    }

    operations.forEach { operation ->
        val box = boxes[operation.value.customHash()]
        when (operation) {
            is LenseOperation.Remove -> box.removeIf { it.value == operation.value }
            is LenseOperation.Assign -> if (box.any { it.value == operation.value }) {
                box.replaceAll { if (it.value == operation.value) operation else it }
            } else {
                box.add(operation)
            }
        }
    }

    return boxes.withIndex().sumOf { (boxIndex, box) ->
        box.withIndex().sumOf { (lenseIndex, lense) ->
            (boxIndex + 1) * (lenseIndex + 1) * lense.number
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

