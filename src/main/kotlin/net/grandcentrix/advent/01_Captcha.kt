package net.grandcentrix.advent

fun calculateCaptchaSum(input: String): Int {
    var predecessor = input.last()

    return input
            .filter { predecessor.apply { predecessor = it } == it }
            .map { it.toString().toInt() }
            .sum()
}
