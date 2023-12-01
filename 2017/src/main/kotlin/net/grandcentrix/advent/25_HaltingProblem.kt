package net.grandcentrix.advent

private class State(val value0: Int, val direction0: Int, val nextState0: String,
                    val value1: Int, val direction1: Int, val nextState1: String)

internal fun diagnosticChecksum(input: List<String>): Int {
    var currentState = initialState(input[0])
    val steps = steps(input[1])
    val instructions = parseInstructions(input.subList(3, input.size))

    var currentPosition = 0
    val memory = mutableMapOf<Int, Int>()

    repeat(steps) {
        val state = instructions[currentState]!!
        if (memory.getOrPut(currentPosition, { 0 }) == 0) {
            memory[currentPosition] = state.value0
            currentPosition += state.direction0
            currentState = state.nextState0
        } else {
            memory[currentPosition] = state.value1
            currentPosition += state.direction1
            currentState = state.nextState1
        }
    }

    return memory.values.sum()
}

private fun steps(input: String) = input.substringAfter("after ").substringBefore(" ").toInt()

private fun initialState(input: String) = input.dropLast(1).last().toString()

private fun parseInstructions(input: List<String>): Map<String, State> {
    return input.chunked(10).associate {
        val name = it[0].dropLast(1).last().toString()
        val value0 = if (it[2].contains("1")) 1 else 0
        val direction0 = if (it[3].contains("right")) 1 else -1
        val nextState0 = it[4].dropLast(1).last().toString()
        val value1 = if (it[6].contains("1")) 1 else 0
        val direction1 = if (it[7].contains("right")) 1 else -1
        val nextState1 = it[8].dropLast(1).last().toString()
        name to State(value0, direction0, nextState0, value1, direction1, nextState1)
    }
}
