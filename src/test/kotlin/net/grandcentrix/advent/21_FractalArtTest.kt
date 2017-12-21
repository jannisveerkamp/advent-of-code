package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class FractalArtTest {

    private val simpleTestInput = listOf(
            "../.# => ##./#../...",
            ".#./..#/### => #..#/..../..../#..#"
    )

    private val testInput = linesFromResource("21_fractal_art_input.txt")

    @Test
    fun `After 2 iterations 5 pixels of the simpleTestInout are on`() {
        assertThat(doArt(simpleTestInput, 2)).isEqualTo(5)
    }

    @Test
    fun `After 5 iterations 0 pixels of the testInput are on`() {
        assertThat(doArt(testInput, 5)).isEqualTo(5)
    }
}
