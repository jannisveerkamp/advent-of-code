package common

class Node<T>(val value: T, var parent: Node<T>?, var children: MutableList<Node<T>> = mutableListOf()) {

    fun addChild(child: Node<T>) {
        children.add(child)
    }

    override fun toString(): String {
        return "Node(value=$value, parent=${parent?.value}, children=$children)"
    }
}