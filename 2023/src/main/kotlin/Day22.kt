data class Brick(val x: IntRange, val y: IntRange, val z: IntRange) {
    companion object {
        fun fromString(input: String): List<Brick> = input.split("\n").map { line ->
            val (from, to) = line.split("~").map { it.split(",").map(String::toInt) }
            Brick(IntRange(from[0], to[0]), IntRange(from[1], to[1]), IntRange(from[2], to[2]))
        }
    }
}

private fun fallDown(bricks: List<Brick>): Pair<List<Brick>, Array<Array<Array<Boolean>>>> {
    val sortedBricks = bricks.sortedBy { it.z.first() }
    val cube = Array(bricks.maxOf { it.x.last() } + 1) {
        Array(bricks.maxOf { it.y.last() } + 1) {
            Array(bricks.maxOf { it.z.last() } + 1) {
                false
            }
        }
    }
    val fallenBricks = sortedBricks.map { brick ->
        var zOffset = 0
        while (brick.z.first() + zOffset > 1) {
//            println("can fall")
            val blockedBelow = brick.x.any { x ->
                brick.y.any { y ->
                    brick.z.any { z ->
                        cube[x][y][z + zOffset - 1]
                    }
                }
            }
            if (blockedBelow) {
                break
            } else {
                zOffset--
            }
        }
        brick.x.forEach { x ->
            brick.y.forEach { y ->
                brick.z.forEach { z ->
                    cube[x][y][z + zOffset] = true
                }
            }
        }
        brick.copy(z = IntRange(brick.z.first() + zOffset, brick.z.last() + zOffset))
    }
    return fallenBricks to cube
}


private fun solveDay22a(input: String): Int {
    val bricks = Brick.fromString(input).sortedBy { it.z.first }

    val (fallenBricks, cube) = fallDown(bricks)

    return fallenBricks.count { brick ->
        val disintegradted = fallenBricks.minus(brick)
        val (newFallenBricks, cube) = fallDown(disintegradted)
        newFallenBricks.minus(disintegradted.toSet()).isEmpty() && disintegradted.minus(newFallenBricks.toSet()).isEmpty()
    }
}

private fun solveDay22b(input: String): Int {
    return 0
}

fun main() {
    val inputExample = readFile("day22_example.txt")
    val inputTask = readFile("day22.txt")

    println("Solution for task 1 example: ${solveDay22a(inputExample)}") // 5
    println("Solution for task 1 task:    ${solveDay22a(inputTask)}") // 393
    println("Solution for task 1 example: ${solveDay22b(inputExample)}") // 7
    println("Solution for task 1 task:    ${solveDay22b(inputTask)}") // ???
}

