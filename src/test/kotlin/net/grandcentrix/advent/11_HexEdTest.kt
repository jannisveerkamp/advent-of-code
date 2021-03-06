package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class HexEdTest {

    private val testInput = fromResource("11_hex_ed_input.txt")

    @Test
    fun `(ne, ne, ne) is 3 steps away`() {
        assertThat(hexDistance(listOf("ne", "ne", "ne"))).isEqualTo(3)
    }

    @Test
    fun `(ne, ne, sw, sw) is 0 steps away`() {
        assertThat(hexDistance(listOf("ne", "ne", "sw", "sw"))).isEqualTo(0)
    }

    @Test
    fun `(ne, ne, s, s) is 2 steps away`() {
        assertThat(hexDistance(listOf("ne", "ne", "s", "s"))).isEqualTo(2)
    }

    @Test
    fun `(se, sw, se, sw, sw) is 3 steps away`() {
        assertThat(hexDistance(listOf("se", "sw", "se", "sw", "sw"))).isEqualTo(3)
    }

    @Test
    fun `(n, ne, se, s, sw, nw) is 0 steps away`() {
        assertThat(hexDistance(listOf("n", "ne", "se", "s", "sw", "nw"))).isEqualTo(0)
    }

    @Test
    fun `testInput is 743 steps away`() {
        assertThat(hexDistance(testInput.split(","))).isEqualTo(743)
    }

    @Test
    fun `largest distance of testInput is 1493 steps away`() {
        assertThat(largestHexDistance(testInput.split(","))).isEqualTo(1493)
    }
}