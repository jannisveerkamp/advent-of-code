import kotlin.math.max
import kotlin.text.lines

private fun parseDay05(input: String): Pair<List<LongRange>, List<Long>> {
    val (ranges, ids) = input.split("\n\n")
    return ranges.lines().map { line ->
        val (start, end) = line.split("-")
        LongRange(start.toLong(), end.toLong())
    } to ids.lines().map { it.toLong() }
}

private fun LongRange.intersects(other: LongRange): Boolean = last >= other.start && other.last >= start

private fun solveDay05a(input: String): Int {
    val (ranges, ids) = parseDay05(input)
    return ids.count { id -> ranges.any { range -> id in range } }
}

private fun solveDay05b(input: String): Long {
    val ranges = parseDay05(input).first.sortedBy { it.first }.toMutableList()

    var changed = true
    while (changed) {
        changed = false
        for (i in ranges.lastIndex downTo 1) {
            val first = ranges[i - 1]
            val second = ranges[i]
            if (first.last >= second.first) {
                ranges[i - 1] = LongRange(first.first, max(first.last, second.last))
                ranges.removeAt(i)
                changed = true
            }
        }
    }

    return ranges.sumOf { it.last - it.first + 1 }
}

fun main() {
    val inputExample = readFile("day05_example.txt")
    val inputTask = readFile("day05.txt")

    println("Solution for task 1 example: ${solveDay05a(inputExample)}") // 3
    println("Solution for task 1 task:    ${solveDay05a(inputTask)}") // 607
    println("Solution for task 2 example: ${solveDay05b(inputExample)}") // 14
    println("Solution for task 2 task:    ${solveDay05b(inputTask)}") // 342433357244012
}
