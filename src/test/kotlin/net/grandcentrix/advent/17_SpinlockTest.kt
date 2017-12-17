package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class SpinlockTest {

    private val simpleTestInput = 3

    private val testInput = 337

    @Test
    fun `Circular spinning for simpleTestInput after 2017 insertions is 638"`() {
        assertThat(circularSpin(simpleTestInput)).isEqualTo(638)
    }

    @Test
    fun `Circular spinning for testInput after 2017 insertions is 600"`() {
        assertThat(circularSpin(testInput)).isEqualTo(600)
    }

    @Test
    fun `Circular spinning for testInput after 50_000_000 insertions is 31220910"`() {
        assertThat(circularSpinValueAfter0(testInput, 50_000_000)).isEqualTo(31220910)
    }
}
