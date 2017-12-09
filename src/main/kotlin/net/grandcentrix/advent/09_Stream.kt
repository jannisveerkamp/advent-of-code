package net.grandcentrix.advent

fun removeGarbage(input: String) = removeGarbageWithSum(input).first

fun garbageCount(input: String) = removeGarbageWithSum(input).second

fun scoreForInput(input: String): Int {
    var currentScore = 0
    var sum = 0
    removeGarbage(input).forEach {
        when(it) {
            '{' -> currentScore++
            '}' -> sum += currentScore--
        }
    }
    return sum
}

private fun removeIgnoredCharacters(input: String): String {
    var wrapper = input
    while (wrapper.contains("!")) {
        val exl = wrapper.indexOf("!")
        wrapper = wrapper.removeRange(exl, exl + 2)
    }
    return wrapper
}

private fun removeGarbageWithSum(input: String): Pair<String, Int> {
    var wrapper = removeIgnoredCharacters(input)

    // Remove and count garbage
    var sum = 0
    while (wrapper.contains("<")) {
        val start = wrapper.indexOf("<")
        val end = wrapper.indexOf(">") + 1
        wrapper = wrapper.removeRange(start, end)
        sum += end - start - 2
    }

    return wrapper to sum
}
