package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class DigitalPlumberTest {

    private val testInput = linesFromResource("12_digital_plumber_input.txt")

    private val simpleTestInput = linesFromResource("12_digital_plumber_simple_input.txt")

    @Test
    fun `program 0 from simpleTestInput is connected with 6 programs (including itself)`() {
        assertThat(joinGroups(simpleTestInput)[0]!!.size).isEqualTo(6)
    }

    @Test
    fun `program 0 from testInput is connected with 239 programs (including itself)`() {
        assertThat(joinGroups(testInput)[0]!!.size).isEqualTo(239)
    }

    @Test
    fun `Number of groups from simpleTestInput 2`() {
        assertThat(numberOfGroups(simpleTestInput)).isEqualTo(2)
    }

    @Test
    fun `Number of groups from testInput 215`() {
        assertThat(numberOfGroups(testInput)).isEqualTo(215)
    }
}