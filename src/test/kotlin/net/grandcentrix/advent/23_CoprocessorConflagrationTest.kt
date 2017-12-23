package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class CoprocessorConflagrationTest {

    private val testInput = linesFromResource("23_coprocessor_conflagration_input.txt")

    @Test
    fun `mul is called 3969 times with testInput`() {
        assertThat(DuetProgramAgain(testInput).duet()).isEqualTo(3969)
    }

    @Test
    fun `initial value for a of 1 results in a value 0 for register h`() {
        assertThat(DuetProgramAgain(testInput).registerH()).isEqualTo(0)
    }
}
