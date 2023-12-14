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
}