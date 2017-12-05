package net.grandcentrix.advent

fun stepsToEscapeMaze(maze: MutableList<Int>): Int = stepsToEscapeMaze(maze) {
    step -> step + 1
}

fun stepsToEscapeAdvancedMaze(maze: MutableList<Int>): Int = stepsToEscapeMaze(maze) {
    step -> if (step >= 3) step - 1 else step + 1
}

private fun stepsToEscapeMaze(maze: MutableList<Int>, stepChange: (Int) -> Int): Int {
    var position = 0
    var steps = 0
    while (position < maze.size) {
        val instruction = maze[position]
        maze[position] = stepChange(maze[position])
        position += instruction
        steps++
    }
    return steps
}
