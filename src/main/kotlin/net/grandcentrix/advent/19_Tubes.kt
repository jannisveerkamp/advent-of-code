package net.grandcentrix.advent

import net.grandcentrix.advent.Direction.DOWN
import net.grandcentrix.advent.Direction.LEFT
import net.grandcentrix.advent.Direction.RIGHT
import net.grandcentrix.advent.Direction.UP

enum class Direction(val x: Int, val y: Int) {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    fun move(pos: Pair<Int, Int>) = pos.first + x to pos.second + y

    fun follows(grid: Array<CharArray>, position: Pair<Int, Int>) = grid[move(position)]
}

private operator fun Array<CharArray>.get(index: Pair<Int, Int>) = this[index.second][index.first]

fun lettersOnPath(input: List<String>): Pair<String, Int> {
    val maximumLength = input.maxBy { it.length }!!.length
    val grid = input.map { it.padEnd(maximumLength, ' ').toCharArray() }.toTypedArray()

    // Initial position and direction
    var position = grid[0].indexOf('|') to 0
    var direction = DOWN
    val listOfLetters = mutableListOf<Char>()
    var steps = 0

    while (true) {
        position = direction.move(position)
        steps++

        val item = grid[position]
        if (item == '+') {
            direction = turn(direction, grid, position)
        } else if (item != '|' && item != '-') {
            listOfLetters.add(item)
            if (direction.follows(grid, position) == ' ') {
                return listOfLetters.joinToString("") to (steps + 1)
            }
        }
    }
}

private fun turn(direction: Direction, grid: Array<CharArray>, position: Pair<Int, Int>): Direction {
    return when (direction) {
        UP, DOWN -> if (LEFT.follows(grid, position) != ' ') LEFT else RIGHT
        LEFT, RIGHT -> if (UP.follows(grid, position) != ' ') UP else DOWN
    }
}
