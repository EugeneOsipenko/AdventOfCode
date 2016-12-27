package day2

import util.loadInputAsLineList

/**
 * http://adventofcode.com/2015/day/2
 */
fun main(args: Array<String>) {
    partOne()
    partTwo()
}

private fun partOne() {
    val result = getInput().map(::createBox).sumBy(Box::getRequiredSurface)
    println(result)
}

private fun partTwo() {
    val result = getInput().map(::createBox).sumBy(Box::getRequiredRibbon)
    println(result)
}

class Box(val length: Int, val width: Int, val height: Int) {
    val topBottom = length * width
    val leftRight = width * height
    val faceBack = height * length

    fun getRequiredSurface(): Int {
        val smallestSide = Math.min(topBottom, Math.min(leftRight, faceBack))
        return 2 * topBottom + 2 * leftRight + 2 * faceBack + smallestSide
    }

    fun getRequiredRibbon(): Int {
        val list = mutableListOf(length, width, height)
        return list.multiply() + list.dropMax().map { it + it }.sum()
    }
}

private fun MutableList<Int>.dropMax(): MutableList<Int> {
    var max = Int.MIN_VALUE
    var index = Int.MIN_VALUE
    forEachIndexed { i, v ->
        if (v > max) {
            max = v
            index = i
        }
    }
    removeAt(index)
    return this
}

private fun List<Int>.multiply(): Int {
    var result: Int = 1
    for (element in this) {
        result *= element
    }
    return result
}

private fun createBox(s: String): Box {
    val dimens = s.split("x")
    return Box(dimens[0].toInt(), dimens[1].toInt(), dimens[2].toInt())
}

private fun getInput(): List<String> {
    val filename = "day2.input"
    val filepath = "src/main/kotlin/day2/" + filename
    return loadInputAsLineList(filename, filepath)
}