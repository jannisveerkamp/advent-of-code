package net.grandcentrix.advent

fun calculateCaptchaSum(input: String): Int {
    var predecessor = input.last()

    return input
            .filter { predecessor.apply { predecessor = it } == it }
            .map { it.toString().toInt() }
            .sum()
}

fun calculateAdvancedCaptchaSum(input: String): Int {
    val step = input.length / 2

    return (0..(input.length - 1))
        .filter { input[it] == input[(it + step) % input.length] }
        .sumBy { input[it].toString().toInt() }
}
