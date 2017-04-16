package year_2016.day1

import applyOn
import java.util.*

fun main(args: Array<String>) {
    val day1 = Day1()
    day1.partOne()
    day1.partTwo()
}

class Day1 {

    fun partOne() {
        val result = INPUT.split(", ")
                .map { Point(if (it[0] == 'R') 1 else -1, it.drop(1).toInt()) }
                .applyOn(0) { it.init() }
                .reduce(Point::apply)
                .let { Math.abs(it.x) + Math.abs(it.y) }

        println(result)
    }

    fun partTwo() {
        val result = INPUT.split(", ")
                .map { Point(if (it[0] == 'R') 1 else -1, it.drop(1).toInt()) }
                .findIntersect()
                ?.let { Math.abs(it.x) + Math.abs(it.y) }

        println(result)
    }

    private fun List<Point>.findIntersect(): Point? {
        val points = ArrayList<Point>()
        points.add(Point())

        forEachIndexed { i, p ->
            if (i == 0) p.init()

            val last = points.last()
            val d = (last.d + p.d + 4) % 4
            var x = last.x
            var y = last.y

            for (j in 0..p.steps - 1) {
                x += p.xMod[d]
                y += p.yMod[d]
                val pnt = Point(d, 0, x, y)

                if (pnt in points) return pnt else points.add(pnt)
            }
        }

        return null
    }

    private data class Point(var d: Int = 0, var steps: Int = 0, var x: Int = 0, var y: Int = 0) {
        val xMod = intArrayOf(0, 1, 0, -1)
        val yMod = intArrayOf(1, 0, -1, 0)

        fun init() {
            d = (d + 4) % 4
            x += xMod[d] * steps
            y += yMod[d] * steps
        }

        fun apply(p: Point): Point {
            d = (d + p.d + 4) % 4
            x += xMod[d] * p.steps
            y += yMod[d] * p.steps
            return this
        }

        override fun equals(other: Any?): Boolean = other is Point && other.x == x && other.y == y
    }
}

