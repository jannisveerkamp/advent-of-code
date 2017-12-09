package net.grandcentrix.advent

fun scoreForInput(input: String): Int {
    return -1
}

fun removeGarbage(input: String): String {
    var wrapper = input

    // Remove ignored characters
    while (wrapper.contains("!")) {
        val exl = wrapper.indexOf("!")
        wrapper = wrapper.removeRange(exl, exl + 2)
    }

    // Remove garbage
    while (wrapper.contains("<")) {
        wrapper = wrapper.removeRange(wrapper.indexOf("<"), wrapper.indexOf(">") + 1)
    }

    return wrapper
}

fun groupCount(input: String): Int {
    return -1
}

