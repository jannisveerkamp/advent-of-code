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
    fun `Manhattan Distance of 325489 is 552`() {
        assertThat(manhattanDistance(325489)).isEqualTo(552)
    }

    @Test
    fun `Value for position of 1 is 1`() {
        assertThat(valueForPosition(1)).isEqualTo(1)
    }

    @Test
    fun `Value for position of 2 is 1`() {
        assertThat(valueForPosition(2)).isEqualTo(1)
    }

    @Test
    fun `Value for position of 3 is 1`() {
        assertThat(valueForPosition(3)).isEqualTo(2)
    }

    @Test
    fun `Value for position of 4 is 4`() {
        assertThat(valueForPosition(4)).isEqualTo(4)
    }

    @Test
    fun `Value for position of 5 is 5`() {
        assertThat(valueForPosition(5)).isEqualTo(5)
    }

    @Test
    fun `Value for position of 6 is 10`() {
        assertThat(valueForPosition(6)).isEqualTo(10)
    }

    @Test
    fun `Value for position of 10 is 26`() {
        assertThat(valueForPosition(10)).isEqualTo(26)
    }

    @Test
    fun `Value for position of 12 is 57`() {
        assertThat(valueForPosition(12)).isEqualTo(57)
    }

    @Test
    fun `Value for position of 22 is 747`() {
        assertThat(valueForPosition(22)).isEqualTo(747)
    }

    @Test
    fun `Value for position of 23 is 806`() {
        assertThat(valueForPosition(23)).isEqualTo(806)
    }

    @Test
    fun `Value for position of 325489 is 0`() {
        assertThat(firstNumberLargerThan(325489)).isEqualTo(330785)
    }
}