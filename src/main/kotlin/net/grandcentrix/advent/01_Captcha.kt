package net.grandcentrix.advent

fun calculateCaptchaSum(input: String): Int {
    var sum = 0
    var previousChar = input.last()

    input.forEach {
        if (it == previousChar) {
            sum += it.toString().toInt()
        }
        previousChar = it
    }

    return sum
}