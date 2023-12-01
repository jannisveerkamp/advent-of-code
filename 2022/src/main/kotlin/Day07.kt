import common.Node

sealed interface Command {
    object GotoRoot : Command
    object GotoParent : Command
    object Ls : Command
    data class GotoDir(val name: String) : Command
    data class Dir(val name: String) : Command
    data class File(val name: String, val size: Int) : Command

    companion object {
        fun from(input: String): Command {
            return when {
                input == "\$ cd /" -> GotoRoot
                input == "\$ cd .." -> GotoParent
                input == "\$ ls" -> Ls
                input.startsWith("\$ cd ") -> GotoDir(input.substring(5))
                input.startsWith("dir ") -> Dir(input.substring(4))
                else -> {
                    val (size, name) = input.split(" ")
                    File(name, size.toInt())
                }
            }
        }
    }
}

sealed interface FileType {
    data class Dir(val name: String) : FileType
    data class File(val name: String, val size: Int) : FileType
}

private fun Node<FileType>.size(): Int = when (value) {
    is FileType.Dir -> children.sumOf { it.size() }
    is FileType.File -> value.size
}

private fun Node<FileType>.dirSizes(): List<Int> = when (value) {
    is FileType.Dir -> children.flatMap { it.dirSizes() } + size()
    is FileType.File -> emptyList()
}

private fun parseFileSystem(input: String): Node<FileType> {
    val commands = input.split("\n").map { Command.from(it) }
    val root = Node<FileType>(FileType.Dir("/"), null)
    lateinit var currentNode: Node<FileType>

    // Build the tree
    commands.forEach { command ->
        when (command) {
            is Command.GotoRoot -> currentNode = root
            is Command.GotoParent -> currentNode = currentNode.parent!!
            is Command.GotoDir -> {
                val dir = currentNode.children.firstOrNull { (it.value as? FileType.Dir)?.name == command.name }
                currentNode = if (dir == null) {
                    val child = Node(FileType.Dir(command.name), currentNode)
                    currentNode.addChild(child)
                    child
                } else {
                    dir
                }
            }
            is Command.Dir -> currentNode.addChild(Node(FileType.Dir(command.name), currentNode))
            is Command.File -> currentNode.addChild(Node(FileType.File(command.name, command.size), currentNode))
            is Command.Ls -> {}
        }
    }
    return root
}

private const val SMALL_SIZE_LIMIT = 100_000
private const val AVAILABLE_SPACE = 70_000_000
private const val UNUSED_SPACE_NEEDED = 30_000_000

fun solveDay07a(input: String): Int {
    val root = parseFileSystem(input)
    return root.dirSizes().filter { it <= SMALL_SIZE_LIMIT  }.sum()
}

fun solveDay07b(input: String): Int {
    val root = parseFileSystem(input)
    val spaceOccupied = root.size()
    return root.dirSizes().filter { dirSize -> spaceOccupied - dirSize <= AVAILABLE_SPACE - UNUSED_SPACE_NEEDED }.min()
}

fun main() {
    val inputExample = readFile("day07_example.txt")
    val inputTask = readFile("day07.txt")

    println("Solution for task 1 example: ${solveDay07a(inputExample)}") // 95437
    println("Solution for task 1 task:    ${solveDay07a(inputTask)}")    // 1348005
    println("Solution for task 2 example: ${solveDay07b(inputExample)}") // 24933642
    println("Solution for task 2 task:    ${solveDay07b(inputTask)}")    // 12785886
}

