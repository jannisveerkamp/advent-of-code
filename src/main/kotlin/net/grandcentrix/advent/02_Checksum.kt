package net.grandcentrix.advent

fun largestDifference(row: List<Int>): Int = row.max()!! - row.min()!!

fun checksum(vararg rows: List<Int>): Int = rows.map { largestDifference(it) }.sum()
