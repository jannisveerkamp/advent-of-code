package net.grandcentrix.advent

import kotlin.math.absoluteValue

data class Particle(var id: Int, private var x: Long, private var y: Long, private var z: Long,
                    private var vx: Long, private var vy: Long, private var vz: Long,
                    private var ax: Long, private var ay: Long, private var az: Long) {

    fun move() {
        vx += ax
        vy += ay
        vz += az
        x += vx
        y += vy
        z += vz
    }

    fun distanceToZero() = x.absoluteValue + y.absoluteValue + z.absoluteValue

    fun collides(particle: Particle) = x == particle.x && y == particle.y && z == particle.z
}

fun closestParticle(input: List<String>, withDestruction: Boolean = false): Pair<Int, Int> {
    val particles = input.mapIndexed { index, it -> parseParticle(index, it) }.toMutableList()

    repeat(500) {
        particles.forEach { it.move() }

        if (withDestruction) {
            particles.removeIf { particle ->
                particles.any { particle !== it && particle.collides(it) }
            }
        }
    }
    return particles.minBy { it.distanceToZero() }!!.id to particles.size
}

fun parseParticle(position: Int, input: String): Particle {
    val pva = input.split(", ").map { it.substring(3, it.length - 1).split(",").map { it.toLong() } }.flatten()
    return Particle(position, pva[0], pva[1], pva[2], pva[3], pva[4], pva[5], pva[6], pva[7], pva[8])
}
