package net.grandcentrix.advent

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class RecursiveCircusTest {

    private val simpleTestInputValidation = mutableListOf(
            CircusProgram("pbga", 66, emptyList()),
            CircusProgram("xhth", 57,  emptyList()),
            CircusProgram("ebii", 61,  emptyList()),
            CircusProgram("havc", 66,  emptyList()),
            CircusProgram("ktlj", 57,  emptyList()),
            CircusProgram("fwft", 72,  listOf("ktlj", "cntj", "xhth")),
            CircusProgram("qoyq", 66,  emptyList()),
            CircusProgram("padx", 45,  listOf("pbga", "havc", "qoyq")),
            CircusProgram("tknk", 41,  listOf("ugml", "padx", "fwft")),
            CircusProgram("jptl", 61,  emptyList()),
            CircusProgram("ugml", 68,  listOf("gyxo", "ebii", "jptl")),
            CircusProgram("gyxo", 61,  emptyList()),
            CircusProgram("cntj", 57,  emptyList())
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

    @Test
    fun `value to balance tower for testInput is 333"`() {
        assertThat(valueToBalance(buildTree(testInput))).isEqualTo(333)
    }

    @Test
    fun `value to balance tower for simpleTestInput is 60"`() {
        assertThat(valueToBalance(buildTree(simpleTestInput))).isEqualTo(60)
    }
}