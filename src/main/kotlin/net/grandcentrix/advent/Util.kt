package net.grandcentrix.advent

fun fromResource(filename: String) = Int::class.java.getResource(filename).readText()

fun linesFromResource(filename: String) = fromResource(filename).split("\n")
