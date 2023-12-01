package common

fun parseNumbers(input: String): List<Int> {
    return input.replace("[^-?0-9]+".toRegex(), " ").trim().split(" ").map { it.toInt() }
}