package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class KnotHashTest {

    private val simpleTestInput = listOf(0, 1, 2, 3, 4)

    private val simpleTestInputLengths = listOf(3, 4, 1, 5)

    private val testInput = IntArray(256) { it -> it }.toList()

    private val testInputLengths = listOf(129, 154, 49, 198, 200, 133, 97, 254, 41, 6, 2, 1, 255, 0, 191, 108)

    @Test
    fun `Knot hash of simpleTestInput is 12`() {
        assertThat(knotHash(simpleTestInput, simpleTestInputLengths)).isEqualTo(12)
    }

    @Test
    fun `Knot hash of testInput is 0`() {
        assertThat(knotHash(testInput, testInputLengths)).isEqualTo(0)
    }
}