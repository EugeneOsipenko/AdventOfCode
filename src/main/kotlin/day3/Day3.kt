package day3


import util.loadInputAsLines
import java.util.*

/**
 * http://adventofcode.com/2015/day/3
 */
fun main(args: Array<String>) {
    val solution = Day3()
    solution.partOne()
    solution.partTwo()
}

class Day3 {

    fun partOne() {
        var currentPos = Point(0, 0)
        val points = HashSet<Point>()

        points.add(currentPos)
        getInput().toCharArray().map({ convertPath(it) }).forEach {
            currentPos += it
            points.add(currentPos)
        }
        println(points.size)
    }

    fun partTwo() {
        var santaPos = Point(0, 0)
        val santaPoints = HashSet<Point>()
        santaPoints.add(santaPos)

        var robotPos = Point(0, 0)
        val robotPoints = HashSet<Point>()
        robotPoints.add(robotPos)

        getInput().toCharArray().map({ convertPath(it) }).forEachIndexed { i, point ->
            if (i % 2 == 0) {
                santaPos += point
                santaPoints.add(santaPos)
            } else {
                robotPos += point
                robotPoints.add(robotPos)
            }
        }

        santaPoints.addAll(robotPoints)
        println(santaPoints.size)
    }

    fun convertPath(c: Char): Point = when (c) {
        '^' -> Point(0, 1)
        'v' -> Point(0, -1)
        '<' -> Point(-1, 0)
        '>' -> Point(1, 0)
        else -> throw IllegalStateException("Unknown direction")
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(p: Point): Point = Point(x + p.x, y + p.y)
    }

    fun getInput(): String {
        val filename = "day3.input"
        val filepath = "src/main/kotlin/day3/" + filename
        return loadInputAsLines(filename, filepath)
    }
}


