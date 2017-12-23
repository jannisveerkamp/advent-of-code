package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class CoprocessorConflagrationTest {

    private val testInput = linesFromResource("23_coprocessor_conflagration_input.txt")

    @Test
    fun `mul is called 0 times with testInput`() {
        assertThat(duetAgain(testInput)).isEqualTo(0)
    }
}
