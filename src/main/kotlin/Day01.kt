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
    val input = parseInput("Day01_test.txt")
    val result = partOne(input)

    assert(result == 11)
    println("partOneTest successful!")
}

private fun partTwoTest() {
    val input = parseInput("Day01_test.txt")
    val result = partTwo(input)

    assert(result == 31)
    println("partTwoTest successful!")
}

private fun partOne(input: Pair<List<Int>, List<Int>>): Int {
    val left = input.first.sorted()
    val right = input.second.sorted()

    return left.mapIndexed { index, num ->
        (num - right[index]).absoluteValue
    }.sum()
}

private fun partTwo(input: Pair<List<Int>, List<Int>>): Int {
    val left = input.first
    val right = input.second

    return left.sumOf { l ->
        l * right.filter { r -> r == l }.size
    }
}

private fun parseInput(filename: String): Pair<List<Int>, List<Int>> {
    val raw: List<String> = readInput(filename)
    val left: MutableList<Int> = mutableListOf()
    val right: MutableList<Int> = mutableListOf()

    raw.forEach { str ->
        val parts = str.split(" ")
        left.add(parts[0].toInt())
        right.add(parts[3].toInt())
    }

    return Pair(left, right)
}

