import kotlin.math.abs
import kotlin.math.sign

private sealed interface Movement {
    val steps: Int

    data class Left(override val steps: Int) : Movement
    data class Right(override val steps: Int) : Movement
    data class Up(override val steps: Int) : Movement
    data class Down(override val steps: Int) : Movement

    companion object {
        fun fromString(input: String): Movement {
            return when (input.first()) {
                'L' -> Left(input.drop(2).toInt())
                'R' -> Right(input.drop(2).toInt())
                'U' -> Up(input.drop(2).toInt())
                'D' -> Down(input.drop(2).toInt())
                else -> error("Unknown input: $input")
            }
        }
    }
}

private data class Point(val x: Int, val y: Int) {

    fun move(movement: Movement): Point = when (movement) {
        is Movement.Left -> copy(x = x - 1)
        is Movement.Right -> copy(x = x + 1)
        is Movement.Up -> copy(y = y + 1)
        is Movement.Down -> copy(y = y - 1)
    }

    fun follow(head: Point): Point {
        return if (abs(x - head.x) > 1 || abs(y - head.y) > 1) {
            Point(x + (head.x - x).sign, y + (head.y - y).sign)
        } else {
            this
        }
    }
}

fun solveDay09(input: String, length: Int): Int {
    val movements = input.split("\n").map { Movement.fromString(it) }
    val rope = Array(length) { Point(0, 0) }

    val visitedPointsByTail = mutableSetOf<Point>()
    movements.forEach { movement ->
        repeat(movement.steps) {
            rope[0] = rope[0].move(movement)
            (1 until rope.size).forEach { i ->
                rope[i] = rope[i].follow(rope[i - 1])
            }
            visitedPointsByTail += rope.last()
        }
    }

    return visitedPointsByTail.size
}

fun main() {
    val inputExample = readFile("day09_example.txt")
    val inputExampleLarger = readFile("day09_example_larger.txt")
    val inputTask = readFile("day09.txt")

    println("Solution for task 1 example: ${solveDay09(inputExample, 2)}") // 13
    println("Solution for task 1 task:    ${solveDay09(inputTask, 2)}") // 6284
    println("Solution for task 2 example: ${solveDay09(inputExampleLarger, 10)}") // 36
    println("Solution for task 2 task:    ${solveDay09(inputTask, 10)}") // 2661
}

