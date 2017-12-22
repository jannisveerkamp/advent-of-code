package net.grandcentrix.advent

import net.grandcentrix.advent.Dir.DOWN
import net.grandcentrix.advent.Dir.LEFT
import net.grandcentrix.advent.Dir.RIGHT
import net.grandcentrix.advent.Dir.UP
import net.grandcentrix.advent.InfectionState.CLEAN
import net.grandcentrix.advent.InfectionState.FLAGGED
import net.grandcentrix.advent.InfectionState.INFECTED
import net.grandcentrix.advent.InfectionState.WEAKENED
import kotlin.math.sqrt

enum class InfectionState {
    CLEAN,
    WEAKENED,
    INFECTED,
    FLAGGED;

    fun simpleEncounter(): InfectionState {
        return when (this) {
            CLEAN -> INFECTED
            INFECTED -> CLEAN
            else -> this
        }
    }

    fun enounter(): InfectionState {
        return when (this) {
            CLEAN -> WEAKENED
            WEAKENED -> INFECTED
            INFECTED -> FLAGGED
            FLAGGED -> CLEAN
        }
    }
}

enum class Dir {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun turnSimple(infected: InfectionState) = turn(infected == INFECTED)

    fun turnComplex(infected: InfectionState): Dir {
        return when (infected) {
            CLEAN -> turn(false)
            INFECTED -> turn(true)
            WEAKENED -> this
            FLAGGED -> when (this) {
                UP -> DOWN
                DOWN -> UP
                LEFT -> RIGHT
                RIGHT -> LEFT
            }
        }
    }

    private fun turn(right: Boolean): Dir {
        return when (this) {
            UP -> if (right) RIGHT else LEFT
            DOWN -> if (right) LEFT else RIGHT
            LEFT -> if (right) UP else DOWN
            RIGHT -> if (right) DOWN else UP
        }
    }
}

internal fun bulkBurstSimple(input: List<String>, iterations: Int): Int {
    return bulkBurst(input, iterations, { it.simpleEncounter() }, { dir, state -> dir.turnSimple(state) },
            iterations / 4 + input.size * 2)
}

internal fun bulkBurstComplex(input: List<String>, iterations: Int): Int {
    return bulkBurst(input, iterations, { it.enounter() }, { dir, state -> dir.turnComplex(state) },
            sqrt(iterations.toDouble()).toInt() / 2 + input.size * 2)
}

internal fun bulkBurst(input: List<String>, iterations: Int, encounter: (InfectionState) -> InfectionState,
                       turn: (Dir, InfectionState) -> Dir, gridSize: Int): Int {
    val grid: Array<Array<InfectionState>> = Array(gridSize) { Array(gridSize) { CLEAN } }

    val offset = gridSize / 2 - input.size / 2
    input.forEachIndexed { indexRow, row ->
        row.forEachIndexed { indexColumn, value ->
            if (value == '#') {
                grid[indexRow + offset][indexColumn + offset] = INFECTED
            }
        }
    }

    var infectionCount = 0
    var direction = UP
    var posX = gridSize / 2
    var posY = gridSize / 2

    repeat(iterations) {
        // 1. Turn
        //direction = direction.turn(grid[posY][posX])
        direction = turn(direction, grid[posY][posX])

        // 2. Clean/infect
        grid[posY][posX] = encounter(grid[posY][posX])
        if (grid[posY][posX] == INFECTED) {
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
