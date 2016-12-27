package day4

import java.security.MessageDigest
import javax.xml.bind.annotation.adapters.HexBinaryAdapter

/**
 * http://adventofcode.com/2015/day/4
 */
fun main(args: Array<String>) {
    partOne()
    partTwo()
}

fun partOne() {
    println(findLowestNumberToMatch("iwrupvqb", "00000"))
}

fun partTwo() {
    println(findLowestNumberToMatch("iwrupvqb", "000000"))
}

fun findLowestNumberToMatch(secret: String, prefix: String): Int {
    val md5 = MessageDigest.getInstance("MD5")
    for (i in 0..Int.MAX_VALUE)  {
        if (md5.digestString(secret + i).startsWith(prefix)) {
            return i
        }
    }

    throw IllegalStateException("Number not found")
}

fun MessageDigest.digestString(s: String): String = HexBinaryAdapter().marshal(digest(s.toByteArray()))
