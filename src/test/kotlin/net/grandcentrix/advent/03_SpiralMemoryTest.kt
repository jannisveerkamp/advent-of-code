package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class SpiralMemoryTest {

    @Test
    fun `Manhattan Distance of 1 is 0`() {
        assertThat(manhattanDistance(1)).isEqualTo(0)
    }

    @Test
    fun `Manhattan Distance of 2 is 1`() {
        assertThat(manhattanDistance(2)).isEqualTo(1)
    }

    @Test
    fun `Manhattan Distance of 3 is 2`() {
        assertThat(manhattanDistance(3)).isEqualTo(2)
    }

    @Test
    fun `Manhattan Distance of 4 is 1`() {
        assertThat(manhattanDistance(4)).isEqualTo(1)
    }

    @Test
    fun `Manhattan Distance of 12 is 3`() {
        assertThat(manhattanDistance(12)).isEqualTo(3)
    }

    @Test
    fun `Manhattan Distance of 23 is 2`() {
        assertThat(manhattanDistance(23)).isEqualTo(2)
    }

    @Test
    fun `Manhattan Distance of 28 is 3`() {
        assertThat(manhattanDistance(28)).isEqualTo(3)
    }

    @Test
    fun `Manhattan Distance of 1024 is 31`() {
        assertThat(manhattanDistance(1024)).isEqualTo(31)
    }

    @Test
    fun `Manhattan Distance of 325489 is 0`() {
        assertThat(manhattanDistance(325489)).isEqualTo(0)
    }
}