private sealed interface Operation {
    fun calc(old: Long): Long
    data class Add(val factor: Long) : Operation {
        override fun calc(old: Long) = old + factor
    }

    data class Multiply(val factor: Long) : Operation {
        override fun calc(old: Long) = old * factor
    }

    object Power : Operation {
        override fun calc(old: Long) = old * old
    }
}

private data class TestDivisible(val mod: Long, val ifTrue: Int, val ifFalse: Int) {
    fun calcMod(oldItem: Long) = if (oldItem % mod == 0L) ifTrue else ifFalse
}

private data class Monkey(
    val number: Int,
    val items: MutableList<Long>,
    val operation: Operation,
    val testDivisible: TestDivisible,
    var inspectedItems: Long = 0
)

private fun parseMonkeys(input: String): List<Monkey> = input.split("\n\n").map { monkey ->
    val lines = monkey.split("\n")
    val number = lines[0].removePrefix("Monkey ").dropLast(1).toInt()
    val startingItems = lines[1].removePrefix("  Starting items: ").split(", ").map { it.toLong() }
    val operation = lines[2].removePrefix("  Operation: new = ")
    val parsedOperation = when {
        operation == "old * old" -> Operation.Power
        operation.contains("+") -> Operation.Add(operation.split(" + ")[1].toLong())
        operation.contains("*") -> Operation.Multiply(operation.split(" * ")[1].toLong())
        else -> error("Unknown operation: $operation")
    }
    val divisibleBy = lines[3].removePrefix("  Test: divisible by ").toLong()
    val ifTrueMonkey = lines[4].removePrefix("    If true: throw to monkey ").toInt()
    val ifFalseMonkey = lines[5].removePrefix("    If false: throw to monkey ").toInt()

    Monkey(
        number = number,
        items = startingItems.toMutableList(),
        operation = parsedOperation,
        testDivisible = TestDivisible(divisibleBy, ifTrueMonkey, ifFalseMonkey)
    )
}

fun solveDay11(input: String, repeat: Int, isA: Boolean): Long {
    val monkeys = parseMonkeys(input)
    val mod = monkeys.map { it.testDivisible.mod }.fold(1, Long::times)

    repeat(repeat) {
        monkeys.forEach { monkey ->
            while (monkey.items.isNotEmpty()) {
                val item = monkey.items.removeFirst()
                val observedItem = if (isA) {
                    monkey.operation.calc(item) / 3
                } else {
                    monkey.operation.calc(item) % mod
                }
                val newMonkey = monkey.testDivisible.calcMod(observedItem)
                monkeys[newMonkey].items.add(observedItem)
                monkey.inspectedItems += 1
            }
        }
    }
    return monkeys.map { it.inspectedItems }.sortedBy { it }.takeLast(2).fold(1, Long::times)
}

fun main() {
    val inputExample = readFile("day11_example.txt")
    val inputTask = readFile("day11.txt")

    println("Solution for task 1 example: ${solveDay11(inputExample, 20, true)}") // 10605
    println("Solution for task 1 task:    ${solveDay11(inputTask, 20, true)}") // 56120
    println("Solution for task 2 example: ${solveDay11(inputExample, 10000, false)}") // 2713310158
    println("Solution for task 2 task:    ${solveDay11(inputTask, 10000, false)}") // 24389045529
}

