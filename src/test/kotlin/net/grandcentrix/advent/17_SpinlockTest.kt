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
    fun `Circular spinning for simpleTestInput is 638"`() {
        assertThat(circularSpin(simpleTestInput)).isEqualTo(638)
    }

    @Test
    fun `Circular spinning for testInput is 600"`() {
        assertThat(circularSpin(testInput)).isEqualTo(600)
    }
}
