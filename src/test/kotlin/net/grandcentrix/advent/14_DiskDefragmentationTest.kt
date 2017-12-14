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
    fun `simpleTestInput used up 8108 squares`() {
        assertThat(squaresUsed(simpleTestInput)).isEqualTo(8108)
    }

    @Test
    fun `testInput used up 0 squares`() {
        assertThat(squaresUsed(testInput)).isEqualTo(0)
    }
}