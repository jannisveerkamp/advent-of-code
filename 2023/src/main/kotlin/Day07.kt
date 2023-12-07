import kotlin.math.pow

private data class Hand(val source: String, val withJokers: Boolean) {

    val cards = if (withJokers) source.toSet().minus('J') else source.toSet()
    val count: List<Int> = cards.map { card -> source.count { it == card } }.sortedDescending()
    val jokers = if (withJokers) source.count { it == 'J' } else 0

    val handValue: Int = when {
        jokers == 5 || count[0] + jokers == 5 -> 6 // 5 of a kind
        count[0] + jokers == 4 -> 5 // 4 of a kind
        (count[0] == 3 && count[1] + jokers == 2) || (count[0] + jokers == 3 && count[1] == 2) -> 4 // Full house
        count[0] + jokers == 3 -> 3 // 3 of a kind
        count[0] == 2 && count[1] + jokers == 2 -> 2 // 2 pairs
        count[0] + jokers == 2 -> 1 // 2 of a kind
        else -> 0 // High card
    }

    val singleCardsValue: Int = source.reversed().mapIndexed { index, char ->
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
        return (handValue - other.handValue).takeIf { it != 0 } ?: (singleCardsValue - other.singleCardsValue)
    }
}

private fun solveDay07(input: String, withJokers: Boolean): Int = input.split("\n")
    .associate {
        val (hand, rank) = it.split(" ")
        Hand(hand, withJokers) to rank.toInt()
    }
    .toSortedMap { hand1, hand2 -> hand1.compare(hand2) }
    .entries.mapIndexed { index, entry ->
        (index + 1) * entry.value
    }
    .sum()

fun main() {
    val inputExample = readFile("day07_example.txt")
    val inputTask = readFile("day07.txt")

    println("Solution for task 1 example: ${solveDay07(inputExample, false)}") // 6440
    println("Solution for task 1 task:    ${solveDay07(inputTask, false)}") // 253954294
    println("Solution for task 2 example: ${solveDay07(inputExample, true)}") // 5905
    println("Solution for task 2 task:    ${solveDay07(inputTask, true)}") // 254837398
}

