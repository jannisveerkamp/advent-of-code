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
        assertThat(DuetProgram(simpleTestInput).duet()).isEqualTo(4L)
    }

    @Test
    fun `At the time the recover operation is executed, the frequency of the last sound played is 2951`() {
        assertThat(DuetProgram(testInput).duet()).isEqualTo(2951L)
    }

    @Test
    fun `Program 1 of of simpleTestInputWithoutSound sent 3 values`() {
        assertThat(duetWithTwoPrograms(simpleTestInputWithoutSound)).isEqualTo(3)
    }

    @Test
    fun `Program 1 of of testInput sent 7366 values`() {
        assertThat(duetWithTwoPrograms(testInput)).isEqualTo(7366)
    }
}
