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
    fun `Knot hash of testInput is 19591`() {
        assertThat(knotHash(testInput, testInputLengths)).isEqualTo(19591)
    }

    @Test
    fun `"1,2,3" to Ascii is 49, 44, 50, 44, 51`() {
        assertThat(toAscii("1,2,3")).isEqualTo(listOf(49, 44, 50, 44, 51))
    }

    @Test
    fun `"1,2,3" to Ascii with appended Sequence is 4, 44, 50, 44, 51, 17, 31, 73, 47, 23`() {
        assertThat(appendSequence(toAscii("1,2,3"))).isEqualTo(listOf(49, 44, 50, 44, 51, 17, 31, 73, 47, 23))
    }

    @Test
    fun `xor16 of 65 ^ 27 ^ 9 ^ 1 ^ 4 ^ 3 ^ 40 ^ 50 ^ 91 ^ 7 ^ 6 ^ 0 ^ 2 ^ 5 ^ 68 ^ 22 is 64`() {
        assertThat(xor16(listOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22))).isEqualTo(listOf(64))
    }

    @Test
    fun `hexadecimal representation of (64, 7, 255) is "4007ff"`() {
        assertThat(toHexadecimal(listOf(64, 7, 255))).isEqualTo("4007ff")
    }

    @Test
    fun `Dense hash of "" is a2582a3a0e66e6e86e3812dcb672a272`() {
        assertThat(denseHash("", testInput, 64)).isEqualTo("a2582a3a0e66e6e86e3812dcb672a272")
    }

    @Test
    fun `Dense hash of "AoC 2017" is 33efeb34ea91902bb2f59c9920caa6cd`() {
        assertThat(denseHash("AoC 2017", testInput, 64)).isEqualTo("33efeb34ea91902bb2f59c9920caa6cd")
    }

    @Test
    fun `Dense hash of "1,2,3" is 3efbe78a8d82f29979031a4aa0b16a9d`() {
        assertThat(denseHash("1,2,3", testInput, 64)).isEqualTo("3efbe78a8d82f29979031a4aa0b16a9d")
    }

    @Test
    fun `Dense hash of "1,2,4" is 63960835bcdc130f0b66d7ff4f6a5a8e`() {
        assertThat(denseHash("1,2,4", testInput, 64)).isEqualTo("63960835bcdc130f0b66d7ff4f6a5a8e")
    }

    @Test
    fun `Dense hash of testInput is "62e2204d2ca4f4924f6e7a80f1288786"`() {
        assertThat(denseHash(testInputLengths.joinToString(separator = ","), testInput, 64))
                .isEqualTo("62e2204d2ca4f4924f6e7a80f1288786")
    }
}