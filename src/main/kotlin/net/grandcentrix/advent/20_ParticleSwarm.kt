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
        particles.forEach {
            it.move()
        }

        if (withDestruction) {
            particles.forEach { outer ->
                particles.forEach { inner ->
                    if (outer !== inner && outer.collides(inner)) {
                        outer.id = -1
                        inner.id = -1
                    }
                }
            }
            particles.removeIf { it.id == -1 }
        }
    }
    return particles.minBy { it.distanceToZero() }!!.id to particles.size
}

fun parseParticle(position: Int, line: String): Particle {
    val elements = line.split(", ")
    val p = elements[0].substring(3, elements[0].length - 1).split(",").map { it.toLong() }
    val v = elements[1].substring(3, elements[1].length - 1).split(",").map { it.toLong() }
    val a = elements[2].substring(3, elements[2].length - 1).split(",").map { it.toLong() }
    return Particle(position, p[0], p[1], p[2], v[0], v[1], v[2], a[0], a[1], a[2])
}
