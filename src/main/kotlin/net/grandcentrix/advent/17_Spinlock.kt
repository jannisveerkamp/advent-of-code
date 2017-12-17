package net.grandcentrix.advent

private const val INSERTIONS = 2017

internal fun circularSpin(steps: Int): Int {
    val buffer = mutableListOf(0)
    var position = 0
    var insertionValue = 1

    repeat(INSERTIONS) { _ ->
        position = (position + 1 + steps) % buffer.size
        buffer.add(position, insertionValue)
        insertionValue++
    }

    return buffer[(position + 1) % buffer.size]
}

internal fun circularSpinValueAfter0(steps: Int, insertions: Int): Int {
    var position = 0
    var value = 0

    for (it in 1..insertions) {
        position = (position + 1 + steps) % it
        if (position == 0) {
            value = it
        }
    }

    return value
}
