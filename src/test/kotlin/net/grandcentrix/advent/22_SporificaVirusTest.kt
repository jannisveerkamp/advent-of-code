package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class SporificaVirusTest {

    private val simpleTestInput = listOf(
            "..#",
            "#..",
            "..."
    )

    private val testInput = listOf(
            ".#...#.#.##..##....##.#.#",
            "###.###..##...##.##....##",
            "....#.###..#...#####..#.#",
            ".##.######..###.##..#...#",
            "#..#..#..##..###...#..###",
            "..####...#.##.#.#.##.####",
            "#......#..####..###..###.",
            "#####.##.#.#.##.###.#.#.#",
            ".#.###....###....##....##",
            ".......########.#.#...#..",
            "...###.####.##..###.##..#",
            "#.#.###.####.###.###.###.",
            ".######...###.....#......",
            "....##.###..#.#.###...##.",
            "#.###..###.#.#.##.#.##.##",
            "#.#.#..###...###.###.....",
            "##..##.##...##.##..##.#.#",
            ".....##......##..#.##...#",
            "..##.#.###.#...#####.#.##",
            "....##..#.#.#.#..###.#..#",
            "###..##.##....##.#....##.",
            "#..####...####.#.##..#.##",
            "####.###...####..##.#.#.#",
            "#.#.#.###.....###.##.###.",
            ".#...##.#.##..###.#.###.."
    )

    @Test
    fun `7 bursts on simpleTestInput caused 5 infections`() {
        assertThat(bulkBurstSimple(simpleTestInput, 7)).isEqualTo(5)
    }

    @Test
    fun `70 bursts on simpleTestInput caused 41 infections`() {
        assertThat(bulkBurstSimple(simpleTestInput, 70)).isEqualTo(41)
    }

    @Test
    fun `10000 bursts on simpleTestInput caused 5587 infections`() {
        assertThat(bulkBurstSimple(simpleTestInput, 10000)).isEqualTo(5587)
    }

    @Test
    fun `10000 bursts on testInput caused 5322 infections`() {
        assertThat(bulkBurstSimple(testInput, 10000)).isEqualTo(5322)
    }

    @Test
    fun `100 bursts on simpleTestInput caused 26 infections with the complex mode`() {
        assertThat(bulkBurstComplex(simpleTestInput, 100)).isEqualTo(26)
    }

    @Test
    fun `10000000 bursts on simpleTestInput caused 2511944 infections with the complex mode`() {
        assertThat(bulkBurstComplex(simpleTestInput, 10000000)).isEqualTo(2511944)
    }

    @Test
    fun `10000000 bursts on testInput caused 2512079 infections with the complex mode`() {
        assertThat(bulkBurstComplex(testInput, 10000000)).isEqualTo(2512079)
    }
}
