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
    fun `Permutation of "abcde" with simpleTestInput is "baedc"`() {
        assertThat(permutate("abcde", simpleTestInput)).isEqualTo("baedc")
    }

    @Test
    fun `Permutation of "abcdefghijklmnop" with testInput is "abcdefghijklmnop"`() {
        assertThat(permutate("abcdefghijklmnop", testInput)).isEqualTo("abcdefghijklmnop")
    }
}
