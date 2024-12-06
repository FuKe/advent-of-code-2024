package dk.vighdata

import kotlin.math.absoluteValue

fun main() {
    partOneTest()

    val puzzleInput = parseInput("Day02.txt")
    val resultPartOne = partOne(puzzleInput)
    println("Part One: $resultPartOne")

    partTwoTest()
    val resultPartTwo = partTwo(puzzleInput)
    println("Part Two: $resultPartTwo")
}

private fun partOneTest() {
    val input = parseInput("Day02_test.txt")
    val result = partOne(input)

    assert(result == 2)
    println("partOneTest successful!")
}

private fun partTwoTest() {
    val input = parseInput("Day02_test.txt")
    val result = partTwo(input)

    assert(result == 4) { println("Actual was $result")}
    println("partOneTest successful!")
}

private fun partOne(input: List<List<Int>>): Int {
    var safeReports = 0

    input.forEach { report ->
        if (isSafe(report)) {
            safeReports++
        }
    }

    return safeReports
}

private fun isSafe(report: List<Int>): Boolean {
    var increases = 0
    var decreases = 0
    var maxDifference = 0
    var minDifference = 10

    report.zipWithNext { a, b ->
        when {
            a < b -> increases++
            a > b -> decreases++
            else -> Unit
        }
        val diff = (a - b).absoluteValue
        if (diff > maxDifference) maxDifference = diff
        if (diff < minDifference) minDifference = diff
    }

    return (increases == 0 || decreases == 0) && maxDifference <= 3 && minDifference >= 1
}

private fun partTwo(input: List<List<Int>>): Int {
    var safeReports = 0

    input.forEach outer@{ report ->
        if (isSafe(report)) {
            safeReports++
        } else {
            report.forEachIndexed { index, _ ->
                val altered = report.toMutableList()
                altered.removeAt(index)
                if (isSafe(altered)) {
                    safeReports++
                    return@outer
                }
            }

        }
    }

    return safeReports
}

private fun parseInput(filename: String): List<List<Int>> {
    val raw: List<String> = readInput(filename)
    return raw.map { it ->
        it.split(" ")
            .filter { it.isNotBlank() }
            .map { it.toInt() }
    }
}
