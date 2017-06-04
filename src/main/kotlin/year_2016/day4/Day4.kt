package year_2016.day4

import loadInputAsLineList
import ordered

fun main(args: Array<String>) {
    val day4 = Day4()
    day4.partOne()
}

class Day4 {

    // aaaaa-bbb-z-y-x-123[abxyz]
    fun partOne() {
        val result = getInput().map {
            val id = it.substringAfterLast("-").substringBefore("[").toInt()
            val checksum = it.substringAfter("[").dropLast(1).ordered()
            val data = it.substring(0, it.indexOf("[") - 3).replace("-", "")
            val isRoom = data.groupBy { it }
                    .toList()
                    .sortedWith(compareByDescending<Pair<Char, List<Char>>> { it.second.size }.thenBy { it.first })
                    .take(5)
                    .map { it.first.toString() }
                    .reduce { acc, s -> acc + s }
                    .ordered() == checksum

            if (isRoom) id else 0
        }.sum()

        println(result)
    }

    private fun getInput(): List<String> {
        val filename = "day4.input"
        val filepath = "src/main/kotlin/year_2016/day4/" + filename
        return loadInputAsLineList(filename, filepath)
    }
}