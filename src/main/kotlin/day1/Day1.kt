package day1

fun main(args: Array<String>) {
    partOne()
    partTwo()
}

private fun partOne() {
    val result = INPUT.toCharArray().map(::floor).sum()
    println(result)
}

private fun partTwo() {
    val result = INPUT.toCharArray().findFirstNegativeFloorIndex() + 1
    println(result)
}

private fun floor(c: Char): Int = if (c == '(') 1 else -1

private fun CharArray.findFirstNegativeFloorIndex(): Int {
    var currentFloor = 0
    forEachIndexed { i, c ->
        currentFloor += floor(c)
        if (currentFloor < 0) return i
    }

    throw IllegalStateException("Santa never goes under 0 floor")
}


