package common

enum class Direction {
    N,
    S,
    W,
    E;

    fun toPoint(): Point = when (this) {
        N -> Point(0, -1)
        S -> Point(0, 1)
        W -> Point(-1, 0)
        E -> Point(1, 0)
    }

    fun turnRight(): Direction = when(this) {
        N -> E
        S -> W
        W -> N
        E -> S
    }

    fun turnLeft(): Direction = when(this) {
        N -> W
        S -> E
        W -> S
        E -> N
    }
}