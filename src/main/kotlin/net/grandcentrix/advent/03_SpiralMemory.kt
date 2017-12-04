package net.grandcentrix.advent

fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray> = Array(sizeOuter) { IntArray(sizeInner) }

fun manhattanDistance(location: Int): Int = SpiralMemory(location).manhattanDistance(location)

fun valueForPosition(position: Int): Int = SpiralMemory(position).getMemoryAt(position)

fun firstNumberLargerThan(value: Int): Int {
    var i = 0
    var result = 0
    while (result < value) {
        result = SpiralMemory(i).getMemoryAt(i)
        i++
    }
    return result
}

class SpiralMemory(size: Int) {

    private val memorySize: Int = getMemorySize(size)

    private fun getMemorySize(size: Int): Int {
        var sqrt = Math.ceil(Math.sqrt(size.toDouble())).toInt()
        if (sqrt % 2 == 0) {
            sqrt++
        }
        return sqrt + 2
    }

    private val memoryIndex = array2dOfInt(memorySize, memorySize)

    private val memory = array2dOfInt(memorySize, memorySize)

    private val center = memorySize / 2

    init {
        var turnDirection = 0 // east
        var stepLength = 1
        var step = 1
        var position = 1
        var x = 0
        var y = 0

        while (position < size + 1) {
            memoryIndex[y + center][x + center] = position
            if (x == 0 && y == 0) {
                memory[center][center] = 1
            } else {
                memory[y + center][x + center] = getSumAround(y + center, x + center)
            }

            when (turnDirection) {
                0 -> x++ // east
                1 -> y-- // north
                2 -> x-- // west
                3 -> y++ // south
            }

            if (step == stepLength) {
                turnDirection = (turnDirection + 1) % 4
                if (turnDirection % 2 == 0) {
                    stepLength++
                }
                step = 1
            } else {
                step++
            }
            position++
        }
    }

    private fun getSumAround(y: Int, x: Int): Int {
        return memory[y - 1][x - 1] +
                memory[y - 1][x] +
                memory[y - 1][x + 1] +
                memory[y][x - 1] +
                memory[y][x + 1] +
                memory[y + 1][x - 1] +
                memory[y + 1][x] +
                memory[y + 1][x + 1]
    }

    fun manhattanDistance(location: Int): Int {
        memoryIndex.forEachIndexed { x, row ->
            row.forEachIndexed { y, element ->
                if (element == location) {
                    return Math.abs(x - center) + Math.abs(y - center)
                }
            }
        }
        return 0
    }

    fun getMemoryAt(location: Int): Int {
        memoryIndex.forEachIndexed { y, row ->
            //return memory[y][row.indexOf(location)]
            row.forEachIndexed { x, element ->
                if (element == location) {
                    return memory[y][x]
                }
            }
        }
        return 0
    }
}