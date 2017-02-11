package day1

fun main(args: Array<String>) {
    val solution = Day1()
    solution.partOne()
    solution.partTwo()
}

class Day1 {

    fun main(args: Array<String>) {
        partOne()
        partTwo()
    }

    fun partOne() {
        val result = INPUT.toCharArray().map { floor(it) }.sum()
        println(result)
    }

    fun partTwo() {
        val result = INPUT.toCharArray().findFirstNegativeFloorIndex() + 1
        println(result)
    }

    fun floor(c: Char): Int = if (c == '(') 1 else -1

    fun CharArray.findFirstNegativeFloorIndex(): Int {
        var currentFloor = 0
        forEachIndexed { i, c ->
            currentFloor += floor(c)
            if (currentFloor < 0) return i
        }

        throw IllegalStateException("Santa never goes under 0 floor")
    }
}





