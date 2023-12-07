import kotlin.math.pow

private data class Hand(val value: String) {
    val cards = value.toSet()
    val hasFiveOfAKind = cards.size == 1
    val hasFourOfAKind = cards.any { card -> value.count { it == card } == 4 }
    val hasFullHouse =
        cards.size == 2 && (value.count { it == cards.first() } == 2 || value.count { it == cards.first() } == 3)
    val hasThreeOfAKind = cards.any { card -> value.count { it == card } == 3 }
    val hasTwoPairs = cards.count { card -> value.count { it == card } == 2 } == 2
    val hasOnePair = cards.size < 5

    val singleCardValue: Int = value.reversed().mapIndexed { index, char ->
        20.0.pow(index).toInt() * when {
            char.isDigit() -> char.digitToInt()
            char == 'T' -> 10
            char == 'J' -> 11
            char == 'Q' -> 12
            char == 'K' -> 13
            char == 'A' -> 14
            else -> error("Unknown card: $char")
        }
    }.sum()

    val cardValue: Int = when {
        hasFiveOfAKind -> 6
        hasFourOfAKind -> 5
        hasFullHouse -> 4
        hasThreeOfAKind -> 3
        hasTwoPairs -> 2
        hasOnePair -> 1
        else -> 0
    }

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
        Hand(hand) to rank.toInt()
    }
    val sorted = deck.toSortedMap(Comparator { hand1, hand2 ->
        hand1.compare(hand2)
    })
    var sum = 0
    sorted.onEachIndexed { index, entry ->
        sum += (index + 1) * entry.value
    }
    return sum
}

private fun solveDay07b(input: String): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day07_example.txt")
    val inputTask = readFile("day07.txt")

    println("Solution for task 1 example: ${solveDay07a(inputExample)}") // 6440
    println("Solution for task 1 task:    ${solveDay07a(inputTask)}") // 253954294
    println("Solution for task 2 example: ${solveDay07b(inputExample)}") // 5905
    println("Solution for task 2 task:    ${solveDay07b(inputTask)}") // ???
}

