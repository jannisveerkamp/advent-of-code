package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class ParticleSwarmTest {

    private val simpleTestInput = listOf(
            "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>",
            "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>"
    )

    private val testInput = linesFromResource("20_particle_swarm_input.txt")

    @Test
    fun `Test particle parsing`() {
        assertThat(parseParticle("p=<1,2,3>, v=<-1,-2,-3>, a=<5,-6,7>"))
                .isEqualTo(Particle(1, 2, 3, -1, -2, -3, 5, -6, 7))
    }

    @Test
    fun `Closest particle to (0,0,0) for simpleTestInput in the long run is #0`() {
        assertThat(closestParticle(simpleTestInput)).isEqualTo(0)
    }

    @Test
    fun `Closest particle to (0,0,0) for testInput in the long run is #0`() {
        assertThat(closestParticle(testInput)).isEqualTo(0)
    }
}
