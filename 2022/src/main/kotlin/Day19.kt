import kotlin.math.max

private fun timeForResources(cost: IntArray, resources: IntArray, robots: IntArray): Int {
    var maxTime = 0
    for (i in 0..2) {
        if (cost[i] == 0) continue
        if (cost[i] > 0 && robots[i] == 0) return Int.MAX_VALUE
        maxTime = max(maxTime, (cost[i] - resources[i] - 1 + robots[i]) / robots[i])
    }
    return maxTime
}

fun solveDay19a(input: String, minutes: Int, maxBlueprints: Int): Int {
    val bluePrints = input.split("\n").map { line ->
        val numbers = common.parseNumbers(line)
        arrayOf(
            intArrayOf(numbers[1], 0, 0),
            intArrayOf(numbers[2], 0, 0),
            intArrayOf(numbers[3], numbers[4], 0),
            intArrayOf(numbers[5], 0, numbers[6])
        )
    }.take(maxBlueprints)
    var output = 0
    var outputSecond = 1

    bluePrints.forEachIndexed { index, bluePrint ->
        var maxGeodes = 0

        fun findQuality(blueprint: Array<IntArray>, resources: IntArray, robots: IntArray, time: Int): Int {
            if ((time * time - time) / 2 + robots[3] * time <= maxGeodes - resources[3]) return 0
            var wait: Int
            var max = resources[3] + robots[3] * time
            var resourceCopy: IntArray
            var robotCopy: IntArray
            for (i in 0..3) {
                wait = timeForResources(blueprint[i], resources, robots) + 1
                if (time - wait < 1) continue
                resourceCopy = resources.clone()
                robotCopy = robots.clone()
                for (j in 0..3) resourceCopy[j] += robots[j] * wait
                for (j in 0..2) resourceCopy[j] -= blueprint[i][j]
                robotCopy[i]++
                max = max(max, findQuality(blueprint, resourceCopy, robotCopy, time - wait))
            }
            maxGeodes = max(maxGeodes, max)
            return max
        }

        findQuality(bluePrint, intArrayOf(0, 0, 0, 0), intArrayOf(1, 0, 0, 0), minutes)
        output += (index + 1) * maxGeodes
        outputSecond *= maxGeodes
        maxGeodes = 0
    }

    return if (maxBlueprints == 3) outputSecond else output
}

fun main() {
    val inputExample = readFile("day19_example.txt")
    val inputTask = readFile("day19.txt")

    println("Solution for task 1 example: ${solveDay19a(inputExample, 24, 30)}") // 33
    println("Solution for task 1 task:    ${solveDay19a(inputTask, 24, 30)}") // 1703
    println("Solution for task 2 example: ${solveDay19a(inputExample, 32, 3)}") // 3472
    println("Solution for task 2 task:    ${solveDay19a(inputTask, 32, 3)}") // 5301
}

