import kotlin.math.max

fun solveDay08a(input: String): Int {
    val forest = input.split("\n").map { line -> line.map { it.digitToInt() } }
    val rows = forest.size
    val columns = forest.first().size
    val visibleMatrix = Array(rows) { Array(columns) { false } }

    for (indexRow in 0 until rows) {
        var currentHeight = -1
        for (indexColumn in 0 until columns) {
            val field = forest[indexRow][indexColumn]
            if (field > currentHeight) {
                currentHeight = field
                visibleMatrix[indexRow][indexColumn] = true
            }
        }
    }

    for (indexRow in 0 until rows) {
        var currentHeight = -1
        for (indexColumn in columns - 1 downTo 0) {
            val field = forest[indexRow][indexColumn]
            if (field > currentHeight) {
                currentHeight = field
                visibleMatrix[indexRow][indexColumn] = true
            }
        }
    }

    for (indexColumn in 0 until columns) {
        var currentHeight = -1
        for (indexRow in 0 until rows) {
            val field = forest[indexRow][indexColumn]
            if (field > currentHeight) {
                currentHeight = field
                visibleMatrix[indexRow][indexColumn] = true
            }
        }
    }

    for (indexColumn in 0 until columns) {
        var currentHeight = -1
        for (indexRow in rows - 1 downTo 0) {
            val field = forest[indexRow][indexColumn]
            if (field > currentHeight) {
                currentHeight = field
                visibleMatrix[indexRow][indexColumn] = true
            }
        }
    }

    return visibleMatrix.sumOf { it.count { it } }
}

fun solveDay08b(input: String): Int {
    val forest = input.split("\n").map { line -> line.map { it.digitToInt() } }
    val rows = forest.size
    val columns = forest.first().size

    var maximum = 0
    for (indexRow in 1 until rows - 1) {
        for (indexColumn in 1 until columns - 1) {
            val field = forest[indexRow][indexColumn]

            var left = 0
            for (x in indexRow - 1 downTo 0) {
                left++
                if (forest[x][indexColumn] >= field) {
                    break
                }
            }

            var right = 0
            for (x in indexRow + 1 until rows) {
                right++
                if (forest[x][indexColumn] >= field) {
                    break
                }
            }

            var top = 0
            for (y in indexColumn - 1 downTo 0) {
                top++
                if (forest[indexRow][y] >= field) {
                    break
                }
            }

            var bottom = 0
            for (y in indexColumn + 1 until rows) {
                bottom++
                if (forest[indexRow][y] >= field) {
                    break
                }
            }
            maximum = max(left * right * top * bottom, maximum)
        }
    }

    return maximum
}

fun main() {
    val inputExample = readFile("day08_example.txt")
    val inputTask = readFile("day08.txt")

    println("Solution for task 1 example: ${solveDay08a(inputExample)}") // 21
    println("Solution for task 1 task:    ${solveDay08a(inputTask)}") // 1717
    println("Solution for task 2 example: ${solveDay08b(inputExample)}") // 8
    println("Solution for task 2 task:    ${solveDay08b(inputTask)}") // 321975
}

