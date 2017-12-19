package net.grandcentrix.advent

import net.grandcentrix.advent.Direction.DOWN
import net.grandcentrix.advent.Direction.LEFT
import net.grandcentrix.advent.Direction.RIGHT
import net.grandcentrix.advent.Direction.UP

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

fun lettersOnPath(input: List<String>): String {
    val maximumLength = input.maxBy { it.length }!!.length
    val grid = input.map { it.padEnd(maximumLength, ' ').toCharArray() }.toTypedArray()

    // Initial position and direction
    var posX = grid[0].indexOf('|')
    var posY = 0
    var direction = DOWN
    val listOfLetters = mutableListOf<Char>()

    while (true) {
        when (direction) {
            UP -> posY--
            DOWN -> posY++
            LEFT -> posX--
            RIGHT -> posX++
        }

        val item = grid[posY][posX]

        if (item == '+') {
            direction = when (direction) {
                UP, DOWN -> if (grid[posY][posX - 1] != ' ') LEFT else RIGHT
                LEFT, RIGHT -> if (grid[posY - 1][posX] != ' ') UP else DOWN
            }
        } else if (item != '|' && item != '-') {
            listOfLetters.add(item)
            val done = when (direction) {
                UP -> grid[posY - 1][posX] == ' '
                DOWN -> grid[posY + 1][posX] == ' '
                LEFT -> grid[posY][posX - 1] == ' '
                RIGHT -> grid[posY][posX + 1] == ' '
            }
            if (done) {
                return listOfLetters.joinToString("")
            }
        }
    }
}

fun printGrid(grid: Array<CharArray>, posX: Int, posY: Int) {
    grid.forEachIndexed { y, row ->
        row.forEachIndexed { x, char ->
            if (posX == x && posY == y) {
                print("*")
            } else {
                print(char)
            }
        }
        println()
    }
}
