import kotlin.math.pow

private data class Hand(val value: String, val withJokers: Boolean) {
    val cards = value.toSet()
    val countOfCards: List<Int> = if (withJokers) {
        cards.minus('J').map { card -> value.count { it == card } }.sorted().reversed()
    } else {
        cards.map { card -> value.count { it == card } }.sorted().reversed()
    }
    val numberOfJokers = if (withJokers) value.count { it == 'J' } else 0

    val cardValue: Int = when {
        numberOfJokers == 5 || countOfCards[0] + numberOfJokers == 5 -> 6
        countOfCards[0] + numberOfJokers == 4 -> 5
        (countOfCards[0] + numberOfJokers == 3 && countOfCards[1] == 2) ||
                (countOfCards[0] == 3 && countOfCards[1] + numberOfJokers == 2) -> 4

        countOfCards[0] + numberOfJokers == 3 -> 3
        countOfCards[0] == 2 && countOfCards[1] + numberOfJokers == 2 -> 2
        countOfCards[0] + numberOfJokers == 2 -> 1
        else -> 0
    }

    val singleCardValue: Int = value.reversed().mapIndexed { index, char ->
        20.0.pow(index).toInt() * when {
            char.isDigit() -> char.digitToInt()
            char == 'T' -> 10
            char == 'J' -> if (withJokers) 1 else 11
            char == 'Q' -> 12
            char == 'K' -> 13
            char == 'A' -> 14
            else -> error("Unknown card: $char")
        }
    }.sum()

    fun compare(other: Hand): Int {
        val compare = cardValue - other.cardValue
        return if (compare == 0) {
            singleCardValue - other.singleCardValue
        } else {
            compare
        }
    }
}

private fun solveDay07a(input: String): Int {
    val deck = input.split("\n").associate {
        val (hand, rank) = it.split(" ")
        Hand(hand, false) to rank.toInt()
    }
    val sorted = deck.toSortedMap { hand1, hand2 -> hand1.compare(hand2) }
    var sum = 0
    sorted.onEachIndexed { index, entry ->
        sum += (index + 1) * entry.value
    }
    return sum
}

private fun solveDay07b(input: String): Int {
    val deck = input.split("\n").associate {
        val (hand, rank) = it.split(" ")
        Hand(hand, true) to rank.toInt()
    }
    val sorted = deck.toSortedMap { hand1, hand2 -> hand1.compare(hand2) }
    var sum = 0
    sorted.onEachIndexed { index, entry ->
        sum += (index + 1) * entry.value
    }
    return sum
}

fun main() {
    val inputExample = readFile("day07_example.txt")
    val inputTask = readFile("day07.txt")

    println("Solution for task 1 example: ${solveDay07a(inputExample)}") // 6440
    println("Solution for task 1 task:    ${solveDay07a(inputTask)}") // 253954294
    println("Solution for task 2 example: ${solveDay07b(inputExample)}") // 5905
    println("Solution for task 2 task:    ${solveDay07b(inputTask)}") // 254837398
}

