package net.grandcentrix.advent

import org.assertj.core.api.Assertions
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class ChecksumTest {

    private val row01 = listOf(737, 1866, 1565, 1452, 1908, 1874, 232, 1928, 201, 241, 922, 281, 1651, 1740, 1012, 1001)
    private val row02 = listOf(339, 581, 41, 127, 331, 133, 51, 131, 129, 95, 499, 527, 518, 435, 508, 494)
    private val row03 = listOf(1014, 575, 1166, 259, 152, 631, 1152, 1010, 182, 943, 163, 158, 1037, 1108, 1092, 887)
    private val row04 = listOf(56, 491, 409, 1263, 1535, 41, 1431, 1207, 1393, 700, 1133, 53, 131, 466, 202, 62)
    private val row05 = listOf(632, 403, 118, 352, 253, 672, 711, 135, 116, 665, 724, 780, 159, 133, 90, 100)
    private val row06 = listOf(1580, 85, 1786, 1613, 1479, 100, 94, 1856, 546, 76, 1687, 1769, 1284, 1422, 1909, 1548)
    private val row07 = listOf(479, 356, 122, 372, 786, 1853, 979, 116, 530, 123, 1751, 887, 109, 1997, 160, 1960)
    private val row08 = listOf(446, 771, 72, 728, 109, 369, 300, 746, 86, 910, 566, 792, 616, 84, 338, 57)
    private val row09 = listOf(6599, 2182, 200, 2097, 4146, 7155, 7018, 1815, 1173, 4695, 201, 7808, 242, 3627, 222, 7266)
    private val row10 = listOf(1729, 600, 651, 165, 1780, 2160, 626, 1215, 149, 179, 1937, 1423, 156, 129, 634, 458)
    private val row11 = listOf(1378, 121, 146, 437, 1925, 2692, 130, 557, 2374, 2538, 2920, 2791, 156, 317, 139, 541)
    private val row12 = listOf(1631, 176, 1947, 259, 2014, 153, 268, 752, 2255, 347, 227, 2270, 2278, 544, 2379, 349)
    private val row13 = listOf(184, 314, 178, 242, 145, 410, 257, 342, 183, 106, 302, 320, 288, 151, 449, 127)
    private val row14 = listOf(175, 5396, 1852, 4565, 4775, 665, 4227, 171, 4887, 181, 2098, 4408, 2211, 3884, 2482, 158)
    private val row15 = listOf(1717, 3629, 244, 258, 281, 3635, 235, 4148, 3723, 4272, 3589, 4557, 4334, 4145, 3117, 4510)
    private val row16 = listOf(55, 258, 363, 116, 319, 49, 212, 44, 303, 349, 327, 330, 316, 297, 313, 67)

    @Test
    fun `largest difference of 5195 is 8`() {
        Assertions.assertThat(largestDifference(listOf(5, 1, 9, 5))).isEqualTo(8)
    }

    @Test
    fun `largest difference of 753 is 4`() {
        Assertions.assertThat(largestDifference(listOf(7, 5, 3))).isEqualTo(4)
    }

    @Test
    fun `largest difference of 2468 is 8`() {
        Assertions.assertThat(largestDifference(listOf(2, 4, 6, 8))).isEqualTo(6)
    }

    @Test
    fun `checksum of simple table is 18`() {
        Assertions.assertThat(checksum(listOf(2, 4, 6, 8), listOf(7, 5, 3), listOf(5, 1, 9, 5))).isEqualTo(18)
    }

    @Test
    fun `largest difference of row01 is 1727`() {
        Assertions.assertThat(largestDifference(row01)).isEqualTo(1727)
    }

    @Test
    fun `largest difference of row02 is 540`() {
        Assertions.assertThat(largestDifference(row02)).isEqualTo(540)
    }

    @Test
    fun `largest difference of row03 is 1014`() {
        Assertions.assertThat(largestDifference(row03)).isEqualTo(1014)
    }

    @Test
    fun `largest difference of row04 is 1494`() {
        Assertions.assertThat(largestDifference(row04)).isEqualTo(1494)
    }

    @Test
    fun `largest difference of row05 is 690`() {
        Assertions.assertThat(largestDifference(row05)).isEqualTo(690)
    }

    @Test
    fun `largest difference of row06 is 1833`() {
        Assertions.assertThat(largestDifference(row06)).isEqualTo(1833)
    }

    @Test
    fun `largest difference of row07 is 1888`() {
        Assertions.assertThat(largestDifference(row07)).isEqualTo(1888)
    }

    @Test
    fun `largest difference of row08 is 853`() {
        Assertions.assertThat(largestDifference(row08)).isEqualTo(853)
    }

    @Test
    fun `largest difference of row09 is 7608`() {
        Assertions.assertThat(largestDifference(row09)).isEqualTo(7608)
    }

    @Test
    fun `largest difference of row10 is 2031`() {
        Assertions.assertThat(largestDifference(row10)).isEqualTo(2031)
    }

    @Test
    fun `largest difference of row11 is 2799`() {
        Assertions.assertThat(largestDifference(row11)).isEqualTo(2799)
    }

    @Test
    fun `largest difference of row12 is 2226`() {
        Assertions.assertThat(largestDifference(row12)).isEqualTo(2226)
    }

    @Test
    fun `largest difference of row13 is 343`() {
        Assertions.assertThat(largestDifference(row13)).isEqualTo(343)
    }

    @Test
    fun `largest difference of row14 is 5238`() {
        Assertions.assertThat(largestDifference(row14)).isEqualTo(5238)
    }

    @Test
    fun `largest difference of row15 is 4322`() {
        Assertions.assertThat(largestDifference(row15)).isEqualTo(4322)
    }

    @Test
    fun `largest difference of row16 is 319`() {
        Assertions.assertThat(largestDifference(row16)).isEqualTo(319)
    }

    @Test
    fun `checksum of test rows is 34925`() {
        Assertions.assertThat(checksum(row01, row02, row03, row04, row05, row06, row07, row08, row09, row10, row11,
                row12, row13, row14, row15, row16)).isEqualTo(34925)
    }
}