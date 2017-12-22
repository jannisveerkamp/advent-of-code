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
        assertThat(bulkBurst(simpleTestInput, 7)).isEqualTo(5)
    }

    @Test
    fun `70 bursts on simpleTestInput caused 41 infections`() {
        assertThat(bulkBurst(simpleTestInput, 70)).isEqualTo(41)
    }

    @Test
    fun `10000 bursts on simpleTestInput caused 5587 infections`() {
        assertThat(bulkBurst(simpleTestInput, 10000)).isEqualTo(5587)
    }

    @Test
    fun `10000 bursts on testInput caused 5322 infections`() {
        assertThat(bulkBurst(testInput, 10000)).isEqualTo(5322)
    }
}
