package year_2016.day2

import loadInputAsLineList
import mapReduce

fun main(args: Array<String>) {
    val day2 = Day2()
    day2.partOne()
    day2.partTwo()
}

typealias Keypad = Array<Array<String>>

class Day2 {

    fun partOne() {
        val current = intArrayOf(1, 1)
        val keypad = arrayOf(
                arrayOf("1", "2", "3"),
                arrayOf("4", "5", "6"),
                arrayOf("7", "8", "9"))

        println(getResult(keypad, current))
    }

    fun partTwo() {
        val current = intArrayOf(2, 0)
        val keypad = arrayOf(
                arrayOf("0", "0", "1", "0", "0"),
                arrayOf("0", "2", "3", "4", "0"),
                arrayOf("5", "6", "7", "8", "9"),
                arrayOf("0", "A", "B", "C", "0"),
                arrayOf("0", "0", "D", "0", "0"))

        println(getResult(keypad, current))
    }

    private fun getResult(keypad: Keypad, current: IntArray): String {
        var p = current
        return getInput()
                .map {
                    it.mapReduce(p) { prev, v ->
                        var step = prev.copyOf()
                        when (v) {
                            'U' -> step[0] = prev[0] - 1
                            'D' -> step[0] = prev[0] + 1
                            'L' -> step[1] = prev[1] - 1
                            'R' -> step[1] = prev[1] + 1
                        }

                        if (step[0] < 0 || step[0] >= keypad.size || step[1] < 0 || step[1] >= keypad.size ||
                                keypad[step[0]][step[1]] == "0") step = prev
                        p = step
                        step
                    }
                }.map { keypad[it[0]][it[1]] }
                .joinToString("") { it }
    }


    fun getInput(): List<String> {
        val filename = "day2.input"
        val filepath = "src/main/kotlin/year_2016/day2/" + filename
        return loadInputAsLineList(filename, filepath)
    }
}
