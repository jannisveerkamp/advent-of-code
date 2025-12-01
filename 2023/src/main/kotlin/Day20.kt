enum class HighLow(val high: Boolean) {
    HIGH(true),
    LOW(false)
}

sealed interface PulseModule {
    val src: String
    val dst: List<String>
    fun pulse(fromModule: PulseModule, highLow: HighLow): List<Pair<String, HighLow>>?

    data class Broadcaster(override val dst: List<String>) : PulseModule {
        override val src: String = "broadcaster"
        override fun pulse(fromModule: PulseModule, highLow: HighLow): List<Pair<String, HighLow>> =
            dst.map { it to highLow }
    }

    data class FlipFlop(
        override val src: String,
        override val dst: List<String>,
        var on: Boolean = false
    ) : PulseModule {
        override fun pulse(fromModule: PulseModule, highLow: HighLow): List<Pair<String, HighLow>>? {
            return when (highLow) {
                HighLow.HIGH -> null
                HighLow.LOW -> {
                    val wasOn = on
                    on = !on
                    dst.map { it to if (wasOn) HighLow.LOW else HighLow.HIGH }
                }
            }
        }
    }

    class Conjunction(
        override val src: String,
        override val dst: List<String>,
        var storage: MutableMap<PulseModule, HighLow> = mutableMapOf()
    ) : PulseModule {

        override fun pulse(fromModule: PulseModule, highLow: HighLow): List<Pair<String, HighLow>> {
            storage[fromModule] = highLow
            return dst.map { it to if (storage.all { it.value == HighLow.HIGH }) HighLow.LOW else HighLow.HIGH }.apply {
                println("Send: $this for storage $storage")
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Conjunction

            if (src != other.src) return false
            return dst == other.dst
        }

        override fun hashCode(): Int {
            var result = src.hashCode()
            result = 31 * result + dst.hashCode()
            return result
        }
    }

    companion object {
        fun fromString(input: String): PulseModule {
            val (first, second) = input.split(" -> ")
            return when {
                input.startsWith("broadcaster") -> Broadcaster(second.split(", "))
                input.startsWith("%") -> FlipFlop(first.drop(1), second.split(", "))
                input.startsWith("&") -> Conjunction(first.drop(1), second.split(", "))
                else -> error("Unknown module: $input")
            }
        }
    }
}

private fun solveDay20a(input: String): Int {
    val modules = input.split("\n").map { line ->
        PulseModule.fromString(line)
    }
    modules.filterIsInstance<PulseModule.Conjunction>().forEach { conjunction ->
        conjunction.storage.putAll(modules.filter { it.dst.contains(conjunction.src) }.associateWith { HighLow.LOW })
    }

    var lowCounter = 0
    var highCounter = 0

    repeat(1) { buttonPushCounter ->
        val currentPulses = mutableListOf(Triple(modules.first { it is PulseModule.Broadcaster }, "broadcaster", HighLow.LOW))
        while (currentPulses.isNotEmpty()) {
            val currentPulse = currentPulses.removeFirst()
            if (currentPulse.third == HighLow.HIGH) highCounter++ else lowCounter++
            println(currentPulse)

            val nextPulses = modules.first { it == currentPulse.first }.pulse(currentPulse.first, currentPulse.third)

            nextPulses?.forEach { nextPulse ->
                currentPulses.add(Triple(modules.first { it.src in nextPulse.first }, nextPulse.first, nextPulse.second))
            }

//            currentPulses.addAll(nextPulses)
//            modules.filter { it.src in next.dst }.forEach { module ->
//                nextPulse.forEach {
//                    newPulses.add(Triple(module, it.first, it.second))
//                }
//            }
//            currentPulses.add(newPulses.toList())
        }
//        println()
    }
    return lowCounter * highCounter
}

private fun solveDay20b(input: String): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day20_example_1.txt")
    val inputExample2 = readFile("day20_example_2.txt")
    val inputTask = readFile("day20.txt")

    println("Solution for task 1 example: ${solveDay20a(inputExample)}") // 32000000
//    println("Solution for task 1 example: ${solveDay20a(inputExample2)}") // 11687500
//    println("Solution for task 1 task:    ${solveDay20a(inputTask)}") // ???
//    println("Solution for task 2 example: ${solveDay20b(inputExample2)}") // ???
//    println("Solution for task 2 task:    ${solveDay20b(inputTask)}") // ???
}

