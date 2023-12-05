data class Instruction(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
    val sourceRange: LongRange = sourceRangeStart until (sourceRangeStart + rangeLength)
    val destinationRange: LongRange = destinationRangeStart until (destinationRangeStart + rangeLength)
    val delta: Long = destinationRangeStart - sourceRangeStart
}

class Almanac(val seeds: List<Long>, val instructions: List<List<Instruction>>) {
}

fun parseAlmanac(input: String): Almanac {
    val almanac = input.split("\n\n")
    val seeds = almanac.first().removePrefix("seeds: ").split(" ").map { it.toLong() }
    val instructions = almanac.drop(1).map { instruction ->
        instruction.split("\n").drop(1).map { line ->
            val instructions = line.split(" ").map { number ->
                number.toLong()
            }
            Instruction(instructions[0], instructions[1], instructions[2])
        }
    }
    return Almanac(seeds, instructions)
}

private fun solveDay05a(input: String): Long {
    val almanac = parseAlmanac(input)

    return almanac.seeds.minOf { seed ->
        var currentValue = seed
        almanac.instructions.forEach { instructions ->
            val instruction = instructions.firstOrNull { instruction ->
                currentValue in instruction.sourceRange
            }
            if (instruction != null) {
                currentValue += instruction.delta
            }
        }
        currentValue
    }
}

private fun solveDay05b(input: String): Long {
    val almanac = parseAlmanac(input)
    val seeds = almanac.seeds.chunked(2).map { it[0]..(it[0] + it[1]) }
    return seeds.minOf { seedRange ->
        seedRange.minOf { seed ->
            var currentValue = seed
            almanac.instructions.forEach { instructions ->
                val instruction = instructions.firstOrNull { instruction ->
                    currentValue in instruction.sourceRange
                }
                if (instruction != null) {
                    currentValue += instruction.delta
                }
            }
            currentValue
        }

    }
}

fun main() {
    val inputExample = readFile("day05_example.txt")
    val inputTask = readFile("day05.txt")

    println("Solution for task 1 example: ${solveDay05a(inputExample)}") // 35
    println("Solution for task 1 task:    ${solveDay05a(inputTask)}") // 226172555
    println("Solution for task 2 example: ${solveDay05b(inputExample)}") // 46
    println("Solution for task 2 task:    ${solveDay05b(inputTask)}") // 47909639, takes 20 minutes :-D
}

