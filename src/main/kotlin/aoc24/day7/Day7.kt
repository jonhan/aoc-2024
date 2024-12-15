package se.hjonas.aoc24.day7

import se.hjonas.aoc24.utils.readInputLines
import se.hjonas.aoc24.utils.toLongs

fun main() {
    val input = readInputLines("day7")
    val result = input.filter { row ->
        row.trim().split(": ").let {
            it[0].toLong() in combinations(it[1])
        }
    }
        .map { it.substringBefore(":").toLong() }
        .sum()
    println("part2: $result")
}

fun combinations(input: String): List<Long> {
    fun generateCombination(result: Long, remaining: List<Long>): List<Long> {
        if (remaining.isEmpty()) {
            return listOf(result)
        }

        return generateCombination(result * remaining.first(), remaining.drop(1)) +
            generateCombination(result + remaining.first(), remaining.drop(1)) +
            generateCombination("$result${remaining.first()}".toLong(), remaining.drop(1))
    }

    val elem = input.split(" ").toLongs()
    return generateCombination(0, elem)
}

private val test = """
    190: 10 19
    3267: 81 40 27
    83: 17 5
    156: 15 6
    7290: 6 8 6 15 
    161011: 16 10 13
    192: 17 8 14
    21037: 9 7 18 13
    292: 11 6 16 20
""".trimIndent().lines()
