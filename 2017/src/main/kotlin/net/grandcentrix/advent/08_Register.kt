package net.grandcentrix.advent

class Instruction(val operationTarget: String,
                  private val operation: (Int) -> Int,
                  private val conditionTarget: String,
                  private val condition: (Int) -> Boolean) {

    fun performInstruction(register: MutableMap<String, Int>) {
        if (condition(register[conditionTarget]!!)) {
            register[operationTarget] = operation(register[operationTarget]!!)
        }
    }
}

fun largestValue(input: List<String>) = performInstructions(input).first.maxBy { it.value }!!.value

fun largestTempValue(input: List<String>) = performInstructions(input).second

fun performInstructions(input: List<String>): Pair<Map<String, Int>, Int> {
    val instructions = input.map { parseLine(it) }
    val register = instructions
            .map { it.operationTarget }
            .associateBy( {it}, {0} )
            .toMutableMap()

    val highestValue = instructions.map {
        it.performInstruction(register)
        register.maxBy { it.value }!!.value
    }.max()
    return register to highestValue!!
}

fun parseLine(line: String): Instruction {
    val inputs = line.split(" ")

    val operationTarget = inputs[0]
    val operationOperator = inputs[1]
    val operationValue = inputs[2].toInt()
    // We don't need to parse the "if" on input[3]
    val conditionTarget = inputs[4]
    val conditionOperator = inputs[5]
    val conditionValue = inputs[6].toInt()

    return Instruction(operationTarget, {it ->
        if (operationOperator == "inc") {
            it + operationValue
        } else {
            it - operationValue
        }
    }, conditionTarget, {it ->
        when (conditionOperator) {
            "==" -> it == conditionValue
            "!=" -> it != conditionValue
            "<" -> it < conditionValue
            "<=" -> it <= conditionValue
            ">" -> it > conditionValue
            ">=" -> it >= conditionValue
            else -> false
        }
    })
}