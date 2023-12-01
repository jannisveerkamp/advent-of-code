package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class PermutationPromenadeTest {

    private val simpleTestInput = "s1,x3/4,pe/b"

    private val testInput = fromResource("16_permutation_promenade_input.txt")

    @Test
    fun `Spins s1 of "abcde" is "eabcd"`() {
        assertThat(spin("abcde", "s1")).isEqualTo("eabcd")
    }

    @Test
    fun `Spins s3 of "abcde" is "cdeab"`() {
        assertThat(spin("abcde", "s3")).isEqualTo("cdeab")
    }

    @Test
    fun `Exchange x3_4 of "eabcd" is "eabdc"`() {
        assertThat(exchange("eabcd", "x3/4")).isEqualTo("eabdc")
    }

    @Test
    fun `Partner pe_b of "eabdc" is "baedc"`() {
        assertThat(partner("eabdc", "pe/b")).isEqualTo("baedc")
    }

    @Test
    fun `Permutation of "abcde" with simpleTestInput is "baedc"`() {
        assertThat(permutate("abcde", simpleTestInput)).isEqualTo("baedc")
    }

    @Test
    fun `Permutation of "abcdefghijklmnop" with testInput is "giadhmkpcnbfjelo"`() {
        assertThat(permutate("abcdefghijklmnop", testInput)).isEqualTo("giadhmkpcnbfjelo")
    }

    @Test
    fun `2 Permutations of "abcde" with simpleTestInput is "ceadb"`() {
        assertThat(permutate("abcde", simpleTestInput, 2)).isEqualTo("ceadb")
    }

    @Test
    fun `1_000_000_000 Permutations of "abcdefghijklmnop" with testInput is "njfgilbkcoemhpad"`() {
        assertThat(permutate("abcdefghijklmnop", testInput, 1_000_000_000)).isEqualTo("njfgilbkcoemhpad")
    }
}
