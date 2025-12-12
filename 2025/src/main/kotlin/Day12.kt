private data class Shape(val shape: List<List<Boolean>>) {
    val tiles: Int = shape.sumOf { it.count { it } }
}

private data class Region(val width: Int, val height: Int, val shapeQuantities: List<Int>) {
    val area = width * height
}

private fun parseDay12(input: String): Pair<List<Shape>, List<Region>> {
    val segments = input.split("\n\n")
    val tiles = segments.dropLast(1).map { tiles ->
        Shape(tiles.lines().drop(1).map { lines -> lines.toCharArray().map { it == '#' }.toList() })
    }
    val regions = segments.last().lines().map { region ->
        val (wh, quantities) = region.split(": ")
        val (width, height) = wh.split("x").map { it.toInt() }
        Region(width, height, quantities.split(" ").map { it.toInt() })
    }
    return tiles to regions
}

private fun solveDay12a(input: String): Int {
    val (tiles, regions) = parseDay12(input)
    return regions.count { region ->
        val spaceNeeded = region.shapeQuantities.mapIndexed { index, quantity -> tiles[index].tiles * quantity }.sum()
        spaceNeeded <= region.area
    }
}

private fun solveDay12b(): String = "No second part :("

fun main() {
    val inputExample = readFile("day12_example.txt")
    val inputTask = readFile(filename = "day12.txt")

    println("Solution for task 1 example: ${solveDay12a(inputExample)}") // 2? 3?
    println("Solution for task 1 task:    ${solveDay12a(inputTask)}") // 499
    println("Solution for task 2 example: ${solveDay12b()}")
    println("Solution for task 2 task:    ${solveDay12b()}")
}

