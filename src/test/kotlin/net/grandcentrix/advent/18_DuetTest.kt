package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class DuetTest {

    private val simpleTestInput = linesFromResource("18_duet_simple_input.txt")

    private val simpleTestInputWithoutSound = linesFromResource("18_duet_part_2_simple_input.txt")

    private val testInput = linesFromResource("18_duet_input.txt")

    @Test
    fun `At the time the recover operation is executed, the frequency of the last sound played is 4`() {
        assertThat(duet(simpleTestInput)).isEqualTo(4L)
    }

    @Test
    fun `At the time the recover operation is executed, the frequency of the last sound played is 2951`() {
        assertThat(duet(testInput)).isEqualTo(2951L)
    }

    @Test
    fun `Program 1 of of simpleTestInputWithoutSound sent 4 values`() {
        assertThat(duetWithoutSound(simpleTestInputWithoutSound)).isEqualTo(4)
    }

    @Test
    fun `Program 1 of of testInput sent 0 values`() {
        assertThat(duetWithoutSound(testInput)).isEqualTo(0)
    }
}
