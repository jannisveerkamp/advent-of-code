package net.grandcentrix.advent

class DuetProgramAgain(input: List<String>) : AbstractDuetProgram(input) {

    var numberOfMul = 0

    override fun snd(target: String) {}

    override fun rcv(command: DuetCommand) = false

    override fun nextCommand(): Boolean {
        val command = commands[currentPosition]
        val target = command.target
        when (command.command) {
            "set" -> register[target] = command.getValue(register)
            "sub" -> register[target] = register[target]!! - command.getValue(register)
            "mul" -> {
                register[target] = register[target]!! * command.getValue(register)
                numberOfMul++
            }
            "jnz" -> if (register[target] ?: target.toLong() != 0L) {
                currentPosition += command.getValue(register).toInt()
                return false
            }
        }
        currentPosition++
        return false
    }

    fun duet(): Int {
        while (currentPosition in 0 until commands.size) {
            nextCommand()
        }
        return numberOfMul
    }
}
