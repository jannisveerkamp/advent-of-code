package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class ElectromagneticMoatTest {

    private val testInput = linesFromResource("24_electromagnetic_moat_input.txt")

    private val simpleTestInput = listOf(
            "0/2",
            "2/2",
            "2/3",
            "3/4",
            "3/5",
            "0/1",
            "10/1",
            "9/10"
    )

    @Test
    fun `Strongest Bridge for simpleTestInput is 31`() {
        assertThat(strongestBridge(simpleTestInput)).isEqualTo(31)
    }

    @Test
    fun `Strongest Bridge for testInput is 0`() {
        assertThat(strongestBridge(testInput)).isEqualTo(0)
    }
}
