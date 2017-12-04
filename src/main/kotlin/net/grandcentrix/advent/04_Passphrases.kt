package net.grandcentrix.advent

fun isPassphraseValid(passphrase: String): Boolean {
    val split = passphrase.split(" ")
    return split.size == split.distinct().size
}

fun containsNoAnagram(passphrase: String): Boolean {
    val split = passphrase.split(" ")

    split.forEach { outer ->
        split.forEach { inner ->
            if (outer !== inner) {
                if (isAnagram(outer, inner)) {
                    return false
                }
            }
        }
    }

    return true
}

fun isAnagram(s1: String, s2: String): Boolean {
    if (s1.length != s2.length) {
        return false
    }
    val table = s2.toMutableList()

    for (char in s1) {
        if (!table.remove(char)) {
            return false
        }
    }

    return table.isEmpty()
}
