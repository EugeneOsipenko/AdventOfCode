package year_2015.day5

import year_2015.util.loadInputAsLineList
import java.util.regex.Pattern

fun main(args: Array<String>) {
    val solution = Day5()
    solution.partOne()
    solution.partTwo()
}

class Day5 {

    fun partOne() {
        val notMatchRegex = Pattern.compile("^(?!.*(ab|cd|pq|xy)).*$")
        val doubleRegex = Pattern.compile("(.)\\1")
        val vowelPattern = Pattern.compile("(.*[aeiou]){3,}")

        val result = getInput()
                .filter {
                    doubleRegex.matcher(it).find()
                            && notMatchRegex.matcher(it).matches()
                            && vowelPattern.matcher(it).find()
                }
                .count()

        println(result)
    }

    fun partTwo() {
        val result = getInput()
                .filter { doesHavePairBetweenLetter(it) && doesHavePairs(it) }
                .count()

        println(result)
    }

    fun doesHavePairBetweenLetter(input: String): Boolean {
        var lastLetter = Char.MIN_SURROGATE
        var beforeLastLetter = Char.MIN_SURROGATE

        input.forEach loop@ {
            if (lastLetter == Char.MIN_SURROGATE) {
                lastLetter = it
                return@loop
            }

            if (beforeLastLetter != Char.MIN_SURROGATE && beforeLastLetter == it) {
                return true
            }

            beforeLastLetter = lastLetter
            lastLetter = it
        }

        return false
    }

    fun doesHavePairs(input: String): Boolean {
        var count = 0

        for (i in 1..input.length) {
            if (i + 1 > input.length) break
            val pair = input.substring(i - 1, i + 1)



            (i + 1..input.length)
                    .takeWhile { it + 2 <= input.length }
                    .map { input.substring(it, it + 2) }
                    .filter { pair == it }
                    .forEach { count++ }
        }

        return count >= 1
    }

    fun getInput(): List<String> {
        val fileName = "day5.input"
        val filePath = "src/main/kotlin/day5/" + fileName
        return loadInputAsLineList(fileName, filePath)
    }
}
