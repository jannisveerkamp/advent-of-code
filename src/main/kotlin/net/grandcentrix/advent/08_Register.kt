package net.grandcentrix.advent

class Instruction(val target: String,
                  private val operation: (Int) -> Int,
                  private val conditionTarget: String,
                  private val condition: (Int) -> Boolean) {

    fun performInstruction(register: MutableMap<String, Int>) {
        if (condition(register[conditionTarget]!!)) {
            register[target] = operation(register[target]!!)
        }
    }
}

fun largestValue(input: List<String>): Int {
    val instructions = input.map { parseLine(it) }
    val register = instructions
            .map { it.target }
            .associateBy( {it}, {0} )
            .toMutableMap()

    instructions.forEach {
        it.performInstruction(register)
    }

    return register.maxBy { it.value }!!.value
}

fun largestTempValue(input: List<String>): Int {
    return -1
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