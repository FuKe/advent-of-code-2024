package dk.vighdata

fun main() {
    partOneTest()

    val puzzleInput = readInput("Day03.txt").first()
    val resultPartOne = partOne(puzzleInput)
    println("Part One: $resultPartOne")

    partTwoTest()
    val resultPartTwo = partTwo(puzzleInput)
    println("Part Two: $resultPartTwo")
}

private fun partOneTest() {
    val input = readInput("Day03_test.txt").first()
    val result = partOne(input)

    assert(result == 161) { println("Test failed! Actual was: $result") }
    println("partOneTest successful!")
}

private fun partOne(input: String): Int {
    val regex = """mul\(\d{1,3},\d{1,3}\)""".toRegex()
    val result = regex.find(input)

    var sum = 0
    var match: MatchResult? = result!!
    while (match != null) {
        sum += mul(match.value)
        match = match.next()
    }

    return sum
}

private fun mul(mulStr: String): Int {
    println(mulStr)
    val regex = """\d{1,3},\d{1,3}""".toRegex()
    val (num1, num2) = regex.find(mulStr)!!
        .value.split(",")
        .map { it.toInt() }

    return num1 * num2
}

private fun partTwoTest(): Int {
    TODO()
}

private fun partTwo(input: String): Int {
    TODO()
}
