package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class StreamTest {

    private val testInput = fromResource("09_stream_input.txt")

    @Test
    fun `Score of "{}" is 1`() {
        assertThat(scoreForInput("{}")).isEqualTo(1)
    }

    @Test
    fun `Score of "{{{}}}" is 6`() {
        assertThat(scoreForInput("{{{}}}")).isEqualTo(6)
    }

    @Test
    fun `Score of "{{},{}}" is 5`() {
        assertThat(scoreForInput("{{},{}}")).isEqualTo(5)
    }

    @Test
    fun `Score of "{{{},{},{{}}}}" is 16`() {
        assertThat(scoreForInput("{{{},{},{{}}}}")).isEqualTo(16)
    }

    @Test
    fun `Score of containing garbage #1 is 1`() {
        assertThat(scoreForInput("{<a>,<a>,<a>,<a>}")).isEqualTo(1)
    }

    @Test
    fun `Score of containing garbage #2 is 9`() {
        assertThat(scoreForInput("{{<ab>},{<ab>},{<ab>},{<ab>}}")).isEqualTo(9)
    }

    @Test
    fun `Score of containing garbage #3 is 9`() {
        assertThat(scoreForInput("{{<!!>},{<!!>},{<!!>},{<!!>}}")).isEqualTo(9)
    }

    @Test
    fun `Score of containing garbage #4 is 3`() {
        assertThat(scoreForInput("{{<a!>},{<a!>},{<a!>},{<ab>}}")).isEqualTo(3)
    }

    @Test
    fun `Score testinput is 0`() {
        assertThat(scoreForInput(testInput)).isEqualTo(0)
    }
}