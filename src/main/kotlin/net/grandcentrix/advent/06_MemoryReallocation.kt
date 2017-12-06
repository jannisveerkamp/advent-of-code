package net.grandcentrix.advent

fun reallocateBlockStepsTillStable(block: MutableList<Int>): Int {
    val seenBlocks = mutableListOf<List<Int>>()
    var steps = 0

    while (!seenBlocks.contains(block)) {
        //println(block)
        seenBlocks.add(block.toList())
        rearrangeBlock(block)
        steps++
    }

    return steps
}

fun reallocateBlockCycleSteps(block: MutableList<Int>): Int {
    val seenBlocks = mutableMapOf<Int, List<Int>>()
    var steps = 0

    while (!seenBlocks.containsValue(block)) {
        //println(block)
        seenBlocks.put(steps, block.toList())
        rearrangeBlock(block)
        steps++
    }

    var test = 0
    seenBlocks.forEach { key, value ->
        if (value == block) {
            test = steps - key
        }
    }
    return test
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


