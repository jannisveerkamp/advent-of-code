package net.grandcentrix.advent

import kotlin.math.absoluteValue

data class Particle(var id: Int, var x: Long, var y: Long, var z: Long,
                    var vx: Long, var vy: Long, var vz: Long,
                    var ax: Long, var ay: Long, var az: Long) {
    fun distanceToZero() = x.absoluteValue + y.absoluteValue + z.absoluteValue
}

fun closestParticle(input: List<String>, withDestruction: Boolean = false): Pair<Int, Int> {
    val particles = input.mapIndexed { index, it -> parseParticle(index, it) }.toMutableList()

    repeat(1000) {
        particles.forEach {
            it.vx += it.ax
            it.vy += it.ay
            it.vz += it.az
            it.x += it.vx
            it.y += it.vy
            it.z += it.vz
        }

        if (withDestruction) {
            particles.forEach { outer ->
                particles.forEach { inner ->
                    if (outer !== inner && outer.x == inner.x && outer.y == inner.y && outer.z == inner.z) {
                        outer.id = -1
                        inner.id = -1
                    }
                }
            }
            particles.removeIf { it.id == -1 }
        }
    }
    // not 655
    return particles.minBy { it.distanceToZero() }!!.id to particles.size
}

fun parseParticle(position: Int, line: String): Particle {
    val elements = line.split(", ")
    val p = elements[0].substring(3, elements[0].length - 1).split(",").map { it.toLong() }
    val v = elements[1].substring(3, elements[1].length - 1).split(",").map { it.toLong() }
    val a = elements[2].substring(3, elements[2].length - 1).split(",").map { it.toLong() }
    return Particle(position, p[0], p[1], p[2], v[0], v[1], v[2], a[0], a[1], a[2])
}
