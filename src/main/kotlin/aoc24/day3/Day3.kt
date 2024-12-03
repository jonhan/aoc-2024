package se.hjonas.aoc24.day3

import se.hjonas.aoc24.utils.readInputText


fun main() {
    val input = readInputText("day3")
    val regex = """mul\(\d{1,3},\d{1,3}\)""".toRegex()

    val matches = regex.findAll(input)
        .map {
            it.value
                .substringAfter("(")
                .substringBefore(")")
                .split(",")
                .let { it[0].toInt() * it[1].toInt() }
        }
        .sum()
    println("groups: $matches")
}
