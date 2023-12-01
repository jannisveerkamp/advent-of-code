package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class HaltingProblemTest {

    private val testInput = linesFromResource("25_halting_problem_input.txt")

    private val simpleTestInput = linesFromResource("25_halting_problem_simple_input.txt")

    @Test
    fun `Diagnostic Checksum for simpleTestInput is 3`() {
        assertThat(diagnosticChecksum(simpleTestInput)).isEqualTo(3)
    }

    @Test
    fun `Diagnostic Checksum for testInput is 5744`() {
        assertThat(diagnosticChecksum(testInput)).isEqualTo(5744)
    }
}
