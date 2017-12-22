package net.grandcentrix.advent

import net.grandcentrix.advent.Dir.DOWN
import net.grandcentrix.advent.Dir.LEFT
import net.grandcentrix.advent.Dir.RIGHT
import net.grandcentrix.advent.Dir.UP

enum class Dir {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun turn(infected: Boolean): Dir {
        return when (this) {
            UP -> if (infected) RIGHT else LEFT
            DOWN -> if (infected) LEFT else RIGHT
            LEFT -> if (infected) UP else DOWN
            RIGHT -> if (infected) DOWN else UP
        }
    }
}

internal fun bulkBurst(input: List<String>, iterations: Int): Int {
    val gridSize = iterations / 4 + input.size
    val grid: Array<BooleanArray> = Array(gridSize) { BooleanArray(gridSize) { false } }

    val offset = gridSize / 2 - input.size / 2
    input.forEachIndexed { indexRow, row ->
        row.forEachIndexed { indexColumn, value ->
            grid[indexRow + offset][indexColumn + offset] = value == '#'
        }
    }

    var infectionCount = 0
    var direction = UP
    var posX = gridSize / 2
    var posY = gridSize / 2

    repeat(iterations) {
        // 1. Turn
        direction = direction.turn(grid[posY][posX])

        // 2. Clean/infect
        grid[posY][posX] = !grid[posY][posX]
        if (grid[posY][posX]) {
            infectionCount++
        }

        // 3. Move
        when (direction) {
            UP -> posY--
            DOWN -> posY++
            LEFT -> posX--
            RIGHT -> posX++
        }
    }
    return infectionCount
}
