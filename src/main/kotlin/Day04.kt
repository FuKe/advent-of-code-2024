package dk.vighdata

private const val day: String = "Day04"

fun main() {
    partOneTest()

    val puzzleInput = parseInput("$day.txt")
    val resultPartOne = partOne(puzzleInput)
    println("Part One: $resultPartOne")

    partTwoTest()
    val resultPartTwo = partTwo(puzzleInput)
    println("Part Two: $resultPartTwo")
}

private fun partOneTest() {
    val input = parseInput("${day}_test.txt")
    val result = partOne(input)

    assert(result == 18) { println("Test failed! Actual was: $result") }
    println("partOneTest successful!")
}

private fun partOne(input: Map<Pair<Int, Int>, Char>): Int =
    scanHorizontally(input) +
            scanVertically(input) +
            scanDiagonally(input)

private fun scanHorizontally(input: Map<Pair<Int, Int>, Char>): Int {
    var count = 0

    input.map { (coordinate, char) ->
        val nextChars = setOf(1,2,3).mapNotNull {
            input[coordinate.copy(coordinate.first + it, coordinate.second)]
        }
        val previousChars = setOf(1,2,3).mapNotNull {
            input[coordinate.copy(coordinate.first - it, coordinate.second)]
        }
        count += detectXmas(char, nextChars, previousChars)
    }

    println("Horizontal count: $count")

    return count
}

private fun scanVertically(input: Map<Pair<Int, Int>, Char>): Int {
    var count = 0

    input.map { (coordinate, char) ->
        val nextChars = setOf(1,2,3).mapNotNull {
            input[coordinate.copy(coordinate.first, coordinate.second + it)]
        }
        val previousChars = setOf(1,2,3).mapNotNull {
            input[coordinate.copy(coordinate.first, coordinate.second - it)]
        }
        count += detectXmas(char, nextChars, previousChars)
    }

    println("Vertical count: $count")
    return count
}

private fun scanDiagonally(input: Map<Pair<Int, Int>, Char>): Int {
    var count = 0

    input.map { (coordinate, char) ->
        val rightDown = setOf(1,2,3).mapNotNull {
            input[coordinate.copy(coordinate.first + it, coordinate.second + it)]
        }
        val leftUp = setOf(1,2,3).mapNotNull {
            input[coordinate.copy(coordinate.first - it, coordinate.second - it)]
        }

        val rightUp = setOf(1,2,3).mapNotNull {
            input[coordinate.copy(coordinate.first + it, coordinate.second - it)]
        }
        val leftDown = setOf(1,2,3).mapNotNull {
            input[coordinate.copy(coordinate.first - it, coordinate.second + it)]
        }

        count += detectXmas(char, rightDown, leftUp)
        count += detectXmas(char, rightUp, leftDown)
    }

    println("Diagonal count: $count")

    return count
}

private fun detectXmas(current: Char, nextChars: List<Char>, previousChars: List<Char>): Int {
    var count = 0

    if (nextChars.size == 3) {
        val result = current + nextChars.joinToString("")
        if (result == "XMAS") count++
    }

    if (previousChars.size == 3) {
        val result =  current + previousChars.joinToString("")
        if (result == "XMAS") count++
    }

    return count
}

private fun partTwoTest() {
    val input = parseInput("${day}_test.txt")
    val result = partTwo(input)

    TODO()
    // assert(result == 48) { println("Test failed! Actual was: $result") }
    println("partTwoTest successful!")
}

private fun partTwo(input: Map<Pair<Int, Int>, Char>): Int {
    TODO()
}

private fun parseInput(filename: String): Map<Pair<Int, Int>, Char> =
    readInput(filename).mapIndexed { y, s ->
        s.mapIndexed { x, char ->
            Pair(x, y) to char
        }
    }.flatten().toMap()
