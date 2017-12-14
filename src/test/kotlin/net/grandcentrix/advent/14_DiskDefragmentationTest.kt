package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class DiskDefragmentation {

    private val simpleTestInput = "flqrgnkx"

    private val testInput = "xlqgujun"

    @Test
    fun `Binary representation of 0 is 0000`() {
        assertThat(toBinary("0")).isEqualTo("0000")
    }

    @Test
    fun `Binary representation of e is 1110`() {
        assertThat(toBinary("e")).isEqualTo("1110")
    }

    @Test
    fun `Binary representation of f is 1111`() {
        assertThat(toBinary("f")).isEqualTo("1111")
    }

    @Test
    fun `Binary representation of a0c2017 is 1010000011000010000000010111`() {
        assertThat(toBinary("a0c2017")).isEqualTo("1010000011000010000000010111")
    }

    @Test
    fun `simpleTestInput used up 8108 squares`() {
        assertThat(squaresUsed(simpleTestInput)).isEqualTo(8108)
    }

    @Test
    fun `testInput used up 8204 squares`() {
        assertThat(squaresUsed(testInput)).isEqualTo(8204)
    }
}