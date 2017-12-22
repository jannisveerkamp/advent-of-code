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
    fun `Rotation of size 2`() {
        assertThat(rotateArray(arrayOf("12", "34"))).isEqualTo(arrayOf(arrayOf('3', '1'), arrayOf('4', '2')))
    }

    @Test
    fun `Rotation of size 3`() {
        assertThat(rotateArray(arrayOf("123", "456", "789")))
                .isEqualTo(arrayOf(arrayOf('7', '4', '1'), arrayOf('8', '5', '2'), arrayOf('9', '6', '3')))
    }

    @Test
    fun `After 2 iterations 12 pixels of the simpleTestInout are on`() {
        assertThat(doArt(simpleTestInput, 2)).isEqualTo(12)
    }

    @Test
    fun `After 5 iterations 190 pixels of the testInput are on`() {
        assertThat(doArt(testInput, 5)).isEqualTo(190)
    }

    @Test
    fun `After 18 iterations 2335049 pixels of the testInput are on`() {
        assertThat(doArt(testInput, 18)).isEqualTo(2335049)
    }

    @Test
    fun `Carve grid with a size of 3`() {
        val grid = carveGrid(listOf("123", "456", "789"))
        assertThat(grid.size).isEqualTo(1)
        assertThat(grid[0].size).isEqualTo(1)
        assertThat(grid[0][0].rows).isEqualTo(listOf("123", "456", "789"))
    }

    @Test
    fun `Carve grid with a size of 4`() {
        val grid = carveGrid(listOf("1234", "5678", "9abc", "defg"))
        assertThat(grid.size).isEqualTo(2)
        assertThat(grid[0].size).isEqualTo(2)
        assertThat(grid[1].size).isEqualTo(2)
        assertThat(grid[0][0].rows).isEqualTo(listOf("12", "56"))
        assertThat(grid[0][1].rows).isEqualTo(listOf("34", "78"))
        assertThat(grid[1][0].rows).isEqualTo(listOf("9a", "de"))
        assertThat(grid[1][1].rows).isEqualTo(listOf("bc", "fg"))
    }

    @Test
    fun `Carve grid with a size of 6`() {
        val grid = carveGrid(listOf("123456", "789abc", "defghi", "jklmno", "pqrstu", "vwxyz0"))
        assertThat(grid.size).isEqualTo(3)
        assertThat(grid[0].size).isEqualTo(3)
        assertThat(grid[1].size).isEqualTo(3)
        assertThat(grid[2].size).isEqualTo(3)
        assertThat(grid[0][0].rows).isEqualTo(listOf("12", "78"))
        assertThat(grid[0][1].rows).isEqualTo(listOf("34", "9a"))
        assertThat(grid[0][2].rows).isEqualTo(listOf("56", "bc"))
        assertThat(grid[1][0].rows).isEqualTo(listOf("de", "jk"))
        assertThat(grid[1][1].rows).isEqualTo(listOf("fg", "lm"))
        assertThat(grid[1][2].rows).isEqualTo(listOf("hi", "no"))
        assertThat(grid[2][0].rows).isEqualTo(listOf("pq", "vw"))
        assertThat(grid[2][1].rows).isEqualTo(listOf("rs", "xy"))
        assertThat(grid[2][2].rows).isEqualTo(listOf("tu", "z0"))
    }
}
