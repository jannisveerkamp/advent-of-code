package common

fun lcm(numbers: List<Long>): Long {
    var result = 1L
    for (number in numbers) {
        result = lcm(result, number)
    }
    return result
}

fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}
