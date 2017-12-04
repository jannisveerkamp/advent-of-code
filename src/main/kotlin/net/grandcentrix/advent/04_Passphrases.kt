package net.grandcentrix.advent

fun isPassphraseValid(passphrase: String): Boolean {
    val split= passphrase.split(" ")
    return split.size == split.distinct().size
}

fun isPassPhraseValidAnagram(passphrase: String): Boolean {
    return false
}
