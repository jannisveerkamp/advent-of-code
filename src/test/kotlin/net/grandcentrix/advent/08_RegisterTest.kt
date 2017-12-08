package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class RegisterTest {

    private val simpleTestInput = mutableListOf(
            "b inc 5 if a > 1",
            "a inc 1 if b < 5",
            "c dec -10 if a >= 1",
            "c inc -20 if c == 10"
    )

    private val testInput = linesFromResource("08_register_input.txt")

    @Test
    fun `simpleTestInput has largest register of 1 after process`() {
        assertThat(largestValue(simpleTestInput)).isEqualTo(1)
    }

    @Test
    fun `testInput has largest register of 3089 after process`() {
        assertThat(largestValue(testInput)).isEqualTo(3089)
    }
}
