package util

import java.math.BigInteger
import java.nio.file.Path
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: Int, isTest: Boolean = false) : List<String> {
    val fileName = "Day$day${ if (isTest) "_test" else ""}.txt"

    return Path("src/resources/$fileName").readLines()
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

object InputReader {

    fun getInputAsString(day: Int, isTest: Boolean = false): String {
        return fromResources(day, isTest).readText()
    }

    fun getInputAsList(day: Int, isTest: Boolean = false): List<String> {
        return fromResources(day, isTest).readLines()
    }

    private fun fromResources(day: Int, isTest: Boolean = false): Path {
        val fileName = "Day$day${ if (isTest) "_test" else ""}.txt"
        return Path("src/resources/$fileName")
    }
}