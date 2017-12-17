package net.grandcentrix.advent

private const val INSERTIONS = 2017

internal fun circularSpin(steps: Int): Int {
    val buffer = mutableListOf(0)
    var currentPosition = 0
    var currentInsertionValue = 1

    repeat(INSERTIONS) { _ ->
        currentPosition = (currentPosition + 1 + steps) % buffer.size
        buffer.add(currentPosition, currentInsertionValue)
        currentInsertionValue++
    }

    return buffer[(currentPosition + 1) % buffer.size]
}
