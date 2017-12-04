package net.grandcentrix.advent

inline fun <T> Iterable<T>.compareItself(compare: (T, T) -> Boolean): Boolean {
    forEach { outer ->
        forEach { inner ->
            if (outer !== inner) {
                if (compare(outer, inner)) {
                    return false
                }
            }
        }
    }
    return true
}

fun isPassphraseValid(passphrase: String): Boolean {
    val split = passphrase.split(" ")
    return split.size == split.distinct().size
}

fun containsNoAnagram(passphrase: String): Boolean {
    return passphrase.split(" ").compareItself { s1, s2 ->
        s1.toList().sorted() == s2.toList().sorted()
    }
}
