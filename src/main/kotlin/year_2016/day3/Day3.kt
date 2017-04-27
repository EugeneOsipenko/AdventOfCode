package year_2016.day3

import loadInputAsLineList
import mapGrouped

fun main(args: Array<String>) {
    val day3 = Day3()
    day3.partOne()
    day3.partTwo()
}

class Day3 {

    fun partOne() {
        val result = getInput()
                .map {
                    val a = it.substring(0, 5).trim().toInt()
                    val b = it.substring(5, 10).trim().toInt()
                    val c = it.substring(10, it.length).trim().toInt()
                    if (a + b > c && b + c > a && c + a > b) 1 else 0
                }.sum()

        println(result)
    }

    fun partTwo() {
        val result = getInput().mapGrouped(3) { f, s, t ->
            fun isT(part: IntRange): Int {
                val a = f.substring(part).trim().toInt()
                val b = s.substring(part).trim().toInt()
                val c = t.substring(part).trim().toInt()
                return if (a + b > c && b + c > a && c + a > b) 1 else 0
            }

            isT(0..4) + isT(5..9) + isT(10..14)
        }.sum()

        println(result)
    }

    private fun getInput(): List<String> {
        val filename = "day3.input"
        val filepath = "src/main/kotlin/year_2016/day3/" + filename
        return loadInputAsLineList(filename, filepath)
    }
}
