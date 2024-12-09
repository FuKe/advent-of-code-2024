package dk.vighdata

fun main() {
    partOneTest()

    val puzzleInput = readInput("Day03.txt").joinToString(" ")
    val resultPartOne = partOne(puzzleInput)
    println("Part One: $resultPartOne")

    partTwoTest()
    val resultPartTwo = partTwo(puzzleInput)
    println("Part Two: $resultPartTwo")
}

private fun partOneTest() {
    val input = readInput("Day03_test.txt").joinToString(" ")
    val result = partOne(input)

    assert(result == 161) { println("Test failed! Actual was: $result") }
    println("partOneTest successful!")
}

private fun partOne(input: String): Int {
    val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    return regex.findAll(input)
        .asSequence()
        .sumOf { result ->
            result.groupValues[1].toInt() * result.groupValues[2].toInt()
        }
}

private fun partTwoTest() {
    val input = readInput("Day03_test.txt").joinToString(" ")
    val result = partTwo(input)

    assert(result == 48) { println("Test failed! Actual was: $result") }
    println("partTwoTest successful!")
}

private fun partTwo(input: String): Int {
    val doDontRegex = """(don't\(\)|do\(\))""".toRegex()

    val cleaned = doDontRegex.findAll(input).asSequence().zipWithNext { a, b ->
        input.substring(a.range.first, b.range.first)
    }.filterNot {
        it.startsWith("don't()")
    }.joinToString("")

    return partOne(cleaned)
}
