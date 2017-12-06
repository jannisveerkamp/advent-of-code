package net.grandcentrix.advent

fun reallocateBlockStepsTillStable(block: MutableList<Int>): Int {
    return reallocateBlock(block) { _, steps -> steps }
}

fun reallocateBlockCycleSteps(block: MutableList<Int>): Int {
    return reallocateBlock(block) { seenBlocks, steps ->
        var stepsSince = 0
        seenBlocks.forEach { stepSeen, value ->
            if (value == block) {
                stepsSince = steps - stepSeen
            }
        }
        stepsSince
    }
}

fun reallocateBlock(block: MutableList<Int>, stepsSince: (Map<Int, List<Int>>, Int) -> Int): Int {
    val seenBlocks = mutableMapOf<Int, List<Int>>()
    var steps = 0

    while (!seenBlocks.containsValue(block)) {
        //println(block)
        seenBlocks.put(steps, block.toList())
        rearrangeBlock(block)
        steps++
    }
    return stepsSince(seenBlocks, steps)
}

fun rearrangeBlock(block: MutableList<Int>) {
    var maxValue = block.max()!!
    var maxIndex = block.indexOf(maxValue)

    block[maxIndex] = 0
    while (maxValue > 0) {
        maxIndex++
        block[maxIndex % block.size]++
        maxValue--
    }
}


