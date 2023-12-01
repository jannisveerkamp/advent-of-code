// A for Rock, B for Paper, and C for Scissors
// X for Rock, Y for Paper, and Z for Scissors
enum class RPS {
    ROCK,
    PAPER,
    SCISSORS;

    // 1 for Rock, 2 for Paper, and 3 for Scissors
    // 0 if you lost, 3 if the round was a draw, and 6 if you won
    fun play(other: RPS): Int = when (this) {
        ROCK -> 1 + when (other) {
            ROCK -> 3
            PAPER -> 0
            SCISSORS -> 6
        }
        PAPER -> 2 + when (other) {
            ROCK -> 6
            PAPER -> 3
            SCISSORS -> 0
        }
        SCISSORS -> 3 + when (other) {
            ROCK -> 0
            PAPER -> 6
            SCISSORS -> 3
        }
    }

    // 1 for Rock, 2 for Paper, and 3 for Scissors
    // 0 if you lost, 3 if the round was a draw, and 6 if you won
    // X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
    fun playUnknown(other: RPS): Int = when (this) {
        ROCK -> when (other) {
            ROCK -> 0 + 3
            PAPER -> 3 + 1
            SCISSORS -> 6 + 2
        }
        PAPER -> when (other) {
            ROCK -> 0 + 1
            PAPER -> 3 + 2
            SCISSORS -> 6 + 3
        }
        SCISSORS -> when (other) {
            ROCK -> 0 + 2
            PAPER -> 3 + 3
            SCISSORS -> 6 + 1
        }
    }

    companion object {
        fun fromChar(char: String): RPS = when (char) {
            "A", "X" -> ROCK
            "B", "Y" -> PAPER
            "C", "Z" -> SCISSORS
            else -> error("Unknown move: $char")
        }
    }
}

fun solveDay02a(input: String): Int {
    return input.split("\n").sumOf { round ->
        val (elf, me) = round.split(" ").map { RPS.fromChar(it) }
        me.play(elf)
    }
}

fun solveDay02b(input: String): Int {
    return input.split("\n").sumOf { round ->
        val (elf, me) = round.split(" ").map { RPS.fromChar(it) }
        elf.playUnknown(me)
    }
}

fun main() {
    val inputExample = readFile("day02_example.txt")
    val inputTask = readFile("day02.txt")

    println("Solution for task 1 example: ${solveDay02a(inputExample)}") // 15
    println("Solution for task 1 task:    ${solveDay02a(inputTask)}") // 10816
    println("Solution for task 2 example: ${solveDay02b(inputExample)}") // 12
    println("Solution for task 2 task:    ${solveDay02b(inputTask)}") // 11657
}

