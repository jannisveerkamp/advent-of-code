package net.grandcentrix.advent

data class DuetCommand(val command: String, val target: String, val value: Long?, val targetValue: String?) {
    fun getValue(register: MutableMap<String, Long>): Long = value ?: register[targetValue]!!
}

fun duet(input: List<String>): Long {
    val commands = parseCommands(input)
    val register: MutableMap<String, Long> = createInitialRegister(commands)
    var currentPosition = 0
    var lastSound = 0L

    loop@ while (currentPosition in 0..(commands.size - 1)) {
        val command = commands[currentPosition]
        val target = command.target
        when (command.command) {
            "snd" -> lastSound = register[target]!!
            "set" -> register[target] = command.getValue(register)
            "add" -> register[target] = register[target]!! + command.getValue(register)
            "mul" -> register[target] = register[target]!! * command.getValue(register)
            "mod" -> register[target] = register[target]!! % command.getValue(register)
            "rcv" -> if (register[target]!! > 0) return lastSound
            "jgz" -> if (register[target]!! > 0) {
                currentPosition += command.getValue(register).toInt()
                continue@loop
            }
        }
        currentPosition++
    }

    return lastSound
}

fun duetWithoutSound(input: List<String>): Int {
    return -1
}

fun createInitialRegister(steps: List<DuetCommand>): MutableMap<String, Long> {
    return steps.filter { it.target.toIntOrNull() == null }.associate { it.target to 0L }.toMutableMap()
}

private fun parseCommands(steps: List<String>): List<DuetCommand> {
    return steps.map {
        val command = it.substring(0, 3)
        val target = it.substring(4..4)
        val value = if (it.length >= 6) it.substring(6).toLongOrNull() else null
        val targetValue = if (value == null && it.length >= 6) it[6].toString() else null
        DuetCommand(command, target, value, targetValue)
    }
}
