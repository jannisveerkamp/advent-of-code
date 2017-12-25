package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class HaltingProblemTest {

    private val testInput = fromResource("25_halting_problem_input.txt")

    private val simpleTestInput = fromResource("25_halting_problem_simple_input.txt")

    @Test
    fun `Strongest Bridge for simpleTestInput is 3`() {
        assertThat(diagnosticChecksum(simpleTestInput)).isEqualTo(3)
    }

    @Test
    fun `Strongest Bridge for testInput is 0`() {
        assertThat(diagnosticChecksum(testInput)).isEqualTo(0)
    }
}
