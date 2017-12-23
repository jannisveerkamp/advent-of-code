package net.grandcentrix.advent

import java.util.LinkedList

abstract class AbstractDuetProgram(input: List<String>) {

    val commands = parseCommands(input)

    val register: MutableMap<String, Long> = createInitialRegister(commands)

    var currentPosition = 0

    abstract fun snd(target: String)

    abstract fun rcv(command: DuetCommand): Boolean

    open fun nextCommand(): Boolean {
        val command = commands[currentPosition]
        val target = command.target
        when (command.command) {
            "snd" -> snd(target)
            "set" -> register[target] = command.getValue(register)
            "add" -> register[target] = register[target]!! + command.getValue(register)
            "mul" -> register[target] = register[target]!! * command.getValue(register)
            "mod" -> register[target] = register[target]!! % command.getValue(register)
            "rcv" -> if (rcv(command)) return true
            "jgz" -> if (register[target] ?: target.toLong() > 0) {
                currentPosition += command.getValue(register).toInt()
                return false
            }
        }
        currentPosition++
        return false
    }
}

class DuetProgram(input: List<String>) : AbstractDuetProgram(input) {

    private var lastSound = 0L

    override fun snd(target: String) {
        lastSound = register[target]!!
    }

    override fun rcv(command: DuetCommand) = register[command.target]!! > 0

    fun duet(): Long {
        while (currentPosition in 0..(commands.size - 1)) {
            if (nextCommand()) {
                return lastSound
            }
        }
        return lastSound
    }
}

class DuetProgramAdvanced(input: List<String>, id: Long) : AbstractDuetProgram(input) {

    init {
        register["p"] = id
    }

    lateinit var otherProgram: DuetProgramAdvanced

    private val messageQueue = LinkedList<Long>()

    var sentValues = 0

    private fun sendValue(target: Long) {
        messageQueue.addLast(target)
    }

    override fun snd(target: String) {
        otherProgram.sendValue(register[target] ?: target.toLong())
        sentValues++
    }

    override fun rcv(command: DuetCommand): Boolean {
        if (messageQueue.isEmpty()) {
            return true
        }
        val item = messageQueue.pop()
        register[command.target] = item
        return false
    }
}

fun duetWithTwoPrograms(input: List<String>): Int {
    val programA = DuetProgramAdvanced(input, 0)
    val programB = DuetProgramAdvanced(input, 1)
    programA.otherProgram = programB
    programB.otherProgram = programA

    while (!programA.nextCommand() || !programB.nextCommand()) { }

    return programB.sentValues
}

data class DuetCommand(val command: String, val target: String, private val value: Long?, private val targetValue: String?) {
    fun getValue(register: MutableMap<String, Long>): Long = value ?: register[targetValue]!!
}

private fun createInitialRegister(steps: List<DuetCommand>): MutableMap<String, Long> {
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
