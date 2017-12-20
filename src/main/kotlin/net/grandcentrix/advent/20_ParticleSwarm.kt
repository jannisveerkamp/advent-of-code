package net.grandcentrix.advent

import kotlin.math.absoluteValue

data class Particle(val id: Int, var x: Long, var y: Long, var z: Long,
                    var vx: Long, var vy: Long, var vz: Long,
                    var ax: Long, var ay: Long, var az: Long) {
    fun distanceToZero() = x.absoluteValue + y.absoluteValue + z.absoluteValue
}

fun closestParticle(input: List<String>): Int {
    val particles = input.mapIndexed { index, it -> parseParticle(index, it) }

    repeat(1000) {
        particles.forEach {
            it.vx += it.ax
            it.vy += it.ay
            it.vz += it.az
            it.x += it.vx
            it.y += it.vy
            it.z += it.vz
        }
    }

    return particles.minBy { it.distanceToZero() }!!.id
}

fun closestParticleWithDestruction(input: List<String>): Int {
    return -1
}

fun parseParticle(position: Int, line: String): Particle {
    val elements = line.split(", ")
    val p = elements[0].substring(3, elements[0].length - 1).split(",").map { it.toLong() }
    val v = elements[1].substring(3, elements[1].length - 1).split(",").map { it.toLong() }
    val a = elements[2].substring(3, elements[2].length - 1).split(",").map { it.toLong() }
    return Particle(position, p[0], p[1], p[2], v[0], v[1], v[2], a[0], a[1], a[2])
}
