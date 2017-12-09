package net.grandcentrix.advent

fun scoreForInput(input: String): Int {
    var currentScore = 0
    var sum = 0
    removeGarbage(input).forEach {
        if (it == '{') {
            currentScore++
        } else if(it == '}') {
            sum += currentScore
            currentScore--
        }
    }


    return sum
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
