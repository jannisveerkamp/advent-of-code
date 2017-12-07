package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class RecursiveCircusTest {

    private val simpleTestInputValidation = mutableMapOf(
            "pbga" to 66 children emptyList(),
            "xhth" to 57 children emptyList(),
            "ebii" to 61 children emptyList(),
            "havc" to 66 children emptyList(),
            "ktlj" to 57 children emptyList(),
            "fwft" to 72 children listOf("ktlj", "cntj", "xhth"),
            "qoyq" to 66 children emptyList(),
            "padx" to 45 children listOf("pbga", "havc", "qoyq"),
            "tknk" to 41 children listOf("ugml", "padx", "fwft"),
            "jptl" to 61 children emptyList(),
            "ugml" to 68 children listOf("gyxo", "ebii", "jptl"),
            "gyxo" to 61 children emptyList(),
            "cntj" to 57 children emptyList()
    )

    private val simpleTestInput = parseCircusInput("07_recursive_circus_input_simple.txt")

    private val testInput = parseCircusInput("07_recursive_circus_input.txt")

    @Test
    fun `verify test input parsing equals simpleTestInput2`() {
        assertThat(simpleTestInput).isEqualTo(simpleTestInputValidation)
    }

    @Test
    fun `testInput has the root Element "rqwgj"`() {
        assertThat(buildTree(testInput).value.name).isEqualTo("rqwgj")
    }

    @Test
    fun `simpleTestInput has the root Element "tknk"`() {
        assertThat(buildTree(simpleTestInput).value.name).isEqualTo("tknk")
    }
}