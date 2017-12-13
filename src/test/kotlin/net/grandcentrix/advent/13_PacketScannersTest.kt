package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class PacketScannersTest {

    private val simpleTestInput = mapOf(
            0 to 3,
            1 to 2,
            4 to 4,
            6 to 4
    )

    private val testInput = mapOf(
            0 to 5,
            1 to 2,
            2 to 3,
            4 to 4,
            6 to 8,
            8 to 4,
            10 to 6,
            12 to 6,
            14 to 8,
            16 to 6,
            18 to 6,
            20 to 12,
            22 to 14,
            24 to 8,
            26 to 8,
            28 to 9,
            30 to 8,
            32 to 8,
            34 to 12,
            36 to 10,
            38 to 12,
            40 to 12,
            44 to 14,
            46 to 12,
            48 to 10,
            50 to 12,
            52 to 12,
            54 to 12,
            56 to 14,
            58 to 12,
            60 to 14,
            62 to 14,
            64 to 14,
            66 to 14,
            68 to 17,
            70 to 12,
            72 to 14,
            76 to 14,
            78 to 14,
            80 to 14,
            82 to 18,
            84 to 14,
            88 to 20
    )

    @Test
    fun `Severity of getting caught in simpleTestInput is 24`() {
        assertThat(severity(simpleTestInput)).isEqualTo(24)
    }

    @Test
    fun `Severity of getting caught in testInput is 1624`() {
        assertThat(severity(testInput)).isEqualTo(1624)
    }

    @Test
    fun `Minimum delay without getting caught forsimpleTestInput is 10`() {
        assertThat(minimumDelayWithoutGettingCaught(simpleTestInput)).isEqualTo(10)
    }

    @Test
    fun `Minimum delay without getting caught testInput is 0`() {
        assertThat(minimumDelayWithoutGettingCaught(testInput)).isEqualTo(0)
    }
}