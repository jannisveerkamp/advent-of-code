package net.grandcentrix.advent

fun largestDifference(row: List<Int>): Int = row.max()!! - row.min()!!

fun checksum(vararg rows: List<Int>): Int = rows.map { largestDifference(it) }.sum()

fun evenlyDivision(row: List<Int>): Int {
    row.forEach { outer ->
        row.forEach { inner ->
            if (outer != inner) {
                if (outer % inner == 0) {
                    return outer / inner
                } else if (inner % outer == 0) {
                    return inner / outer
                }
            }
        }
    }
    return 0
}

fun advancedChecksum(vararg rows: List<Int>): Int = rows.map { evenlyDivision(it) }.sum()
