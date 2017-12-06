package net.grandcentrix.advent

fun reallocateBlockStepsTillStable(block: MutableList<Int>): Int {
    return reallocateBlock(block) { _, steps -> steps }
}

fun reallocateBlockCycleSteps(block: MutableList<Int>): Int {
    return reallocateBlock(block) { seenBlocks, steps ->
        steps - seenBlocks.filterValues { it == block }.keys.first()
    }
}

fun reallocateBlock(block: MutableList<Int>, stepsSince: (Map<Int, List<Int>>, Int) -> Int): Int {
    val seenBlocks = mutableMapOf<Int, List<Int>>()
    var steps = 0

    while (!seenBlocks.containsValue(block)) {
        seenBlocks.put(steps++, block.toList())
        rearrangeBlock(block)
    }
    return stepsSince(seenBlocks, steps)
}

fun rearrangeBlock(block: MutableList<Int>) {
    var maxValue = block.max()!!
    var maxIndex = block.indexOf(maxValue)
    block[maxIndex] = 0

    while (maxValue > 0) {
        maxValue--
        maxIndex++
        block[maxIndex % block.size]++
    }
}
