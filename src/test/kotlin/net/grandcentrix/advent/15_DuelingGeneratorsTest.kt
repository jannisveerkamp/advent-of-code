package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class DuelingGeneratorsTest {

    @Test
    fun `Dueling 65 and 8921 5 times gives an equals count of 1`() {
        assertThat(duelEqualsCount(65, 8921, 5)).isEqualTo(1)
    }

    @Test
    fun `Dueling 65 and 8_921 40_000_000 times gives an equals count of 588`() {
        assertThat(duelEqualsCount(65, 8_921, 40_000_000)).isEqualTo(588)
    }

    @Test
    fun `Dueling 277 and 349 40_000_000 times gives an equals count of 0`() {
        assertThat(duelEqualsCount(277, 349, 40_000_000)).isEqualTo(0)
    }
}
