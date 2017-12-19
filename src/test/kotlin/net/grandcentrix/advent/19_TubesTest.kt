package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class TubesTest {

    private val simpleTestInput = linesFromResource("19_tubes_simple_input.txt")

    private val testInput = linesFromResource("19_tubes_input.txt")

    @Test
    fun `Going through simpleTestInput the packet sees the letters ABCDEF and takes 38 steps`() {
        assertThat(lettersOnPath(simpleTestInput)).isEqualTo("ABCDEF" to 38)
    }

    @Test
    fun `Going through testInput the packet sees the letters BPDKCZWHGT and takes 17728 steps`() {
        assertThat(lettersOnPath(testInput)).isEqualTo("BPDKCZWHGT" to 17728)
    }
}
