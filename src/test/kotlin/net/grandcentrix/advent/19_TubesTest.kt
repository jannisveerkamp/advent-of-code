package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class TubesTest {

    private val simpleTestInput = fromResource("19_tubes_simple_input.txt")

    private val testInput = fromResource("19_tubes_input.txt")

    @Test
    fun `Going through simpleTestInput the packet sees the letters ABCDEF`() {
        assertThat(lettersOnPath(simpleTestInput)).isEqualTo("ABCDEF")
    }

    @Test
    fun `Going through testInput the packet sees the letters A`() {
        assertThat(lettersOnPath(testInput)).isEqualTo("A")
    }
}
