package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class DuelingGeneratorsTest {

    @Test
    fun `binary representation of 1092455 is 00000000000100001010101101100111`() {
        assertThat(toBinary(1092455)).isEqualTo("00000000000100001010101101100111")
    }

    @Test
    fun `binary representation of 430625591 is 00011001101010101101001100110111`() {
        assertThat(toBinary(430625591)).isEqualTo("00011001101010101101001100110111")
    }

    @Test
    fun `binary representation of 1181022009 is 01000110011001001111011100111001`() {
        assertThat(toBinary(1181022009)).isEqualTo("01000110011001001111011100111001")
    }

    @Test
    fun `binary representation of 1233683848 is 01001001100010001000010110001000`() {
        assertThat(toBinary(1233683848)).isEqualTo("01001001100010001000010110001000")
    }

    @Test
    fun `binary representation of 245556042 is 00001110101000101110001101001010`() {
        assertThat(toBinary(245556042)).isEqualTo("00001110101000101110001101001010")
    }

    @Test
    fun `binary representation of 1431495498 is 01010101010100101110001101001010`() {
        assertThat(toBinary(1431495498)).isEqualTo("01010101010100101110001101001010")
    }


    @Test
    fun `binary representation of 1744312007 is 01100111111110000001011011000111`() {
        assertThat(toBinary(1744312007)).isEqualTo("01100111111110000001011011000111")
    }

    @Test
    fun `binary representation of 137874439 is 00001000001101111100110000000111`() {
        assertThat(toBinary(137874439)).isEqualTo("00001000001101111100110000000111")
    }

    @Test
    fun `binary representation of 1352636452 is 01010000100111111001100000100100`() {
        assertThat(toBinary(1352636452)).isEqualTo("01010000100111111001100000100100")
    }

    @Test
    fun `binary representation of 285222916 is 00010001000000000010100000000100`() {
        assertThat(toBinary(285222916)).isEqualTo("00010001000000000010100000000100")
    }

    @Test
    fun `The last 16 bits of 1181022009 and 1233683848 are not equal`() {
        assertThat(equalsLast16Bits(toBinary(1181022009), toBinary(1233683848))).isFalse()
    }

    @Test
    fun `The last 16 bits of 245556042 and 1431495498 are equal`() {
        assertThat(equalsLast16Bits(toBinary(245556042), toBinary(1431495498))).isTrue()
    }

    @Test
    fun `Dueling 65 and 8921 5 times gives an equals count of 1`() {
        assertThat(duelEqualsCount(65, 8921, 5)).isEqualTo(1)
    }

    @Test
    fun `Dueling 65 and 8_921 40_000_000 times gives an equals count of 588`() {
        assertThat(duelEqualsCount(65, 8_921, 40_000_000)).isEqualTo(588)
    }

    @Test
    fun `Dueling 277 and 349 40_000_000 times gives an equals count of 592`() {
        assertThat(duelEqualsCount(277, 349, 40_000_000)).isEqualTo(592)
    }

    @Test
    fun `Dueling 65 and 8_921 1055 times while dropping values gives an equals count of 0`() {
        assertThat(duelEqualsCountDropValues(65, 8_921, 1055)).isEqualTo(0)
    }

    @Test
    fun `Dueling 65 and 8_921 1056 times while dropping values gives an equals count of 1`() {
        assertThat(duelEqualsCountDropValues(65, 8_921, 1056)).isEqualTo(1)
    }

    @Test
    fun `Dueling 65 and 8_921 5_000_000 times while dropping values gives an equals count of 309`() {
        assertThat(duelEqualsCountDropValues(65, 8_921, 5_000_000)).isEqualTo(309)
    }

    @Test
    fun `Dueling 277 and 349 45_000_000 times while dropping values gives an equals count of 320`() {
        assertThat(duelEqualsCountDropValues(277, 349, 5_000_000)).isEqualTo(320)
    }
}
