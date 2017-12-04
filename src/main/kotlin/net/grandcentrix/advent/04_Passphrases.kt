package net.grandcentrix.advent

fun isPassphraseValid(passphrase: String): Boolean {
    val split = passphrase.split(" ")
    return split.size == split.distinct().size
}

fun containsNoAnagram(passphrase: String): Boolean {
    val test = passphrase.split(" ")
    return !test.any { outer ->
        test.any { inner ->
            inner !== outer && inner.toList().sorted() == outer.toList().sorted()
        }
    }
}
