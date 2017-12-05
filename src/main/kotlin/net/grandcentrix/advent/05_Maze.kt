package net.grandcentrix.advent

fun stepsToEscapeMaze(maze: MutableList<Int>): Int {
    var position = 0
    var steps = 0
    while (position < maze.size) {
        val instruction = maze[position]
        maze[position] = instruction + 1
        position += instruction
        steps++
    }
    return steps
}

fun stepsToEscapeAdvancedMaze(maze: MutableList<Int>): Int {
    var position = 0
    var steps = 0
    while (position < maze.size) {
        val instruction = maze[position]
        if (instruction >= 3) {
            maze[position] = instruction - 1
        } else {
            maze[position] = instruction + 1
        }
        position += instruction
        steps++
    }
    return steps
}
