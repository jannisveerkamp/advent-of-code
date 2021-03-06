package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class StreamTest {

    private val testInput = fromResource("09_stream_input.txt")

    @Test
    fun `Remove Garbage`() {
        assertThat(removeGarbage("{<>}")).isEqualTo("{}")
        assertThat(removeGarbage("{<random characters>}")).isEqualTo("{}")
        assertThat(removeGarbage("{<<<<>}")).isEqualTo("{}")
        assertThat(removeGarbage("{<{!>}>}")).isEqualTo("{}")
        assertThat(removeGarbage("{<!!>}")).isEqualTo("{}")
        assertThat(removeGarbage("{<!!!>>}")).isEqualTo("{}")
        assertThat(removeGarbage("{<{o\"i!a,<{i<a>}")).isEqualTo("{}")
        assertThat(removeGarbage("{{<!>},{<!>},{<!>},{<a>}}")).isEqualTo("{{}}")
    }

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
    fun `Score testinput is 14421`() {
        assertThat(scoreForInput(testInput)).isEqualTo(14421)
    }

    @Test
    fun `Garbage Count Test`() {
        assertThat(garbageCount("<>")).isEqualTo(0)
        assertThat(garbageCount("<random characters>")).isEqualTo(17)
        assertThat(garbageCount("<<<<>")).isEqualTo(3)
        assertThat(garbageCount("<{!>}>")).isEqualTo(2)
        assertThat(garbageCount("<!!>")).isEqualTo(0)
        assertThat(garbageCount("<!!!>>")).isEqualTo(0)
        assertThat(garbageCount("<{o\"i!a,<{i<a>")).isEqualTo(10)
    }

    @Test
    fun `Garbage Count for testInput is 6817`() {
        assertThat(garbageCount(testInput)).isEqualTo(6817)
    }
}