package net.grandcentrix.advent

private const val FACTOR_GENERATOR_A = 16_807

private const val FACTOR_GENERATOR_B = 48_271

private const val DIVISOR = 2_147_483_647 // This equals Int.MAX_VALUE!

internal fun duelEqualsCount(startValueGeneratorA: Int, startValueGeneratorB: Int, iterations: Int): Int {
    var currentA = startValueGeneratorA.toLong()
    var currentB = startValueGeneratorB.toLong()
    var matches = 0

    repeat(iterations) {
        currentA = (currentA * FACTOR_GENERATOR_A) % DIVISOR
        currentB = (currentB * FACTOR_GENERATOR_B) % DIVISOR

        if (equalsLast16Bits(toBinary(currentA), toBinary(currentB))) {
            matches++
        }
    }

    return matches
}

internal fun equalsLast16Bits(s1: String, s2: String): Boolean {
    return s1.takeLast(16) == s2.takeLast(16)
}

internal fun toBinary(number: Long): String {
    return java.lang.Integer.toBinaryString(number.toInt()).padStart(32, '0')
}