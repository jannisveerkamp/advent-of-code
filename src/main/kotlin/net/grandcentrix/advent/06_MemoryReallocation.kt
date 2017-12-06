package net.grandcentrix.advent

fun reallocateBlockStepsTillStable(block: MutableList<Int>): Int {
    val seenBlocks = mutableListOf<List<Int>>()
    var steps = 0

    while (!seenBlocks.contains(block)) {
        println(block)
        seenBlocks.add(block.toList())
        rearrangeBlock(block)
        steps++
    }

    return steps
}

fun reallocateBlockCycleSteps(block: MutableList<Int>): Int {
    return -1
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


