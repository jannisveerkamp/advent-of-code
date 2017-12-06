package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class MemoryReallocationTest {

    private val testInput = mutableListOf(4, 1, 15, 12, 0, 9, 9, 5, 5, 8, 7, 3, 14, 5, 12, 3)

    @Test
    fun `(0, 2, 7, 0) needs 5 steps to get a configuration it has seen before`() {
        assertThat(reallocateBlock(mutableListOf(0, 2, 7, 0))).isEqualTo(5)
    }

    @Test
    fun `test input needs 6681 steps to get a configuration it has seen before`() {
        assertThat(reallocateBlock(testInput)).isEqualTo(6681)
    }
}