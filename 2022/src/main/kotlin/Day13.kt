import kotlinx.serialization.json.*

private val json = Json {
    isLenient = true
}

private sealed interface Element
private data class ArrayElement(val children: List<Element>) : Element
private data class Primitive(val value: Int) : Element

private fun parsePackets(packet: JsonElement): Element = when (packet) {
    is JsonArray -> ArrayElement(packet.map { parsePackets(it) })
    is JsonPrimitive -> Primitive(packet.content.toInt())
    else -> error("unknown type: $packet")
}

private fun compare(first: Element, second: Element): Int {
    return when {
        first is Primitive && second is Primitive -> second.value.compareTo(first.value)
        first is ArrayElement && second is ArrayElement -> {
            first.children.zip(second.children).forEach {
                val compare = compare(it.first, it.second)
                if (compare != 0) {
                    return compare
                }
            }
            if (first.children.size == second.children.size) {
                0
            } else if (first.children.size > second.children.size) {
                -1
            } else {
                1
            }
        }
        first is Primitive && second is ArrayElement -> compare(ArrayElement(listOf(first)), second)
        first is ArrayElement && second is Primitive -> compare(first, ArrayElement(listOf(second)))
        else -> error("should never happen")
    }
}

fun solveDay13a(input: String): Int {
    return input.split("\n\n").withIndex().sumOf { (index, packetPair) ->
        val (firstPacketRaw, secondPacketRaw) = packetPair.split("\n")
        val firstPacket = parsePackets(json.parseToJsonElement(firstPacketRaw))
        val secondPacket = parsePackets(json.parseToJsonElement(secondPacketRaw))
        if (compare(firstPacket, secondPacket) != -1) {
            index + 1
        } else {
            0
        }
    }
}

fun solveDay13b(input: String): Int {
    val packetPairs = input.split("\n\n")
    val allPacketPairs = mutableListOf<Element>()

    packetPairs.forEach { packetPair ->
        val (firstPacketRaw, secondPacketRaw) = packetPair.split("\n")
        allPacketPairs.add(parsePackets(json.parseToJsonElement(firstPacketRaw)))
        allPacketPairs.add(parsePackets(json.parseToJsonElement(secondPacketRaw)))
    }

    val dividerPacket1 = ArrayElement(listOf(ArrayElement(listOf(Primitive(2)))))
    allPacketPairs.add(dividerPacket1)
    val dividerPacket2 = ArrayElement(listOf(ArrayElement(listOf(Primitive(6)))))
    allPacketPairs.add(dividerPacket2)

    allPacketPairs.sortWith(Comparator { o1: Element, o2: Element ->
        return@Comparator -compare(o1, o2)
    })

    return (allPacketPairs.indexOf(dividerPacket1) + 1) * (allPacketPairs.indexOf(dividerPacket2) + 1)
}

fun main() {
    val inputExample = readFile("day13_example.txt")
    val inputTask = readFile("day13.txt")

    println("Solution for task 1 example: ${solveDay13a(inputExample)}") // 13
    println("Solution for task 1 task:    ${solveDay13a(inputTask)}")    // 5390
    println("Solution for task 2 example: ${solveDay13b(inputExample)}") // 140
    println("Solution for task 2 task:    ${solveDay13b(inputTask)}")    // 19261
}

