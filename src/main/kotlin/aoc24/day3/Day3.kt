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
    println("part 1: $matches")
    part2(input)
}

fun part2(input: String) {
    val regex = """do\(\)|don't\(\)|mul\(\d{1,3},\d{1,3}\)""".toRegex()
    var isActive = true

    val result = regex
        .findAll(input)
        .mapNotNull {
            when {
                it.value.contains("don") -> Operation.Deactivate
                it.value.contains("do()") -> Operation.Activate
                it.value.toMul() != null -> it.value.toMul()
                else -> null
            }
        }.filter {
            when (it) {
                is Operation.Mul -> isActive
                is Operation.Activate -> {
                    isActive = true
                    false
                }

                is Operation.Deactivate -> {
                    isActive = false
                    false
                }
            }
        }.filterIsInstance<Operation.Mul>()
        .map { (first, second) -> first * second }
        .sum()

    println("part 2: $result")
}

sealed interface Operation {
    data object Activate : Operation
    data object Deactivate : Operation
    data class Mul(val first: Int, val second: Int) : Operation
}

fun String.toMul(): Operation.Mul? {
    val regex = """mul\(\d{1,3},\d{1,3}\)""".toRegex()
    return if (regex.matches(this)) {
        this.substringAfter("(")
            .substringBefore(")")
            .split(",")
            .let { Operation.Mul(it[0].toInt(), it[1].toInt()) }
    } else {
        null
    }
}
