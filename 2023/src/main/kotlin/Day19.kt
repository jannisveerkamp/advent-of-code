private val WORKFLOW_REGEX = """(\w*)\{(.*)}""".toRegex()
private val WORKFLOW_INNER_REGEX = """(\w*)[<,>](\d*):(\w*)""".toRegex()
private val PART_REGEX = """\{x=(\d+),m=(\d+),a=(\d+),s=(\d+)}""".toRegex()

private enum class WorkflowComparator(val char: Char) {
    GREATER('>'),
    SMALLER('<');

    fun match(value: Int, otherValue: Int): Boolean = when (this) {
        GREATER -> value > otherValue
        SMALLER -> value < otherValue
    }
}

private data class WorkflowOperation(
    val comparer: Char,
    val condition: WorkflowComparator,
    val value: Int,
    val target: String
)

private data class Workflow(val name: String, val operations: List<WorkflowOperation>, val fallback: String) {

    fun match(x: Int, m: Int, a: Int, s: Int): String {
        val matchOperation = operations.firstOrNull { operation ->
            when (operation.comparer) {
                'x' -> operation.condition.match(x, operation.value)
                'm' -> operation.condition.match(m, operation.value)
                'a' -> operation.condition.match(a, operation.value)
                's' -> operation.condition.match(s, operation.value)
                else -> error("Unknown comparer: ${operation.comparer}")
            }
        }
        return matchOperation?.target ?: fallback
    }

    companion object {
        fun fromString(input: String): Workflow {
            val match = WORKFLOW_REGEX.find(input)!!
            val name = match.groups[1]!!.value
            val conditions = match.groups[2]!!.value.split(",")
            val operations = conditions.dropLast(1).map { operation ->
                val innerMatch = WORKFLOW_INNER_REGEX.find(operation)!!
                WorkflowOperation(
                    innerMatch.groupValues[1].first(),
                    if (operation.contains(">")) WorkflowComparator.GREATER else WorkflowComparator.SMALLER,
                    innerMatch.groupValues[2].toInt(),
                    innerMatch.groupValues[3]
                )
            }
            return Workflow(name, operations, conditions.last())
        }
    }
}

private data class PartsToSort(val x: Int, val m: Int, val a: Int, val s: Int) {

    val sum: Int = x + m + a + s

    companion object {
        fun fromString(input: String): PartsToSort {
            val match = PART_REGEX.find(input)!!
            return PartsToSort(
                match.groups[1]!!.value.toInt(),
                match.groups[2]!!.value.toInt(),
                match.groups[3]!!.value.toInt(),
                match.groups[4]!!.value.toInt()
            )
        }
    }
}

private fun solveDay19a(input: String): Int {
    val (operationInput, partInput) = input.split("\n\n")
    val operations = operationInput.split("\n").map { line -> Workflow.fromString(line) }
    val parts = partInput.split("\n").map { line -> PartsToSort.fromString(line) }

    return parts.sumOf { part ->
        var currentOperation = operations.first { it.name == "in" }
        while (true) {
            when (val next = currentOperation.match(part.x, part.m, part.a, part.s)) {
                "A" -> return@sumOf part.sum
                "R" -> return@sumOf 0
                else -> currentOperation = operations.first { it.name == next }
            }
        }
        0
    }
}

private fun solveDay19b(input: String): Int {
    return 0
}


fun main() {
    val inputExample = readFile("day19_example.txt")
    val inputTask = readFile("day19.txt")

    println("Solution for task 1 example: ${solveDay19a(inputExample)}") // 19114
    println("Solution for task 1 task:    ${solveDay19a(inputTask)}") // 456651
//    println("Solution for task 2 example: ${solveDay19b(inputExample)}") // ???
//    println("Solution for task 2 task:    ${solveDay19b(inputTask)}") // ???
}

