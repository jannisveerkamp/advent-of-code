package net.grandcentrix.advent

private const val FACTOR_GENERATOR_A = 16_807

private const val FACTOR_GENERATOR_B = 48_271

private const val DIVISOR = 2_147_483_647 // This equals Int.MAX_VALUE!

internal fun duelEqualsCount(startA: Int, startB: Int, iterations: Int,
                             ruleA: (Long) -> Boolean = { false },
                             ruleB: (Long) -> Boolean = { false }): Int {
    var currentA = startA.toLong()
    var currentB = startB.toLong()

    return generateSequence(0, Int::inc).take(iterations).count {
        do {
            currentA = (currentA * FACTOR_GENERATOR_A) % DIVISOR
        } while (ruleA(currentA))
        do {
            currentB = (currentB * FACTOR_GENERATOR_B) % DIVISOR
        } while (ruleB(currentB))

        equalsLast16Bits(toBinary(currentA), toBinary(currentB))
    }
}

internal fun duelEqualsCountDropValues(startValueGeneratorA: Int, startValueGeneratorB: Int, iterations: Int): Int {
    return duelEqualsCount(startValueGeneratorA, startValueGeneratorB, iterations,
            { it -> it % 4 != 0L }, { it -> it % 8 != 0L })
}

internal fun equalsLast16Bits(s1: String, s2: String) = s1.takeLast(16) == s2.takeLast(16)

internal fun toBinary(number: Long) = java.lang.Integer.toBinaryString(number.toInt()).padStart(32, '0')
