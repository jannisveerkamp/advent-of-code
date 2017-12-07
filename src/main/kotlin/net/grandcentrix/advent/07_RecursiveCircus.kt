package net.grandcentrix.advent

data class CircusProgram(val name: String, val weight: Int)

infix fun Pair<String, Int>.children(that: List<String>): Pair<CircusProgram, List<String>> = Pair(CircusProgram(first, second), that)

fun parseCircusInput(filename: String): Map<CircusProgram, List<String>> {
    val lines = linesFromResource(filename)
    val map = mutableMapOf<CircusProgram, List<String>>()

    return map
}

fun rootElement(input: Map<CircusProgram, List<String>>): String {
    return "-"
}
