package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class DuetTest {

    private val simpleTestInput = linesFromResource("18_duet_simple_input.txt")

    private val testInput = linesFromResource("18_duet_input.txt")

    @Test
    fun `At the time the recover operation is executed, the frequency of the last sound played is 4`() {
        assertThat(duet(simpleTestInput)).isEqualTo(4)
    }

    @Test
    fun `At the time the recover operation is executed, the frequency of the last sound played is 0`() {
        assertThat(duet(testInput)).isEqualTo(0)
    }
}
