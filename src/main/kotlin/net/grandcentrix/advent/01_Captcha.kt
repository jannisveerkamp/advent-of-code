package net.grandcentrix.advent

fun calculateAdvancedCaptchaSum(input: String): Int {
    return calculateCaptchaSum(input, input.length / 2)
}

fun calculateCaptchaSum(input: String, step: Int = 1): Int {
    return (0..(input.length - 1))
        .filter { input[it] == input[(it + step) % input.length] }
        .sumBy { input[it].toString().toInt() }
}
