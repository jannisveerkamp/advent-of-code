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
                if (outer.toList().sorted() == inner.toList().sorted()) {
                    return false
                }
            }
        }
    }

    return true
}
